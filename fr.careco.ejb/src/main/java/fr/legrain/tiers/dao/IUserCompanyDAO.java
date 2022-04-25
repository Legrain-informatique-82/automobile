package fr.legrain.tiers.dao;

import fr.legrain.tiers.model.UserCompany;

public interface IUserCompanyDAO extends IGenericDAO<UserCompany> {
	
	public UserCompany findByIdOrigine(Integer idOrigine);
	
}
