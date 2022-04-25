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

import fr.careco.blueway.ws.model.AutorisationB2C;
import fr.careco.blueway.ws.model.IAutorisationServiceRemote;

@ManagedBean
@ViewScoped  
public class WsB2cUserBean {  

	private List<AutorisationB2C> values; 
	
	private List<AutorisationB2C> valuesFiltered; 
	

	private @EJB IAutorisationServiceRemote userService;

		
	private AutorisationB2C[] selectedAutorisationB2Cs; 
    private AutorisationB2C newAutorisationB2C = new AutorisationB2C();
    private AutorisationB2C selectedAutorisationB2C = new AutorisationB2C();
    
	public WsB2cUserBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		values = getValues();
	}
	
	public List<AutorisationB2C> getValues(){  
		List<AutorisationB2C> l = null;

		try {
			
			l =  userService.selectAllB2C();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateAutorisationB2C() {
    	try {
    	
    		newAutorisationB2C.setActiver(1); //actif par defaut
    		
    		newAutorisationB2C = userService.enregistrerMerge(newAutorisationB2C);  		
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	
    /**
     *
     * @param actionEvent
     */
    public void doUpdateAutorisationB2C(ActionEvent actionEvent){
    	    	
    	userService.merge(selectedAutorisationB2C);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteAutorisationB2Cs(ActionEvent actionEvent){
    	for (AutorisationB2C c : selectedAutorisationB2Cs) {
    		try {
				userService.remove(userService.enregistrerMerge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }     
        

	public AutorisationB2C[] getSelectedAutorisationB2Cs() {
		return selectedAutorisationB2Cs;
	}

	public void setSelectedAutorisationB2Cs(AutorisationB2C[] selectedAutorisationB2Cs) {
		this.selectedAutorisationB2Cs = selectedAutorisationB2Cs;
	}

	public AutorisationB2C getNewAutorisationB2C() {
		return newAutorisationB2C;
	}

	public void setNewAutorisationB2C(AutorisationB2C newAutorisationB2C) {
		this.newAutorisationB2C = newAutorisationB2C;
	}

	public AutorisationB2C getSelectedAutorisationB2C() {
		return selectedAutorisationB2C;
	}

	public void setSelectedAutorisationB2C(AutorisationB2C selectedAutorisationB2C) {
		this.selectedAutorisationB2C = selectedAutorisationB2C;
	}

	public void setValues(List<AutorisationB2C> values) {
		this.values = values;
	}

	public List<AutorisationB2C> getValuesFiltered() {
		return valuesFiltered;
	}

	public void setValuesFiltered(List<AutorisationB2C> valuesFiltered) {
		this.valuesFiltered = valuesFiltered;
	}

}  
