package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.AuthView;
//import javax.ejb.Remote;

//@Remote
public interface IAuthViewDAO extends IGenericDAO<AuthView> {
	public List<AuthView> findByRoleID(int roleID);
}
