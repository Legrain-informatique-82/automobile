package fr.legrain.careco.webapp.admin.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.tiers.service.remote.IRoleServiceRemote;
import fr.legrain.tiers.model.Role;

@ManagedBean
@ViewScoped  
public class RoleBean {  

	private List<Role> values; 
	
	private @EJB IRoleServiceRemote roleService;

		
	private Role[] selectedRoles; 
    private Role newRole = new Role();
    private Role selectedRole = new Role();
   

	public RoleBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		values = getValues();
	}
	
	public List<Role> getValues(){  
		List<Role> l = null;

		try {
			
			l =  roleService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateRole() {
    	try {

    		newRole = roleService.enregistrerMerge(newRole);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateRole(ActionEvent actionEvent){
    	    	
    	roleService.merge(selectedRole);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteRoles(ActionEvent actionEvent){
    	for (Role c : selectedRoles) {
    		try {
    			roleService.remove(roleService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public Role[] getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(Role[] selectedUsers) {
		this.selectedRoles = selectedUsers;
	}

	public Role getNewRole() {
		return newRole;
	}

	public void setNewRole(Role newRole) {
		this.newRole = newRole;
	}

	public Role getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(Role selectedRole) {
		this.selectedRole = selectedRole;
	}

	public void setValues(List<Role> values) {
		this.values = values;
	}


}  
