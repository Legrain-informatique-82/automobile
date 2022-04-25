package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IClientDAO;
import fr.legrain.tiers.model.Client;
import fr.legrain.validator.common.BeanValidator;

public class ClientDAO implements IClientDAO {
	
	static Logger logger = Logger.getLogger(ClientDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public ClientDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from Client a";


	public void persist(Client transientInstance) {
		System.out.println("persisting Client instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(Client persistentInstance) {
		System.out.println("removing Client instance");
		try {
			Client e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Client merge(Client detachedInstance) {
		System.out.println("merging Client instance");
		try {
			Client result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Client findById(int id) {
		System.out.println("getting Client instance with id: " + id);
		try {
			Client instance = entityManager.find(Client.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<Client> findByTypeClient(int idClient) {
		System.out.println("getting Client instance with idClient: " + idClient);
		try {
			//if(!idClient.equals("")){
				Query query = entityManager.createQuery("select f from Client f where f.idClient='"+idClient+"'");
				List<Client> l = query.getResultList();
				System.out.println("get successful");
				return l;
			//}
			//return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<Client> findByRefConstructeur(String refConstructeur, String typePiece) {
		System.out.println("getting Client instance with refConstructeur: " + refConstructeur);
		try {
			if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery("select f from Client f where f.refConstructeur='"+refConstructeur+"' and r.typeDePiece='"+typePiece+"'");
				List<Client> l = query.getResultList();
				Client instance = (Client)query.getSingleResult();
				System.out.println("get successful");
				return l;
			}
			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Client findByCode(String code) {
		System.out.println("getting Client instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Client f where f.Clientname='"+code+"'");
				Client instance = (Client)query.getSingleResult();
				System.out.println("get successful");
				return instance;
			}
			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}


	public List<Client> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<Client> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Client> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<Client> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Client> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<Client> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(Client value) throws Exception {
		BeanValidator<Client> validator = new BeanValidator<Client>(Client.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(Client value, String propertyName) throws Exception {
		BeanValidator<Client> validator = new BeanValidator<Client>(Client.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(Client transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected Client refresh(Client persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
