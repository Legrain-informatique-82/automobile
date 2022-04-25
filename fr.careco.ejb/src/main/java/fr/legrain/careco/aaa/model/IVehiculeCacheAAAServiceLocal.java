package fr.legrain.careco.aaa.model;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;

import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServer;
import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServerDTO;
import fr.legrain.bdg.tiers.service.remote.IGenericCRUDServiceRemote;

@Local
public interface IVehiculeCacheAAAServiceLocal extends IGenericCRUDServiceRemote<VehiculeCacheAAA,VehiculeCacheAAADTO>,
														IAbstractLgrDAOServer<VehiculeCacheAAA>,IAbstractLgrDAOServerDTO<VehiculeCacheAAADTO>{
	
	public VehiculeCacheAAA wsSiVinConsulterVehiculeParImmatOuVin(String immatriculation, String vin) throws EJBException;
	public VehiculeCacheAAA wsSiVinConsulterVehiculeParImmatOuVinProprio(String immatriculation, String vin, String debutNomProprietaire, boolean sansCache) throws EJBException;
	public VehiculeCacheAAA rechercheDansCache(String immatriculation, String vin) throws EJBException;
	
	public List<String> selectAllMarque();
	public List<String> selectAllModele(String marque);
	public List<String> selectAllAnnee();
	
	public List<VehiculeCacheAAA> selectAllLimit(int startPosition, int maxResults);
	public long count();
	
}
