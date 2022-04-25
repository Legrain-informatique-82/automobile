package fr.careco.blueway.ws.model;

import java.util.Date;
import java.util.List;

import fr.legrain.tiers.dao.IGenericDAO;
//import javax.ejb.Remote;

//@Remote
public interface ILogAppelB2BDAO extends IGenericDAO<LogAppelB2B> {

	public List<LogAppelB2B> findByLogin(String login);
	public List<LogAppelB2B> findByDate(Date debut, Date fin);
	public List<LogAppelB2B> findByLoginDate(String login, Date debut, Date fin);
	
}
