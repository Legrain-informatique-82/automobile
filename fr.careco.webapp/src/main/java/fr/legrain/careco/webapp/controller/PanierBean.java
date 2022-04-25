package fr.legrain.careco.webapp.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import fr.legrain.bdg.tiers.service.remote.ILignePanierServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IPanierServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITransactionAchatVenteServiceRemote;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.tiers.model.Client;
import fr.legrain.tiers.model.GarantieCareco;
import fr.legrain.tiers.model.LignePanier;
import fr.legrain.tiers.model.Panier;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.TransactionAchatVente;
import fr.legrain.tiers.model.User;

@ManagedBean
@SessionScoped 
public class PanierBean implements Serializable {  
   
	private static final long serialVersionUID = -2015303376608860893L;
	
	private User user;
	private Calendar cal = Calendar.getInstance();
	
	private Boolean clientModif = false;
	private String  immatriculation;
	
	//private Panier panierEnCours;
	private Stock pieceEnCours;
	private LignePanier lignePanierEnCours;
	
	private Panier selectedDetailPanierB2C;
	private Panier selectedDetailPanierB2B;
	
	private Panier selectedPanierActifAccueil;
	private Panier selectedPanierActifAccueilB2C;
	private Panier selectedPanierActifAccueilB2B;
	
	private Panier detailPanier;
	private Panier detailPanierBas;
	
	private List<Panier> listePanierActif;
	private List<Panier> listePanierPerime;
	private List<Panier> listePanierActifOuPerime;
	private List<Panier> listePanierEnCours;
	private List<Panier> listePanierValider;
	private List<Panier> listePanierHistorique;
	
	private List<Panier> listePanierActifB2B;
	private List<Panier> listePanierPerimeB2B;
	private List<Panier> listePanierActifOuPerimeB2B;
	private List<Panier> listePanierEnCoursB2B;
	private List<Panier> listePanierValiderB2B;
	private List<Panier> listePanierHistoriqueB2B;
	
	@EJB
    private IPanierServiceRemote panierService;
	
	@EJB
    private ILignePanierServiceRemote lignePanierService;
	
	@EJB
    private IStockServiceRemote stockService;
	
	@EJB
    private ITransactionAchatVenteServiceRemote transactionAchatVenteService;
	
	@ManagedProperty(value="#{rechercheVehiculePanier}")
	private RechercheVehiculeSession rv;
	
	@ManagedProperty(value="#{rechercheVehiculePanier}")
	private RechercheVehiculeSession rvClient;
	
//	@ManagedProperty(value="#{rechercheVehiculeClientPanier}")
//	private RechercheVehiculeClientSession rvClient;
	
	@PostConstruct
	public void init() {
		user = Auth.findUserInSession();
		initAllPanier();
	}
	
	public void initAllPanier() {
		initAllPanier(false);
	}
	
	public void initAllPanier(boolean reinitialiseSelectionDetail) {
		
		int idDetailPanierAvantRefesh = -1;
		int idDetailLignePanierAvantRefesh = -1;
		if(detailPanier!=null) {
			idDetailPanierAvantRefesh = detailPanier.getId();
			if(lignePanierEnCours!=null) {
				idDetailLignePanierAvantRefesh = lignePanierEnCours.getId();
			}
		}
		
		boolean b2c = true;
		listePanierActif = panierService.findPanierActif(user,b2c);
		listePanierPerime = panierService.findPanierPerime(user,b2c);
		listePanierActifOuPerime = panierService.findPanierActifOuPerime(user,b2c);
		listePanierEnCours = panierService.findPanierEnCours(user,b2c);
		listePanierValider = panierService.findPanierValider(user,b2c);
		listePanierHistorique = panierService.findPanierHistorique(user,b2c);
		
		b2c = false;
		listePanierActifB2B = panierService.findPanierActif(user,b2c);
		listePanierPerimeB2B = panierService.findPanierPerime(user,b2c);
		listePanierActifOuPerimeB2B = panierService.findPanierActifOuPerime(user,b2c);
		listePanierEnCoursB2B = panierService.findPanierEnCours(user,b2c);
		listePanierValiderB2B = panierService.findPanierValider(user,b2c);
		listePanierHistoriqueB2B = panierService.findPanierHistorique(user,b2c);
		
		//si pas de panier, pas de client => la vue plante
		if((detailPanier==null || reinitialiseSelectionDetail) && listePanierActifOuPerime!=null && !listePanierActifOuPerime.isEmpty()) {
			detailPanier = listePanierActifOuPerime.get(0);
		} else if((detailPanier==null || reinitialiseSelectionDetail) && listePanierActifOuPerimeB2B!=null && !listePanierActifOuPerimeB2B.isEmpty()) {
			detailPanier = listePanierActifOuPerimeB2B.get(0);
		} else if((detailPanier==null || reinitialiseSelectionDetail) && listePanierActif!=null && !listePanierActif.isEmpty()) {
			detailPanier = listePanierActif.get(0);	
		} else if((detailPanier==null || reinitialiseSelectionDetail) && listePanierActifB2B!=null && !listePanierActifB2B.isEmpty()) {
			detailPanier = listePanierActifB2B.get(0);
//		} else if(listePanierPerime!=null && !listePanierPerime.isEmpty()) {
//			detailPanier = listePanierPerime.get(0);	 // pour éviter le null
		}
		
		if(idDetailPanierAvantRefesh!=-1 || reinitialiseSelectionDetail) {
			if(reinitialiseSelectionDetail) {
				idDetailPanierAvantRefesh = detailPanier.getId();
			}
//			for (Panier p : listePanierActif) {
//				if(p.getId()==idDetailPanierAvantRefesh) {
//					detailPanier = p;
//				}
//			}
//			for (Panier p : listePanierActifB2B) {
//				if(p.getId()==idDetailPanierAvantRefesh) {
//					detailPanier = p;
//				}
//			}
			for (Panier p : listePanierActifOuPerime) {
				if(p.getId()==idDetailPanierAvantRefesh) {
					detailPanier = p;
					selectedDetailPanierB2C = detailPanier;
				}
			}
			for (Panier p : listePanierActifOuPerimeB2B) {
				if(p.getId()==idDetailPanierAvantRefesh) {
					detailPanier = p;
					selectedDetailPanierB2B = detailPanier;
				}
			}
		}

		if(detailPanier!=null) {
			if(!detailPanier.getLignes().isEmpty()) {
				lignePanierEnCours = detailPanier.getLignes().get(0);
				
				if(idDetailLignePanierAvantRefesh!=-1) {
					for (LignePanier l : detailPanier.getLignes()) {
						if(l.getId().equals(idDetailLignePanierAvantRefesh)) {
							lignePanierEnCours = l;
						}
					}
				} 
				pieceEnCours = lignePanierEnCours.getIdPiece();
			} else {
				pieceEnCours = new Stock(); // pour éviter le null
				//lignePanierEnCours = new LignePanier();
			}

		}
		
		reinitialiseStatusPiece();
		
//		FacesContext context = FacesContext.getCurrentInstance();  
//	    context.addMessage(null, new FacesMessage("Panier", 
//	    		"Pas de panier à traiter"
//	    		));  
	}
	
	public void reinitialiseStatusPiece() {
		//
		//
		//Mise à jour du statut des lignes panier, à faire ailleur de façon automatique
		try {
			//pour les panier périme, les pièces redeviennent disponibles
			for (Panier p : listePanierPerime) {
				for(LignePanier l : p.getLignes()) {
					l.getIdPiece().setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
				}
				panierService.enregistrerMerge(p);
			}
			for (Panier p : listePanierPerimeB2B) {
				for(LignePanier l : p.getLignes()) {
					l.getIdPiece().setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
				}
				panierService.enregistrerMerge(p);
			}
			
			//pour les panier actif, s'assurer que tous les pièces sont en status panier
			for (Panier p : listePanierActif) {
				for(LignePanier l : p.getLignes()) {
					l.getIdPiece().setStatus(IStockServiceRemote.STATUS_PIECE_PANIER);
				}
				panierService.enregistrerMerge(p);
			}
			for (Panier p : listePanierActifB2B) {
				for(LignePanier l : p.getLignes()) {
					l.getIdPiece().setStatus(IStockServiceRemote.STATUS_PIECE_PANIER);
				}
				panierService.enregistrerMerge(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getNbPanierActif() {
		String nb = "*";
		int nbPanier = -1;
		if(listePanierActif!=null && listePanierActifB2B!=null) {
			nbPanier = listePanierActif.size()+listePanierActifB2B.size();
		}
		if(nbPanier==-1) {
			return nb;
		} else {
			return LibConversion.integerToString(nbPanier);
		}
	}
	
	public Panier cherchePanier(List<Panier> paniers, String immat, Integer idPanier) {
		Panier p = null;
		if(paniers!=null && !paniers.isEmpty()) {
			for (Panier pa : paniers) {
				if( (immat!=null && pa.getClient().getImmatriculation()!=null && pa.getClient().getImmatriculation().equalsIgnoreCase(immat))
						|| (idPanier!=null && pa.getId().equals(idPanier))) {
					p = pa;
				}
			}
		}
		return p;
	}
	
	public Panier cherchePanier(String immat, Integer idPanier) {
		detailPanier = null;
		if(listePanierActif !=null) {
			for (Panier p : listePanierActif) {
				if( (immat!=null && p.getClient().getImmatriculation()!=null && p.getClient().getImmatriculation().equalsIgnoreCase(immat))
						|| (idPanier!=null && p.getId().equals(idPanier))) {
					detailPanier = p;
				}
			}
		}
		if(detailPanier==null) {
			//immat non trouvée dans les paniers actifs B2C, on cherche dans les paniers actifs B2B.
			if(listePanierActif !=null) {
				for (Panier p : listePanierActif) {
					if( (immat!=null && p.getClient().getImmatriculation()!=null && p.getClient().getImmatriculation().equalsIgnoreCase(immat))
							|| (idPanier!=null && p.getId().equals(idPanier))) {
						detailPanier = p;
					}
				}
			}
		}
		if(detailPanier==null) {
			//immat non trouvée dans les paniers actifs, on cherche dans les paniers périmés, B2C et B2B.
			if(listePanierPerime !=null) {
				for (Panier p : listePanierPerime) {
					if( (immat!=null && p.getClient().getImmatriculation()!=null && p.getClient().getImmatriculation().equalsIgnoreCase(immat))
							|| (idPanier!=null && p.getId().equals(idPanier))) {
						//passe le panier en actif
						p.setDatePanier(new Date());
						cal.add(Calendar.HOUR_OF_DAY, 28);
						p.setDateFin(cal.getTime());
						
						detailPanier = p;
					} 
				}
			}
		}
		if(detailPanier==null) {
			//immat non trouvée dans les paniers actifs, on cherche dans les paniers périmés, B2C et B2B.
			if(listePanierPerimeB2B !=null) {
				for (Panier p : listePanierPerimeB2B) {
					if( (immat!=null && p.getClient().getImmatriculation()!=null && p.getClient().getImmatriculation().equalsIgnoreCase(immat))
							|| (idPanier!=null && p.getId().equals(idPanier))) {
						//passe le panier en actif
						p.setDatePanier(new Date());
						cal.add(Calendar.HOUR_OF_DAY, 28);
						p.setDateFin(cal.getTime());
						
						detailPanier = p;
					}
				}
			}
		}
		return detailPanier;
	}
	
	public void enregistreModificationPanierAccueil() throws Exception {
		//Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
    	if(selectedPanierActifAccueil!=null) {
    		selectedPanierActifAccueil = panierService.enregistrerMerge(selectedPanierActifAccueil);
    	}
		
		//modeModification = false;
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Contact", 
	    		"Le contact bien été modifié"
	    		)); 
	}
	
	public int findNbPanierContientPiece(int idPiece) throws FinderException {
		int nb = 0;
		Stock piece = stockService.findById(idPiece);
		if(piece!=null) {
			List<Panier> l = panierService.findPanierContientPiece(piece);
			if(l!=null) {
				nb = l.size();
			}
		}
		return nb;
	}
	
	public List<Panier> findPanierContientPiece(int idPiece) throws FinderException {
		List<Panier> l = new ArrayList<Panier>();
		Stock piece = stockService.findById(idPiece);
		if(piece!=null) {
			l = panierService.findPanierContientPiece(piece);
		}
		return l;
	}
	
	public void cherchePanierActionListener(ActionEvent event) throws Exception {
		
		String immatRecherche = (String) event.getComponent().getAttributes().get("immatRecherche");
		
		selectedPanierActifAccueil = cherchePanier(listePanierActif, immatRecherche, null);
		if(selectedPanierActifAccueil==null) selectedPanierActifAccueil = cherchePanier(listePanierActifB2B, immatRecherche, null);
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
	    		)); 
	}
	
	public void refreshPanier(ActionEvent event) throws Exception {
		initAllPanier();
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Raffraichissement de la liste de tous les paniers de la session."
	    		)); 
	    
	    System.out.println("Raffraichissement de la liste de tous les paniers de la session.");
	}
	
	/*
	 * Ajouter un article dans le panier courant
	 */
	public void ajoutPanier(ActionEvent event) throws Exception {
		//BigDecimal txTVADefaut = new BigDecimal("19.6");
//		BigDecimal txTVADefaut = new BigDecimal("20.0");
//		String typeClientDefaut = "btoc";
//		String nomClientDefaut = "Comptoir";
//		String prenomClientDefaut = "Client";
		
		Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
		String immatRecherche = (String) event.getComponent().getAttributes().get("immatRecherche");
//		String immatRechercheTest = (String) event.getComponent().getAttributes().get("immatRechercheTest");
		Integer idPanierActif = (Integer) event.getComponent().getAttributes().get("idPanierActif");
		
		SocietesAmies idSocieteAmieRemise = (SocietesAmies) event.getComponent().getAttributes().get("idSocieteAmieRemise");

//		
//		if(immatRechercheTest!=null && !immatRechercheTest.equals("")) {
//			immatRecherche = immatRechercheTest;
//		}
		
		//if(getRechercheCarteGriseBean().getImmatriculation()!=null) {
			//cherchePanier(getRechercheCarteGriseBean().getImmatriculation());
		if(immatRecherche!=null && !immatRecherche.equals("")) {
			immatRecherche = VehiculeCacheAAA.immatNettoyee(immatRecherche);
			cherchePanier(immatRecherche, null);
		} else if(idPanierActif!=null) {
			cherchePanier(null, idPanierActif);
		}
		
		Stock piece = stockService.findById(idPiece);
		piece.setStatus(IStockServiceRemote.STATUS_PIECE_PANIER);
		piece = stockService.merge(piece);
		
		//panierService.ajouterPanier(u,idPiece);
		if(detailPanier==null) {
			detailPanier = nouveauPanier(immatRecherche);
			
//			detailPanier = new Panier();
//			Date d = new Date();
//			cal.setTime(d);
//			
//			detailPanier.setDatePanier(d);
//			cal.add(Calendar.HOUR_OF_DAY, 28);
//			detailPanier.setDateFin(cal.getTime());
//			
//			detailPanier.setVendeur(user);
//			detailPanier.setIdEntreprise(user.getUserCompany());
//			
//			Client c = new Client();
//			//c.setImmatriculation(getRechercheCarteGriseBean().getImmatriculation());
//			c.setImmatriculation(immatRecherche);
//			c.setTva(txTVADefaut);
//			c.setType(typeClientDefaut);
//			c.setNom(nomClientDefaut);
//			c.setPrenom(prenomClientDefaut);
//			detailPanier.setClient(c);
//			detailPanier.setTva(c.getTva());
			
			//panierService.enregistrerMerge(detailPanier);
		}
		
		if(!detailPanier.pieceDejaDansPanier(piece)) {
		
			detailPanier.ajouteLigne(piece);
			
			detailPanier = panierService.enregistrerMerge(detailPanier);
			
			initGarantieCareco(detailPanier);
			
			detailPanier = panierService.enregistrerMerge(detailPanier);
			
			//listePanierActif.add(detailPanier);
			
			if(listePanierActif ==null) {
				listePanierActif=new ArrayList<Panier>();
			}
			
			initAllPanier();
			
			selectedPanierActifAccueil = cherchePanier(listePanierActif, null, detailPanier.getId());
			if(selectedPanierActifAccueil==null) selectedPanierActifAccueil = cherchePanier(listePanierActifB2B, null, detailPanier.getId());
			
			FacesContext context = FacesContext.getCurrentInstance();  
		    context.addMessage(null, new FacesMessage("Panier", 
		    		"La pièce "+idPiece+" a bien été placée dans le panier n°"+detailPanier.getId()
		    		)); 
		    System.out.println("La pièce "+idPiece+" a bien été placée dans le panier n°"+detailPanier.getId());
		} else {
			FacesContext context = FacesContext.getCurrentInstance();  
		    context.addMessage(null, new FacesMessage("Panier", 
		    		"La pièce "+idPiece+" est déjà présente dans le panier n°"+detailPanier.getId()
		    		));  
		    System.out.println("La pièce "+idPiece+" est déjà présente dans le panier n°"+detailPanier.getId());
		}
	}
	
	public RechercheCarteGriseBean getRechercheCarteGriseBean() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    return (RechercheCarteGriseBean) context.getApplication().evaluateExpressionGet(context,"#{rechercheCarteGriseBean}", RechercheCarteGriseBean.class);
	}
	
	public void selectionPrincipalPanier(ActionEvent event) throws FinderException {
		
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		detailPanier = panierService.findById(idPanier);

		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Sélection du panier principal"
	    		)); 
	}
	
    public void onRowSelectDetailPanierB2C(SelectEvent event) {  
    	detailPanier = selectedDetailPanierB2C;
    	
    	if(!detailPanier.getLignes().isEmpty()) {
    		lignePanierEnCours = selectedDetailPanierB2C.getLignes().get(0);
    		pieceEnCours = lignePanierEnCours.getIdPiece();
    	}
    	
    	if(detailPanier.getClient().getImmatriculation()!=null) {
    		try {
				rvClient.rechercheImmatOuVIN(detailPanier.getClient().getImmatriculation(), null);
			} catch (EJBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Chargement du détail du panier N°"+detailPanier.getId()
	    		)); 
    } 
    
    public void onRowSelectDetailPanierB2B(SelectEvent event) {  
    	detailPanier = selectedDetailPanierB2B;
    	
    	if(!detailPanier.getLignes().isEmpty()) {
    		lignePanierEnCours = selectedDetailPanierB2B.getLignes().get(0);
    		pieceEnCours = lignePanierEnCours.getIdPiece();
    	}
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Chargement du détail du panier N°"+detailPanier.getId()
	    		)); 
    } 
	
	public void chargerDetailPanier(ActionEvent event) throws FinderException {
		
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		detailPanier = panierService.findById(idPanier);
		//detailPanier = detailPanier;
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Chargement du détail du panier N°"+detailPanier.getId()
	    		)); 
	}
	
	public void ajouterClientPanier(ActionEvent event) throws Exception {
		
		initGarantieCareco(detailPanier);
		detailPanier.recalcul();
		detailPanier = panierService.enregistrerMerge(detailPanier);
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Ajouter client"
	    		)); 
	}
	
	public void modfierClientPanier(ActionEvent event) throws Exception {
		
		//listePanierActif.remove(detailPanier);
		
		initGarantieCareco(detailPanier);
		detailPanier.recalcul();
		detailPanier = panierService.enregistrerMerge(detailPanier);
		
		//listePanierActif.add(detailPanier);
		initAllPanier(); //refresh à améliorer
		
		
		clientModif = false;
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Modifier client"
	    		)); 
	}
	
	public void validerPanier(ActionEvent event) throws Exception {
		
	    /*
	     * VENDEUR
	     * * nouvelle commande => approuver ou annuler
	     * * envoyer => sortie stock
	     * 
	     * ACHETEUR
	     * * nouvelle commande => attente de validation
	     * * reçu => rentrée en stock
	     */
	    String etatParDefautVendeur = ITransactionAchatVenteServiceRemote.etatParDefautVendeur; //approuve, annule,envoye
	    String etatParDefautAcheteur = ITransactionAchatVenteServiceRemote.etatParDefautAcheteur; //
	    int nbPieceStockLocal = 0;
	    int nbPieceEnCommande = 0;
	    int nbAutrePanierModifie = 0;
	    List<Panier> listePanierContientPiece = null;
	    
	    
	    TransactionAchatVente tx = null;
	    Stock s = null;
	    boolean pieceDansStockLocal = false;
	    
	    for (LignePanier l : detailPanier.getLignes()) {
	    	//si pièce de la ligne pas dans mon stock, création d'une transaction achat/vente B2B
	    	tx = new TransactionAchatVente();
	    	pieceDansStockLocal = false;
	    	
	    	if(l.getIdPiece().getIdStock().getId().equals(user.getUserCompany().getId())) {
	    		pieceDansStockLocal  = true;
	    		nbPieceStockLocal++;
	    	} else {
	    		nbPieceEnCommande++;
	    	}
	    
	    	if(pieceDansStockLocal) {
	    		tx.setEtatAcheteur(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_NOUVEAU);
		    	tx.setEtatVendeur(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_NOUVEAU);
		    	//tx.setTermine(true);
		    	tx.setTermine(false);
	    	} else {
	    		tx.setEtatAcheteur(etatParDefautVendeur);
		    	tx.setEtatVendeur(etatParDefautAcheteur);
		    	tx.setTermine(false);
	    	}
	    	
	    	tx.setDerierChangementEtat(new Date());
	    	transactionAchatVenteService.enregistrerMerge(tx);
	    	
	    	l.setTransactionAchatVente(tx);
	    	s = stockService.findById(l.getIdPiece().getId());
	    	
	    	listePanierContientPiece = panierService.findPanierContientPiece(s);
	    	if(listePanierContientPiece!=null) {
	    		for (Panier panier : listePanierContientPiece) {
	    			if(!panier.getId().equals(detailPanier.getId())) { //être sur de ne pas supprimé la pièce du paniers courant, mais unqiement dans les autres paniers
	    				panier.supprimerLigne(s);
	    				panierService.enregistrerMerge(panier);
	    				System.out.println("Pièce n° "+s.getId()+" supprimé du panier N°"+panier.getId()+" à la validation du panier N°"+detailPanier.getId());
	    				nbAutrePanierModifie++;
	    			}
				}
	    	}
	    	
	    	if(pieceDansStockLocal) {
	    		s.setStatus(IStockServiceRemote.STATUS_PIECE_VENDUE_CLIENT);
	    	} else {
	    		s.setStatus(IStockServiceRemote.STATUS_PIECE_TRANSACTION);
	    	}
	    	
	    	stockService.enregistrerMerge(s);
	    	
		}
	    detailPanier = panierService.enregistrerMerge(detailPanier);
	    
	    //Suppression de cette ligne dans les autres panier de tous les utilisateurs
	    
	    //création d'une ligne de commande par pièce
	    //apparition des lignes dans "mes achats", attente de validation
	    
	    //apparition dans "mes ventes" sur le compte ou la pièce est commandé, attente de validation
	    
	    FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Le panier "+detailPanier.getId()+" a été validé. "
	    		+(nbPieceStockLocal!=0?nbPieceStockLocal+"/"+detailPanier.getLignes().size()+" pièces présente dans le stock local. ":" ")
	    		+(nbPieceEnCommande!=0?nbPieceEnCommande+"/"+detailPanier.getLignes().size()+" pièces mise en commande. ":" ")
	    		+(nbAutrePanierModifie!=0?nbAutrePanierModifie+" Pièces retirés dans "+nbAutrePanierModifie+" autres paniers. ":" ")
	    		)); 
	    
	    System.out.println("Le panier "+detailPanier.getId()+" a été validé. "
	    		+(nbPieceStockLocal!=0?nbPieceStockLocal+"/"+detailPanier.getLignes().size()+" pièces présente dans le stock local. ":" ")
	    		+(nbPieceEnCommande!=0?nbPieceEnCommande+"/"+detailPanier.getLignes().size()+" pièces mise en commande. ":" ")
	    		+(nbAutrePanierModifie!=0?nbAutrePanierModifie+" Pièces retirés dans "+nbAutrePanierModifie+" autres paniers. ":" ")
	    		);
	    
	    initAllPanier(true); //pour rafraichissment Ajax, refresh après la notification sinon le n° de panier en cours est faux
	}	

	public void genererDevisPanier(ActionEvent event) throws Exception {
		
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		if(!detailPanier.getDevis()) {
			detailPanier.setDevis(true);
			panierService.enregistrerMerge(detailPanier);
		}
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Devis"
	    		));  
	}
	
	public void initGarantieCareco(Panier p) {
		GarantieCareco pe = null;
		for (LignePanier l : p.getLignes()) {
			if( (l.getIdPiece()!=null && l.getIdPiece().getGarantie()!=null && l.getIdPiece().getGarantie()>=12)
					|| (l.getDureePeVendu()!=null)
					) {
				//il y a une garantie careco "d'origine" pour la pièce ou elle a été modifié dans le panier
				//if(l.getPEDesactivee()==null || !l.getPEDesactivee()) {
				
				if(l.getDureePeVendu()==null || (l.getDureePeVendu()!=null && !l.getDureePeVendu().equals(0))) {
					//la durée de la PE vendu n'a pas été mise à 0 dans le panier pour cette ligne
					pe = panierService.findGarantieCarecoByMontant(l.caculTTCPourPE(), null);
					l.setIdContratPE(pe);
				} else {
					l.setIdContratPE(null);
				}
			}
		}
		p.recalcul();
	}
	
	public void enregistreModificationDetailPanier() throws Exception {
		//Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
    	
    	panierService.enregistrerMerge(detailPanier);
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Le panier a été modifié."
	    		)); 
	}
	
	public Panier nouveauPanierVide() throws Exception {
		detailPanier = nouveauPanier();
		detailPanier = panierService.enregistrerMerge(detailPanier);
		int id = detailPanier.getId();
		
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Nouveau panier vide "+detailPanier.getId()
	    		)); 
	    
	    initAllPanier();
	    
	    selectedPanierActifAccueil = cherchePanier(null, id);
	    
		return detailPanier;
	}
	
	public Panier nouveauPanier() {
		return nouveauPanier(null);
	}
	
	public Panier nouveauPanier(String immatRecherche) {
		BigDecimal txTVADefaut = new BigDecimal("20.0");
		String typeClientDefaut = "btoc";
		String nomClientDefaut = "Comptoir";
		String prenomClientDefaut = "Client";
		
		detailPanier = new Panier();
		Date d = new Date();
		cal.setTime(d);
		
		detailPanier.setDatePanier(d);
		cal.add(Calendar.HOUR_OF_DAY, 28);
		detailPanier.setDateFin(cal.getTime());
		
		detailPanier.setVendeur(user);
		detailPanier.setIdEntreprise(user.getUserCompany());
		
		Client c = new Client();
		//c.setImmatriculation(getRechercheCarteGriseBean().getImmatriculation());
		c.setImmatriculation(immatRecherche);
		c.setTva(txTVADefaut);
		c.setType(typeClientDefaut);
		c.setNom(nomClientDefaut);
		c.setPrenom(prenomClientDefaut);
		detailPanier.setClient(c);
		detailPanier.setTva(c.getTva());
		
	    return detailPanier;
	}
	
    public void onRowSelectPanierActifAccueilB2C(SelectEvent event) {  
    	
    	//pieceEnCours = selectedPanierActifAccueilB2C;
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Sélection du panier "+selectedPanierActifAccueil.getId()
	    		)); 
	   System.out.println("Sélection du panier "+selectedPanierActifAccueil.getId());
    } 
    
    public void onRowSelectPanierActifAccueilB2B(SelectEvent event) {  
    	
    	//pieceEnCours = selectedPanierActifAccueilB2C;
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Sélection du panier "+selectedPanierActifAccueil.getId()
	    		)); 
	    System.out.println("Sélection du panier "+selectedPanierActifAccueil.getId());
    } 
	
    public void onRowSelectDetailPiecePanier(SelectEvent event) {  
    	
    	pieceEnCours = lignePanierEnCours.getIdPiece();
    	
    	rv.setListeFicheCareco(null);
    	if(pieceEnCours.getImmatriculation()!=null) {
    		try {
				rv.rechercheImmatOuVIN(pieceEnCours.getImmatriculation(), null);
			} catch (EJBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Affichage du détail de la pièce sélectionnée "+pieceEnCours.getId()
	    		)); 
    } 
	
	public void selectionDetailPiecePanier(ActionEvent event) throws FinderException {
		
		Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
		
		pieceEnCours = stockService.findById(idPiece);
		
		rv.setListeFicheCareco(null);
		if(pieceEnCours.getImmatriculation()!=null) {
    		try {
				rv.rechercheImmatOuVIN(pieceEnCours.getImmatriculation(), null);
			} catch (EJBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Affichage du détail de la pièce sélectionnée "+pieceEnCours.getId()
	    		));  
	}
	
	public void supprimerPiecePanier(ActionEvent event) throws Exception {
		//Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
		
		//Panier p = panierService.findById(idPanier);
		Stock s = stockService.findById(idPiece);
		
		//la pièce redevient disponible
		s.setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
		
		//p.supprimerLigne(s);
		detailPanier.supprimerLigne(s);
		
		s = stockService.enregistrerMerge(s);
		
		if(detailPanier.getLignes()==null || detailPanier.getLignes().isEmpty()) {
			//panierService.remove(detailPanier);
		}
		
		panierService.enregistrerMerge(detailPanier);
	
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Pièce supprimer de ce panier"
	    		));  
	}
	
//	private void log(String method) {
//		System.out.println("*************************************************************************");
//		System.out.println(method+" => lignePanierEnCours : "+lignePanierEnCours+" *** "+lignePanierEnCours.getId()+" *** "+lignePanierEnCours.hashCode());
//		System.out.println("Liv : "+lignePanierEnCours.getSupplementLivraison());
//		System.out.println("Prix : "+lignePanierEnCours.getPrixVenteTTCFinal());
//		System.out.println("PE : "+lignePanierEnCours.getDureePeVendu());
//		System.out.println("*************************************************************************");
//	}
	
	public void majPrixPanier(ActionEvent event) throws FinderException {
		
		//on se positione sur la ligne a modifier
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		//lignePanierEnCours = lignePanierService.findById(idLignePanier);
		
//		lignePanierEnCours = detailPanier.findLigne(idLignePanier);
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Mise à jour du prix de la ligne"
	    		));  
	}
	
	public void reinitialisePrix(ActionEvent event) throws Exception {
		
		//on se positione sur la ligne a modifier
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		//lignePanierEnCours = lignePanierService.findById(idLignePanier);
		
		//lignePanierEnCours = detailPanier.findLigne(idLignePanier);
		lignePanierEnCours.setDureePeVendu(null);
		lignePanierEnCours.initPrix();
		
		//lignePanierEnCours.recalculHTFromTTC();
		initGarantieCareco(detailPanier);
		detailPanier.recalcul();
		
		panierService.enregistrerMerge(detailPanier);
		
		initAllPanier();
		
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Prix par défaut"
	    		));  
	}
	
	public void commentaireLignePanier(ActionEvent event) throws Exception {
		
		//on se positione sur la ligne a modifier
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		//lignePanierEnCours = lignePanierService.findById(idLignePanier);
		
		lignePanierEnCours = detailPanier.findLigne(idLignePanier);

	}
	
	public void desactiverPEsurLigne(ActionEvent event) throws Exception {
		
		//on se positione sur la ligne a modifier
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		//lignePanierEnCours = lignePanierService.findById(idLignePanier);
		
		lignePanierEnCours = detailPanier.findLigne(idLignePanier);
		
		if(lignePanierEnCours.getDureePeVendu()==null) {
			if(lignePanierEnCours.getIdPiece().getGarantie()>=12) {
				lignePanierEnCours.setDureePeVendu(lignePanierEnCours.getIdPiece().getGarantie());
			} else {
				lignePanierEnCours.setDureePeVendu(0);
			}
		}
		
		if(lignePanierEnCours.getTypeVente()==null) {
			lignePanierEnCours.setTypeVente(LignePanier.TYPE_VENTE_MONTAGE_GARAGE);
		}

	}
	
	public void validerCommentaireLignePanier(ActionEvent event) throws Exception {
//		lignePanierEnCours.recalculHTFromTTC();
//		initGarantieCareco(detailPanier);
//		detailPanier.recalcul();
		
		//detailPanier.getLignes().remove(lignePanierEnCours);
		panierService.enregistrerMerge(detailPanier);
				
		initAllPanier();

		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Commentaire modifié pour cette ligne"
	    		)); 
	}
	
	public void validerModifPELignePanier(ActionEvent event) throws Exception {
		//lignePanierEnCours.recalculHTFromTTC();
		initGarantieCareco(detailPanier);
		detailPanier.recalcul();
		
		//detailPanier.getLignes().remove(lignePanierEnCours);
		panierService.enregistrerMerge(detailPanier);
				
		initAllPanier();
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Calcul PE modifié pour cette ligne"
	    		)); 
	}
	
	public void validerMajPrixPanier(ActionEvent event) throws Exception {
		
		lignePanierEnCours.recalculHTFromTTC();
		if(lignePanierEnCours.getPrixVenteHTFinal().compareTo(lignePanierEnCours.getIdPiece().getPrixMinimum())<0) {
			lignePanierEnCours.initPrix();
		}
		initGarantieCareco(detailPanier);
		detailPanier.recalcul();
		
		
		panierService.enregistrerMerge(detailPanier);
		initAllPanier();
		
		//detailPanier.getLignes().remove(lignePanierEnCours);
				
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Prix ok"
	    		));  
	}
	
	public void majLivraisonPanier(ActionEvent event) throws FinderException {
		
		//on se positione sur la ligne a modifier
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
//		lignePanierEnCours = lignePanierService.findById(idLignePanier);
		
//		lignePanierEnCours = detailPanier.findLigne(idLignePanier);
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Mise à jour du prix de livraison de la ligne"
	    		));  
	}
	
	public void validerMajLivraisonPanier(ActionEvent event) throws Exception {
		
		//lignePanierEnCours.recalculHTFromTTC();
		//initGarantieCareco(detailPanier);
		detailPanier.recalcul();
		
		//detailPanier.getLignes().remove(lignePanierEnCours);
		panierService.enregistrerMerge(detailPanier);
		
		initAllPanier();
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Prix de livraison ok"
	    		));  
	}
	
	public void definirCommeActifPanier(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		detailPanier = panierService.findById(idPanier);
		
		if(detailPanier.panierPerime()) {
			Date d = new Date();
			detailPanier.setDatePanier(d);
			cal.add(Calendar.HOUR_OF_DAY, 28);
			detailPanier.setDateFin(cal.getTime());
			
			panierService.enregistrerMerge(detailPanier);
			
			initAllPanier();
			
			FacesContext context = FacesContext.getCurrentInstance();  
		    context.addMessage(null, new FacesMessage("Panier", 
		    		"Panier définit comme actif"
		    		)); 
		}
	}
	
    public void onRowSelectDetailPanierBas(SelectEvent event) {  
    	//detailPanierBas
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Chargement du détail du panier N°"+detailPanierBas.getId()
	    		)); 
    } 
	
	public void chargeDetailBasPanier(ActionEvent event) throws FinderException {
		
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		detailPanierBas = panierService.findById(idPanier);
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Panier", 
	    		"Chargement du détail du panier N°"+detailPanierBas.getId()
	    		));  
	}
	
	protected void refreshPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String refreshpage = fc.getViewRoot().getViewId();
		ViewHandler ViewH =fc.getApplication().getViewHandler();
		UIViewRoot UIV = ViewH.createView(fc,refreshpage);
		UIV.setViewId(refreshpage);
		fc.setViewRoot(UIV);
	}

//	public Panier getPanierEnCours() {
//		return detailPanier;
//	}

	public Panier getDetailPanier() {
		return detailPanier;
	}

	public List<Panier> getListePanierActif() {
		return listePanierActif;
	}

	public List<Panier> getListePanierPerime() {
		return listePanierPerime;
	}

	public List<Panier> getListePanierEnCours() {
		return listePanierEnCours;
	}

	public List<Panier> getListePanierValider() {
		return listePanierValider;
	}

	public List<Panier> getListePanierHistorique() {
		return listePanierHistorique;
	}

	public Panier getDetailPanierBas() {
		return detailPanierBas;
	}

	public Stock getPieceEnCours() {
		return pieceEnCours;
	}

	public RechercheVehiculeSession getRv() {
		return rv;
	}

	public void setRv(RechercheVehiculeSession rv) {
		this.rv = rv;
	}

	public Boolean getClientModif() {
		return clientModif;
	}

	public void setClientModif(Boolean clientModif) {
		this.clientModif = clientModif;
	}

	public List<Panier> getListePanierActifB2B() {
		return listePanierActifB2B;
	}

	public List<Panier> getListePanierPerimeB2B() {
		return listePanierPerimeB2B;
	}

	public List<Panier> getListePanierEnCoursB2B() {
		return listePanierEnCoursB2B;
	}

	public List<Panier> getListePanierValiderB2B() {
		return listePanierValiderB2B;
	}

	public List<Panier> getListePanierHistoriqueB2B() {
		return listePanierHistoriqueB2B;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public LignePanier getLignePanierEnCours() {
		return lignePanierEnCours;
	}

	public void setLignePanierEnCours(LignePanier lignePanierEnCours) {
		this.lignePanierEnCours = lignePanierEnCours;
	}

	public void setPanierEnCours(Panier detailPanier) {
		this.detailPanier = detailPanier;
	}

	public void setDetailPanierBas(Panier detailPanierBas) {
		this.detailPanierBas = detailPanierBas;
	}

	public void setPieceEnCours(Stock pieceEnCours) {
		this.pieceEnCours = pieceEnCours;
	}

	public User getUser() {
		return user;
	}

	public void setDetailPanier(Panier detailPanier) {
		this.detailPanier = detailPanier;
	}

	public Panier getSelectedDetailPanierB2C() {
		return selectedDetailPanierB2C;
	}

	public Panier getSelectedDetailPanierB2B() {
		return selectedDetailPanierB2B;
	}

	public void setSelectedDetailPanierB2C(Panier selectedDetailPanierB2C) {
		this.selectedDetailPanierB2C = selectedDetailPanierB2C;
	}

	public void setSelectedDetailPanierB2B(Panier selectedDetailPanierB2B) {
		this.selectedDetailPanierB2B = selectedDetailPanierB2B;
	}

	public RechercheVehiculeSession getRvClient() {
		return rvClient;
	}

	public void setRvClient(RechercheVehiculeSession rvClient) {
		this.rvClient = rvClient;
	}

	public List<Panier> getListePanierActifOuPerime() {
		return listePanierActifOuPerime;
	}

	public List<Panier> getListePanierActifOuPerimeB2B() {
		return listePanierActifOuPerimeB2B;
	}

	public Panier getSelectedPanierActifAccueilB2C() {
		return selectedPanierActifAccueilB2C;
	}

	public Panier getSelectedPanierActifAccueilB2B() {
		return selectedPanierActifAccueilB2B;
	}

	public void setSelectedPanierActifAccueilB2C(
			Panier selectedPanierActifAccueilB2C) {
		this.selectedPanierActifAccueilB2C = selectedPanierActifAccueilB2C;
	}

	public void setSelectedPanierActifAccueilB2B(
			Panier selectedPanierActifAccueilB2B) {
		this.selectedPanierActifAccueilB2B = selectedPanierActifAccueilB2B;
	}

	public Panier getSelectedPanierActifAccueil() {
		return selectedPanierActifAccueil;
	}

	public void setSelectedPanierActifAccueil(Panier selectedPanierActifAccueil) {
		this.selectedPanierActifAccueil = selectedPanierActifAccueil;
	}

}  
              