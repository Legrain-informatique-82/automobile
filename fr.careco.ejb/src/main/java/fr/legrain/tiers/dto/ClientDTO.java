package fr.legrain.tiers.dto;

import java.math.BigDecimal;

import fr.legrain.bdg.model.ModelObject;

public class ClientDTO  extends ModelObject implements java.io.Serializable {


	private static final long serialVersionUID = -4204212621816520176L;
	
	private Integer id;
	private String type; //(BtoB ou BtoC radio)
	private String nom;
	private String prenom;
	private String societe; //(ou id societe car liste/combo)
	private String telephoneFixe;
	private String commentaireTelFixe;
	private String telephonePortable;
	private String commentairePortable;
	private String fax;
	private String numCarteClient;
	private String numCompteClient;
	private String email;
	private String adresseFacturation;
	private String adresseLivraison;
	private BigDecimal tva;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public String getTelephoneFixe() {
		return telephoneFixe;
	}
	public void setTelephoneFixe(String telephoneFixe) {
		this.telephoneFixe = telephoneFixe;
	}
	public String getCommentaireTelFixe() {
		return commentaireTelFixe;
	}
	public void setCommentaireTelFixe(String commentaireTelFixe) {
		this.commentaireTelFixe = commentaireTelFixe;
	}
	public String getTelephonePortable() {
		return telephonePortable;
	}
	public void setTelephonePortable(String telephonePortable) {
		this.telephonePortable = telephonePortable;
	}
	public String getCommentairePortable() {
		return commentairePortable;
	}
	public void setCommentairePortable(String commentairePortable) {
		this.commentairePortable = commentairePortable;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getNumCarteClient() {
		return numCarteClient;
	}
	public void setNumCarteClient(String numCarteClient) {
		this.numCarteClient = numCarteClient;
	}
	public String getNumCompteClient() {
		return numCompteClient;
	}
	public void setNumCompteClient(String numCompteClient) {
		this.numCompteClient = numCompteClient;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresseFacturation() {
		return adresseFacturation;
	}
	public void setAdresseFacturation(String adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}
	public String getAdresseLivraison() {
		return adresseLivraison;
	}
	public void setAdresseLivraison(String adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}
	public BigDecimal getTva() {
		return tva;
	}
	public void setTva(BigDecimal tva) {
		this.tva = tva;
	}
}
