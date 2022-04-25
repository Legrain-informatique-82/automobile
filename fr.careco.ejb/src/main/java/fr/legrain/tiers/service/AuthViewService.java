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

import fr.legrain.bdg.model.mapping.mapper.AuthViewMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IAuthViewServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IAuthViewDAO;
import fr.legrain.tiers.dto.AuthViewDTO;
import fr.legrain.tiers.model.AuthView;

/**
 * Session Bean implementation class AuthViewBean
 */
@Stateless
public class AuthViewService extends AbstractApplicationDAOServer<AuthView, AuthViewDTO> implements IAuthViewServiceRemote {

	static Logger logger = Logger.getLogger(AuthViewService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IAuthViewDAO dao;

	/**
	 * Default constructor. 
	 */
	public AuthViewService() {
		// TODO Auto-generated constructor stub
	}
	
	//	private String defaultJPQLQuery = "select a from AuthView a";

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(AuthView transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(AuthView persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public AuthView merge(AuthView detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public AuthView findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public AuthView findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<AuthView> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<AuthViewDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<AuthViewDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<AuthView> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<AuthViewDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public AuthViewDTO entityToDTO(AuthView entity) {
//		AuthViewDTO dto = new AuthViewDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		AuthViewMapper mapper = new AuthViewMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<AuthViewDTO> listEntityToListDTO(List<AuthView> entity) {
		List<AuthViewDTO> l = new ArrayList<AuthViewDTO>();

		for (AuthView AuthView : entity) {
			l.add(entityToDTO(AuthView));
		}

		return l;
	}

	public List<AuthViewDTO> selectAllDTO() {
		System.out.println("List of AuthViewDTO EJB :");
		ArrayList<AuthViewDTO> liste = new ArrayList<AuthViewDTO>();

		List<AuthView> projects = selectAll();
		for(AuthView project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public AuthViewDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public AuthViewDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(AuthViewDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(AuthViewDTO dto) throws EJBException {
		try {
			AuthViewMapper mapper = new AuthViewMapper();
			AuthView entity = null;
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
	public void persistDTO(AuthViewDTO dto) throws CreateException {
		try {
			AuthViewMapper mapper = new AuthViewMapper();
			AuthView entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(AuthViewDTO dto) throws RemoveException {
		try {
			AuthViewMapper mapper = new AuthViewMapper();
			AuthView entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected AuthView refresh(AuthView persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(AuthView value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(AuthView value, String propertyName) {
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
	public void validateDTO(AuthViewDTO dto) {
		try {
			AuthViewMapper mapper = new AuthViewMapper();
			AuthView entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<AuthViewDTO> validator = new BeanValidator<AuthViewDTO>(AuthViewDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(AuthViewDTO dto, String propertyName) {
		try {
			AuthViewMapper mapper = new AuthViewMapper();
			AuthView entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<AuthViewDTO> validator = new BeanValidator<AuthViewDTO>(AuthViewDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}
	
	@Override
	public List<AuthView> findByRoleID(int roleID) {
		return dao.findByRoleID(roleID);
	}

}
