package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.tiers.dao.IAdherentCarecoDAO;
import fr.legrain.tiers.model.Adherent;
import fr.legrain.validator.common.BeanValidator;

public class AdherentCarecoDAO implements IAdherentCarecoDAO {
	
	static Logger logger = Logger.getLogger(AdherentCarecoDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public AdherentCarecoDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from Adherent a";


	public void persist(Adherent transientInstance) {
		System.out.println("persisting Adherent instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(Adherent persistentInstance) {
		System.out.println("removing Adherent instance");
		try {
			Adherent e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Adherent merge(Adherent detachedInstance) {
		System.out.println("merging Adherent instance");
		try {
			Adherent result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Adherent findById(int id) {
		System.out.println("getting Adherent instance with id: " + id);
		try {
			Adherent instance = entityManager.find(Adherent.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public Adherent findByIdOrigine(Integer idOrigine) {
		System.out.println("getting AdherentCareco instance with idOrigine: " + idOrigine);
		try {
//			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Adherent f where f.idOrigine='"+idOrigine+"'");
				Adherent instance = (Adherent)query.getSingleResult();
				System.out.println("get successful");
				return instance;
//			}
//			return null;
		} catch (NoResultException e) {
			System.out.println("Aucun résultat pour cette requete");
			return null;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<Adherent> findByTypeAdherentCareco(int idAdherentCareco) {
		System.out.println("getting Adherent instance with idAdherentCareco: " + idAdherentCareco);
		try {
			//if(!idAdherentCareco.equals("")){
				Query query = entityManager.createQuery("select f from Adherent f where f.idAdherentCareco='"+idAdherentCareco+"'");
				List<Adherent> l = query.getResultList();
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
	
	public List<Adherent> findByRefConstructeur(String refConstructeur, String typePiece) {
		System.out.println("getting Adherent instance with refConstructeur: " + refConstructeur);
		try {
			if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery("select f from Adherent f where f.refConstructeur='"+refConstructeur+"' and r.typeDePiece='"+typePiece+"'");
				List<Adherent> l = query.getResultList();
				Adherent instance = (Adherent)query.getSingleResult();
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

	public Adherent findByCode(String code) {
		System.out.println("getting Adherent instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Adherent f where f.name='"+code+"'");
				Adherent instance = (Adherent)query.getSingleResult();
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


	public List<Adherent> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<Adherent> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Adherent> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<Adherent> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Adherent> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<Adherent> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<String> selectAll(String debut) {
		System.out.println("getting AdherentCareco instance : " );
		try {
			//if(!idStock.equals("")){
				Query query = null;
				/*
						this.centre = centre;
		this.adherent = adherent;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.telephone = telephone;
		this.telecopie = telecopie;
				 */
				if(debut!=null)
					query = entityManager.createQuery("select f.centre from Adherent f where f.centre like '%"+debut+"%'");
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

	@Override
	public boolean validate(Adherent value) throws Exception {
		BeanValidator<Adherent> validator = new BeanValidator<Adherent>(Adherent.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(Adherent value, String propertyName) throws Exception {
		BeanValidator<Adherent> validator = new BeanValidator<Adherent>(Adherent.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(Adherent transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected AdherentCareco refresh(AdherentCareco persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
