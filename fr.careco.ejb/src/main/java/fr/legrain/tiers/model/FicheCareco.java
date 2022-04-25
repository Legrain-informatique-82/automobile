package fr.legrain.tiers.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.legrain.tiers.dao.jpa.ILgrEntity;
import fr.legrain.tiers.dao.jpa.MyListener;

@Entity
@Table(name = "FicheCareco")
@EntityListeners({MyListener.class})
@NamedQueries(value = { 
		@NamedQuery(name=FicheCareco.QN.FIND_BY_CNIT, query="select f from FicheCareco f where f.typeCG= :cnit")
		})
public class FicheCareco implements java.io.Serializable, ILgrEntity {
	
	public static class QN {
		public static final String FIND_BY_CNIT = "FicheCareco.findByCnit";
	}
	
	private static final long serialVersionUID = 6987246540914292256L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="idCareco")
	private String idCareco;
	
	@Column(name="marque")
	private String marque;	
	
	@Column(name="modele")
	private String modele;	
	
	@Column(name="mode")
	private String mode;	
	
	@Column(name="genre")
	private String genre;	
	
	@Column(name="cm3")
	private String cm3;
	
	@Column(name="cyl")
	private String cyl;
	
	@Column(name="sppes")
	private String sppes;	
	
	@Column(name="cvDin")
	private String cvDin; 
	
	@Column(name="dateDe")
	@Temporal(value = TemporalType.DATE)
	private Date dateDe;	
	
	@Column(name="dateA")
	@Temporal(value = TemporalType.DATE)
	private Date dateA;	
	
	@Column(name="typeCG")
	private String typeCG;	
	
	@Column(name="typeMot")
	private String typeMot;
	
	@Column(name="codeMot")
	private String codeMot;
	
	@Column(name="obsMot")
	private String obsMot;	
	
	@Column(name="refBte")
	private String refBte;	
	
	@Column(name="codeBte")
	private String codeBte;
	
	@Column(name="genreBte")
	private String genreBte;	
	
	@Column(name="obsBte")
	private String obsBte;	
	
	@Column(name="cetteBoite")
	private String cetteBoite;
	
	@Column(name="avecCetteBoite")
	private String avecCetteBoite;	
	
	@Column(name="avecCetteAutreBoite")
	private String avecCetteAutreBoite;
	
	@Column(name="pivotBoiteDeVitesse")
	private String pivotBoiteDeVitesse;
	
	@Column(name="ceTypeMoteurEstCompatibleAvec")
	private String ceTypeMoteurEstCompatibleAvec;	
	
	@Column(name="ceMoteur")
	private String ceMoteur;	
	
	@Column(name="pivotMoteurs")
	private String pivotMoteurs;
	
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

	public FicheCareco() {
		
	}
	
	public FicheCareco(String idCareco, String marque, String modele, String mode,
			String genre, String cm3, String cyl, String sppes, String cv,
			Date dateDe, Date dateA, String typeCG,
			String typeMot, String codeMot, String obsMot, String refBte,
			String codeBte, String genreBte, String obsBte, String cetteBoite,
			String avecCetteBoite, String avecCetteAutreBoite,
			String pivotBoiteDeVitesse, String ceTypeMoteurEstCompatibleAvec,
			String ceMoteur, String pivotMoteurs) {
		super();
		this.idCareco = idCareco;
		this.marque = marque;
		this.modele = modele;
		this.mode = mode;
		this.genre = genre;
		this.cm3 = cm3;
		this.cyl = cyl;
		this.sppes = sppes;
		this.cvDin = cv;
		this.dateDe = dateDe;
		this.dateA = dateA;
		this.typeCG = typeCG;
		this.typeMot = typeMot;
		this.codeMot = codeMot;
		this.obsMot = obsMot;
		this.refBte = refBte;
		this.codeBte = codeBte;
		this.genreBte = genreBte;
		this.obsBte = obsBte;
		this.cetteBoite = cetteBoite;
		this.avecCetteBoite = avecCetteBoite;
		this.avecCetteAutreBoite = avecCetteAutreBoite;
		this.pivotBoiteDeVitesse = pivotBoiteDeVitesse;
		this.ceTypeMoteurEstCompatibleAvec = ceTypeMoteurEstCompatibleAvec;
		this.ceMoteur = ceMoteur;
		this.pivotMoteurs = pivotMoteurs;
	}

	public String getIdCareco() {
		return idCareco;
	}

	public void setIdCareco(String idCareco) {
		this.idCareco = idCareco;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCm3() {
		return cm3;
	}

	public void setCm3(String cm3) {
		this.cm3 = cm3;
	}

	public String getCyl() {
		return cyl;
	}

	public void setCyl(String cyl) {
		this.cyl = cyl;
	}

	public String getSppes() {
		return sppes;
	}

	public void setSppes(String sppes) {
		this.sppes = sppes;
	}

	public String getCvDin() {
		return cvDin;
	}

	public void setCvDin(String cvDin) {
		this.cvDin = cvDin;
	}

	public Date getDateDe() {
		return dateDe;
	}

	public void setDateDe(Date dateDe) {
		this.dateDe = dateDe;
	}

	public Date getDateA() {
		return dateA;
	}

	public void setDateA(Date dateA) {
		this.dateA = dateA;
	}

	public String getTypeCG() {
		return typeCG;
	}

	public void setTypeCG(String typeCG) {
		this.typeCG = typeCG;
	}

	public String getTypeMot() {
		return typeMot;
	}

	public void setTypeMot(String typeMot) {
		this.typeMot = typeMot;
	}

	public String getCodeMot() {
		return codeMot;
	}

	public void setCodeMot(String codeMot) {
		this.codeMot = codeMot;
	}

	public String getObsMot() {
		return obsMot;
	}

	public void setObsMot(String obsMot) {
		this.obsMot = obsMot;
	}

	public String getRefBte() {
		return refBte;
	}

	public void setRefBte(String refBte) {
		this.refBte = refBte;
	}

	public String getCodeBte() {
		return codeBte;
	}

	public void setCodeBte(String codeBte) {
		this.codeBte = codeBte;
	}

	public String getGenreBte() {
		return genreBte;
	}

	public void setGenreBte(String genreBte) {
		this.genreBte = genreBte;
	}

	public String getObsBte() {
		return obsBte;
	}

	public void setObsBte(String obsBte) {
		this.obsBte = obsBte;
	}

	public String getCetteBoite() {
		return cetteBoite;
	}

	public void setCetteBoite(String cetteBoite) {
		this.cetteBoite = cetteBoite;
	}

	public String getAvecCetteBoite() {
		return avecCetteBoite;
	}

	public void setAvecCetteBoite(String avecCetteBoite) {
		this.avecCetteBoite = avecCetteBoite;
	}

	public String getAvecCetteAutreBoite() {
		return avecCetteAutreBoite;
	}

	public void setAvecCetteAutreBoite(String avecCetteAutreBoite) {
		this.avecCetteAutreBoite = avecCetteAutreBoite;
	}

	public String getPivotBoiteDeVitesse() {
		return pivotBoiteDeVitesse;
	}

	public void setPivotBoiteDeVitesse(String pivotBoiteDeVitesse) {
		this.pivotBoiteDeVitesse = pivotBoiteDeVitesse;
	}

	public String getCeTypeMoteurEstCompatibleAvec() {
		return ceTypeMoteurEstCompatibleAvec;
	}

	public void setCeTypeMoteurEstCompatibleAvec(
			String ceTypeMoteurEstCompatibleAvec) {
		this.ceTypeMoteurEstCompatibleAvec = ceTypeMoteurEstCompatibleAvec;
	}

	public String getCeMoteur() {
		return ceMoteur;
	}

	public void setCeMoteur(String ceMoteur) {
		this.ceMoteur = ceMoteur;
	}

	public String getPivotMoteurs() {
		return pivotMoteurs;
	}

	public void setPivotMoteurs(String pivotMoteurs) {
		this.pivotMoteurs = pivotMoteurs;
	}

	public Integer getId() {
		return id;
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

	public void setId(Integer id) {
		this.id = id;
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
