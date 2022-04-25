package fr.legrain.lib.data;

public class VerrouInterface {
	static private VerrouInterface verrouInterface = null;
	static private boolean verrouille = false;
	
	public static VerrouInterface getVerrouInterface(){
		if (verrouInterface==null) verrouInterface=new VerrouInterface();
		return verrouInterface;
	}
	
	private VerrouInterface(){}

	public static boolean isVerrouille() {
		return verrouille;
	}

	public static void setVerrouille(boolean verrouille) {
		getVerrouInterface().verrouille = verrouille;
	}

}
