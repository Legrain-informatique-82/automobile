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

import fr.legrain.bdg.model.mapping.mapper.AdherentCarecoMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IAdherentCarecoServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IAdherentCarecoDAO;
import fr.legrain.tiers.dto.AdherentCarecoDTO;
import fr.legrain.tiers.model.Adherent;

/**
 * Session Bean implementation class AdherentCarecoBean
 */
@Stateless
public class AdherentCarecoService extends AbstractApplicationDAOServer<Adherent, AdherentCarecoDTO> implements IAdherentCarecoServiceRemote {

	static Logger logger = Logger.getLogger(AdherentCarecoService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IAdherentCarecoDAO dao;

	/**
	 * Default constructor. 
	 */
	public AdherentCarecoService() {
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
	public void persist(Adherent transientInstance) throws CreateException {
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(Adherent persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public Adherent merge(Adherent detachedInstance) {
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public Adherent findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public Adherent findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<Adherent> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<AdherentCarecoDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<AdherentCarecoDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<Adherent> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<AdherentCarecoDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public AdherentCarecoDTO entityToDTO(Adherent entity) {
//		AdherentCarecoDTO dto = new AdherentCarecoDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		AdherentCarecoMapper mapper = new AdherentCarecoMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<AdherentCarecoDTO> listEntityToListDTO(List<Adherent> entity) {
		List<AdherentCarecoDTO> l = new ArrayList<AdherentCarecoDTO>();

		for (Adherent AdherentCareco : entity) {
			l.add(entityToDTO(AdherentCareco));
		}

		return l;
	}

	public List<AdherentCarecoDTO> selectAllDTO() {
		System.out.println("List of AdherentCarecoDTO EJB :");
		ArrayList<AdherentCarecoDTO> liste = new ArrayList<AdherentCarecoDTO>();

		List<Adherent> projects = selectAll();
		for(Adherent project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public AdherentCarecoDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public AdherentCarecoDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(AdherentCarecoDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(AdherentCarecoDTO dto) throws EJBException {
		try {
			AdherentCarecoMapper mapper = new AdherentCarecoMapper();
			Adherent entity = null;
//			if(dto.getId()!=null) {
//				entity = dao.findById(dto.getId());
//				if(dto.getVersionObj()!=entity.getVersionObj()) {
//					throw new OptimisticLockException(entity,
//							"L'objet à été modifié depuis le dernier accés. AdherentCareco ID : "+dto.getId()+" - AdherentCareco Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
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
	public void persistDTO(AdherentCarecoDTO dto) throws CreateException {
		try {
			AdherentCarecoMapper mapper = new AdherentCarecoMapper();
			Adherent entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(AdherentCarecoDTO dto) throws RemoveException {
		try {
			AdherentCarecoMapper mapper = new AdherentCarecoMapper();
			Adherent entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected Adherent refresh(Adherent persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(Adherent value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(Adherent value, String propertyName) {
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
	public void validateDTO(AdherentCarecoDTO dto) {
		try {
			AdherentCarecoMapper mapper = new AdherentCarecoMapper();
			Adherent entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<AdherentCarecoDTO> validator = new BeanValidator<AdherentCarecoDTO>(AdherentCarecoDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(AdherentCarecoDTO dto, String propertyName) {
		try {
			AdherentCarecoMapper mapper = new AdherentCarecoMapper();
			Adherent entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<AdherentCarecoDTO> validator = new BeanValidator<AdherentCarecoDTO>(AdherentCarecoDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}

}
