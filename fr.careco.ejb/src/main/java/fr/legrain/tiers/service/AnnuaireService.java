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

import fr.legrain.bdg.model.mapping.mapper.AnnuaireMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IAnnuaireServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IAnnuaireDAO;
import fr.legrain.tiers.dto.AnnuaireDTO;
import fr.legrain.tiers.model.Annuaire;

/**
 * Session Bean implementation class AnnuaireBean
 */
@Stateless
public class AnnuaireService extends AbstractApplicationDAOServer<Annuaire, AnnuaireDTO> implements IAnnuaireServiceRemote {

	static Logger logger = Logger.getLogger(AnnuaireService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IAnnuaireDAO dao;

	/**
	 * Default constructor. 
	 */
	public AnnuaireService() {
		// TODO Auto-generated constructor stub
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List<String> selectAll(String debut) {
		return dao.selectAll(debut);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Annuaire transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(Annuaire persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public Annuaire merge(Annuaire detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public Annuaire findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public Annuaire findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<Annuaire> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<AnnuaireDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<AnnuaireDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<Annuaire> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<AnnuaireDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public AnnuaireDTO entityToDTO(Annuaire entity) {
//		AnnuaireDTO dto = new AnnuaireDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		AnnuaireMapper mapper = new AnnuaireMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<AnnuaireDTO> listEntityToListDTO(List<Annuaire> entity) {
		List<AnnuaireDTO> l = new ArrayList<AnnuaireDTO>();

		for (Annuaire Annuaire : entity) {
			l.add(entityToDTO(Annuaire));
		}

		return l;
	}

	public List<AnnuaireDTO> selectAllDTO() {
		System.out.println("List of AnnuaireDTO EJB :");
		ArrayList<AnnuaireDTO> liste = new ArrayList<AnnuaireDTO>();

		List<Annuaire> projects = selectAll();
		for(Annuaire project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public AnnuaireDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public AnnuaireDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(AnnuaireDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(AnnuaireDTO dto) throws EJBException {
		try {
			AnnuaireMapper mapper = new AnnuaireMapper();
			Annuaire entity = null;
//			if(dto.getId()!=null) {
//				entity = dao.findById(dto.getId());
//				if(dto.getVersionObj()!=entity.getVersionObj()) {
//					throw new OptimisticLockException(entity,
//							"L'objet à été modifié depuis le dernier accés. Annuaire ID : "+dto.getId()+" - Annuaire Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
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
	public void persistDTO(AnnuaireDTO dto) throws CreateException {
		try {
			AnnuaireMapper mapper = new AnnuaireMapper();
			Annuaire entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(AnnuaireDTO dto) throws RemoveException {
		try {
			AnnuaireMapper mapper = new AnnuaireMapper();
			Annuaire entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected Annuaire refresh(Annuaire persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(Annuaire value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(Annuaire value, String propertyName) {
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
	public void validateDTO(AnnuaireDTO dto) {
		try {
			AnnuaireMapper mapper = new AnnuaireMapper();
			Annuaire entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<AnnuaireDTO> validator = new BeanValidator<AnnuaireDTO>(AnnuaireDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(AnnuaireDTO dto, String propertyName) {
		try {
			AnnuaireMapper mapper = new AnnuaireMapper();
			Annuaire entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<AnnuaireDTO> validator = new BeanValidator<AnnuaireDTO>(AnnuaireDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
