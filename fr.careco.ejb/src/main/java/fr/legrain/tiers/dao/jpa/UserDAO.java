package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IUserDAO;
import fr.legrain.tiers.model.User;
import fr.legrain.validator.common.BeanValidator;

public class UserDAO /*extends AbstractApplicationDAOServer<User>*/ implements IUserDAO {
	
	static Logger logger = Logger.getLogger(UserDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from User a";


	public void persist(User transientInstance) {
		System.out.println("persisting User instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(User persistentInstance) {
		System.out.println("removing User instance");
		try {
			User e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		System.out.println("merging User instance");
		try {
			User result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public User findById(int id) {
		System.out.println("getting User instance with id: " + id);
		try {
			User instance = entityManager.find(User.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public User findByCode(String code) {
		System.out.println("getting User instance with code: " + code);
		try {
			if(!code.equals("")){
				//Query query = entityManager.createQuery("select f from User f where f.username='"+code+"'");
				Query query = entityManager.createNamedQuery(User.QN.FIND_BY_USERNAME);
				query.setParameter("code", code);
				
				User instance = (User)query.getSingleResult();
				
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


	public List<User> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<User> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<User> selectAll(String debut) {
		try {
			//if(!idStock.equals("")){
				Query query = null;
				if(debut==null) debut="";
					query = entityManager.createQuery("select u from User u where u.nom like :debut order by u.nom");
				query.setParameter("debut", debut+"%");
				List<User> l = query.getResultList();
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
	public List<User> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<User> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<User> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<User> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(User value) throws Exception {
		BeanValidator<User> validator = new BeanValidator<User>(User.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(User value, String propertyName) throws Exception {
		BeanValidator<User> validator = new BeanValidator<User>(User.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(User transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected User refresh(User persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
