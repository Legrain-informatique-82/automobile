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

import fr.legrain.bdg.model.mapping.mapper.LignePanierMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.ILignePanierServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.ILignePanierDAO;
import fr.legrain.tiers.dto.LignePanierDTO;
import fr.legrain.tiers.model.LignePanier;

/**
 * Session Bean implementation class LignePanierBean
 */
@Stateless
public class LignePanierService extends AbstractApplicationDAOServer<LignePanier, LignePanierDTO> implements ILignePanierServiceRemote {

	static Logger logger = Logger.getLogger(LignePanierService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private ILignePanierDAO dao;

	/**
	 * Default constructor. 
	 */
	public LignePanierService() {
		// TODO Auto-generated constructor stub
	}
	
	//	private String defaultJPQLQuery = "select a from LignePanier a";

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(LignePanier transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(LignePanier persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public LignePanier merge(LignePanier detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public LignePanier findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public LignePanier findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<LignePanier> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<LignePanierDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<LignePanierDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<LignePanier> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<LignePanierDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public LignePanierDTO entityToDTO(LignePanier entity) {
//		LignePanierDTO dto = new LignePanierDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		LignePanierMapper mapper = new LignePanierMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<LignePanierDTO> listEntityToListDTO(List<LignePanier> entity) {
		List<LignePanierDTO> l = new ArrayList<LignePanierDTO>();

		for (LignePanier LignePanier : entity) {
			l.add(entityToDTO(LignePanier));
		}

		return l;
	}

	public List<LignePanierDTO> selectAllDTO() {
		System.out.println("List of LignePanierDTO EJB :");
		ArrayList<LignePanierDTO> liste = new ArrayList<LignePanierDTO>();

		List<LignePanier> projects = selectAll();
		for(LignePanier project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public LignePanierDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public LignePanierDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(LignePanierDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(LignePanierDTO dto) throws EJBException {
		try {
			LignePanierMapper mapper = new LignePanierMapper();
			LignePanier entity = null;
//			if(dto.getId()!=null) {
//				entity = dao.findById(dto.getId());
//				if(dto.getVersionObj()!=entity.getVersionObj()) {
//					throw new OptimisticLockException(entity,
//							"L'objet à été modifié depuis le dernier accés. LignePanier ID : "+dto.getId()+" - LignePanier Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
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
	public void persistDTO(LignePanierDTO dto) throws CreateException {
		try {
			LignePanierMapper mapper = new LignePanierMapper();
			LignePanier entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(LignePanierDTO dto) throws RemoveException {
		try {
			LignePanierMapper mapper = new LignePanierMapper();
			LignePanier entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected LignePanier refresh(LignePanier persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(LignePanier value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(LignePanier value, String propertyName) {
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
	public void validateDTO(LignePanierDTO dto) {
		try {
			LignePanierMapper mapper = new LignePanierMapper();
			LignePanier entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<LignePanierDTO> validator = new BeanValidator<LignePanierDTO>(LignePanierDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(LignePanierDTO dto, String propertyName) {
		try {
			LignePanierMapper mapper = new LignePanierMapper();
			LignePanier entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<LignePanierDTO> validator = new BeanValidator<LignePanierDTO>(LignePanierDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
