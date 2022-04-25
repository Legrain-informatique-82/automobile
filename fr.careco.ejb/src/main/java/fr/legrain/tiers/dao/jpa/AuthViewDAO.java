package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IAuthViewDAO;
import fr.legrain.tiers.model.AuthView;
import fr.legrain.validator.common.BeanValidator;

public class AuthViewDAO  implements IAuthViewDAO {
	
	static Logger logger = Logger.getLogger(AuthViewDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public AuthViewDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from AuthView a";


	public void persist(AuthView transientInstance) {
		System.out.println("persisting AuthView instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(AuthView persistentInstance) {
		System.out.println("removing AuthView instance");
		try {
			AuthView e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public AuthView merge(AuthView detachedInstance) {
		System.out.println("merging AuthView instance");
		try {
			AuthView result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public AuthView findById(int id) {
		System.out.println("getting AuthView instance with id: " + id);
		try {
			AuthView instance = entityManager.find(AuthView.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public List<AuthView> findByRoleID(int roleID) {
		System.out.println("getting AuthView instance with code: " + roleID);
		try {
			//if(!roleID.equals("")){
				Query query = entityManager.createQuery("select f from AuthView f where f.role.id='"+roleID+"'");
				List<AuthView> l = query.getResultList();
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



	public List<AuthView> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<AuthView> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<AuthView> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<AuthView> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<AuthView> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<AuthView> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(AuthView value) throws Exception {
		BeanValidator<AuthView> validator = new BeanValidator<AuthView>(AuthView.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(AuthView value, String propertyName) throws Exception {
		BeanValidator<AuthView> validator = new BeanValidator<AuthView>(AuthView.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(AuthView transientInstance) {
		entityManager.detach(transientInstance);
	}

	@Override
	public AuthView findByCode(String code) {
		return null;
	}

	//	@Override
	//	protected AuthView refresh(AuthView persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
