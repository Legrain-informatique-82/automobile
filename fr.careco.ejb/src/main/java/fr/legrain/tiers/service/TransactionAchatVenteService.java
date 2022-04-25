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

import fr.legrain.bdg.model.mapping.mapper.TransactionAchatVenteMapper;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.ITransactionAchatVenteServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.ITransactionAchatVenteDAO;
import fr.legrain.tiers.dto.TransactionAchatVenteDTO;
import fr.legrain.tiers.model.LignePanier;
import fr.legrain.tiers.model.Panier;
import fr.legrain.tiers.model.TransactionAchatVente;
import fr.legrain.tiers.model.User;

/**
 * Session Bean implementation class TransactionAchatVenteBean
 */
@Stateless
public class TransactionAchatVenteService extends AbstractApplicationDAOServer<TransactionAchatVente, TransactionAchatVenteDTO> implements ITransactionAchatVenteServiceRemote {

	static Logger logger = Logger.getLogger(TransactionAchatVenteService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private ITransactionAchatVenteDAO dao;

	/**
	 * Default constructor. 
	 */
	public TransactionAchatVenteService() {
		// TODO Auto-generated constructor stub
	}
	
	//	private String defaultJPQLQuery = "select a from TransactionAchatVente a";

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(TransactionAchatVente transientInstance) throws CreateException {
		
//		transientInstance.setQuiCree(userService.findUserLoggedId());
//		transientInstance.setQuiModif(userService.findUserLoggedId());

		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(TransactionAchatVente persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public TransactionAchatVente merge(TransactionAchatVente detachedInstance) {
		
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}

	public TransactionAchatVente findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public TransactionAchatVente findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<TransactionAchatVente> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<TransactionAchatVenteDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<TransactionAchatVenteDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<TransactionAchatVente> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<TransactionAchatVenteDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public TransactionAchatVenteDTO entityToDTO(TransactionAchatVente entity) {
//		TransactionAchatVenteDTO dto = new TransactionAchatVenteDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		TransactionAchatVenteMapper mapper = new TransactionAchatVenteMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<TransactionAchatVenteDTO> listEntityToListDTO(List<TransactionAchatVente> entity) {
		List<TransactionAchatVenteDTO> l = new ArrayList<TransactionAchatVenteDTO>();

		for (TransactionAchatVente TransactionAchatVente : entity) {
			l.add(entityToDTO(TransactionAchatVente));
		}

		return l;
	}

	public List<TransactionAchatVenteDTO> selectAllDTO() {
		System.out.println("List of TransactionAchatVenteDTO EJB :");
		ArrayList<TransactionAchatVenteDTO> liste = new ArrayList<TransactionAchatVenteDTO>();

		List<TransactionAchatVente> projects = selectAll();
		for(TransactionAchatVente project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public TransactionAchatVenteDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public TransactionAchatVenteDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(TransactionAchatVenteDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(TransactionAchatVenteDTO dto) throws EJBException {
		try {
			TransactionAchatVenteMapper mapper = new TransactionAchatVenteMapper();
			TransactionAchatVente entity = null;
//			if(dto.getId()!=null) {
//				entity = dao.findById(dto.getId());
//				if(dto.getVersionObj()!=entity.getVersionObj()) {
//					throw new OptimisticLockException(entity,
//							"L'objet à été modifié depuis le dernier accés. TransactionAchatVente ID : "+dto.getId()+" - TransactionAchatVente Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
//				} else {
//					 entity = mapper.mapDtoToEntity(dto,entity);
//				}
//			}
			
			//dao.merge(entity);
			dao.detach(entity); //pour passer les controles
			enregistrerMerge(entity);
		} catch (Exception e) {
			e.printStackTrace();
			//throw new CreateException(e.getTransactionAchatVente());
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void persistDTO(TransactionAchatVenteDTO dto) throws CreateException {
		try {
			TransactionAchatVenteMapper mapper = new TransactionAchatVenteMapper();
			TransactionAchatVente entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(TransactionAchatVenteDTO dto) throws RemoveException {
		try {
			TransactionAchatVenteMapper mapper = new TransactionAchatVenteMapper();
			TransactionAchatVente entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected TransactionAchatVente refresh(TransactionAchatVente persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(TransactionAchatVente value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(TransactionAchatVente value, String propertyName) {
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
	public void validateDTO(TransactionAchatVenteDTO dto) {
		try {
			TransactionAchatVenteMapper mapper = new TransactionAchatVenteMapper();
			TransactionAchatVente entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<TransactionAchatVenteDTO> validator = new BeanValidator<TransactionAchatVenteDTO>(TransactionAchatVenteDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(TransactionAchatVenteDTO dto, String propertyName) {
		try {
			TransactionAchatVenteMapper mapper = new TransactionAchatVenteMapper();
			TransactionAchatVente entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<TransactionAchatVenteDTO> validator = new BeanValidator<TransactionAchatVenteDTO>(TransactionAchatVenteDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}
	
	public List<LignePanier> chargeDonnees(List<LignePanier> listeLignePanier) {
		if(listeLignePanier!=null) {
			//List<Panier> l = dao.findUserAchatEnCours(user);
			for (LignePanier l : listeLignePanier) {
				//appel des get pour accéder aux données afin d'éviter d'avoir besoin d'une connection sur la base depuis la vue
				//voir si on peut faire un chargement EAGER JPA pour eviter cela.
				l.getPanier().getIdEntreprise().getNom();
				l.getPanier().getVendeur().getNom();
				l.getPanier().getClient().getNom();
			}
		}
		return listeLignePanier;
	}

	@Override
	public List<LignePanier> findUserAchatEnCours(User user) {
		return chargeDonnees(dao.findUserAchatEnCours(user));
	}

	@Override
	public List<LignePanier> findUserAchatHistorique(User user) {
		return chargeDonnees(dao.findUserAchatHistorique(user));
	}

	@Override
	public List<LignePanier> findUserVentesEnCours(User user) {
		return chargeDonnees(dao.findUserVentesEnCours(user));
	}
	
	@Override
	public List<LignePanier> findUserVentesLocaleEnCours(User user) {
		return chargeDonnees(dao.findUserVentesLocaleEnCours(user));
	}

	@Override
	public List<LignePanier> findUserVentesValider(User user) {
		return chargeDonnees(dao.findUserVentesHistorique(user));
	}

	@Override
	public List<LignePanier> findUserVentesHistorique(User user) {
		return chargeDonnees(dao.findUserVentesHistorique(user));
	}
	
	@Override
	public List<LignePanier> findUserVentesLocaleHistorique(User user) {
		return chargeDonnees(dao.findUserVentesLocaleHistorique(user));
	}

}
