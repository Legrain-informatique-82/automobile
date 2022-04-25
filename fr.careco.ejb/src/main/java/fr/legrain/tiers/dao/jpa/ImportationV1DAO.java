package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IImportationV1DAO;
import fr.legrain.tiers.model.ImportationV1;
import fr.legrain.validator.common.BeanValidator;

public class ImportationV1DAO implements IImportationV1DAO {
	
	static Logger logger = Logger.getLogger(ImportationV1DAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public ImportationV1DAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from ImportationV1 a";


	public void persist(ImportationV1 transientInstance) {
		System.out.println("persisting ImportationV1 instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(ImportationV1 persistentInstance) {
		System.out.println("removing ImportationV1 instance");
		try {
			ImportationV1 e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public ImportationV1 merge(ImportationV1 detachedInstance) {
		System.out.println("merging ImportationV1 instance");
		try {
			ImportationV1 result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public ImportationV1 findById(int id) {
		System.out.println("getting ImportationV1 instance with id: " + id);
		try {
			ImportationV1 instance = entityManager.find(ImportationV1.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<ImportationV1> findByTypeImportationV1(int idImportationV1) {
		System.out.println("getting ImportationV1 instance with idImportationV1: " + idImportationV1);
		try {
			//if(!idImportationV1.equals("")){
				Query query = entityManager.createQuery("select f from ImportationV1 f where f.idImportationV1='"+idImportationV1+"'");
				List<ImportationV1> l = query.getResultList();
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
	
	public List<ImportationV1> findByOrgine(String tableOrigine, int idOrigine) {
		System.out.println("getting ImportationV1 instance with tableOrigine: " + tableOrigine);
		try {
			//if(!idImportationV1.equals("")){
				Query query = entityManager.createQuery("select f from ImportationV1 f where f.tableOrigine='"+tableOrigine+"' and f.idOrigine="+idOrigine);
				List<ImportationV1> l = query.getResultList();
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
	
	public List<ImportationV1> findByCourant(String tableCourante, int idCourant) {
		System.out.println("getting ImportationV1 instance with tableOrigine: " + tableCourante);
		try {
			//if(!idImportationV1.equals("")){
				Query query = entityManager.createQuery("select f from ImportationV1 f where f.tableCourante='"+tableCourante+"' and f.idCourant="+idCourant);
				List<ImportationV1> l = query.getResultList();
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
	

	public ImportationV1 findByCode(String code) {
		System.out.println("getting ImportationV1 instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from ImportationV1 f where f.ImportationV1name='"+code+"'");
				ImportationV1 instance = (ImportationV1)query.getSingleResult();
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


	public List<ImportationV1> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<ImportationV1> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<ImportationV1> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<ImportationV1> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<ImportationV1> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<ImportationV1> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(ImportationV1 value) throws Exception {
		BeanValidator<ImportationV1> validator = new BeanValidator<ImportationV1>(ImportationV1.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(ImportationV1 value, String propertyName) throws Exception {
		BeanValidator<ImportationV1> validator = new BeanValidator<ImportationV1>(ImportationV1.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(ImportationV1 transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected ImportationV1 refresh(ImportationV1 persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
