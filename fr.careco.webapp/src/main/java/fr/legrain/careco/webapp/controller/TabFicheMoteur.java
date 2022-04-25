package fr.legrain.careco.webapp.controller;

import java.util.Date;

public class TabFicheMoteur {

	private String CNIT;
	private boolean compatible;
	private String typeMoteur;
	private String commentaire;
	private Date de;
	private Date a;
	private String idFiche;
	private String compatMoteurClub;
	
	public TabFicheMoteur() {
		
	}
	
	public TabFicheMoteur(String cNIT, boolean compatible, String codeMoteur,
			String commentaire) {
		super();
		CNIT = cNIT;
		this.compatible = compatible;
		this.typeMoteur = codeMoteur;
		this.commentaire = commentaire;
	}
	
	public TabFicheMoteur(String cNIT, boolean compatible, String codeMoteur,
			String commentaire, Date de, Date a, String idFiche,String compatMoteurClub) {
		super();
		CNIT = cNIT;
		this.compatible = compatible;
		this.typeMoteur = codeMoteur;
		this.commentaire = commentaire;
		this.de = de;
		this.a = a;
		this.idFiche = idFiche;
		this.compatMoteurClub = compatMoteurClub;
	}
	
	public String getCNIT() {
		return CNIT;
	}
	public void setCNIT(String cNIT) {
		CNIT = cNIT;
	}
	public boolean isCompatible() {
		return compatible;
	}
	public void setCompatible(boolean compatible) {
		this.compatible = compatible;
	}
	public String getTypeMoteur() {
		return typeMoteur;
	}
	public void setTypeMoteur(String codeMoteur) {
		this.typeMoteur = codeMoteur;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getIdFiche() {
		return idFiche;
	}

	public void setIdFiche(String idFiche) {
		this.idFiche = idFiche;
	}

	public Date getDe() {
		return de;
	}

	public void setDe(Date de) {
		this.de = de;
	}

	public Date getA() {
		return a;
	}

	public void setA(Date a) {
		this.a = a;
	}

	public String getCompatMoteurClub() {
		return compatMoteurClub;
	}

	public void setCompatMoteurClub(String compatMoteurClub) {
		this.compatMoteurClub = compatMoteurClub;
	}
	
	
}
