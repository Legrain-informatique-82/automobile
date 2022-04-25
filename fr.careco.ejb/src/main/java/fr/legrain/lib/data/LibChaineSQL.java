package fr.legrain.lib.data;


public final class LibChaineSQL {
	
	public static String formatStringSQL(String chaine){
		chaine=chaine.replace("'","''");
		return chaine;
	}
	
	public static String EnleveRetourChariot(String chaine){
		if(chaine!=null){
			String RC = System.getProperty("line.separator");
			chaine=chaine.replaceAll(RC,"");
		}
		return chaine;
	}
}
