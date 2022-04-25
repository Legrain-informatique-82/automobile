package fr.legrain.careco.v1.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.legrain.tiers.dao.IGenericDAO;

//@Remote
public interface IGlobalV1DAO extends IGenericDAO<CarPart> {
	
	public List<CoreUser> selectAllCoreUser();
	
	public List<AdherentV1> selectAllAdherent();
	
	public List<Company> selectAllCompany();
	
	public List<CarPart> selectAllCarPart();
	
	public List<UserV1> selectAllUserV1();
	
	public Map<Integer,Company> selectAllUserV1CompanyId();
	
}
