package fr.legrain.careco.webapp.admin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import fr.legrain.careco.aaa.model.IVehiculeCacheAAAServiceLocal;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.lib.data.LibConversion;

@ManagedBean
@ViewScoped  
public class CacheAAABean {  

//	private List<VehiculeCacheAAA> listeCacheAAA; 
	
	private LazyDataModel<VehiculeCacheAAA> listeCacheAAA;
	
	private @EJB IVehiculeCacheAAAServiceLocal VehiculeCacheAAAService;

	private VehiculeCacheAAA[] selectedVehiculeCacheAAAs; 
    private VehiculeCacheAAA newVehiculeCacheAAA = new VehiculeCacheAAA();
    private VehiculeCacheAAA selectedVehiculeCacheAAA = new VehiculeCacheAAA();
    
	public CacheAAABean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
//		listeCacheAAA = VehiculeCacheAAAService.selectAll();

		listeCacheAAA = new LazyDataModel<VehiculeCacheAAA>() {
		
			private static final long serialVersionUID = 6700000018358122757L;

			@Override
			public List<VehiculeCacheAAA> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
				return VehiculeCacheAAAService.selectAllLimit(first, pageSize);
			}
			
//		    public VehiculeCacheAAA getRowData(String rowKey) {
//		        for(VehiculeCacheAAA car : datasource) {
//		            if(car.getCode_Immat().equals(rowKey))
//		                return car;
//		        }
//		 
//		        return null;
//		    }
		 

		    public Object getRowKey(VehiculeCacheAAA car) {
		        return car.getCode_Immat();
		    }
		};
		
		int totalRowCount = LibConversion.stringToInteger(VehiculeCacheAAAService.count()+"");
		listeCacheAAA.setRowCount(totalRowCount);
	}
	
	
	public LazyDataModel<VehiculeCacheAAA> getListeCacheAAA(){  
		return listeCacheAAA;
	}
	
//	public List<VehiculeCacheAAA> getListeCacheAAA(){  
//		return listeCacheAAA;
//	}
	
	/**
     * Create, Update and Delete operations
     */
    public void doCreateVehiculeCacheAAA() {
    	try {
    		
    		newVehiculeCacheAAA = VehiculeCacheAAAService.enregistrerMerge(newVehiculeCacheAAA);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateVehiculeCacheAAA(ActionEvent actionEvent){
    	
    	VehiculeCacheAAAService.merge(selectedVehiculeCacheAAA);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteVehiculeCacheAAAs(ActionEvent actionEvent){
    	for (VehiculeCacheAAA c : selectedVehiculeCacheAAAs) {
    		try {
    			VehiculeCacheAAAService.remove(VehiculeCacheAAAService.merge(c));
			} catch (RemoveException e) {
				e.printStackTrace();
			}
		}
    }     
        

	public VehiculeCacheAAA[] getSelectedVehiculeCacheAAAs() {
		return selectedVehiculeCacheAAAs;
	}

	public void setSelectedVehiculeCacheAAAs(VehiculeCacheAAA[] selectedVehiculeCacheAAAs) {
		this.selectedVehiculeCacheAAAs = selectedVehiculeCacheAAAs;
	}

	public VehiculeCacheAAA getNewVehiculeCacheAAA() {
		return newVehiculeCacheAAA;
	}

	public void setNewVehiculeCacheAAA(VehiculeCacheAAA newVehiculeCacheAAA) {
		this.newVehiculeCacheAAA = newVehiculeCacheAAA;
	}

	public VehiculeCacheAAA getSelectedVehiculeCacheAAA() {
		return selectedVehiculeCacheAAA;
	}

	public void setSelectedVehiculeCacheAAA(VehiculeCacheAAA selectedVehiculeCacheAAA) {
		this.selectedVehiculeCacheAAA = selectedVehiculeCacheAAA;
	}

//	public void setListeCacheAAA(List<VehiculeCacheAAA> values) {
//		this.listeCacheAAA = values;
//	}
	
	public void setListeCacheAAA(LazyDataModel<VehiculeCacheAAA> values) {
		this.listeCacheAAA = values;
	}


}  
