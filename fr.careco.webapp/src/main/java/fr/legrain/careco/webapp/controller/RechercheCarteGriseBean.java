package fr.legrain.careco.webapp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import fr.legrain.bdg.tiers.service.remote.IAnnuaireServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IFicheCarecoServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IPanierServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserServiceRemote;
import fr.legrain.careco.aaa.model.IVehiculeCacheAAAServiceRemote;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.lib.data.LibDate;
import fr.legrain.tiers.dao.IStockDAO;
import fr.legrain.tiers.model.FicheCareco;
import fr.legrain.tiers.model.Panier;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;

@ManagedBean
@ViewScoped 
public class RechercheCarteGriseBean implements Serializable {  

	//saisies carte grise
	private String immatriculation;
//	private String immatriculationTest;
	private String vin;
	private String cnit;
	private String numLivrePolice;
	
	private Stock pieceSelectionPourPanierSansImmat = null;
	
	private String typeRecherche = "0";

	//saisies marque
	private String typeRechercheMarque = "1";
	private String marque;
	private String modele;
	private String anneeMin;
	private String anneeMax;
	private Map<String,String> marques;
	private Map<String,String> modeles;
	private Map<String,String> annees;

	//saisies piece
	private String typeMoteur;
	private String typeBoite;
	private String casier;
	private Integer kmsMin;
	private Integer kmsMax;

	//saisies annuaire
	private String annuaire;

	private User user;
	
	private Boolean rechercheSansImmat = false;


	//résultats
	private List<FicheCareco> listeFicheCareco;
	private VehiculeCacheAAA infosAAA;

	//résultats stocks
	private List<Stock> listeResultatCarecoNational;
	private List<Stock> listeResultatMonStock;
	private List<Stock> listeResultatMultiSiteStock;
	private List<Stock> listeResultatAmisStock;
	private Stock selectedElementResultatRechercheStockNationalCareco;

	@EJB
	private IVehiculeCacheAAAServiceRemote vehiculeCacheAAAService;

	@EJB
	private IFicheCarecoServiceRemote ficheCarecoService;

	@EJB
	private IStockServiceRemote stockService;

	@EJB
	private IAnnuaireServiceRemote annuaireService;

	@ManagedProperty(value="#{rechercheVehicule}")
	private RechercheVehiculeView rv;

	@PostConstruct
	public void init() {
		user = Auth.findUserInSession();
	}
	
	public void lastFocused() {
		System.out.println("typeRecherche : "+typeRecherche);
	}

	//	public void rechercheWS() throws ParseException {
	//		VehiculeCacheAAA v = vehiculeCacheAAAService.wsSiVinConsulterVehiculeParImmatOuVin(immatriculation,null);
	//		if(v!=null) {
	//			System.out.println(v.getType());
	//		}
	//	}


	public void recherche() throws Exception {

		System.out.println("RechercheCarteGriseBean.recherche()");
		System.out.println("Immatriculation : "+immatriculation);
		infosAAA = null;
		pieceSelectionPourPanierSansImmat = null;

		typeRecherche = "0";
		String typeRechercheImmatriculation = "1";
		String typeRechercheVIN = "2";
		String typeRechercheCNIT = "3";
		String typeRechercheLivrePolice = "4";

		typeRecherche = typeRechercheImmatriculation; //pour test

		try {
			if (typeRecherche.equals(typeRechercheImmatriculation)) {
	//			if(immatriculationTest!=null && !immatriculationTest.equals("")) {
	//				System.out.println("Utilisation de immat test : "+immatriculationTest);
	//				immatriculation = immatriculationTest;
	//			}
				rv.rechercheImmatOuVIN(immatriculation, null);
			} else if (typeRecherche.equals(typeRechercheVIN)) {
				System.out.println("Recherche par VIN");
				//stockService.findByVin(vin);
				rv.rechercheImmatOuVIN(null, vin);
			} else if (typeRecherche.equals(typeRechercheCNIT)) {
				System.out.println("Recherche par CNIT : "+cnit);
				List<Stock> l = stockService.findByCNIT(cnit);
				if(!l.isEmpty()) {
					if(l.get(0).getImmatriculation()!=null && !l.get(0).getImmatriculation().equals("")) {
						rv.rechercheImmatOuVIN(immatriculation, null);
					}
				}
			} else if (typeRecherche.equals(typeRechercheLivrePolice)) {
				System.out.println("Recherche par Num livre de police : "+numLivrePolice);
				List<Stock> l = stockService.findByNumLivrePolice(user.getUserCompany().getId(),numLivrePolice);
				if(!l.isEmpty()) {
					if(l.get(0).getImmatriculation()!=null && !l.get(0).getImmatriculation().equals("")) {
						rv.rechercheImmatOuVIN(immatriculation, null);
					}
				}
			}
		
		} catch(EJBException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_FATAL, "Erreur lors de la recherche : "+ e.getClass().getName(), ""));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
	                FacesMessage.SEVERITY_FATAL,  e.getMessage(),""));
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, e.getClass().getName(), e.getMessage()));
		}

		rechercheEnStock();

	}
	
	public void initPrixAmi(List<Stock> l) {
		List<SocietesAmies> amie = user.getUserCompany().findAmie(user.getUserCompany().getId());
		if(amie!=null) {
			Map<Integer,SocietesAmies> mapAmie = new HashMap<Integer, SocietesAmies>();
			for (SocietesAmies societesAmies : amie) {
				mapAmie.put(societesAmies.getSocieteB().getId(), societesAmies);
			}
			for (Stock s : l) {
				s.calculPrixAmie(mapAmie.get(s.getIdStock().getId()));
				s.setReductionAmie(mapAmie.get(s.getIdStock().getId()));
			}
		}
	}

	public void initPrixMultiSite(List<Stock> l) {
		List<SocietesAmies> amie = user.getUserCompany().findMultiSite(user.getUserCompany().getId());
		if(amie!=null) {
			Map<Integer,SocietesAmies> mapAmie = new HashMap<Integer, SocietesAmies>();
			for (SocietesAmies societesAmies : amie) {
				mapAmie.put(societesAmies.getSocieteB().getId(), societesAmies);
			}
			for (Stock s : l) {
				s.calculPrixAmie(mapAmie.get(s.getIdStock().getId()));
				s.setReductionAmie(mapAmie.get(s.getIdStock().getId()));
			}
		}
	}
	
	public void rechercheEnStock() throws ParseException {
		if(rv.getInfosAAA()!=null && !rv.getInfosAAA().estVide()) {
			
			//pour remplir liste code boite et moteur
			rv.getTabsAstuceBoite();
			rv.getTabsAstuceMoteur();
			
			//recupérer le CNIT
			//recupérer date début
			//recupérer date fin
			//rechercher dans les fiches careco avec CNIT, date deb et fin de mise en circulation
			//récuprer compatibilité boite et moteur
			String cnit = rv.getInfosAAA().getType();
			Date date1ereCirc = LibDate.stringToDate(rv.getInfosAAA().getDate_1er_Cir_Jour()+"/"+rv.getInfosAAA().getDate_1er_Cir_Mois()+"/"+rv.getInfosAAA().getDate_1er_Cir_Annee());
			List<FicheCareco> fc = ficheCarecoService.findByCNIT_TypeCG(cnit,date1ereCirc);
			// les différents onglets seront généré à partir de cette liste dans les méthodes getTabsAstuceMoteur() et getTabsAstuceBoite()
			listeFicheCareco = fc;


			//résultats stocks
			//faire les recherche de stock
			List<String> listeToutesRefRecherche = new ArrayList<String>();
			listeToutesRefRecherche.addAll(rv.getListeCodeMoteur());
			listeToutesRefRecherche.addAll(rv.getListeCodeBoite());

			//Mon stock
			//List<Stock> listeResultatMonStock;
			listeResultatMonStock = stockService.findByUserEtPieceStock(user.getUserCompany().getId(),listeToutesRefRecherche, null);

			//Multi Site
			listeResultatMultiSiteStock = new ArrayList<Stock>();
			if(user.getUserCompany().getIdAdherent()!=null) {
				List<Integer> idMultiSite = user.getUserCompany().getIdAdherent().findIdMultiSite(user.getUserCompany().getId());
				if(idMultiSite!=null) {
					listeResultatMultiSiteStock = stockService.findByMultiCompanyEtPieceStock(idMultiSite,listeToutesRefRecherche, null);
				}
				initPrixMultiSite(listeResultatMultiSiteStock);
			}

			//Stock ami
			listeResultatAmisStock = new ArrayList<Stock>();
			if(user.getUserCompany().getIdAdherent()!=null) {
				List<Integer> idAmie = user.getUserCompany().findIdAmie(user.getUserCompany().getId());
				if(idAmie!=null && !idAmie.isEmpty()) {
					listeResultatAmisStock = stockService.findByMultiCompanyEtPieceStock(idAmie,listeToutesRefRecherche, null);
				}
				initPrixAmi(listeResultatAmisStock);
			}

			//Stock National
			//List<Stock> listeResultatCarecoNational;
			listeResultatCarecoNational = stockService.findByGroupeEntrepiseEtPieceStock(user.getUserCompany().getIdGroupeEntreprise().getId(),listeToutesRefRecherche, null);

			//listeResultatCarecoNational = stockService.selectAll(); // pour test

		}
	}

	public void rechercheVIN() throws Exception {

		System.out.println("RechercheCarteGriseBean.rechercheVIN()");
		System.out.println("Vin : "+vin);
		infosAAA = null;
		immatriculation = null;
		
		System.out.println("Recherche par VIN");
		List<Stock> l = stockService.findByVin(vin);
		if(!l.isEmpty()) {
			Iterator<Stock> it = l.iterator();
			boolean trouve = false;
			//recherche par rapport à une des immat du stock ?
			//ou recherche par rapport à tous les refConstructeur du stock pour ce cnit ?
			while (it.hasNext() && !trouve) {
				Stock type = (Stock) it.next();
				if(type.getImmatriculation()!=null && !type.getImmatriculation().equals("")) {
					trouve = true;
					System.out.println("Recherche de d'une immat à partir du vin du stock pour profiter du cache");
					//immatriculation = type.getImmatriculation();
					rv.rechercheImmatOuVIN(type.getImmatriculation(), null);
					if(rv.getInfosAAA()==null) {
						System.out.println("Pas d'infos dans le cache, appel du werbservice sur le VIN");
						rv.rechercheImmatOuVIN(null, vin);
					}
				}	
			}
		}

		rechercheEnStock();
	}

	public void rechercheCNIT() throws Exception {

		System.out.println("RechercheCarteGriseBean.rechercheCNIT()");
		System.out.println("CNIT : "+cnit);
		infosAAA = null;
		immatriculation = null;

		System.out.println("Recherche par CNIT : "+cnit);
		List<Stock> l = stockService.findByCNIT(cnit);
		if(!l.isEmpty()) {
			Iterator<Stock> it = l.iterator();
			boolean trouve = false;
			//recherche par rapport à une des immat du stock ?
			//ou recherche par rapport à tous les refConstructeur du stock pour ce cnit ?
			while (it.hasNext() && !trouve) {
				Stock type = (Stock) it.next();
				if(type.getImmatriculation()!=null && !type.getImmatriculation().equals("")) {
					trouve = true;
					//immatriculation = type.getImmatriculation();
					rv.rechercheImmatOuVIN(type.getImmatriculation(), null);
				}	
			}
//			if(l.get(0).getImmatriculation()!=null && !l.get(0).getImmatriculation().equals("")) {
//				rv.rechercheImmatOuVIN(immatriculation, null);
//			}
		}

		rechercheEnStock();
	}

	public void rechercheLivrePolice() throws Exception {

		System.out.println("RechercheCarteGriseBean.rechercheLivrePolice()");
		System.out.println("Livre de police : "+numLivrePolice);
		infosAAA = null;
		immatriculation = null;

		System.out.println("Recherche par Num livre de police : "+numLivrePolice);
		List<Stock> l = stockService.findByNumLivrePolice(user.getUserCompany().getId(),numLivrePolice);
		if(!l.isEmpty()) {
			if(l.get(0).getImmatriculation()!=null && !l.get(0).getImmatriculation().equals("")) {
				rv.rechercheImmatOuVIN(l.get(0).getImmatriculation(), null);
				
				rechercheEnStock();
			}else {
				//pas d'immat pour afficher des infos et faire une recherche de fiche infos, et eventuellement chercher des pièces compatibles dans tous les stocks
				//on affiche juste le stock local
				listeResultatMonStock = l;
			} 
		}

	}

	public void rechercheParModele() throws EJBException, Exception {
		
		System.out.println("RechercheCarteGriseBean.rechercheParModele()");
		System.out.println("Type de recherche : "+typeRechercheMarque);
		System.out.println("Marque : "+marque);
		System.out.println("Modele : "+modele);
		System.out.println("Annee Min : "+anneeMin);
		System.out.println("Annee Max : "+anneeMax);
		
		immatriculation = null;
		
		String typePiece = null;
		if(typeRechercheMarque.equals("2")) {
			typePiece = IStockDAO.TYPE_PIECE_MOTEUR;
		} else if(typeRechercheMarque.equals("2")) {
			typePiece = IStockDAO.TYPE_PIECE_BOITE_VITESSE;
		}
		
		/*
		 * Recherche "optimale" :
		 * rechercher dans le cache tous les véhicule correspondant pour obtenir les CNIT et année date de mise en circulation correspondant,
		 * vehiculeCacheAAAService.rechercheCNITAnnee(marque, modele, anneeMin, anneeMax);
		 * à partir de ces infos, faire les recherches de fiche infos correspondante
		 * a prtir des fiches infos récupérer la liste des références constructeur et code compatible
		 * faire la recherche dans les stocks
		 * OU
		 * Recherche "simple et moins complète" :
		 * rechercher directement dans la table stock
		 */
//		vehiculeCacheAAAService.rechercheCNITAnnee(marque, modele, anneeMin, anneeMax);
		
		infosAAA = null;

		List<Stock> l = stockService.findByMarqueModeleAnnee(user.getUserCompany().getId(),typePiece,marque,modele,anneeMin,anneeMax);
		
		if(!l.isEmpty()) {
			if(l.get(0).getImmatriculation()!=null && !l.get(0).getImmatriculation().equals("")) {
				rv.rechercheImmatOuVIN(immatriculation, null);
			}
		}

		rechercheEnStock();

		FacesContext context = FacesContext.getCurrentInstance();  
		context.addMessage(null, new FacesMessage("Recherche", 
				"Recherche par modèle."
				)); 

	}

	public void recherchePiece() {

		FacesContext context = FacesContext.getCurrentInstance();  
		context.addMessage(null, new FacesMessage("Recherche", 
				"Recherche par pièce."
				)); 
	}
	
	public void rechercheMoteur(ActionEvent event) {  
		immatriculation = null;
		
		//Mon stock
		//List<Stock> listeResultatMonStock;
		listeResultatMonStock = stockService.findByUserEtPieceStock(user.getUserCompany().getId(),typeMoteur, null);

		//Multi Site
		listeResultatMultiSiteStock = new ArrayList<Stock>();
		if(user.getUserCompany().getIdAdherent()!=null) {
			List<Integer> idMultiSite = user.getUserCompany().getIdAdherent().findIdMultiSite(user.getUserCompany().getId());
			if(idMultiSite!=null) {
				listeResultatMultiSiteStock = stockService.findByMultiCompanyEtPieceStock(idMultiSite,typeMoteur, null);
			}
			initPrixMultiSite(listeResultatMultiSiteStock);
		}

		//Stock ami
		listeResultatAmisStock = new ArrayList<Stock>();
		if(user.getUserCompany().getIdAdherent()!=null) {
			List<Integer> idMultiSite = user.getUserCompany().findIdAmie(user.getUserCompany().getId());
			if(idMultiSite!=null) {
				listeResultatAmisStock = stockService.findByMultiCompanyEtPieceStock(idMultiSite,typeMoteur, null);
			}
			initPrixAmi(listeResultatAmisStock);
		}
		

		//Stock National
		//List<Stock> listeResultatCarecoNational;
		listeResultatCarecoNational = stockService.findByGroupeEntrepiseEtPieceStock(user.getUserCompany().getIdGroupeEntreprise().getId(),typeMoteur, null);
		
	}

	public void rechercheBoite(ActionEvent event) { 
		immatriculation = null;
		
		//Mon stock
		//List<Stock> listeResultatMonStock;
		listeResultatMonStock = stockService.findByUserEtPieceStock(user.getUserCompany().getId(),typeBoite, null);

		//Multi Site
		listeResultatMultiSiteStock = new ArrayList<Stock>();
		if(user.getUserCompany().getIdAdherent()!=null) {
			List<Integer> idMultiSite = user.getUserCompany().getIdAdherent().findIdMultiSite(user.getUserCompany().getId());
			if(idMultiSite!=null) {
				listeResultatMultiSiteStock = stockService.findByMultiCompanyEtPieceStock(idMultiSite,typeBoite, null);
			}
			initPrixMultiSite(listeResultatMultiSiteStock);
		}

		//Stock ami
		listeResultatAmisStock = new ArrayList<Stock>();
		if(user.getUserCompany().getIdAdherent()!=null) {
			List<Integer> idMultiSite = user.getUserCompany().findIdAmie(user.getUserCompany().getId());
			if(idMultiSite!=null) {
				listeResultatAmisStock = stockService.findByMultiCompanyEtPieceStock(idMultiSite,typeBoite, null);
			}
		}

		//Stock National
		//List<Stock> listeResultatCarecoNational;
		listeResultatCarecoNational = stockService.findByGroupeEntrepiseEtPieceStock(user.getUserCompany().getIdGroupeEntreprise().getId(),typeBoite, null);
			
	}

	public void rechercheCasier(ActionEvent event) {  
		immatriculation = null;
		//casier
		
		//Mon stock
		//List<Stock> listeResultatMonStock;
		listeResultatMonStock = stockService.findByUserEtCasier(user.getUserCompany().getId(),casier);

		//Multi Site
		listeResultatMultiSiteStock = new ArrayList<Stock>();

		//Stock ami
		listeResultatAmisStock = new ArrayList<Stock>();

		//Stock National
		//List<Stock> listeResultatCarecoNational;	
		listeResultatCarecoNational = new ArrayList<Stock>();
	}

	public void detailPiece(SelectEvent event) {  
		FacesMessage message = new FacesMessage();  
		message.setSeverity(FacesMessage.SEVERITY_INFO);  
		message.setSummary("Reordered: "); 

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("detail_piece_careco_template.xhtml?id=" +selectedElementResultatRechercheStockNationalCareco.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}

	} 

	public Stock getSelectedElementResultatRechercheStockNationalCareco() {
		return selectedElementResultatRechercheStockNationalCareco;
	}

	public void setSelectedElementResultatRechercheStockNationalCareco(
			Stock selectedElementResultatRechercheStockNationalCareco) {
		this.selectedElementResultatRechercheStockNationalCareco = selectedElementResultatRechercheStockNationalCareco;
	}

	public List<Stock> getListeResultatCarecoNational() {
		return listeResultatCarecoNational;
	}

	public VehiculeCacheAAA getInfosAAA() {
		return infosAAA;
	}

	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getCnit() {
		return cnit;
	}
	public void setCnit(String cnit) {
		this.cnit = cnit;
	}
	public String getNumLivrePolice() {
		return numLivrePolice;
	}
	public void setNumLivrePolice(String numLivrePolice) {
		this.numLivrePolice = numLivrePolice;
	}

	public List<String> autoCompleteVIN(String query) {  
		List<String> results = new ArrayList<String>();

		/*
		 * rechercher dans stock local, puis stock national
		 */
		return stockService.selectAllVIN(user.getUserCompany().getIdGroupeEntreprise().getId(),user.getUserCompany().getId(),query);
	}

	public List<String> autoCompleteCNIT(String query) {  
		List<String> results = new ArrayList<String>(); 

		/*
		 * rechercher dans stock local, puis stock national, sans doublon
		 */
		return stockService.selectAllCNIT(user.getUserCompany().getIdGroupeEntreprise().getId(),user.getUserCompany().getId(),query); 
	} 

	public List<String> autoCompleteLivrePolice(String query) {  
		List<String> results = new ArrayList<String>();
		/*
		 * rechercher uniquement dans stock local
		 */
		return stockService.selectAllLivrePolice(user.getUserCompany().getId(),query); 
	}

	public List<String> autoCompleteTypeMoteur(String query) {  
		List<String> results = new ArrayList<String>();

		/*
		 * rechercher dans le stock national
		 */
		return stockService.selectAllTypeMoteur(user.getUserCompany().getId(),query); 
	}

	public List<String> autoCompleteTypeBoite(String query) {  
		List<String> results = new ArrayList<String>();

		/*
		 * rechercher dans le stock national
		 */
		return stockService.selectAllTypeBoite(user.getUserCompany().getId(),query);
	}

	public List<String> autoCompleteCasier(String query) {  
		List<String> results = new ArrayList<String>();

		/*
		 * rechercher dans le stock local uniquement
		 */
		return stockService.selectAllCasier(user.getUserCompany().getId(),query);
	}

	public List<String> autoCompleteAnnuaire(String query) {  
		List<String> results = new ArrayList<String>();

		/*
		 * rechercher dans le stock local uniquement
		 */
		return annuaireService.selectAll(query);
	}

	public List<Stock> getListeResultatMonStock() {
		return listeResultatMonStock;
	}

	public List<Stock> getListeResultatMultiSiteStock() {
		return listeResultatMultiSiteStock;
	}

	public List<Stock> getListeResultatAmisStock() {
		return listeResultatAmisStock;
	}

	public String getTypeRecherche() {
		return typeRecherche;
	}

	public String getMarque() {
		return marque;
	}

	public String getModele() {
		return modele;
	}

	public String getAnneeMin() {
		return anneeMin;
	}

	public String getAnneeMax() {
		return anneeMax;
	}

	public String getTypeMoteur() {
		return typeMoteur;
	}

	public String getTypeBoite() {
		return typeBoite;
	}

	public String getCasier() {
		return casier;
	}

	public Integer getKmsMin() {
		return kmsMin;
	}

	public Integer getKmsMax() {
		return kmsMax;
	}

	public Map<String, String> getMarques() {
		//marques = vehiculeCacheAAAService.selectAllMarque();
		if(marques==null) {
			marques = new HashMap<String, String>();
		}
		if(marques.isEmpty()) {
			List<String> l = vehiculeCacheAAAService.selectAllMarque();

			for (String el : l) {
				marques.put(el, el);
			}
			marques.remove(null);
		}

		return marques;
	}

	public void updateModeleFromMarque() {
		//modeles = vehiculeCacheAAAService.selectAllModele(marque);
		List<String> l = vehiculeCacheAAAService.selectAllModele(marque);
		if(modeles==null) modeles = new HashMap<String, String>(); else modeles.clear();
		for (String el : l) {
			modeles.put(el, el);
		}
	}

	public Map<String, String> getModeles() {
		if(modeles==null) modeles = new HashMap<String, String>();
		return modeles;
	}

	public Map<String, String> getAnnees() {

		//annees = vehiculeCacheAAAService.selectAllAnnee();
		List<String> l = vehiculeCacheAAAService.selectAllAnnee();
		if(annees==null) {
			annees = new HashMap<String, String>();
		}
		if(annees.isEmpty()) {
			for (String el : l) {
				annees.put(el, el);
			}
			annees.remove(null);
		}
		return annees;
	}

	public IStockServiceRemote getStockService() {
		return stockService;
	}

	public void setStockService(IStockServiceRemote stockService) {
		this.stockService = stockService;
	}

	public void setTypeRecherche(String typeRecherche) {
		this.typeRecherche = typeRecherche;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public void setAnneeMin(String anneeMin) {
		this.anneeMin = anneeMin;
	}

	public void setAnneeMax(String anneeMax) {
		this.anneeMax = anneeMax;
	}

	public void setTypeMoteur(String typeMoteur) {
		this.typeMoteur = typeMoteur;
	}

	public void setTypeBoite(String typeBoite) {
		this.typeBoite = typeBoite;
	}

	public void setCasier(String casier) {
		this.casier = casier;
	}

	public void setKmsMin(Integer kmsMin) {
		this.kmsMin = kmsMin;
	}

	public void setKmsMax(Integer kmsMax) {
		this.kmsMax = kmsMax;
	}

	public String getAnnuaire() {
		return annuaire;
	}

	public void setAnnuaire(String annuaire) {
		this.annuaire = annuaire;
	}

//	public String getImmatriculationTest() {
//		return immatriculationTest;
//	}
//
//	public void setImmatriculationTest(String immatriculationTest) {
//		this.immatriculationTest = immatriculationTest;
//	}

	public RechercheVehiculeView getRv() {
		return rv;
	}

	public void setRv(RechercheVehiculeView rv) {
		this.rv = rv;
	}

	public String getTypeRechercheMarque() {
		return typeRechercheMarque;
	}

	public void setTypeRechercheMarque(String typeRechercheMarque) {
		this.typeRechercheMarque = typeRechercheMarque;
	}

	public Boolean getRechercheSansImmat() {
		return rechercheSansImmat;
	}

	public void setRechercheSansImmat(Boolean rechercheSansImmat) {
		this.rechercheSansImmat = rechercheSansImmat;
	}

	public Stock getPieceSelectionPourPanierSansImmat() {
		return pieceSelectionPourPanierSansImmat;
	}

	public void setPieceSelectionPourPanierSansImmat(
			Stock pieceSelectionPourPanierSansImmat) {
		this.pieceSelectionPourPanierSansImmat = pieceSelectionPourPanierSansImmat;
	} 

}  
