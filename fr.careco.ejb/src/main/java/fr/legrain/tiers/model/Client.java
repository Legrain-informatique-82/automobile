package fr.legrain.tiers.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.legrain.tiers.dao.jpa.ILgrEntity;
import fr.legrain.tiers.dao.jpa.MyListener;

@Entity
@Table(name = "Client")
@EntityListeners({MyListener.class})
public class Client implements java.io.Serializable, ILgrEntity {
	
	private static final long serialVersionUID = 5785522852516952468L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="type")
	private String type; //(BtoB ou BtoC radio)
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="immatriculation")
	private String immatriculation;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="societe")
	private String societe; //(ou id societe car liste/combo)
	
	@Column(name="telephoneFixe")
	private String telephoneFixe;
	
	@Column(name="commentaireTelFixe")
	private String commentaireTelFixe;
	
	@Column(name="telephonePortable")
	private String telephonePortable;
	
	@Column(name="commentairePortable")
	private String commentairePortable;
	
	@Column(name="fax")
	private String fax;
	
	@Column(name="numCarteClient")
	private String numCarteClient;
	
	@Column(name="numCompteClient")
	private String numCompteClient;
	
	@Column(name="email")
	private String email;
	
	@Column(name="adresseFacturation")
	private String adresseFacturation;
	
	@Column(name="adresse2Facturation")
	private String adresse2Facturation;
	
	@Column(name="codePostalFacturation")
	private String codePostalFacturation;
	
	@Column(name="villeFacturation")
	private String villeFacturation;
	
	@Column(name="paysFacturation")
	private String paysFacturation;

	@Column(name="adresseLivraison")
	private String adresseLivraison;
	
	@Column(name="adresse2Livraison")
	private String adresse2Livraison;
	
	@Column(name="codePostalLivraison")
	private String codePostalLivraison;
	
	@Column(name="villeLivraison")
	private String villeLivraison;
	
	@Column(name="paysLivraison")
	private String paysLivraison;
	
	@Column(name="tva", scale = 2, precision = 9)
	private BigDecimal tva; //(0 ou 9,8 ou 19,6 radio =>  table TVA ?) 
	
	@Column(name="quanCree")
	@Temporal(TemporalType.TIMESTAMP)
	private Date quanCree;
	
	@Column(name="quandModif")
	@Temporal(TemporalType.TIMESTAMP)
	private Date quandModif;
	
	@Column(name="quiCree")
	private Integer quiCree;
	
	@Column(name="quiModif")
	private Integer quiModif;
	
	@Column(name="tableOrigine")
	private String tableOrigine;
	
	@Column(name="idOrigine")
	private Integer idOrigine;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="versionObj")
	private Integer versionObj;

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

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Date getQuanCree() {
		return quanCree;
	}

	public Date getQuandModif() {
		return quandModif;
	}

	public Integer getQuiCree() {
		return quiCree;
	}

	public Integer getQuiModif() {
		return quiModif;
	}

	public String getTableOrigine() {
		return tableOrigine;
	}

	public Integer getIdOrigine() {
		return idOrigine;
	}

	public String getIp() {
		return ip;
	}

	public Integer getVersionObj() {
		return versionObj;
	}

	public void setQuanCree(Date quanCree) {
		this.quanCree = quanCree;
	}

	public void setQuandModif(Date quandModif) {
		this.quandModif = quandModif;
	}

	public void setQuiCree(Integer quiCree) {
		this.quiCree = quiCree;
	}

	public void setQuiModif(Integer quiModif) {
		this.quiModif = quiModif;
	}

	public void setTableOrigine(String tableOrigine) {
		this.tableOrigine = tableOrigine;
	}

	public void setIdOrigine(Integer idOrigine) {
		this.idOrigine = idOrigine;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	public String getAdresse2Facturation() {
		return adresse2Facturation;
	}

	public String getCodePostalFacturation() {
		return codePostalFacturation;
	}

	public String getVilleFacturation() {
		return villeFacturation;
	}

	public String getPaysFacturation() {
		return paysFacturation;
	}

	public String getAdresse2Livraison() {
		return adresse2Livraison;
	}

	public String getCodePostalLivraison() {
		return codePostalLivraison;
	}

	public String getVilleLivraison() {
		return villeLivraison;
	}

	public String getPaysLivraison() {
		return paysLivraison;
	}

	public void setAdresse2Facturation(String adresse2Facturation) {
		this.adresse2Facturation = adresse2Facturation;
	}

	public void setCodePostalFacturation(String codePostalFacturation) {
		this.codePostalFacturation = codePostalFacturation;
	}

	public void setVilleFacturation(String villeFacturation) {
		this.villeFacturation = villeFacturation;
	}

	public void setPaysFacturation(String paysFacturation) {
		this.paysFacturation = paysFacturation;
	}

	public void setAdresse2Livraison(String adresse2Livraison) {
		this.adresse2Livraison = adresse2Livraison;
	}

	public void setCodePostalLivraison(String codePostalLivraison) {
		this.codePostalLivraison = codePostalLivraison;
	}

	public void setVilleLivraison(String villeLivraison) {
		this.villeLivraison = villeLivraison;
	}

	public void setPaysLivraison(String paysLivraison) {
		this.paysLivraison = paysLivraison;
	}
	
}
