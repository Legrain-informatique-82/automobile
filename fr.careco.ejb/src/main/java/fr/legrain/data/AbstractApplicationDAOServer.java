package fr.legrain.data;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import fr.legrain.lib.data.ConnectionJDBC;
import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.LibDate;
import fr.legrain.lib.data.MessCtrlLgr;
import fr.legrain.validator.common.ICtrlLgr;
//import fr.legrain.validator.JPACtrl_Application;
import fr.legrain.validator.common.LgrHibernateValidated;

public abstract class AbstractApplicationDAOServer<Entity,DTO> extends AbstractLgrDAOServer<Entity,DTO> {

	static Logger logger = Logger.getLogger(AbstractApplicationDAOServer.class);
	
	//private static final String PERSISTENCE_UNIT_NAME = "sample";
	
	@Inject
	protected ICtrlLgr ctrlGeneraux = null;

	public AbstractApplicationDAOServer() {
		super();
		initConnexion();
		//ctrlGeneraux = new JPACtrl_Application();
		//System.err.println("CHEMIN EN DUR A CHANGER : (Const.C_FICHIER_MODIF) (Modif.properties)");
		//gestionModif.setListeGestionModif("/home/nicolas/public/lgrdoss/BureauDeGestion/demo/Bd/Modif.properties");

	}
	
	@PostConstruct
	public void init() {
		//System.err.println("***********aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		gestionModif.setListeGestionModif("/home/nicolas/public/lgrdoss/BureauDeGestion/demo/Bd/Modif.properties");
	}

	@Override
	public void initConnexion() {	
		//System.err.println("CHEMIN EN DUR A CHANGER : connection 'parallèle' pour obtenir metadata pour les controles");
		final String C_URL_BDD          = "jdbc:firebirdsql:";
//		public static final String C_URL_BDD          = "jdbc:firebirdsql://192.168.0.6/";
		final String C_USER             = "ADMIN";
		final String C_PASS             = "lgr82admin";
		final String C_DRIVER_JDBC      = "org.firebirdsql.jdbc.FBDriver";
		final String C_FICHIER_BDD      = "/home/nicolas/public/lgrdoss/BureauDeGestion/demo/Bd/GEST_COM.FDB";
//		Connection cnx = ConnectionJDBC.getConnection(Const.C_URL_BDD+Const.C_FICHIER_BDD, Const.C_USER, Const.C_PASS, Const.C_DRIVER_JDBC);
		Connection cnx = ConnectionJDBC.getConnection(C_URL_BDD+"//localhost/"+C_FICHIER_BDD, C_USER, C_PASS, C_DRIVER_JDBC);
		setCnx(cnx);
////		if (gestionModif.getListeGestionModif()==null)
//			gestionModif.setListeGestionModif(Const.C_FICHIER_MODIF);
//		gestionModif.setCnx(cnx);
//		emf = getEntityManagerFactory();
	}

	public static void deconnectionGestionModif(){
		GestionModif.deConnection();
	}
	
	
//	public static EntityManagerFactory getEntityManagerFactory() {
//		try {
//			
//		if (emf == null) {
//			logger.info("Creation de EntityManagerFactory ...");
//			// Create the EntityManagerFactory
//			Map<String,String> configOverrides = new HashMap<String,String>();
//			configOverrides.put("hibernate.connection.driver_class", Const.C_DRIVER_JDBC);
//			configOverrides.put("hibernate.connection.password", Const.C_PASS);
//			configOverrides.put("hibernate.connection.url", Const.C_URL_BDD+Const.C_FICHIER_BDD);
//			configOverrides.put("hibernate.connection.username", Const.C_USER);
//			//configOverrides.put("hibernate.dialect", "org.hibernate.dialect.FirebirdDialect");
//			configOverrides.put("hibernate.dialect", "fr.legrain.gestCom.global.LgrHibernateFirebirdDialect");
//			configOverrides.put("hibernate.validator.autoregister_listeners", "false");
//			//configOverrides.put("hibernate.show_sql", "true");
//
//			/* Enable Hibernate's automatic session context management */
////			configOverrides.put("current_session_context_class", "thread");
////			configOverrides.put("hibernate.archive.autodetection", "class, hbm");
//			
//			configOverrides.put("hibernate.cache.use_second_level_cache", "true");
//			configOverrides.put("hibernate.cache.provider_class","org.hibernate.cache.EhCacheProvider");
//			
//			//configOverrides.put("hibernate.cache.use_query_cache", "true");
//
//			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,configOverrides);
//			logger.info("EntityManagerFactory créé.");
//		}
//		} catch (Exception e) {
//			logger.error("EntityManagerFactory pas générée correctement",e);
//			return null;
//		}
//		return emf;
//	}
	
//	public static void CloseEntityManagerFactory() {
//			ConnectionJDBC.closeConnection();
//			deconnectionGestionModif();
//			if (emf != null) {
//				emf.close();
//				emf=null;
//			}
//	}
	
	public boolean validateAll(Entity entity, String validationContext, boolean verrouilleCode) throws Exception{
		//for (Field field : Entity.getClass().getDeclaredFields()) {

		Class a = (Class<Entity>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		boolean res = true;
		PropertyDescriptor propertyDescriptor = null;
		//for (Field field : a.getClass().getDeclaredFields()) {
		for (Field field : a.getDeclaredFields()) {
			LgrHibernateValidated col = null;
		    // check if field has annotation
			//System.out.println(a.getName()+" *** "+field.getName());
			
		    //if ((col = field.getAnnotation(LgrHibernateValidated.class)) != null) {
			propertyDescriptor = PropertyUtils.getPropertyDescriptor(entity, field.getName());
			if (propertyDescriptor!=null)
				col = PropertyUtils.getReadMethod(propertyDescriptor).getAnnotation(LgrHibernateValidated.class);
			
			if (propertyDescriptor!=null && col != null) {
		    	res = validate(entity, field.getName(), validationContext);
		    	if(!res) {
		    		return false;
		    	}
		    }
		}
		return true;
		
	}

	
	public boolean validate(Entity entity, String field, String validationContext) throws Exception{
		return validate(entity, field, validationContext, false);
	}
	
	/**
	 * 
	 * @param entity - Entite contenant la valeur
	 * @param field - Nom du champ de l'entité à valider
	 * @param validationContext - contexte de validation
	 * @param verrouilleCode - Pour les champs/codes dont on veut empecher la modif s'ils sont deja en cours de modif 
	 * ou pour empecher de générer 2 fois un même code
	 * @return
	 */
	public boolean validate(Entity entity, String field, String validationContext, boolean verrouilleCode) throws Exception{
		//public IStatus validate(Object entity, String field, String contexte){
			boolean s = false;
			try {
//				ClassValidator<TaUnite> uniteValidator = new ClassValidator<TaUnite>( TaUnite.class );
//				InvalidValue[] iv = uniteValidator.getInvalidValues(entity,field);
				
				System.out.println("Serveur (validate appellé manuellement, pas automatiquement à partir des Beans Validation) => xxxDAO.validate() : "+field);

				MessCtrlLgr mess = new MessCtrlLgr();
				LgrHibernateValidated a = PropertyUtils.getReadMethod(PropertyUtils.getPropertyDescriptor(entity, field)).getAnnotation(LgrHibernateValidated.class);
				mess.setContexte(validationContext);
				if(a!=null){//isa le 19-03-2009 car existant dans taPrixDao par exemple
					mess.setNomChampDB(a.champ());
					mess.setNomChamp(field);
					mess.setNomTable(entity.getClass().getAnnotation(Table.class).name());
					mess.setEntityClass(a.clazz());
//					mess.setModeObjet(getModeObjet());
					if(PropertyUtils.getProperty(entity, field)!=null) {
						if(PropertyUtils.getProperty(entity, field).getClass() == java.util.Date.class)
							mess.setValeur(LibDate.dateToString((java.util.Date)PropertyUtils.getProperty(entity, field)));
						else
							mess.setValeur(PropertyUtils.getProperty(entity, field).toString());

						//annulerCodeGenere(mess.getValeur(),mess.getNomChampDB());
						if(verrouilleCode) {
							rentreCodeGenere(mess.getValeur(),mess.getNomChampDB());
						}
					} else {
						mess.setValeur(null);
					}
					mess.setNomChampId(initChampId(entity));
					initValeurIdTable(entity);
					mess.setID_Valeur(valeurIdTable.toString());

					ctrlGeneraux.setModeServeur(true);
					ctrlGeneraux.ctrlSaisie(mess);
				} else {
					logger.error("Annotation de controle (LgrHibernateValidated) non presente.");
				}
				//s = new Status(Status.OK,gestComBdPlugin.PLUGIN_ID,"OK");
				s = true;
				//if(s.getSeverity()!=IStatus.ERROR ){
				if(!s){
					ctrlSaisieSpecifique(entity, field);
				}
				return s;
			} catch (ExceptLgr e) {
				logger.error("",e);
				//s = new Status(Status.ERROR,gestComBdPlugin.PLUGIN_ID,e.getMessage(),e);
				s = false;
				throw new Exception(e.getMessage());
				//return s;
				
				//valid = false;
			} catch (IllegalAccessException e) {
				logger.error("",e);
			} catch (InvocationTargetException e) {
				logger.error("",e);
			} catch (NoSuchMethodException e) {
				logger.error("",e);
			} catch (Exception e) {
				logger.error("",e);
			}
			return s;

		}
	
	/**
	 * Verification entre les champs, permet par exemple d'initialiser ou de modifier une valeurs dès qu'une autre valeur est saisie
	 * @param entity - l'objet modifier
	 * @param field - le champ modifier dans cet objet
	 * @throws ExceptLgr
	 */
	public void ctrlSaisieSpecifique(Entity entity,String field) throws ExceptLgr {}
    
	/*
	public static GenCode recupCodeDocument(String nomTable) throws Exception{
		try{
			boolean nonAutorise = false;
			String res=null;
			int compteur=-1;
			GenCode code = new GenCode();
			code.setListeGestCode(Const.C_FICHIER_GESTCODE);
			TaInfoEntrepriseDAO taInfoEntrepriseDAO = new TaInfoEntrepriseDAO();
			TaInfoEntreprise taInfoEntreprise = taInfoEntrepriseDAO.findInstance();
			while (!nonAutorise) {
				compteur ++;
				res=code.genereCode(nomTable,compteur,taInfoEntreprise.getCodexoInfoEntreprise());
				nonAutorise=autoriseUtilisationCodeGenere(res,nomTable);	
			};
			code.reinitialiseListeGestCode();
			return code;
		}catch(Exception e){
			return null;
		}
	}	

	public String genereCodeJPA() throws Exception{
		return genereCodeJPA(new LinkedList<String>());		
	}
	
	public String genereCodeJPA(List<String> listeCodes) throws Exception{
		try{
			boolean nonAutorise = false;
			String res=null;
			int compteur=-1;
			GenCode code = new GenCode();
			code.setListeGestCode(Const.C_FICHIER_GESTCODE);
			TaInfoEntrepriseDAO taInfoEntrepriseDAO = new TaInfoEntrepriseDAO();
			TaInfoEntreprise taInfoEntreprise = taInfoEntrepriseDAO.findInstance();
			while (!nonAutorise) {
				compteur ++;
				res=code.genereCodeJPA(nomTableJPA,compteur,taInfoEntreprise.getCodexoInfoEntreprise());
				nonAutorise=autoriseUtilisationCodeGenere(res,nomTableMere,true);
				if(listeCodes.size()>0)
					nonAutorise=!listeCodes.contains(res);
			};
			code.reinitialiseListeGestCode();
			return res;
		}catch(Exception e){
			logger.error("",e);
			return "";
		}
	}
	*/
	
	
	
	
	
	/* ejb
	public Boolean retourneEtatLegrain(Map<IDocumentTiers, Boolean> listeEtat,IDocumentTiers doc){
		for (IDocumentTiers docLoc : listeEtat.keySet()) {
			if(doc.getCodeDocument().equals(docLoc.getCodeDocument()))
				return docLoc.isLegrain();
		}
		return false;
	}
	
	public List<IDocumentTiers> selectDocNonTransformeRequete(IDocumentTiers doc ,String codeTiers,String typeOrigine,String typeDest,Date debut,Date fin) {
		// TODO Auto-generated method stub
		logger.debug("selectDocNonTransformeRequete ");
		try {			
			String requete = "select a from Ta"+typeOrigine+" a join a.taTiers t where " ;
			if(codeTiers!=null ) //&& !codeTiers.equals("")
				requete+=" t.codeTiers like '"+codeTiers+"' and ";
			if(doc!=null)
				requete+=" a.idDocument = "+doc.getIdDocument()+" and ";
			requete+=" a.dateDocument between ? and ? and not exists(select r from TaRDocument r " +
					"join r.ta"+typeOrigine+" org " +
					"join r.ta"+typeDest+" dest " +
					" where org.idDocument" +
					" = a.idDocument and (dest.idDocument is not null or dest.idDocument!=0))";
			requete+=" order by t.codeTiers,a.ttc,a.dateDocument ";
			Query ejbQuery = getEntityManager().createQuery(requete);
			ejbQuery.setParameter(1, debut);
			ejbQuery.setParameter(2, fin);
			List<IDocumentTiers> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	*/

}