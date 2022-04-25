package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IAuthURLDAO;
import fr.legrain.tiers.model.AuthURL;
import fr.legrain.tiers.model.User;
import fr.legrain.validator.common.BeanValidator;

public class AuthURLDAO  implements IAuthURLDAO {
	
	static Logger logger = Logger.getLogger(AuthURLDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public AuthURLDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from AuthURL a";


	public void persist(AuthURL transientInstance) {
		System.out.println("persisting AuthURL instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(AuthURL persistentInstance) {
		System.out.println("removing AuthURL instance");
		try {
			AuthURL e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public AuthURL merge(AuthURL detachedInstance) {
		System.out.println("merging AuthURL instance");
		try {
			AuthURL result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public AuthURL findById(int id) {
		System.out.println("getting AuthURL instance with id: " + id);
		try {
			AuthURL instance = entityManager.find(AuthURL.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public List<AuthURL> findByRoleID(int roleID) {
		System.out.println("getting AuthURL instance with roleID: " + roleID);
		try {
			//if(!roleID.equals("")){
				//Query query = entityManager.createQuery("select f from AuthURL f where f.role.id='"+roleID+"'");
				Query query = entityManager.createNamedQuery(AuthURL.QN.FIND_BY_ROLEID);
				query.setParameter("roleID", roleID);
				
				List<AuthURL> l = query.getResultList();
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


	public List<AuthURL> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<AuthURL> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<AuthURL> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<AuthURL> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<AuthURL> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<AuthURL> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(AuthURL value) throws Exception {
		BeanValidator<AuthURL> validator = new BeanValidator<AuthURL>(AuthURL.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(AuthURL value, String propertyName) throws Exception {
		BeanValidator<AuthURL> validator = new BeanValidator<AuthURL>(AuthURL.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(AuthURL transientInstance) {
		entityManager.detach(transientInstance);
	}

	@Override
	public AuthURL findByCode(String code) {
		return null;
	}

	//	@Override
	//	protected AuthURL refresh(AuthURL persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
