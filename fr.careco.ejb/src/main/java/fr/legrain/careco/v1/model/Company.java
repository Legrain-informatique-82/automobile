package fr.legrain.careco.v1.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Company")
public class Company implements java.io.Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String 	name;
	
	@Column(name="comment")
	private String 	comment;
	
	@Column(name="agent")
	private String 	agent;
	
	@Column(name="centerNumber")
	private Integer centerNumber;
	
	@Column(name="shareholderNumber")
	private Integer shareholderNumber;
	
	@Column(name="PEAccount")
	private Integer PEAccount;
	
	@Column(name="ECommerceAccount")
	private String 	ECommerceAccount;
	
	@Column(name="CarPartAccount")
	private String 	CarPartAccount;
	
	@Column(name="address1")
	private String 	address1;
	
	@Column(name="address2")
	private String 	address2;
	
	@Column(name="zipCode")
	private String 	zipCode;
	
	@Column(name="city")
	private String 	city;
	
	@Column(name="departement")
	private String 	departement;
	
	@Column(name="region")
	private String 	region;
	
	@Column(name="latitude")
	private String 	latitude;
	
	@Column(name="longitude")
	private String 	longitude;
	
	@Column(name="tel")
	private String 	tel;
	
	@Column(name="fax")
	private String 	fax;
	
	@Column(name="email")
	private String 	email;
	
	@Column(name="active")
	private Boolean active;
	
	@Column(name="adherentId")
	private Integer adherentId;
	
	@Column(name="magentoId")
	private Integer magentoId;
	
	@Column(name="note")
	private BigDecimal 	note;
	
	@Column(name="vovaId")
	private Integer vovaId;
	
	@Column(name="axioId")
	private Integer axioId;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public String getAgent() {
		return agent;
	}

	public Integer getCenterNumber() {
		return centerNumber;
	}

	public Integer getShareholderNumber() {
		return shareholderNumber;
	}

	public Integer getPEAccount() {
		return PEAccount;
	}

	public String getECommerceAccount() {
		return ECommerceAccount;
	}

	public String getCarPartAccount() {
		return CarPartAccount;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCity() {
		return city;
	}

	public String getDepartement() {
		return departement;
	}

	public String getRegion() {
		return region;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getTel() {
		return tel;
	}

	public String getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getActive() {
		return active;
	}

	public Integer getAdherentId() {
		return adherentId;
	}

	public Integer getMagentoId() {
		return magentoId;
	}

	public BigDecimal getNote() {
		return note;
	}

	public Integer getVovaId() {
		return vovaId;
	}

	public Integer getAxioId() {
		return axioId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public void setCenterNumber(Integer centerNumber) {
		this.centerNumber = centerNumber;
	}

	public void setShareholderNumber(Integer hareholderNumber) {
		this.shareholderNumber = hareholderNumber;
	}

	public void setPEAccount(Integer pEAccount) {
		PEAccount = pEAccount;
	}

	public void setECommerceAccount(String eCommerceAccount) {
		ECommerceAccount = eCommerceAccount;
	}

	public void setCarPartAccount(String carPartAccount) {
		CarPartAccount = carPartAccount;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setAdherentId(Integer adherentId) {
		this.adherentId = adherentId;
	}

	public void setMagentoId(Integer magentoId) {
		this.magentoId = magentoId;
	}

	public void setNote(BigDecimal note) {
		this.note = note;
	}

	public void setVovaId(Integer vovaId) {
		this.vovaId = vovaId;
	}

	public void setAxioId(Integer axioId) {
		this.axioId = axioId;
	}



}
