package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IImportStockTempDAO;
import fr.legrain.tiers.model.ImportStockTemp;
import fr.legrain.validator.common.BeanValidator;

public class ImportStockTempDAO implements IImportStockTempDAO {
	
	static Logger logger = Logger.getLogger(ImportStockTempDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public ImportStockTempDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from ImportStockTemp a";


	public void persist(ImportStockTemp transientInstance) {
		System.out.println("persisting ImportStockTemp instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(ImportStockTemp persistentInstance) {
		System.out.println("removing ImportStockTemp instance");
		try {
			ImportStockTemp e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public ImportStockTemp merge(ImportStockTemp detachedInstance) {
		System.out.println("merging ImportStockTemp instance");
		try {
			ImportStockTemp result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public ImportStockTemp findById(int id) {
		System.out.println("getting ImportStockTemp instance with id: " + id);
		try {
			ImportStockTemp instance = entityManager.find(ImportStockTemp.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	/**
	 * tout le ImportStockTemp pour une entreprise
	 */
	public List<ImportStockTemp> findByUserImportStockTemp(int idImportStockTemp) {
		System.out.println("getting ImportStockTemp instance with idStock : " + idImportStockTemp);
		try {
			//if(!idImportStockTemp.equals("")){
				Query query = entityManager.createQuery("select f from ImportStockTemp f where f.idStock='"+idImportStockTemp+"'");
				List<ImportStockTemp> l = query.getResultList();
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
	
	public ImportStockTemp findByCode(String code) {
		System.out.println("getting ImportStockTemp instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from ImportStockTemp f where f.ImportStockTempname='"+code+"'");
				ImportStockTemp instance = (ImportStockTemp)query.getSingleResult();
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

	public List<ImportStockTemp> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<ImportStockTemp> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<ImportStockTemp> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<ImportStockTemp> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<ImportStockTemp> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<ImportStockTemp> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(ImportStockTemp value) throws Exception {
		BeanValidator<ImportStockTemp> validator = new BeanValidator<ImportStockTemp>(ImportStockTemp.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(ImportStockTemp value, String propertyName) throws Exception {
		BeanValidator<ImportStockTemp> validator = new BeanValidator<ImportStockTemp>(ImportStockTemp.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(ImportStockTemp transientInstance) {
		entityManager.detach(transientInstance);
	}
	
	public void deleteByUserImportStockTemp(int idCompany) {
		System.out.println("delete ImportStockTemp instance with idCompany " + idCompany);
		try {
				Query query = entityManager.createQuery("delete from ImportStockTemp o where o.idStock.id like :idCompany");
				query.setParameter("idCompany", idCompany);
				int deleted = query.executeUpdate();
				System.out.println("delete successful");
		} catch (RuntimeException re) {
			System.out.println("delete failed");
			re.printStackTrace();
			throw re;
		}
	}

	//	@Override
	//	protected ImportStockTemp refresh(ImportStockTemp persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
