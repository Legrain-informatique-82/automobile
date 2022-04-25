package fr.careco.blueway.ws.model;

import java.util.List;

import fr.legrain.tiers.dao.IGenericDAO;
//import javax.ejb.Remote;

//@Remote
public interface IAutorisationB2CDAO extends IGenericDAO<AutorisationB2C> {

	public AutorisationB2C findByLogin(String login, String password);
	public List<AutorisationB2C> selectAll(String debut);
	
}
