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

import fr.legrain.bdg.model.mapping.mapper.RoleMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IRoleServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IRoleDAO;
import fr.legrain.tiers.dto.RoleDTO;
import fr.legrain.tiers.model.Role;

/**
 * Session Bean implementation class RoleBean
 */
@Stateless
public class RoleService extends AbstractApplicationDAOServer<Role, RoleDTO> implements IRoleServiceRemote {

	static Logger logger = Logger.getLogger(RoleService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IRoleDAO dao;

	/**
	 * Default constructor. 
	 */
	public RoleService() {
		// TODO Auto-generated constructor stub
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Role transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(Role persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public Role merge(Role detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public Role findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public Role findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<Role> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<RoleDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<RoleDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<Role> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<RoleDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public RoleDTO entityToDTO(Role entity) {
//		RoleDTO dto = new RoleDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		RoleMapper mapper = new RoleMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<RoleDTO> listEntityToListDTO(List<Role> entity) {
		List<RoleDTO> l = new ArrayList<RoleDTO>();

		for (Role Role : entity) {
			l.add(entityToDTO(Role));
		}

		return l;
	}

	public List<RoleDTO> selectAllDTO() {
		System.out.println("List of RoleDTO EJB :");
		ArrayList<RoleDTO> liste = new ArrayList<RoleDTO>();

		List<Role> projects = selectAll();
		for(Role project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public RoleDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public RoleDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(RoleDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(RoleDTO dto) throws EJBException {
		try {
			RoleMapper mapper = new RoleMapper();
			Role entity = null;
//			if(dto.getId()!=null) {
//				entity = dao.findById(dto.getId());
//				if(dto.getVersionObj()!=entity.getVersionObj()) {
//					throw new OptimisticLockException(entity,
//							"L'objet à été modifié depuis le dernier accés. Role ID : "+dto.getId()+" - Role Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
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
	public void persistDTO(RoleDTO dto) throws CreateException {
		try {
			RoleMapper mapper = new RoleMapper();
			Role entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(RoleDTO dto) throws RemoveException {
		try {
			RoleMapper mapper = new RoleMapper();
			Role entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected Role refresh(Role persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(Role value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(Role value, String propertyName) {
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
	public void validateDTO(RoleDTO dto) {
		try {
			RoleMapper mapper = new RoleMapper();
			Role entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<RoleDTO> validator = new BeanValidator<RoleDTO>(RoleDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(RoleDTO dto, String propertyName) {
		try {
			RoleMapper mapper = new RoleMapper();
			Role entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<RoleDTO> validator = new BeanValidator<RoleDTO>(RoleDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
