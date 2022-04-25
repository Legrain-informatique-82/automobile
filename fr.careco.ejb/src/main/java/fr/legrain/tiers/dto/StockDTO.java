package fr.legrain.tiers.dto;

import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.bdg.model.ModelObject;

public class StockDTO  extends ModelObject implements java.io.Serializable {


	private static final long serialVersionUID = -2681815321723594828L;
	
	private Integer id;
	private Integer idStock;
	private Boolean disponibilite;
	private String typeDePiece;
	private String refConstructeur;
	private String CNITTypeMine;
	private String marque;
	private String modele;
	private String version;
	private Integer kms;
	private String empl;
	private String vendeur;
	private Integer garantie;
	private BigDecimal prixCareco;
	private BigDecimal prixVente;
	
	private String numLivrePolice;
	private String immatriculation;
	private Boolean export;
	private Boolean demonte;
	private Date date1erMiseEnCirculation;
	private BigDecimal prixAchat;
	private String emplacementCasier;
	private String numeroDeSeriePiece;
	private BigDecimal prixMinimum;
	private BigDecimal prixVenteConseille;
	private String commentaireInterne;
	private String commentaireCommercial;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
	}
	public String getTypeDePiece() {
		return typeDePiece;
	}
	public void setTypeDePiece(String typeDePiece) {
		this.typeDePiece = typeDePiece;
	}
	public String getRefConstructeur() {
		return refConstructeur;
	}
	public void setRefConstructeur(String refConstructeur) {
		this.refConstructeur = refConstructeur;
	}
	public String getCNITTypeMine() {
		return CNITTypeMine;
	}
	public void setCNITTypeMine(String cNITTypeMine) {
		CNITTypeMine = cNITTypeMine;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getKms() {
		return kms;
	}
	public void setKms(Integer kms) {
		this.kms = kms;
	}
	public String getEmpl() {
		return empl;
	}
	public void setEmpl(String empl) {
		this.empl = empl;
	}
	public String getVendeur() {
		return vendeur;
	}
	public void setVendeur(String vendeur) {
		this.vendeur = vendeur;
	}
	public Integer getGarantie() {
		return garantie;
	}
	public void setGarantie(Integer garantie) {
		this.garantie = garantie;
	}
	public BigDecimal getPrixCareco() {
		return prixCareco;
	}
	public void setPrixCareco(BigDecimal prixCareco) {
		this.prixCareco = prixCareco;
	}
	public BigDecimal getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(BigDecimal prixVente) {
		this.prixVente = prixVente;
	}
	public String getNumLivrePolice() {
		return numLivrePolice;
	}
	public void setNumLivrePolice(String numLivrePolice) {
		this.numLivrePolice = numLivrePolice;
	}
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public Boolean getExport() {
		return export;
	}
	public void setExport(Boolean export) {
		this.export = export;
	}
	public Boolean getDemonte() {
		return demonte;
	}
	public void setDemonte(Boolean demonte) {
		this.demonte = demonte;
	}
	public Date getDate1erMiseEnCirculation() {
		return date1erMiseEnCirculation;
	}
	public void setDate1erMiseEnCirculation(Date date1erMiseEnCirculation) {
		this.date1erMiseEnCirculation = date1erMiseEnCirculation;
	}
	public BigDecimal getPrixAchat() {
		return prixAchat;
	}
	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}
	public String getEmplacementCasier() {
		return emplacementCasier;
	}
	public void setEmplacementCasier(String emplacementCasier) {
		this.emplacementCasier = emplacementCasier;
	}
	public String getNumeroDeSeriePiece() {
		return numeroDeSeriePiece;
	}
	public void setNumeroDeSeriePiece(String numeroDeSeriePiece) {
		this.numeroDeSeriePiece = numeroDeSeriePiece;
	}
	public BigDecimal getPrixMinimum() {
		return prixMinimum;
	}
	public void setPrixMinimum(BigDecimal prixMinimum) {
		this.prixMinimum = prixMinimum;
	}
	public BigDecimal getPrixVenteConseille() {
		return prixVenteConseille;
	}
	public void setPrixVenteConseille(BigDecimal prixVenteConseille) {
		this.prixVenteConseille = prixVenteConseille;
	}
	public String getCommentaireInterne() {
		return commentaireInterne;
	}
	public void setCommentaireInterne(String commentaireInterne) {
		this.commentaireInterne = commentaireInterne;
	}
	public String getCommentaireCommercial() {
		return commentaireCommercial;
	}
	public void setCommentaireCommercial(String commentaireCommercial) {
		this.commentaireCommercial = commentaireCommercial;
	}
	public Integer getIdStock() {
		return idStock;
	}
	public void setIdStock(Integer idStock) {
		this.idStock = idStock;
	}
	
	
	
}
