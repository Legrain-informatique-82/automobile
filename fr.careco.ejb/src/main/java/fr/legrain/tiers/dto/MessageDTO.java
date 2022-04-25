package fr.legrain.tiers.dto;

import fr.legrain.bdg.model.ModelObject;

public class MessageDTO  extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = 4970589302078618631L;

	private Integer id;
	
	private String de;
	private String a;
	private String sujet;
	private String message;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
