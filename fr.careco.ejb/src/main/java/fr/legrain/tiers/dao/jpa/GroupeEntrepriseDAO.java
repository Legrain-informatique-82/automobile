package fr.legrain.tiers.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.tiers.dao.IGroupeEntrepriseDAO;
import fr.legrain.tiers.model.GroupeEntreprise;
import fr.legrain.validator.common.BeanValidator;

public class GroupeEntrepriseDAO implements IGroupeEntrepriseDAO {
	
	static Logger logger = Logger.getLogger(GroupeEntrepriseDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public GroupeEntrepriseDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from GroupeEntreprise a";


	public void persist(GroupeEntreprise transientInstance) {
		System.out.println("persisting GroupeEntreprise instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(GroupeEntreprise persistentInstance) {
		System.out.println("removing GroupeEntreprise instance");
		try {
			GroupeEntreprise e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public GroupeEntreprise merge(GroupeEntreprise detachedInstance) {
		System.out.println("merging GroupeEntreprise instance");
		try {
			GroupeEntreprise result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public GroupeEntreprise findById(int id) {
		System.out.println("getting GroupeEntreprise instance with id: " + id);
		try {
			GroupeEntreprise instance = entityManager.find(GroupeEntreprise.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<GroupeEntreprise> findByTypeGroupeEntreprise(int idGroupeEntreprise) {
		System.out.println("getting GroupeEntreprise instance with idGroupeEntreprise: " + idGroupeEntreprise);
		try {
			//if(!idGroupeEntreprise.equals("")){
				Query query = entityManager.createQuery("select f from GroupeEntreprise f where f.idGroupeEntreprise='"+idGroupeEntreprise+"'");
				List<GroupeEntreprise> l = query.getResultList();
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
	
	public List<GroupeEntreprise> findByRefConstructeur(String refConstructeur, String typePiece) {
		System.out.println("getting GroupeEntreprise instance with refConstructeur: " + refConstructeur);
		try {
			if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery("select f from GroupeEntreprise f where f.refConstructeur='"+refConstructeur+"' and r.typeDePiece='"+typePiece+"'");
				List<GroupeEntreprise> l = query.getResultList();
				GroupeEntreprise instance = (GroupeEntreprise)query.getSingleResult();
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

	public GroupeEntreprise findByCode(String code) {
		System.out.println("getting GroupeEntreprise instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from GroupeEntreprise f where f.GroupeEntreprisename='"+code+"'");
				GroupeEntreprise instance = (GroupeEntreprise)query.getSingleResult();
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


	public List<GroupeEntreprise> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<GroupeEntreprise> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<GroupeEntreprise> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<GroupeEntreprise> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<GroupeEntreprise> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<GroupeEntreprise> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<String> selectAll(String debut) {
		System.out.println("getting GroupeEntreprise instance : " );
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
					query = entityManager.createQuery("select f.centre from GroupeEntreprise f where f.centre like '%"+debut+"%'");
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
	public boolean validate(GroupeEntreprise value) throws Exception {
		BeanValidator<GroupeEntreprise> validator = new BeanValidator<GroupeEntreprise>(GroupeEntreprise.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(GroupeEntreprise value, String propertyName) throws Exception {
		BeanValidator<GroupeEntreprise> validator = new BeanValidator<GroupeEntreprise>(GroupeEntreprise.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(GroupeEntreprise transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected GroupeEntreprise refresh(GroupeEntreprise persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
