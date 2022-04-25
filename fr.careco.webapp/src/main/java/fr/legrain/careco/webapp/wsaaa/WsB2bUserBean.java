package fr.legrain.careco.webapp.wsaaa;

//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import fr.careco.blueway.ws.model.AutorisationB2B;
import fr.careco.blueway.ws.model.IAutorisationServiceRemote;

@ManagedBean
@ViewScoped  
public class WsB2bUserBean {  

	private List<AutorisationB2B> values; 
	
	private List<AutorisationB2B> valuesFiltered; 
	

	private @EJB IAutorisationServiceRemote userService;

		
	private AutorisationB2B[] selectedAutorisationB2Bs; 
    private AutorisationB2B newAutorisationB2B = new AutorisationB2B();
    private AutorisationB2B selectedAutorisationB2B = new AutorisationB2B();
    
	public WsB2bUserBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		values = getValues();
	}
	
	public List<AutorisationB2B> getValues(){  
		List<AutorisationB2B> l = null;

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
    public void doCreateAutorisationB2B() {
    	try {
    		
    		newAutorisationB2B.setActiver(1); //actif par defaut

    		newAutorisationB2B = userService.enregistrerMerge(newAutorisationB2B);
    		    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	public void resetPassword(ActionEvent event) throws Exception {
		
		Integer idAutorisationB2B = (Integer) event.getComponent().getAttributes().get("idAutorisationB2B");
		AutorisationB2B u = userService.findById(idAutorisationB2B);
		
		//u.setPasswd(u.passwordHashSHA256_Base64("careco"));

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
    public void doUpdateAutorisationB2B(ActionEvent actionEvent){
    	
    	//selectedAutorisationB2B.setPasswd(selectedAutorisationB2B.passwordHashSHA256_Base64(selectedAutorisationB2B.getPasswd()));
    	
    	userService.merge(selectedAutorisationB2B);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteAutorisationB2Bs(ActionEvent actionEvent){
    	for (AutorisationB2B c : selectedAutorisationB2Bs) {
    		try {
				userService.remove(userService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public AutorisationB2B[] getSelectedAutorisationB2Bs() {
		return selectedAutorisationB2Bs;
	}

	public void setSelectedAutorisationB2Bs(AutorisationB2B[] selectedAutorisationB2Bs) {
		this.selectedAutorisationB2Bs = selectedAutorisationB2Bs;
	}

	public AutorisationB2B getNewAutorisationB2B() {
		return newAutorisationB2B;
	}

	public void setNewAutorisationB2B(AutorisationB2B newAutorisationB2B) {
		this.newAutorisationB2B = newAutorisationB2B;
	}

	public AutorisationB2B getSelectedAutorisationB2B() {
		return selectedAutorisationB2B;
	}

	public void setSelectedAutorisationB2B(AutorisationB2B selectedAutorisationB2B) {
		this.selectedAutorisationB2B = selectedAutorisationB2B;
	}

	public void setValues(List<AutorisationB2B> values) {
		this.values = values;
	}

	public List<AutorisationB2B> getValuesFiltered() {
		return valuesFiltered;
	}

	public void setValuesFiltered(List<AutorisationB2B> valuesFiltered) {
		this.valuesFiltered = valuesFiltered;
	}

}  
