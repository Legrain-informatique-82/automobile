package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.tiers.dao.IUserCompanyDAO;
import fr.legrain.tiers.model.Adherent;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.UserCompany;
import fr.legrain.validator.common.BeanValidator;

public class UserCompanyDAO implements IUserCompanyDAO {
	
	static Logger logger = Logger.getLogger(UserCompanyDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public UserCompanyDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from UserCompany a";


	public void persist(UserCompany transientInstance) {
		System.out.println("persisting UserCompany instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(UserCompany persistentInstance) {
		System.out.println("removing UserCompany instance");
		try {
			UserCompany e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public UserCompany merge(UserCompany detachedInstance) {
		System.out.println("merging UserCompany instance");
		try {
			UserCompany result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public UserCompany findById(int id) {
		System.out.println("getting UserCompany instance with id: " + id);
		try {
			UserCompany instance = entityManager.find(UserCompany.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public UserCompany findByIdOrigine(Integer idOrigine) {
		System.out.println("getting AdherentCareco instance with idOrigine: " + idOrigine);
		try {
//			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from UserCompany f where f.idOrigine='"+idOrigine+"'");
				UserCompany instance = (UserCompany)query.getSingleResult();
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
	
	public List<SocietesAmies> findSocieteAmie(int idUserCompany) {
		System.out.println("getting UserCompany instance with idUserCompany: " + idUserCompany);
		try {
			//if(!idUserCompany.equals("")){
				Query query = entityManager.createQuery("select f from SocietesAmies f where f.societeA.id= :idUserCompany and f.typeRelation = :typeRelation");
				query.setParameter("idUserCompany", idUserCompany);
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
	
	public List<SocietesAmies> findSocieteAmieAjoutee(int idUserCompany) {
		System.out.println("getting UserCompany instance with idUserCompany: " + idUserCompany);
		try {
			//if(!idUserCompany.equals("")){
				Query query = entityManager.createQuery("select f from SocietesAmies f where f.societeB.id= :idUserCompany and f.typeRelation = :typeRelation");
				query.setParameter("idUserCompany", idUserCompany);
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
	
	public List<SocietesAmies> findSocieteMultiSite(int idUserCompany) {
		System.out.println("getting UserCompany instance with idUserCompany: " + idUserCompany);
		try {
			//if(!idUserCompany.equals("")){
				Query query = entityManager.createQuery("select f from SocietesAmies f where f.societeA.id= :idUserCompany and f.typeRelation = :typeRelation");
				query.setParameter("idUserCompany", idUserCompany);
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

	public UserCompany findByCode(String code) {
		System.out.println("getting UserCompany instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from UserCompany f where f.nom='"+code+"'");
				UserCompany instance = (UserCompany)query.getSingleResult();
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


	public List<UserCompany> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<UserCompany> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<UserCompany> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<UserCompany> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<UserCompany> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<UserCompany> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(UserCompany value) throws Exception {
		BeanValidator<UserCompany> validator = new BeanValidator<UserCompany>(UserCompany.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(UserCompany value, String propertyName) throws Exception {
		BeanValidator<UserCompany> validator = new BeanValidator<UserCompany>(UserCompany.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(UserCompany transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected UserCompany refresh(UserCompany persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
