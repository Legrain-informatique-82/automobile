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
import fr.legrain.tiers.model.Panier;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;

@ManagedBean
@ViewScoped 
public class AchatBean implements Serializable {  
	
	private User user;
	
	private List<LignePanier> mesAchatsEncours;
	private List<LignePanier> mesAchatsHistorique;
	
	@EJB
	private ITransactionAchatVenteServiceRemote transactionAchatVenteService;
	
	@EJB
	private ILignePanierServiceRemote lignePanierService;
	
	@EJB
	private IStockServiceRemote stockService;
	
	public void refresh() {
		mesAchatsEncours = transactionAchatVenteService.findUserAchatEnCours(user);
		mesAchatsHistorique = transactionAchatVenteService.findUserAchatHistorique(user);
	}
	
	@PostConstruct
	public void init() {
		user = Auth.findUserInSession();
		refresh();
	}

	public List<LignePanier> getMesAchatsEncours() {
		return mesAchatsEncours;
	}

	public List<LignePanier> getMesAchatsHistorique() {
		return mesAchatsHistorique;
	}
	
	public Integer getNbAchatsEnCours() {
		if(mesAchatsEncours!=null && mesAchatsEncours.size()!=0) 
			return mesAchatsEncours.size();
		else
			return null;
	}
	
	public void confirmerArrive(ActionEvent event) throws Exception {
		Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		
		//changement de statut
		LignePanier lignePanier = lignePanierService.findById(idLignePanier);
		lignePanier.getTransactionAchatVente().setEtatAcheteur(ITransactionAchatVenteServiceRemote.ACHETEUR_ARRIVE_PIECE_CONFIRMEE);
		lignePanier.getTransactionAchatVente().setEtatVendeur(ITransactionAchatVenteServiceRemote.VENDEUR_ARRIVE_PIECE_CONFIRMEE_PAR_ACHETEUR);
		lignePanier.getTransactionAchatVente().setTermine(true);
		
		lignePanier = lignePanierService.enregistrerMerge(lignePanier);
		
		
		//mise en stock
		Stock piece = stockService.findById(lignePanier.getIdPiece().getId());
		//Stock uc = stockService.findWithJPQLQuery("select s from Stock where s.idStock="+user.getUserCompany().getId());
		piece = stockService.changeProprio(piece, user.getUserCompany());
		//piece.setIdStock(user.getUserCompany());
		
		//piece non disponible car vendu
		piece.setStatus(IStockServiceRemote.STATUS_PIECE_VENDUE_CLIENT);
		
		stockService.enregistrerMerge(piece);
		
		refresh();
		
		
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Achat", 
	    		"Réception de la pièce confirmer."
	    		));  
		
	}
	
	
}  
              