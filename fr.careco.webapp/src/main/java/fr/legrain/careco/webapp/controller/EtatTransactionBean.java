package fr.legrain.careco.webapp.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

@ManagedBean
@ViewScoped 
public class EtatTransactionBean implements Serializable {  
	
	public static final String ETAT_ACHETEUR_NOUVEAU = "nouveau";
	public static final String ETAT_ACHETEUR_NOUVEAU_LIBELLE = "nouveau";
	
	private Map<String, String> s;
	
	
}  
              