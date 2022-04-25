package fr.legrain.tiers.dto;

import fr.legrain.bdg.model.ModelObject;
import fr.legrain.tiers.model.Panier;
import fr.legrain.tiers.model.Stock;

public class LignePanierDTO  extends ModelObject implements java.io.Serializable {


	private static final long serialVersionUID = 5911873230554329011L;
	
	private Integer id;
	private String idTransaction;
	private Stock idPiece;
	private Panier panier;
	private String proprietaire; //id ?
	private String marque;
	private String modele;
	private String type;
	private String typePiece;
	private String dateDateDernierChangementEtat;
	private String cnit;
	private String vin;
	private String prixAchat;
	private String statut; //(Approuvé,)
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}
	public Stock getIdPiece() {
		return idPiece;
	}
	public void setIdPiece(Stock idPiece) {
		this.idPiece = idPiece;
	}
	public Panier getPanier() {
		return panier;
	}
	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypePiece() {
		return typePiece;
	}
	public void setTypePiece(String typePiece) {
		this.typePiece = typePiece;
	}
	public String getDateDateDernierChangementEtat() {
		return dateDateDernierChangementEtat;
	}
	public void setDateDateDernierChangementEtat(
			String dateDateDernierChangementEtat) {
		this.dateDateDernierChangementEtat = dateDateDernierChangementEtat;
	}
	public String getCnit() {
		return cnit;
	}
	public void setCnit(String cnit) {
		this.cnit = cnit;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getPrixAchat() {
		return prixAchat;
	}
	public void setPrixAchat(String prixAchat) {
		this.prixAchat = prixAchat;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
	
}
