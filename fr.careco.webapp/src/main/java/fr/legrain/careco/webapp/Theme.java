package fr.legrain.careco.webapp;

public class Theme {
	
	private String nom;
	private String preview;
	
	
	public Theme(String nom,String preview) {
		super();
		this.nom = nom;
		this.preview = preview;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	
	

}
