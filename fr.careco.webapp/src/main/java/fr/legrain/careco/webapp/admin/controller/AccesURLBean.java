package fr.legrain.careco.webapp.admin.controller;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.tiers.service.remote.IAuthURLServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IRoleServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserServiceRemote;
import fr.legrain.tiers.model.AuthURL;

@ManagedBean
@ViewScoped  
public class AccesURLBean {  

	private List<AuthURL> values; 
	

	private @EJB IUserServiceRemote userService;
	private @EJB IUserCompanyServiceRemote userCompanyService;
	private @EJB IRoleServiceRemote roleService;
	private @EJB IAuthURLServiceRemote authURLService;

		
	private AuthURL[] selectedUrls; 
    private AuthURL newUrl = new AuthURL();
    private AuthURL selectedUrl = new AuthURL();

	public AccesURLBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		values = getValues();
	}
	
	public List<AuthURL> getValues(){  
		List<AuthURL> l = null;

		try {
			
			l =  authURLService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateUrl() {
    	try {
    		
    		newUrl = authURLService.enregistrerMerge(newUrl);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateUrl(ActionEvent actionEvent){
    	
    	authURLService.merge(selectedUrl);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteUrls(ActionEvent actionEvent){
    	for (AuthURL c : selectedUrls) {
    		try {
    			authURLService.remove(authURLService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public AuthURL[] getSelectedUrls() {
		return selectedUrls;
	}

	public void setSelectedUrls(AuthURL[] selectedUsers) {
		this.selectedUrls = selectedUsers;
	}

	public AuthURL getNewUrl() {
		return newUrl;
	}

	public void setNewUrl(AuthURL newUser) {
		this.newUrl = newUser;
	}

	public AuthURL getSelectedUrl() {
		return selectedUrl;
	}

	public void setSelectedUrl(AuthURL selectedUser) {
		this.selectedUrl = selectedUser;
	}

	public void setValues(List<AuthURL> values) {
		this.values = values;
	}

}  
