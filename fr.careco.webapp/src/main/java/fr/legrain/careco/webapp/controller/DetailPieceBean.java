package fr.legrain.careco.webapp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.tiers.model.Role;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.service.DevService;

@ManagedBean
@ViewScoped 
public class DetailPieceBean implements Serializable {  
	
	private User user;
	

	@EJB DevService devService;
	
	@EJB
	private IStockServiceRemote stockService;
	
	private Integer parameter;
	
	private Stock piece;
	
	@ManagedProperty(value="#{rechercheVehicule}")
	private RechercheVehiculeView rv;
	
	private boolean modeModification = false;
	
	public void refresh() {
		try {
			if (!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) { 
				//http://stackoverflow.com/questions/14687910/how-to-avoid-prerenderview-method-call-in-ajax-request
				recherchePiece(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void init() {
		user = Auth.findUserInSession();
		//refresh();
		
	}
	
	public boolean autoriseModifPiece() {
		if(user.hasRole(Role.ROLE_ADMIN)) {
			//l'admin peut tout modifier
			return true;
		}else if(user.hasRole(Role.ROLE_MANAGER)
				|| user.hasRole(Role.ROLE_STOCKEUR)) {
			if(piece.getStatus().equals(IStockServiceRemote.STATUS_PIECE_TRANSACTION)
					|| piece.getStatus().equals(IStockServiceRemote.STATUS_PIECE_VENDUE_CLIENT)
					|| piece.getStatus().equals(IStockServiceRemote.STATUS_PIECE_SUPPRIMEE)
					) {
				return false;
			}
			//les stockeurs et les manageurs peuvent modifier les pièces de leur entreprise
			if(piece.getIdStock().getId().equals(user.getUserCompany().getId())) {
				return true;
			}
		}
		return false;
	}
	
	public void recherchePiece(ActionEvent event) throws Exception {
		//Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
    	
    	piece = stockService.findById(parameter);
    	if(piece!=null && piece.getImmatriculation()!=null && !piece.getImmatriculation().equals("")) {
    		rv.rechercheImmatOuVIN(piece.getImmatriculation(), null);
    	}

	}
	
	public void passeEnModeModification(ActionEvent event) throws Exception {
		//Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
    	
    	//piece = stockService.findById(parameter);
    	//rv.rechercheImmatOuVIN(piece.getImmatriculation(), null);
		modeModification = true;
	}
	
	public void enregistreModification() throws Exception {
		//Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
    	
    	piece = stockService.enregistrerMerge(piece);
		
		modeModification = false;
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Détail pièce", 
	    		"La pièce à bien été modifiée"
	    		)); 
	}
	
	public void annulerModification(ActionEvent event) throws Exception {
		//Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
    	
    	piece = stockService.findById(parameter);
    	//rv.rechercheImmatOuVIN(piece.getImmatriculation(), null);
		modeModification = false;
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Détail pièce", 
	    		"Modification annulée"
	    		)); 
	}

	public Integer getParameter() {
		return parameter;
	}

	public void setParameter(Integer valeur1) {
		this.parameter = valeur1;
	}

	public Stock getPiece() {
		return piece;
	}

	public void setPiece(Stock piece) {
		this.piece = piece;
	}

	public RechercheVehiculeView getRv() {
		return rv;
	}

	public void setRv(RechercheVehiculeView rv) {
		this.rv = rv;
	}

	public User getUser() {
		return user;
	}

	public boolean isModeModification() {
		return modeModification;
	}

	public void setModeModification(boolean modeModification) {
		this.modeModification = modeModification;
	}


	
}  
              