package fr.legrain.careco.dumpmc.model;

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
@Table(name = "company_export")
public class CompanyMoteurClub implements java.io.Serializable {

	private static final long serialVersionUID = -6911967292670267952L;

	@Id
	@Column(name="company_id")
 	private Integer company_id;
	
	@Column(name="company_name")
	private String 	company_name;
	
	@Column(name="company_address")
	private String 	company_address;
	
	@Column(name="company_city")
	private String 	company_city;
	
	@Column(name="company_zipcode")
	private String 	company_zipcode;
	
	@Column(name="company_country")
	private String 	company_country;
	
	@Column(name="company_phone")
	private String 	company_phone;
	
	@Column(name="company_phone_office")
	private String 	company_phone_office;
	
	@Column(name="company_gsm")
	private String	company_gsm;
	
	@Column(name="company_fax")
	private String 	company_fax;
	
	@Column(name="company_email")
	private String 	company_email;
	
	@Column(name="company_taux_remise_list")
	private String 	company_taux_remise_list;
	
	@Column(name="company_VAT")
	private BigDecimal 	company_VAT;
	
	@Column(name="company_VAT_NR")
	private BigDecimal 	company_VAT_NR;
	
	@Column(name="company_siret")
	private String 	company_siret;
	
	@Column(name="company_intracom")
	private String	company_intracom;
	
	@Column(name="company_ship_moteur")
	private BigDecimal 	company_ship_moteur;
	
	@Column(name="company_ship_boite")
	private BigDecimal 	company_ship_boite;
	
	@Column(name="company_ship_culasse")
	private BigDecimal 	company_ship_culasse;
	
	@Column(name="company_ctre_remb")
	private BigDecimal 	company_ctre_remb;
	
	@Column(name="company_rib_bank")
	private String 	company_rib_bank;
	
	@Column(name="company_rib_agency")
	private String 	company_rib_agency;
	
	@Column(name="company_rib_guichet")
	private String 	company_rib_guichet;
	
	@Column(name="company_rib_num")
	private String 	company_rib_num;
	
	@Column(name="company_rib_key")
	private String 	company_rib_key;
	
	@Column(name="company_rib_iban")
	private String 	company_rib_iban;
	
	@Column(name="company_rib_swift")
	private String 	company_rib_swift;
	
	@Column(name="company_emplacement_list")
	private String 	company_emplacement_list;
	
	@Column(name="company_cdt_garanty")
	private String 	company_cdt_garanty;

	public Integer getCompany_id() {
		return company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public String getCompany_address() {
		return company_address;
	}

	public String getCompany_city() {
		return company_city;
	}

	public String getCompany_zipcode() {
		return company_zipcode;
	}

	public String getCompany_country() {
		return company_country;
	}

	public String getCompany_phone() {
		return company_phone;
	}

	public String getCompany_phone_office() {
		return company_phone_office;
	}

	public String getCompany_gsm() {
		return company_gsm;
	}

	public String getCompany_fax() {
		return company_fax;
	}

	public String getCompany_email() {
		return company_email;
	}

	public String getCompany_taux_remise_list() {
		return company_taux_remise_list;
	}

	public BigDecimal getCompany_VAT() {
		return company_VAT;
	}

	public BigDecimal getCompany_VAT_NR() {
		return company_VAT_NR;
	}

	public String getCompany_siret() {
		return company_siret;
	}

	public String getCompany_intracom() {
		return company_intracom;
	}

	public BigDecimal getCompany_ship_moteur() {
		return company_ship_moteur;
	}

	public BigDecimal getCompany_ship_boite() {
		return company_ship_boite;
	}

	public BigDecimal getCompany_ship_culasse() {
		return company_ship_culasse;
	}

	public BigDecimal getCompany_ctre_remb() {
		return company_ctre_remb;
	}

	public String getCompany_rib_bank() {
		return company_rib_bank;
	}

	public String getCompany_rib_agency() {
		return company_rib_agency;
	}

	public String getCompany_rib_guichet() {
		return company_rib_guichet;
	}

	public String getCompany_rib_num() {
		return company_rib_num;
	}

	public String getCompany_rib_key() {
		return company_rib_key;
	}

	public String getCompany_rib_iban() {
		return company_rib_iban;
	}

	public String getCompany_rib_swift() {
		return company_rib_swift;
	}

	public String getCompany_emplacement_list() {
		return company_emplacement_list;
	}

	public String getCompany_cdt_garanty() {
		return company_cdt_garanty;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public void setCompany_city(String company_city) {
		this.company_city = company_city;
	}

	public void setCompany_zipcode(String company_zipcode) {
		this.company_zipcode = company_zipcode;
	}

	public void setCompany_country(String company_country) {
		this.company_country = company_country;
	}

	public void setCompany_phone(String company_phone) {
		this.company_phone = company_phone;
	}

	public void setCompany_phone_office(String company_phone_office) {
		this.company_phone_office = company_phone_office;
	}

	public void setCompany_gsm(String company_gsm) {
		this.company_gsm = company_gsm;
	}

	public void setCompany_fax(String company_fax) {
		this.company_fax = company_fax;
	}

	public void setCompany_email(String company_email) {
		this.company_email = company_email;
	}

	public void setCompany_taux_remise_list(String company_taux_remise_list) {
		this.company_taux_remise_list = company_taux_remise_list;
	}

	public void setCompany_VAT(BigDecimal company_VAT) {
		this.company_VAT = company_VAT;
	}

	public void setCompany_VAT_NR(BigDecimal company_VAT_NR) {
		this.company_VAT_NR = company_VAT_NR;
	}

	public void setCompany_siret(String company_siret) {
		this.company_siret = company_siret;
	}

	public void setCompany_intracom(String company_intracom) {
		this.company_intracom = company_intracom;
	}

	public void setCompany_ship_moteur(BigDecimal company_ship_moteur) {
		this.company_ship_moteur = company_ship_moteur;
	}

	public void setCompany_ship_boite(BigDecimal company_ship_boite) {
		this.company_ship_boite = company_ship_boite;
	}

	public void setCompany_ship_culasse(BigDecimal company_ship_culasse) {
		this.company_ship_culasse = company_ship_culasse;
	}

	public void setCompany_ctre_remb(BigDecimal company_ctre_remb) {
		this.company_ctre_remb = company_ctre_remb;
	}

	public void setCompany_rib_bank(String company_rib_bank) {
		this.company_rib_bank = company_rib_bank;
	}

	public void setCompany_rib_agency(String company_rib_agency) {
		this.company_rib_agency = company_rib_agency;
	}

	public void setCompany_rib_guichet(String company_rib_guichet) {
		this.company_rib_guichet = company_rib_guichet;
	}

	public void setCompany_rib_num(String company_rib_num) {
		this.company_rib_num = company_rib_num;
	}

	public void setCompany_rib_key(String company_rib_key) {
		this.company_rib_key = company_rib_key;
	}

	public void setCompany_rib_iban(String company_rib_iban) {
		this.company_rib_iban = company_rib_iban;
	}

	public void setCompany_rib_swift(String company_rib_swift) {
		this.company_rib_swift = company_rib_swift;
	}

	public void setCompany_emplacement_list(String company_emplacement_list) {
		this.company_emplacement_list = company_emplacement_list;
	}

	public void setCompany_cdt_garanty(String company_cdt_garanty) {
		this.company_cdt_garanty = company_cdt_garanty;
	}
	

	
}
