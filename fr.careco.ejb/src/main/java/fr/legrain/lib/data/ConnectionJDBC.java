package fr.legrain.lib.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionJDBC {
	
	static Logger logger = Logger.getLogger(ConnectionJDBC.class.getName());
	
	private static Connection cnx = null;
	
	private ConnectionJDBC(){}
	
	static public Connection getConnection(String url,String user,String password,String driver) {
//		try {
//			if(cnx==null) {
//				//Class.forName(driver);
//				org.firebirdsql.jdbc.FBDriver f;//pour charger la classe du pilote, Class.forName() ne semble pas fonctionner dans l'EAR
//				Class.forName(driver,true,Thread.currentThread().getContextClassLoader());
//				cnx = DriverManager.getConnection(url,user,password);
//				cnx.setAutoCommit(false);
//			}
//			return cnx;
//		 
//		} catch (ClassNotFoundException e) {
//			logger.error("",e);
//		} catch (SQLException e) {
//			logger.error("",e);
//		}
		return null;
	}
	
	static public boolean closeConnection() {
		boolean retour = false;
		try {
			if(cnx!=null) {
				cnx.close();
				cnx=null;
				retour = true;
			}
			return retour;
		} catch (SQLException e) {
			logger.error("",e);
			return retour;
		}
	}


}
