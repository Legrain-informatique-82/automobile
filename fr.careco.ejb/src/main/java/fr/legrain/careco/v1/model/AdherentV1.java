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
@Table(name = "Adherent")
public class AdherentV1 implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
 	private Integer id;
	
	@Column(name="code")
 	private String 	code;
	
	@Column(name="name")
 	private String 	name;
 	
 	@Column(name="sinceDate")
 	@Temporal(TemporalType.TIME)
 	private Date sinceDate; 
 	
 	@Column(name="resignationDate")
 	@Temporal(TemporalType.TIME)
	private Date resignationDate;
	
 	@Column(name="active")
	private Boolean active;
 	
 	@Column(name="phone")
 	private String 	phone;
 	
 	@Column(name="phone2")
 	private String	 phone2;
 	
 	@Column(name="mobile")
 	private String 	mobile;
 	
 	@Column(name="fax")
 	private String 	fax;
 	
 	@Column(name="email")
 	private String 	email;
 	
 	@Column(name="address1")
 	private String 	address1;
 	
 	@Column(name="address2")
 	private String 	address2;
 	
 	@Column(name="zipcode")
 	private String 	zipcode;
 	
 	@Column(name="city")
 	private String 	city;
 	
 	@Column(name="country")
 	private String 	country;
 	
 	@Column(name="taxRateTypePrivate")
 	private BigDecimal	 taxRateTypePrivate;
 	
 	@Column(name="taxRateTypePublic")
	private BigDecimal 	taxRateTypePublic;
 	
 	@Column(name="networkId")
	private Integer networkId;
	
	@Column(name="bossId")
 	private Integer bossId;

	public Integer getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Date getSinceDate() {
		return sinceDate;
	}

	public Date getResignationDate() {
		return resignationDate;
	}

	public Boolean getActive() {
		return active;
	}

	public String getPhone() {
		return phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public String getMobile() {
		return mobile;
	}

	public String getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public BigDecimal getTaxRateTypePrivate() {
		return taxRateTypePrivate;
	}

	public BigDecimal getTaxRateTypePublic() {
		return taxRateTypePublic;
	}

	public Integer getNetworkId() {
		return networkId;
	}

	public Integer getBossId() {
		return bossId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSinceDate(Date sinceDate) {
		this.sinceDate = sinceDate;
	}

	public void setResignationDate(Date resignationDate) {
		this.resignationDate = resignationDate;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setTaxRateTypePrivate(BigDecimal taxRateTypePrivate) {
		this.taxRateTypePrivate = taxRateTypePrivate;
	}

	public void setTaxRateTypePublic(BigDecimal taxRateTypePublic) {
		this.taxRateTypePublic = taxRateTypePublic;
	}

	public void setNetworkId(Integer networkId) {
		this.networkId = networkId;
	}

	public void setBossId(Integer bossId) {
		this.bossId = bossId;
	}
	
}
