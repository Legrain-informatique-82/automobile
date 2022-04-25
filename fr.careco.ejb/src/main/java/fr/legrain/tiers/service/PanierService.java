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

import fr.legrain.bdg.model.mapping.mapper.PanierMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IPanierServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IGarantieCarecoDAO;
import fr.legrain.tiers.dao.IPanierDAO;
import fr.legrain.tiers.dto.PanierDTO;
import fr.legrain.tiers.model.GarantieCareco;
import fr.legrain.tiers.model.LignePanier;
import fr.legrain.tiers.model.Panier;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;

/**
 * Session Bean implementation class PanierBean
 */
@Stateless
@WebService
public class PanierService extends AbstractApplicationDAOServer<Panier, PanierDTO> implements IPanierServiceRemote {

	static Logger logger = Logger.getLogger(PanierService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IPanierDAO dao;
	
	@Inject private IGarantieCarecoDAO daoGarantieCareco;

	/**
	 * Default constructor. 
	 */
	public PanierService() {
		// TODO Auto-generated constructor stub
	}
	
	//	private String defaultJPQLQuery = "select a from Panier a";
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public GarantieCareco findGarantieCarecoByMontant(BigDecimal prix, Integer duree) {
		return daoGarantieCareco.findGarantieCarecoByMontant(prix, duree);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Panier transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(Panier persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public Panier merge(Panier detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public Panier findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public Panier findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<Panier> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<PanierDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<PanierDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<Panier> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<PanierDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public PanierDTO entityToDTO(Panier entity) {
//		PanierDTO dto = new PanierDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		PanierMapper mapper = new PanierMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<PanierDTO> listEntityToListDTO(List<Panier> entity) {
		List<PanierDTO> l = new ArrayList<PanierDTO>();

		for (Panier Panier : entity) {
			l.add(entityToDTO(Panier));
		}

		return l;
	}

	public List<PanierDTO> selectAllDTO() {
		System.out.println("List of PanierDTO EJB :");
		ArrayList<PanierDTO> liste = new ArrayList<PanierDTO>();

		List<Panier> projects = selectAll();
		for(Panier project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public PanierDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public PanierDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(PanierDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(PanierDTO dto) throws EJBException {
		try {
			PanierMapper mapper = new PanierMapper();
			Panier entity = null;
//			if(dto.getId()!=null) {
//				entity = dao.findById(dto.getId());
//				if(dto.getVersionObj()!=entity.getVersionObj()) {
//					throw new OptimisticLockException(entity,
//							"L'objet à été modifié depuis le dernier accés. Panier ID : "+dto.getId()+" - Panier Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
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
	public void persistDTO(PanierDTO dto) throws CreateException {
		try {
			PanierMapper mapper = new PanierMapper();
			Panier entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(PanierDTO dto) throws RemoveException {
		try {
			PanierMapper mapper = new PanierMapper();
			Panier entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected Panier refresh(Panier persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(Panier value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(Panier value, String propertyName) {
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
	public void validateDTO(PanierDTO dto) {
		try {
			PanierMapper mapper = new PanierMapper();
			Panier entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<PanierDTO> validator = new BeanValidator<PanierDTO>(PanierDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(PanierDTO dto, String propertyName) {
		try {
			PanierMapper mapper = new PanierMapper();
			Panier entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<PanierDTO> validator = new BeanValidator<PanierDTO>(PanierDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}
	
	public void ajouterPanier(User u, String idPiece) {
		System.out.println(idPiece);
	}
	
	public List<Panier> chargeDonnees(List<Panier> listePanier) {
		if(listePanier!=null) {
			//List<Panier> l = dao.findUserAchatEnCours(user);
			for (Panier p : listePanier) {
				//appel des get pour accéder aux données afin d'éviter d'avoir besoin d'une connection sur la base depuis la vue
				//voir si on peut faire un chargement EAGER JPA pour eviter cela.
				p.getVendeur().getNom();
			}
		}
		return listePanier;
	}
	
	public List<Panier> findPanierContientPiece(Stock piece) {
		return chargeDonnees(dao.findPanierContientPiece(piece));
	}
	
	public List<Panier> findPanierActifOuPerime(User u, boolean b2c) {
		return chargeDonnees(dao.findPanierActifOuPerime(u, b2c));
	}

	@Override
	public List<Panier> findPanierActif(User u, boolean b2c) {
		return chargeDonnees(dao.findPanierActif(u, b2c));
	}

	@Override
	public List<Panier> findPanierPerime(User u, boolean b2c) {
		return chargeDonnees(dao.findPanierPerime(u, b2c));
	}

	@Override
	public List<Panier> findPanierEnCours(User u, boolean b2c) {
		return chargeDonnees(dao.findPanierEnCours(u, b2c));
	}

	@Override
	public List<Panier> findPanierValider(User u, boolean b2c) {
		return chargeDonnees(dao.findPanierValider(u, b2c));
	}

	@Override
	public List<Panier> findPanierHistorique(User u, boolean b2c) {
		return chargeDonnees(dao.findPanierHistorique(u, b2c));
	}

}
