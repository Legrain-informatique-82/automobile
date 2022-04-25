package fr.legrain.careco.dumpmc.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.tiers.model.Stock;
import fr.legrain.validator.common.BeanValidator;

public class GlobalDumpMcDAO implements IGlobalDumpMcDAO {
	
	static Logger logger = Logger.getLogger(GlobalDumpMcDAO.class);

	@PersistenceContext(unitName = "careco_dump_mc")
	private EntityManager entityManager;

	/**
	 * Default constructor. 
	 */
	public GlobalDumpMcDAO() {
		// TODO Auto-generated constructor stub
	}

	private String defaultJPQLQuery = "select a from StockMoteurClub a";


	public void persist(StockMoteurClub transientInstance) {
		System.out.println("persisting StockMoteurClub instance");
		try {
			entityManager.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			re.printStackTrace();
			throw re;
		}
	}

	public void remove(StockMoteurClub persistentInstance) {
		System.out.println("removing StockMoteurClub instance");
		try {
			StockMoteurClub e = entityManager.merge(persistentInstance);
			entityManager.remove(e);
			System.out.println("remove successful");
		} catch (RuntimeException re) {
			System.out.println("remove failed");
			re.printStackTrace();
			throw re;
		}
	}

	public StockMoteurClub merge(StockMoteurClub detachedInstance) {
		System.out.println("merging StockMoteurClub instance");
		try {
			StockMoteurClub result = entityManager.merge(detachedInstance);
			//entityManager.flush();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	/**
	 * *******************************************************************************************************
	 * *******************************************************************************************************
	 */
	public StockMoteurClub findWithLocalStock(Stock s) {
		System.out.println("getting StockMoteurClub instance with Stock object");
		StockMoteurClub instance = null;
		try {
			if(s!=null){
				List<StockMoteurClub> l = null;
				Query ejbQuery = null;
				
				//recherche par immat
				if(s.getImmatriculation()!=null && !s.getImmatriculation().equals("")) {
					System.out.println("Recherche par immat : "+s.getImmatriculation());
					//VehiculeCacheAAA.immatPourWebService(s.getImmatriculation());
					ejbQuery = entityManager.createQuery("select f from StockMoteurClub f where f.numero_immat='"+s.getImmatriculation()+"'");
					l = ejbQuery.getResultList();
				}
				//sinon recherche par VIN
				if(l==null && s.getVin()!=null && !s.getVin().equals("")) {
					System.out.println("Recherche par VIN : "+s.getVin());
					ejbQuery = entityManager.createQuery("select f from StockMoteurClub f where f.num_type='"+s.getVin()+"'");
					l = ejbQuery.getResultList();
				}
				//sinon recherche par numero de livre de police
				if(l==null && s.getNumLivrePolice()!=null && !s.getNumLivrePolice().equals("")) {
					System.out.println("Recherche par numLivrePolice : "+s.getNumLivrePolice());
					ejbQuery = entityManager.createQuery("select f from StockMoteurClub f where f.numero_prod='"+s.getNumLivrePolice()+"'");
					l = ejbQuery.getResultList();
				}
				//sinon emplacement casier
				if(l==null && s.getEmplacementCasier()!=null && !s.getEmplacementCasier().equals("")) {
					//prendre en compte l'id company
//					ejbQuery = entityManager.createQuery("select f from StockMoteurClub f where f.numero_prod='"+s.getNumLivrePolice()+"'");
//					l = ejbQuery.getResultList();
				}
				
				//sinon autre
//				Query query = entityManager.createQuery("select f from VehiculeCacheAAA f where f.Immat_SIV='"+immat+"'");
//				StockMoteurClub instance = (StockMoteurClub)query.getSingleResult();
				
				if(l!=null && !l.isEmpty()) {
					//si plusieur résultat, surement boite et moteur, renvoyé la pièce du bon type
					if(l.size()==1) {
						instance = l.get(0);
					} else {
						for (StockMoteurClub stockMoteurClub : l) {
							if(stockMoteurClub.getSujet_stock().equals(s.getTypeDePiece())) {
								//moteur ou boite
								instance = stockMoteurClub;
								System.out.println("get successful");
								break;
							}
						}
					}
				}
				
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
	
	public StockMoteurClub findByImmat(String immat) {
		System.out.println("getting StockMoteurClub instance with immat: " + immat);
		try {
			if(!immat.equals("")){
				Query query = entityManager.createQuery("select f from StockMoteurClub f where f.Immat_SIV='"+immat+"'");
				StockMoteurClub instance = (StockMoteurClub)query.getSingleResult();
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
	
	public StockMoteurClub findByVIN(String vin) {
		System.out.println("getting StockMoteurClub instance with vin: " + vin);
		try {
			if(!vin.equals("")){
				Query query = entityManager.createQuery("select f from StockMoteurClub f where f.Type='"+vin+"'");
				StockMoteurClub instance = (StockMoteurClub)query.getSingleResult();
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

	public StockMoteurClub findById(int id) {
		System.out.println("getting StockMoteurClub instance with id: " + id);
		try {
			StockMoteurClub instance = entityManager.find(StockMoteurClub.class, id);
			System.out.println("get successful");
			return instance;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			re.printStackTrace();
			throw re;
		}
	}

	public StockMoteurClub findByCode(String code) {
		System.out.println("getting StockMoteurClub instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from StockMoteurClub f where f.StockMoteurClubname='"+code+"'");
				StockMoteurClub instance = (StockMoteurClub)query.getSingleResult();
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


	public List<StockMoteurClub> selectAll() {
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<StockMoteurClub> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<StockMoteurClub> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<StockMoteurClub> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<StockMoteurClub> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<StockMoteurClub> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	
	public List<StockMoteurClub> selectAllStockMoteurClub() {
		try {
			//Query ejbQuery = entityManager.createQuery("select disctinct(v.marque) from StockMoteurClub v");
			TypedQuery<StockMoteurClub> ejbQuery = entityManager.createQuery("select u from StockMoteurClub u", StockMoteurClub.class);
			List<StockMoteurClub> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}


	@Override
	public boolean validate(StockMoteurClub value) throws Exception {
		BeanValidator<StockMoteurClub> validator = new BeanValidator<StockMoteurClub>(StockMoteurClub.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(StockMoteurClub value, String propertyName) throws Exception {
		BeanValidator<StockMoteurClub> validator = new BeanValidator<StockMoteurClub>(StockMoteurClub.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(StockMoteurClub transientInstance) {
		entityManager.detach(transientInstance);
	}

	//	@Override
	//	protected StockMoteurClub refresh(StockMoteurClub persistentInstance) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

}
