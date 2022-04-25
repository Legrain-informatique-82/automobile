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
@Table(name = "GarantieCareco")
@EntityListeners({MyListener.class})
public class GarantieCareco implements java.io.Serializable, ILgrEntity {
	
	private static final long serialVersionUID = -8929636743919606499L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nomContrat12")
	private String nomContrat12;
	
	@Column(name="nomContrat24")
	private String nomContrat24;
	
 	@Column(name="prixVentePieceDebut")
	private BigDecimal prixVentePieceDebut;
 	
 	@Column(name="prixVentePiecefin")
	private BigDecimal prixVentePiecefin;
 	
 	@Column(name="prixPE12HT")
	private BigDecimal prixPE12HT;
 	
 	@Column(name="prixPE12TTC")
	private BigDecimal prixPE12TTC;
 	
 	@Column(name="prixPE24HT")
	private BigDecimal prixPE24HT;
 	
 	@Column(name="prixPE24TTC")
	private BigDecimal prixPE24TTC;
 	
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

	public String getNomContrat12() {
		return nomContrat12;
	}

	public void setNomContrat12(String nomContrat12) {
		this.nomContrat12 = nomContrat12;
	}

	public String getNomContrat24() {
		return nomContrat24;
	}

	public void setNomContrat24(String nomContrat24) {
		this.nomContrat24 = nomContrat24;
	}

	public BigDecimal getPrixVentePieceDebut() {
		return prixVentePieceDebut;
	}

	public void setPrixVentePieceDebut(BigDecimal prixVentePieceDebut) {
		this.prixVentePieceDebut = prixVentePieceDebut;
	}

	public BigDecimal getPrixVentePiecefin() {
		return prixVentePiecefin;
	}

	public void setPrixVentePiecefin(BigDecimal prixVentePiecefin) {
		this.prixVentePiecefin = prixVentePiecefin;
	}

	public BigDecimal getPrixPE12HT() {
		return prixPE12HT;
	}

	public void setPrixPE12HT(BigDecimal prixPE12HT) {
		this.prixPE12HT = prixPE12HT;
	}

	public BigDecimal getPrixPE12TTC() {
		return prixPE12TTC;
	}

	public void setPrixPE12TTC(BigDecimal prixPE12TTC) {
		this.prixPE12TTC = prixPE12TTC;
	}

	public BigDecimal getPrixPE24HT() {
		return prixPE24HT;
	}

	public void setPrixPE24HT(BigDecimal prixPE24HT) {
		this.prixPE24HT = prixPE24HT;
	}

	public BigDecimal getPrixPE24TTC() {
		return prixPE24TTC;
	}

	public void setPrixPE24TTC(BigDecimal prixPE24TTC) {
		this.prixPE24TTC = prixPE24TTC;
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
