package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.tiers.dao.ISocietesAmiesDAO;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.validator.common.BeanValidator;

public class SocietesAmiesDAO implements ISocietesAmiesDAO {
	
	static Logger logger = Logger.getLogger(SocietesAmiesDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public SocietesAmiesDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from SocietesAmies a";


	public void persist(SocietesAmies transientInstance) {
		System.out.println("persisting SocietesAmies instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(SocietesAmies persistentInstance) {
		System.out.println("removing SocietesAmies instance");
		try {
			SocietesAmies e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public SocietesAmies merge(SocietesAmies detachedInstance) {
		System.out.println("merging SocietesAmies instance");
		try {
			SocietesAmies result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public SocietesAmies findById(int id) {
		System.out.println("getting SocietesAmies instance with id: " + id);
		try {
			SocietesAmies instance = entityManager.find(SocietesAmies.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public SocietesAmies findByIdOrigine(Integer idOrigine) {
		System.out.println("getting AdherentCareco instance with idOrigine: " + idOrigine);
		try {
//			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from SocietesAmies f where f.idOrigine='"+idOrigine+"'");
				SocietesAmies instance = (SocietesAmies)query.getSingleResult();
				System.out.println("get successful");
				return instance;
//			}
//			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<SocietesAmies> findSocieteAmie(int idSocietesAmies) {
		System.out.println("getting SocietesAmies instance with idSocietesAmies: " + idSocietesAmies);
		try {
			//if(!idSocietesAmies.equals("")){
				Query query = entityManager.createQuery("select f from SocietesAmies f where f.societeA.id= :idSocietesAmies and f.typeRelation = :typeRelation");
				query.setParameter("idSocietesAmies", idSocietesAmies);
				query.setParameter("typeRelation", IUserCompanyServiceRemote.TYPE_SOCIETE_AMIE_AMIE);
				List<SocietesAmies> l = query.getResultList();
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
	
	public List<SocietesAmies> findSocieteAmieAjoutee(int idSocietesAmies) {
		System.out.println("getting SocietesAmies instance with idSocietesAmies: " + idSocietesAmies);
		try {
			//if(!idSocietesAmies.equals("")){
				Query query = entityManager.createQuery("select f from SocietesAmies f where f.societeB.id= :idSocietesAmies and f.typeRelation = :typeRelation");
				query.setParameter("idSocietesAmies", idSocietesAmies);
				query.setParameter("typeRelation", IUserCompanyServiceRemote.TYPE_SOCIETE_AMIE_AMIE);
				List<SocietesAmies> l = query.getResultList();
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
	
	public List<SocietesAmies> findSocieteMultiSite(int idSocietesAmies) {
		System.out.println("getting SocietesAmies instance with idSocietesAmies: " + idSocietesAmies);
		try {
			//if(!idSocietesAmies.equals("")){
				Query query = entityManager.createQuery("select f from SocietesAmies f where f.societeA.id= :idSocietesAmies and f.typeRelation = :typeRelation");
				query.setParameter("idSocietesAmies", idSocietesAmies);
				query.setParameter("typeRelation", IUserCompanyServiceRemote.TYPE_SOCIETE_AMIE_MULTISITE);
				List<SocietesAmies> l = query.getResultList();
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

	public SocietesAmies findByCode(String code) {
		System.out.println("getting SocietesAmies instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from SocietesAmies f where f.nom='"+code+"'");
				SocietesAmies instance = (SocietesAmies)query.getSingleResult();
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


	public List<SocietesAmies> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<SocietesAmies> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<SocietesAmies> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<SocietesAmies> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<SocietesAmies> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<SocietesAmies> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(SocietesAmies value) throws Exception {
		BeanValidator<SocietesAmies> validator = new BeanValidator<SocietesAmies>(SocietesAmies.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(SocietesAmies value, String propertyName) throws Exception {
		BeanValidator<SocietesAmies> validator = new BeanValidator<SocietesAmies>(SocietesAmies.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(SocietesAmies transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected SocietesAmies refresh(SocietesAmies persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
