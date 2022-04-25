package fr.legrain.validator.common;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.LgrConstantes;
import fr.legrain.lib.data.LgrLocale;
import fr.legrain.lib.data.LibChaine;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.LibDate;
import fr.legrain.lib.data.MessCtrlLgr;
import fr.legrain.lib.data.MessUtilisateur;

public class JPACtrlChampSimple {

	static Logger logger = Logger.getLogger(JPACtrlChampSimple.class.getName());

	/*static*/ private HashMap listeTableBD=new HashMap(); //Liste des tables et des vues présentes dans la base
	/*static*/ private Properties listeIDBD=new Properties();
	public MessUtilisateur messUtilisateur;

	private boolean afficheMessage;
	private String fichierIniID;

	/**
	 * cle primaire de chaque table
	 * @return String - Chemin du fichier contenant les identifiants primaire de chaque table
	 */
	public String getFichierIni_ID(){
		return fichierIniID;
	}

	public JPACtrlChampSimple() {
		super();
		if(listeTableBD==null)
			listeTableBD = new HashMap();
		if (listeIDBD==null)
			listeIDBD = new Properties();
		afficheMessage = true;
	}

	/**
	 * ctrl_ValeurQueDesChiffres N°109
	 * @param message TMessCtrlLgr
	 * @throws TExceptLgr
	 */
	public boolean ctrl_ValeurQueDesChiffres(MessCtrlLgr message,boolean formate) throws ExceptLgr {
		DecimalFormatSymbols decimalSeparateur = new DecimalFormatSymbols();
		String V= "";
		try {/*message.getNomTable()+"."+*/
			if(! LibChaine.emptyNumeric(message.getValeur())){
				message.setMessageAffiche("La valeur du champ : "+message.getNomChamp()+" n'est pas correcte.");//
				if (message.getValeur()!=null){
					for (int i = 0; i < message.getValeur().length(); i++) {
						V =  message.getValeur().substring(i,i+1);
						try {
							if (formate && !LibChaine.formatChiffre(V))							
								LibConversion.stringToInteger(V);
							else if (!formate)
								LibConversion.stringToInteger(V);
						} catch (Exception e) {
							throw new ExceptLgr(message,message.getMessageAffiche(),message.getCodeErreur(),(afficheMessage && !message.isDejaAffiche()),JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
			return true;
		} catch(Exception e) {
			logger.error("Erreur : ctrl_ValeurQueDesChiffres", e);
			return false;
		}
	}

	/**
	 * ctrl_ValeurNumerique
	 * @param message TMessCtrlLgr
	 * @throws TExceptLgr
	 */
	public void ctrl_ValeurNumerique(MessCtrlLgr message) throws ExceptLgr {
		try {
			if(! LibChaine.emptyNumeric(message.getValeur())){
				if (ctrl_ValeurQueDesChiffres(message,true)){
					message.setMessageAffiche("La valeur : "+message.getValeur()+" n'est pas une valeur numérique correcte");
					//LibConversion.stringToFloat(message.getValeur());
					//System.out.println("message.getValeur() : "+message.getValeur());
					NumberFormat nf = NumberFormat.getInstance(LgrLocale.decimal);

					Number myNumber = nf.parse(message.getValeur());
					//System.out.println("Number : "+myNumber);
					myNumber = myNumber.floatValue();
					//System.out.println("floatValue : "+myNumber.floatValue());
					//Number.parseFloat(message.getValeur());
				}else{ 
					throw new ExceptLgr();
				}
			}
		} catch(Exception e) {
			logger.error("Erreur : ctrl_ValeurNumerique", e);
			throw new ExceptLgr(message,message.getMessageAffiche(),message.getCodeErreur(),(afficheMessage && !message.isDejaAffiche()),JOptionPane.WARNING_MESSAGE);
		}		
	}

	/**
	 * ctrl_MontantPositif N°106
	 * @param message TMessCtrlLgr
	 * @throws TExceptLgr
	 */
	public void ctrl_MontantPositif(MessCtrlLgr message) throws ExceptLgr {
		try {
			if(! LibChaine.emptyNumeric(message.getValeur())){
				if (ctrl_ValeurQueDesChiffres(message,true)){
					ctrl_ValeurNumerique(message);			
					message.setMessageAffiche("Le champ "+message.getNomChamp()+" est négatif.");//+C_STR_SAUT_DEUX+C_CHAMP_NON_VIDE;
					if ((message.getValeur()==null)||(java.lang.Float.valueOf(message.getValeur()).floatValue()<0)){
						throw new ExceptLgr(message,message.getMessageAffiche(),message.getCodeErreur(),(afficheMessage && !message.isDejaAffiche()),JOptionPane.WARNING_MESSAGE);
					}
				}else {
					throw new ExceptLgr();
				}
			}
		} catch(Exception e) {
			logger.error("Erreur : ctrl_MontantPositif", e);
			throw new ExceptLgr(message,message.getMessageAffiche(),message.getCodeErreur(),(afficheMessage && !message.isDejaAffiche()),JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * ctrl_Boolean N°108
	 * @param message
	 * @throws ExceptLgr
	 */
	public void ctrl_Boolean(MessCtrlLgr message) throws ExceptLgr {
		try {
			message.setMessageAffiche("La valeur du champ : "+message.getNomChamp()+" n'est pas correcte.");
			if (message.getValeur()!=null){
				if(((message.getValeur() == null) || !(message.getValeur().equalsIgnoreCase("true")||
						message.getValeur().equalsIgnoreCase("false")||
						message.getValeur().equalsIgnoreCase("1")||
						message.getValeur().equalsIgnoreCase("0"))))
					throw new ExceptLgr();
			}
		} catch(Exception e) {
			logger.error("Erreur : ctrl_Boolean", e);
			throw new ExceptLgr(message,message.getMessageAffiche(),message.getCodeErreur(),(afficheMessage && !message.isDejaAffiche()),JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * ctrl_ValeursKeyAutorisees N°110
	 * @param message TMessCtrlLgr
	 * @throws TExceptLgr
	 */
	public boolean ctrl_ValeursKeyAutorisees(MessCtrlLgr message) throws ExceptLgr {
		String V= "";
		try {			
//			message.setMessageAffiche("La valeur du champ : "+BdLgr.getTitreChamp(message.getNomChamp())+" n'est pas correcte.");//
			message.setMessageAffiche("La valeur du champ : "+message.getNomChamp()+" n'est pas correcte.");//
			if (message.getValeur()!=null){
				for (int i = 0; i < message.getValeur().length(); i++) {
					V =  message.getValeur().substring(i,i+1);
					try {
						if (!LibChaine.keyAutorise(V))	throw new ExceptLgr();		
					} catch (Exception e) {
						throw new ExceptLgr(message,message.getMessageAffiche(),message.getCodeErreur(),(afficheMessage && !message.isDejaAffiche()),JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			return true;
		} catch(Exception e) {
			logger.error("Erreur : ctrl_ValeursKeyAutorisees", e);
			return false;
		}
	}

	/**
	 * ctrl_Date N°105
	 * @param message TMessCtrlLgr
	 * @throws TExceptLgr
	 */
	public void ctrl_Date(MessCtrlLgr message) throws ExceptLgr {
		java.util.Date dateTmp = null;
		try {
			message.setMessageAffiche("La date "+ message.getValeur() +" n''est pas une date correcte");
			dateTmp=LibDate.stringToDate(message.getValeur());
			if(LibDate.dateToString(dateTmp).equals(""))
				throw new Exception();
			//if(!LibDate.dateToString(dateTmp).equals(message.getValeur()))
			//throw new Exception();
		}
		catch (Exception e ){
			logger.error("Erreur : ctrl_Date", e);
			throw new ExceptLgr(message,message.getMessageAffiche(),message.getCodeErreur(),(afficheMessage && !message.isDejaAffiche()),JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * ctrl_Date N°105
	 * @param message TMessCtrlLgr
	 * @throws TExceptLgr
	 */
	public void ctrl_Date(String date) throws ExceptLgr {
		MessCtrlLgr message = new MessCtrlLgr();
		message.setValeur(date);
		ctrl_Date(message);
	}


	/**
	 * ctrl_EstNonVide N°107 et 102
	 * @param message TMessCtrlLgr
	 * @throws TExceptLgr
	 */
	public void ctrl_EstNonVide(MessCtrlLgr message) throws ExceptLgr {
		try {
			//if ((message.getValeur()==null)||(message.getValeur().equals(LgrConstantes.C_STR_VIDE))) {
			if (LibChaine.empty(message.getValeur())) {	
				message.setMessageAffiche("Le champ "+message.getNomChamp()+" est vide.");
				throw new ExceptLgr();
			}
		}
		catch (ExceptLgr exceptLgr ){
			logger.error("Erreur : ctrl_EstNonVide", exceptLgr);
			throw new ExceptLgr(message,message.getMessageAffiche(),message.getCodeErreur(),(afficheMessage && !message.isDejaAffiche()),JOptionPane.WARNING_MESSAGE);
		}
		catch (Exception e ){
			logger.error("Erreur : ctrl_EstNonVide", e);
			throw new ExceptLgr(LgrConstantes.C_ERREUR_INCONNUE,message.getCodeErreur(),afficheMessage,JOptionPane.WARNING_MESSAGE);//,result);
		}
	}

	/**
	 * ctrl_CodePostal N°104
	 * @param valeur String
	 * @param codeErreur int
	 * @param nomChamp String
	 * @throws TExceptLgr
	 */
	public void ctrl_CodePostal(MessCtrlLgr message) throws ExceptLgr {
		try {
			message.setMessageAffiche("Le code postal " + message.getValeur() + " n''est pas correct");
			if ((message.getValeur()==null)||(message.getValeur().length() < 5))
				throw new ExceptLgr();
			//ejb
			//ctrl_Longueur(message);
		}
		catch (Exception e ){
			logger.error("Erreur : ctrl_CodePostal", e);
			throw new ExceptLgr(message,message.getMessageAffiche(),message.getCodeErreur(),(afficheMessage && !message.isDejaAffiche()),JOptionPane.WARNING_MESSAGE);
		}
	}


	public void ctrl_Majuscule(MessCtrlLgr message) throws ExceptLgr {
		message.setValeur(message.getValeur().toUpperCase());
	}


}
