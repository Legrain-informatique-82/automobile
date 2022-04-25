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

import fr.careco.blueway.ws.model.ILogAppelServiceRemote;
import fr.careco.blueway.ws.model.IAutorisationServiceRemote;
import fr.careco.blueway.ws.model.LogAppelB2C;

@ManagedBean
@ViewScoped  
public class WsB2cLogBean {  

	private List<LogAppelB2C> values;
	
	private Date dateDebut; 
	private Date dateFin; 
	
	private List<LogAppelB2C> valuesFiltered; 
	

	private @EJB ILogAppelServiceRemote logService;

		
	private LogAppelB2C[] selectedLogAppelB2Cs; 
    private LogAppelB2C newLogAppelB2C = new LogAppelB2C();
    private LogAppelB2C selectedLogAppelB2C = new LogAppelB2C();
    
	public WsB2cLogBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		values = logService.selectAllB2C();
	}
	
	public void recherche(ActionEvent event) throws Exception {
		values = logService.findByDateB2C(dateDebut,dateFin);
	}
	
	public void rechercheInit(ActionEvent event) throws Exception {
		values = logService.selectAllB2C();
	}
	
	public List<LogAppelB2C> getValues(){  
//		List<LogAppelB2C> l = null;
//
//		try {
//			
//			l =  logService.selectAllB2C();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return l;
		return values;
	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateLogAppelB2C() {
//    	try {
//    		newLogAppelB2C = logService.enregistrerMerge(newLogAppelB2C);
//    		
//    		newLogAppelB2C = logService.enregistrerMerge(newLogAppelB2C);
//    		
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    	}
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateLogAppelB2C(ActionEvent actionEvent){
    	
//    	logService.merge(selectedLogAppelB2C);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteLogAppelB2Cs(ActionEvent actionEvent){
//    	for (LogAppelB2C c : selectedLogAppelB2Cs) {
//    		try {
//				logService.remove(logService.merge(c));
//			} catch (RemoveException e) {
//				e.printStackTrace();
//			}
//		}
    }     
        

	public LogAppelB2C[] getSelectedLogAppelB2Cs() {
		return selectedLogAppelB2Cs;
	}

	public void setSelectedLogAppelB2Cs(LogAppelB2C[] selectedLogAppelB2Cs) {
		this.selectedLogAppelB2Cs = selectedLogAppelB2Cs;
	}

	public LogAppelB2C getNewLogAppelB2C() {
		return newLogAppelB2C;
	}

	public void setNewLogAppelB2C(LogAppelB2C newLogAppelB2C) {
		this.newLogAppelB2C = newLogAppelB2C;
	}

	public LogAppelB2C getSelectedLogAppelB2C() {
		return selectedLogAppelB2C;
	}

	public void setSelectedLogAppelB2C(LogAppelB2C selectedLogAppelB2C) {
		this.selectedLogAppelB2C = selectedLogAppelB2C;
	}

	public void setValues(List<LogAppelB2C> values) {
		this.values = values;
	}

	public List<LogAppelB2C> getValuesFiltered() {
		return valuesFiltered;
	}

	public void setValuesFiltered(List<LogAppelB2C> valuesFiltered) {
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

}  
