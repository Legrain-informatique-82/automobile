package fr.legrain.tiers.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import fr.legrain.bdg.model.mapping.mapper.AnnuaireMapper;
import fr.legrain.bdg.tiers.service.local.IDatabaseServiceLocal;
import fr.legrain.bdg.tiers.service.local.IUserServiceLocal;
import fr.legrain.bdg.tiers.service.remote.IAnnuaireServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.tiers.dao.IAnnuaireDAO;
import fr.legrain.tiers.dto.AnnuaireDTO;
import fr.legrain.tiers.model.Annuaire;

/**
 * Session Bean implementation class AnnuaireBean
 */
@Stateless
public class DatabaseService implements IDatabaseServiceLocal {

	static Logger logger = Logger.getLogger(DatabaseService.class);
	
	//@EJB IUserServiceLocal userService;

	//@Inject private IAnnuaireDAO dao;

	/**
	 * Default constructor. 
	 */
	public DatabaseService() {
		
	}
	
	public File backupDB(String nomDump) throws IOException {
		/*
#!/bin/bash
USER=admin
HOST=localhost
PASSWORD=lgrser0110
DEST=/root/save_mysql/dump/
for i in $(mysql --user=$USER --password=$PASSWORD --host=$HOST --batch --skip-column-names -e "show databases"| sed  's/ /%/g'); do
       /usr/bin/mysqldump --user=$USER --password=$PASSWORD $i > $DEST$i.sql
done
		 */

	    File file = null;
	    String dbUserName = "careco";
	    String dbPassword = "carecopass";
	    String cheminDump = "/var/careco/database_dump";
	    
	    String dbName1 = "careco";
	    String dbName2 = "careco_dump_ProjetAAA";

	    file = new File("BKUP_DB_" +Calendar.getInstance().toString().replace("/", "-") + ".sql");
	    
	    String tagDump = "";
	    if(nomDump!=null && !nomDump.equals("")) {
	    	tagDump = nomDump;
	    	tagDump = tagDump.trim();
	    	//tagDump = tagDump.replace(oldChar, newChar);
	    	tagDump +="_";
	    }

	    //String executeCmd = "mysqldump -u " +dbUserName+ " -p" +dbPassword+" "+"careco" + " > " +file.getPath();
	    String executeCmd1 = "mysqldump -u " +dbUserName+ " -p" +dbPassword+" "+dbName1+ " > " +cheminDump+"/"+tagDump+dbName1+"_$(date +%Y-%m-%d_%H-%M-%S).sql";
	    String executeCmd2 = "mysqldump -u " +dbUserName+ " -p" +dbPassword+" "+dbName2+ " > " +cheminDump+"/"+tagDump+dbName2+"_$(date +%Y-%m-%d_%H-%M-%S).sql";

	    Process runtimeProcess1;
	    Process runtimeProcess2;
	    try {
	    	//base 1
	    	String[] arg1 = new String[] {
	    			"/bin/bash","-c",executeCmd1
	    	};
	        runtimeProcess1 = Runtime.getRuntime().exec(arg1);
	        int processComplete1 = runtimeProcess1.waitFor();
	        
	        //base 2
	        String[] arg2 = new String[] {
	    			"/bin/bash","-c",executeCmd2
	    	};
	        runtimeProcess2 = Runtime.getRuntime().exec(arg2);
	        int processComplete2 = runtimeProcess2.waitFor();

	        //nettoyage
	        if (processComplete1 == 0 && processComplete2 == 0) {
	            System.out.println("Backup created successfully");
	            runtimeProcess1.destroy();
	            runtimeProcess2.destroy();
	            return file;
	        } else {

	            System.out.println("Could not create the backup");
	        }

	    } catch (Exception ex) {
	        ex.printStackTrace();

	    }

	    return file;
	}
	
}
