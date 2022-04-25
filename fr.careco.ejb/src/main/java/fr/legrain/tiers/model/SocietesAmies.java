package fr.legrain.tiers.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.legrain.tiers.dao.jpa.ILgrEntity;
import fr.legrain.tiers.dao.jpa.MyListener;

@Entity
@Table(name = "SocietesAmies")
@EntityListeners({MyListener.class})
public class SocietesAmies implements java.io.Serializable, ILgrEntity {
	
	private static final long serialVersionUID = 33523459113588531L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	//@EmbeddedId
	//private MyCompositePK id;
	
	//@LazyCollection(LazyCollectionOption.FALSE) //annotation hibernate
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "societeA")
	private UserCompany societeA;
	
	//@Column(name="societeB")
	//@LazyToOne(value = LazyToOneOption.PROXY)
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "societeB")
	private UserCompany societeB;
	
//	//@LazyCollection(LazyCollectionOption.FALSE) //annotation hibernate
//	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinColumns ({
//        @JoinColumn(name="societeA", referencedColumnName = "id", insertable=false ,updatable=false),
//        @JoinColumn(name="societeB", referencedColumnName = "id", insertable=false ,updatable=false)
//    })
//	private UserCompany societeA;
//	
//	//@Column(name="societeB")
//	//@LazyToOne(value = LazyToOneOption.PROXY)
//	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinColumns ({
//        @JoinColumn(name="societeA", referencedColumnName = "id",  insertable=false ,updatable=false),
//        @JoinColumn(name="societeB", referencedColumnName = "id",  insertable=false ,updatable=false)
//    })
//	private UserCompany societeB;
	
	@Column(name="pourcentageReduction")
	private BigDecimal pourcentageReduction;
	
	@Column(name="typeRelation")
	private String typeRelation;
	
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

	public UserCompany getSocieteA() {
		return societeA;
	}

	public UserCompany getSocieteB() {
		return societeB;
	}

	public BigDecimal getPourcentageReduction() {
		return pourcentageReduction;
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

	public void setSocieteA(UserCompany societeA) {
		this.societeA = societeA;
	}

	public void setSocieteB(UserCompany societeB) {
		this.societeB = societeB;
	}

	public void setPourcentageReduction(BigDecimal pourcentageReduction) {
		this.pourcentageReduction = pourcentageReduction;
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

	public String getTypeRelation() {
		return typeRelation;
	}

	public void setTypeRelation(String typeRelation) {
		this.typeRelation = typeRelation;
	}
	
	

}
