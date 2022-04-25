package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.AuthURL;
//import javax.ejb.Remote;

//@Remote
public interface IAuthURLDAO extends IGenericDAO<AuthURL> {
	
	public List<AuthURL> findByRoleID(int roleID);
	
}
