package fr.legrain.tiers.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import fr.legrain.bdg.model.mapping.mapper.MessageMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IMessageServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IMessageDAO;
import fr.legrain.tiers.dto.MessageDTO;
import fr.legrain.tiers.model.Message;

/**
 * Session Bean implementation class MessageBean
 */
//@Stateless(name="MessageBean")
@Stateless
//@Remote(MessageBeanRemote.class)
@DeclareRoles("admin")
@WebService
//public class MessageService extends AbstractLgrDAOServer<Message> implements IMessageServiceRemote {
public class MessageService extends AbstractApplicationDAOServer<Message, MessageDTO> implements IMessageServiceRemote {

	static Logger logger = Logger.getLogger(MessageService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IMessageDAO dao;

	/**
	 * Default constructor. 
	 */
	public MessageService() {
		// TODO Auto-generated constructor stub
	}
	
	//	private String defaultJPQLQuery = "select a from Message a";
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List<Message> selectMessageFrom(int idUser) {
		return dao.selectMessageFrom(idUser);
	}
	
	public List<Message> selectMessageTo(int idUser) {
		return dao.selectMessageTo(idUser);
	}
	
	public Integer getNbNouveauMessage(int idUser) {
		return dao.getNbNouveauMessage(idUser);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Message transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(Message persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public Message merge(Message detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		try {
			//Pour éviter "IllegalStateException: An entity copy was already assigned to a different entity."
			detachedInstance.setDe(userService.findById(detachedInstance.getDe().getId()));
			detachedInstance.setA(userService.findById(detachedInstance.getA().getId()));
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public Message findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public Message findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	@RolesAllowed("admin")
	public List<Message> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<MessageDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<MessageDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<Message> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<MessageDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public MessageDTO entityToDTO(Message entity) {
//		MessageDTO dto = new MessageDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		MessageMapper mapper = new MessageMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<MessageDTO> listEntityToListDTO(List<Message> entity) {
		List<MessageDTO> l = new ArrayList<MessageDTO>();

		for (Message Message : entity) {
			l.add(entityToDTO(Message));
		}

		return l;
	}

	@RolesAllowed("admin")
	public List<MessageDTO> selectAllDTO() {
		System.out.println("List of MessageDTO EJB :");
		ArrayList<MessageDTO> liste = new ArrayList<MessageDTO>();

		List<Message> projects = selectAll();
		for(Message project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public MessageDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public MessageDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(MessageDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(MessageDTO dto) throws EJBException {
		try {
			MessageMapper mapper = new MessageMapper();
			Message entity = null;
//			if(dto.getId()!=null) {
//				entity = dao.findById(dto.getId());
//				if(dto.getVersionObj()!=entity.getVersionObj()) {
//					throw new OptimisticLockException(entity,
//							"L'objet à été modifié depuis le dernier accés. Message ID : "+dto.getId()+" - Message Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
//				} else {
//					 entity = mapper.mapDtoToEntity(dto,entity);
//				}
//			}
			
			//dao.merge(entity);
			dao.detach(entity); //pour passer les controles
			enregistrerMerge(entity);
		} catch (Exception e) {
			e.printStackTrace();
			//throw new CreateException(e.getMessage());
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void persistDTO(MessageDTO dto) throws CreateException {
		try {
			MessageMapper mapper = new MessageMapper();
			Message entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(MessageDTO dto) throws RemoveException {
		try {
			MessageMapper mapper = new MessageMapper();
			Message entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected Message refresh(Message persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(Message value) /*throws ExceptLgr*/ {
		try {
			String validationContext = "";
			validateAll(value,validationContext,false); //ancienne validation, extraction de l'annotation et appel
			//dao.validate(value); //validation automatique via la JSR bean validation
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateEntityProperty(Message value, String propertyName) {
		try {
			String validationContext = "";
			validate(value, propertyName, validationContext);
			//dao.validateField(value,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTO(MessageDTO dto) {
		try {
			MessageMapper mapper = new MessageMapper();
			Message entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<MessageDTO> validator = new BeanValidator<MessageDTO>(MessageDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(MessageDTO dto, String propertyName) {
		try {
			MessageMapper mapper = new MessageMapper();
			Message entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<MessageDTO> validator = new BeanValidator<MessageDTO>(MessageDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
