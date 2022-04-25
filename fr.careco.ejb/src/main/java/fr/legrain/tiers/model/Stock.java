package fr.legrain.tiers.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import fr.legrain.tiers.dao.jpa.ILgrEntity;
import fr.legrain.tiers.dao.jpa.MyListener;

@Entity
@Table(name = "Stock") 
@EntityListeners({MyListener.class})
@NamedQueries(value = { 
		@NamedQuery(name=Stock.QN.FIND_BY_USER_ET_PIECESTOCK, query="select f from Stock f where f.idStock.id = :idStock and f.refConstructeur in :inClause and f.status in :statusInClause"),
		@NamedQuery(name=Stock.QN.FIND_BY_USER_ET_PIECESTOCK_TYPEPIECE, query="select f from Stock f where f.idStock.id = :idStock and f.refConstructeur in :inClause and f.status in :statusInClause and f.typeDePiece= :typePiece"),
		@NamedQuery(name=Stock.QN.FIND_BY_MULTICOMPANY_ET_PIECESTOCK, query="select f from Stock f where f.idStock.id in :idStock and f.refConstructeur in :inClause and f.status in :statusInClause"),
		@NamedQuery(name=Stock.QN.FIND_BY_MULTICOMPANY_ET_PIECESTOCK_PLUS_COMPAT, query="select f from Stock f where f.idStock.id in :idStock and ( (f.refConstructeur in :inClause) or (f.codeCompatibilite in :inClauseCompat) ) and f.status in :statusInClause"),
		@NamedQuery(name=Stock.QN.FIND_BY_MULTICOMPANY_ET_PIECESTOCK_TYPEPIECE, query="select f from Stock f where f.idStock.id in :idStock and f.refConstructeur in :inClause and f.status in :statusInClause and f.typeDePiece= :typePiece")
		})
public class Stock implements java.io.Serializable, ILgrEntity {
	
	//codeCompatibilite
	public static class QN {
		public static final String FIND_BY_USER_ET_PIECESTOCK = "Stock.findByUserEtPieceStock";
		public static final String FIND_BY_USER_ET_PIECESTOCK_TYPEPIECE = "Stock.findByUserEtPieceStockTypePiece";
		public static final String FIND_BY_MULTICOMPANY_ET_PIECESTOCK = "Stock.findByMultiCompanyEtPieceStock";
		public static final String FIND_BY_MULTICOMPANY_ET_PIECESTOCK_PLUS_COMPAT = "Stock.findByMultiCompanyEtPieceStockPlusCompat";
		public static final String FIND_BY_MULTICOMPANY_ET_PIECESTOCK_TYPEPIECE = "Stock.findByMultiCompanyEtPieceStockTypePiece";
	}
	
	private static final long serialVersionUID = -7963326011646342770L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "idStock")
	private UserCompany idStock;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "idStockOrigine")
	private UserCompany idStockOrigine;
	
	@Column(name="disponibilite")
	private Boolean disponibilite = true;
	
	@Column(name="typeDePiece")
	private String typeDePiece;
	
	@Column(name="refConstructeur")
	private String refConstructeur;
	
	@Column(name="codeCompatibilite")
	private String codeCompatibilite;
	
	@Column(name="CNITTypeMine")
	private String CNITTypeMine;
	
	@Column(name="vin")
	private String vin;
	
	@Column(name="marque")
	private String marque;
	
	@Column(name="modele")
	private String modele;
	
	@Column(name="version")
	private String version;
	
	@Column(name="kms")
	private Integer kms;
	
	@Column(name="empl")
	private String empl;
	
	@Column(name="vendeur")
	private String vendeur;
	
	@Column(name="garantie")
	private Integer garantie;
	
	@Column(name="prixCareco")
	private BigDecimal prixCareco;
	
	@Column(name="prixVente")
	private BigDecimal prixVente;
	
	@Column(name="numLivrePolice")
	private String numLivrePolice;
	
	@Column(name="immatriculation")
	private String immatriculation;
	
	@Column(name="export")
	private Boolean export;
	
	@Column(name="demonte")
	private Boolean demonte;
	
	@Column(name="date1erMiseEnCirculation")
	@Temporal(value = TemporalType.DATE)
	private Date date1erMiseEnCirculation;
	
	@Column(name="prixAchat")
	private BigDecimal prixAchat;
	
	@Column(name="emplacementCasier")
	private String emplacementCasier;
	
	@Column(name="numeroDeSeriePiece")
	private String numeroDeSeriePiece;
	
	@Column(name="prixMinimum")
	private BigDecimal prixMinimum;
	
	@Column(name="prixVenteConseille")
	private BigDecimal prixVenteConseille;
	
	@Column(name="commentaireInterne")
	private String commentaireInterne;
	
	@Column(name="commentaireCommercial")
	private String commentaireCommercial;
	
	@Column(name="dossier")
	private String dossier;
	
	@Column(name="nogo")
	private String nogo;
	
	@Column(name="pieceLourde")
	private Boolean pieceLourde = true;
	
	@OneToMany(mappedBy="idPiece", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
	private List<ImagePiece> images;
	
	@Column(name="pieceOrpheline")
	private Boolean pieceOrpheline = true;
	
	@Column(name="visibleAdherent")
	private Boolean visibleAdherent = true;
	
	@Column(name="visibleStock")
	private Boolean visibleStock = true;
	
	@Column(name="dmsRef")
	private String dmsRef;
	
	@Column(name="energie")
	private String energie;
	
	@Column(name="cacheAAA")
	private Integer cacheAAA;
	
	@Column(name="dumpMoteurClub")
	private Integer dumpMoteurClub;
	
	@Column(name="importFichier")
	private String importFichier;
	
	@Column(name="idImportFichier")
	private Integer idImportFichier;
	
	@Column(name="importTypeFichier")
	private String importTypeFichier;
	
	@Column(name="status")
	private String status;
	
	@Column(name="dateAchat")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAchat;
	
	@Transient
	private BigDecimal prixAmi;
	
	@Transient
	private SocietesAmies reductionAmie;
	
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
	
	
	
	public Stock() {}
	
	public BigDecimal calculPrixAmie(SocietesAmies amie) {
		prixAmi = null;
		if(amie!=null && amie.getPourcentageReduction()!=null) {
			BigDecimal taux = amie.getPourcentageReduction().divide(new BigDecimal("100"),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
			prixAmi = prixVente.subtract(taux.multiply(prixVente,MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
		} else {
			prixAmi = prixVente;
		}
		
		return prixAmi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

	public String getTypeDePiece() {
		return typeDePiece;
	}

	public void setTypeDePiece(String typeDepiece) {
		this.typeDePiece = typeDepiece;
	}

	public String getRefConstructeur() {
		return refConstructeur;
	}

	public void setRefConstructeur(String refConstructeur) {
		this.refConstructeur = refConstructeur;
	}

	public String getCNITTypeMine() {
		return CNITTypeMine;
	}

	public void setCNITTypeMine(String cNITTypeMine) {
		CNITTypeMine = cNITTypeMine;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getKms() {
		return kms;
	}

	public void setKms(Integer kms) {
		this.kms = kms;
	}

	public String getEmpl() {
		return empl;
	}

	public void setEmpl(String empl) {
		this.empl = empl;
	}

	public String getVendeur() {
		return vendeur;
	}

	public void setVendeur(String vendeur) {
		this.vendeur = vendeur;
	}

	public Integer getGarantie() {
		return garantie;
	}

	public void setGarantie(Integer garantie) {
		this.garantie = garantie;
	}

	public BigDecimal getPrixCareco() {
		return prixCareco;
	}

	public void setPrixCareco(BigDecimal prixCareco) {
		this.prixCareco = prixCareco;
	}

	public BigDecimal getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(BigDecimal prixVente) {
		this.prixVente = prixVente;
		setPrixCareco(prixVente.multiply(new BigDecimal(0.70)).setScale(2,BigDecimal.ROUND_HALF_UP));
	}

	public String getNumLivrePolice() {
		return numLivrePolice;
	}

	public void setNumLivrePolice(String numLivrePolice) {
		this.numLivrePolice = numLivrePolice;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Boolean getExport() {
		return export;
	}

	public void setExport(Boolean export) {
		this.export = export;
	}

	public Boolean getDemonte() {
		return demonte;
	}

	public void setDemonte(Boolean demonte) {
		this.demonte = demonte;
	}

	public Date getDate1erMiseEnCirculation() {
		return date1erMiseEnCirculation;
	}

	public void setDate1erMiseEnCirculation(Date date1erMiseEnCirculation) {
		this.date1erMiseEnCirculation = date1erMiseEnCirculation;
	}

	public BigDecimal getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}

	public String getEmplacementCasier() {
		return emplacementCasier;
	}

	public void setEmplacementCasier(String emplacementCasier) {
		this.emplacementCasier = emplacementCasier;
	}

	public String getNumeroDeSeriePiece() {
		return numeroDeSeriePiece;
	}

	public void setNumeroDeSeriePiece(String numeroDeSeriePiece) {
		this.numeroDeSeriePiece = numeroDeSeriePiece;
	}

	public BigDecimal getPrixMinimum() {
		return prixMinimum;
	}

	public void setPrixMinimum(BigDecimal prixMinimum) {
		this.prixMinimum = prixMinimum;
	}

	public BigDecimal getPrixVenteConseille() {
		return prixVenteConseille;
	}

	public void setPrixVenteConseille(BigDecimal prixVenteConseille) {
		this.prixVenteConseille = prixVenteConseille;
	}

	public String getCommentaireInterne() {
		return commentaireInterne;
	}

	public void setCommentaireInterne(String commentaireInterne) {
		this.commentaireInterne = commentaireInterne;
	}

	public String getCommentaireCommercial() {
		return commentaireCommercial;
	}

	public void setCommentaireCommercial(String commentaireCommercial) {
		this.commentaireCommercial = commentaireCommercial;
	}

	public UserCompany getIdStock() {
		return idStock;
	}

	public void setIdStock(UserCompany idStock) {
		this.idStock = idStock;
	}

	public UserCompany getIdStockOrigine() {
		return idStockOrigine;
	}

	public void setIdStockOrigine(UserCompany idStockOrigine) {
		this.idStockOrigine = idStockOrigine;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getDossier() {
		return dossier;
	}

	public String getNogo() {
		return nogo;
	}

	public void setDossier(String dossier) {
		this.dossier = dossier;
	}

	public void setNogo(String nogo) {
		this.nogo = nogo;
	}

	public Boolean getPieceLourde() {
		return pieceLourde;
	}

	public void setPieceLourde(Boolean pieceLourde) {
		this.pieceLourde = pieceLourde;
	}

	public List<ImagePiece> getImages() {
		return images;
	}

	public void setImages(List<ImagePiece> images) {
		this.images = images;
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

	public String getCodeCompatibilite() {
		return codeCompatibilite;
	}

	public void setCodeCompatibilite(String codeCompatibilite) {
		this.codeCompatibilite = codeCompatibilite;
	}

	public Boolean getPieceOrpheline() {
		return pieceOrpheline;
	}

	public Boolean getVisibleAdherent() {
		return visibleAdherent;
	}

	public Boolean getVisibleStock() {
		return visibleStock;
	}

	public String getDmsRef() {
		return dmsRef;
	}

	public String getEnergie() {
		return energie;
	}

	public void setPieceOrpheline(Boolean pieceOrpheline) {
		this.pieceOrpheline = pieceOrpheline;
	}

	public void setVisibleAdherent(Boolean visibleAdherent) {
		this.visibleAdherent = visibleAdherent;
	}

	public void setVisibleStock(Boolean visibleStock) {
		this.visibleStock = visibleStock;
	}

	public void setDmsRef(String dmsRef) {
		this.dmsRef = dmsRef;
	}

	public void setEnergie(String energie) {
		this.energie = energie;
	}

	public Integer getCacheAAA() {
		return cacheAAA;
	}

	public Integer getDumpMoteurClub() {
		return dumpMoteurClub;
	}

	public String getImportFichier() {
		return importFichier;
	}

	public Integer getIdImportFichier() {
		return idImportFichier;
	}

	public String getStatus() {
		return status;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setCacheAAA(Integer cacheAAA) {
		this.cacheAAA = cacheAAA;
	}

	public void setDumpMoteurClub(Integer dumpMoteurClub) {
		this.dumpMoteurClub = dumpMoteurClub;
	}

	public void setImportFichier(String importFichier) {
		this.importFichier = importFichier;
	}

	public void setIdImportFichier(Integer idImportFichier) {
		this.idImportFichier = idImportFichier;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public String getImportTypeFichier() {
		return importTypeFichier;
	}

	public void setImportTypeFichier(String importTypeFichier) {
		this.importTypeFichier = importTypeFichier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((versionObj == null) ? 0 : versionObj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (versionObj == null) {
			if (other.versionObj != null)
				return false;
		} else if (!versionObj.equals(other.versionObj))
			return false;
		return true;
	}

	public BigDecimal getPrixAmi() {
		return prixAmi;
	}

	public void setPrixAmi(BigDecimal prixAmi) {
		this.prixAmi = prixAmi;
	}

	public SocietesAmies getReductionAmie() {
		return reductionAmie;
	}

	public void setReductionAmie(SocietesAmies reductionAmie) {
		this.reductionAmie = reductionAmie;
	}

}
