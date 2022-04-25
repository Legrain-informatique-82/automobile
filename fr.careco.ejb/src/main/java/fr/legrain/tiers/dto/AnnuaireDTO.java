package fr.legrain.tiers.dto;

import fr.legrain.bdg.model.ModelObject;

public class AnnuaireDTO  extends ModelObject implements java.io.Serializable {
	
	private static final long serialVersionUID = 8574733171349367852L;
	
	private Integer id;
	private String centre;
	private String adherent;
	private String adresse;
	private String cp;
	private String ville;
	private String telephone;
	private String telecopie;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCentre() {
		return centre;
	}
	public void setCentre(String centre) {
		this.centre = centre;
	}
	public String getAdherent() {
		return adherent;
	}
	public void setAdherent(String adherent) {
		this.adherent = adherent;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTelecopie() {
		return telecopie;
	}
	public void setTelecopie(String telecopie) {
		this.telecopie = telecopie;
	}
	
	
}
