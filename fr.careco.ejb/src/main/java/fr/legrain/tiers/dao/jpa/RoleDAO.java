package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IRoleDAO;
import fr.legrain.tiers.model.Role;
import fr.legrain.validator.common.BeanValidator;

public class RoleDAO /*extends AbstractApplicationDAOServer<Role>*/ implements IRoleDAO {
	
	static Logger logger = Logger.getLogger(RoleDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public RoleDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from Role a";


	public void persist(Role transientInstance) {
		System.out.println("persisting Role instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(Role persistentInstance) {
		System.out.println("removing Role instance");
		try {
			Role e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Role merge(Role detachedInstance) {
		System.out.println("merging Role instance");
		try {
			Role result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Role findById(int id) {
		System.out.println("getting Role instance with id: " + id);
		try {
			Role instance = entityManager.find(Role.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Role findByCode(String code) {
		System.out.println("getting Role instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Role f where f.role='"+code+"'");
				Role instance = (Role)query.getSingleResult();
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


	public List<Role> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<Role> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Role> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<Role> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Role> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<Role> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(Role value) throws Exception {
		BeanValidator<Role> validator = new BeanValidator<Role>(Role.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(Role value, String propertyName) throws Exception {
		BeanValidator<Role> validator = new BeanValidator<Role>(Role.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(Role transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected Role refresh(Role persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
