package fr.legrain.data;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.ColumnResult;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryHint;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

import fr.legrain.lib.data.ConnectionJDBC;
import fr.legrain.lib.data.LibConversion;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Le Grain SA</p>
 * @author Le Grain SA
 * @version 1.0
 */

//@Entity
//@Table(name="TA_MODIF")
//@NamedNativeQueries(value = { 
//		@NamedNativeQuery(name = GestionModif.QNN.AUTORISE_MODIF_GENERE_VERIF, /*resultClass = Person.class, */
//					query = "{? = call " + "AUTORISE_MODIF_GENERE" +"(?,?,?,?)}",    
//					hints = {    
//						@QueryHint(name = "org.hibernate.callable", value = "true")  
//					}    
//		),
//		@NamedNativeQuery(name = GestionModif.QNN.AUTORISE_MODIF_GENERE, /*resultClass = Person.class, */
//					query = "{? = call " + "AUTORISE_MODIF_GENERE" +"(?,?,?)}",    
//					hints = {    
//						@QueryHint(name = "org.hibernate.callable", value = "true")  
//					}    
//		),
//		@NamedNativeQuery(name = GestionModif.QNN.ANNULE_MODIFICATION, /*resultClass = Person.class, */
//					query = "{call " + "ANNULE_MODIFICATION" +"(?,?,?)}",    
//					hints = {    
//						@QueryHint(name = "org.hibernate.callable", value = "true")  
//					}    
//		),
//		
//		@NamedNativeQuery(name = GestionModif.QNN.RENTRE_EN_MODIFICATION, /*resultClass = Person.class, */
//					query = "{call " + "RENTRE_EN_MODIFICATION" +"(?,?,?)}",    
//					hints = {    
//						@QueryHint(name = "org.hibernate.callable", value = "true")  
//					}
//		)
//		
//})
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class GestionModif extends JPABdLgrServer implements IGestionModif {

	public static class QNN {
		public static final String AUTORISE_MODIF_GENERE_VERIF = "GestionModif.AUTORISE_MODIF_GENERE_VERIF";
		public static final String AUTORISE_MODIF_GENERE = "GestionModif.AUTORISE_MODIF_GENERE";
		public static final String ANNULE_MODIFICATION = "GestionModif.ANNULE_MODIFICATION";
		public static final String RENTRE_EN_MODIFICATION = "GestionModif.RENTRE_EN_MODIFICATION";
	}
	
	//@Resource
   // private UserTransaction transaction ;
	
	//@Resource(mappedName="java:/BDGFirebirdDS")
	@Resource(mappedName="java:/CarecoMYSQLDS")
    private DataSource dataSource ;

	static private PropertiesConfiguration listeGestionModif = null;
	static Logger logger = Logger.getLogger(GestionModif.class.getName());
	protected void ecritFileName(String value) {};
	static protected String ipConnection = null;
	
	//@PersistenceContext(unitName = "bdg")
    private EntityManager entityManager;

	protected String urlComplet =null;
	protected String user =null;
	protected String pass =null;
	protected String driver =null;
	//TODO supprimer l'objet base et tout ce qui s'y rapporte dès que possible (surement a la fin de la migration vers jpa)
	//private static Database base ;
	//private Connection cnx = null;

	public GestionModif(){
		
	}
	
	//@Id
	//@Column(name="ID_MODIF")
	//private int idModif = 1;
	
	@PostConstruct
	public void init() {
		entityManager = getEntityManager();
		cnx = getJDBCConnectionFromEntityManagerAndHibernate();
	}

	public GestionModif(Connection cnx){
		//this.cnx = cnx;
		this.cnx = getJDBCConnectionFromEntityManagerAndHibernate();
//		this.cnx = dataSource.getConnection();

	}

	public GestionModif(String url,String user,String pass,String driver) {
		this.urlComplet=url;
		this.user=user;
		this.pass=pass;
		this.driver=driver;
		if(cnx==null) {
			connection(url,user,pass,driver);
		}
		try {
			cnx.setAutoCommit(false);
		} catch (SQLException e) {
			logger.error("",e);
		}
		logger.info("connection gestion modif");
	}

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#connection(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#connection(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean connection(String url,String user,String pass,String driver) {
		try{
//			if (base==null) 
//				base = new Database();
//			base.setConnection(new com.borland.dx.sql.dataset.ConnectionDescriptor(
//					url,user,pass , false, driver));
//			base.setAutoCommit(false);
//			cnx=base.getJdbcConnection();
			if(cnx==null)
				//cnx = ConnectionJDBC.getConnection(url, user, pass, driver);
				cnx = getJDBCConnectionFromEntityManagerAndHibernate(); 
			
			
			return true;
		}catch(Exception e){
			logger.error("Erreur : getDatabase", e);
			return false;
		}
	}
	
	private Connection getJDBCConnectionFromEntityManagerAndHibernate(){
		Session hibernateSession = entityManager.unwrap(Session.class); // unwraps the Connection class.
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) hibernateSession.getSessionFactory();
		ConnectionProvider cp = sfi.getConnectionProvider();
		try {
			//return cp.getConnection();
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean deConnection() {
		try{
			if(cnx!=null)
				cnx.close();
			//base=null;
			return true;
		}catch(Exception e){
			logger.error("Erreur : getDatabase", e);
			return false;
		}
	}


	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#initIp(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#initIp(java.lang.String)
	 */
	@Override
	public void initIp(String ip){
		ipConnection=ip;
	}

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#initQuery()
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#initQuery()
	 */
	@Override
	public boolean initQuery(){
//		if(fIBQuery.getQuery()==null){
//			fIBQuery.setQuery(new QueryDescriptor(fIBBase,"select * from ta_modif", true));
//			fIBQuery.open();
//		}
		return true;
	}

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#setListeGestionModif(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#setListeGestionModif(java.lang.String)
	 */
	@Override
	public void setListeGestionModif(String fileName){
		try {
			//Modif pour CARECO, par de gestion modif pour l'instant
			if (!new File(fileName).exists()) {
//				MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "ATTENTION",
//						"Le fichier .properties "+ fileName + " est inexistant");
				//throw new Exception("Le fichier .properties "+ fileName + " est inexistant"); //careco
			} else {
				FileInputStream file = new FileInputStream(fileName);
				listeGestionModif=new PropertiesConfiguration();
				listeGestionModif.load(file);
				file.close();
			}
		}
		catch (Exception e) {
			logger.error("Erreur : setListeGestionModif", e);
		}
	}


	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#recupChampGenere(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#recupChampGenere(java.lang.String)
	 */
	@Override
	public String recupChampGenere(String nomTable)throws Exception{
		org.apache.commons.configuration.SubsetConfiguration propertie = null;
		propertie = new org.apache.commons.configuration.SubsetConfiguration(listeGestionModif,nomTable,".");
		String res ="";
		if (!propertie.isEmpty()){
			List<Object> liste = listeGestionModif.getList(nomTable+".GENERE");
			if (liste!=null && !liste.isEmpty()){
				for (Object f : liste) {
					if (((String)f).startsWith("CODE_"))
						res=(String)f;
				}
			}
		}		
		return res;
	}

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#recupChampVerifModification(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#recupChampVerifModification(java.lang.String)
	 */
	@Override
	public String recupChampVerifModification(String nomTable)throws Exception{
		org.apache.commons.configuration.SubsetConfiguration propertie = null;
		propertie = new org.apache.commons.configuration.SubsetConfiguration(listeGestionModif,nomTable,".");
		String res =null;
		if (!propertie.isEmpty()){
			List<Object> liste = listeGestionModif.getList(nomTable);
			if (liste!=null && !liste.isEmpty()){
				for (Object f : liste) {
					if (((String)f).contains("ID_"))
						res=(String)f;
				}
			}
		}		
		return res;
	}

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#autoriseModif(java.lang.String, java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#autoriseModif(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean autoriseModif(String nomTable,String nomChamp,String valeurChamp) throws Exception{
//		Query query = entityManager.createNamedQuery(QNN.AUTORISE_MODIF_GENERE_VERIF);
//		//query.setParameter(1, arg1);
//		query.setParameter(2, nomTable);
//		query.setParameter(3, nomChamp);
//		query.setParameter(4, valeurChamp);
//		Object o = query.getSingleResult();    
//		return true;
		boolean res = true;
		CallableStatement cs;
		PreparedStatement ps;
		if (valeurChamp==null && nomChamp!=null)return res;
		//cs=baseModif.createCallableStatement("{? = call " + "AUTORISE_MODIF" +"(?,?,?)}");
//		cs = cnx.prepareCall("{? = call " + "AUTORISE_MODIF" +"(?,?,?)}");
//		cs.registerOutParameter(1,Types.INTEGER);
//		cs.setString(2,nomTable);
//		cs.setString(3,nomChamp);
//		cs.setString(4,valeurChamp);
//		cs.execute();
//		if (cs.getInt(1)>0)
//			res=false;
		
		ps = cnx.prepareStatement("SELECT RETOUR FROM AUTORISE_MODIF (?,?,?)");
		//ps.registerOutParameter(1,Types.INTEGER);
		ps.setString(1,nomTable);
		ps.setString(2,nomChamp);
		ps.setString(3,valeurChamp);
		ResultSet rs = ps.executeQuery();
		//if(rs.first()) { //fonctionne pas avec Jaybird
		if(rs.next()) {
			if(rs.getInt(1)>0)
				res=false;
		}
		
//		if (res)
//		logger.info("Autorise modif RESULT : "+cs.getInt(1)+" ip_acces = "+ipConnection
//		+" table : "+nomTable+" champ : "+nomChamp+" valeur : "+valeurChamp);
		return res;
	}

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#autoriseModifCodeGenere(java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#autoriseModifCodeGenere(java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public boolean autoriseModifCodeGenere(String nomTable,String nomChamp,String valeurChamp,Boolean verif_Connection) throws Exception{
		
//		Query query = entityManager.createNamedQuery(QNN.AUTORISE_MODIF_GENERE_VERIF);
//		//query.setParameter(1, arg1);
//		query.setParameter(2, nomTable);
//		query.setParameter(3, nomChamp);
//		query.setParameter(4, valeurChamp);
//		query.setParameter(5, LibConversion.booleanToInt(verif_Connection));
//		Object o = query.getSingleResult();    
//		return true;
		boolean res = true;
		CallableStatement cs;
		if (valeurChamp==null && nomChamp!=null)return res;
		//cs=baseModif.createCallableStatement("{? = call " + "AUTORISE_MODIF_GENERE" +"(?,?,?)}");
		cs = cnx.prepareCall("{? = call " + "AUTORISE_MODIF_GENERE" +"(?,?,?,?)}");
		cs.registerOutParameter(1,Types.INTEGER);
		cs.setString(2,nomTable);
		cs.setString(3,nomChamp);
		cs.setString(4,valeurChamp);	
		cs.setInt(5,LibConversion.booleanToInt(verif_Connection));
		cs.execute();
		if (cs.getInt(1)>0)
			res=false;
		if (res){//vérifier s'il n'existe pas déjà

		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#annuleModif(java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#annuleModif(java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void annuleModif(String nomTable,String nomChamp,String valeurChamp,boolean commited) throws Exception{
		try{
			CallableStatement cs;
			PreparedStatement ps;
			if (valeurChamp!=null && nomChamp!=null){
				//cs=baseModif.getJdbcConnection().prepareCall("{call " + "ANNULE_MODIFICATION" +"(?,?,?)}");
//				cs = cnx.prepareCall("{call " + "ANNULE_MODIFICATION" +"(?,?,?)}");
//				cs.setString(1,nomTable);
//				cs.setString(2,nomChamp);
//				cs.setString(3,valeurChamp);
//				//cs.execute();
//				cs.executeUpdate();
				
				ps = cnx.prepareStatement("EXECUTE procedure ANNULE_MODIFICATION" +"?,?,?");
				ps.setString(1,nomTable);
				ps.setString(2,nomChamp);
				ps.setString(3,valeurChamp);
				ps.execute();
				
				//if (commited) 
//				cnx.commit();
//				Query query = entityManager.createNamedQuery(QNN.ANNULE_MODIFICATION);
//				query.setParameter(1, nomTable);
//				query.setParameter(2, nomChamp);
//				query.setParameter(3, valeurChamp);
//				query.executeUpdate();    
				logger.info("commit - annuleModif");
			}
		}catch(Exception e){
			logger.debug("",e);
			throw new Exception(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#annuleModif(java.lang.String, java.lang.String, java.lang.Integer, boolean)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#annuleModif(java.lang.String, java.lang.String, java.lang.Integer, boolean)
	 */
	@Override
	public void annuleModif(String nomTable,String nomChamp,Integer valeurChamp,boolean commited) throws Exception{
		if(valeurChamp!=null)
			annuleModif(nomTable, nomChamp, valeurChamp.toString(), commited);
	}

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#rentreEnModif(java.lang.String, java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#rentreEnModif(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void rentreEnModif(String nomTable,String nomChamp,String valeurChamp) throws Exception{
		try{
			CallableStatement cs;
			PreparedStatement ps;
			if (valeurChamp!=null && nomChamp!=null){
				
				Statement s = cnx.createStatement();
//				s.execute("INSERT INTO TA_MODIF ("
//						//+ "ID_MODIF, "
//						+ "TABLE_MODIF, CHAMP_MODIF, VALEUR_MODIF"
//					   //+" ,IP_ACCES, QUI_CREE_MODIF, QUAND_CREE_MODIF, QUI_MODIF_MODIF,"
//					   //+" QUAND_MODIF_MODIF, VERSION, VERSION_OBJ"
//					   + ")"
//					+"VALUES ("
//					//+"   'ID_MODIF*',  "
//					+"   'TA_T_TIERS', "
//					 +"   'ID_T_TIERS', "
//					  +"  '3' "
//					 // +",  'IP_ACCES*', "
//					 //  +" 'QUI_CREE_MODIF', "
//					 //   +" 'QUAND_CREE_MODIF', "
//					 //   +" 'QUI_MODIF_MODIF', "
//					 //   +" 'QUAND_MODIF_MODIF', "
//					  //  +" 'VERSION', "
//					  //  +" 'VERSION_OBJ'"
//					+")");
//				s.execute("EXECUTE procedure RENTRE_EN_MODIFICATION '"+nomTable+"', '"+nomChamp+"', '"+valeurChamp+"'");
				
//				transaction.begin();
				//entityManager.getTransaction().begin();
//				cs = cnx.prepareCall("{call " + "RENTRE_EN_MODIFICATION" +"(?,?,?)}");
//				cs.setString(1,nomTable);
//				cs.setString(2,nomChamp);
//				cs.setString(3,valeurChamp);
//				cs.execute();
				
				ps = cnx.prepareStatement("EXECUTE procedure RENTRE_EN_MODIFICATION " +"?,?,?");
				ps.setString(1,nomTable);
				ps.setString(2,nomChamp);
				ps.setString(3,valeurChamp);
				ps.execute();
				
//				transaction.commit();
				//entityManager.flush();
				//entityManager.getTransaction().commit();
				if(!cnx.getAutoCommit())
					cnx.commit();
				
//				StoredProcedureQuery storedProcedure = null;
//						em.createStoredProcedureQuery("RENTRE_EN_MODIFICATION");
//				// set parameters
//				storedProcedure.registerStoredProcedureParameter("subtotal", Double.class, ParameterMode.IN);
//				storedProcedure.registerStoredProcedureParameter("tax", Double.class, ParameterMode.OUT);
//				storedProcedure.setParameter("subtotal", 1f);
//				// execute SP
//				storedProcedure.execute();
//				// get result
//				//Double tax = (Double)storedProcedure.getOutputParameterValue("tax");
				
//				Query query = entityManager.createNativeQuery("{call " + "RENTRE_EN_MODIFICATION" +"(?,?,?)}");
//				query.setHint("org.hibernate.callable", true);
//				query.setParameter(1, nomTable);
//				query.setParameter(2, nomChamp);
//				query.setParameter(3, valeurChamp);
//				query.executeUpdate();  
				
//				Query query = entityManager.createNamedQuery(QNN.RENTRE_EN_MODIFICATION);
//				query.setParameter(1, nomTable);
//				query.setParameter(2, nomChamp);
//				query.setParameter(3, valeurChamp);
//				query.executeUpdate();    
				logger.info("commit - rentreEnModif");
			}
		}catch(Exception e){
			logger.debug("",e);
			throw new Exception(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#rentreEnModif(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#rentreEnModif(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void rentreEnModif(String nomTable,String nomChamp,Integer valeurChamp) throws Exception{
		if(valeurChamp!=null)
			rentreEnModif(nomTable, nomChamp, valeurChamp.toString());
	}


	public static PropertiesConfiguration getListeGestionModif() {
		return listeGestionModif;
	}

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#setCnx(java.sql.Connection)
	 */
	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#setCnx(java.sql.Connection)
	 */
	@Override
	public void setCnx(Connection cnx) {
		this.cnx = cnx;
	}

	public static String getIpConnection() {
		return ipConnection;
	}

}
