package fr.legrain.careco.webapp.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import fr.legrain.bdg.tiers.service.remote.IAdherentCarecoServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IGroupeEntrepriseServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.tiers.model.Adherent;
import fr.legrain.tiers.model.GroupeEntreprise;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.UserCompany;

@ManagedBean
@ViewScoped  
public class EntrepriseBean {  

	private List<UserCompany> listeEntreprise; 
	private List<Adherent> listeAdherent;
	private List<GroupeEntreprise> listeGroupeEntreprise;
	
	private List<SocietesAmies> multiSite; 
	private List<SocietesAmies> amie; 
	
	private @EJB IUserCompanyServiceRemote userCompanyService;
	private @EJB IAdherentCarecoServiceRemote adherentCarecoService;
	private @EJB IGroupeEntrepriseServiceRemote groupeEntrepriseService;

	private UserCompany[] selectedEntreprises; 
    private UserCompany newEntreprise = new UserCompany();
    private UserCompany selectedEntreprise = new UserCompany();
     

	public EntrepriseBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		listeEntreprise = userCompanyService.selectAll();
		listeAdherent = adherentCarecoService.selectAll();
		listeGroupeEntreprise = groupeEntrepriseService.selectAll();
	}
	
	public List<UserCompany> getListeEntreprise(){  
		return listeEntreprise;
	}
	
	public void onRowSelectDetail(SelectEvent event) {  
		
		//multiSite = selectedEntreprise.findMultiSite(null);
		//amie = selectedEntreprise.findAmie(null);
		multiSite = userCompanyService.findSocieteMultiSite(selectedEntreprise.getId());
		amie = userCompanyService.findSocieteAmie(selectedEntreprise.getId());
		
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Detail", 
	    		""+selectedEntreprise.getNom()
	    		)); 
	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateEntreprise() {
    	try {

    		newEntreprise = userCompanyService.enregistrerMerge(newEntreprise);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateEntreprise(ActionEvent actionEvent){    	
    	userCompanyService.merge(selectedEntreprise);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteEntreprises(ActionEvent actionEvent){
    	for (UserCompany c : selectedEntreprises) {
    		try {
    			userCompanyService.remove(userCompanyService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public UserCompany[] getSelectedEntreprises() {
		return selectedEntreprises;
	}

	public void setSelectedEntreprises(UserCompany[] selectedUserCompanies) {
		this.selectedEntreprises = selectedUserCompanies;
	}

	public UserCompany getNewEntreprise() {
		return newEntreprise;
	}

	public void setNewEntreprise(UserCompany newUserCompany) {
		this.newEntreprise = newUserCompany;
	}

	public UserCompany getSelectedEntreprise() {
		return selectedEntreprise;
	}

	public void setSelectedEntreprise(UserCompany selectedUserCompany) {
		this.selectedEntreprise = selectedUserCompany;
	}

	public void setListeEntreprise(List<UserCompany> values) {
		this.listeEntreprise = values;
	}

	public List<SocietesAmies> getMultiSite() {
		return multiSite;
	}

	public IUserCompanyServiceRemote getUserCompanyService() {
		return userCompanyService;
	}

	public void setMultiSite(List<SocietesAmies> multiSite) {
		this.multiSite = multiSite;
	}

	public void setUserCompanyService(IUserCompanyServiceRemote userCompanyService) {
		this.userCompanyService = userCompanyService;
	}

	public List<SocietesAmies> getAmie() {
		return amie;
	}

	public void setAmie(List<SocietesAmies> amie) {
		this.amie = amie;
	}

	public List<Adherent> getListeAdherent() {
		return listeAdherent;
	}

	public List<GroupeEntreprise> getListeGroupeEntreprise() {
		return listeGroupeEntreprise;
	}

	public void setListeAdherent(List<Adherent> listeAdherent) {
		this.listeAdherent = listeAdherent;
	}

	public void setListeGroupeEntreprise(
			List<GroupeEntreprise> listeGroupeEntreprise) {
		this.listeGroupeEntreprise = listeGroupeEntreprise;
	}

}  
