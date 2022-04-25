package fr.legrain.tiers.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import fr.legrain.bdg.model.mapping.mapper.UserMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IUserServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IUserDAO;
import fr.legrain.tiers.dto.UserDTO;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;

/**
 * Session Bean implementation class UserBean
 */
@Stateless
public class UsersService extends AbstractApplicationDAOServer<User, UserDTO> implements IUserServiceRemote, IUserServiceLocal {

	static Logger logger = Logger.getLogger(UsersService.class);
	
	@Resource 
	private SessionContext context;

	@Inject private IUserDAO dao;

	/**
	 * Default constructor. 
	 */
	public UsersService() {
		// TODO Auto-generated constructor stub
	}
	
	//	private String defaultJPQLQuery = "select a from User a";

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public User findUserLogged() {
		User u = null;
		u = dao.findByCode(context.getCallerPrincipal().getName());
		return u;
	}
	
	public Integer findUserLoggedId() {
		Integer userId = null;
		User u = null;
		u = findUserLogged();
		if(u!=null) {
			userId = u.getId();
		}
		return userId;
	}

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(User transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(findUserLoggedId());
//		transientInstance.setQuiModif(findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(User persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public User merge(User detachedInstance) {
		
//		detachedInstance.setQuiCree(findUserLoggedId());
//		detachedInstance.setQuiModif(findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public User findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public User findByCode(String code) throws FinderException {
		return chargeSocietesAmie(dao.findByCode(code));
	}

	public List<User> selectAll() {
		return dao.selectAll();
	}
	
	public List<User> selectAll(String debut) {
		return dao.selectAll(debut);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<UserDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<UserDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<User> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<UserDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public UserDTO entityToDTO(User entity) {
//		UserDTO dto = new UserDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		UserMapper mapper = new UserMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<UserDTO> listEntityToListDTO(List<User> entity) {
		List<UserDTO> l = new ArrayList<UserDTO>();

		for (User User : entity) {
			l.add(entityToDTO(User));
		}

		return l;
	}

	public List<UserDTO> selectAllDTO() {
		System.out.println("List of UserDTO EJB :");
		ArrayList<UserDTO> liste = new ArrayList<UserDTO>();

		List<User> projects = selectAll();
		for(User project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public UserDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public UserDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(UserDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(UserDTO dto) throws EJBException {
		try {
			UserMapper mapper = new UserMapper();
			User entity = null;
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
	public void persistDTO(UserDTO dto) throws CreateException {
		try {
			UserMapper mapper = new UserMapper();
			User entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(UserDTO dto) throws RemoveException {
		try {
			UserMapper mapper = new UserMapper();
			User entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected User refresh(User persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(User value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(User value, String propertyName) {
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
	public void validateDTO(UserDTO dto) {
		try {
			UserMapper mapper = new UserMapper();
			User entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<UserDTO> validator = new BeanValidator<UserDTO>(UserDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(UserDTO dto, String propertyName) {
		try {
			UserMapper mapper = new UserMapper();
			User entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<UserDTO> validator = new BeanValidator<UserDTO>(UserDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}
	
	public User chargeSocietesAmie(User u) {
		if(u!=null) {
			//appel des get pour accéder aux données afin d'éviter d'avoir besoin d'une connection sur la base depuis la vue
			//voir si on peut faire un chargement EAGER JPA pour eviter cela.
			if(u.getUserCompany().getAmies()!=null) {
				for (SocietesAmies amie : u.getUserCompany().getAmies()) {
					amie.getSocieteB().getNom();
				}
			}
		}
		return u;
	}

}
