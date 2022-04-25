package fr.careco.blueway.ws.model;

import java.util.List;

import fr.legrain.tiers.dao.IGenericDAO;
//import javax.ejb.Remote;

//@Remote
public interface IAutorisationB2BDAO extends IGenericDAO<AutorisationB2B> {

	public AutorisationB2B findByLogin(String login, String password);
	public List<AutorisationB2B> selectAll(String debut);
	
}
