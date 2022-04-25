package fr.legrain.tiers.service;

import java.math.BigDecimal;
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

import fr.legrain.bdg.model.mapping.mapper.UserCompanyMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.ISocietesAmiesDAO;
import fr.legrain.tiers.dao.IUserCompanyDAO;
import fr.legrain.tiers.dto.UserCompanyDTO;
import fr.legrain.tiers.model.ImportStockTemp;
import fr.legrain.tiers.model.Panier;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.UserCompany;

/**
 * Session Bean implementation class UserCompanyBean
 */
//@Stateless(name="UserCompanyBean")
@Stateless
public class UserCompanyService extends AbstractApplicationDAOServer<UserCompany, UserCompanyDTO> implements IUserCompanyServiceRemote {

	static Logger logger = Logger.getLogger(UserCompanyService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IUserCompanyDAO dao;
	@Inject private ISocietesAmiesDAO daoSocietesAmies;

	/**
	 * Default constructor. 
	 */
	public UserCompanyService() {
		// TODO Auto-generated constructor stub
	}
	
	//	private String defaultJPQLQuery = "select a from UserCompany a";
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List<SocietesAmies> chargeDonnees(List<SocietesAmies> liste) {
		if(liste!=null) {
			//List<Panier> l = dao.findUserAchatEnCours(user);
			for (SocietesAmies p : liste) {
				//appel des get pour accéder aux données afin d'éviter d'avoir besoin d'une connection sur la base depuis la vue
				//voir si on peut faire un chargement EAGER JPA pour eviter cela.
				p.getSocieteB().getNom();
				p.getSocieteA().getNom();
			}
		}
		return liste;
	}
	
	public SocietesAmies findByIdSocieteAmie(int idSocietesAmies) throws FinderException {
		return daoSocietesAmies.findById(idSocietesAmies);
	}
	
	public List<SocietesAmies> findSocieteAmie(int idUserCompany) {
		return chargeDonnees(daoSocietesAmies.findSocieteAmie(idUserCompany));
	}
	
	public List<SocietesAmies> findSocieteAmieAjoutee(int idUserCompany) {
		return chargeDonnees(daoSocietesAmies.findSocieteAmieAjoutee(idUserCompany));
	}
	
	public List<SocietesAmies> findSocieteMultiSite(int idUserCompany) {
		return chargeDonnees(daoSocietesAmies.findSocieteMultiSite(idUserCompany));
	}
	
	public void removeSocietesAmies(SocietesAmies persistentInstance) throws RemoveException {
		daoSocietesAmies.remove(persistentInstance);
	}

	public SocietesAmies persistSocietesAmies(SocietesAmies detachedInstance) {
		
		SocietesAmies nouvelleSocieteAmies = new SocietesAmies();
    	//ajouter mon entreprise dans la liste des société amies de l'entreprise sélectionnée.
    	nouvelleSocieteAmies.setSocieteA(dao.findById(detachedInstance.getSocieteA().getId()));
    	nouvelleSocieteAmies.setSocieteB(dao.findById(detachedInstance.getSocieteB().getId()));
    	nouvelleSocieteAmies.setPourcentageReduction(detachedInstance.getPourcentageReduction());
    	nouvelleSocieteAmies.setTypeRelation(detachedInstance.getTypeRelation());
    	return daoSocietesAmies.merge(nouvelleSocieteAmies);
		
		//return daoSocietesAmies.merge(detachedInstance);
	}
	
	public SocietesAmies mergeSocietesAmies(SocietesAmies detachedInstance) {

    	return daoSocietesAmies.merge(detachedInstance);
		
		//return daoSocietesAmies.merge(detachedInstance);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(UserCompany transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(UserCompany persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public UserCompany merge(UserCompany detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public UserCompany findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public UserCompany findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<UserCompany> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<UserCompanyDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<UserCompanyDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<UserCompany> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<UserCompanyDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public UserCompanyDTO entityToDTO(UserCompany entity) {
//		UserCompanyDTO dto = new UserCompanyDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		UserCompanyMapper mapper = new UserCompanyMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<UserCompanyDTO> listEntityToListDTO(List<UserCompany> entity) {
		List<UserCompanyDTO> l = new ArrayList<UserCompanyDTO>();

		for (UserCompany UserCompany : entity) {
			l.add(entityToDTO(UserCompany));
		}

		return l;
	}

	public List<UserCompanyDTO> selectAllDTO() {
		System.out.println("List of UserCompanyDTO EJB :");
		ArrayList<UserCompanyDTO> liste = new ArrayList<UserCompanyDTO>();

		List<UserCompany> projects = selectAll();
		for(UserCompany project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public UserCompanyDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public UserCompanyDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(UserCompanyDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(UserCompanyDTO dto) throws EJBException {
		try {
			UserCompanyMapper mapper = new UserCompanyMapper();
			UserCompany entity = null;
//			if(dto.getId()!=null) {
//				entity = dao.findById(dto.getId());
//				if(dto.getVersionObj()!=entity.getVersionObj()) {
//					throw new OptimisticLockException(entity,
//							"L'objet à été modifié depuis le dernier accés. UserCompany ID : "+dto.getId()+" - UserCompany Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
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
	public void persistDTO(UserCompanyDTO dto) throws CreateException {
		try {
			UserCompanyMapper mapper = new UserCompanyMapper();
			UserCompany entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(UserCompanyDTO dto) throws RemoveException {
		try {
			UserCompanyMapper mapper = new UserCompanyMapper();
			UserCompany entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected UserCompany refresh(UserCompany persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(UserCompany value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(UserCompany value, String propertyName) {
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
	public void validateDTO(UserCompanyDTO dto) {
		try {
			UserCompanyMapper mapper = new UserCompanyMapper();
			UserCompany entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<UserCompanyDTO> validator = new BeanValidator<UserCompanyDTO>(UserCompanyDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(UserCompanyDTO dto, String propertyName) {
		try {
			UserCompanyMapper mapper = new UserCompanyMapper();
			UserCompany entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<UserCompanyDTO> validator = new BeanValidator<UserCompanyDTO>(UserCompanyDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}
	
}
