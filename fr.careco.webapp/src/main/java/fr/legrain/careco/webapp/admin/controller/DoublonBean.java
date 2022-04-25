package fr.legrain.careco.webapp.admin.controller;

//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;

@ManagedBean
@ViewScoped  
public class DoublonBean {  

	private List<Stock> values; 
	
	private @EJB IStockServiceRemote stockService;

		
	private Stock[] selectedStocks; 
    private Stock newStock = new Stock();
    private Stock selectedStock = new Stock();
    private List<Stock> listeStock = null;
        

	public DoublonBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		User u = Auth.findUserInSession();
		listeStock =  stockService.findDuplicate(u.getUserCompany().getId());
		//values = getValues();
	}
	
//	public List<Stock> getValues(){  
//		List<Stock> l = null;
//
//		try {
//			
//			l =  stockService.findDuplicate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return l;
//	}
	
//	/**
//     * Create, Update and Delete operations
//     */
//    public void doCreateStock() {
//    	try {
//    		
//    		newStock.setPasswd(newStock.passwordHashSHA256_Base64(newStock.getPasswd()));
//
//    		newStock = StockService.enregistrerMerge(newStock);
//    		
//    		if(roles!=null) {
//    			if(newStock.getRoles()==null) {
//    				newStock.setRoles(new ArrayList<StockRole>());
//    			}
//    			for (Role r : roles.getTarget()) {
//    				newStock.getRoles().add(new StockRole(newStock,r));
//    			}	
//    		}
//    		
//    		newStock = StockService.enregistrerMerge(newStock);
//    		
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    	}
//    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateStock(ActionEvent actionEvent){
    	    	
//    	StockService.merge(selectedStock);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteStocks(ActionEvent actionEvent){
    	for (Stock c : selectedStocks) {
    		try {
				stockService.remove(stockService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public Stock[] getSelectedStocks() {
		return selectedStocks;
	}

	public void setSelectedStocks(Stock[] selectedStocks) {
		this.selectedStocks = selectedStocks;
	}

	public Stock getNewStock() {
		return newStock;
	}

	public void setNewStock(Stock newStock) {
		this.newStock = newStock;
	}

	public Stock getSelectedStock() {
		return selectedStock;
	}

	public void setSelectedStock(Stock selectedStock) {
		this.selectedStock = selectedStock;
	}

	public void setValues(List<Stock> values) {
		this.values = values;
	}

	public List<Stock> getListeStock() {
		return listeStock;
	}

	public void setListeStock(List<Stock> listeStock) {
		this.listeStock = listeStock;
	}

}  
