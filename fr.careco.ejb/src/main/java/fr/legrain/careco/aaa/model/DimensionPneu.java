package fr.legrain.careco.aaa.model;

public class DimensionPneu {
	private String dimensionComplete;
	private String largeur;
	private String hauteur;
	private String strucutureDuPneu;
	private String diametre;
	private String indiceCharge;
	private String indiceVitesse;
	
	public DimensionPneu(String dimensionComplete) {
		this.dimensionComplete = dimensionComplete;
		//175.65R14.82T
		// autre format constaté à gérer : 185/65 R 15 81Tsur l'immat ax456ax
		
		int firstCharPos = -1;
		if(dimensionComplete.indexOf(".")!=-1) {
			//la chaine est à priori au bon format
			//175.65R14.82T
			largeur = dimensionComplete.substring(0,dimensionComplete.indexOf("."));

			for (int i = 0; i < dimensionComplete.length(); i++) {
				if(Character.isLetter(dimensionComplete.charAt(i))) {
					firstCharPos=i;
					break;
				}
			}

			hauteur = dimensionComplete.substring(dimensionComplete.indexOf(".")+1,firstCharPos);
			strucutureDuPneu = dimensionComplete.substring(firstCharPos,firstCharPos+1);
			diametre = dimensionComplete.substring(firstCharPos+1,dimensionComplete.lastIndexOf("."));
			indiceCharge = dimensionComplete.substring(dimensionComplete.lastIndexOf(".")+1,dimensionComplete.length()-1);
			indiceVitesse = dimensionComplete.substring(dimensionComplete.length()-1);
		}
	}
	
	public DimensionPneu() {
		
	}
	
	
	public String getLargeur() {
		return largeur;
	}
	public String getHauteur() {
		return hauteur;
	}
	public String getStrucutureDuPneu() {
		return strucutureDuPneu;
	}
	public String getDiametre() {
		return diametre;
	}
	public String getIndiceCharge() {
		return indiceCharge;
	}
	public String getIndiceVitesse() {
		return indiceVitesse;
	}
	public void setLargeur(String largeur) {
		this.largeur = largeur;
	}
	public void setHauteur(String hauteur) {
		this.hauteur = hauteur;
	}
	public void setStrucutureDuPneu(String strucutureDuPneu) {
		strucutureDuPneu = strucutureDuPneu;
	}
	public void setDiametre(String diamètre) {
		this.diametre = diamètre;
	}
	public void setIndiceCharge(String indiceCharge) {
		this.indiceCharge = indiceCharge;
	}
	public void setIndiceVitesse(String indiceVitesse) {
		this.indiceVitesse = indiceVitesse;
	}

	public String getDimensionComplete() {
		return dimensionComplete;
	}

	public void setDimensionComplete(String dimensionComplete) {
		this.dimensionComplete = dimensionComplete;
	}
	
	
}
