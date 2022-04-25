package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.UserCompany;

public interface ISocietesAmiesDAO extends IGenericDAO<SocietesAmies> {
	
	public List<SocietesAmies> findSocieteAmie(int idUserCompany);
	public List<SocietesAmies> findSocieteAmieAjoutee(int idUserCompany);
	public List<SocietesAmies> findSocieteMultiSite(int idUserCompany);
	
}
