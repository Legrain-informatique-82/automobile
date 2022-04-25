package fr.legrain.careco.webapp;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped 
public class ParamApplicationBean {  
    
    //mode recherche uniquement, désactive panier et transaction
	//ConstWeb.maintenance = true;
	
	//mode maintenance
	
	//
      
    public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
} 
