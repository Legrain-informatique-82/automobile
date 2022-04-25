package fr.legrain.tiers.dao.jpa;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.lib.data.LibDate;
import fr.legrain.tiers.dao.IFicheCarecoDAO;
import fr.legrain.tiers.model.FicheCareco;
import fr.legrain.tiers.model.User;
import fr.legrain.validator.common.BeanValidator;

public class FicheCarecoDAO implements IFicheCarecoDAO {
	
	static Logger logger = Logger.getLogger(FicheCarecoDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public FicheCarecoDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from FicheCareco a";


	public void persist(FicheCareco transientInstance) {
		System.out.println("persisting FicheCareco instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(FicheCareco persistentInstance) {
		System.out.println("removing FicheCareco instance");
		try {
			FicheCareco e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public FicheCareco merge(FicheCareco detachedInstance) {
		System.out.println("merging FicheCareco instance");
		try {
			FicheCareco result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public FicheCareco findById(int id) {
		System.out.println("getting FicheCareco instance with id: " + id);
		try {
			FicheCareco instance = entityManager.find(FicheCareco.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<FicheCareco> findByCNIT_TypeCG(String typeCG, Date date1ereMiseEnCirculation) {
		System.out.println("getting FicheCareco instance with typeCG: " + typeCG);
		try {
			if(!typeCG.equals("")){
				//Query query = entityManager.createQuery("select f from FicheCareco f where f.typeCG='"+typeCG+"'");
				
				Query query = entityManager.createNamedQuery(FicheCareco.QN.FIND_BY_CNIT);
				query.setParameter("cnit", typeCG);
//				FicheCareco instance = (FicheCareco)query.getSingleResult();
//				System.out.println("get successful");
//				return instance;
				List<FicheCareco> l = query.getResultList();
				List<FicheCareco> listeResult = new ArrayList<FicheCareco>();
				Date dateDe = null;
				Date dateA = null;
				for (FicheCareco ficheCareco : l) {
					dateDe = ficheCareco.getDateDe();
					dateA = ficheCareco.getDateA();
					if(date1ereMiseEnCirculation.after(dateDe) && date1ereMiseEnCirculation.before(dateA)) {
						listeResult.add(ficheCareco);
					}
				}
				return listeResult;
			}
			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public FicheCareco findByCode(String code) {
		System.out.println("getting FicheCareco instance with idCareco: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from FicheCareco f where f.idCareco='"+code+"'");
				FicheCareco instance = (FicheCareco)query.getSingleResult();
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


	public List<FicheCareco> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<FicheCareco> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<FicheCareco> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<FicheCareco> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<FicheCareco> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<FicheCareco> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(FicheCareco value) throws Exception {
		BeanValidator<FicheCareco> validator = new BeanValidator<FicheCareco>(FicheCareco.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(FicheCareco value, String propertyName) throws Exception {
		BeanValidator<FicheCareco> validator = new BeanValidator<FicheCareco>(FicheCareco.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(FicheCareco transientInstance) {
		entityManager.detach(transientInstance);
	}

}
