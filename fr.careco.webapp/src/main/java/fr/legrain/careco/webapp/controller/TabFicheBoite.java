package fr.legrain.careco.webapp.controller;

import java.util.Date;

public class TabFicheBoite {

	private String CNIT;
	private boolean compatible;
	private String refBoite;
	private String commentaire;
	private Date de;
	private Date a;
	private String idFiche;
	private String compatMoteurClub;
	
	public TabFicheBoite() {
		
	}
	
	public TabFicheBoite(String cNIT, boolean compatible, String codeMoteur,
			String commentaire) {
		super();
		CNIT = cNIT;
		this.compatible = compatible;
		this.refBoite = codeMoteur;
		this.commentaire = commentaire;
	}
	
	public TabFicheBoite(String cNIT, boolean compatible, String codeMoteur,
			String commentaire, Date de, Date a, String idFiche, String compatMoteurClub) {
		super();
		CNIT = cNIT;
		this.compatible = compatible;
		this.refBoite = codeMoteur;
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
	public String getRefBoite() {
		return refBoite;
	}
	public void setRefBoite(String codeMoteur) {
		this.refBoite = codeMoteur;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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

	public String getIdFiche() {
		return idFiche;
	}

	public void setIdFiche(String idFiche) {
		this.idFiche = idFiche;
	}

	public String getCompatMoteurClub() {
		return compatMoteurClub;
	}

	public void setCompatMoteurClub(String compatMoteurClub) {
		this.compatMoteurClub = compatMoteurClub;
	}
	
	
}
