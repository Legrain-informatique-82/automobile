package fr.legrain.lib.data;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;


public final class LibChaine {
	
	private static List<String> listeFormat = null;
	
	private static List<String> listeDemisMinus = null;
	
	private static List<String> listeKeyRejetees = null;
	public static final char[] C_UNAUTHORIZED_CHAR_FILE_IN_NAME = new char[] {'\\','/',':','*','?','\"','<','>','|'};
	public static final char[] C_UNAUTHORIZED_CHAR_PROJET_NAME = new char[]  {' ','\\','/',':','*','?','\"','<','>','|',/*'é','à','ù','è',*/'^','~','#','{','}','[',']','`'/*,'¤'*/};
	private static void initListeFormat(){
		if (listeFormat==null){
			listeFormat = new ArrayList();
			DecimalFormatSymbols decimalSeparateur = new DecimalFormatSymbols();
			listeFormat.add(String.valueOf(decimalSeparateur.getDecimalSeparator()));
			listeFormat.add(decimalSeparateur.getCurrencySymbol());
			listeFormat.add(String.valueOf(decimalSeparateur.getGroupingSeparator()));
			listeFormat.add(String.valueOf(decimalSeparateur.getMinusSign()));
			listeFormat.add(String.valueOf(decimalSeparateur.getMonetaryDecimalSeparator()));
			listeFormat.add(decimalSeparateur.getInternationalCurrencySymbol());
			listeFormat.add("+");
			//listeFormat.add(",");
			listeFormat.add(".");
		}
			
			if (listeDemisMinus==null){
				listeDemisMinus = new ArrayList();
			listeDemisMinus.add("|");
			listeDemisMinus.add(":");
			listeDemisMinus.add(".");
			
		}
	}
	
	

	
	
	/**
	 * Nettoyage d'une chaine de caractère
	 * @param unauthorizedChar - char[] caractère interdit
	 * @param chaine - chaine à nettoyer
	 * @param replacingChar - char caractère de remplacement
	 * @return chaine nettoyée
	 */
	public static String clearString(String chaine, char[] unauthorizedChar, char replacingChar) {
		for (int i = 0; i < unauthorizedChar.length; i++) {
			chaine = chaine.replace(unauthorizedChar[i],replacingChar);
		}
		return chaine;
	}
	
	public static String toUrlRewriting(String chaine){
		chaine = clearString(chaine, new char[] {'.','*','$',';',',','?',':','/','|','!','@','&','#',
				'(',')','[',']','{','}','"','\'','+','°','€','}','<','>','=','\\'},'-'); //remplace les caractères non autorisés par des "-"
		chaine = StringUtils.lowerCase(chaine); //tout en minuscule
		chaine = StringUtils.stripEnd(chaine,"0123456789"); //supprime les chiffres à la fin
		chaine = StringUtils.replace(chaine," ","-"); //remplace les espaces par des "-"
		chaine = StringUtils.replace(chaine,"é","e");
		chaine = StringUtils.replace(chaine,"è","e");
		chaine = StringUtils.replace(chaine,"ê","e");
		chaine = StringUtils.replace(chaine,"à","a");
		chaine = StringUtils.replace(chaine,"ç","c");
		chaine = StringUtils.replace(chaine,"ù","u");
		chaine = StringUtils.deleteWhitespace(chaine); //supprime les espaces
		chaine = StringUtils.stripEnd(chaine,"-"); //supprime le "-" final s'il existe
		return chaine;
	}
	
	public static boolean formatChiffre(String chaine){
		boolean trouve=false;	
		initListeFormat();
		for (String S : listeFormat) {
			if(S.equals(chaine)) return true;	
		}		
		return trouve;
	}
	
	public static boolean formatDemisMinus(String chaine){
		boolean trouve=false;	
		initListeFormat();
		for (String S : listeDemisMinus) {
			if(S.equals(chaine)) return true;	
		}		
		return trouve;
	}
	
	
	private static void initListeKeyRejetees(){
		if (listeKeyRejetees==null){
			listeKeyRejetees = new ArrayList();
			listeKeyRejetees.add(";");
			//listeKeyRejetees.add("'");
		}
	}
	
	public static boolean keyAutorise(String chaine){
		boolean valide=true;	
		initListeKeyRejetees();
		for (String S : listeKeyRejetees) {
			if(S.equals(chaine)) return false;	
		}		
		return valide;
	}	
	
	public static boolean emptyNumeric(String chaine){
		if (chaine==null)return true;
		if (empty(chaine))return true;
		if (chaine.trim().equals("0")||chaine.trim().equals("0.0")) return true;
		return false;
	}

	public static boolean emptyNumeric(Float chaine){
		if (chaine==null)return true;
		if (chaine.equals(new Float(0))) return true;
		return false;
	}
	
	public static boolean emptyNumeric(BigDecimal chaine){
		if (chaine==null)return true;
		if (chaine.equals(new BigDecimal(0))) return true;
		return false;
	}
	
	public static boolean emptyNumeric(Double chaine){
		if (chaine==null)return true;
		if (chaine.equals(new Double(0))) return true;
		return false;
	}
	
	public static boolean emptyNumeric(Integer chaine){
		if (chaine==null)return true;
		if (chaine.equals(0)) return true;
		return false;
	}	
	

	public static boolean emptyNumeric(Date chaine){
		if (chaine==null)return true;
		if (chaine.equals(0)) return true;
		return false;
	}
	
	
	public static boolean empty(String chaine){
		if (chaine==null)return true;
		if(chaine.trim().equals(""))return true;
		return false;
	}

	public static Boolean empty(Boolean chaine){
		if (chaine==null)return false;
		return chaine;
	}
	public static String lgrStringNonNull(String chaine){
		if (chaine==null)return "";
		else
		return chaine;
	}	
	
	public static String pathCorrect(String path){
		if(path.startsWith("/"))path = path.replaceFirst("/", "");
		return path;		
	}
}
