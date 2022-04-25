package fr.legrain.tiers.dao.jpa;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IGarantieCarecoDAO;
import fr.legrain.tiers.model.GarantieCareco;
import fr.legrain.validator.common.BeanValidator;

public class GarantieCarecoDAO implements IGarantieCarecoDAO {
	
	static Logger logger = Logger.getLogger(GarantieCarecoDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public GarantieCarecoDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from GarantieCareco a";


	public void persist(GarantieCareco transientInstance) {
		System.out.println("persisting GarantieCareco instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(GarantieCareco persistentInstance) {
		System.out.println("removing GarantieCareco instance");
		try {
			GarantieCareco e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public GarantieCareco merge(GarantieCareco detachedInstance) {
		System.out.println("merging GarantieCareco instance");
		try {
			GarantieCareco result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public GarantieCareco findById(int id) {
		System.out.println("getting GarantieCareco instance with id: " + id);
		try {
			GarantieCareco instance = entityManager.find(GarantieCareco.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<GarantieCareco> findByTypeGarantieCareco(int idGarantieCareco) {
		System.out.println("getting GarantieCareco instance with idGarantieCareco: " + idGarantieCareco);
		try {
			//if(!idGarantieCareco.equals("")){
				Query query = entityManager.createQuery("select f from GarantieCareco f where f.idGarantieCareco='"+idGarantieCareco+"'");
				List<GarantieCareco> l = query.getResultList();
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
	
	public List<GarantieCareco> findByRefConstructeur(String refConstructeur, String typePiece) {
		System.out.println("getting GarantieCareco instance with refConstructeur: " + refConstructeur);
		try {
			if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery("select f from GarantieCareco f where f.refConstructeur='"+refConstructeur+"' and r.typeDePiece='"+typePiece+"'");
				List<GarantieCareco> l = query.getResultList();
				GarantieCareco instance = (GarantieCareco)query.getSingleResult();
				System.out.println("get successful");
				return l;
			}
			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public GarantieCareco findByCode(String code) {
		System.out.println("getting GarantieCareco instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from GarantieCareco f where f.GarantieCareconame='"+code+"'");
				GarantieCareco instance = (GarantieCareco)query.getSingleResult();
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


	public List<GarantieCareco> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<GarantieCareco> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<GarantieCareco> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<GarantieCareco> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<GarantieCareco> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<GarantieCareco> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<String> selectAll(String debut) {
		System.out.println("getting GarantieCareco instance : " );
		try {
			//if(!idStock.equals("")){
				Query query = null;
				if(debut!=null)
					query = entityManager.createQuery("select f.centre from GarantieCareco f where f.centre like '%"+debut+"%'");
				else
					query = entityManager.createQuery("select f.centre from Stock f ");
				List<String> l = query.getResultList();
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
	
	public GarantieCareco findGarantieCarecoByMontant(BigDecimal prix, Integer duree) {
		try {
			//if(!code.equals("")){
				Query query = entityManager.createQuery("select f from GarantieCareco f where f.prixVentePieceDebut <= :prix1 and f.prixVentePiecefin >= :prix2");
				query.setParameter("prix1", prix);
				query.setParameter("prix2", prix);
				GarantieCareco instance = (GarantieCareco)query.getSingleResult();
				System.out.println("get successful");
				return instance;
			//}
			//return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(GarantieCareco value) throws Exception {
		BeanValidator<GarantieCareco> validator = new BeanValidator<GarantieCareco>(GarantieCareco.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(GarantieCareco value, String propertyName) throws Exception {
		BeanValidator<GarantieCareco> validator = new BeanValidator<GarantieCareco>(GarantieCareco.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(GarantieCareco transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected GarantieCareco refresh(GarantieCareco persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
