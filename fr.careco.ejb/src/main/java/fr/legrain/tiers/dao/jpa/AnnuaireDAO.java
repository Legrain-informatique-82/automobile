package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IAnnuaireDAO;
import fr.legrain.tiers.model.Annuaire;
import fr.legrain.validator.common.BeanValidator;

public class AnnuaireDAO implements IAnnuaireDAO {
	
	static Logger logger = Logger.getLogger(AnnuaireDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public AnnuaireDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from Annuaire a";


	public void persist(Annuaire transientInstance) {
		System.out.println("persisting Annuaire instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(Annuaire persistentInstance) {
		System.out.println("removing Annuaire instance");
		try {
			Annuaire e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Annuaire merge(Annuaire detachedInstance) {
		System.out.println("merging Annuaire instance");
		try {
			Annuaire result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Annuaire findById(int id) {
		System.out.println("getting Annuaire instance with id: " + id);
		try {
			Annuaire instance = entityManager.find(Annuaire.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<Annuaire> findByTypeAnnuaire(int idAnnuaire) {
		System.out.println("getting Annuaire instance with idAnnuaire: " + idAnnuaire);
		try {
			//if(!idAnnuaire.equals("")){
				Query query = entityManager.createQuery("select f from Annuaire f where f.idAnnuaire='"+idAnnuaire+"'");
				List<Annuaire> l = query.getResultList();
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
	
	public List<Annuaire> findByRefConstructeur(String refConstructeur, String typePiece) {
		System.out.println("getting Annuaire instance with refConstructeur: " + refConstructeur);
		try {
			if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery("select f from Annuaire f where f.refConstructeur='"+refConstructeur+"' and r.typeDePiece='"+typePiece+"'");
				List<Annuaire> l = query.getResultList();
				Annuaire instance = (Annuaire)query.getSingleResult();
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

	public Annuaire findByCode(String code) {
		System.out.println("getting Annuaire instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Annuaire f where f.Annuairename='"+code+"'");
				Annuaire instance = (Annuaire)query.getSingleResult();
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


	public List<Annuaire> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<Annuaire> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Annuaire> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<Annuaire> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Annuaire> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<Annuaire> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<String> selectAll(String debut) {
		System.out.println("getting Annuaire instance : " );
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
//				if(debut!=null)
//					query = entityManager.createQuery("select f.centre from Annuaire f where f.centre like '%"+debut+"%'");
//				else
//					query = entityManager.createQuery("select f.centre from Stock f ");
				
				if(debut!=null)
					query = entityManager.createQuery("select f.centre||' (Tel: '||f.telephone||')'||' (Fax: '||f.telecopie||')'||' (Adresse: '||f.adresse||' '||f.cp||' '||f.ville||')' from Annuaire f where f.centre like '%"+debut+"%'");
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
	public boolean validate(Annuaire value) throws Exception {
		BeanValidator<Annuaire> validator = new BeanValidator<Annuaire>(Annuaire.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(Annuaire value, String propertyName) throws Exception {
		BeanValidator<Annuaire> validator = new BeanValidator<Annuaire>(Annuaire.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(Annuaire transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected Annuaire refresh(Annuaire persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
