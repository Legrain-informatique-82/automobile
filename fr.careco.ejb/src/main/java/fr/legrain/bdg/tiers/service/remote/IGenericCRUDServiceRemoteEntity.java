package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.Remote;
import javax.ejb.RemoveException;

//@Remote
public interface IGenericCRUDServiceRemoteEntity<Entity> {
	
	public abstract void persist(Entity transientInstance) throws CreateException;
	public abstract void remove(Entity persistentInstance) throws RemoveException;
	public abstract Entity merge(Entity detachedInstance);
	
	public Entity findById(int id) throws FinderException;
	public Entity findByCode(String code) throws FinderException;
   // public List<Entity> findWithNamedQuery(String queryName, int resultLimit);
	
	public abstract void validateEntity(Entity value);
	public abstract void validateEntityProperty(Entity value, String propertyName);
	
	public List<Entity> selectAll();
	
	public int selectCount();
	
}
