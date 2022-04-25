package fr.legrain.careco.webapp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.tiers.service.remote.ILignePanierServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITransactionAchatVenteServiceRemote;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.service.DevService;

@ManagedBean
@ViewScoped 
public class UnStockBean implements Serializable {  
	
	private User user;
	

	@EJB DevService devService;
	
	@EJB
	private IStockServiceRemote stockService;
	
	private String parameter;
	
	private List<Stock> listeResultatCarecoNational;
	private List<Stock> listeResultatMonStock;
	private List<Stock> listeResultatMultiSiteStock;
	private List<Stock> listeResultatAmisStock;
	private Stock selectedElementResultatRechercheStockNationalCareco;
	
	
	
	public void refresh() {
		try {
			rechercheStock(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void init() {
		user = Auth.findUserInSession();
		//refresh();
		
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
	
	public void rechercheStock(ActionEvent event) throws Exception {
		//Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");

		
		//Mon stock
		//List<Stock> listeResultatMonStock;
		listeResultatMonStock = stockService.findByUserEtPieceStock(user.getUserCompany().getId(),parameter, null);

		//Multi Site
		listeResultatMultiSiteStock = new ArrayList<Stock>();
		if(user.getUserCompany().getIdAdherent()!=null) {
			List<Integer> idMultiSite = user.getUserCompany().getIdAdherent().findIdMultiSite(user.getUserCompany().getId());
			if(idMultiSite!=null) {
				listeResultatMultiSiteStock = stockService.findByMultiCompanyEtPieceStock(idMultiSite,parameter, null);
			}
			initPrixMultiSite(listeResultatMultiSiteStock);
		}

		//Stock ami
		listeResultatAmisStock = new ArrayList<Stock>();
		if(user.getUserCompany().getIdAdherent()!=null) {
			List<Integer> idAmie = user.getUserCompany().findIdAmie(user.getUserCompany().getId());
			if(idAmie!=null && !idAmie.isEmpty()) {
				listeResultatAmisStock = stockService.findByMultiCompanyEtPieceStock(idAmie,parameter, null);
			}
			initPrixAmi(listeResultatAmisStock);
		}

		//Stock National
		//List<Stock> listeResultatCarecoNational;
		listeResultatCarecoNational = stockService.findByGroupeEntrepiseEtPieceStock(user.getUserCompany().getIdGroupeEntreprise().getId(),parameter, null);

		
//		FacesContext context = FacesContext.getCurrentInstance();  
//	    context.addMessage(null, new FacesMessage("Dev", 
//	    		"xxxxxxxxxx : "+VehiculeCacheAAA.immatPourWebService(parameter)
//	    		)); 
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String valeur1) {
		this.parameter = valeur1;
	}

	public List<Stock> getListeResultatCarecoNational() {
		return listeResultatCarecoNational;
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

	public Stock getSelectedElementResultatRechercheStockNationalCareco() {
		return selectedElementResultatRechercheStockNationalCareco;
	}

	public void setListeResultatCarecoNational(
			List<Stock> listeResultatCarecoNational) {
		this.listeResultatCarecoNational = listeResultatCarecoNational;
	}

	public void setListeResultatMonStock(List<Stock> listeResultatMonStock) {
		this.listeResultatMonStock = listeResultatMonStock;
	}

	public void setListeResultatMultiSiteStock(
			List<Stock> listeResultatMultiSiteStock) {
		this.listeResultatMultiSiteStock = listeResultatMultiSiteStock;
	}

	public void setListeResultatAmisStock(List<Stock> listeResultatAmisStock) {
		this.listeResultatAmisStock = listeResultatAmisStock;
	}

	public void setSelectedElementResultatRechercheStockNationalCareco(
			Stock selectedElementResultatRechercheStockNationalCareco) {
		this.selectedElementResultatRechercheStockNationalCareco = selectedElementResultatRechercheStockNationalCareco;
	}


	
}  
              