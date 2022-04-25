package fr.careco.blueway.ws.model;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.RemoveException;

import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServer;
import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServerDTO;
import fr.legrain.bdg.tiers.service.remote.IGenericCRUDServiceRemote;

@Remote
public interface IAutorisationServiceRemote extends IGenericCRUDServiceRemote<AutorisationB2B,AutorisationB2BDTO>,
														IAbstractLgrDAOServer<AutorisationB2B>,IAbstractLgrDAOServerDTO<AutorisationB2BDTO>{
	
	public AutorisationB2B findByLoginB2B(String login, String password);
	public AutorisationB2C findByLoginB2C(String login, String password);
	public List<AutorisationB2B> selectAll(String debut);
	public List<AutorisationB2C> selectAllB2C(String debut);
	
	public AutorisationB2C enregistrerMerge(AutorisationB2C persistentInstance) throws Exception;
	public void remove(AutorisationB2C persistentInstance) throws RemoveException;
	public AutorisationB2C merge(AutorisationB2C persistentInstance);
	
	public List<AutorisationB2C> selectAllB2C();
	
}
