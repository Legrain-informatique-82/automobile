package fr.legrain.careco.aaa.model;

import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.persistence.Query;

import fr.legrain.tiers.dao.IGenericDAO;
//import javax.ejb.Remote;

//@Remote
public interface IVehiculeCacheAAADAO extends IGenericDAO<VehiculeCacheAAA> {

	public VehiculeCacheAAA findByImmat(String immat);
	
	public VehiculeCacheAAA findByVIN(String vin);
	
	public Map<String, String> rechercheCNITAnnee(String marque, String modele, String anneeMin, String anneeMax);
	
	
	public List<String> selectAllMarque();
	public List<String> selectAllModele(String marque);
	public List<String> selectAllAnnee();
	
	public List<VehiculeCacheAAA> selectAll(int startPosition, int maxResults);
	public long count();
	
}
