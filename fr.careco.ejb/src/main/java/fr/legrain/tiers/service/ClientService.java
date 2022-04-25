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

import fr.legrain.bdg.model.mapping.mapper.ClientMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IClientServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IClientDAO;
import fr.legrain.tiers.dto.ClientDTO;
import fr.legrain.tiers.model.Client;

/**
 * Session Bean implementation class ClientBean
 */
@Stateless
@WebService
public class ClientService extends AbstractApplicationDAOServer<Client, ClientDTO> implements IClientServiceRemote {

	static Logger logger = Logger.getLogger(ClientService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IClientDAO dao;

	/**
	 * Default constructor. 
	 */
	public ClientService() {
		// TODO Auto-generated constructor stub
	}
	
	//	private String defaultJPQLQuery = "select a from Client a";

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Client transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(Client persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public Client merge(Client detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public Client findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public Client findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<Client> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<ClientDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<ClientDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<Client> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<ClientDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public ClientDTO entityToDTO(Client entity) {
//		ClientDTO dto = new ClientDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		ClientMapper mapper = new ClientMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<ClientDTO> listEntityToListDTO(List<Client> entity) {
		List<ClientDTO> l = new ArrayList<ClientDTO>();

		for (Client Client : entity) {
			l.add(entityToDTO(Client));
		}

		return l;
	}

	public List<ClientDTO> selectAllDTO() {
		System.out.println("List of ClientDTO EJB :");
		ArrayList<ClientDTO> liste = new ArrayList<ClientDTO>();

		List<Client> projects = selectAll();
		for(Client project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public ClientDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public ClientDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(ClientDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(ClientDTO dto) throws EJBException {
		try {
			ClientMapper mapper = new ClientMapper();
			Client entity = null;
//			if(dto.getId()!=null) {
//				entity = dao.findById(dto.getId());
//				if(dto.getVersionObj()!=entity.getVersionObj()) {
//					throw new OptimisticLockException(entity,
//							"L'objet à été modifié depuis le dernier accés. Client ID : "+dto.getId()+" - Client Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
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
	public void persistDTO(ClientDTO dto) throws CreateException {
		try {
			ClientMapper mapper = new ClientMapper();
			Client entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(ClientDTO dto) throws RemoveException {
		try {
			ClientMapper mapper = new ClientMapper();
			Client entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected Client refresh(Client persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(Client value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(Client value, String propertyName) {
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
	public void validateDTO(ClientDTO dto) {
		try {
			ClientMapper mapper = new ClientMapper();
			Client entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<ClientDTO> validator = new BeanValidator<ClientDTO>(ClientDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(ClientDTO dto, String propertyName) {
		try {
			ClientMapper mapper = new ClientMapper();
			Client entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<ClientDTO> validator = new BeanValidator<ClientDTO>(ClientDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
