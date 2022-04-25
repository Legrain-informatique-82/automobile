package fr.legrain.careco.aaa.model;

import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.Remote;

import fr.careco.aaa.ws.NumeroImmatriculationInconnu_Exception;
import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServer;
import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServerDTO;
import fr.legrain.bdg.tiers.service.remote.IGenericCRUDServiceRemote;
import fr.legrain.tiers.dto.UserDTO;
import fr.legrain.tiers.model.User;

@Remote
public interface IVehiculeCacheAAAServiceRemote extends IGenericCRUDServiceRemote<VehiculeCacheAAA,VehiculeCacheAAADTO>,
														IAbstractLgrDAOServer<VehiculeCacheAAA>,IAbstractLgrDAOServerDTO<VehiculeCacheAAADTO>{
	
	public VehiculeCacheAAA wsSiVinConsulterVehiculeParImmatOuVin(String immatriculation, String vin) throws EJBException;
	public VehiculeCacheAAA wsSiVinConsulterVehiculeParImmatOuVinProprio(String immatriculation, String vin, String debutNomProprietaire, boolean sansCache) throws EJBException;
	public VehiculeCacheAAA rechercheDansCache(String immatriculation, String vin) throws EJBException;
	
	public Map<String, String> rechercheCNITAnnee(String marque, String modele, String anneeMin, String anneeMax) throws EJBException;
	
	public List<String> selectAllMarque();
	public List<String> selectAllModele(String marque);
	public List<String> selectAllAnnee();
	
}
