package fr.legrain.careco.webapp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean
@ViewScoped 
public class DashboardMesVentesBean implements Serializable {  
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -456134677288069311L;
	private DashboardModel model;  
      
    public DashboardMesVentesBean() {  
        model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
          
        column1.addWidget("id_resultat3");
        column1.addWidget("id_resultat4"); 
           
        model.addColumn(column1);  
    }  
      
    public DashboardModel getModel() {  
        return model;  
    }  
}  
              