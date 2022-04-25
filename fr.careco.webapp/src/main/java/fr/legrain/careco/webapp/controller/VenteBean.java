package fr.legrain.careco.webapp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.tiers.service.remote.ILignePanierServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITransactionAchatVenteServiceRemote;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.tiers.model.LignePanier;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;

@ManagedBean
@ViewScoped 
public class VenteBean implements Serializable { 
	
	private User user;
   
	private List<LignePanier> mesVentesEncours;
	private List<LignePanier> mesVentesLocaleEncours;
	private List<LignePanier> mesVentesValider;
	private List<LignePanier> mesVentesHistorique;
	private List<LignePanier> mesVentesLocaleHistorique;
	
	private LignePanier selectedLignePanier;
	
	@EJB
	private ITransactionAchatVenteServiceRemote transactionAchatVenteService;
	
	@EJB
	private ILignePanierServiceRemote lignePanierService;
	
	@EJB
	private IStockServiceRemote stockService;
	
	public void refresh() {
		mesVentesEncours = transactionAchatVenteService.findUserVentesEnCours(user);
		mesVentesHistorique = transactionAchatVenteService.findUserVentesHistorique(user);
		mesVentesValider = transactionAchatVenteService.findUserVentesValider(user);
		
		mesVentesLocaleEncours = transactionAchatVenteService.findUserVentesLocaleEnCours(user);
		mesVentesLocaleHistorique  = transactionAchatVenteService.findUserVentesLocaleHistorique(user);
//		if(!mesVentesEncours.isEmpty()) {
//			selectedLignePanier = mesVentesEncours.get(0);
//		}
		selectedLignePanier = new LignePanier();
	}
	
	@PostConstruct
	public void init() {
		user = Auth.findUserInSession();
		refresh();
	}
	
	public List<LignePanier> getMesVentesEncours() {
		return mesVentesEncours;
	}
	public List<LignePanier> getMesVentesValider() {
		return mesVentesValider;
	}
	public List<LignePanier> getMesVentesHistorique() {
		return mesVentesHistorique;
	}
	
	public Integer getNbVentesEnCours() {
		if(mesVentesEncours!=null && mesVentesEncours.size()!=0) 
			return mesVentesEncours.size();
		else
			return null;
	}
   
	public void confirmerVente(ActionEvent event) throws Exception {
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		
		//changement de statut
		LignePanier lignePanier = lignePanierService.findById(idLignePanier);
		lignePanier.getTransactionAchatVente().setEtatVendeur(ITransactionAchatVenteServiceRemote.VENDEUR_VENTE_CONFIRMEE);
		lignePanier.getTransactionAchatVente().setEtatAcheteur(ITransactionAchatVenteServiceRemote.ACHETEUR_VENTE_CONFIRMEE_PAR_VENDEUR);
		
		lignePanierService.enregistrerMerge(lignePanier);

		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Vente", 
	    		"Confirmation de la vente de la pièce."
	    		));  
	    
	    refresh();
	}
	
	public void confirmerVenteLocale(ActionEvent event) throws Exception {
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		
		//changement de statut
		LignePanier lignePanier = lignePanierService.findById(idLignePanier);
		lignePanier.getTransactionAchatVente().setEtatVendeur(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
		lignePanier.getTransactionAchatVente().setEtatAcheteur(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
		lignePanier.getTransactionAchatVente().setTermine(true);
		
		lignePanierService.enregistrerMerge(lignePanier);

		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Vente locale", 
	    		"Confirmation de la vente de la pièce présente dans le stock local."
	    		));  
	    
	    refresh();
	}
	
	public void refuserVente(ActionEvent event) throws Exception {
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		
		//changement de statut
		LignePanier lignePanier = lignePanierService.findById(idLignePanier);
		lignePanier.getTransactionAchatVente().setEtatVendeur(ITransactionAchatVenteServiceRemote.VENDEUR_VENTE_REFUSEE);
		lignePanier.getTransactionAchatVente().setEtatAcheteur(ITransactionAchatVenteServiceRemote.ACHETEUR_VENTE_REFUSEE_PAR_VENDEUR);
		lignePanier.getTransactionAchatVente().setTermine(true);
		
		Stock s = stockService.findById(lignePanier.getIdPiece().getId());
    	s.setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
    	s = stockService.enregistrerMerge(s);
		//lignePanier.getIdPiece().setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
		
		lignePanierService.enregistrerMerge(lignePanier);
		
		refresh();

		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Vente", 
	    		"Refus de la vente de la pièce."
	    		));  
	}
	
	public void refuserVenteLocale(ActionEvent event) throws Exception {
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		
		//changement de statut
		LignePanier lignePanier = lignePanierService.findById(idLignePanier);
		lignePanier.getTransactionAchatVente().setEtatVendeur(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
		lignePanier.getTransactionAchatVente().setEtatAcheteur(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
		lignePanier.getTransactionAchatVente().setTermine(true);
		
		Stock s = stockService.findById(lignePanier.getIdPiece().getId());
    	s.setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
    	s = stockService.enregistrerMerge(s);
		//lignePanier.getIdPiece().setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
		
		lignePanierService.enregistrerMerge(lignePanier);
		
		refresh();

		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Vente locale", 
	    		"Refus de la vente de la pièce présente dans le stock local."
	    		));  
	}
	
	public void notifierExpedition(ActionEvent event) throws Exception {
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		
		//changement de statut
		LignePanier lignePanier = lignePanierService.findById(idLignePanier);
		lignePanier.getTransactionAchatVente().setEtatVendeur(ITransactionAchatVenteServiceRemote.VENDEUR_PIECE_ENVOYEE);
		lignePanier.getTransactionAchatVente().setEtatAcheteur(ITransactionAchatVenteServiceRemote.ACHETEUR_PIECE_ENVOYEE_PAR_VENDEUR);
		
		Stock s = stockService.findById(lignePanier.getIdPiece().getId());
    	s.setStatus(IStockServiceRemote.STATUS_PIECE_DESTOCKER);
    	s = stockService.enregistrerMerge(s);
		//lignePanier.getIdPiece().setStatus(IStockServiceRemote.STATUS_PIECE_DESTOCKER);
		
		lignePanierService.enregistrerMerge(lignePanier);
		
		//sortie du stock
//		Stock piece = stockService.findById(lignePanier.getIdPiece().getId());
//		piece.setIdStock(user.getUserCompany());
//		stockService.enregistrerMerge(piece);

		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Vente", 
	    		"Confirmation de l'expédition de la pièce."
	    		)); 
	    
	    refresh();
	}

	public List<LignePanier> getMesVentesLocaleEncours() {
		return mesVentesLocaleEncours;
	}

	public List<LignePanier> getMesVentesLocaleHistorique() {
		return mesVentesLocaleHistorique;
	}

	public LignePanier getSelectedLignePanier() {
		return selectedLignePanier;
	}

	public void setSelectedLignePanier(LignePanier selectedLignePanier) {
		this.selectedLignePanier = selectedLignePanier;
	}
	
	
}  
              