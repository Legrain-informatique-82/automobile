package fr.legrain.careco.aaa.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import fr.legrain.lib.data.LibDate;
import fr.legrain.validator.common.BeanValidator;

public class VehiculeCacheAAADAO implements IVehiculeCacheAAADAO {
	
	static Logger logger = Logger.getLogger(VehiculeCacheAAADAO.class);

	@PersistenceContext(unitName = "careco_AAA")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public VehiculeCacheAAADAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from VehiculeCacheAAA a";


	public void persist(VehiculeCacheAAA transientInstance) {
		System.out.println("persisting VehiculeCacheAAA instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(VehiculeCacheAAA persistentInstance) {
		System.out.println("removing VehiculeCacheAAA instance");
		try {
			VehiculeCacheAAA e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public VehiculeCacheAAA merge(VehiculeCacheAAA detachedInstance) {
		System.out.println("merging VehiculeCacheAAA instance");
		try {
			VehiculeCacheAAA result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public VehiculeCacheAAA findByImmat(String immat) {
		System.out.println("getting VehiculeCacheAAA instance with immat: " + immat);
		try {
			if(!immat.equals("")){
//				Query query = entityManager.createQuery("select f from VehiculeCacheAAA f where f.Immat_SIV='"+immat+"'");
				
				Query query = entityManager.createNamedQuery(VehiculeCacheAAA.QN.FIND_BY_IMMAT);
				query.setParameter("paramImmat", immat);
				
				VehiculeCacheAAA instance = (VehiculeCacheAAA)query.getSingleResult();
				System.out.println("get successful");
				return instance;
			}
			return null;
		} catch (NoResultException e) {
			System.out.println("Aucun résultat pour cette requete");
			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public VehiculeCacheAAA findByVIN(String vin) {
		System.out.println("getting VehiculeCacheAAA instance with vin: " + vin);
		try {
			if(!vin.equals("")){
				//Query query = entityManager.createQuery("select f from VehiculeCacheAAA f where f.Type='"+vin+"'");
				
				Query query = entityManager.createNamedQuery(VehiculeCacheAAA.QN.FIND_BY_VIN);
				query.setParameter("vin", vin);
				
				VehiculeCacheAAA instance = (VehiculeCacheAAA)query.getSingleResult();
				System.out.println("get successful");
				return instance;
			}
			return null;
		} catch (NoResultException e) {
			System.out.println("Aucun résultat pour cette requete");
			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public Map<String, String> rechercheCNITAnnee(String marque, String modele, String anneeMin, String anneeMax) {
		System.out.println("getting rechercheCNITAnnee instance with marque: " + marque);
		try {
			if(!marque.equals("")){
				Query query = entityManager.createQuery("select v.Type, v.Date_1er_Cir_Jour || '/' || v.Date_1er_Cir_Mois || '/' || v.Date_1er_Cir_Annee from VehiculeCacheAAA v where v.marque='"+marque+"' and v.Modele='"+modele+"' and v.Date_1er_Cir_Annee as numericis not null and cast(v.Date_1er_Cir_Annee as numeric) > "+anneeMin+" and v.Date_1er_Cir_Annee is not null and cast(v.Date_1er_Cir_Annee as numeric) < "+anneeMax);
//				String cnit = infosAAA.getType();
//				Date date1ereCirc = LibDate.stringToDate(infosAAA.getDate_1er_Cir_Jour()+"/"+infosAAA.getDate_1er_Cir_Mois()+"/"+infosAAA.getDate_1er_Cir_Annee());
				List<Object> l = query.getResultList();
				System.out.println("get successful");
				return null;
			}
			return null;
		} catch (NoResultException e) {
			System.out.println("Aucun résultat pour cette requete");
			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public VehiculeCacheAAA findById(int id) {
		System.out.println("getting VehiculeCacheAAA instance with id: " + id);
		try {
			VehiculeCacheAAA instance = entityManager.find(VehiculeCacheAAA.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public VehiculeCacheAAA findByCode(String code) {
		System.out.println("getting VehiculeCacheAAA instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from VehiculeCacheAAA f where f.VehiculeCacheAAAname='"+code+"'");
				VehiculeCacheAAA instance = (VehiculeCacheAAA)query.getSingleResult();
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


	public List<VehiculeCacheAAA> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<VehiculeCacheAAA> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public long count() {
		try {
			Query ejbQuery = entityManager.createQuery("select count(a.code_Immat) from VehiculeCacheAAA a");
			long count = (Long) ejbQuery.getSingleResult();
			System.out.println("selectAll successful");
			return count;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<VehiculeCacheAAA> selectAll(int startPosition, int maxResults) {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			ejbQuery.setFirstResult(startPosition);
			ejbQuery.setMaxResults(maxResults);
			List<VehiculeCacheAAA> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<VehiculeCacheAAA> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<VehiculeCacheAAA> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<VehiculeCacheAAA> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<VehiculeCacheAAA> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	public List<String> selectAllMarque() {
		try {
			//Query ejbQuery = entityManager.createQuery("select disctinct(v.marque) from VehiculeCacheAAA v");
			//TypedQuery<String> ejbQuery = entityManager.createQuery("select distinct v.marque  from VehiculeCacheAAA v order by v.marque", String.class);
			TypedQuery<String> ejbQuery = entityManager.createNamedQuery(VehiculeCacheAAA.QN.SELECT_ALL_MARQUE, String.class);
			List<String> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<String> selectAllModele(String marque) {
		try {
			//Query ejbQuery = entityManager.createQuery("select disctinct(v.modele) from VehiculeCacheAAA v");
			TypedQuery<String> ejbQuery = entityManager.createQuery("select distinct v.Modele from VehiculeCacheAAA v where v.marque='"+marque+"' order by v.Modele",String.class);
			List<String> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<String> selectAllAnnee() {
		try {
			//Query ejbQuery = entityManager.createQuery("select disctinct(v.date_1er_Cir_Annee) from VehiculeCacheAAA v");
			TypedQuery<String> ejbQuery = entityManager.createQuery("select distinct v.Date_1er_Cir_Annee from VehiculeCacheAAA v order by v.Date_1er_Cir_Annee",String.class);
			List<String> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(VehiculeCacheAAA value) throws Exception {
		BeanValidator<VehiculeCacheAAA> validator = new BeanValidator<VehiculeCacheAAA>(VehiculeCacheAAA.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(VehiculeCacheAAA value, String propertyName) throws Exception {
		BeanValidator<VehiculeCacheAAA> validator = new BeanValidator<VehiculeCacheAAA>(VehiculeCacheAAA.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(VehiculeCacheAAA transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected VehiculeCacheAAA refresh(VehiculeCacheAAA persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
