package fr.legrain.careco.dumpmc.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_moteur_export")
public class StockMoteurClub implements java.io.Serializable {

	private static final long serialVersionUID = -3213383264209718067L;
	
	@Id
	@Column(name="stock_id")
	private Integer stock_id;
	
	@Column(name="company_id")
	private Integer company_id;
	
	@Column(name="company_name")
	private String 	company_name;
	
	@Column(name="vendor_id")
	private Integer vendor_id;
	
	@Column(name="creator_user_id")
	private Integer creator_user_id;
	
	@Column(name="numero_dossier")
	private String 	numero_dossier;
	
	@Column(name="numero_prod")
	private String 	numero_prod;
	
	@Column(name="sujet_stock")
	private String 	sujet_stock;
	
	@Column(name="type_stock")
	private Integer type_stock;
	
	@Column(name="export")
	private Integer export;
	
	@Column(name="date_achat")
	private String	date_achat;
	
	@Column(name="prix_achat")
	private Integer	 prix_achat;
	
	@Column(name="prix_port")
	private Integer prix_port;
	
	@Column(name="type_tva")
	//14 	type_tva 	enum('0', '1', '2') 
	private Integer type_tva;
	
	@Column(name="emplacement_id")
	private Integer emplacement_id;
	
	@Column(name="emplacement_stock")
	private String 	emplacement_stock;
	
	@Column(name="emplacement_in_transit")
	private Integer emplacement_in_transit;
	
	@Column(name="prix")
	private BigDecimal 	prix;
	
	@Column(name="prix_cat")
	private String 	prix_cat;
	
	@Column(name="taux_remise")
	private BigDecimal 	taux_remise;
	
	@Column(name="taux_remise_level")
	private Integer taux_remise_level;
	
	@Column(name="indic_negoce")
	private String 	indic_negoce;
	
	@Column(name="marque")
	private String 	marque;
	
	@Column(name="modele")
	private String 	modele;
	
	@Column(name="modele_categorie")
	private String 	modele_categorie;
	
	@Column(name="categorie")
	private String 	categorie;
	
	@Column(name="genre")
	private String 	genre;
	
	@Column(name="cylindree")
	private String 	cylindree;
	
	@Column(name="cylindres")
	private String 	cylindres;
	
	@Column(name="soupapes")
	private String 	soupapes;
	
	@Column(name="annee")
	private String 	annee;
	
	@Column(name="puissance_cv_din")
	private String 	puissance_cv_din;
	
	@Column(name="origine")
	private String 	origine;
	
	@Column(name="garantie_fournisseur")
	private Integer garantie_fournisseur;
	
	@Column(name="garantie")
	private Integer garantie;
	
	@Column(name="port_fournisseur")
	private BigDecimal 	port_fournisseur;
	
	@Column(name="kms")
	private Integer kms;
	
	@Column(name="kms_displayable")
	//38 	kms_displayable 	enum('0', '1') 
	private Integer kms_displayable;
	
	@Column(name="kms_certified")
	//39 	kms_certified 	enum('0', '1') 
	private Integer kms_certified;
	
	@Column(name="priorite")
	private Integer priorite;
	
	@Column(name="stock_declasse")
	private Integer stock_declasse;
	
	@Column(name="stock_added_class")
	private String 	stock_added_class;
	
	@Column(name="b_caution")
	private String 	b_caution;
	
	@Column(name="b_specif")
	private String 	b_specif;
	
	@Column(name="observation")
	private String 	observation;
	
	@Column(name="description")
	private String 	description;
	
	@Column(name="produit_demonte")
	private Integer produit_demonte;
	
	@Column(name="moteur_type")
	private String 	moteur_type;
	
	@Column(name="moteur_code")
	private String 	moteur_code;
	
	@Column(name="numero_immat")
	private String 	numero_immat;
	
	@Column(name="num_type")
	private String 	num_type;
	
	@Column(name="type_carte_grise")
	private String 	type_carte_grise;
	
	@Column(name="serial_number")
	private String 	serial_number;
	
	@Column(name="boite_type")
	private String 	boite_type;
	
	@Column(name="boite_code")
	private String 	boite_code;
	
	@Column(name="genre_boite")
	private String 	genre_boite;
	
	@Column(name="piece_type")
	private String 	piece_type;
	
	@Column(name="piece_code")
	private String 	piece_code;
	
	@Column(name="standby")
	private Integer standby;
	
	@Column(name="reserved")
	//60 	reserved 	enum('0', '1') 
	private Integer reserved;
	
	@Column(name="reserved_contact_id")
	private Integer reserved_contact_id;
	
	@Column(name="reserved_date")
	private String 	reserved_date;
	
	@Column(name="a_recuperer")
	//63 	a_recuperer 	enum('0', '1') 
	private Integer a_recuperer;
	
	@Column(name="rvmi")
	private Integer rvmi;
	
	@Column(name="rvmi_val")
	private String 	rvmi_val;
	
	@Column(name="rvmi_notes")
	private String 	rvmi_notes;
	
	@Column(name="etat_stock")
	private Integer etat_stock;
	
	@Column(name="date_created")
	private String 	date_created;
	
	@Column(name="cg_caross")
	private String 	cg_caross;
	
	@Column(name="cg_energie")
	private String 	cg_energie;
	
	@Column(name="cg_puiss")
	private String 	cg_puiss;
	
	@Column(name="mask_caution")
	//72 	mask_caution 	enum('0', '1') 
	private Integer mask_caution;
	
	@Column(name="mask_rvmi")
	//73 	mask_rvmi 	enum('0', '1') 
	private Integer mask_rvmi;
	
	@Column(name="resa_lead")
	//74 	resa_lead 	enum('0', '1') 
	private Integer resa_lead;
	
	@Column(name="ctrl_status")
	private Integer ctrl_status;
	
	@Column(name="TAG_TECH")
	private String 	TAG_TECH;

	public Integer getStock_id() {
		return stock_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public Integer getVendor_id() {
		return vendor_id;
	}

	public Integer getCreator_user_id() {
		return creator_user_id;
	}

	public String getNumero_dossier() {
		return numero_dossier;
	}

	public String getNumero_prod() {
		return numero_prod;
	}

	public String getSujet_stock() {
		return sujet_stock;
	}

	public Integer getType_stock() {
		return type_stock;
	}

	public Integer getExport() {
		return export;
	}

	public String getDate_achat() {
		return date_achat;
	}

	public Integer getPrix_achat() {
		return prix_achat;
	}

	public Integer getPrix_port() {
		return prix_port;
	}

	public Integer getType_tva() {
		return type_tva;
	}

	public Integer getEmplacement_id() {
		return emplacement_id;
	}

	public String getEmplacement_stock() {
		return emplacement_stock;
	}

	public Integer getEmplacement_in_transit() {
		return emplacement_in_transit;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public String getPrix_cat() {
		return prix_cat;
	}

	public BigDecimal getTaux_remise() {
		return taux_remise;
	}

	public Integer getTaux_remise_level() {
		return taux_remise_level;
	}

	public String getIndic_negoce() {
		return indic_negoce;
	}

	public String getMarque() {
		return marque;
	}

	public String getModele() {
		return modele;
	}

	public String getModele_categorie() {
		return modele_categorie;
	}

	public String getCategorie() {
		return categorie;
	}

	public String getGenre() {
		return genre;
	}

	public String getCylindree() {
		return cylindree;
	}

	public String getCylindres() {
		return cylindres;
	}

	public String getSoupapes() {
		return soupapes;
	}

	public String getAnnee() {
		return annee;
	}

	public String getPuissance_cv_din() {
		return puissance_cv_din;
	}

	public String getOrigine() {
		return origine;
	}

	public Integer getGarantie_fournisseur() {
		return garantie_fournisseur;
	}

	public Integer getGarantie() {
		return garantie;
	}

	public BigDecimal getPort_fournisseur() {
		return port_fournisseur;
	}

	public Integer getKms() {
		return kms;
	}

	public Integer getKms_displayable() {
		return kms_displayable;
	}

	public Integer getKms_certified() {
		return kms_certified;
	}

	public Integer getPriorite() {
		return priorite;
	}

	public Integer getStock_declasse() {
		return stock_declasse;
	}

	public String getStock_added_class() {
		return stock_added_class;
	}

	public String getB_caution() {
		return b_caution;
	}

	public String getB_specif() {
		return b_specif;
	}

	public String getObservation() {
		return observation;
	}

	public String getDescription() {
		return description;
	}

	public Integer getProduit_demonte() {
		return produit_demonte;
	}

	public String getMoteur_type() {
		return moteur_type;
	}

	public String getMoteur_code() {
		return moteur_code;
	}

	public String getNumero_immat() {
		return numero_immat;
	}

	public String getNum_type() {
		return num_type;
	}

	public String getType_carte_grise() {
		return type_carte_grise;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public String getBoite_type() {
		return boite_type;
	}

	public String getBoite_code() {
		return boite_code;
	}

	public String getGenre_boite() {
		return genre_boite;
	}

	public String getPiece_type() {
		return piece_type;
	}

	public String getPiece_code() {
		return piece_code;
	}

	public Integer getStandby() {
		return standby;
	}

	public Integer getReserved() {
		return reserved;
	}

	public Integer getReserved_contact_id() {
		return reserved_contact_id;
	}

	public String getReserved_date() {
		return reserved_date;
	}

	public Integer getA_recuperer() {
		return a_recuperer;
	}

	public Integer getRvmi() {
		return rvmi;
	}

	public String getRvmi_val() {
		return rvmi_val;
	}

	public String getRvmi_notes() {
		return rvmi_notes;
	}

	public Integer getEtat_stock() {
		return etat_stock;
	}

	public String getDate_created() {
		return date_created;
	}

	public String getCg_caross() {
		return cg_caross;
	}

	public String getCg_energie() {
		return cg_energie;
	}

	public String getCg_puiss() {
		return cg_puiss;
	}

	public Integer getMask_caution() {
		return mask_caution;
	}

	public Integer getMask_rvmi() {
		return mask_rvmi;
	}

	public Integer getResa_lead() {
		return resa_lead;
	}

	public Integer getCtrl_status() {
		return ctrl_status;
	}

	public String getTAG_TECH() {
		return TAG_TECH;
	}

	public void setStock_id(Integer stock_id) {
		this.stock_id = stock_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public void setVendor_id(Integer vendor_id) {
		this.vendor_id = vendor_id;
	}

	public void setCreator_user_id(Integer creator_user_id) {
		this.creator_user_id = creator_user_id;
	}

	public void setNumero_dossier(String numero_dossier) {
		this.numero_dossier = numero_dossier;
	}

	public void setNumero_prod(String numero_prod) {
		this.numero_prod = numero_prod;
	}

	public void setSujet_stock(String sujet_stock) {
		this.sujet_stock = sujet_stock;
	}

	public void setType_stock(Integer type_stock) {
		this.type_stock = type_stock;
	}

	public void setExport(Integer export) {
		this.export = export;
	}

	public void setDate_achat(String date_achat) {
		this.date_achat = date_achat;
	}

	public void setPrix_achat(Integer prix_achat) {
		this.prix_achat = prix_achat;
	}

	public void setPrix_port(Integer prix_port) {
		this.prix_port = prix_port;
	}

	public void setType_tva(Integer type_tva) {
		this.type_tva = type_tva;
	}

	public void setEmplacement_id(Integer emplacement_id) {
		this.emplacement_id = emplacement_id;
	}

	public void setEmplacement_stock(String emplacement_stock) {
		this.emplacement_stock = emplacement_stock;
	}

	public void setEmplacement_in_transit(Integer emplacement_in_transit) {
		this.emplacement_in_transit = emplacement_in_transit;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public void setPrix_cat(String prix_cat) {
		this.prix_cat = prix_cat;
	}

	public void setTaux_remise(BigDecimal taux_remise) {
		this.taux_remise = taux_remise;
	}

	public void setTaux_remise_level(Integer taux_remise_level) {
		this.taux_remise_level = taux_remise_level;
	}

	public void setIndic_negoce(String indic_negoce) {
		this.indic_negoce = indic_negoce;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public void setModele_categorie(String modele_categorie) {
		this.modele_categorie = modele_categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setCylindree(String cylindree) {
		this.cylindree = cylindree;
	}

	public void setCylindres(String cylindres) {
		this.cylindres = cylindres;
	}

	public void setSoupapes(String soupapes) {
		this.soupapes = soupapes;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public void setPuissance_cv_din(String puissance_cv_din) {
		this.puissance_cv_din = puissance_cv_din;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public void setGarantie_fournisseur(Integer garantie_fournisseur) {
		this.garantie_fournisseur = garantie_fournisseur;
	}

	public void setGarantie(Integer garantie) {
		this.garantie = garantie;
	}

	public void setPort_fournisseur(BigDecimal port_fournisseur) {
		this.port_fournisseur = port_fournisseur;
	}

	public void setKms(Integer kms) {
		this.kms = kms;
	}

	public void setKms_displayable(Integer kms_displayable) {
		this.kms_displayable = kms_displayable;
	}

	public void setKms_certified(Integer kms_certified) {
		this.kms_certified = kms_certified;
	}

	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}

	public void setStock_declasse(Integer stock_declasse) {
		this.stock_declasse = stock_declasse;
	}

	public void setStock_added_class(String stock_added_class) {
		this.stock_added_class = stock_added_class;
	}

	public void setB_caution(String b_caution) {
		this.b_caution = b_caution;
	}

	public void setB_specif(String b_specif) {
		this.b_specif = b_specif;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setProduit_demonte(Integer produit_demonte) {
		this.produit_demonte = produit_demonte;
	}

	public void setMoteur_type(String moteur_type) {
		this.moteur_type = moteur_type;
	}

	public void setMoteur_code(String moteur_code) {
		this.moteur_code = moteur_code;
	}

	public void setNumero_immat(String numero_immat) {
		this.numero_immat = numero_immat;
	}

	public void setNum_type(String num_type) {
		this.num_type = num_type;
	}

	public void setType_carte_grise(String type_carte_grise) {
		this.type_carte_grise = type_carte_grise;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public void setBoite_type(String boite_type) {
		this.boite_type = boite_type;
	}

	public void setBoite_code(String boite_code) {
		this.boite_code = boite_code;
	}

	public void setGenre_boite(String genre_boite) {
		this.genre_boite = genre_boite;
	}

	public void setPiece_type(String piece_type) {
		this.piece_type = piece_type;
	}

	public void setPiece_code(String piece_code) {
		this.piece_code = piece_code;
	}

	public void setStandby(Integer standby) {
		this.standby = standby;
	}

	public void setReserved(Integer reserved) {
		this.reserved = reserved;
	}

	public void setReserved_contact_id(Integer reserved_contact_id) {
		this.reserved_contact_id = reserved_contact_id;
	}

	public void setReserved_date(String reserved_date) {
		this.reserved_date = reserved_date;
	}

	public void setA_recuperer(Integer a_recuperer) {
		this.a_recuperer = a_recuperer;
	}

	public void setRvmi(Integer rvmi) {
		this.rvmi = rvmi;
	}

	public void setRvmi_val(String rvmi_val) {
		this.rvmi_val = rvmi_val;
	}

	public void setRvmi_notes(String rvmi_notes) {
		this.rvmi_notes = rvmi_notes;
	}

	public void setEtat_stock(Integer etat_stock) {
		this.etat_stock = etat_stock;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public void setCg_caross(String cg_caross) {
		this.cg_caross = cg_caross;
	}

	public void setCg_energie(String cg_energie) {
		this.cg_energie = cg_energie;
	}

	public void setCg_puiss(String cg_puiss) {
		this.cg_puiss = cg_puiss;
	}

	public void setMask_caution(Integer mask_caution) {
		this.mask_caution = mask_caution;
	}

	public void setMask_rvmi(Integer mask_rvmi) {
		this.mask_rvmi = mask_rvmi;
	}

	public void setResa_lead(Integer resa_lead) {
		this.resa_lead = resa_lead;
	}

	public void setCtrl_status(Integer ctrl_status) {
		this.ctrl_status = ctrl_status;
	}

	public void setTAG_TECH(String tAG_TECH) {
		TAG_TECH = tAG_TECH;
	}

	
}
