package fr.legrain.careco.webapp.admin.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.tiers.service.remote.IFicheCarecoServiceRemote;
import fr.legrain.tiers.model.FicheCareco;

@ManagedBean
@ViewScoped  
public class FicheInfosBean {  

	private List<FicheCareco> listeFicheInfos; 
	
	private @EJB IFicheCarecoServiceRemote ficheCarecoService;

	private FicheCareco[] selectedFicheInfoss; 
    private FicheCareco newFicheInfos = new FicheCareco();
    private FicheCareco selectedFicheInfos = new FicheCareco();
    
	public FicheInfosBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		listeFicheInfos = ficheCarecoService.selectAll();
	}
	
	public List<FicheCareco> getListeFicheInfos(){  
		return listeFicheInfos;
	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateFicheCareco() {
    	try {
    		
    		newFicheInfos = ficheCarecoService.enregistrerMerge(newFicheInfos);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateFicheCareco(ActionEvent actionEvent){
    	
    	ficheCarecoService.merge(selectedFicheInfos);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteFicheCarecos(ActionEvent actionEvent){
    	for (FicheCareco c : selectedFicheInfoss) {
    		try {
    			ficheCarecoService.remove(ficheCarecoService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public FicheCareco[] getSelectedFicheInfoss() {
		return selectedFicheInfoss;
	}

	public void setSelectedFicheInfoss(FicheCareco[] selectedFicheCarecos) {
		this.selectedFicheInfoss = selectedFicheCarecos;
	}

	public FicheCareco getNewFicheInfos() {
		return newFicheInfos;
	}

	public void setNewFicheInfos(FicheCareco newFicheCareco) {
		this.newFicheInfos = newFicheCareco;
	}

	public FicheCareco getSelectedFicheInfos() {
		return selectedFicheInfos;
	}

	public void setSelectedFicheInfos(FicheCareco selectedFicheCareco) {
		this.selectedFicheInfos = selectedFicheCareco;
	}

	public void setListeFicheInfos(List<FicheCareco> values) {
		this.listeFicheInfos = values;
	}


}  
