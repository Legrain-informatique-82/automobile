package fr.legrain.careco.v1.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import fr.legrain.validator.common.BeanValidator;

public class GlobalV1DAO implements IGlobalV1DAO {
	
	static Logger logger = Logger.getLogger(GlobalV1DAO.class);

	@PersistenceContext(unitName = "careco_import_V1")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public GlobalV1DAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from CarPart a";


	public void persist(CarPart transientInstance) {
		System.out.println("persisting CarPart instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(CarPart persistentInstance) {
		System.out.println("removing CarPart instance");
		try {
			CarPart e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public CarPart merge(CarPart detachedInstance) {
		System.out.println("merging CarPart instance");
		try {
			CarPart result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public CarPart findByImmat(String immat) {
		System.out.println("getting CarPart instance with immat: " + immat);
		try {
			if(!immat.equals("")){
				Query query = entityManager.createQuery("select f from CarPart f where f.Immat_SIV='"+immat+"'");
				CarPart instance = (CarPart)query.getSingleResult();
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
	
	public CarPart findByVIN(String vin) {
		System.out.println("getting CarPart instance with vin: " + vin);
		try {
			if(!vin.equals("")){
				Query query = entityManager.createQuery("select f from CarPart f where f.Type='"+vin+"'");
				CarPart instance = (CarPart)query.getSingleResult();
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

	public CarPart findById(int id) {
		System.out.println("getting CarPart instance with id: " + id);
		try {
			CarPart instance = entityManager.find(CarPart.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public CarPart findByCode(String code) {
		System.out.println("getting CarPart instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from CarPart f where f.CarPartname='"+code+"'");
				CarPart instance = (CarPart)query.getSingleResult();
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


	public List<CarPart> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<CarPart> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<CarPart> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<CarPart> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<CarPart> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<CarPart> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	public List<CoreUser> selectAllCoreUser() {
		try {
			//Query ejbQuery = entityManager.createQuery("select disctinct(v.marque) from CarPart v");
			TypedQuery<CoreUser> ejbQuery = entityManager.createQuery("select u from CoreUser u", CoreUser.class);
			List<CoreUser> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<AdherentV1> selectAllAdherent() {
		try {
			//Query ejbQuery = entityManager.createQuery("select disctinct(v.marque) from CarPart v");
			TypedQuery<AdherentV1> ejbQuery = entityManager.createQuery("select u from AdherentV1 u", AdherentV1.class);
			List<AdherentV1> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<Company> selectAllCompany() {
		try {
			//Query ejbQuery = entityManager.createQuery("select disctinct(v.marque) from CarPart v");
			TypedQuery<Company> ejbQuery = entityManager.createQuery("select u from Company u", Company.class);
			List<Company> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<UserV1> selectAllUserV1() {
		try {
			//Query ejbQuery = entityManager.createQuery("select disctinct(v.marque) from CarPart v");
			TypedQuery<UserV1> ejbQuery = entityManager.createQuery("select u from UserV1 u", UserV1.class);
			List<UserV1> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public Map<Integer,Company> selectAllUserV1CompanyId() {
		try {
			Map<Integer,Company> r = new HashMap<Integer, Company>();
			List<UserV1> l = selectAllUserV1();
			for (UserV1 userV1 : l) {
				r.put(userV1.getParentCoreUserId(), userV1.getCompanyId());
			}
			return r;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<CarPart> selectAllCarPart() {
		try {
			//Query ejbQuery = entityManager.createQuery("select disctinct(v.marque) from CarPart v");
			TypedQuery<CarPart> ejbQuery = entityManager.createQuery("select u from CarPart u", CarPart.class);
			List<CarPart> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}


	@Override
	public boolean validate(CarPart value) throws Exception {
		BeanValidator<CarPart> validator = new BeanValidator<CarPart>(CarPart.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(CarPart value, String propertyName) throws Exception {
		BeanValidator<CarPart> validator = new BeanValidator<CarPart>(CarPart.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(CarPart transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected CarPart refresh(CarPart persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
