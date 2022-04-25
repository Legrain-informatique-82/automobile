package fr.legrain.careco.webapp.wsaaa;

//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Date;
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
import fr.careco.blueway.ws.model.ILogAppelServiceRemote;
import fr.careco.blueway.ws.model.LogAppelB2B;
import fr.careco.blueway.ws.model.IAutorisationServiceRemote;
import fr.careco.blueway.ws.model.LogAppelB2B;
import fr.legrain.tiers.model.User;

@ManagedBean
@ViewScoped  
public class WsB2bLogBean {  

	private List<LogAppelB2B> values; 
	
	private Date dateDebut; 
	private Date dateFin;
	private AutorisationB2B u; 
	
	private List<LogAppelB2B> valuesFiltered; 
	

	private @EJB ILogAppelServiceRemote logService;
	private @EJB IAutorisationServiceRemote userService;

		
	private LogAppelB2B[] selectedLogAppelB2Bs; 
    private LogAppelB2B newLogAppelB2B = new LogAppelB2B();
    private LogAppelB2B selectedLogAppelB2B = new LogAppelB2B();
    
	public WsB2bLogBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		values = logService.selectAll();
	}
	
	public List<LogAppelB2B> getValues(){  
//		List<LogAppelB2B> l = null;
//
//		try {
//			
//			l =  logService.selectAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return l;
		return values;
	}
	
	public void recherche(ActionEvent event) throws Exception {
		if(u!=null && dateDebut!=null && dateFin!=null) {
			//dates et nom
			values = logService.findByLoginDateB2B(u.getLoginAuthen(),dateDebut,dateFin);
		} else if(u==null && dateDebut!=null && dateFin!=null) {
			//que les dates
			values = logService.findByDateB2B(dateDebut,dateFin);
		} else if(u!=null && dateDebut==null && dateFin==null) {
			//que le nom
			values = logService.findByLoginB2B(u.getLoginAuthen());
		} else if(u==null && dateDebut==null && dateFin==null) {
			//tout vide
			values = logService.selectAll();
		} else {
			values = logService.selectAll();
		}
	}
	
	public void rechercheInit(ActionEvent event) throws Exception {
		values = logService.selectAll();
		dateDebut=null;
		dateFin=null;
		u=null;
	}
	
	public List<AutorisationB2B> autoCompleteUser(String query) {		
		List<AutorisationB2B> l = userService.selectAll(query);

		return l;
	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateLogAppelB2B() {
    	try {
    		
    		//newLogAppelB2B.setPasswd(newLogAppelB2B.passwordHashSHA256_Base64(newLogAppelB2B.getPasswd()));

    		newLogAppelB2B = logService.enregistrerMerge(newLogAppelB2B);
   
    		values = logService.selectAll();
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	public void resetPassword(ActionEvent event) throws Exception {
		
		Integer idLogAppelB2B = (Integer) event.getComponent().getAttributes().get("idLogAppelB2B");
		LogAppelB2B u = logService.findById(idLogAppelB2B);
		
		//u.setPasswd(u.passwordHashSHA256_Base64("careco"));

		u = logService.enregistrerMerge(u);
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Utilisateur", 
	    		"Mot de passe réinistialisé"
	    		));  
	}
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateLogAppelB2B(ActionEvent actionEvent){
    	
    	//selectedLogAppelB2B.setPasswd(selectedLogAppelB2B.passwordHashSHA256_Base64(selectedLogAppelB2B.getPasswd()));
    	
    	logService.merge(selectedLogAppelB2B);
    	values = logService.selectAll();
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteLogAppelB2Bs(ActionEvent actionEvent){
    	for (LogAppelB2B c : selectedLogAppelB2Bs) {
    		try {
				logService.remove(logService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public LogAppelB2B[] getSelectedLogAppelB2Bs() {
		return selectedLogAppelB2Bs;
	}

	public void setSelectedLogAppelB2Bs(LogAppelB2B[] selectedLogAppelB2Bs) {
		this.selectedLogAppelB2Bs = selectedLogAppelB2Bs;
	}

	public LogAppelB2B getNewLogAppelB2B() {
		return newLogAppelB2B;
	}

	public void setNewLogAppelB2B(LogAppelB2B newLogAppelB2B) {
		this.newLogAppelB2B = newLogAppelB2B;
	}

	public LogAppelB2B getSelectedLogAppelB2B() {
		return selectedLogAppelB2B;
	}

	public void setSelectedLogAppelB2B(LogAppelB2B selectedLogAppelB2B) {
		this.selectedLogAppelB2B = selectedLogAppelB2B;
	}

	public void setValues(List<LogAppelB2B> values) {
		this.values = values;
	}

	public List<LogAppelB2B> getValuesFiltered() {
		return valuesFiltered;
	}

	public void setValuesFiltered(List<LogAppelB2B> valuesFiltered) {
		this.valuesFiltered = valuesFiltered;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public AutorisationB2B getU() {
		return u;
	}

	public void setU(AutorisationB2B u) {
		this.u = u;
	}

}  
