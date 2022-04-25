package fr.legrain.careco.webapp.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.model.mapping.mapper.StockMapper;
import fr.legrain.bdg.tiers.service.remote.IFicheCarecoServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.lib.data.LibDate;
import fr.legrain.tiers.dto.StockDTO;
import fr.legrain.tiers.model.FicheCareco;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;

@ManagedBean
@ViewScoped 
public class ExpertiseVHUBean implements Serializable {  
   
	private static final long serialVersionUID = -1552329127018220675L;
	private String immatriculation;
	private String immatriculationTest;
	private String numLivrePolice;
	private int nbKms = 0;
	
	private String codeMoteurAjout;
	private String commentaireMoteurAjout;
	
	private String refBoiteAjout;
	private String commentaireBoiteAjout;
	
	private FicheCareco ficheGenere = null;
	
	private boolean pieceOrpheline = false;
	
	private Boolean moteurEnregistre = false;
	private Boolean boiteEnregistre = false;
	
	private User user;
	
//	private Stock moteur;
//	private Stock boite;
	
	private Stock moteur = new Stock();
	private Stock boite = new Stock();
	
	@EJB
    private IStockServiceRemote stockService;
	
	@EJB
    private IFicheCarecoServiceRemote ficheCarecoService;
	
	@ManagedProperty(value="#{rechercheVehicule}")
	private RechercheVehiculeView rv;
	
	@PostConstruct
	public void init() {
		user = Auth.findUserInSession();
		rv.setAjoutPossible(true);
//		moteur = new Stock();
//		boite = new Stock();
		
		//pour formulaire pièce orphelines
		moteur.setPrixMinimum(new BigDecimal(1));
		boite.setPrixMinimum(new BigDecimal(1));
	}
	
//	@ManagedProperty(value="#{rechercheCarteGriseBean.infosAAA}")
//	private VehiculeCacheAAA infosAAA;
//	public VehiculeCacheAAA getInfosAAA() {
//		return infosAAA;
//	}
//	public void setInfosAAA(VehiculeCacheAAA infosAAA) {
//		this.infosAAA = infosAAA;
//	}
	
	
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public String getNumLivrePolice() {
		return numLivrePolice;
	}
	public void setNumLivrePolice(String numLivrePolice) {
		this.numLivrePolice = numLivrePolice;
	}
	public int getNbKms() {
		return nbKms;
	}
	public void setNbKms(int nbKms) {
		this.nbKms = nbKms;
	}
	public Stock getMoteur() {
		return moteur;
	}
	public void setMoteur(Stock moteur) {
		this.moteur = moteur;
	}
	public Stock getBoite() {
		return boite;
	}
	public void setBoite(Stock boite) {
		this.boite = boite;
	}
	
	public VehiculeCacheAAA getInfosAAA() {
//	    FacesContext context = FacesContext.getCurrentInstance();
//	    return (VehiculeCacheAAA) context.getApplication().evaluateExpressionGet(context,"#{rechercheCarteGriseBean.infosAAA}", VehiculeCacheAAA.class);
		return rv.getInfosAAA();
	}
	
	public void selectionMoteur(ActionEvent event) {
		String typeMoteur = (String) event.getComponent().getAttributes().get("typeMoteur");
		
		moteur.setRefConstructeur(typeMoteur);
		moteur.setNumLivrePolice(numLivrePolice);
		moteur.setKms(nbKms);
		moteur.setImmatriculation(immatriculation);
		moteur.setPrixMinimum(new BigDecimal(1));

	}
	
	public void selectionBoite(ActionEvent event) {
		String typeBoite = (String) event.getComponent().getAttributes().get("typeBoite");
		
		boite.setRefConstructeur(typeBoite);
		boite.setNumLivrePolice(numLivrePolice);
		boite.setKms(nbKms);
		boite.setImmatriculation(immatriculation);
		boite.setPrixMinimum(new BigDecimal(1));
	}
	
	public void stockMoteurOrphelin() {
		pieceOrpheline = true;
		stockMoteur();
	}
	
	public void stockMoteur() {
		try {
			VehiculeCacheAAA infosAAA = null;
			
			if(!pieceOrpheline) {
				infosAAA = getInfosAAA();
			}
			
			moteur.setIdStock(user.getUserCompany());
			moteur.setIdStockOrigine(user.getUserCompany());
			moteur.setTypeDePiece("Moteur");
			
			if(!pieceOrpheline) {
				moteur.setDate1erMiseEnCirculation(LibDate.stringToDate(infosAAA.getDate_1er_Cir_Jour()+"/"+infosAAA.getDate_1er_Cir_Mois()+"/"+infosAAA.getDate_1er_Cir_Annee()));
			
				moteur.setCNITTypeMine(infosAAA.getType());
				moteur.setVin(infosAAA.getType_Vin_Cg());
				
				moteur.setMarque(infosAAA.getMarque());
				moteur.setModele(infosAAA.getModele());
				moteur.setVersion(infosAAA.getVersion());
			}
			
			moteur.setVendeur(user.getUserCompany().getNom());
			moteur.setDateAchat(new Date());
			moteur.setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
					
			//stockService.enregistrerPersist(moteur);
			moteur = stockService.enregistrerMerge(moteur);
			
			FacesContext context = FacesContext.getCurrentInstance();  
		    context.addMessage(null, new FacesMessage("Stock", 
		    		"Le moteur "+moteur.getRefConstructeur()+" a bien été enregistré dans votre stock."
		    		));  
		    moteurEnregistre = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stockBoiteOrpheline() {
		pieceOrpheline = true;
		stockBoite();
	}
	
	public void stockBoite() {
		try {
			
			VehiculeCacheAAA infosAAA = null;
			if(!pieceOrpheline) {
				infosAAA = getInfosAAA();
			}
			
			boite.setIdStock(user.getUserCompany());
			boite.setIdStockOrigine(user.getUserCompany());
			boite.setTypeDePiece("Boite");
			
			if(!pieceOrpheline) {
				boite.setDate1erMiseEnCirculation(LibDate.stringToDate(infosAAA.getDate_1er_Cir_Jour()+"/"+infosAAA.getDate_1er_Cir_Mois()+"/"+infosAAA.getDate_1er_Cir_Annee()));
				boite.setCNITTypeMine(infosAAA.getType());

				boite.setVin(infosAAA.getType_Vin_Cg());

				boite.setMarque(infosAAA.getMarque());
				boite.setModele(infosAAA.getModele());
				boite.setVersion(infosAAA.getVersion());
			}
			
			boite.setVendeur(user.getUserCompany().getNom());
			boite.setDateAchat(new Date());
			boite.setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
		
			//stockService.enregistrerPersist(boite);
			boite = stockService.enregistrerMerge(boite);
			
			FacesContext context = FacesContext.getCurrentInstance();  
		    context.addMessage(null, new FacesMessage("Stock", 
		    		"La boite à vitesse "+boite.getRefConstructeur()+" a bien été enregistrée dans votre stock."
		    		)); 
			
		    boiteEnregistre = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void recherche() throws Exception {
		if(immatriculationTest!=null && !immatriculationTest.equals("")) {
			System.out.println("Utilisation de immat test : "+immatriculation);
			immatriculation = immatriculationTest;
		}
		rv.rechercheImmatOuVIN(immatriculation, null);
	}
	
	
	public void ajoutMontageMoteur(ActionEvent event) throws Exception {
		
		if(ficheGenere==null) {
			ficheGenere = rv.generateNewFicheInfo();
		}
		ficheGenere.setTypeMot(codeMoteurAjout);
		
		String codeMoteurCompat = "";
		boolean first = true;
		for (String codeMoteurAutresFiche : rv.getListeCodeMoteur()) {
			if(first) {
				codeMoteurCompat+=codeMoteurAutresFiche;
				first = false;
			} else {
				codeMoteurCompat+="|"+codeMoteurAutresFiche;
			}
		}
		ficheGenere.setCodeMot(codeMoteurCompat);
		if(commentaireMoteurAjout!=null && !commentaireMoteurAjout.equals("")) {
			ficheGenere.setObsMot(commentaireMoteurAjout);
		}
		ficheGenere = ficheCarecoService.enregistrerMerge(ficheGenere);
		
		moteur.setRefConstructeur(codeMoteurAjout);
		moteur.setNumLivrePolice(numLivrePolice);
		moteur.setKms(nbKms);
		moteur.setImmatriculation(immatriculation);
		moteur.setPrixMinimum(new BigDecimal(1));
		
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("VHU", 
	    		"Confirmation ajout d'un nouveau type de moteur."
	    		)); 
	}
	
	public void ajoutMontageBoite(ActionEvent event) throws Exception {
		
		if(ficheGenere==null) {
			ficheGenere = rv.generateNewFicheInfo();
		}
		ficheGenere.setRefBte(refBoiteAjout);
		
		String codeBoiteCompat = "";
		boolean first = true;
//		for (String codeBoiteAutresFiche : rv.getListeCodeMoteur()) {
		for (String codeBoiteAutresFiche : rv.getListeCodeBoite()) {
			if(first) {
				codeBoiteCompat+=codeBoiteAutresFiche;
				first = false;
			} else {
				codeBoiteCompat+="|"+codeBoiteAutresFiche;
			}
		}
		ficheGenere.setCodeBte(codeBoiteCompat);
		if(commentaireBoiteAjout!=null && !commentaireBoiteAjout.equals("")) {
			ficheGenere.setObsBte(commentaireBoiteAjout);
		}
		ficheGenere = ficheCarecoService.enregistrerMerge(ficheGenere);
		
		boite.setRefConstructeur(refBoiteAjout);
		boite.setNumLivrePolice(numLivrePolice);
		boite.setKms(nbKms);
		boite.setImmatriculation(immatriculation);
		boite.setPrixMinimum(new BigDecimal(1));
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("VHU", 
	    		"Confirmation ajout d'un nouveau type de boite."
	    		)); 
	}

	public String getImmatriculationTest() {
		return immatriculationTest;
	}

	public void setImmatriculationTest(String immatriculationTest) {
		this.immatriculationTest = immatriculationTest;
	}

	public RechercheVehiculeView getRv() {
		return rv;
	}

	public void setRv(RechercheVehiculeView rv) {
		this.rv = rv;
	}

	public String getCodeMoteurAjout() {
		return codeMoteurAjout;
	}

	public String getCommentaireMoteurAjout() {
		return commentaireMoteurAjout;
	}

	public String getRefBoiteAjout() {
		return refBoiteAjout;
	}

	public String getCommentaireBoiteAjout() {
		return commentaireBoiteAjout;
	}

	public void setCodeMoteurAjout(String codeMoteurAjout) {
		this.codeMoteurAjout = codeMoteurAjout;
	}

	public void setCommentaireMoteurAjout(String commentaireMoteurAjout) {
		this.commentaireMoteurAjout = commentaireMoteurAjout;
	}

	public void setRefBoiteAjout(String refBoiteAjout) {
		this.refBoiteAjout = refBoiteAjout;
	}

	public void setCommentaireBoiteAjout(String commentaireBoiteAjout) {
		this.commentaireBoiteAjout = commentaireBoiteAjout;
	}

	public Boolean getMoteurEnregistre() {
		return moteurEnregistre;
	}

	public Boolean getBoiteEnregistre() {
		return boiteEnregistre;
	}

	public void setMoteurEnregistre(Boolean moteurEnregistre) {
		this.moteurEnregistre = moteurEnregistre;
	}

	public void setBoiteEnregistre(Boolean boiteEnregistre) {
		this.boiteEnregistre = boiteEnregistre;
	}
	
	public List<String> autoCompleteCasier(String query) {  
		List<String> results = new ArrayList<String>();

		/*
		 * rechercher dans le stock local uniquement
		 */
		return stockService.selectAllCasier(user.getUserCompany().getId(),query);
	}

   
}  
              