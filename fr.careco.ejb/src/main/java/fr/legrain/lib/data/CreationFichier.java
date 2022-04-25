package fr.legrain.lib.data;


import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CreationFichier {
    
    /**
     * Prend en argument le nom d'un fichier et écrit le ce qui est
     * saisi au clavier dans ce fichier. La saisie se termine
     * lorsqu'une ligne blanche est lue.
     */

	
	/**
     * Construit un objet FileReader  et ouvre un fichier en écriture. 
     * Écrase le fichier s'il existait déjà.
     * @param nomFichier nom du fichier à créer
     * @return le fichier ouvert
     */
    static FileWriter  creeFichier(String nomFichier) {
            FileWriter writer = null;
			try {
				if (!LibChaine.empty(nomFichier))
					writer = new FileWriter(nomFichier);
			} catch (IOException e) {
				return null;
			}
           // writer.close(); // A ne pas oublier !!!
            return writer;
    }

    
    /**
     * 	Construit un objet FileReader et ouvre un fichier en lecture
     * @param nomFichier nom du fichier à ouvrir
     * @return le fichier ouvert
     */
    static FileReader  OuvreFichier(String nomFichier) {
    	FileReader reader = null;
		try {
			if (!LibChaine.empty(nomFichier))
				reader = new FileReader(nomFichier);
		} catch (IOException e) {
			return null;
		}
       // writer.close(); // A ne pas oublier !!!
        return reader;
}	
    
//    static public void creeTableSurFichier(String nomTable,Database base)
//    {
//    	try
//    	{      
//    		String adressedufichier = "C:/Projet/Java/Eclipse/GestionCommerciale/Ceres/listing.txt";
//    		
//    		FileReader fr = new FileReader(adressedufichier);
//    		BufferedReader br = new BufferedReader(fr);
//    		StringBuffer requete = new StringBuffer("");
//    		
////  		requete.append("create table  ")
////  		.append(nomTable).append(" () ");
//    		
//    		String ligne = br.readLine();
//    		String[] retour = null;
//    		int i =1;
//    		while (ligne!=null){
//    			retour = ligne.split(";");
//    			requete.append("insert into ")
//    			.append(nomTable)
////  			.append(" (client,civilite,libcivilite,nom,prenom,adresse1," +
////  			"adresse2,region,codepostal,ville,pays,tel,fax,tarif,tri,repres," +
////  			"contact,commande,cpt,fact,cpt_banque1,cpt_banque2,cpt_banque3," +
////  			"cpt_banque_cle,divers1,divers2,ref) ")
//    			.append(" values (")
//    			.append(i);
//    			for(int j=0; j<retour.length; j++) {
//    				requete.append(",");
//    				if (LibChaine.empty(retour[j]))requete.append("null");
//    				else
//    					if (j==12 || j==13)
//        					requete.append("'"+LibChaineSQL.formatStringSQL(retour[j].trim())+"'");
//    					else	
//    					requete.append("'"+LibChaineSQL.formatStringSQL(retour[j])+"'");
//    			}        			
//    			requete.append(")");
//  			PreparedStatement rqt;
//			try {
//				rqt = base.getJdbcConnection().prepareStatement(requete.toString());
//	  			rqt.execute();
//	  			requete=new StringBuffer("");
//	  			i++;
//			} catch (SQLException e) {
//				requete=new StringBuffer("");
//				//System.out.println("erreur : " + e);
//			}
//    			
//    			ligne = br.readLine();
//    		}
//    		br.close();
//    	}
//    	catch(IOException ioe){System.out.println("erreur : " + ioe);
//    	}
//    }

}

