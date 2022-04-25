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
public class DashboardBean implements Serializable {  
    
    private DashboardModel model;  
      
    public DashboardBean() {  
        model = new DefaultDashboardModel();  
        DashboardColumn column1 = new DefaultDashboardColumn();  
        DashboardColumn column2 = new DefaultDashboardColumn();  
          
        column1.addWidget("id_recherche");
        column1.addWidget("id_mon_compte");
        column1.addWidget("id_detail_panier_actif");
        
        column2.addWidget("id_complements");  
        column2.addWidget("id_boite_mail");  
        column2.addWidget("id_carnet_adresse"); 
        column2.addWidget("id_mes_achats"); 
        column2.addWidget("id_mes_ventes");
           
        model.addColumn(column1);  
        model.addColumn(column2);   
    }  
      
    public void handleReorder(DashboardReorderEvent event) {  
//        FacesMessage message = new FacesMessage();  
//        message.setSeverity(FacesMessage.SEVERITY_INFO);  
//        message.setSummary("Reordered: " + event.getWidgetId());  
//        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());  
//          
//        addMessage(message);  
    }  
      
      
    private void addMessage(FacesMessage message) {  
//        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
      
    public DashboardModel getModel() {  
        return model;  
    }  
}  
              