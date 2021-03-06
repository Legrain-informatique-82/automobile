package fr.legrain.careco.webapp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;

@ManagedBean
@ViewScoped 
public class MonStockBean implements Serializable {  
   
	private static final long serialVersionUID = 8548409772590184298L;
	
	private List<Stock> monStock;
	private Stock selectedStock;
	private Stock[] selectedStocks;
	
	private List<Stock> monStockSupprime;
	private List<String> listeStatusSupprimee = new ArrayList<String>();
	
	private int idTypeStockUser = 0;
	
	@EJB
    private IStockServiceRemote stockService;
	
	@PostConstruct
	public void init() {
		User u = Auth.findUserInSession();
		idTypeStockUser = u.getUserCompany().getId();
		monStock = stockService.findByUserStock(idTypeStockUser);
		
		listeStatusSupprimee.add(IStockServiceRemote.STATUS_PIECE_SUPPRIMEE);
		monStockSupprime = stockService.findByUserStock(idTypeStockUser,listeStatusSupprimee);
	}

	public List<Stock> getMonStock() {
		//monStock = stockService.findByUserStock(idTypeStockUser);
		
		return monStock;
	}
	
	public void supprimer(ActionEvent event) throws Exception {
		Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
		Stock s = null;
		try {
			
			s = stockService.findById(idPiece);
			//stockService.supprimer(s);
			
			s.setStatus(IStockServiceRemote.STATUS_PIECE_SUPPRIMEE);
			s = stockService.enregistrerMerge(s);

			//pour refresh ajax
			monStock = stockService.findByUserStock(idTypeStockUser);
			monStockSupprime = stockService.findByUserStock(idTypeStockUser,listeStatusSupprimee);

			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage("Mon stock", 
					"La pi??ce "+idPiece+" a bien ??t?? supprim??e."
					)); 
			
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("id_message_erreur_global", new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Erreur lors de la suppression", s!=null?"La pi??ce "+s.getId()+" ne peu pas ??tre supprim??e pour l'instant, cette pi??ce est utilis??e dans un panier ou une transaction.":""));
		}
	}
	
    public void supprimerSelectionStocks(ActionEvent actionEvent){
    	List<Stock> pieceErreurSuppr = new ArrayList<Stock>();
    	for (Stock s : selectedStocks) {
    		try {
    			s = stockService.findById(s.getId());
    			//monStock.remove(s);
    			
    			//stockService.supprimer(s);
    			
    			s.setStatus(IStockServiceRemote.STATUS_PIECE_SUPPRIMEE);
    			s = stockService.enregistrerMerge(s);
    			
			} catch (RemoveException e) {
				pieceErreurSuppr.add(s);
			} catch (Exception e) {
				pieceErreurSuppr.add(s);
			}
		}
    	//pour refresh ajax
    	monStock = stockService.findByUserStock(idTypeStockUser);
    	monStockSupprime = stockService.findByUserStock(idTypeStockUser,listeStatusSupprimee);
    	
    	if((selectedStocks.length-pieceErreurSuppr.size())>0) {
    		FacesContext context = FacesContext.getCurrentInstance();  
    		context.addMessage(null, new FacesMessage("Mon stock", 
    				"Les "+(selectedStocks.length-pieceErreurSuppr.size())+" pi??ces s??lectionn??es ont bien ??t?? supprim??es."
    				)); 
    	}

    	if(!pieceErreurSuppr.isEmpty()) {
    		for (Stock stock : pieceErreurSuppr) {
    			FacesContext.getCurrentInstance().addMessage("id_message_erreur_global", new FacesMessage(
    					FacesMessage.SEVERITY_ERROR, "Erreur lors de la suppression", stock!=null?"La pi??ce "+stock.getId()+" ne peu pas ??tre supprim?? pour l'instant, cette pi??ce est utilis??e dans un panier ou une transaction.":""));
    		}
    	}
    } 
    
	public void restaurerPiece(ActionEvent event) throws Exception {
		Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
		Stock s = null;
		try {
			
			s = stockService.findById(idPiece);
			//stockService.supprimer(s);
			
			s.setStatus(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
			s = stockService.enregistrerMerge(s);

			//pour refresh ajax
			monStock = stockService.findByUserStock(idTypeStockUser);
			monStockSupprime = stockService.findByUserStock(idTypeStockUser,listeStatusSupprimee);

			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage("Mon stock", 
					"La pi??ce "+idPiece+" a bien ??t?? restaur??e dans votre stock."
					)); 
			
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("id_message_erreur_global", new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Erreur lors de la restauration de la pi??ce", s!=null?"La pi??ce "+s.getId()+" ne peu pas ??tre restaur??e pour l'instant.":""));
		}
	}
    
    public void stockSelect(SelectEvent event) {  
//    	  ConfigurableNavigationHandler nh = (ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
//    	  nh.performNavigation("detail_piece_careco_template.xhtml?parameter="+((Stock)event.getObject()).getId()+"&faces-redirect=true");
    }

	public Stock getSelectedStock() {
		return selectedStock;
	}

	public void setSelectedStock(Stock selectedStock) {
		this.selectedStock = selectedStock;
	}

	public int getIdTypeStockUser() {
		return idTypeStockUser;
	}

	public Stock[] getSelectedStocks() {
		return selectedStocks;
	}

	public void setSelectedStocks(Stock[] selectedStocks) {
		this.selectedStocks = selectedStocks;
	}

	public List<Stock> getMonStockSupprime() {
		return monStockSupprime;
	}

	public void setMonStockSupprime(List<Stock> monStockSupprime) {
		this.monStockSupprime = monStockSupprime;
	}
   
}  
              