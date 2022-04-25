package fr.legrain.tiers.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import fr.legrain.lib.data.LibDate;
import fr.legrain.tiers.dao.IPanierDAO;
import fr.legrain.tiers.model.Panier;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;
import fr.legrain.validator.common.BeanValidator;

public class PanierDAO implements IPanierDAO {
	
	static Logger logger = Logger.getLogger(PanierDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public PanierDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from Panier a";


	public void persist(Panier transientInstance) {
		System.out.println("persisting Panier instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(Panier persistentInstance) {
		System.out.println("removing Panier instance");
		try {
			Panier e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Panier merge(Panier detachedInstance) {
		System.out.println("merging Panier instance");
		try {
			Panier result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Panier findById(int id) {
		System.out.println("getting Panier instance with id: " + id);
		try {
			Panier instance = entityManager.find(Panier.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<Panier> findByTypePanier(int idPanier) {
		System.out.println("getting Panier instance with idPanier: " + idPanier);
		try {
			//if(!idPanier.equals("")){
				Query query = entityManager.createQuery("select f from Panier f where f.idPanier='"+idPanier+"'");
				List<Panier> l = query.getResultList();
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
	
	public List<Panier> findByRefConstructeur(String refConstructeur, String typePiece) {
		System.out.println("getting Panier instance with refConstructeur: " + refConstructeur);
		try {
			if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery("select f from Panier f where f.refConstructeur='"+refConstructeur+"' and r.typeDePiece='"+typePiece+"'");
				List<Panier> l = query.getResultList();
				Panier instance = (Panier)query.getSingleResult();
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

	public Panier findByCode(String code) {
		System.out.println("getting Panier instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Panier f where f.Paniername='"+code+"'");
				Panier instance = (Panier)query.getSingleResult();
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


	public List<Panier> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<Panier> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Panier> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<Panier> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Panier> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<Panier> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(Panier value) throws Exception {
		BeanValidator<Panier> validator = new BeanValidator<Panier>(Panier.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(Panier value, String propertyName) throws Exception {
		BeanValidator<Panier> validator = new BeanValidator<Panier>(Panier.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(Panier transientInstance) {
		entityManager.detach(transientInstance);
	}
	
	public String typeClient(boolean b2c) {
		if(b2c) {
			return "btoc";
		} else {
			return "btob";
		}
	}
	
	@Override
	public List<Panier> findPanierContientPiece(Stock piece) {
		Date maintenant = new Date();
		try {
			//panier pas encore valider, aucune transaction en cours
			if(piece!=null) {
				System.out.println("getting Panier instance with containing piece : "+ piece.getId());
				Query query = entityManager.createQuery(
						"select p from Panier p where "
								//tous les paniers sans transaction, sans distinction de date
								+ " not exists( select l from p.lignes l where l.transactionAchatVente is not null ) "
								+ " and exists( select l from p.lignes l where l.idPiece.id = :idPiece ) "
								);
				query.setParameter("idPiece", piece.getId());
				List<Panier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			}

		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
		return null;
	}
	
	@Override
	public List<Panier> findPanierActifOuPerime(User u, boolean b2c) {
		
		Date maintenant = new Date();
		try {
			//panier pas encore valider, aucune transaction en cours
			if(u!=null) {
				System.out.println("getting Panier for user : " +u.getId()+" - "+u.getNom());
				Query query = entityManager.createQuery(
						"select p from Panier p where "
								+ " p.client.type = '"+typeClient(b2c)+"'"
								//+ " and p.vendeur.id="+u.getId()+" "
								+ " and p.idEntreprise.id="+u.getUserCompany().getId()+" "
								//tous les paniers sans transaction, sans distinction de date
								+ " and not exists( select l from p.lignes l where l.transactionAchatVente is not null ) "
								+ " order by  p.quandModif desc, p.quanCree desc"
								);
				List<Panier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			}

		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
		return null;
	}

	@Override
	public List<Panier> findPanierActif(User u, boolean b2c) {
		System.out.println("getting Panier instance with idPanier: " );
		Date maintenant = new Date();
		try {
			//panier dont les dates sont valide mais pas encore valider, aucune transaction en cours
			if(u!=null) {
				Query query = entityManager.createQuery(
						"select p from Panier p where "
								+ " p.client.type = '"+typeClient(b2c)+"'"
								//+ " and p.vendeur.id="+u.getId()+" "
								+ " and p.idEntreprise.id="+u.getUserCompany().getId()+" "
								+ " and p.dateFin > :maintenant"
								+ " and not exists( select l from p.lignes l where l.transactionAchatVente is not null ) "
								+ " order by p.quandModif desc, p.quanCree desc"
								);
				query.setParameter("maintenant",maintenant,TemporalType.DATE);
				List<Panier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			}

		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
		return null;
	}

	@Override
	public List<Panier> findPanierPerime(User u, boolean b2c) {
		
		System.out.println("getting Panier instance with idPanier: " );
		Date maintenant = new Date();
		try {
			//panier dont les dates ne sont plus valide valide et non encore valider, aucune transaction en cours
			if(u!=null) {
				Query query = entityManager.createQuery(
						"select p from Panier p where "
								+ " p.client.type = '"+typeClient(b2c)+"'"
								//+ " and p.vendeur.id="+u.getId()+" "
								+ " and p.idEntreprise.id="+u.getUserCompany().getId()+" "
								+ " and p.dateFin < :maintenant"
								+ " and not exists( select l from p.lignes l where l.transactionAchatVente is not null ) "
								+ " order by p.quandModif desc, p.quanCree desc"
								);
				query.setParameter("maintenant",maintenant,TemporalType.DATE);
				List<Panier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			}

		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
		return null;
	}

	@Override
	public List<Panier> findPanierEnCours(User u, boolean b2c) {
			
		System.out.println("getting Panier instance with idPanier: " );
		Date maintenant = new Date();
		try {
			//panier valider, transaction en cours, attente de pièce ou de réponse
			if(u!=null) {
				Query query = entityManager.createQuery(
						"select p from Panier p where "
								+ " p.client.type = '"+typeClient(b2c)+"'"
								//+ " and p.vendeur.id="+u.getId()+" "
								+ " and p.idEntreprise.id="+u.getUserCompany().getId()+" "
								
								//transaction
								+ " and exists( select l from p.lignes l where l.transactionAchatVente is not null  and l.transactionAchatVente.termine<>1) "
								
								//vérfier état de la transaction
								+ " order by p.quandModif desc, p.quanCree desc"
								
								);
				//query.setParameter("maintenant",maintenant,TemporalType.DATE);
				List<Panier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			}

		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
		return null;

	}

	@Override
	public List<Panier> findPanierValider(User u, boolean b2c) {
		
		System.out.println("getting Panier instance with idPanier: " );
		Date maintenant = new Date();
		try {
			//panier valider, plus de transaction en attente
			if(u!=null) {
			Query query = entityManager.createQuery(
					"select p from Panier p where "
							+ " p.client.type = '"+typeClient(b2c)+"'"
							//+ " and p.vendeur.id="+u.getId()+" "
							+ " and p.idEntreprise.id="+u.getUserCompany().getId()+" "
							
							//transaction
							+ " and exists( "
							+ " select l from p.lignes l where l.transactionAchatVente is not null  "
							+ " and l.transactionAchatVente.termine<>1"
							+ " and l.transactionAchatVente.etatVendeur<>'nouveau'"
							+ " ) "
							
							//vérfier état de la transaction
							+ " order by p.quandModif desc, p.quanCree desc"
							
							);
				//query.setParameter("maintenant",maintenant,TemporalType.DATE);
				List<Panier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			}

		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
		return null;
	}

	@Override
	public List<Panier> findPanierHistorique(User u, boolean b2c) {
	
		System.out.println("getting Panier instance with idPanier: " );
		Date maintenant = new Date();
		try {
			//panier valider, plus ancien
			if(u!=null) {
			Query query = entityManager.createQuery(
					"select p from Panier p where "
							+ " p.client.type = '"+typeClient(b2c)+"'"
							//+ " and p.vendeur.id="+u.getId()+" "
							+ " and p.idEntreprise.id="+u.getUserCompany().getId()+" "
							
							//transaction
							+ " and exists( select l from p.lignes l where l.transactionAchatVente is not null  and l.transactionAchatVente.termine<>0) "
							
							//vérfier état de la transaction
							
							+ " order by p.quandModif desc, p.quanCree desc"
							
							);
				//query.setParameter("maintenant",maintenant,TemporalType.DATE);
				List<Panier> l = query.getResultList();
				System.out.println("get successful");
				return l;

			}
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
		return null;
	}


}
