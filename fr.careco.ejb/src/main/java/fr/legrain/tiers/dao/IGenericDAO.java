package fr.legrain.tiers.dao;

import java.util.List;

import javax.ejb.FinderException;

//import javax.ejb.Remote;

//@Remote
public interface IGenericDAO<Entity> {
	public void persist(Entity transientInstance);
	public void remove(Entity persistentInstance);
	public Entity merge(Entity detachedInstance);
	public Entity findById(int id);
	public Entity findByCode(String code);
	public List<Entity> selectAll();
	public List<Entity> findWithNamedQuery(String queryName);
	public List<Entity> findWithJPQLQuery(String JPQLquery);
	public boolean validate(Entity value) throws Exception;
	public boolean validateField(Entity value, String propertyName) throws Exception;
	public void detach(Entity transientInstance);
}
