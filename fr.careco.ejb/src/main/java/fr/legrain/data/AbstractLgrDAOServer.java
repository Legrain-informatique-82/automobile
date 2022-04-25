package fr.legrain.data;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.proxy.HibernateProxy;

import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServer;
import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServerDTO;
import fr.legrain.bdg.tiers.service.remote.IGenericCRUDServiceRemoteDTO;
import fr.legrain.bdg.tiers.service.remote.IGenericCRUDServiceRemoteEntity;
import fr.legrain.lib.data.LibChaine;
import fr.legrain.lib.data.ModeObjet;

//import fr.legrain.lib.data.ModeObjet.EnumModeObjet;

public abstract class AbstractLgrDAOServer<Entity,DTO> 
					extends JPABdLgrServer 
					implements IAbstractLgrDAOServer<Entity>, IAbstractLgrDAOServerDTO<DTO>,
								IGenericCRUDServiceRemoteEntity<Entity>, IGenericCRUDServiceRemoteDTO<DTO>{
	
	static Logger logger = Logger.getLogger(AbstractLgrDAOServer.class.getName());
	
	private static final int TYPE_ENREGISTREMENT_MERGE = 1;
	private static final int TYPE_ENREGISTREMENT_PERSIST = 2;
	
	private static final int  LOCK_TYPE_LGR = 1;
	private static final int LOCK_TYPE_JPA = 2;
	
	//protected static IGestionModif gestionModif = new GestionModif();
	@Inject
	protected IGestionModif gestionModif = null;
	//protected static GestionModif gestionModif;
	/** nom de la table dans la bdd correspondant a l'entite geree par ce DAO*/
	protected String nomTable;
	protected String nomTableJPA;
	protected String nomTableMere;
	/** veleur de l'ID de l'entite geree par ce DAO*/
	protected Integer valeurIdTable;
	protected String champIdTable;
	protected String champIdEntite;
	protected String champGenere;
	protected String valeurGenere;
	
	private int lockType = LOCK_TYPE_LGR;
//	private int lockType = LOCK_TYPE_JPA;
	
	/** Contient le mode dans lequel se trouve l'objet */
	//private ModeObjet modeObjet = new ModeObjet();
	static private Properties listeChampMaj=new Properties(); 
	protected String JPQLQuery = null;
	protected String JPQLQueryInitial = null;
	
	//Paramètre pour la construction de la clause "where" de requete simple
	//clé = nom du champs, valeur = [type de comparaison (=,>,<,like)][valeur du champs]
	protected Map<String,String[]> paramWhereSQL = null;
	
	protected HashMap listeChampsCalcules = null;
		
	public AbstractLgrDAOServer() {
		super();
	}

	protected abstract Entity refresh(Entity persistentInstance);
	
//	public abstract void validateEntity(Entity value);
//	public abstract void validateEntityProperty(Entity value, String propertyName);
//	public abstract void validateDTO(DTO dto);
//	public abstract void validateDTOProperty(DTO dto, String propertyName);
	
	@Override
	public void enregistrerPersist(Entity transientInstance) throws Exception{
		enregistrer(transientInstance,TYPE_ENREGISTREMENT_PERSIST);
	}

	@Override
	public Entity enregistrerMerge(Entity persistentInstance) throws Exception{
		return enregistrer(persistentInstance,TYPE_ENREGISTREMENT_MERGE);
	}
	
	@Override
	public void enregistrerPersistDTO(DTO transientInstance) throws Exception{
		enregistrerDTO(transientInstance,TYPE_ENREGISTREMENT_PERSIST);
	}

	@Override
	public void enregistrerMergeDTO(DTO persistentInstance) throws Exception{
		enregistrerDTO(persistentInstance,TYPE_ENREGISTREMENT_MERGE);
	}

	public void consultation() {
		//getModeObjet().setMode(ModeObjet.EnumModeObjet.C_MO_CONSULTATION);
	}
	
	/**
	 * 
	 * @param instance
	 * @param typeEnregistrement - MERGE ou PERSIST, cf constantes TYPE_ENREGISTREMENT_
	 * @throws Exception
	 */
	private Entity enregistrer(Entity instance,int typeEnregistrement) throws Exception{
		Entity retour = null;
		init(instance);
		
		
		if(lockType==LOCK_TYPE_LGR) {
			if(typeEnregistrement==TYPE_ENREGISTREMENT_MERGE)
				retour = merge(instance);
			else if(typeEnregistrement==TYPE_ENREGISTREMENT_PERSIST)
				persist(instance);
			
			annuleModification(false);
		} else {
			if(typeEnregistrement==TYPE_ENREGISTREMENT_MERGE)
				retour = merge(instance);
			else if(typeEnregistrement==TYPE_ENREGISTREMENT_PERSIST)
				persist(instance);
		}
		
		return retour;
	}
	
	private void enregistrerDTO(DTO instance,int typeEnregistrement) throws Exception{
		//init(instance);
		
		if(lockType==LOCK_TYPE_LGR) {
			if(typeEnregistrement==TYPE_ENREGISTREMENT_MERGE)
				mergeDTO(instance);
			else if(typeEnregistrement==TYPE_ENREGISTREMENT_PERSIST)
				persistDTO(instance);
			
			annuleModification(false);
		} else {
			if(typeEnregistrement==TYPE_ENREGISTREMENT_MERGE)
				mergeDTO(instance);
			else if(typeEnregistrement==TYPE_ENREGISTREMENT_PERSIST)
				persistDTO(instance);
		}
	}

	public void afterEnregistrer() throws Exception{
		if (champGenere!=null && valeurGenere!=null)
			annulerCodeGenere(valeurGenere,champGenere);
		//modeObjet.setMode(ModeObjet.EnumModeObjet.C_MO_CONSULTATION);
		
	}

	@Override
	public Boolean autoriseModification(Entity persistentInstance){
		Boolean retour=true;
		try{
			if(lockType==LOCK_TYPE_LGR) {
				if(persistentInstance!=null){
					init(persistentInstance);
					retour=autoriseModification();
				}
			}
		}
		catch (Exception e) {
			retour = false;
		}
		return retour;
	}

	@Override
	public void messageNonAutoriseModification() throws Exception{
		if(lockType==LOCK_TYPE_LGR) {
//			if(!dataSetEnModif()) {
					String message="Cet enregistrement a été modifié par un autre utilisateur !!!" +
					"\r\nIl doit être rechargé pour y " +
					"apporter de nouvelles modifications.";
//					MessageDialog.openWarning(PlatformUI.getWorkbench()
//							.getActiveWorkbenchWindow().getShell(),"Attention",
//							message);
					throw new Exception(message);
//			}
		}
	}	

	@Override
	public void verifAutoriseModification(Entity persistentInstance) throws Exception{
		if(lockType==LOCK_TYPE_LGR) {
			init(persistentInstance);
//			if(!dataSetEnModif()) {
				if (autoriseModification()){						
				}else{
					String message="Cet enregistrement est déjà en cours de modification !!!" +
					"\r\nVous devez attendre qu'il soit libéré pour y " +
					"apporter de nouvelles modifications.";
//					MessageDialog.openWarning(PlatformUI.getWorkbench()
//							.getActiveWorkbenchWindow().getShell(),"Attention",
//							message);
					throw new Exception(message);
				}
//			}
		}
	}
	
	@Override
	public void verifAutoriseModificationDTO(DTO persistentInstance) throws Exception{
		if(lockType==LOCK_TYPE_LGR) {
			//init(persistentInstance);
			
//			if(!dataSetEnModif()) {
				if (autoriseModification()){						
				}else{
					String message="Cet enregistrement est déjà en cours de modification !!!" +
					"\r\nVous devez attendre qu'il soit libéré pour y " +
					"apporter de nouvelles modifications.";
//					MessageDialog.openWarning(PlatformUI.getWorkbench()
//							.getActiveWorkbenchWindow().getShell(),"Attention",
//							message);
					throw new Exception(message);
				}
//			}
		}
	}

	@Override
	public void modifier(Entity persistentInstance) throws Exception{
		if(lockType==LOCK_TYPE_LGR) {
			init(persistentInstance);
			if (autoriseModification()){					
				rentreEnModification();	
			}else{
				String message="Cet enregistrement est déjà en cours de modification !!!" +
				"\r\nVous devez attendre qu'il soit libéré pour y " +
				"apporter de nouvelles modifications.";
//				logger.info(persistentInstance.toString()+" est deja en cours de modification");
//				MessageDialog.openWarning(PlatformUI.getWorkbench()
//						.getActiveWorkbenchWindow().getShell(),"Attention",
//						message);
				throw new Exception(message);				
			}
		}
		//modeObjet.setMode(ModeObjet.EnumModeObjet.C_MO_EDITION);
	}
	
	@Override
	public void modifierDTO(DTO persistentInstance) throws Exception{
		if(lockType==LOCK_TYPE_LGR) {
			//init(persistentInstance);
			if (autoriseModification()){					
				rentreEnModification();	
			}else{
				String message="Cet enregistrement est déjà en cours de modification !!!" +
				"\r\nVous devez attendre qu'il soit libéré pour y " +
				"apporter de nouvelles modifications.";
//				logger.info(persistentInstance.toString()+" est deja en cours de modification");
//				MessageDialog.openWarning(PlatformUI.getWorkbench()
//						.getActiveWorkbenchWindow().getShell(),"Attention",
//						message);
				throw new Exception(message);				
			}
		}
		//modeObjet.setMode(ModeObjet.EnumModeObjet.C_MO_EDITION);
	}

	@Override
	public void inserer(Entity transientInstance) throws Exception{
//		modeObjet.setMode(ModeObjet.EnumModeObjet.C_MO_INSERTION);
	}

	@Override
	public void annuler(Entity persistentInstance) throws Exception{
		if(lockType==LOCK_TYPE_LGR) {
			annuleModification(true);
		}
		
		if (champGenere!=null && valeurGenere!=null)
			annulerCodeGenere(valeurGenere,champGenere);
//		if(modeObjet.getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_EDITION)==0)
//			persistentInstance=refresh(persistentInstance);
//		modeObjet.setMode(ModeObjet.EnumModeObjet.C_MO_CONSULTATION);
	}
	
	@Override
	public void annulerDTO(DTO persistentInstance) throws Exception{
		if(lockType==LOCK_TYPE_LGR) {
			annuleModification(true);
		}
		
		if (champGenere!=null && valeurGenere!=null)
			annulerCodeGenere(valeurGenere,champGenere);
	}

	@Override
	public Entity annulerT(Entity persistentInstance) throws Exception{
		if(lockType==LOCK_TYPE_LGR) {
			annuleModification(true);
		}
		
		if (champGenere!=null && valeurGenere!=null)
			annulerCodeGenere(valeurGenere,champGenere);
//		if(modeObjet.getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_EDITION)==0)
//			persistentInstance=refresh(persistentInstance);
//		modeObjet.setMode(ModeObjet.EnumModeObjet.C_MO_CONSULTATION);
		return persistentInstance;
	}
	
	@Override
	public DTO annulerTDTO(DTO persistentInstance) throws Exception{
		if(lockType==LOCK_TYPE_LGR) {
			annuleModification(true);
		}
		
		if (champGenere!=null && valeurGenere!=null)
			annulerCodeGenere(valeurGenere,champGenere);
//		if(modeObjet.getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_EDITION)==0)
//			persistentInstance=refresh(persistentInstance);
//		modeObjet.setMode(ModeObjet.EnumModeObjet.C_MO_CONSULTATION);
		return persistentInstance;
	}

	@Override
	public void supprimer(Entity persistentInstance) throws Exception{
		remove(persistentInstance);
	}
	
	@Override
	public void supprimerDTO(DTO persistentInstance) throws Exception{
		removeDTO(persistentInstance);
	}

	public void commit(EntityTransaction transaction) throws Exception {
		if(transaction.isActive()){
			getEntityManager().flush();
			transaction.commit();
			}
//		if((modeObjet.getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_INSERTION) == 0)
//				|| (modeObjet.getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_EDITION) == 0)) {
			afterEnregistrer();		
//		}
	}

	public void begin(EntityTransaction transaction) throws Exception {
		if(!transaction.isActive()){
			transaction.begin();
		}
	}
	
	public abstract void initConnexion();
	
//	public boolean dataSetEnModif() {
//		if(modeObjet.getMode()==EnumModeObjet.C_MO_EDITION
//				|| modeObjet.getMode()==EnumModeObjet.C_MO_IMPORTATION
//				|| modeObjet.getMode()==EnumModeObjet.C_MO_INSERTION)
//			return true;
//		else
//			return false;
//	}

//	public ModeObjet getModeObjet() {
//		return modeObjet;
//	}
//
//	public void setModeObjet(ModeObjet modeObjet) {
//		this.modeObjet = modeObjet;
//	}
	
//	public void addChangeModeListener(ChangeModeListener l) {
//		modeObjet.addChangeModeListener(l);
//	}
//	
//	public void removeChangeModeListener(ChangeModeListener l) {
//		modeObjet.removeChangeModeListener(l);
//	}

	@Override
	public String getChampIdTable() {
		return champIdTable;
	}
	
//	public abstract void ctrlSaisieSpecifique( ValeurChamps valeurChamps) throws ExceptLgr;

	@Override
	public Map<String,String[]> getParamWhereSQL() {
		return paramWhereSQL;
	}

	@Override
	public void setParamWhereSQL(Map<String,String[]> paramWhereSQL) {
		if(paramWhereSQL!=null){
		this.paramWhereSQL = paramWhereSQL;
		changeCloseWhere();
		}
	}
	
	private void initJPQLQueryInitial() {
		if(JPQLQueryInitial==null) {
			JPQLQueryInitial=getJPQLQuery();
		}
	}
	
	protected void changeCloseWhere() {
		changeCloseWhere(true);
	}
	
	/**
	 * Creation de la nouvelle requete à partir de <code>paramWhereSQL</code> et ajout
	 * d'une close "where" à la fin de celle-ci. 
	 * La nouvelle requete remplace la précédente.
	 * @param init boolean - si vrai, travaille a partir de la requete initiale,
	 *  sinon ajoute les clauses a la fin de la requete actuelle.
	 */
	protected void changeCloseWhere(boolean init) {
		String nouvelleRequete = "";
		initJPQLQueryInitial();
		if(init) {
			setJPQLQuery(JPQLQueryInitial);
		}
		
		nouvelleRequete = getJPQLQuery();
		boolean debutRqt = false;
		if(!paramWhereSQL.isEmpty()) {
			if (!nouvelleRequete.toUpperCase().contains("WHERE")){
				nouvelleRequete += " where ";					
				debutRqt = true;
			}
			for(String champs : paramWhereSQL.keySet()) {
				if(!debutRqt)
					nouvelleRequete+=" and ";
				else
					debutRqt = false;
				nouvelleRequete += champs + " " + paramWhereSQL.get(champs)[0] 
				                                                            +" "+ paramWhereSQL.get(champs)[1];
			}
		}
		setJPQLQuery(nouvelleRequete);
	}

	@Override
	public String getJPQLQuery() {
		return JPQLQuery;
	}

	@Override
	public void setJPQLQuery(String query) {
		JPQLQuery = query;
	}
	
	/*
	 * =========================================================================================================================
	 * Gestion du verrouillage et des codes generes ===> IBQuLgr
	 * =========================================================================================================================
	 */
	
	/**
	 * Si l'objet passé en parametre est un proxy Hibernate/CGLIB, récupère la vrai classe. Sinon retourne null.<br>
	 * Ex : TaArticle$$EnhencedByCGLIB ==> TaArticle<br>
	 * <p>Cette methode est spécifique à Hibernate et ne peu pas être utilisé avec d'autres implémentations de JPA.</p>
	 * @param entity
	 * @return
	 * @see AbstractLgrDAOServer#deproxyEntity(Object)
	 */
	private Class deproxyClass(Object entity) {
		Class clazz = null;
		if (entity instanceof HibernateProxy) {
			clazz = ((HibernateProxy)entity).getHibernateLazyInitializer().getPersistentClass();
		}
		return clazz;
	}
	
	/**
	 * Si l'objet passé en parametre est un proxy Hibernate/CGLIB, retourne l'entité gérée par ce proxy. Sinon retourne null<br>
	 * <p>Cette methode est spécifique à Hibernate et ne peu pas être utilisé avec d'autres implémentations de JPA.</p>
	 * @param entity
	 * @return
	 * @see AbstractLgrDAOServer#deproxyClass(Object)
	 */
	private Entity deproxyEntity(Object entity) {
		Entity res = null;
		if (entity instanceof HibernateProxy) { 
			res = (Entity) ((HibernateProxy)entity).getHibernateLazyInitializer().getImplementation();
		}
		return res;
	}
	
	/**
	 * initialisation de <code>nomTable</code>, <code>champIdTable</code> et <code>valeurIdTable</code> a partir des annotations JPA
	 * @param entity
	 */
	protected void init(Entity entity){
		initNomTable(entity);
		initValeurIdTable(entity);
		//gestionModif.setCnx(cnx);
	}
	
	/**
	 * initialisation de <code>nomTable</code> a partir des annotations JPA
	 * @param entity
	 */
	protected void initNomTable(Entity entity) {
		//nomTable
		Table ta = null;
		SecondaryTable taSecond=null;
		//if(nomTable==null) {
		Class c = deproxyClass(entity);
		if(c!=null) {
			ta = (Table) c.getAnnotation(Table.class);
		} else {
			ta = entity.getClass().getAnnotation(Table.class);
			taSecond=entity.getClass().getAnnotation(SecondaryTable.class);
		}
		if(ta==null){
			nomTable=taSecond.name();
		}
		else
		nomTable=ta.name();
		nomTableJPA=entity.getClass().getSimpleName();
		//}
	}
	
	protected void initNomTableMere(Object entity) {
		Table ta = null;
		SecondaryTable taSecond=null;
		Class c = deproxyClass(entity);
		if(c!=null) {
			ta = (Table) c.getAnnotation(Table.class);
		} else {
			ta = entity.getClass().getAnnotation(Table.class);
		}
		nomTableMere=ta.name();		
	}

	@Override
	public int initValeurIdTable(Entity entity) {
		//valeurIdTable		
		Class clazz = deproxyClass(entity);
		PropertyDescriptor[] p;
		if(clazz!=null) {
			p = PropertyUtils.getPropertyDescriptors(clazz);
		} else {
			p = PropertyUtils.getPropertyDescriptors(entity);
		}
		
		boolean idTrouve = false;
		int i = 0;
		while(!idTrouve && i<p.length) {
			if(p[i].getReadMethod().getAnnotation(Id.class)!=null) {
				idTrouve = true;
			}
			if(!idTrouve) {
				i++;
			}
		}
		if(idTrouve) {
			try {
				Column c = p[i].getReadMethod().getAnnotation(Column.class);
				champIdTable = c.name();
				champIdEntite = p[i].getName();
				valeurIdTable = (Integer)p[i].getReadMethod().invoke(entity, new Object[]{});
				return valeurIdTable;
			} catch (IllegalArgumentException e) {
				logger.error("",e);
			} catch (IllegalAccessException e) {
				logger.error("",e);
			} catch (InvocationTargetException e) {
				logger.error("",e);
			}
			return 0;
		} else {
			return 0;
		}
	}
	
	
	public boolean autoriseUtilisationCodeGenere(String code,String nomTable) throws Exception{
		return autoriseUtilisationCodeGenere(code,nomTable,true);
	}	

	@Override
	public boolean autoriseUtilisationCodeGenere(String code) throws Exception{
		return autoriseUtilisationCodeGenere(code,nomTable,true);
	}

	public boolean autoriseUtilisationCodeGenere(String code,String nomTable,Boolean verif_Connection) throws Exception{
		boolean res = true;
		String champVerif=gestionModif.recupChampGenere(nomTable);
		if (champVerif!=null)
			res= gestionModif.autoriseModifCodeGenere(nomTable,champVerif,code,verif_Connection);
		return res;
	}	

	@Override
	public boolean rentreCodeGenere(String code,String ChampCourant) throws Exception{
		try{
			boolean res = false;
			String champVerif=gestionModif.recupChampGenere(this.nomTable);
			if (champVerif.equals(ChampCourant)|| ChampCourant==null)
				if (champVerif!=null && code !=null && !LibChaine.empty(code))
				{
					gestionModif.rentreEnModif(nomTable,champVerif,code);
					champGenere=champVerif;
					valeurGenere=code;
					res=true;
				}
			return res;
		}catch(Exception e){
			logger.error("",e);
			return false;
		}
	}

	@Override
	public void annulerCodeGenere(String code,String ChampCourant) throws Exception{
		String champVerif=gestionModif.recupChampGenere(this.nomTable);
		if (champVerif.equals(ChampCourant)|| ChampCourant==null)
			if (champVerif!=null && code !=null && !LibChaine.empty(code))
			{
				gestionModif.annuleModif(nomTable,champVerif,code,false);
			}
	}

	@Override
	public boolean recordModifiable(String nomTable,Integer valeurChamp) {
		try{
			CallableStatement cs;
			boolean res=true;
			if (valeurChamp==null )throw new Exception("Valeur id_table non renseignée");
			if (!LibChaine.emptyNumeric(valeurChamp)){
				cs = cnx.prepareCall("{? = call " + "RECORD_MODIFIABLE" +"(?,?)}");

				cs.registerOutParameter(1,Types.INTEGER);
				cs.setString(2,nomTable);
				cs.setInt(3,valeurChamp);
				cs.execute();
				if (cs.getInt(1)>0)
					res=false;
			}
			return res;
		}catch(Exception e){
			logger.error("",e);
			return false;
		}
	}

	@Override
	public boolean autoriseModification() throws Exception{
		boolean res = true;
//careco
//		String champVerif=gestionModif.recupChampVerifModification(this.nomTable);
//		if (champVerif!=null)
//			res= gestionModif.autoriseModif(nomTable,champVerif,valeurIdTable.toString());
		return res;
	}

	@Override
	public void rentreEnModification() throws Exception{
//		String champVerif=gestionModif.recupChampVerifModification(this.nomTable);
//		if (champVerif!=null)
//			gestionModif.rentreEnModif(nomTable,champVerif,valeurIdTable);
	}

	@Override
	public void annuleModification(boolean commited) throws Exception{
//		String champVerif=gestionModif.recupChampVerifModification(this.nomTable);
//		if (champVerif!=null)
//			gestionModif.annuleModif(nomTable,champVerif,
//					valeurIdTable,commited);
	}

	@Override
	public void setListeChampMaj(String fileName) throws Exception{
		try {
			if (!new File(fileName).exists()) {
//				MessageDialog.openWarning(PlatformUI.getWorkbench()
//						.getActiveWorkbenchWindow().getShell(), "ATTENTION",
//						"Le fichier .properties "+ fileName + " est inexistant");
				throw new Exception("Le fichier .properties "+ fileName + " est inexistant");
			} else {
				FileInputStream file = new FileInputStream(fileName);
				listeChampMaj.load(file);
				file.close();
			}
		}
		catch (Exception e) {
			logger.error("Erreur : setListeChampMaj", e);
		}
	}
	
	public static Properties getListeChampMaj() {
		return listeChampMaj;
	}

	@Override
	public String getNomTable() {
		return nomTable;
	}

	@Override
	public void setNomTable(String nomTable) {
		this.nomTable = nomTable;
	}

	@Override
	public Integer getValeurIdTable() {
		return valeurIdTable;
	}

	@Override
	public String getChampIdEntite() {
		return champIdEntite;
	}

	@Override
	public HashMap getListeChampsCalcules() {
		return listeChampsCalcules;
	}

	@Override
	public void setListeChampsCalcules(HashMap listeChampsCalcules) {
		this.listeChampsCalcules = listeChampsCalcules;
	}

	@Override
	public int selectCount() {
		return 1;
//		return selectAll().size();
	}

	@Override
	public String getChampGenere() {
		return champGenere;
	}

	@Override
	public void setChampGenere(String champGenere) {
		this.champGenere = champGenere;
	}

	@Override
	public String getJPQLQueryInitial() {
		return JPQLQueryInitial;
	}

	@Override
	public void setJPQLQueryInitial(String jPQLQueryInitial) {
		JPQLQueryInitial = jPQLQueryInitial;
	}
	protected String initChampId(Entity entity) {	
			Class clazz = deproxyClass(entity);
			PropertyDescriptor[] p;
			if(clazz!=null) {
				p = PropertyUtils.getPropertyDescriptors(clazz);
			} else {
				p = PropertyUtils.getPropertyDescriptors(entity);
			}
			
			boolean idTrouve = false;
			int i = 0;
			while(!idTrouve && i<p.length) {
				if(p[i].getReadMethod().getAnnotation(Id.class)!=null) {
					idTrouve = true;
				}
				if(!idTrouve) {
					i++;
				}
			}
			if(idTrouve) {
				try {
					Column c = p[i].getReadMethod().getAnnotation(Column.class);
					this.champIdTable =c.name();
					return this.champIdTable;
				} catch (IllegalArgumentException e) {
					logger.error("",e);
				} 
				return null;
			} else {
				return null;
			}
		}	
	
	protected String initChampId(Class entityClass) {	
		PropertyDescriptor[] p=null;
		boolean idTrouve = false;
		int i = 0;
		if(entityClass!=null) {
			p = PropertyUtils.getPropertyDescriptors(entityClass);
			
			while(!idTrouve && i<p.length) {
				if(p[i].getReadMethod().getAnnotation(Id.class)!=null) {
					idTrouve = true;
				}
				if(!idTrouve) {
					i++;
				}
			}
		} 	
		if(idTrouve) {
			try {
				Id c = p[i].getReadMethod().getAnnotation(Id.class);
				this.champIdTable =p[i].getName();
				return this.champIdTable;
			} catch (IllegalArgumentException e) {
				logger.error("",e);
			} 
			return null;
		} else {
			return null;
		}
	}

//	public ModeObjet getModeObjet() {
//		return modeObjet;
//	}
//
//	public void setModeObjet(ModeObjet modeObjet) {
//		this.modeObjet = modeObjet;
//	}

}