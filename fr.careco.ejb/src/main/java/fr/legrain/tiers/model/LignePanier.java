package fr.legrain.tiers.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

import javax.ejb.EJB;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.legrain.bdg.tiers.service.remote.IPanierServiceRemote;
import fr.legrain.tiers.dao.jpa.ILgrEntity;
import fr.legrain.tiers.dao.jpa.MyListener;

@Entity
@Table(name = "LignePanier")
@EntityListeners({MyListener.class})
public class LignePanier implements java.io.Serializable, ILgrEntity {
	
	private static final long serialVersionUID = 8176055288343186763L;
	
	public static final String TYPE_VENTE_MONTAGE_GARAGE = "montage_garage";
	public static final String TYPE_VENTE_LIVRAISON_CLIENT = "livraison_client";
	public static final String TYPE_VENTE_LIVRAISON_CLIENT_PAR_FOURNISSEUR = "livraison_client_par_fournisseur";
	

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	//Un panier contient une liste de transaction (id panier dans transaction)

	//@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPiece")
	private Stock idPiece;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPanier")
	private Panier panier;
	
	@Column(name="dateDateDernierChangementEtat")
	@Temporal(value = TemporalType.DATE)
	private Date dateDateDernierChangementEtat = new Date();
	
//	@Column(name="prixAchat")
//	private BigDecimal prixAchat;
	
	@Column(name="statut")
	private String statut; //(Approuvé,)
	
	@Column(name="prixVenteTTCFinal")
	private BigDecimal prixVenteTTCFinal;
	
	@Column(name="prixVenteHTFinal")
	private BigDecimal prixVenteHTFinal; 
	
	@ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "transactionAchatVente")
	private TransactionAchatVente transactionAchatVente;
	
 	@Column(name="tauxTVA")
	private BigDecimal tauxTVA; 

 	@Column(name="montantTVA")
	private BigDecimal montantTVA; 

 	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, /*CascadeType.MERGE,*/ CascadeType.REFRESH})
 	@JoinColumn(name = "idContratPE")
 	private GarantieCareco idContratPE; 
 	
 	@Column(name="montantPE")
	private BigDecimal montantPE;
 	
	@Column(name="montantPEHT")
	private BigDecimal montantPEHT; 
	
	@Column(name="montantPETVA")
	private BigDecimal montantPETVA; 
	
	@Column(name="tauxTVAPE")
	private BigDecimal tauxTVAPE; 
 	
	@Column(name="supplementLivraison")
	private BigDecimal supplementLivraison; 
	
	@Column(name="supplementLivraisonHT")
	private BigDecimal supplementLivraisonHT;

	@Column(name="supplementLivraisonTVA")
	private BigDecimal supplementLivraisonTVA; 
	
	@Column(name="tauxTVALivraison")
	private BigDecimal tauxTVALivraison; 
	
//	@Column(name="PEDesactivee")
//	private Boolean PEDesactivee = false;
	
	@Column(name="dureePeVendu")
	private Integer dureePeVendu;
	
	@Column(name="typeVente")
	private String typeVente;
	
	@Column(name="commentaireCommande")
	private String commentaireCommande;
	
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
	
	public void initPrix() {
//		if(idPiece.getPrixMinimum()!=null && idPiece.getPrixCareco()!=null && idPiece.getPrixMinimum().compareTo(idPiece.getPrixCareco())>0) {
//			setPrixVenteHTFinal(idPiece.getPrixMinimum());
//		} else {
//			setPrixVenteHTFinal(idPiece.getPrixCareco());
//		}
		
		if(idPiece.getPrixMinimum()!=null && idPiece.getPrixVente()!=null && idPiece.getPrixMinimum().compareTo(idPiece.getPrixVente())>0) {
			setPrixVenteHTFinal(idPiece.getPrixMinimum());
		} else {
			setPrixVenteHTFinal(idPiece.getPrixVente());
		}
	}
	
	public void recalculHTFromTTC() {
		BigDecimal tx = tauxTVA;
		if(tx==null) {
			tx = panier.getTva();
		}
//		BigDecimal txDivise = tx.setScale(2,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100));
//		
//		BigDecimal diviseur = new BigDecimal(1).add(txDivise,MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
//		
//		prixVenteHTFinal = prixVenteTTCFinal.divide(diviseur,MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
		
		//precision du diviseur = 3, car sinon pour un taux de 19,60% il arrondi a 1.20  au lieu de 1.196
		prixVenteHTFinal = prixVenteTTCFinal.divide(BigDecimal.valueOf(1).add(
				 (tx.divide(new BigDecimal(100),MathContext.DECIMAL128).setScale(3,BigDecimal.ROUND_HALF_UP)
			)),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
	
	}
	
	public void recalculFraisLivraisonHTFromTTC() {
		BigDecimal tx =  Panier.getTauxtvagarantiepe();
		
		if(supplementLivraison!=null) {
			//precision du diviseur = 3, car sinon pour un taux de 19,60% il arrondi a 1.20  au lieu de 1.196
			supplementLivraisonHT = supplementLivraison.divide(BigDecimal.valueOf(1).add(
					(tx.divide(new BigDecimal(100),MathContext.DECIMAL128).setScale(3,BigDecimal.ROUND_HALF_UP)
				)),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
	
	}
	
	public BigDecimal caculTTCPourPE() {
		BigDecimal tx =  Panier.getTauxtvagarantiepe();
		
		BigDecimal montantTVALigne = prixVenteHTFinal.multiply(
				new BigDecimal(1).add(tx).divide(new BigDecimal(100)
				)
				).setScale(2,BigDecimal.ROUND_HALF_UP);
		return prixVenteHTFinal.add(montantTVALigne);
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Stock getIdPiece() {
		return idPiece;
	}

	public void setIdPiece(Stock idPiece) {
		this.idPiece = idPiece;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Date getDateDateDernierChangementEtat() {
		return dateDateDernierChangementEtat;
	}

	public void setDateDateDernierChangementEtat(
			Date dateDateDernierChangementEtat) {
		this.dateDateDernierChangementEtat = dateDateDernierChangementEtat;
	}

//	public String getPrixAchat() {
//		return prixAchat;
//	}
//
//	public void setPrixAchat(String prixAchat) {
//		this.prixAchat = prixAchat;
//	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public TransactionAchatVente getTransactionAchatVente() {
		return transactionAchatVente;
	}

	public void setTransactionAchatVente(TransactionAchatVente transactionAchatVente) {
		this.transactionAchatVente = transactionAchatVente;
	}

	public BigDecimal getPrixVenteTTCFinal() {
		return prixVenteTTCFinal;
	}

	public void setPrixVenteTTCFinal(BigDecimal prixVenteTTCFinal) {
		this.prixVenteTTCFinal = prixVenteTTCFinal;
	}

	public BigDecimal getPrixVenteHTFinal() {
		return prixVenteHTFinal;
	}

	public void setPrixVenteHTFinal(BigDecimal prixVenteHTFinal) {
		this.prixVenteHTFinal = prixVenteHTFinal;
	}

	public BigDecimal getTauxTVA() {
		return tauxTVA;
	}

	public BigDecimal getMontantTVA() {
		return montantTVA;
	}

	public GarantieCareco getIdContratPE() {
		return idContratPE;
	}

	public BigDecimal getMontantPE() {
		return montantPE;
	}

	public BigDecimal getSupplementLivraison() {
		return supplementLivraison;
	}

	public void setTauxTVA(BigDecimal tauxTVA) {
		this.tauxTVA = tauxTVA;
	}

	public void setMontantTVA(BigDecimal montantTVA) {
		this.montantTVA = montantTVA;
	}

	public void setIdContratPE(GarantieCareco idContratPE) {
		this.idContratPE = idContratPE;
	}

	public void setMontantPE(BigDecimal montantPE) {
		this.montantPE = montantPE;
	}

	public void setSupplementLivraison(BigDecimal supplementLivraison) {
		System.out.println("supplementLivraison => id ligne "+id+" ** avant "+this.supplementLivraison+" après : "+supplementLivraison);
		this.supplementLivraison = supplementLivraison;
		//setSupplementLivraisonHT(supplementLivraison);
		recalculFraisLivraisonHTFromTTC();
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
		LignePanier other = (LignePanier) obj;
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

	public BigDecimal getMontantPEHT() {
		return montantPEHT;
	}

	public BigDecimal getMontantPETVA() {
		return montantPETVA;
	}

	public BigDecimal getTauxTVAPE() {
		return tauxTVAPE;
	}

	public BigDecimal getSupplementLivraisonHT() {
		return supplementLivraisonHT;
	}

	public BigDecimal getSupplementLivraisonTVA() {
		return supplementLivraisonTVA;
	}

	public BigDecimal getTauxTVALivraison() {
		return tauxTVALivraison;
	}

	public void setMontantPEHT(BigDecimal montantPEHT) {
		this.montantPEHT = montantPEHT;
	}

	public void setMontantPETVA(BigDecimal montantPETVA) {
		this.montantPETVA = montantPETVA;
	}

	public void setTauxTVAPE(BigDecimal tauxTVAPE) {
		this.tauxTVAPE = tauxTVAPE;
	}

	public void setSupplementLivraisonHT(BigDecimal supplementLivraisonHT) {
		this.supplementLivraisonHT = supplementLivraisonHT;
	}

	public void setSupplementLivraisonTVA(BigDecimal supplementLivraisonTVA) {
		this.supplementLivraisonTVA = supplementLivraisonTVA;
	}

	public void setTauxTVALivraison(BigDecimal tauxTVALivraison) {
		this.tauxTVALivraison = tauxTVALivraison;
	}

	public Integer getDureePeVendu() {
		return dureePeVendu;
	}

	public String getTypeVente() {
		return typeVente;
	}

	public String getCommentaireCommande() {
		return commentaireCommande;
	}

	public void setDureePeVendu(Integer dureePeVendu) {
		this.dureePeVendu = dureePeVendu;
	}

	public void setTypeVente(String typeVente) {
		this.typeVente = typeVente;
	}

	public void setCommentaireCommande(String commentaireCommande) {
		this.commentaireCommande = commentaireCommande;
	}

//	public Boolean getPEDesactivee() {
//		return PEDesactivee;
//	}
//
//	public void setPEDesactivee(Boolean pEDesactivee) {
//		PEDesactivee = pEDesactivee;
//	}
	
	
}
