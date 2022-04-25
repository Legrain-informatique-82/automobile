package fr.legrain.careco.webapp.admin.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.tiers.service.remote.IGroupeEntrepriseServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.tiers.model.GroupeEntreprise;
import fr.legrain.tiers.model.UserCompany;

@ManagedBean
@ViewScoped  
public class GroupementBean {  

	private List<GroupeEntreprise> values; 
	
	private @EJB IUserCompanyServiceRemote userCompanyService;
	private @EJB IGroupeEntrepriseServiceRemote groupeEntrepriseService;

		
	private GroupeEntreprise[] selectedGroupeEntreprises; 
    private GroupeEntreprise newGroupeEntreprise = new GroupeEntreprise();
    private GroupeEntreprise selectedGroupeEntreprise = new GroupeEntreprise();
    
	public GroupementBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		values = getValues();
	}


	public List<UserCompany> getUserCompanyList(){  
		List<UserCompany> l = null;

		try {
			
			l =  userCompanyService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public List<GroupeEntreprise> getValues(){  
		List<GroupeEntreprise> l = null;

		try {
			
			l =  groupeEntrepriseService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateGroupeEntreprise() {
    	try {
    		
    		newGroupeEntreprise = groupeEntrepriseService.enregistrerMerge(newGroupeEntreprise);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateGroupeEntreprise(ActionEvent actionEvent){
    	
    	groupeEntrepriseService.merge(selectedGroupeEntreprise);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteGroupeEntreprises(ActionEvent actionEvent){
    	for (GroupeEntreprise c : selectedGroupeEntreprises) {
    		try {
    			groupeEntrepriseService.remove(groupeEntrepriseService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public GroupeEntreprise[] getSelectedGroupeEntreprises() {
		return selectedGroupeEntreprises;
	}

	public void setSelectedGroupeEntreprises(GroupeEntreprise[] selectedGroupeEntreprises) {
		this.selectedGroupeEntreprises = selectedGroupeEntreprises;
	}

	public GroupeEntreprise getNewGroupeEntreprise() {
		return newGroupeEntreprise;
	}

	public void setNewGroupeEntreprise(GroupeEntreprise newGroupeEntreprise) {
		this.newGroupeEntreprise = newGroupeEntreprise;
	}

	public GroupeEntreprise getSelectedGroupeEntreprise() {
		return selectedGroupeEntreprise;
	}

	public void setSelectedGroupeEntreprise(GroupeEntreprise selectedGroupeEntreprise) {
		this.selectedGroupeEntreprise = selectedGroupeEntreprise;
	}

	public void setValues(List<GroupeEntreprise> values) {
		this.values = values;
	}


}  
