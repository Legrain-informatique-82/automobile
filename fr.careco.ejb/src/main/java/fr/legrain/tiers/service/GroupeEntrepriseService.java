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

import fr.legrain.bdg.model.mapping.mapper.GroupeEntrepriseMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IGroupeEntrepriseServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IGroupeEntrepriseDAO;
import fr.legrain.tiers.dto.GroupeEntrepriseDTO;
import fr.legrain.tiers.model.GroupeEntreprise;

/**
 * Session Bean implementation class GroupeEntrepriseBean
 */
@Stateless
public class GroupeEntrepriseService extends AbstractApplicationDAOServer<GroupeEntreprise, GroupeEntrepriseDTO> implements IGroupeEntrepriseServiceRemote {

	static Logger logger = Logger.getLogger(GroupeEntrepriseService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IGroupeEntrepriseDAO dao;

	/**
	 * Default constructor. 
	 */
	public GroupeEntrepriseService() {
		// TODO Auto-generated constructor stub
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(GroupeEntreprise transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(GroupeEntreprise persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public GroupeEntreprise merge(GroupeEntreprise detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public GroupeEntreprise findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public GroupeEntreprise findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<GroupeEntreprise> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<GroupeEntrepriseDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<GroupeEntrepriseDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<GroupeEntreprise> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<GroupeEntrepriseDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public GroupeEntrepriseDTO entityToDTO(GroupeEntreprise entity) {
//		GroupeEntrepriseDTO dto = new GroupeEntrepriseDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		GroupeEntrepriseMapper mapper = new GroupeEntrepriseMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<GroupeEntrepriseDTO> listEntityToListDTO(List<GroupeEntreprise> entity) {
		List<GroupeEntrepriseDTO> l = new ArrayList<GroupeEntrepriseDTO>();

		for (GroupeEntreprise GroupeEntreprise : entity) {
			l.add(entityToDTO(GroupeEntreprise));
		}

		return l;
	}

	public List<GroupeEntrepriseDTO> selectAllDTO() {
		System.out.println("List of GroupeEntrepriseDTO EJB :");
		ArrayList<GroupeEntrepriseDTO> liste = new ArrayList<GroupeEntrepriseDTO>();

		List<GroupeEntreprise> projects = selectAll();
		for(GroupeEntreprise project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public GroupeEntrepriseDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public GroupeEntrepriseDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(GroupeEntrepriseDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(GroupeEntrepriseDTO dto) throws EJBException {
		try {
			GroupeEntrepriseMapper mapper = new GroupeEntrepriseMapper();
			GroupeEntreprise entity = null;
//			if(dto.getId()!=null) {
//				entity = dao.findById(dto.getId());
//				if(dto.getVersionObj()!=entity.getVersionObj()) {
//					throw new OptimisticLockException(entity,
//							"L'objet à été modifié depuis le dernier accés. GroupeEntreprise ID : "+dto.getId()+" - GroupeEntreprise Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
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
	public void persistDTO(GroupeEntrepriseDTO dto) throws CreateException {
		try {
			GroupeEntrepriseMapper mapper = new GroupeEntrepriseMapper();
			GroupeEntreprise entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(GroupeEntrepriseDTO dto) throws RemoveException {
		try {
			GroupeEntrepriseMapper mapper = new GroupeEntrepriseMapper();
			GroupeEntreprise entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected GroupeEntreprise refresh(GroupeEntreprise persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(GroupeEntreprise value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(GroupeEntreprise value, String propertyName) {
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
	public void validateDTO(GroupeEntrepriseDTO dto) {
		try {
			GroupeEntrepriseMapper mapper = new GroupeEntrepriseMapper();
			GroupeEntreprise entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<GroupeEntrepriseDTO> validator = new BeanValidator<GroupeEntrepriseDTO>(GroupeEntrepriseDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(GroupeEntrepriseDTO dto, String propertyName) {
		try {
			GroupeEntrepriseMapper mapper = new GroupeEntrepriseMapper();
			GroupeEntreprise entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<GroupeEntrepriseDTO> validator = new BeanValidator<GroupeEntrepriseDTO>(GroupeEntrepriseDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
