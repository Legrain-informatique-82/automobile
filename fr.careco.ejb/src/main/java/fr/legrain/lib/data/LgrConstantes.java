package fr.legrain.lib.data;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Le Grain SA</p>
 * @author Le Grain SA
 * @version 1.0
 */

public final class LgrConstantes {
	
	public static final String C_STR_VIDE = "";
	public static final String C_ERREUR_INCONNUE = "Cette erreur est inconnue";
	
	public static final String C_DATE_MASK_FORMATTER = "##/##/####";
	public static final char C_DATE_MASK_PLACE_HOLDER = '-';
	public static final String C_MASQUE_DATE_COMPLET = "--/--/----";
	public static final String C_DATE_FORMAT = "dd/MM/yyyy";
	
	public static final String C_FORMAT_CUR_SIGLE = "###,###.00 ¤";
	public static final String C_FORMAT_NUM_2 = "###,###.00";
	public static final String C_FORMAT_NUM_3 = "###,###.000";
	public static final String C_FORMAT_POURCENT = "###,###.00 %";
	
	public static final String C_LIB_BTNFERMER = "Fermer [F4]";
	public static final String C_LIB_BTNANNULER = "Annuler [ESC]";
	public static final String C_LIB_BTNINSERER = "Insérer [F6]";
	public static final String C_LIB_BTNMODIFIER = "Modifier [F2]";
	public static final String C_LIB_BTNSUPPRIMER = "Supprimer [F10]";
	public static final String C_LIB_BTNIMPRIMER = "Imprimer [F11]";
	public static final String C_LIB_BTNENREGISTRER = "Enregistrer [F3]";
	public static final String C_LIB_BTNAIDE = "Aide [F1]";
	public static final String C_LIB_BTNREFRESH = "Rafraîchir [F5]";
	public static final String C_LIB_BTNRECHERCHER = "Rechercher [F3]";
	
	//param par défaut pour la connexion à la base de données
	public static final String C_USER_BDD_DEFAUT        = "SYSDBA";
	public static final String C_PASS_BDD_DEFAUT        = "masterkey";
	public static final String C_URL_BASE_BDD_DEFAUT    = "jdbc:firebirdsql://localhost/";
	public static final String C_DRIVER_JDBC_BDD_DEFAUT = "org.firebirdsql.jdbc.FBDriver";	
	
	private static Component focusedLgr = null;
	
	public static Component getFocusedLgr() {
		return LgrConstantes.focusedLgr;
	}
	
	public static void setFocusedLgr(Component focusedLgr) {
		LgrConstantes.focusedLgr = focusedLgr;
	}
	
	public static final Color fondPanel = new Color(251,250,247); //gris blanc
	public static final Color fondCompSaisie = new Color(239,237,233);  //gris/orange très clair
	public static final Color fondBouton = new Color(238,155,0); //orange
	public static final Color quadrillage = new Color(199,195,187); //et bordure bouton => gris 
	public static final Color champsPointe = new Color(242,230,207); //beige/orange clair
	public static final Color blanc = new Color(255,255,255);
	public static final Color noir = new Color(0,0,0);
		
	public static final Border bordureBtn = new LineBorder(quadrillage,1,true);
	public static final Border bordureChampsSasie = new LineBorder(fondBouton,1,true);
	
}
