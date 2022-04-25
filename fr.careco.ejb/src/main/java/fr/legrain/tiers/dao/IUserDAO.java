package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.User;
//import javax.ejb.Remote;

//@Remote
public interface IUserDAO extends IGenericDAO<User> {
//	public void persist(TaTCivilite transientInstance);
//	public void remove(TaTCivilite persistentInstance);
//	public TaTCivilite merge(TaTCivilite detachedInstance);
//	public TaTCivilite findById(int id);
//	public TaTCivilite findByCode(String code);
//	public List<TaTCivilite> selectAll();
//	public List<TaTCivilite> findWithNamedQuery(String queryName);
//	public List<TaTCivilite> findWithJPQLQuery(String JPQLquery);
//	public boolean validate(TaTCivilite entity) throws Exception;
	
	public List<User> selectAll(String debut);
}
