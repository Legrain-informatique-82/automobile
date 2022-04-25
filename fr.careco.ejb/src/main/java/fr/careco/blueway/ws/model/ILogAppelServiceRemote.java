package fr.careco.blueway.ws.model;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServer;
import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServerDTO;
import fr.legrain.bdg.tiers.service.remote.IGenericCRUDServiceRemote;

@Remote
public interface ILogAppelServiceRemote extends IGenericCRUDServiceRemote<LogAppelB2B,LogAppelB2BDTO>,
														IAbstractLgrDAOServer<LogAppelB2B>,IAbstractLgrDAOServerDTO<LogAppelB2BDTO>{
	
	public List<LogAppelB2B> findByLoginB2B(String login);
	public List<LogAppelB2B> findByDateB2B(Date debut, Date fin);
	public List<LogAppelB2B> findByLoginDateB2B(String login, Date debut, Date fin);
	
	public List<LogAppelB2C> findByLoginB2C(String login);
	public List<LogAppelB2C> findByDateB2C(Date debut, Date fin);
	public List<LogAppelB2C> findByLoginDateB2C(String login, Date debut, Date fin);
	
	public LogAppelB2B enregistreLogB2B(LogAppelB2B log);
	public LogAppelB2C enregistreLogB2C(LogAppelB2C log);
	
	public List<LogAppelB2C> selectAllB2C();
}
