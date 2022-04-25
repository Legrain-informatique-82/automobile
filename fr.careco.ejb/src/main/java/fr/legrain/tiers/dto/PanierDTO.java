package fr.legrain.tiers.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import fr.legrain.bdg.model.ModelObject;
import fr.legrain.tiers.model.LignePanier;

public class PanierDTO  extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = -2571794018405801610L;
	
	private Integer id;
	private String validite;
	private String client;
	private Date datePanier;
	private Date dateFin;
	private String vendeur;
	private Integer nbPiece;
	private Boolean devis;
	private BigDecimal prixHT;
	private BigDecimal tva;
	private BigDecimal totalTTC;
	private Boolean actif;
	//private List<LignePanier> lignes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValidite() {
		return validite;
	}
	public void setValidite(String validite) {
		this.validite = validite;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Date getDatePanier() {
		return datePanier;
	}
	public void setDatePanier(Date datePanier) {
		this.datePanier = datePanier;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public String getVendeur() {
		return vendeur;
	}
	public void setVendeur(String vendeur) {
		this.vendeur = vendeur;
	}
	public Integer getNbPiece() {
		return nbPiece;
	}
	public void setNbPiece(Integer nbPiece) {
		this.nbPiece = nbPiece;
	}
	public Boolean getDevis() {
		return devis;
	}
	public void setDevis(Boolean devis) {
		this.devis = devis;
	}
	public BigDecimal getPrixHT() {
		return prixHT;
	}
	public void setPrixHT(BigDecimal prixHT) {
		this.prixHT = prixHT;
	}
	public BigDecimal getTva() {
		return tva;
	}
	public void setTva(BigDecimal tva) {
		this.tva = tva;
	}
	public BigDecimal getTotalTTC() {
		return totalTTC;
	}
	public void setTotalTTC(BigDecimal totalTTC) {
		this.totalTTC = totalTTC;
	}
	public Boolean getActif() {
		return actif;
	}
	public void setActif(Boolean actif) {
		this.actif = actif;
	}
//	public List<LignePanier> getLignes() {
//		return lignes;
//	}
//	public void setLignes(List<LignePanier> lignes) {
//		this.lignes = lignes;
//	}
	
	
}
