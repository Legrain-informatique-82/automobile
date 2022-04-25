package fr.legrain.tiers.service;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.ejb3.annotation.TransactionTimeout;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import fr.legrain.bdg.tiers.service.local.IDatabaseServiceLocal;
import fr.legrain.bdg.tiers.service.local.IStockServiceLocal;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IFicheCarecoServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IRoleServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.careco.aaa.model.IVehiculeCacheAAADAO;
import fr.legrain.careco.aaa.model.IVehiculeCacheAAAServiceLocal;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.careco.dumpmc.model.IGlobalDumpMcDAO;
import fr.legrain.careco.dumpmc.model.StockMoteurClub;
import fr.legrain.careco.v1.model.AdherentV1;
import fr.legrain.careco.v1.model.CarPart;
import fr.legrain.careco.v1.model.Company;
import fr.legrain.careco.v1.model.CoreUser;
import fr.legrain.careco.v1.model.IGlobalV1DAO;
import fr.legrain.careco.v1.model.UserV1;
import fr.legrain.careco.v1.model.v1Mapper;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.LibDate;
import fr.legrain.tiers.dao.IAdherentCarecoDAO;
import fr.legrain.tiers.dao.IAnnuaireDAO;
import fr.legrain.tiers.dao.IFicheCarecoDAO;
import fr.legrain.tiers.dao.IGroupeEntrepriseDAO;
import fr.legrain.tiers.dao.IImportStockTempDAO;
import fr.legrain.tiers.dao.IImportationV1DAO;
import fr.legrain.tiers.dao.IRoleDAO;
import fr.legrain.tiers.dao.IStockDAO;
import fr.legrain.tiers.dao.IUserCompanyDAO;
import fr.legrain.tiers.dao.IUserDAO;
import fr.legrain.tiers.model.Adherent;
import fr.legrain.tiers.model.Annuaire;
import fr.legrain.tiers.model.FicheCareco;
import fr.legrain.tiers.model.GroupeEntreprise;
import fr.legrain.tiers.model.ImportStockTemp;
import fr.legrain.tiers.model.ImportationV1;
import fr.legrain.tiers.model.Role;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;
import fr.legrain.tiers.model.UserRole;

/**
 * Session Bean implementation class AuthURLBean
 */
@Stateless
public class DevService /*extends AbstractApplicationDAOServer<AuthURL, AuthURLDTO> implements IAuthURLServiceRemote*/ {

	static Logger logger = Logger.getLogger(DevService.class);
	
	public static final String FICHIER_TEXTE_MOTEUR_CLUB = "FICHIER_TEXTE_MOTEUR_CLUB";
	public static final String FICHIER_TEXTE_CSV = "FICHIER_TEXTE_CSV";
	
	//int maxTest = 500;
	int maxTest = 0;
	
	@Resource 
	private SessionContext context;
	
	@EJB IUserServiceLocal userService;
	@EJB IStockServiceLocal stockService;
	
	@EJB IVehiculeCacheAAAServiceLocal vehiculeCacheAAA;
	

	@Inject private IFicheCarecoDAO daoFicheCareco;
	@Inject private IAnnuaireDAO daoAnnuaire;
	
	@Inject private IVehiculeCacheAAADAO daoAAA;
	
	@Inject private IRoleDAO daoRoleServiceRemote;
	
	@Inject private IGlobalV1DAO daoV1;
	@Inject private IGlobalDumpMcDAO daoDumpMoteurClub;
	
	@Inject private IImportationV1DAO daoImportationV1;
	
	@Inject private IGroupeEntrepriseDAO daoGroupeEntreprise;
	
	@Inject private IAdherentCarecoDAO daoAdherentCareco;
	@Inject private IUserCompanyDAO daoUserCompany;
	@Inject private IUserDAO daoUser;
	@Inject private IStockDAO daoStock;
	@Inject private IImportStockTempDAO daoImportStockTemp;
	
	@EJB IDatabaseServiceLocal databaseService;

	public DevService() {}

	public String test() {
		
		System.out.println("user logged : "+context.getCallerPrincipal().getName()); 
//		try {
//			//importFromExcelFile("");
//			//importFromExcelFileAnnuaire("");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return "user logged : "+context.getCallerPrincipal().getName();
		
//		return "aaa";
	}
	
	public void mysqlDump(String nomDump) throws IOException {
		databaseService.backupDB(nomDump);
	}
	
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void importStockCSV(String importFile) throws Exception {
		
		UserCompany c = userService.findUserLogged().getUserCompany();
		
		//CSVReader reader = new CSVReader(new FileReader(importFile));
		CSVReader reader = new CSVReader(new FileReader(importFile),';', '"', 1);
	    List<String[]> myEntries = reader.readAll();
	    
//	    ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
//	    strat.setType(Stock.class);
//	    String[] columns = new String[] {"name", "orderNumber", "id"}; // the fields to bind do in your JavaBean
//	    strat.setColumnMapping(columns);
//
//	    CsvToBean csv = new CsvToBean();
//	    List list = csv.parse(strat, reader);
	    
	    int POS_idOrigine 		= 0; //int
	    int POS_numLivrePolice 	= 1; //string
	    int POS_typeDePiece 	= 2; //string "Moteur" ou "Boite"
	    int POS_immatriculation = 3; //string
	    int POS_refConstructeur = 4; //string
	    int POS_codeCompatibilite 	= 5; //string
	    int POS_CNITTypeMine 		= 6; //string
	    int POS_vin 	= 7; //string
	    int POS_marque 	= 8; //string
	    int POS_modele 	= 9; //string
	    int POS_version = 10; //string
	    int POS_energie = 11; //string
	    int POS_numeroDeSeriePiece = 12; //string
	    int POS_kms 		= 13; //int
	    int POS_garantie 	= 14; //int
	    int POS_prixVente 	= 15; //float
	    int POS_prixMinimum = 16; //float
	    int POS_prixAchat 	= 17; //float
	    int POS_empl 		= 18; //string
	    int POS_emplacementCasier = 19; //string
	    int POS_commentaireCommercial = 20; //string
	    int POS_commentaireInterne 	= 21; //string
	    int POS_disponibilite = 22; //int - bool
	    int POS_export 		= 23; //int - bool
	    int POS_demonte 	= 24; //int - bool
	    int POS_pieceLourde = 25; //int - bool
	    int POS_dmsRef 		= 26; //string
	    int POS_dateAchat   = 27; //string, date
	    
	    //Stock stock = null;
	    ImportStockTemp stock = null;
	    for (String[] ligne : myEntries) {
	    	stock = new ImportStockTemp();
	    	
	    	for (int i = 0; i < ligne.length; i++) {
	    		if(ligne[i]!=null && (ligne[i].equals("") || ligne[i].equals("NULL"))) {
	    			ligne[i]=null;
	    		}
	    	}

	    	stock.setIdStock(c);
	    	stock.setIdStockOrigine(c);
	    	
	    	stock.setDisponibilite(LibConversion.StringToBoolean(ligne[POS_disponibilite]));
	    	stock.setTypeDePiece(ligne[POS_typeDePiece]);
	    	
	    	stock.setRefConstructeur(ligne[POS_refConstructeur]);
	    	stock.setCodeCompatibilite(ligne[POS_codeCompatibilite]);

	    	stock.setCNITTypeMine(ligne[POS_CNITTypeMine]);
	    	stock.setVin(ligne[POS_vin]);
	    	stock.setMarque(ligne[POS_marque]);
	    	stock.setModele(ligne[POS_modele]);
	    	stock.setVersion(ligne[POS_version]);
	    	stock.setEnergie(ligne[POS_energie]);
	    	stock.setNumeroDeSeriePiece(ligne[POS_numeroDeSeriePiece]);
	    	
	    	stock.setKms(LibConversion.stringToInteger(ligne[POS_kms],null));
	    	stock.setEmpl(ligne[POS_empl]);
	    	stock.setEmplacementCasier(ligne[POS_emplacementCasier]);
	    	
	    	stock.setGarantie(LibConversion.stringToInteger(ligne[POS_garantie]==null?null:ligne[POS_garantie]));
	    	
	    	stock.setPrixVente(LibConversion.stringToBigDecimal(ligne[POS_prixVente]==null?null:ligne[POS_prixVente]));
	    	stock.setPrixMinimum(LibConversion.stringToBigDecimal(ligne[POS_prixMinimum]==null?null:ligne[POS_prixMinimum]));
	    	stock.setPrixAchat(LibConversion.stringToBigDecimal(ligne[POS_prixAchat]==null?null:ligne[POS_prixAchat]));
	    	stock.setNumLivrePolice(ligne[POS_numLivrePolice]);
	    	stock.setNogo(stock.getNumLivrePolice());
	    	stock.setDossier(stock.getNumLivrePolice());
	    	
	    	stock.setImmatriculation(ligne[POS_immatriculation]);
	    	if(stock.getImmatriculation()==null || !stock.getImmatriculation().equals("")) {
	    		stock.setPieceOrpheline(true);
	    	}
	    	stock.setExport(LibConversion.StringToBoolean(ligne[POS_export],null));
	    	stock.setDemonte(LibConversion.StringToBoolean(ligne[POS_demonte],null));
	    	
	    	stock.setDateAchat(LibDate.stringToDate(ligne[POS_dateAchat]));
	    	
	    	stock.setCommentaireCommercial(ligne[POS_commentaireCommercial]);
	    	stock.setCommentaireInterne(ligne[POS_commentaireInterne]);
	    	
	    	stock.setPieceLourde(LibConversion.StringToBoolean(ligne[POS_pieceLourde],null));
	    	stock.setDmsRef(ligne[POS_dmsRef]);
	    	
	    	//stock.setIdOrigine(LibConversion.stringToBigDecimal(ligne[POS_idOrigine]==null?null:ligne[POS_idOrigine]).intValue());
	    	
	    	stock.setImportFichier(importFile);
	    	stock.setIdImportFichier(LibConversion.stringToBigDecimal(ligne[POS_idOrigine]==null?null:ligne[POS_idOrigine]).intValue());
	    	stock.setImportTypeFichier(FICHIER_TEXTE_CSV);

	    	//stockService.enregistrerMerge(stock);
	    	stockService.mergeImportStock(stock);
		}
	}
	
	/**
	 * @param importFile - fichier csv moteur club
	 * @throws Exception 
	 */
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void importStockMoteurClub(String importFile) throws Exception {
		
		int POS_IDMotorsClub = 0;
		int POS_Dossier	 = 1;
		int POS_Production = 2;
		int POS_Piece = 3;
		int POS_Statut = 4;
		int POS_Export = 5;
		int POS_DateAchat = 6;
		int POS_PrixAchat = 7;
		int POS_CoutPort = 8;
		int POS_Site = 9;
		int POS_Societe	 = 10;
		int POS_Emplacement	 = 11;
		int POS_EnTransit = 12;
		int POS_PrixDeVente	 = 13;
		int POS_Cat	= 14;
		int POS_Remise = 15;
		int POS_Famille	= 16;
		int POS_Marque = 17;
		int POS_Modele = 18;
		int POS_Categorie = 19;
		int POS_Genre = 20;
		int POS_Cylindree = 21;
		int POS_Cylindres = 22;
		int POS_Soupapes = 23;
		int POS_Annee = 24;
		int POS_CVDin = 25;
		int POS_Origine	 = 26;
		int POS_GarantieFourn = 27;
		int POS_GarantieInterne	 = 28;
		int POS_Kilometrage	 = 29;
		int POS_KmMentionnable = 30;
		int POS_Priorite = 31;
		int POS_Declasse = 32;
		int POS_Caution	 = 33;
		int POS_Specificite	= 34;
		int POS_Observations = 35;
		int POS_Informations = 36;
		int POS_EtatVehicule = 37;	
		int POS_TypeMoteur = 38;
		int POS_CodeMoteur = 39;
		int POS_NumeroMoteur = 40;	
		int POS_RefBoite = 41;
		int POS_CodeBoite = 42;
		int POS_GenreBoite = 43;
		int POS_TypePiece = 44;	
		int POS_CodePiece = 45;	
		int POS_Reservation	= 46;
		int POS_NumContactReservation = 47;
		int POS_RVMI = 48;	
		int POS_EtatRVMI = 49;
		int POS_NotesRVMI = 50;
		int POS_Immatriculation	= 51;
		int POS_TypeMine = 52;
		int POS_NumSerieDuType = 53;
		int POS_Energie	 = 54;
		int POS_CVFisc = 55;
		int POS_Carosserie = 56;
		int POS_Preparation	= 57;
		int POS_TAG_TECH = 58;
		
		UserCompany c = userService.findUserLogged().getUserCompany();
		
		//CSVReader reader = new CSVReader(new FileReader(importFile));
		CSVReader reader = new CSVReader(new FileReader(importFile),';', '"', 2); //2 lignes d'entête
	    List<String[]> myEntries = reader.readAll();
	    
	    List<String> idRefuse = new ArrayList<String>();
	    
	    //Stock stock = null;
	    ImportStockTemp stockTmp = null;
	    Stock stock = null;
	    for (String[] ligne : myEntries) {
	    	
	    	for (int i = 0; i < ligne.length; i++) {
	    		if(ligne[i]!=null && (ligne[i].equals("") || ligne[i].equals("NULL"))) {
	    			ligne[i]=null;
	    		}
	    	}
	    	
	    	try {
	    		stock = null;
	    		stock = daoStock.findByIdFichierOrigine(c.getId(),LibConversion.stringToBigDecimal(ligne[POS_IDMotorsClub]==null?null:ligne[POS_IDMotorsClub]).intValue(),FICHIER_TEXTE_MOTEUR_CLUB);

				if(stock==null) {
					System.out.println("=== Nouvelle pièce en provenance de moteur club");
					stockTmp = new ImportStockTemp();

					stockTmp.setIdStock(c);
					stockTmp.setIdStockOrigine(c);

					//////////////////////////////////////
					//stock.setDisponibilite(LibConversion.StringToBoolean(ligne[POS_disponibilite]));
					if(ligne[POS_Piece]!=null && ligne[POS_Piece].equals("M")) {
						stockTmp.setTypeDePiece("Moteur");
						stockTmp.setRefConstructeur(ligne[POS_TypeMoteur]);
						stockTmp.setCodeCompatibilite(ligne[POS_CodeMoteur]);
					}
					else if(ligne[POS_Piece]!=null && ligne[POS_Piece].equals("B")) {
						stockTmp.setTypeDePiece("Boite");
						stockTmp.setRefConstructeur(ligne[POS_RefBoite]);
						stockTmp.setCodeCompatibilite(ligne[POS_CodeBoite]);
					}
					else {
						//autre, type en saisie libre dans moteur club
						stockTmp.setTypeDePiece(ligne[POS_Piece]);
						stockTmp.setRefConstructeur(ligne[POS_TypePiece]);
						stockTmp.setCodeCompatibilite(ligne[POS_CodePiece]);
					}

					stockTmp.setCNITTypeMine(ligne[POS_TypeMine]);
					stockTmp.setVin(ligne[POS_NumSerieDuType]);
					stockTmp.setMarque(ligne[POS_Marque]);
					stockTmp.setModele(ligne[POS_Modele]);
					stockTmp.setVersion(ligne[POS_Categorie]);
					stockTmp.setEnergie(ligne[POS_Energie]);
					//	    	stock.setNumeroDeSeriePiece(ligne[POS_numeroDeSeriePiece]);

					stockTmp.setKms(LibConversion.stringToInteger(ligne[POS_Kilometrage],null));
					stockTmp.setEmpl(null);
					stockTmp.setEmplacementCasier(ligne[POS_Emplacement]);
					stockTmp.setVendeur(ligne[POS_Societe]);

					stockTmp.setGarantie(LibConversion.stringToInteger(ligne[POS_GarantieInterne]==null?null:ligne[POS_GarantieInterne]));

					stockTmp.setPrixVente(LibConversion.stringToBigDecimal(ligne[POS_PrixDeVente]==null?null:ligne[POS_PrixDeVente]));

					//stock.setPrixMinimum(LibConversion.stringToBigDecimal(ligne[POS_prixMinimum]==null?null:ligne[POS_prixMinimum]));
					stockTmp.setPrixMinimum(new BigDecimal(1));

					stockTmp.setPrixAchat(LibConversion.stringToBigDecimal(ligne[POS_PrixAchat]==null?null:ligne[POS_PrixAchat]));
					stockTmp.setNumLivrePolice(ligne[POS_Dossier]);
					stockTmp.setNogo(stockTmp.getNumLivrePolice());
					stockTmp.setDossier(stockTmp.getNumLivrePolice());

					stockTmp.setImmatriculation(ligne[POS_Immatriculation]);
					if(stockTmp.getImmatriculation()==null || !stockTmp.getImmatriculation().equals("")) {
						stockTmp.setPieceOrpheline(true);
					}
					stockTmp.setExport(LibConversion.StringToBoolean(ligne[POS_Export],null));
					//	    	stock.setDemonte(LibConversion.StringToBoolean(ligne[POS_demonte],null));

					stockTmp.setDateAchat(LibDate.stringToDate(ligne[POS_DateAchat]));

					//	    	stock.setCommentaireCommercial(ligne[POS_commentaireCommercial]);
					stockTmp.setCommentaireInterne(ligne[POS_Informations]);
					stockTmp.setCommentaireCommercial(ligne[POS_Observations]);

					//	    	stock.setPieceLourde(LibConversion.StringToBoolean(ligne[POS_pieceLourde],null));
					//	    	stock.setDmsRef(ligne[POS_dmsRef]);
					//////////////////////////////////////
					stockTmp.setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);

					stockTmp.setImportFichier(importFile);
					stockTmp.setIdImportFichier(LibConversion.stringToBigDecimal(ligne[POS_IDMotorsClub]==null?null:ligne[POS_IDMotorsClub]).intValue());
					stockTmp.setImportTypeFichier(FICHIER_TEXTE_MOTEUR_CLUB);

					//stockService.enregistrerMerge(stock);
					stockService.mergeImportStock(stockTmp);
				} else {
					System.out.println("=== Mise à jour d'une pièce (id : "+stock.getId()+") déjà importé de moteur club (idMc : "+stock.getIdImportFichier()+") précédement");
					if(ligne[POS_Piece]!=null && ligne[POS_Piece].equals("M")) {
						stock.setTypeDePiece("Moteur");
						stock.setRefConstructeur(ligne[POS_TypeMoteur]);
						stock.setCodeCompatibilite(ligne[POS_CodeMoteur]);
					}
					else if(ligne[POS_Piece]!=null && ligne[POS_Piece].equals("B")) {
						stock.setTypeDePiece("Boite");
						stock.setRefConstructeur(ligne[POS_RefBoite]);
						stock.setCodeCompatibilite(ligne[POS_CodeBoite]);
					}
					else {
						//autre, type en saisie libre dans moteur club
						stock.setTypeDePiece(ligne[POS_Piece]);
						stock.setRefConstructeur(ligne[POS_TypePiece]);
						stock.setCodeCompatibilite(ligne[POS_CodePiece]);
					}

					stock.setCNITTypeMine(ligne[POS_TypeMine]);
					stock.setVin(ligne[POS_NumSerieDuType]);
					stock.setMarque(ligne[POS_Marque]);
					stock.setModele(ligne[POS_Modele]);
					stock.setVersion(ligne[POS_Categorie]);
					stock.setEnergie(ligne[POS_Energie]);
					//	    	stock.setNumeroDeSeriePiece(ligne[POS_numeroDeSeriePiece]);

					stock.setKms(LibConversion.stringToInteger(ligne[POS_Kilometrage],null));
					stock.setEmpl(null);
					stock.setEmplacementCasier(ligne[POS_Emplacement]);
					stockTmp.setVendeur(ligne[POS_Societe]);

					stock.setGarantie(LibConversion.stringToInteger(ligne[POS_GarantieInterne]==null?null:ligne[POS_GarantieInterne]));

					stock.setPrixVente(LibConversion.stringToBigDecimal(ligne[POS_PrixDeVente]==null?null:ligne[POS_PrixDeVente]));

					//stock.setPrixMinimum(LibConversion.stringToBigDecimal(ligne[POS_prixMinimum]==null?null:ligne[POS_prixMinimum]));
					stock.setPrixMinimum(new BigDecimal(1));

					stock.setPrixAchat(LibConversion.stringToBigDecimal(ligne[POS_PrixAchat]==null?null:ligne[POS_PrixAchat]));
					stock.setNumLivrePolice(ligne[POS_Dossier]);
					stock.setNogo(stock.getNumLivrePolice());
					stock.setDossier(stock.getNumLivrePolice());

					stock.setImmatriculation(ligne[POS_Immatriculation]);
					if(stock.getImmatriculation()==null || !stock.getImmatriculation().equals("")) {
						stock.setPieceOrpheline(true);
					}
					stock.setExport(LibConversion.StringToBoolean(ligne[POS_Export],null));
					//	    	stock.setDemonte(LibConversion.StringToBoolean(ligne[POS_demonte],null));

					stock.setDateAchat(LibDate.stringToDate(ligne[POS_DateAchat]));

					//	    	stock.setCommentaireCommercial(ligne[POS_commentaireCommercial]);
					stock.setCommentaireInterne(ligne[POS_Informations]);
					stock.setCommentaireCommercial(ligne[POS_Observations]);

					//	    	stock.setPieceLourde(LibConversion.StringToBoolean(ligne[POS_pieceLourde],null));
					//	    	stock.setDmsRef(ligne[POS_dmsRef]);
					//////////////////////////////////////
					stock.setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
					
					stock.setImportFichier(importFile);
					
					stockService.merge(stock);
				}
	    	} catch(Exception e) {
	    		idRefuse.add(ligne[POS_IDMotorsClub]);
	    	}
		}
	    
	    if(!idRefuse.isEmpty()) {
	    	for (String idMC : idRefuse) {
				System.out.println("ID Moteur Club refusé à l'importation (format de ligne incorrect) : "+idMC);
			}
	    	System.out.println("=== Nombre de ligne refusé : "+idRefuse.size());
	    }

//		String excelXLSXFileName = importFile;
//
//		Vector<Vector<Cell>> vectorData = new Vector<Vector<Cell>>();
//
//		try {
//			FileInputStream fileInputStream = new FileInputStream(excelXLSXFileName);
//			XSSFWorkbook xssfWorkBook = new XSSFWorkbook(fileInputStream);
//
//			// Read data at sheet 0
//			XSSFSheet xssfSheet = xssfWorkBook.getSheetAt(0);
//
//			Iterator rowIteration = xssfSheet.rowIterator();
//
//			Vector<Cell> vectorCellEachRowData = null;
//			Cell cell = null;
//			for(Row row : xssfSheet) {
//				if(!isRowEmpty(row)) {
//					vectorCellEachRowData = new Vector<Cell>();
//					for(int cn=0; cn<row.getLastCellNum(); cn++) {
//						// If the cell is missing from the file, generate a blank one
//						// (Works by specifying a MissingCellPolicy)
//						cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
//						vectorCellEachRowData.addElement(cell);
//					}
//					vectorData.addElement(vectorCellEachRowData);
//				}
//			}
//
//			Stock f = null;
//			for(int i=1; i<vectorData.size(); i++) { //1 => on saute la ligne de titre
//				vectorCellEachRowData = (Vector<Cell>) vectorData.get(i);
//				// looping every cell in each row
//
//				
//				System.out.println("ligne "+i);
//				//System.out.print(vectorCellEachRowData.get(j).toString()+" | ");
//				f = new Stock(
//						vectorCellEachRowData.get(0).toString(), //id 
//						vectorCellEachRowData.get(1).toString(), //marque 
//						vectorCellEachRowData.get(2).toString(), //modele 
//						vectorCellEachRowData.get(3).toString(), //mode
//						vectorCellEachRowData.get(4).toString(), //genre 
//						vectorCellEachRowData.get(5).toString(), //cm3 
//						vectorCellEachRowData.get(6).toString(), //cyl 
//						vectorCellEachRowData.get(7).toString(), //sppes 
//						vectorCellEachRowData.get(8).toString(), //cv
//						vectorCellEachRowData.get(9).getDateCellValue(), //dateDe 
//						vectorCellEachRowData.get(10).getDateCellValue() //dateA 
//						);
//				
//				daoStock.persist(f);
//				//System.out.println("");
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
	}
	
	public List<String> autoCompleteImmatCache(String query) {  
        List<String> results = new ArrayList<String>();
        /*
         * rechercher uniquement dans stock local
         */
        
        List<VehiculeCacheAAA> v = daoAAA.findWithJPQLQuery("select a from VehiculeCacheAAA a where a.Immat_SIV like '"+query+"%'");
        
        int i = 0;  
        for (VehiculeCacheAAA aaa : v) {
            results.add(aaa.getImmat_SIV()); 
            i++;
            if(i>20) {
            	break;
            }
        }  
          
        return results;  
    }

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										Utilitaire
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c <= row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

	public void importFromExcelFile(String excelXLSXFileName) throws ParseException {
		//XLSX
		//excelXLSXFileName = "/home/nicolas/Téléchargements/fiupdate3 echantillon.xlsx";
		excelXLSXFileName = "/home/nicolas/Téléchargements/fiupdate3.xlsx";
		Vector vectorDataExcelXLSX = new Vector();


		Vector<Vector<Cell>> vectorData = new Vector<Vector<Cell>>();

		try {
			FileInputStream fileInputStream = new FileInputStream(excelXLSXFileName);

			XSSFWorkbook xssfWorkBook = new XSSFWorkbook(fileInputStream);

			// Read data at sheet 0
			XSSFSheet xssfSheet = xssfWorkBook.getSheetAt(0);

			Iterator rowIteration = xssfSheet.rowIterator();

			Vector<Cell> vectorCellEachRowData = null;
			Cell cell = null;
			for(Row row : xssfSheet) {
				if(!isRowEmpty(row)) {
					vectorCellEachRowData = new Vector<Cell>();
					for(int cn=0; cn<row.getLastCellNum(); cn++) {
						// If the cell is missing from the file, generate a blank one
						// (Works by specifying a MissingCellPolicy)
						cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
						vectorCellEachRowData.addElement(cell);
						// Print the cell for debugging
						//System.out.println("CELL: " + cn + " --> " + cell.toString());
					}
					vectorData.addElement(vectorCellEachRowData);
				}
			}

			//// Looping every row at sheet 0
			//while (rowIteration.hasNext()) {
			//XSSFRow xssfRow = (XSSFRow) rowIteration.next();
			//Iterator cellIteration = xssfRow.cellIterator();
			//
			//Vector vectorCellEachRowData = new Vector();
			//
			//// Looping every cell in each row at sheet 0
			//while (cellIteration.hasNext()) {
			//XSSFCell xssfCell = (XSSFCell) cellIteration.next();
			//vectorCellEachRowData.addElement(xssfCell);
			//}
			//
			//vectorData.addElement(vectorCellEachRowData);
			//}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		//Importation fichier "Fiche Careco"
		// Looping every row data in vector
		Vector<Cell> vectorCellEachRowData = null;
		FicheCareco f = null;
		for(int i=1; i<vectorData.size(); i++) { //1 => on saute la ligne de titre
			vectorCellEachRowData = (Vector<Cell>) vectorData.get(i);
			// looping every cell in each row

			//for(int j=0; j<vectorCellEachRowData.size(); j++) {
			System.out.println("ligne "+i);
			//System.out.print(vectorCellEachRowData.get(j).toString()+" | ");
			f = new FicheCareco(
					vectorCellEachRowData.get(0).toString(), //id 
					vectorCellEachRowData.get(1).toString(), //marque 
					vectorCellEachRowData.get(2).toString(), //modele 
					vectorCellEachRowData.get(3).toString(), //mode
					vectorCellEachRowData.get(4).toString(), //genre 
					vectorCellEachRowData.get(5).toString(), //cm3 
					vectorCellEachRowData.get(6).toString(), //cyl 
					vectorCellEachRowData.get(7).toString(), //sppes 
					vectorCellEachRowData.get(8).toString(), //cv
					vectorCellEachRowData.get(9).getDateCellValue(), //dateDe 
					vectorCellEachRowData.get(10).getDateCellValue(), //dateA 
					vectorCellEachRowData.get(11).toString(), //typeCG
					vectorCellEachRowData.get(12).toString(), //typeMot 
					vectorCellEachRowData.get(13).toString(), //codeMot 
					vectorCellEachRowData.get(14).toString(), //obsMot 
					vectorCellEachRowData.get(15).toString(), //refBte
					vectorCellEachRowData.get(16).toString(), //codeBte 
					vectorCellEachRowData.get(17).toString(), //genreBte 
					vectorCellEachRowData.get(18).toString(), //obsBte 
					vectorCellEachRowData.get(19).toString(), //cetteBoite
					vectorCellEachRowData.get(20).toString(), //avecCetteBoite 
					vectorCellEachRowData.get(21).toString(), //avecCetteAutreBoite
					vectorCellEachRowData.get(22).toString(), //pivotBoiteDeVitesse 
					vectorCellEachRowData.get(23).toString(), //ceTypeMoteurEstCompatibleAvec
					vectorCellEachRowData.get(24).toString(), //ceMoteur 
					vectorCellEachRowData.get(25).toString() //pivotMoteurs
					);
			//}
			daoFicheCareco.persist(f);
			//System.out.println("");
		}
	}
	
	public void importFromExcelFileAnnuaire(String excelXLSXFileName) throws ParseException {
		//XLS
		//excelXLSXFileName = "/home/nicolas/Téléchargements/fiupdate3 echantillon.xlsx";
		excelXLSXFileName = "/home/nicolas/Téléchargements/liste à communiquer aux adhérents2.xls";
		Vector vectorDataExcelXLSX = new Vector();


		Vector<Vector<Cell>> vectorData = new Vector<Vector<Cell>>();

		try {
			FileInputStream fileInputStream = new FileInputStream(excelXLSXFileName);

			Workbook xssfWorkBook = WorkbookFactory.create(fileInputStream);

			// Read data at sheet 0
			Sheet xssfSheet = xssfWorkBook.getSheetAt(0);

			Iterator rowIteration = xssfSheet.rowIterator();

			Vector<Cell> vectorCellEachRowData = null;
			Cell cell = null;
			for(Row row : xssfSheet) {
				if(!isRowEmpty(row)) {
					vectorCellEachRowData = new Vector<Cell>();
					for(int cn=0; cn<row.getLastCellNum(); cn++) {
						// If the cell is missing from the file, generate a blank one
						// (Works by specifying a MissingCellPolicy)
						cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
						vectorCellEachRowData.addElement(cell);
						// Print the cell for debugging
						//System.out.println("CELL: " + cn + " --> " + cell.toString());
					}
					vectorData.addElement(vectorCellEachRowData);
				}
			}

			//// Looping every row at sheet 0
			//while (rowIteration.hasNext()) {
			//XSSFRow xssfRow = (XSSFRow) rowIteration.next();
			//Iterator cellIteration = xssfRow.cellIterator();
			//
			//Vector vectorCellEachRowData = new Vector();
			//
			//// Looping every cell in each row at sheet 0
			//while (cellIteration.hasNext()) {
			//XSSFCell xssfCell = (XSSFCell) cellIteration.next();
			//vectorCellEachRowData.addElement(xssfCell);
			//}
			//
			//vectorData.addElement(vectorCellEachRowData);
			//}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		//Importation fichier "Fiche Careco"
		// Looping every row data in vector
		Vector<Cell> vectorCellEachRowData = null;
		Annuaire f = null;
		for(int i=1; i<vectorData.size(); i++) { //1 => on saute la ligne de titre
			vectorCellEachRowData = (Vector<Cell>) vectorData.get(i);
			// looping every cell in each row

			//for(int j=0; j<vectorCellEachRowData.size(); j++) {
			System.out.println("ligne "+i);
			//System.out.print(vectorCellEachRowData.get(j).toString()+" | ");
			f = new Annuaire(

					vectorCellEachRowData.get(0).toString(), //Centres 
					vectorCellEachRowData.get(1).toString(), //Adhérent 
					vectorCellEachRowData.get(2).toString(), //Adresse 
					vectorCellEachRowData.get(3).toString(), //CP
					vectorCellEachRowData.get(4).toString(), //Ville 
					vectorCellEachRowData.get(5).toString(), //Téléphone 
					vectorCellEachRowData.get(6).toString() //Télécopie 
					);
			//}
			daoAnnuaire.persist(f);
			//System.out.println("");
		}
	}
	
	private List<AdherentV1> listeAdherent = null;
	private List<Company> listeCompany = null;
	private List<CoreUser> listeCoreUser = null;
	private List<CarPart> listeCarPart = null;
	private Map<Integer,Company> mapUserV1IdCompany = null;
	private Map<String,Role> mapRole = null;
	
	private Map<Integer,Integer> mapIDCompanyV1MC = null;
	
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void selectDataV1() {
		System.out.println("DevService.selectDataV1() - debut");
		listeAdherent = daoV1.selectAllAdherent();
		listeCompany = daoV1.selectAllCompany();
		listeCoreUser = daoV1.selectAllCoreUser();
		listeCarPart = daoV1.selectAllCarPart();
		mapUserV1IdCompany = daoV1.selectAllUserV1CompanyId();
		
		mapIDCompanyV1MC = new HashMap<Integer, Integer>();
		//mapping réalisé manuellement
		mapIDCompanyV1MC.put(1, 88);
		mapIDCompanyV1MC.put(2, null);
		mapIDCompanyV1MC.put(3, 29);
		mapIDCompanyV1MC.put(4, 27);
		mapIDCompanyV1MC.put(5, 103);
		mapIDCompanyV1MC.put(6, 53);
		mapIDCompanyV1MC.put(7, 57);
		mapIDCompanyV1MC.put(8, 97);
		mapIDCompanyV1MC.put(9, 55);
		mapIDCompanyV1MC.put(10, null);
		mapIDCompanyV1MC.put(11, 56);
		mapIDCompanyV1MC.put(12, 58);
		mapIDCompanyV1MC.put(13, 148);
		mapIDCompanyV1MC.put(14, 102);
		mapIDCompanyV1MC.put(15, 113);
		mapIDCompanyV1MC.put(16, 173);
		mapIDCompanyV1MC.put(17, null);
		mapIDCompanyV1MC.put(18, 59);
		mapIDCompanyV1MC.put(19, 150);
		mapIDCompanyV1MC.put(20, 60);
		mapIDCompanyV1MC.put(21, 51);
		mapIDCompanyV1MC.put(22, 80);
		mapIDCompanyV1MC.put(23, 149);
		mapIDCompanyV1MC.put(24, 157);
		mapIDCompanyV1MC.put(25, 61);
		mapIDCompanyV1MC.put(26, 62);
		mapIDCompanyV1MC.put(27, 120);
		mapIDCompanyV1MC.put(28, 63);
		mapIDCompanyV1MC.put(29, 54);
		mapIDCompanyV1MC.put(30, 82);
		mapIDCompanyV1MC.put(31, 81);
		mapIDCompanyV1MC.put(32, 83);
		mapIDCompanyV1MC.put(33, 146);
		mapIDCompanyV1MC.put(34, 64);
		mapIDCompanyV1MC.put(35, 66);
		mapIDCompanyV1MC.put(36, 180);
		mapIDCompanyV1MC.put(37, null);
		mapIDCompanyV1MC.put(38, 67);
		mapIDCompanyV1MC.put(39, 68);
		mapIDCompanyV1MC.put(40, null);
		mapIDCompanyV1MC.put(41, 121);
		mapIDCompanyV1MC.put(42, 44);
		mapIDCompanyV1MC.put(43, 70);
		mapIDCompanyV1MC.put(44, 71);
		mapIDCompanyV1MC.put(45, 115);
		mapIDCompanyV1MC.put(46, 181);
		mapIDCompanyV1MC.put(47, null);
		mapIDCompanyV1MC.put(48, 182);
		mapIDCompanyV1MC.put(49, 100);
		mapIDCompanyV1MC.put(50, 114);
		mapIDCompanyV1MC.put(51, 152);
		mapIDCompanyV1MC.put(52, 72);
		mapIDCompanyV1MC.put(53, 94);
		mapIDCompanyV1MC.put(54, 73);
		mapIDCompanyV1MC.put(55, 74);
		mapIDCompanyV1MC.put(56, 75);
		mapIDCompanyV1MC.put(57, 158);
		mapIDCompanyV1MC.put(58, 76);
		mapIDCompanyV1MC.put(59, 89);
		mapIDCompanyV1MC.put(60, 159);
		mapIDCompanyV1MC.put(61, 77);
		mapIDCompanyV1MC.put(62, 179);
		mapIDCompanyV1MC.put(63, 111);//*
		mapIDCompanyV1MC.put(64, 78);
		mapIDCompanyV1MC.put(65, 26);//*
		mapIDCompanyV1MC.put(66, 107);
		mapIDCompanyV1MC.put(67, 79);
		mapIDCompanyV1MC.put(68, 108);
		mapIDCompanyV1MC.put(69, 84);
		mapIDCompanyV1MC.put(70, null);
		mapIDCompanyV1MC.put(71, 119);
		mapIDCompanyV1MC.put(72, 192);
		mapIDCompanyV1MC.put(73, 49);
		mapIDCompanyV1MC.put(74, 117);
		mapIDCompanyV1MC.put(75, 24);
//		mapIDCompanyV1MC.put(76, null);
//		mapIDCompanyV1MC.put(77, null);
		mapIDCompanyV1MC.put(78, null);
		mapIDCompanyV1MC.put(79, 95);
		mapIDCompanyV1MC.put(80, null);
		mapIDCompanyV1MC.put(81, 191);
		mapIDCompanyV1MC.put(82, 112);
	
		System.out.println("DevService.selectDataV1() - fin");
	}

	/**
	 * Importation des pièces depuis le dump de la V1<br>
	 * Adhérents<br>
	 * Companies<br>
	 * Utilisateurs<br>
	 * Stocks<br>
	 */
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void immportV1() {
		v1Mapper mapper = new v1Mapper();
		Adherent ac = null;
		UserCompany uc = null;
		User user = null;
		Stock s = null;
		Date dateDebut = new Date();
		Date datefin = null;
		
		//par défaut que des CARECO
		GroupeEntreprise grp = daoGroupeEntreprise.findById(1);
		
		boolean forceUpdate = false;
		
		System.out.println("Importation des adhérents");
		int totalNbAdherent = listeAdherent.size();
		int nbAdherentOK = 0;
		for (AdherentV1 adherentV1 : listeAdherent) {
			ac = new Adherent();
			ac = mapper.mapAdherentToAdherentCareco(adherentV1, ac);
			ac.setTableOrigine("Adherent");
			ac.setIdOrigine(adherentV1.getId());
			ac = daoAdherentCareco.merge(ac);
			//daoImportationV1.merge(new ImportationV1("Adherent",a.getId(),"Adherent",ac.getId()));
			
			daoAdherentCareco.findByIdOrigine(adherentV1.getId());
			
			nbAdherentOK++;
			System.out.println("Adhérent "+nbAdherentOK+"/"+totalNbAdherent);
		}
		
		System.out.println("Importation des compagnie");
		int totalNbCompany = listeCompany.size();
		int nbCompanyOK = 0;
		for (Company companyV1 : listeCompany) {
			uc = new UserCompany();
			uc = mapper.mapCompanyToUserCompany(companyV1, uc);
			//ac = daoAdherentCareco.findById( daoImportationV1.findByOrgine("Adherent", c.getAdherentId()).get(0).getIdCourant() );
			ac = daoAdherentCareco.findByIdOrigine(companyV1.getAdherentId());
			uc.setIdAdherent(ac);
			uc.setIdGroupeEntreprise(grp);
			uc.setTableOrigine("Company");
			uc.setIdOrigine(companyV1.getId());
			uc.setIdMoteurClub(mapIDCompanyV1MC.get(companyV1.getId()));
			uc = daoUserCompany.merge(uc);
			//daoImportationV1.merge(new ImportationV1("Company",c.getId(),"UserCompany",uc.getId()));
			
			nbCompanyOK++;
			System.out.println("Company "+nbCompanyOK+"/"+totalNbCompany);
		}
		
		System.out.println("Importation des utilisateurs");
		List<Role> r = daoRoleServiceRemote.selectAll();
		mapRole = new HashMap<String, Role>();
		for (Role role : r) {
			if(role.getRole().equals("admin")) {
				mapRole.put("admin", role);
			} else if(role.getRole().equals("manageur")) {
				mapRole.put("manager", role);
			} else if(role.getRole().equals("vendeur")) {
				mapRole.put("vendor", role);
			} else if(role.getRole().equals("stockeur")) {
				mapRole.put("stocker", role);
			}
			mapRole.put(role.getRole(), role);
		}
		int totalNbUser = listeCoreUser.size();
		int nbUserOK = 0;
		for (CoreUser uv1 : listeCoreUser) {
			user = new User();
			user = mapper.mapCoreUserToUser(uv1, user);
			
			if(mapUserV1IdCompany.get(uv1.getId())!=null) {
				//user.setUserCompany(daoUserCompany.findById(mapUserV1IdCompany.get(u.getId()).getId()));
				//user.setUserCompany(daoUserCompany.findById( daoImportationV1.findByOrgine("Company",mapUserV1IdCompany.get(u.getId()).getId()).get(0).getIdCourant() ));
				user.setUserCompany( daoUserCompany.findByIdOrigine(mapUserV1IdCompany.get(uv1.getId()).getId()) );
			} else {
				System.out.println("Pas de Company pour l'utilisateur : "+uv1.getId()+" *** "+uv1.getEmail());
			}
			//Enregistrement de l'utilisateur
			user.setTableOrigine("CoreUser");
			user.setIdOrigine(uv1.getId());
			user = daoUser.merge(user);
			//daoImportationV1.merge(new ImportationV1("CoreUser",u.getId(),"User",user.getId()));
			
			//ajout des roles
			String[] rolesV1 = uv1.getRoles().split(",");
			List<UserRole> ur = new ArrayList<UserRole>();
			for (String ro : rolesV1) {
				if(mapRole.get(ro)!=null) {
					ur.add(new UserRole(user,mapRole.get(ro)));
				} else {
					System.out.println("Pas de role : "+ro);
				}
			}
			user.setRoles(ur);
			
			daoUser.merge(user);
			
			nbUserOK++;
			System.out.println("User "+nbUserOK+"/"+totalNbUser);
		}
		addTestUser();
		

		System.out.println("Importation des pièces en stocks");
		int i = 1;
		int totalNbPiece = listeCarPart.size();
		int nbPieceOK = 0;
		for (CarPart pieceV1 : listeCarPart) {
			System.out.println("==================================> Piece : "+i);
			s = new Stock();
			s = mapper.mapCarPartToStock(pieceV1, s);
			
//			s.setIdStock( daoUserCompany.findById( daoImportationV1.findByOrgine("Company", u.getCompanyId()).get(0).getIdCourant() ) );
//			s.setIdStockOrigine( daoUserCompany.findById( daoImportationV1.findByOrgine("Company", u.getCompanyId()).get(0).getIdCourant() ) );
			s.setIdStock( daoUserCompany.findByIdOrigine(/*mapUserV1IdCompany.get(*/pieceV1.getCompanyId()/*).getId()*/) );
			s.setIdStockOrigine( daoUserCompany.findByIdOrigine(/*mapUserV1IdCompany.get(*/pieceV1.getCompanyId()/*).getId()*/) );
			
			s.setTableOrigine("CarPart");
			s.setIdOrigine(pieceV1.getId());
			
			s = daoStock.merge(s);
			//daoImportationV1.merge(new ImportationV1("CarPart",u.getId(),"Stock",s.getId()));
			
			nbPieceOK++;
			System.out.println("Pièce stock "+nbPieceOK+"/"+totalNbPiece);
			
			i++;
			if(maxTest!=0 && i>=maxTest) {
				break;
			}
		}
		
		datefin = new Date();
		System.out.println("Début importation : "+dateDebut+" Fin importation : "+datefin);
		
	}
	
	/**
	 * Mise à jour du stock depuis le dump de la V1, importation de nouvelle pièce si dump plus récent.
	 */
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void updateStockWithV1() {
		v1Mapper mapper = new v1Mapper();
		Date dateDebut = new Date();
		Date datefin = null;
		int totalNbPiece = listeCarPart.size();
		int nbPieceOK = 0;
		System.out.println("Mise à jour des pièces en stocks");
		Stock s = null;
		int i = 1;
		for (CarPart pieceV1 : listeCarPart) {
			System.out.println("==================================> Piece : "+i+" ===> ID V1 : "+pieceV1.getId());
			s = daoStock.findByIdOrigine(pieceV1.getId());
			if(s==null) {
				s = new Stock();
				
				s.setIdStock( daoUserCompany.findByIdOrigine(/*mapUserV1IdCompany.get(*/pieceV1.getCompanyId()/*).getId()*/) );
				s.setIdStockOrigine( daoUserCompany.findByIdOrigine(/*mapUserV1IdCompany.get(*/pieceV1.getCompanyId()/*).getId()*/) );
				
				//origine
				s.setTableOrigine("CarPart");
				s.setIdOrigine(pieceV1.getId());
				
			} else {
				System.out.println("Update pièce : "+s.getId());
			}
			s = mapper.mapCarPartToStock(pieceV1, s);
			
			s = daoStock.merge(s);
			
			nbPieceOK++;
			System.out.println("Pièce stock "+nbPieceOK+"/"+totalNbPiece);
			
			i++;
			if(maxTest!=0 && i>=maxTest) {
				break;
			}
		}
		
		datefin = new Date();
		System.out.println("Début importation : "+dateDebut+" Fin importation : "+datefin);
	}
	
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void updateStockWithAAA() {
		updateStockWithAAA(false);
	}
	
	/**
	 * Mise à jour du stock à partir des infromations du cache AAA ou du webservice
	 * @param tout - vrai si on souhaite mettre à jour toute les pièces et non uniquement celles provenant de la V1
	 */
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void updateStockWithAAA(boolean tout) {
		v1Mapper mapper = new v1Mapper();
		Date dateDebut = new Date();
		Date datefin = null;
		int nbPieceOK = 0;
		System.out.println("Mise à jour des pièces en stocks depuis le webservice AAA ou le cache");
		int i = 1;
		List<Stock> l = stockService.selectAll();
		int totalNbPiece = l.size();
		VehiculeCacheAAA cacheAAA = null;
		for (Stock piece : l) {
			
			System.out.println("==================================> Piece : "+i);
			
			if(piece.getTableOrigine()!=null || tout) {
				//cette piece vient de la v1 ou force maj sur toute les pièces
		
				if(piece!=null && piece.getImmatriculation()!=null && !piece.getImmatriculation().equals("") ) {
					try {
						cacheAAA = vehiculeCacheAAA.wsSiVinConsulterVehiculeParImmatOuVin(VehiculeCacheAAA.immatPourWebService(piece.getImmatriculation()), null);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if(cacheAAA!=null) {
						
						//origine
						//stocker l'id de la ligne du cache utilisé
						piece.setCacheAAA(cacheAAA.getCode_Immat());
						
						piece.setCNITTypeMine(cacheAAA.getType());
						piece.setVin(cacheAAA.getType_Vin_Cg());
						try {
							piece.setDate1erMiseEnCirculation(LibDate.stringToDate(cacheAAA.getDate_1er_Cir_Jour()+"/"+cacheAAA.getDate_1er_Cir_Mois()+"/"+cacheAAA.getDate_1er_Cir_Annee()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						//pas sur dans AAA d'après CARECO
						//piece.setEnergie(cacheAAA.getEnergie());
						//piece.setMarque(cacheAAA.getMarque());
						//piece.setModele(cacheAAA.getModele());
						//piece.setVersion(cacheAAA.getVersion());
	
						//faire un fichier de log
						
						System.out.println("Update pièce avec infos AAA : "+piece.getId());
					}
				}
			}

			piece = daoStock.merge(piece);
			
			nbPieceOK++;
			System.out.println("Pièce stock "+nbPieceOK+"/"+totalNbPiece);
			
			i++;
			if(maxTest!=0 && i>=maxTest) {
				break;
			}
		}
		System.out.println("Fin de la mise à jour des pièces en stocks depuis le webservice AAA ou le cache");
		
		datefin = new Date();
		System.out.println("Début importation : "+dateDebut+" Fin importation : "+datefin);
	}
	
	/**
	 * Mise à jour du stock depuis le dump moteur club qui a servi pour la création de la V1
	 */
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void updateStockWithDumpMoteurClub() {
		v1Mapper mapper = new v1Mapper();
		Date dateDebut = new Date();
		Date datefin = null;
		int nbPieceOK = 0;
		System.out.println("Mise à jour des pièces en stocks depuis le dump Moteur Club");
		int i = 1;
		List<Stock> l = stockService.selectAll();
		int totalNbPiece = l.size();
		StockMoteurClub smc = null;
		for (Stock piece : l) {
			
			System.out.println("==================================> Piece : "+i);
			
			if(piece.getTableOrigine()!=null) {
				//cette piece vient de la v1
		
				if(piece!=null && piece.getImmatriculation()!=null && !piece.getImmatriculation().equals("") ) {
					smc = daoDumpMoteurClub.findWithLocalStock(piece);
					if(smc!=null) {
						
						//origine
						//stocker l'id de la ligne du dump utilisé
						piece.setDumpMoteurClub(smc.getStock_id());
						
						piece.setGarantie(smc.getGarantie());
						if(smc.getProduit_demonte()!=null) {
							piece.setDemonte(LibConversion.intToBoolean(smc.getProduit_demonte()));
						}
						if(smc.getExport()!=null) {
							piece.setExport(LibConversion.intToBoolean(smc.getExport()));
						}
						if(smc.getDate_achat()!=null) {
							try {
								piece.setDateAchat(LibDate.stringToDate(smc.getDate_achat()));
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						
						System.out.println("Update pièce avec infos Dump Moteur Club de fevrier : "+piece.getId());
					}
				}
			}

			piece = daoStock.merge(piece);
			
			nbPieceOK++;
			System.out.println("Pièce stock "+nbPieceOK+"/"+totalNbPiece);
			
			i++;
			if(maxTest!=0 && i>=maxTest) {
				break;
			}
		}
		System.out.println("Fin de la mise à jour des pièces en stocks depuis le dump Moteur Club");
		
		datefin = new Date();
		System.out.println("Début importation : "+dateDebut+" Fin importation : "+datefin);
	}
	
	public void addTestUser() {
		Role roleAdmin = daoRoleServiceRemote.findByCode("admin");
		Role roleUtilisateur = daoRoleServiceRemote.findByCode("utilisateur");
		List<UserRole> ur =null;
		User u = null;
		
		//daoUser.findByCode("nicolas");
		u = new User();
		u.setUsername("nicolas");
		u.setPasswd(u.passwordHashSHA256_Base64("lgr006"));
		ur = new ArrayList<UserRole>();
		ur.add(new UserRole(u,roleAdmin));
		u.setRoles(ur);
		u.setUserCompany(daoUserCompany.findById(1));
		
		daoUser.merge(u);
		
		u = new User();
		u.setUsername("legrain");
		u.setPasswd(u.passwordHashSHA256_Base64("lgr"));
		ur = new ArrayList<UserRole>();
		ur.add(new UserRole(u,roleUtilisateur));
		u.setRoles(ur);
		u.setUserCompany(daoUserCompany.findById(1));
		
		daoUser.merge(u);
		
		u = daoUser.findByCode("ppottier");
//		u = new User();
//		u.setUsername("ppottier");
		u.setPasswd(u.passwordHashSHA256_Base64("7226"));
		ur = new ArrayList<UserRole>();
		ur.add(new UserRole(u,roleAdmin));
		u.setRoles(ur);
		u.setUserCompany(daoUserCompany.findById(1));
		
		daoUser.merge(u);
	}
	
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public List<ImportStockTemp> findImport() {
		try {
			return daoImportStockTemp.findByUserImportStockTemp(userService.findUserLogged().getUserCompany().getId());
		} catch(Exception e) {
			return new ArrayList<ImportStockTemp>();
		}
	}
	
	@TransactionTimeout(value = 600, unit = TimeUnit.MINUTES)
	public void validerImportationStock() {

		try {
			stockService.validerImportationStock(userService.findUserLogged().getUserCompany().getId(),premiereImportationTexte());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void annulerImportationStock() {
		stockService.annulerImportationStock(userService.findUserLogged().getUserCompany().getId());
	}
	
	public Boolean premiereImportationTexte() {
		List<Stock> listeStock = daoStock.findWithJPQLQuery("select a from Stock a where a.importTypeFichier is null and a.idStock.id="+userService.findUserLogged().getUserCompany().getId());
		if(listeStock.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}


}
