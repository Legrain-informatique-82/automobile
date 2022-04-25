package fr.legrain.tiers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import org.jboss.ejb3.annotation.TransactionTimeout;

import fr.legrain.bdg.model.mapping.mapper.StockMapper;
import fr.legrain.bdg.tiers.service.local.IStockServiceLocal;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IImportStockTempDAO;
import fr.legrain.tiers.dao.IStockDAO;
import fr.legrain.tiers.dao.IUserCompanyDAO;
import fr.legrain.tiers.dto.StockDTO;
import fr.legrain.tiers.model.ImportStockTemp;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.UserCompany;

/**
 * Session Bean implementation class StockBean
 */
@Stateless
public class StockService extends AbstractApplicationDAOServer<Stock, StockDTO> implements IStockServiceRemote, IStockServiceLocal {

	static Logger logger = Logger.getLogger(StockService.class);
	
	@EJB IUserServiceLocal userService;

	@Inject private IStockDAO dao;
	
	@Inject private IImportStockTempDAO daoImportStockTemp;
	
	@Inject private IUserCompanyDAO daoUC;

	/**
	 * Default constructor. 
	 */
	public StockService() {
		// TODO Auto-generated constructor stub
	}
	
	//	private String defaultJPQLQuery = "select a from Stock a";
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 									
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public List<Stock> findByRefConstructeur(String refConstructeur, String typePiece){
		return dao.findByRefConstructeur(refConstructeur, typePiece);
	}
	
	public List<Stock> findByUserEtCasier(int idStock, String casier) {
		return dao.findByUserEtCasier(idStock,casier);
	}
	
	public List<Stock> findByUserStock(int idStock) {
		return dao.findByUserStock(idStock);
	}
	
	public List<Stock> findByUserStock(int idStock, List<String> listeStatusPiece) {
		return dao.findByUserStock(idStock,listeStatusPiece);
	}
	
	public List<Stock> findByUserEtPieceStock(int idStock, String refConstructeur, String typePiece) {
		return dao.findByUserEtPieceStock(idStock,refConstructeur,typePiece);
	}
	
	public List<Stock> findByMultiCompanyEtPieceStock(List<Integer> idStock, String refConstructeur, String typePiece) {
		return dao.findByMultiCompanyEtPieceStock(idStock,refConstructeur,typePiece);
	}
	
	public List<Stock> findByUserEtPieceStock(int idStock, List<String> refsConstructeur, String typePiece) {
		return dao.findByUserEtPieceStock(idStock,refsConstructeur,typePiece);
	}
	
	public List<Stock> findByMultiCompanyEtPieceStock(List<Integer> idStock, List<String> refsConstructeur, String typePiece) {
		return dao.findByMultiCompanyEtPieceStock(idStock,refsConstructeur,typePiece);
	}
	
	public List<Stock> findByGroupeEntrepiseEtPieceStock(int idGroupeEntrepise, String refConstructeur, String typePiece) {
		return dao.findByGroupeEntrepiseEtPieceStock(idGroupeEntrepise,refConstructeur,typePiece);
	}
	
	public List<Stock> findByGroupeEntrepiseEtPieceStock(int idGroupeEntrepise, List<String> refsConstructeur, String typePiece) {
		return dao.findByGroupeEntrepiseEtPieceStock(idGroupeEntrepise,refsConstructeur,typePiece);
	}
	
	public List<String> selectAllCNIT(int idGroupeEntrepise, int idStock, String debut) {
		return dao.selectAllCNIT(idGroupeEntrepise,idStock,debut);
	}
	
	public List<String> selectAllVIN(int idGroupeEntrepise, int idStock, String debut) {
		return dao.selectAllVIN(idGroupeEntrepise,idStock,debut);
	}
	
	public List<String> selectAllLivrePolice(int idStock, String debut) {
		return dao.selectAllLivrePolice(idStock,debut);
	}
	
	public List<String> selectAllTypeMoteur(int idStock, String debut) {
		return dao.selectAllTypeMoteur(idStock,debut);
	}
	
	public List<String> selectAllTypeBoite(int idStock, String debut) {
		return dao.selectAllTypeBoite(idStock,debut);
	}
	
	public List<String> selectAllCasier(int idStock, String debut) {
		return dao.selectAllCasier(idStock,debut);
	}
	
	public List<Stock> findByNumLivrePolice(int idStock,String numLivrePolice) {
		return dao.findByNumLivrePolice(idStock, numLivrePolice);
	}

	public List<Stock> findByVin(String vin) {
		return dao.findByVin(vin);
	}

	public List<Stock> findByCNIT(String cnit) {
		return dao.findByCNIT(cnit);
	}
	
	public List<Stock> findByMarqueModeleAnnee(int idStock, String typePiece, String marque, String modele, String anneeMin, String anneeMax) {
		return dao.findByMarqueModeleAnnee(idStock, typePiece, marque, modele, anneeMin, anneeMax);
	}
	
	public List<Stock> findDuplicate(int idStock) {
		return dao.findDuplicate(idStock);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Stock transientInstance) throws CreateException {

//		transientInstance.setQuiCree(userService.findUserLoggedId());
		//transientInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(transientInstance);

		dao.persist(transientInstance);
	}

	public void remove(Stock persistentInstance) throws RemoveException {
		dao.remove(persistentInstance);
	}

	@Override
	public Stock merge(Stock detachedInstance) {
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		
		validateEntity(detachedInstance);

		return dao.merge(detachedInstance);
	}
	
	public Stock changeProprio(Stock detachedInstance, UserCompany nouveau) {
		nouveau = daoUC.findById(nouveau.getId());
		detachedInstance = dao.findById(detachedInstance.getId());
		detachedInstance.setIdStock(nouveau);

		return dao.merge(detachedInstance);
	}
	
	public void removeImportStock(ImportStockTemp persistentInstance) throws RemoveException {
		daoImportStockTemp.remove(persistentInstance);
	}

	public ImportStockTemp mergeImportStock(ImportStockTemp detachedInstance) {
//		detachedInstance.setQuiCree(userService.findUserLoggedId());
//		detachedInstance.setQuiModif(userService.findUserLoggedId());
		

		return daoImportStockTemp.merge(detachedInstance);
	}

	public Stock findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public Stock findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

	public List<Stock> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<StockDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<StockDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<Stock> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<StockDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public StockDTO entityToDTO(Stock entity) {
//		StockDTO dto = new StockDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		StockMapper mapper = new StockMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<StockDTO> listEntityToListDTO(List<Stock> entity) {
		List<StockDTO> l = new ArrayList<StockDTO>();

		for (Stock Stock : entity) {
			l.add(entityToDTO(Stock));
		}

		return l;
	}

	public List<StockDTO> selectAllDTO() {
		System.out.println("List of StockDTO EJB :");
		ArrayList<StockDTO> liste = new ArrayList<StockDTO>();

		List<Stock> projects = selectAll();
		for(Stock project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public StockDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public StockDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(StockDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}

	@Override
	public void mergeDTO(StockDTO dto) throws EJBException {
		try {
			StockMapper mapper = new StockMapper();
			Stock entity = null;
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
	public void persistDTO(StockDTO dto) throws CreateException {
		try {
			StockMapper mapper = new StockMapper();
			Stock entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(StockDTO dto) throws RemoveException {
		try {
			StockMapper mapper = new StockMapper();
			Stock entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected Stock refresh(Stock persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateEntity(Stock value) /*throws ExceptLgr*/ {
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
	public void validateEntityProperty(Stock value, String propertyName) {
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
	public void validateDTO(StockDTO dto) {
		try {
			StockMapper mapper = new StockMapper();
			Stock entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<StockDTO> validator = new BeanValidator<StockDTO>(StockDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	public void validateDTOProperty(StockDTO dto, String propertyName) {
		try {
			StockMapper mapper = new StockMapper();
			Stock entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<StockDTO> validator = new BeanValidator<StockDTO>(StockDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}
	
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void validerImportationStock(int idCompany, boolean effacerAvantImport) throws Exception {
		//supprimer les stock courants de l'entreprise
		//sauf ceux utilisés dans des transactions
		
		if(effacerAvantImport) {
			//il y a deja des stock qui ne proviennent pas d'une import fichier texte, surement import initial de la V1
			//suppression de tous les stocks pour les remplacés par ceux de l'import fichier texte
			//supprimer les stocks de cette entreprise
			dao.deleteByUserStock(userService.findUserLogged().getUserCompany().getId());
			
			//PROBLEME AVEC LES IMAGES ****
			//PROBLEME AVEC LES PIECES DANS LES TRANSACTION QUI PEUVENT AUSSI ETRE ENCORE PRESENTE DANS LA NOUVELLE IMPORTATION
		}
		
		//remplacer par les nouveaux stocks
		List<ImportStockTemp> l = daoImportStockTemp.findByUserImportStockTemp(idCompany); 
		StockMapper mapper = new StockMapper();
		Stock s = null;
		for (ImportStockTemp importStockTemp : l) {
			s = dao.findByIdFichierOrigine(idCompany,importStockTemp.getIdImportFichier(),DevService.FICHIER_TEXTE_MOTEUR_CLUB);
			if(s==null) {
				s = new Stock();				
			} else {
				System.out.println("Update pièce : "+s.getId());
			}
			
			s = mapper.mapImportStockTempToStock(importStockTemp, s);
			s = enregistrerMerge(s);
		}
		
		
		annulerImportationStock(idCompany);

	}

	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void annulerImportationStock(int idCompany) {
		//supprimer les stocks de cette entreprise de la table temporaire
		daoImportStockTemp.deleteByUserImportStockTemp(userService.findUserLogged().getUserCompany().getId());

	}

}
