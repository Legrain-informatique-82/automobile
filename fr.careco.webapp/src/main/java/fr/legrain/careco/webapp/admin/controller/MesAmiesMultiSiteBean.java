package fr.legrain.careco.webapp.admin.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;

@ManagedBean
@ViewScoped  
public class MesAmiesMultiSiteBean {  
	
	private final String remiseDefaut = "30";

	private List<UserCompany> listeEntreprise; 
	
	private List<SocietesAmies> multiSite; 
	private List<SocietesAmies> amie;
	private List<SocietesAmies> amieAjoutee; 
	
	private @EJB IUserCompanyServiceRemote userCompanyService;

	private UserCompany[] selectedEntreprises; 
    private UserCompany newEntreprise = new UserCompany();
    private UserCompany selectedEntreprise = new UserCompany();
    
    private UserCompany monEntreprise = null;
    
    private String montantReduction;
    private SocietesAmies relationSocietesAmiesSelectionne;
     

	public MesAmiesMultiSiteBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		User u = Auth.findUserInSession();
		monEntreprise = u.getUserCompany();
		listeEntreprise = userCompanyService.selectAll();
		
		multiSite = userCompanyService.findSocieteMultiSite(monEntreprise.getId());
		amie = userCompanyService.findSocieteAmie(monEntreprise.getId());
		amieAjoutee = userCompanyService.findSocieteAmieAjoutee(monEntreprise.getId());
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
	
    public void initAjoutSocieteAmie(ActionEvent event) { 
    	
    	montantReduction = remiseDefaut;
    	
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Detail", 
	    		""+selectedEntreprise.getNom()
	    		)); 
    }
    
    public void initModif(ActionEvent event) throws FinderException { 
    	
    	Integer idRelationSocieteAmie = (Integer) event.getComponent().getAttributes().get("idRelationSocieteAmie");
    	
    	relationSocietesAmiesSelectionne = userCompanyService.findByIdSocieteAmie(idRelationSocieteAmie);
    	
    	montantReduction = relationSocietesAmiesSelectionne.getPourcentageReduction().toString();
    	
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Detail", 
	    		""+selectedEntreprise.getNom()
	    		)); 
    }
	
    public void ajouteSocieteAmie(ActionEvent event) throws FinderException {    
    	
    	SocietesAmies nouvelleSocieteAmies = new SocietesAmies();
    	//ajouter mon entreprise dans la liste des société amies de l'entreprise sélectionnée.
//    	nouvelleSocieteAmies.setSocieteA(selectedEntreprise);
//    	nouvelleSocieteAmies.setSocieteB(monEntreprise);
    	nouvelleSocieteAmies.setSocieteA(userCompanyService.findById(selectedEntreprise.getId()));
    	nouvelleSocieteAmies.setSocieteB(userCompanyService.findById(monEntreprise.getId()));
    	nouvelleSocieteAmies.setPourcentageReduction(new BigDecimal(montantReduction));
    	nouvelleSocieteAmies.setTypeRelation(IUserCompanyServiceRemote.TYPE_SOCIETE_AMIE_AMIE);
    	
    	userCompanyService.persistSocietesAmies(nouvelleSocieteAmies);
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Nouvelle société amie ajouter", 
	    		""+selectedEntreprise.getNom()
	    		)); 
    }
    
    public void supprimerSocieteAmie(ActionEvent event) throws FinderException, RemoveException { 
    	
    	Integer idRelationSocieteAmie = (Integer) event.getComponent().getAttributes().get("idRelationSocieteAmie");
    	relationSocietesAmiesSelectionne = userCompanyService.findByIdSocieteAmie(idRelationSocieteAmie);
    	userCompanyService.removeSocietesAmies(relationSocietesAmiesSelectionne);
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Detail", 
	    		""+selectedEntreprise.getNom()
	    		)); 
    }
    
    public void modifierSocieteAmie(ActionEvent event) throws FinderException { 
    	
    	//Integer idRelationSocieteAmie = (Integer) event.getComponent().getAttributes().get("idRelationSocieteAmie");
    	//relationSocietesAmiesSelectionne = userCompanyService.findByIdSocieteAmie(idRelationSocieteAmie);
    	relationSocietesAmiesSelectionne.setPourcentageReduction(new BigDecimal(montantReduction));
    	userCompanyService.mergeSocietesAmies(relationSocietesAmiesSelectionne);
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Detail", 
	    		""+selectedEntreprise.getNom()
	    		)); 
    }
    
    public void ajouteReciproqueSocieteAmie(ActionEvent event) { 
    	
    	Integer idRelationSocieteAmie = (Integer) event.getComponent().getAttributes().get("idRelationSocieteAmie");
    	
    	FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Detail", 
	    		""+selectedEntreprise.getNom()
	    		)); 
    }
    
    public boolean lienSocieteAmieExiste(UserCompany c) {
    	for (SocietesAmies u : multiSite) {
			if(c.getId().equals(u.getSocieteB().getId())) {
				return true;
			}
		}
    	return false;
    }
    
    public void refreshListeMultiSite(ActionEvent event) throws FinderException { 
    	
    	Integer idRelationSocieteAmie = (Integer) event.getComponent().getAttributes().get("idRelationSocieteAmie");
    	
    	for(UserCompany c : monEntreprise.getIdAdherent().getMultiSite()) {
    		if(!c.getId().equals(monEntreprise.getId())
    				&& !lienSocieteAmieExiste(c)) {
    			SocietesAmies nouvelleSocieteAmies = new SocietesAmies();
    			nouvelleSocieteAmies.setSocieteB(userCompanyService.findById(c.getId()));
    			nouvelleSocieteAmies.setSocieteA(userCompanyService.findById(monEntreprise.getId()));
    			nouvelleSocieteAmies.setPourcentageReduction(new BigDecimal(remiseDefaut));
    			nouvelleSocieteAmies.setTypeRelation(IUserCompanyServiceRemote.TYPE_SOCIETE_AMIE_MULTISITE);

    			userCompanyService.persistSocietesAmies(nouvelleSocieteAmies);
    		}
    	}
    	
    	multiSite = userCompanyService.findSocieteMultiSite(monEntreprise.getId());
    	
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

	public UserCompany getMonEntreprise() {
		return monEntreprise;
	}

	public void setMonEntreprise(UserCompany monEntreprise) {
		this.monEntreprise = monEntreprise;
	}

	public List<SocietesAmies> getAmieAjoutee() {
		return amieAjoutee;
	}

	public void setAmieAjoutee(List<SocietesAmies> amieAjoutee) {
		this.amieAjoutee = amieAjoutee;
	}

	public String getMontantReduction() {
		return montantReduction;
	}

	public void setMontantReduction(String montantReduction) {
		this.montantReduction = montantReduction;
	}

	public SocietesAmies getRelationSocietesAmiesSelectionne() {
		return relationSocietesAmiesSelectionne;
	}

	public void setRelationSocietesAmiesSelectionne(
			SocietesAmies relationSocietesAmiesSelectionne) {
		this.relationSocietesAmiesSelectionne = relationSocietesAmiesSelectionne;
	}

}  
