package fr.careco.blueway.ws.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.validator.common.BeanValidator;

public class AutorisationB2CDAO implements IAutorisationB2CDAO {
	
	static Logger logger = Logger.getLogger(AutorisationB2CDAO.class);

	@PersistenceContext(unitName = "careco_AAA")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public AutorisationB2CDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from AutorisationB2C a";


	public void persist(AutorisationB2C transientInstance) {
		System.out.println("persisting AutorisationB2C instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(AutorisationB2C persistentInstance) {
		System.out.println("removing AutorisationB2C instance");
		try {
			AutorisationB2C e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public AutorisationB2C merge(AutorisationB2C detachedInstance) {
		System.out.println("merging AutorisationB2C instance");
		try {
			AutorisationB2C result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public AutorisationB2C findByLogin(String login, String password) {
		System.out.println("getting AutorisationB2C instance with login: " + login);
		try {
			if(!login.equals("")){
//				Query query = entityManager.createQuery("select f from AutorisationB2C f where f.Immat_SIV='"+immat+"'");
				
				Query query = entityManager.createNamedQuery(AutorisationB2C.QN.FIND_BY_LOGIN);
				query.setParameter("login", login);
				query.setParameter("pwd", password);
				
				AutorisationB2C instance = (AutorisationB2C)query.getSingleResult();
				System.out.println("get successful");
				return instance;
			}
			return null;
		} catch (NoResultException e) {
			System.out.println("Aucun résultat pour cette requete");
			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public AutorisationB2C findById(int id) {
		System.out.println("getting AutorisationB2C instance with id: " + id);
		try {
			AutorisationB2C instance = entityManager.find(AutorisationB2C.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public AutorisationB2C findByCode(String code) {
		System.out.println("getting AutorisationB2C instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from AutorisationB2C f where f.nomAuthen='"+code+"'");
				AutorisationB2C instance = (AutorisationB2C)query.getSingleResult();
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


	public List<AutorisationB2C> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<AutorisationB2C> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<AutorisationB2C> selectAll(String debut) {
		try {
			//if(!idStock.equals("")){
				Query query = null;
				if(debut==null) debut="";
					query = entityManager.createQuery("select u from AutorisationB2C u where u.nomAuthen like :debut order by u.nomAuthen");
				query.setParameter("debut", debut+"%");
				List<AutorisationB2C> l = query.getResultList();
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

	@Override
	public List<AutorisationB2C> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<AutorisationB2C> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<AutorisationB2C> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<AutorisationB2C> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(AutorisationB2C value) throws Exception {
		BeanValidator<AutorisationB2C> validator = new BeanValidator<AutorisationB2C>(AutorisationB2C.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(AutorisationB2C value, String propertyName) throws Exception {
		BeanValidator<AutorisationB2C> validator = new BeanValidator<AutorisationB2C>(AutorisationB2C.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(AutorisationB2C transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected AutorisationB2C refresh(AutorisationB2C persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
