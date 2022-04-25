package fr.careco.blueway.ws.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.validator.common.BeanValidator;

public class LogAppelB2CDAO implements ILogAppelB2CDAO {
	
	static Logger logger = Logger.getLogger(LogAppelB2CDAO.class);

	@PersistenceContext(unitName = "careco_AAA")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public LogAppelB2CDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from LogAppelB2C a";


	public void persist(LogAppelB2C transientInstance) {
		System.out.println("persisting LogAppelB2C instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(LogAppelB2C persistentInstance) {
		System.out.println("removing LogAppelB2C instance");
		try {
			LogAppelB2C e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public LogAppelB2C merge(LogAppelB2C detachedInstance) {
		System.out.println("merging LogAppelB2C instance");
		try {
			LogAppelB2C result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<LogAppelB2C> findByLogin(String login) {
		System.out.println("getting LogAppelB2C instance with login: " + login);
		try {
			if(!login.equals("")){
//				Query query = entityManager.createQuery("select f from LogAppelB2B f where f.Immat_SIV='"+immat+"'");
				
				Query query = entityManager.createNamedQuery(LogAppelB2C.QN.FIND_BY_LOGIN);
				query.setParameter("login", login);
				
				List<LogAppelB2C> instance = query.getResultList();
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
	
	public List<LogAppelB2C> findByDate(Date debut, Date fin) {
		System.out.println("getting LogAppelB2C instance with debut: " + debut +" and fin: "+fin);
		try {
			if(debut!=null && fin!=null){
//				Query query = entityManager.createQuery("select f from LogAppelB2B f where f.Immat_SIV='"+immat+"'");
				
				Query query = entityManager.createNamedQuery(LogAppelB2C.QN.FIND_BY_DATE);
				query.setParameter("debut", debut);
				query.setParameter("fin", fin);
				
				List<LogAppelB2C> instance = query.getResultList();
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
	
	public List<LogAppelB2C> findByLoginDate(String login, Date debut, Date fin) {
		System.out.println("getting LogAppelB2B instance with login: " + login +" and debut: " + debut +" and fin: "+fin);
		try {
			if(!login.equals("") &&debut!=null && fin!=null){
//				Query query = entityManager.createQuery("select f from LogAppelB2B f where f.Immat_SIV='"+immat+"'");
				
				Query query = entityManager.createNamedQuery(LogAppelB2C.QN.FIND_BY_LOGIN_DATE);
				query.setParameter("login", login);
				query.setParameter("debut", debut);
				query.setParameter("fin", fin);
				
				List<LogAppelB2C> instance = query.getResultList();
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

	public LogAppelB2C findById(int id) {
		System.out.println("getting LogAppelB2C instance with id: " + id);
		try {
			LogAppelB2C instance = entityManager.find(LogAppelB2C.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public LogAppelB2C findByCode(String code) {
		System.out.println("getting LogAppelB2C instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from LogAppelB2C f where f.nomAuthen='"+code+"'");
				LogAppelB2C instance = (LogAppelB2C)query.getSingleResult();
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


	public List<LogAppelB2C> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<LogAppelB2C> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<LogAppelB2C> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<LogAppelB2C> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<LogAppelB2C> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<LogAppelB2C> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(LogAppelB2C value) throws Exception {
		BeanValidator<LogAppelB2C> validator = new BeanValidator<LogAppelB2C>(LogAppelB2C.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(LogAppelB2C value, String propertyName) throws Exception {
		BeanValidator<LogAppelB2C> validator = new BeanValidator<LogAppelB2C>(LogAppelB2C.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(LogAppelB2C transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected LogAppelB2C refresh(LogAppelB2C persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
