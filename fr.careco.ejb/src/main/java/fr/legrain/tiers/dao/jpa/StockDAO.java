package fr.legrain.tiers.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.tiers.dao.IStockDAO;
import fr.legrain.tiers.model.Adherent;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;
import fr.legrain.validator.common.BeanValidator;

public class StockDAO implements IStockDAO {
	
	static Logger logger = Logger.getLogger(StockDAO.class);

	@PersistenceContext(unitName = "careco")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public StockDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from Stock a";


	public void persist(Stock transientInstance) {
		System.out.println("persisting Stock instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(Stock persistentInstance) {
		System.out.println("removing Stock instance");
		try {
			Stock e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Stock merge(Stock detachedInstance) {
		System.out.println("merging Stock instance");
		try {
			Stock result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}

	public Stock findById(int id) {
		System.out.println("getting Stock instance with id: " + id);
		try {
			Stock instance = entityManager.find(Stock.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	private List<String> stockRechercheDefaut() {
		ArrayList<String> l = new ArrayList<String>();
		l.add(IStockServiceRemote.STATUS_PIECE_DISPONIBLE);
		l.add(IStockServiceRemote.STATUS_PIECE_PANIER);
		return l;
	}
	
	public List<Stock> findDuplicate(int idStock) {
		System.out.println("getting duplciate Stock instance with idStock : "+idStock);
		try {
			Query query = entityManager.createQuery("select s1 from Stock s1 where s1.idStock='"+idStock+"' "
					+ " and exists ("
					+ "select s2 from Stock s2 where "
					+ "s1.id <> s2.id"
					+ " and s1.idStock = s2.idStock"
					+ " and s1.idStock = s2.idStock"
					+ " and s1.idStockOrigine = s2.idStockOrigine"
					+ " and s1.typeDePiece = s2.typeDePiece"
					+ " and s1.numLivrePolice = s2.numLivrePolice"
					+ " and s1.immatriculation = s2.immatriculation"
					+ " and s1.refConstructeur = s2.refConstructeur"
					+ " and s1.CNITTypeMine = s2.CNITTypeMine"
					+ " and s1.emplacementCasier = s2.emplacementCasier"
					+ " and s1.dateAchat = s2.dateAchat"
					+ " and s1.status = s2.status and s1.status='"+IStockServiceRemote.STATUS_PIECE_DISPONIBLE+"'"
					+ " and s1.vin = s2.vin"
					+ " and s1.kms = s2.kms"
					+ ") order by s1.nogo"
					);

			List<Stock> l = query.getResultList();
			System.out.println("get successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	/**
	 * tout le stock pour une entreprise
	 */
	public List<Stock> findByUserStock(int idStock) {
		return findByUserStock(idStock,stockRechercheDefaut());
	}
	
	/**
	 * tout le stock pour une entreprise
	 */
	public List<Stock> findByUserStock(int idStock, List<String> listeStatusPiece) {
		System.out.println("getting Stock instance with idStock: " + idStock);
		try {
			//if(!idStock.equals("")){
				Query query = entityManager.createQuery("select f from Stock f where f.idStock='"+idStock+"' and f.status in :statusInClause");
				query.setParameter("statusInClause", listeStatusPiece);
				List<Stock> l = query.getResultList();
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
	
	public List<Stock> findByNumLivrePolice(int idStock, String numLivrePolice) {
		System.out.println("getting Stock instance with numLivrePolice: " + numLivrePolice);
		try {
			//if(!idStock.equals("")){
				Query query = entityManager.createQuery("select f from Stock f where f.idStock='"+idStock+"' and f.numLivrePolice='"+numLivrePolice+"' and f.status in :statusInClause");
				query.setParameter("statusInClause", stockRechercheDefaut());
				List<Stock> l = query.getResultList();
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
	
	public List<Stock> findByVin(String vin) {
		System.out.println("getting Stock instance with vin: " + vin);
		try {
			//if(!idStock.equals("")){
				Query query = entityManager.createQuery("select f from Stock f where f.vin='"+vin+"' and f.status in :statusInClause");
				query.setParameter("statusInClause", stockRechercheDefaut());
				List<Stock> l = query.getResultList();
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
	
	public List<Stock> findByCNIT(String cnit) {
		System.out.println("getting Stock instance with cnit: " + cnit);
		try {
			//if(!idStock.equals("")){
				Query query = entityManager.createQuery("select f from Stock f where f.CNITTypeMine='"+cnit+"' and f.status in :statusInClause");
				query.setParameter("statusInClause", stockRechercheDefaut());
				List<Stock> l = query.getResultList();
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
	
	public List<Stock> findByUserEtCasier(int idStock, String casier) {
		System.out.println("getting Stock instance with casier: " + casier+" idStock : "+idStock);
		try {
			//if(!casier.equals("")){
				Query query = entityManager.createQuery("select f from Stock f where f.emplacementCasier='"+casier+"' and f.idStock='"+idStock+"' and f.status in :statusInClause");
				query.setParameter("statusInClause", stockRechercheDefaut());
				List<Stock> l = query.getResultList();
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
	
	/**
	 * Stock pour une entreprise pour une pièce
	 */
	public List<Stock> findByUserEtPieceStock(int idStock, String refConstructeur, String typePiece) {
		System.out.println("getting Stock instance with idStock: " + idStock);
		try {
			//if(!idStock.equals("")){
				String jpql = "select f from Stock f where f.idStock='"+idStock+"' "
						+ " and f.refConstructeur='"+refConstructeur+"' and f.status in :statusInClause";
				if(typePiece!=null && !typePiece.equals("")) {
					jpql+=" and f.typeDePiece='"+typePiece+"'";
				}
				Query query = entityManager.createQuery(jpql);
				query.setParameter("statusInClause", stockRechercheDefaut());
				List<Stock> l = query.getResultList();
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
	
	public List<Stock> findByMultiCompanyEtPieceStock(List<Integer> idStock, String refConstructeur, String typePiece) {
		System.out.println("getting Stock instance with idStock: " + idStock);
		try {
			//if(!idStock.equals("")){
				String jpql = "select f from Stock f where f.idStock.id in :idStock "
						+ " and f.refConstructeur='"+refConstructeur+"' and f.status in :statusInClause";
				if(typePiece!=null && !typePiece.equals("")) {
					jpql+=" and f.typeDePiece='"+typePiece+"'";
				}
				Query query = entityManager.createQuery(jpql);
				query.setParameter("idStock", idStock);
				query.setParameter("statusInClause", stockRechercheDefaut());
				List<Stock> l = query.getResultList();
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
	
	/**
	 * 
	 */
	public List<Stock> findByUserEtPieceStock(int idStock, List<String> refsConstructeur, String typePiece) {
		System.out.println("getting Stock instance with idStock: " + idStock+" ref: "+refsConstructeur+" type: "+typePiece);
		try {
			//if(!idStock.equals("")){
//			String jpql = "select f from Stock f where f.idStock='"+idStock+"' "
//						+ " and f.refConstructeur in :inClause and f.status in :statusInClause";
//			if(typePiece!=null && !typePiece.equals("")) {
//				jpql+=" and f.typeDePiece='"+typePiece+"'";
//			}
//			
//			Query query = entityManager.createQuery(jpql);
//			query.setParameter("statusInClause", stockRechercheDefaut());
//
//				query.setParameter("inClause", refsConstructeur);
			
			Query query = null;
			if(typePiece!=null && !typePiece.equals("")) {
				query = entityManager.createNamedQuery(Stock.QN.FIND_BY_USER_ET_PIECESTOCK_TYPEPIECE);
				query.setParameter("typePiece", typePiece);
			} else {
				query = entityManager.createNamedQuery(Stock.QN.FIND_BY_USER_ET_PIECESTOCK);
			}
			query.setParameter("idStock", idStock);
			query.setParameter("statusInClause", stockRechercheDefaut());
			query.setParameter("inClause", refsConstructeur);
			
			List<Stock> l = query.getResultList();
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
	
	public List<Stock> findByMultiCompanyEtPieceStock(List<Integer> idStock, List<String> refsConstructeur, String typePiece) {
		System.out.println("getting Stock instance with idStock: " + idStock);
		try {
			Query query = null;
			if(typePiece!=null && !typePiece.equals("")) {
				query = entityManager.createNamedQuery(Stock.QN.FIND_BY_MULTICOMPANY_ET_PIECESTOCK_TYPEPIECE);
				query.setParameter("typePiece", typePiece);
			} else {
				query = entityManager.createNamedQuery(Stock.QN.FIND_BY_MULTICOMPANY_ET_PIECESTOCK);
				//query = entityManager.createNamedQuery(Stock.QN.FIND_BY_MULTICOMPANY_ET_PIECESTOCK_PLUS_COMPAT);
			}
			query.setParameter("idStock", idStock);
			query.setParameter("statusInClause", stockRechercheDefaut());
			query.setParameter("inClause", refsConstructeur);
			
//			List<String> refsCompat = new ArrayList<String>(refsConstructeur);
//			query.setParameter("inClauseCompat", refsCompat);
			
			List<Stock> l = query.getResultList();
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
	
	/**
	 * Stock pour tout un groupe d'entreprise pour une pièce
	 * @param idGroupeEntrepise
	 * @param refConstructeur
	 * @param typePiece
	 * @return
	 */
	public List<Stock> findByGroupeEntrepiseEtPieceStock(int idGroupeEntrepise, String refConstructeur, String typePiece) {
		System.out.println("getting Stock instance with idGroupeEntrepise: " + idGroupeEntrepise);
		try {
			//if(!idStock.equals("")){
				String jpql = "select f from Stock f where f.idStock.idGroupeEntreprise.id='"+idGroupeEntrepise+"' "
					+ " and f.refConstructeur='"+refConstructeur+"' and f.status in :statusInClause";				
				if(typePiece!=null && !typePiece.equals("")) {
					jpql+=" and f.typeDePiece='"+typePiece+"'";
				}
				
				Query query = entityManager.createQuery(jpql);
				query.setParameter("statusInClause", stockRechercheDefaut());
				List<Stock> l = query.getResultList();
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
	
	public List<Stock> findByGroupeEntrepiseEtPieceStock(int idGroupeEntrepise, List<String> refsConstructeur, String typePiece) {
		System.out.println("getting Stock instance with idGroupeEntrepise: " + idGroupeEntrepise);
		try {
			//if(!idStock.equals("")){
				String jpql = "select f from Stock f where f.idStock.idGroupeEntreprise.id='"+idGroupeEntrepise+"' "
						+ " and f.refConstructeur in :inClause and f.status in :statusInClause";
				if(typePiece!=null && !typePiece.equals("")) {
					jpql+=" and f.typeDePiece='"+typePiece+"'";
				}
				
				Query query = entityManager.createQuery(jpql);
				query.setParameter("statusInClause", stockRechercheDefaut());
				query.setParameter("inClause", refsConstructeur);
				List<Stock> l = query.getResultList();
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
	
	/**
	 * Stock pour une pièce quelque soit son emplacement
	 */
	public List<Stock> findByRefConstructeur(String refConstructeur, String typePiece) {
		System.out.println("getting Stock instance with refConstructeur: " + refConstructeur);
		try {
			if(!refConstructeur.equals("")){
				Query query = entityManager.createQuery("select f from Stock f where f.refConstructeur='"+refConstructeur+"' and f.typeDePiece='"+typePiece+"' and f.status in :statusInClause");
				query.setParameter("statusInClause", stockRechercheDefaut());
				List<Stock> l = query.getResultList();
				Stock instance = (Stock)query.getSingleResult();
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
	
	public List<String> selectAllCNIT(int idGroupeEntrepise, int idStock, String debut) {
		System.out.println("getting Stock instance with idGroupeEntrepise: " + idGroupeEntrepise);
		try {
			//if(!idStock.equals("")){
				Query query = null;
				if(debut!=null)
					query = entityManager.createQuery("select distinct f.CNITTypeMine from Stock f where f.idStock.idGroupeEntreprise.id='"+idGroupeEntrepise+"' and f.CNITTypeMine like '"+debut+"%' and f.status in :statusInClause");
				else
					query = entityManager.createQuery("select distinct f.CNITTypeMine from Stock f where f.idStock.idGroupeEntreprise.id='"+idGroupeEntrepise+"' and f.status in :statusInClause");
				query.setParameter("statusInClause", stockRechercheDefaut());
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
		
	public List<String> selectAllVIN(int idGroupeEntrepise, int idStock, String debut) {
		System.out.println("getting Stock instance with idGroupeEntrepise: " + idGroupeEntrepise);
		try {
			//if(!idStock.equals("")){
				Query query = null;
				if(debut!=null)
					query = entityManager.createQuery("select f.vin from Stock f where f.idStock.idGroupeEntreprise.id='"+idGroupeEntrepise+"' and f.vin like '"+debut+"%' and f.status in :statusInClause");
				else
					query = entityManager.createQuery("select f.vin from Stock f where f.idStock.idGroupeEntreprise.id='"+idGroupeEntrepise+"' and f.status in :statusInClause");
				query.setParameter("statusInClause", stockRechercheDefaut());
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
	
	public List<String> selectAllLivrePolice(int idStock, String debut) {
		System.out.println("getting Stock instance with idGroupeEntrepise: " + idStock);
		try {
			//if(!idStock.equals("")){
				Query query = null;
				if(debut!=null)
					query = entityManager.createQuery("select distinct f.numLivrePolice from Stock f where f.idStock.id='"+idStock+"' and f.numLivrePolice like '"+debut+"%' and f.status in :statusInClause");
				else
					query = entityManager.createQuery("select distinct f.numLivrePolice from Stock f where f.idStock.id='"+idStock+"' and f.status in :statusInClause");
				query.setParameter("statusInClause", stockRechercheDefaut());
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
	
	public List<String> selectAllTypeMoteur(int idStock, String debut) {
		System.out.println("getting Stock instance with idGroupeEntrepise: " );
		try {
			//if(!idStock.equals("")){
				Query query = null;
				if(debut!=null)
					query = entityManager.createQuery("select distinct f.refConstructeur from Stock f where f.idStock.id='"+idStock+"' and f.typeDePiece='Moteur' and f.refConstructeur like '"+debut+"%' and f.status in :statusInClause");
				else
					query = entityManager.createQuery("select distinct f.refConstructeur from Stock f where f.idStock.id='"+idStock+"' and f.typeDePiece='Moteur' and f.status in :statusInClause");
//				if(debut!=null)
//					query = entityManager.createQuery("select distinct f.refConstructeur from Stock f where f.refConstructeur like '"+debut+"%'");
//				else
//					query = entityManager.createQuery("select distinct f.refConstructeur from Stock f where f.idStock.id='"+idStock+"' and f.typeDePiece='Moteur'");
				query.setParameter("statusInClause", stockRechercheDefaut());
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
	
	public List<String> selectAllTypeBoite(int idStock, String debut) {
		System.out.println("getting Stock instance with idGroupeEntrepise: " );
		try {
			//if(!idStock.equals("")){
				Query query = null;
				if(debut!=null)
					query = entityManager.createQuery("select distinct f.refConstructeur from Stock f where f.idStock.id='"+idStock+"' and f.typeDePiece='Boite' and f.refConstructeur like '"+debut+"%' and f.status in :statusInClause");
				else
					query = entityManager.createQuery("select distinct f.refConstructeur from Stock f where f.idStock.id='"+idStock+"' and f.typeDePiece='Boite' and f.status in :statusInClause");
//				if(debut!=null)
//					query = entityManager.createQuery("select distinct f.refConstructeur from Stock f where f.refConstructeur like '"+debut+"%'");
//				else
//					query = entityManager.createQuery("select distinct f.refConstructeur from Stock f where f.idStock.id='"+idStock+"' and f.typeDePiece='Boite'");
				query.setParameter("statusInClause", stockRechercheDefaut());
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
	
	public List<String> selectAllCasier(int idStock, String debut) {
		System.out.println("getting Stock instance with idGroupeEntrepise: ");
		try {
			//if(!idStock.equals("")){
				Query query = null;
				if(debut!=null)
					query = entityManager.createQuery("select distinct f.emplacementCasier from Stock f where f.idStock.id='"+idStock+"' and f.emplacementCasier like '"+debut+"%' and f.status in :statusInClause order by f.emplacementCasier");
				else
					query = entityManager.createQuery("select distinct f.emplacementCasier from Stock f where f.idStock.id='"+idStock+"' and f.status in :statusInClause order by f.emplacementCasier");
				query.setParameter("statusInClause", stockRechercheDefaut());
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
	
	public List<Stock> findByMarqueModeleAnnee(int idStock, String typePiece, String marque, String modele, String anneeMin, String anneeMax) {
//		Query query = entityManager.createQuery("select v.Type, v.Date_1er_Cir_Jour || '/' || v.Date_1er_Cir_Mois || '/' || v.Date_1er_Cir_Annee from VehiculeCacheAAA v where v.marque='"+marque+"' and v.Modele='"+modele+"' and v.Date_1er_Cir_Annee as numericis not null and cast(v.Date_1er_Cir_Annee as numeric) > "+anneeMin+" and v.Date_1er_Cir_Annee is not null and cast(v.Date_1er_Cir_Annee as numeric) < "+anneeMax);
//		System.out.println("getting Stock instance with idGroupeEntrepise: ");
//		try {
//			//if(!idStock.equals("")){
//				Query query = null;
//				if(debut!=null)
//					query = entityManager.createQuery("select distinct f.emplacementCasier from Stock f where f.idStock.id='"+idStock+"' and f.emplacementCasier like '"+debut+"%' and f.status in :statusInClause order by f.emplacementCasier");
//				else
//					query = entityManager.createQuery("select distinct f.emplacementCasier from Stock f where f.idStock.id='"+idStock+"' and f.status in :statusInClause order by f.emplacementCasier");
//				query.setParameter("statusInClause", stockRechercheDefaut());
//				List<String> l = query.getResultList();
//				System.out.println("get successful");
//				return l;
//			//}
			return null;
//		} catch (RuntimeException re) {
//			System.out.println("get failed");
//			re.printStackTrace();
//			throw re;
//		}
	}
	
	public Stock findByCode(String code) {
		System.out.println("getting Stock instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Stock f where f.Stockname='"+code+"' and f.status in :statusInClause");
				query.setParameter("statusInClause", stockRechercheDefaut());
				Stock instance = (Stock)query.getSingleResult();
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
	
	public Stock findByIdOrigine(Integer idOrigine) {
		System.out.println("getting Stock instance with idOrigine: " + idOrigine);
		try {
//			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Stock f where f.idOrigine='"+idOrigine+"'");
				query.setParameter("statusInClause", stockRechercheDefaut());
				Stock instance = (Stock)query.getSingleResult();
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
	
	public Stock findByIdOrigine(Integer idOrigine, String nomTableOrigine) {
		System.out.println("getting Stock instance with idOrigine: " + idOrigine+" et nomTableOrigine : "+nomTableOrigine);
		try {
//			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Stock f where f.idOrigine='"+idOrigine+"' and f.tableOrigine='"+nomTableOrigine+"'");
				query.setParameter("statusInClause", stockRechercheDefaut());
				Stock instance = (Stock)query.getSingleResult();
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
	
	public Stock findByIdFichierOrigine(int idStock, Integer idImportFichier, String typeFichierOrigine) {
		System.out.println("getting Stock instance with idImportFichier: " + idImportFichier+" et typeFichierOrigine : "+typeFichierOrigine);
		try {
//			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from Stock f where f.idStock.id='"+idStock+"' and f.idImportFichier='"+idImportFichier+"' and f.importTypeFichier='"+typeFichierOrigine+"'");
				Stock instance = (Stock)query.getSingleResult();
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

	public List<Stock> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<Stock> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Stock> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<Stock> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Stock> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<Stock> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(Stock value) throws Exception {
		BeanValidator<Stock> validator = new BeanValidator<Stock>(Stock.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(Stock value, String propertyName) throws Exception {
		BeanValidator<Stock> validator = new BeanValidator<Stock>(Stock.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(Stock transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected Stock refresh(Stock persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	
	public void deleteByUserStock(int idCompany) {
		System.out.println("delete Stock instance with idCompany " + idCompany);
		try {
				//Query query = entityManager.createQuery("delete from Stock o where o.idStock.id like :idCompany");
			Query query = entityManager.createQuery("delete from Stock o where o.idStock.id = :idCompany and o.idOrigine is not null");
				query.setParameter("idCompany", idCompany);
				int deleted = query.executeUpdate();
				System.out.println("delete successful");
		} catch (RuntimeException re) {
			System.out.println("delete failed");
			re.printStackTrace();
			throw re;
		}
	}

}
