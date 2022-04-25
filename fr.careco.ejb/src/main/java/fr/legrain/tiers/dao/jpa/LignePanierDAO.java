package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.ILignePanierDAO;
import fr.legrain.tiers.model.LignePanier;
import fr.legrain.validator.common.BeanValidator;

public class LignePanierDAO implements ILignePanierDAO {
	
	static Logger logger = Logger.getLogger(LignePanierDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public LignePanierDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from LignePanier a";


	public void persist(LignePanier transientInstance) {
		System.out.println("persisting LignePanier instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(LignePanier persistentInstance) {
		System.out.println("removing LignePanier instance");
		try {
			LignePanier e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public LignePanier merge(LignePanier detachedInstance) {
		System.out.println("merging LignePanier instance");
		try {
			LignePanier result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public LignePanier findById(int id) {
		System.out.println("getting LignePanier instance with id: " + id);
		try {
			LignePanier instance = entityManager.find(LignePanier.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<LignePanier> findByTypeLignePanier(int idLignePanier) {
		System.out.println("getting LignePanier instance with idLignePanier: " + idLignePanier);
		try {
			//if(!idLignePanier.equals("")){
				Query query = entityManager.createQuery("select f from LignePanier f where f.idLignePanier='"+idLignePanier+"'");
				List<LignePanier> l = query.getResultList();
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
	
	public List<LignePanier> findByRefConstructeur(String refConstructeur, String typePiece) {
		System.out.println("getting LignePanier instance with refConstructeur: " + refConstructeur);
		try {
			if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery("select f from LignePanier f where f.refConstructeur='"+refConstructeur+"' and r.typeDePiece='"+typePiece+"'");
				List<LignePanier> l = query.getResultList();
				LignePanier instance = (LignePanier)query.getSingleResult();
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

	public LignePanier findByCode(String code) {
		System.out.println("getting LignePanier instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from LignePanier f where f.LignePaniername='"+code+"'");
				LignePanier instance = (LignePanier)query.getSingleResult();
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


	public List<LignePanier> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<LignePanier> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<LignePanier> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<LignePanier> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<LignePanier> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<LignePanier> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(LignePanier value) throws Exception {
		BeanValidator<LignePanier> validator = new BeanValidator<LignePanier>(LignePanier.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(LignePanier value, String propertyName) throws Exception {
		BeanValidator<LignePanier> validator = new BeanValidator<LignePanier>(LignePanier.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(LignePanier transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected LignePanier refresh(LignePanier persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
