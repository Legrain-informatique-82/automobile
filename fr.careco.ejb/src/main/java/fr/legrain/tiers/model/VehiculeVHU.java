package fr.legrain.tiers.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.legrain.tiers.dao.jpa.ILgrEntity;
import fr.legrain.tiers.dao.jpa.MyListener;

@Entity
@Table(name = "VehiculeVHU")
@EntityListeners({MyListener.class})
public class VehiculeVHU implements java.io.Serializable, ILgrEntity {

	private static final long serialVersionUID = 1601634337224451460L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="numeroLivrePolice")
	private String numeroLivrePolice;
	
	@Column(name="immatriculation")
	private String immatriculation;
	
	@Column(name="vin")
	private String vin;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "company")
	private UserCompany company;

	@Column(name="cacheAAA")
	private Integer cacheAAA;
	
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
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="versionObj")
	private Integer versionObj;

	public Integer getId() {
		return id;
	}

	public String getNumeroLivrePolice() {
		return numeroLivrePolice;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public String getVin() {
		return vin;
	}

	public UserCompany getCompany() {
		return company;
	}

	public Integer getCacheAAA() {
		return cacheAAA;
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

	public String getIp() {
		return ip;
	}

	public Integer getVersionObj() {
		return versionObj;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumeroLivrePolice(String numeroLivrePolice) {
		this.numeroLivrePolice = numeroLivrePolice;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public void setCompany(UserCompany company) {
		this.company = company;
	}

	public void setCacheAAA(Integer cacheAAA) {
		this.cacheAAA = cacheAAA;
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

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

}
