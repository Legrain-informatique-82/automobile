package fr.legrain.tiers.model;

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
@Table(name = "TransactionAchatVente")
@EntityListeners({MyListener.class})
public class TransactionAchatVente implements java.io.Serializable, ILgrEntity {

	private static final long serialVersionUID = 9091596303180472179L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="etatVendeur")
	private String etatVendeur;
	
	@Column(name="etatAcheteur")
	private String etatAcheteur;
	
	@Column(name="derierChangementEtat")
	@Temporal(value = TemporalType.DATE)
	private Date derierChangementEtat;
	
	@Column(name="termine")
	private Boolean termine;
	
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

	public String getEtatVendeur() {
		return etatVendeur;
	}

	public void setEtatVendeur(String etatVendeur) {
		this.etatVendeur = etatVendeur;
	}

	public String getEtatAcheteur() {
		return etatAcheteur;
	}

	public void setEtatAcheteur(String etatAcheteur) {
		this.etatAcheteur = etatAcheteur;
	}

	public Date getDerierChangementEtat() {
		return derierChangementEtat;
	}

	public void setDerierChangementEtat(Date derierChangementEtat) {
		this.derierChangementEtat = derierChangementEtat;
	}

	public Boolean getTermine() {
		return termine;
	}

	public void setTermine(Boolean termine) {
		this.termine = termine;
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

}
