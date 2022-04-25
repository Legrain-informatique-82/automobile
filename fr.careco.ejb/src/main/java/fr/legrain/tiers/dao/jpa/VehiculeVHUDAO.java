package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IVehiculeVHUDAO;
import fr.legrain.tiers.model.VehiculeVHU;
import fr.legrain.validator.common.BeanValidator;

public class VehiculeVHUDAO implements IVehiculeVHUDAO {
	
	static Logger logger = Logger.getLogger(VehiculeVHUDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public VehiculeVHUDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from VehiculeVHU a";


	public void persist(VehiculeVHU transientInstance) {
		System.out.println("persisting VehiculeVHU instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(VehiculeVHU persistentInstance) {
		System.out.println("removing VehiculeVHU instance");
		try {
			VehiculeVHU e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public VehiculeVHU merge(VehiculeVHU detachedInstance) {
		System.out.println("merging VehiculeVHU instance");
		try {
			VehiculeVHU result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public VehiculeVHU findById(int id) {
		System.out.println("getting VehiculeVHU instance with id: " + id);
		try {
			VehiculeVHU instance = entityManager.find(VehiculeVHU.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<VehiculeVHU> findByTypeVehiculeVHU(int idVehiculeVHU) {
		System.out.println("getting VehiculeVHU instance with idVehiculeVHU: " + idVehiculeVHU);
		try {
			//if(!idVehiculeVHU.equals("")){
				Query query = entityManager.createQuery("select f from VehiculeVHU f where f.idVehiculeVHU='"+idVehiculeVHU+"'");
				List<VehiculeVHU> l = query.getResultList();
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
	
	public List<VehiculeVHU> findByRefConstructeur(String refConstructeur, String typePiece) {
		System.out.println("getting VehiculeVHU instance with refConstructeur: " + refConstructeur);
		try {
			if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery("select f from VehiculeVHU f where f.refConstructeur='"+refConstructeur+"' and r.typeDePiece='"+typePiece+"'");
				List<VehiculeVHU> l = query.getResultList();
				VehiculeVHU instance = (VehiculeVHU)query.getSingleResult();
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

	public VehiculeVHU findByCode(String code) {
		System.out.println("getting VehiculeVHU instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from VehiculeVHU f where f.VehiculeVHUname='"+code+"'");
				VehiculeVHU instance = (VehiculeVHU)query.getSingleResult();
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


	public List<VehiculeVHU> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<VehiculeVHU> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<VehiculeVHU> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<VehiculeVHU> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<VehiculeVHU> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<VehiculeVHU> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(VehiculeVHU value) throws Exception {
		BeanValidator<VehiculeVHU> validator = new BeanValidator<VehiculeVHU>(VehiculeVHU.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(VehiculeVHU value, String propertyName) throws Exception {
		BeanValidator<VehiculeVHU> validator = new BeanValidator<VehiculeVHU>(VehiculeVHU.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(VehiculeVHU transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected VehiculeVHU refresh(VehiculeVHU persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
