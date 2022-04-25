package fr.legrain.careco.webapp.admin.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.tiers.service.remote.IAdherentCarecoServiceRemote;
import fr.legrain.tiers.model.Adherent;

@ManagedBean
@ViewScoped  
public class AdherentBean {  

	private List<Adherent> listeAdherent; 
	
	private @EJB IAdherentCarecoServiceRemote adherentCarecoService;

	private Adherent[] selectedAdherents; 
    private Adherent newAdherent = new Adherent();
    private Adherent selectedAdherent = new Adherent();
    
	public AdherentBean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		listeAdherent = adherentCarecoService.selectAll();
	}
	
	public List<Adherent> getListeAdherent(){  
		return listeAdherent;
	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateAdherent() {
    	try {
    		
    		newAdherent = adherentCarecoService.enregistrerMerge(newAdherent);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateAdherent(ActionEvent actionEvent){
    	
    	adherentCarecoService.merge(selectedAdherent);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteAdherents(ActionEvent actionEvent){
    	for (Adherent c : selectedAdherents) {
    		try {
    			adherentCarecoService.remove(adherentCarecoService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public Adherent[] getSelectedAdherents() {
		return selectedAdherents;
	}

	public void setSelectedAdherents(Adherent[] selectedAdherents) {
		this.selectedAdherents = selectedAdherents;
	}

	public Adherent getNewAdherent() {
		return newAdherent;
	}

	public void setNewAdherent(Adherent newAdherent) {
		this.newAdherent = newAdherent;
	}

	public Adherent getSelectedAdherent() {
		return selectedAdherent;
	}

	public void setSelectedAdherent(Adherent selectedAdherent) {
		this.selectedAdherent = selectedAdherent;
	}

	public void setListeAdherent(List<Adherent> values) {
		this.listeAdherent = values;
	}


}  
