package fr.legrain.lib.data;

public class ValeurChamps {
	private String valeur = null;
	private String champs = null;
	
	public ValeurChamps(){
		
	}
	public ValeurChamps(String valeur,String champs){
		this.champs=champs;
		this.valeur=valeur;
	}
	public String getChamps() {
		return champs;
	}
	public void setChamps(String champs) {
		this.champs = champs;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
}

