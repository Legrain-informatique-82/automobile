package fr.careco.blueway.ws.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.model.User;
import fr.legrain.validator.common.BeanValidator;

public class AutorisationB2BDAO implements IAutorisationB2BDAO {
	
	static Logger logger = Logger.getLogger(AutorisationB2BDAO.class);

	@PersistenceContext(unitName = "careco_AAA")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public AutorisationB2BDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from AutorisationB2B a";


	public void persist(AutorisationB2B transientInstance) {
		System.out.println("persisting AutorisationB2B instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(AutorisationB2B persistentInstance) {
		System.out.println("removing AutorisationB2B instance");
		try {
			AutorisationB2B e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public AutorisationB2B merge(AutorisationB2B detachedInstance) {
		System.out.println("merging AutorisationB2B instance");
		try {
			AutorisationB2B result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public AutorisationB2B findByLogin(String login, String password) {
		System.out.println("getting AutorisationB2B instance with login: " + login);
		try {
			if(!login.equals("")){
//				Query query = entityManager.createQuery("select f from AutorisationB2B f where f.Immat_SIV='"+immat+"'");
				
				Query query = entityManager.createNamedQuery(AutorisationB2B.QN.FIND_BY_LOGIN);
				query.setParameter("login", login);
				query.setParameter("pwd", password);
				
				AutorisationB2B instance = (AutorisationB2B)query.getSingleResult();
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

	public AutorisationB2B findById(int id) {
		System.out.println("getting AutorisationB2B instance with id: " + id);
		try {
			AutorisationB2B instance = entityManager.find(AutorisationB2B.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public AutorisationB2B findByCode(String code) {
		System.out.println("getting AutorisationB2B instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from AutorisationB2B f where f.nomAuthen='"+code+"'");
				AutorisationB2B instance = (AutorisationB2B)query.getSingleResult();
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


	public List<AutorisationB2B> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<AutorisationB2B> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<AutorisationB2B> selectAll(String debut) {
		try {
			//if(!idStock.equals("")){
				Query query = null;
				if(debut==null) debut="";
					query = entityManager.createQuery("select u from AutorisationB2B u where u.nomAuthen like :debut order by u.nomAuthen");
				query.setParameter("debut", debut+"%");
				List<AutorisationB2B> l = query.getResultList();
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
	public List<AutorisationB2B> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<AutorisationB2B> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<AutorisationB2B> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<AutorisationB2B> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(AutorisationB2B value) throws Exception {
		BeanValidator<AutorisationB2B> validator = new BeanValidator<AutorisationB2B>(AutorisationB2B.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(AutorisationB2B value, String propertyName) throws Exception {
		BeanValidator<AutorisationB2B> validator = new BeanValidator<AutorisationB2B>(AutorisationB2B.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(AutorisationB2B transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected AutorisationB2B refresh(AutorisationB2B persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
