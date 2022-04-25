package fr.legrain.tiers.dto;

import fr.legrain.bdg.model.ModelObject;

public class UserCompanyDTO  extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = 9106348516624539939L;
	
	private Integer id;
	private String nom;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
