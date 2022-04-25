package fr.legrain.tiers.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.bdg.tiers.service.remote.ITransactionAchatVenteServiceRemote;
import fr.legrain.tiers.dao.ITransactionAchatVenteDAO;
import fr.legrain.tiers.model.LignePanier;
import fr.legrain.tiers.model.TransactionAchatVente;
import fr.legrain.tiers.model.User;
import fr.legrain.validator.common.BeanValidator;

public class TransactionAchatVenteDAO implements ITransactionAchatVenteDAO {
	
	static Logger logger = Logger.getLogger(TransactionAchatVenteDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public TransactionAchatVenteDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from TransactionAchatVente a";


	public void persist(TransactionAchatVente transientInstance) {
		System.out.println("persisting TransactionAchatVente instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(TransactionAchatVente persistentInstance) {
		System.out.println("removing TransactionAchatVente instance");
		try {
			TransactionAchatVente e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public TransactionAchatVente merge(TransactionAchatVente detachedInstance) {
		System.out.println("merging TransactionAchatVente instance");
		try {
			TransactionAchatVente result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public TransactionAchatVente findById(int id) {
		System.out.println("getting TransactionAchatVente instance with id: " + id);
		try {
			TransactionAchatVente instance = entityManager.find(TransactionAchatVente.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<TransactionAchatVente> findByTypeTransactionAchatVente(int idTransactionAchatVente) {
		System.out.println("getting TransactionAchatVente instance with idTransactionAchatVente: " + idTransactionAchatVente);
		try {
			//if(!idTransactionAchatVente.equals("")){
				Query query = entityManager.createQuery("select f from TransactionAchatVente f where f.idTransactionAchatVente='"+idTransactionAchatVente+"'");
				List<TransactionAchatVente> l = query.getResultList();
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
	
	public List<TransactionAchatVente> findByRefConstructeur(String refConstructeur, String typePiece) {
		System.out.println("getting TransactionAchatVente instance with refConstructeur: " + refConstructeur);
		try {
			if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery("select f from TransactionAchatVente f where f.refConstructeur='"+refConstructeur+"' and r.typeDePiece='"+typePiece+"'");
				List<TransactionAchatVente> l = query.getResultList();
				TransactionAchatVente instance = (TransactionAchatVente)query.getSingleResult();
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

	public TransactionAchatVente findByCode(String code) {
		System.out.println("getting TransactionAchatVente instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from TransactionAchatVente f where f.TransactionAchatVentename='"+code+"'");
				TransactionAchatVente instance = (TransactionAchatVente)query.getSingleResult();
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


	public List<TransactionAchatVente> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TransactionAchatVente> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<TransactionAchatVente> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<TransactionAchatVente> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<TransactionAchatVente> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<TransactionAchatVente> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(TransactionAchatVente value) throws Exception {
		BeanValidator<TransactionAchatVente> validator = new BeanValidator<TransactionAchatVente>(TransactionAchatVente.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(TransactionAchatVente value, String propertyName) throws Exception {
		BeanValidator<TransactionAchatVente> validator = new BeanValidator<TransactionAchatVente>(TransactionAchatVente.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(TransactionAchatVente transientInstance) {
		entityManager.detach(transientInstance);
	}

	@Override
	public List<LignePanier> findUserAchatEnCours(User user) {
		System.out.println("getting TransactionAchatVente instance : " );
		String etat = "1";
		try {
			
			List<String> listeEtatVendeur = new ArrayList<String>();
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_NOUVEAU);
			
			List<String> listeEtatAcheteur = new ArrayList<String>();
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_NOUVEAU);
			
			//if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery(
						"select l from LignePanier l "
						+ "join l.panier p "
						+ "join p.vendeur v "
						+ "join l.transactionAchatVente tx "
						+ "where "
						//+ "v.id='"+user.getId()+"' "
						+ " v.userCompany.id="+user.getUserCompany().getId()+" "
						+ "and tx.termine<>'"+etat+"' "
						+ "and tx.etatVendeur not in :etatVendeur and tx.etatAcheteur not in :etatAcheteur");
				
				query.setParameter("etatVendeur", listeEtatVendeur);
				query.setParameter("etatAcheteur", listeEtatAcheteur);
				List<LignePanier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			//}
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<LignePanier> findUserAchatHistorique(User user) {
		System.out.println("getting TransactionAchatVente instance : " );
		String etat = "1";
		try {
			
			List<String> listeEtatVendeur = new ArrayList<String>();
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_NOUVEAU);
			
			List<String> listeEtatAcheteur = new ArrayList<String>();
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_NOUVEAU);
			
			//if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery(
						"select l from LignePanier l "
						+ "join l.panier p "
						+ "join p.vendeur v "
						+ "join l.transactionAchatVente tx "
						+ "where "
						//+ "v.id='"+user.getId()+"' "
						+ " v.userCompany.id="+user.getUserCompany().getId()+" "
						+ " and tx.termine = '"+etat+"' "
						+ "and tx.etatVendeur not in :etatVendeur and tx.etatAcheteur not in :etatAcheteur");
				
				query.setParameter("etatVendeur", listeEtatVendeur);
				query.setParameter("etatAcheteur", listeEtatAcheteur);
				
				List<LignePanier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			//}
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<LignePanier> findUserVentesEnCours(User user) {
		System.out.println("getting TransactionAchatVente instance: " );
		String etat = "1";
		try {
			List<String> listeEtatVendeur = new ArrayList<String>();
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_NOUVEAU);
			
			List<String> listeEtatAcheteur = new ArrayList<String>();
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_NOUVEAU);
			//if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery(
						"select l from LignePanier l "
						+ "join l.panier p "
						+ "join l.idPiece stock "
						+ "join stock.idStockOrigine origine "	
						+ "join l.transactionAchatVente tx "
						+ "where origine.id='"+user.getUserCompany().getId()+"' and tx.termine<>'"+etat+"' "
						+ "and tx.etatVendeur not in :etatVendeur and tx.etatAcheteur not in :etatAcheteur");
				
				query.setParameter("etatVendeur", listeEtatVendeur);
				query.setParameter("etatAcheteur", listeEtatAcheteur);
				
				List<LignePanier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			//}
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	@Override
	public List<LignePanier> findUserVentesLocaleEnCours(User user) {
		System.out.println("getting TransactionAchatVente instance : " );
		String etat = "1";
		try {
			List<String> listeEtatVendeur = new ArrayList<String>();
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_NOUVEAU);
			
			List<String> listeEtatAcheteur = new ArrayList<String>();
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_NOUVEAU);
			
			//if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery(
						"select l from LignePanier l "
						+ "join l.panier p "
						+ "join l.idPiece stock "
						+ "join stock.idStockOrigine origine "	
						+ "join l.transactionAchatVente tx "
						+ "where origine.id='"+user.getUserCompany().getId()+"' and tx.termine<>'"+etat+"' "
						+ "and tx.etatVendeur in :etatVendeur and tx.etatAcheteur in :etatAcheteur");
				
				query.setParameter("etatVendeur", listeEtatVendeur);
				query.setParameter("etatAcheteur", listeEtatAcheteur);
				
				List<LignePanier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			//}
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}

	}

	@Override
	public List<LignePanier> findUserVentesValider(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LignePanier> findUserVentesHistorique(User user) {
		System.out.println("getting TransactionAchatVente instance with : " );
		String etat = "1";
		try {
			List<String> listeEtatVendeur = new ArrayList<String>();
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
			
			List<String> listeEtatAcheteur = new ArrayList<String>();
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
			
			//if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery(
						"select l from LignePanier l "
						+ "join l.panier p "
						+ "join l.idPiece stock "
						+ "join stock.idStockOrigine origine "	
						+ "join l.transactionAchatVente tx "
						+ "where origine.id='"+user.getUserCompany().getId()+"' and tx.termine='"+etat+"' "
						+ "and tx.etatVendeur not in :etatVendeur and tx.etatAcheteur not in :etatAcheteur");
				
				query.setParameter("etatVendeur", listeEtatVendeur);
				query.setParameter("etatAcheteur", listeEtatAcheteur);
				
				List<LignePanier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			//}
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	@Override
	public List<LignePanier> findUserVentesLocaleHistorique(User user) {
		System.out.println("getting TransactionAchatVente instance with : " );
		String etat = "1";
		try {
			
			List<String> listeEtatVendeur = new ArrayList<String>();
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
			listeEtatVendeur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
			
			List<String> listeEtatAcheteur = new ArrayList<String>();
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_ANNULEE);
			listeEtatAcheteur.add(ITransactionAchatVenteServiceRemote.TRANSACTION_LOCALE_CONFIRMEE);
			
			//if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery(
						"select l from LignePanier l "
						+ "join l.panier p "
						+ "join l.idPiece stock "
						+ "join stock.idStockOrigine origine "	
						+ "join l.transactionAchatVente tx "
						+ "where origine.id='"+user.getUserCompany().getId()+"' and tx.termine='"+etat+"' "
						+ "and tx.etatVendeur in :etatVendeur and tx.etatAcheteur in :etatAcheteur");
				
				query.setParameter("etatVendeur", listeEtatVendeur);
				query.setParameter("etatAcheteur", listeEtatAcheteur);
				
				List<LignePanier> l = query.getResultList();
				System.out.println("get successful");
				return l;
			//}
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

}
