package fr.legrain.careco.webapp.admin.controller;

//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import fr.legrain.bdg.tiers.service.remote.IRoleServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserServiceRemote;
import fr.legrain.tiers.model.Role;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;
import fr.legrain.tiers.model.UserRole;

@ManagedBean
@ViewScoped  
public class UserController {  

	private List<User> values; 
	
	private List<User> valuesFiltered; 
	
	//private @EJB(name="UserBean") UserBean ws;
	//private @EJB(name="	java:app/fr.legrain.bdg.ejb/UserBean!fr.legrain.tiers.service.remote.UserBeanRemote") UserBean ws;
	//private @Inject UserBean ws;
	//private @EJB UserBean ws;
	private @EJB IUserServiceRemote userService;
	private @EJB IUserCompanyServiceRemote userCompanyService;
	private @EJB IRoleServiceRemote roleService;

		
	private User[] selectedUsers; 
    private User newUser = new User();
    private User selectedUser = new User();
    
    private DualListModel<Role> roles;
    

	public UserController() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		values = getValues();
	}
	
	public DualListModel<Role> getRoles(){  
		
		List<Role> roleSource = new ArrayList<Role>();  
        List<Role> roleTarget = new ArrayList<Role>();
        
        roleSource = roleService.selectAll();          
        roles = new DualListModel<Role>(roleSource, roleTarget);  
        
		return roles;
//		return null;
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
	
	public List<User> getValues(){  
		List<User> l = null;

		try {
			
			l =  userService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateUser() {
    	try {
    		
    		newUser.setPasswd(newUser.passwordHashSHA256_Base64(newUser.getPasswd()));

    		newUser = userService.enregistrerMerge(newUser);
    		
    		if(roles!=null) {
    			if(newUser.getRoles()==null) {
    				newUser.setRoles(new ArrayList<UserRole>());
    			}
    			for (Role r : roles.getTarget()) {
    				newUser.getRoles().add(new UserRole(newUser,r));
    			}	
    		}
    		
    		newUser = userService.enregistrerMerge(newUser);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	public void resetPassword(ActionEvent event) throws Exception {
		
		Integer idUser = (Integer) event.getComponent().getAttributes().get("idUser");
		User u = userService.findById(idUser);
		
		u.setPasswd(u.passwordHashSHA256_Base64("careco"));

		u = userService.enregistrerMerge(u);
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Utilisateur", 
	    		"Mot de passe réinistialisé"
	    		));  
	}
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateUser(ActionEvent actionEvent){
    	
    	selectedUser.setPasswd(selectedUser.passwordHashSHA256_Base64(selectedUser.getPasswd()));
    	
    	userService.merge(selectedUser);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteUsers(ActionEvent actionEvent){
    	for (User c : selectedUsers) {
    		try {
				userService.remove(userService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public User[] getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(User[] selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public void setValues(List<User> values) {
		this.values = values;
	}

	public void setRoles(DualListModel<Role> roles) {
		this.roles = roles;
	}

	public List<User> getValuesFiltered() {
		return valuesFiltered;
	}

	public void setValuesFiltered(List<User> valuesFiltered) {
		this.valuesFiltered = valuesFiltered;
	}

}  
