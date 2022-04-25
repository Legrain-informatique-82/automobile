package fr.careco.blueway.ws.model;

import java.util.Date;
import java.util.List;

import fr.legrain.tiers.dao.IGenericDAO;
//import javax.ejb.Remote;

//@Remote
public interface ILogAppelB2CDAO extends IGenericDAO<LogAppelB2C> {

	public List<LogAppelB2C> findByLogin(String login);
	public List<LogAppelB2C> findByDate(Date debut, Date fin);
	public List<LogAppelB2C> findByLoginDate(String login, Date debut, Date fin);
	
}
