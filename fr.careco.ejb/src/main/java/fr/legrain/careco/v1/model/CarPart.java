package fr.legrain.careco.v1.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "CarPart")
public class CarPart implements java.io.Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="licensePlate")
	private String 	licensePlate;
	
	@Column(name="internalComment")
	private String 	internalComment;
	
	@Column(name="globalComment")
	private String 	globalComment;

	@Column(name="publishDate")
	@Temporal(TemporalType.TIME)
	private Date publishDate;

	@Column(name="timestamp")
	@Temporal(TemporalType.TIME)
	private Date timestamp;

	@Column(name="active")
	private Boolean active;
	
	@Column(name="carVersion")
	private String 	carVersion;
	
	@Column(name="carKm")
	private Integer carKm;
	
	@Column(name="destination")
	private String 	destination;
	
	@Column(name="price")
	private BigDecimal 	price;

	@Column(name="registrationDate")
	@Temporal(TemporalType.TIME)
	private Date registrationDate;

	@Column(name="serieNumber")
	private String 	serieNumber;
	
	@Column(name="mineType")
	private String 	mineType;
	
	@Column(name="partType")
	private String 	partType;
	
	@Column(name="partCode")
	private String 	partCode;
	
	@Column(name="lastCarPartStatusId")
	private String 	lastCarPartStatusId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "carModelId")
	private CarModel carModelId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "carBrandId")
	private CarBrand carBrandId;
	
	@Column(name="companyId")
	private Integer companyId;
	
	@Column(name="adherentId")
	private Integer adherentId;
	
	@Column(name="energy")
	private String 	energy;
	
	@Column(name="isVisibleInStock")
	private Integer isVisibleInStock;
	
	@Column(name="isVisibleByAdherent")
	private Boolean isVisibleByAdherent;
	
	@Column(name="location")
	private String 	location;
	
	@Column(name="typeId")
	private Integer typeId;
	
	@Column(name="partStatus")
	private String 	partStatus;
	
	@Column(name="typeGearbox")
	private String 	typeGearbox;

	@Column(name="reservedEndDate")
	@Temporal(TemporalType.TIME)
	private Date reservedEndDate;

	@Column(name="ficheInfoId")
	private Integer ficheInfoId;
	
	@Column(name="isOrdered")
	private Boolean isOrdered;
	
	@Column(name="isPrepared")
	private Integer isPrepared;
	
	@Column(name="nogo")
	private String 	nogo;
	
	@Column(name="discount")
	private BigDecimal 	discount;
	
	@Column(name="storageLocation")
	private String 	storageLocation;
	
	@Column(name="partSerialNumber")
	private String 	partSerialNumber;

	public Integer getId() {
		return id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public String getInternalComment() {
		return internalComment;
	}

	public String getGlobalComment() {
		return globalComment;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Boolean getActive() {
		return active;
	}

	public String getCarVersion() {
		return carVersion;
	}

	public Integer getCarKm() {
		return carKm;
	}

	public String getDestination() {
		return destination;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public String getSerieNumber() {
		return serieNumber;
	}

	public String getMineType() {
		return mineType;
	}

	public String getPartType() {
		return partType;
	}

	public String getPartCode() {
		return partCode;
	}

	public String getLastCarPartStatusId() {
		return lastCarPartStatusId;
	}

	public CarModel getCarModelId() {
		return carModelId;
	}

	public CarBrand getCarBrandId() {
		return carBrandId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public Integer getAdherentId() {
		return adherentId;
	}

	public String getEnergy() {
		return energy;
	}

	public Integer getIsVisibleInStock() {
		return isVisibleInStock;
	}

	public Boolean getIsVisibleByAdherent() {
		return isVisibleByAdherent;
	}

	public String getLocation() {
		return location;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public String getPartStatus() {
		return partStatus;
	}

	public String getTypeGearbox() {
		return typeGearbox;
	}

	public Date getReservedEndDate() {
		return reservedEndDate;
	}

	public Integer getFicheInfoId() {
		return ficheInfoId;
	}

	public Boolean getIsOrdered() {
		return isOrdered;
	}

	public Integer getIsPrepared() {
		return isPrepared;
	}

	public String getNogo() {
		return nogo;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public String getPartSerialNumber() {
		return partSerialNumber;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public void setInternalComment(String internalComment) {
		this.internalComment = internalComment;
	}

	public void setGlobalComment(String globalComment) {
		this.globalComment = globalComment;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setCarVersion(String carVersion) {
		this.carVersion = carVersion;
	}

	public void setCarKm(Integer carKm) {
		this.carKm = carKm;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setSerieNumber(String serieNumber) {
		this.serieNumber = serieNumber;
	}

	public void setMineType(String mineType) {
		this.mineType = mineType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public void setLastCarPartStatusId(String lastCarPartStatusId) {
		this.lastCarPartStatusId = lastCarPartStatusId;
	}

	public void setCarModelId(CarModel carModelId) {
		this.carModelId = carModelId;
	}

	public void setCarBrandId(CarBrand carBrandId) {
		this.carBrandId = carBrandId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setAdherentId(Integer adherentId) {
		this.adherentId = adherentId;
	}

	public void setEnergy(String energy) {
		this.energy = energy;
	}

	public void setIsVisibleInStock(Integer isVisibleInStock) {
		this.isVisibleInStock = isVisibleInStock;
	}

	public void setIsVisibleByAdherent(Boolean isVisibleByAdherent) {
		this.isVisibleByAdherent = isVisibleByAdherent;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
	}

	public void setTypeGearbox(String typeGearbox) {
		this.typeGearbox = typeGearbox;
	}

	public void setReservedEndDate(Date reservedEndDate) {
		this.reservedEndDate = reservedEndDate;
	}

	public void setFicheInfoId(Integer ficheInfoId) {
		this.ficheInfoId = ficheInfoId;
	}

	public void setIsOrdered(Boolean isOrdered) {
		this.isOrdered = isOrdered;
	}

	public void setIsPrepared(Integer isPrepared) {
		this.isPrepared = isPrepared;
	}

	public void setNogo(String nogo) {
		this.nogo = nogo;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public void setPartSerialNumber(String partSerialNumber) {
		this.partSerialNumber = partSerialNumber;
	}



}
