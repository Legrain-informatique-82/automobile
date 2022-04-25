package fr.legrain.tiers.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.lib.data.LibCalcul;
import fr.legrain.tiers.dao.jpa.ILgrEntity;
import fr.legrain.tiers.dao.jpa.MyListener;

@Entity
@Table(name = "Panier")
@EntityListeners({MyListener.class})
public class Panier implements java.io.Serializable, ILgrEntity {
	
	private static final long serialVersionUID = -5445894979083785721L;
	
	@Transient
	private static final BigDecimal tauxTVAGarantiePE = new BigDecimal("20.0"); //fixe, cf doc
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "idClient")
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "idEntreprise")
	private UserCompany idEntreprise;
	
	@Column(name="validite")
	private String validite;
	
	@Column(name="datePanier")
	@Temporal(TemporalType.DATE)
	private Date datePanier;
	
	@Column(name="dateFin")
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "vendeur")
	private User vendeur;
	
	@Column(name="nbPiece")
	private Integer nbPiece = 0;
	
	@Column(name="devis")
	private Boolean devis = false;
	
	@Column(name="prixHT")
	private BigDecimal prixHT = new BigDecimal(0);
	
	@Column(name="tva", scale = 2, precision = 9)
	private BigDecimal tva;
	
	@Column(name="totalTTC")
	private BigDecimal totalTTC = new BigDecimal(0);
	
	@Column(name="montantTVA")
	private BigDecimal montantTVA;
	
	@Column(name="montantLivraison")
	private BigDecimal montantLivraison;
	
	@Column(name="montantLivraisonHT")
	private BigDecimal montantLivraisonHT;
	
	@Column(name="montantLivraisonTVA")
	private BigDecimal montantLivraisonTVA;
	
	@Column(name="totalPEHT")
	private BigDecimal totalPEHT;
	
	@Column(name="totalPETVA")
	private BigDecimal totalPETVA;
	
	@Column(name="totalPETTC")
	private BigDecimal totalPETTC;
	
	@Column(name="commentaire")
	private String commentaire;
	
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
	
	@OneToMany(mappedBy="panier", orphanRemoval=true, fetch = FetchType.EAGER , cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<LignePanier> lignes;
	
	public boolean panierPerime() {
		return new Date().after(dateFin);
	}
	
	public boolean pieceDejaDansPanier(Stock s) {
		boolean trouve = false;
		if(lignes!=null) {
			for (LignePanier l : lignes) {
				if(l.getIdPiece().getId().equals(s.getId())) {
					trouve = true;
				}
			}
		}
		return trouve;
	}
	
	public void ajouteLigne(Stock s) {
		if(lignes==null) {
			lignes = new LinkedList<LignePanier>();
		}
		
		if(!pieceDejaDansPanier(s)) {
			//le panier ne contient pas déjà de ligne pour cette pièce donc on peu l'ajouter
			LignePanier ligne = new LignePanier();
			s.setStatus(IStockServiceRemote.STATUS_PIECE_PANIER);
			ligne.setIdPiece(s);
			
			
//			if(s.getPrixMinimum()!=null && s.getPrixCareco()!=null && s.getPrixMinimum().compareTo(s.getPrixCareco())>0) {
//				ligne.setPrixVenteHTFinal(s.getPrixMinimum());
//			} else {
//				ligne.setPrixVenteHTFinal(s.getPrixCareco());
//			}
			ligne.initPrix();
			
			lignes.add(ligne);
			ligne.setPanier(this);
			
			nbPiece++;
			
			recalcul();
		}
	}
	
	public void supprimerLigne(Stock s) {
		if(lignes!=null) {
			LignePanier ligneASupprimer = null;
			for (LignePanier l : lignes) {
				if(l.getIdPiece().getId().equals(s.getId())) {
					ligneASupprimer = l;
				}
			}
			if(ligneASupprimer!=null) {
				if(lignes.remove(ligneASupprimer)) {
					ligneASupprimer.setPanier(null);
					nbPiece--;

					recalcul();
				}
			}
		}
	}
	
	public void recalcul() {
		totalTTC = new BigDecimal("0");
		prixHT = new BigDecimal("0");
		montantTVA = new BigDecimal("0");
		
		totalPEHT = new BigDecimal("0");
		totalPETTC = new BigDecimal("0");
		totalPETVA = new BigDecimal("0");
		
		montantLivraison = new BigDecimal("0");
		montantLivraisonHT = new BigDecimal("0");
		montantLivraisonTVA = new BigDecimal("0");
		
		tva = client.getTva();
		
		BigDecimal montantTVALigne = null;
		for (LignePanier l : lignes) {
			
			l.setTauxTVA(tva);
			
			l.setMontantPE(new BigDecimal("0"));
			l.setMontantPEHT(new BigDecimal("0"));
			l.setTauxTVAPE(null);
			
			if(l.getIdContratPE()!=null) {
				if(l.getDureePeVendu()!=null) {
					if(l.getDureePeVendu()==12) {
						l.setMontantPE(l.getIdContratPE().getPrixPE12TTC());
						l.setMontantPEHT(l.getIdContratPE().getPrixPE12HT());
						l.setTauxTVAPE(null);
					} if(l.getDureePeVendu()==24) {
						l.setMontantPE(l.getIdContratPE().getPrixPE24TTC());
						l.setMontantPEHT(l.getIdContratPE().getPrixPE24HT());
						l.setTauxTVAPE(null);
					}
				} else {
					//pas de modif de la PE dans le panier, on prends la PE par défaut de la pièce 
					if(l.getIdPiece().getGarantie()==12) {
						l.setMontantPE(l.getIdContratPE().getPrixPE12TTC());
						l.setMontantPEHT(l.getIdContratPE().getPrixPE12HT());
						l.setTauxTVAPE(null);
					} if(l.getIdPiece().getGarantie()==24) {
						l.setMontantPE(l.getIdContratPE().getPrixPE24TTC());
						l.setMontantPEHT(l.getIdContratPE().getPrixPE24HT());
						l.setTauxTVAPE(null);
					}
				}
				
				totalPEHT = totalPEHT.add(l.getMontantPEHT());
				totalPETTC = totalPETTC.add(l.getMontantPE());
			} 
//			else {
//				l.setMontantPE(new BigDecimal("0"));
//				l.setMontantPEHT(new BigDecimal("0"));
//				l.setTauxTVAPE(null);
//			}
			
			if(l.getSupplementLivraison()!=null) {
				montantLivraison = montantLivraison.add(l.getSupplementLivraison());
				montantLivraisonHT = montantLivraisonHT.add(l.getSupplementLivraisonHT());
			}
			
			if(tva!=null) {
				//tva = tva.setScale(2,BigDecimal.ROUND_HALF_UP);
//				montantTVALigne = l.getPrixVenteHTFinal().multiply(
//						new BigDecimal(1).add(tva).divide(new BigDecimal(100)
//						)
//						).setScale(2,BigDecimal.ROUND_HALF_UP);
				montantTVALigne = (l.getPrixVenteHTFinal().multiply(tva.divide(new BigDecimal(100))));
				l.setMontantTVA(montantTVALigne);
				montantTVA = montantTVA.add(montantTVALigne);
				//l.setPrixVenteTTCFinal(l.getPrixVenteHTFinal().add(montantTVALigne));
				
				l.setPrixVenteTTCFinal(LibCalcul.arrondi(l.getPrixVenteHTFinal().add(montantTVALigne)));
				//new BigDecimal(montantTVALigne.toString().substring(0,montantTVALigne.toString().indexOf(".")+2));
				//l.setPrixVenteTTCFinal(l.getPrixVenteHTFinal().add(montantTVALigne));
			}
			
			prixHT = prixHT.add(l.getPrixVenteHTFinal());
		}
		
		//calcul du TTC pour les pièces
		totalTTC = prixHT.add(montantTVA,MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
		
		//ajout des autres totaux
		//Total PE
		totalTTC = totalTTC.add(totalPETTC,MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
		//totalLivraison
		totalTTC = totalTTC.add(montantLivraison,MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
		
		//mise à jour des totaux TVA
		totalPETVA = totalPETTC.subtract(totalPEHT,MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
		montantLivraisonTVA = montantLivraison.subtract(montantLivraisonHT,MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	public LignePanier findLigne(int idLignePanier) {
		boolean trouve = false;
		
		Iterator<LignePanier> ite = lignes.iterator();
		
		LignePanier retour = null;
		LignePanier var = null;
		
		while (ite.hasNext() && !trouve) {
			var = ite.next();
			if(var.getId().equals(idLignePanier)) {
				retour = var;
				trouve = true;
			}
		}
		
		return retour;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValidite() {
		return validite;
	}

	public void setValidite(String validite) {
		this.validite = validite;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDatePanier() {
		return datePanier;
	}

	public void setDatePanier(Date datePanier) {
		this.datePanier = datePanier;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public User getVendeur() {
		return vendeur;
	}

	public void setVendeur(User vendeur) {
		this.vendeur = vendeur;
	}

	public Integer getNbPiece() {
		return nbPiece;
	}

	public void setNbPiece(Integer nbPiece) {
		this.nbPiece = nbPiece;
	}

	public Boolean getDevis() {
		return devis;
	}

	public void setDevis(Boolean devis) {
		this.devis = devis;
	}

	public BigDecimal getPrixHT() {
		return prixHT;
	}

	public void setPrixHT(BigDecimal prixHT) {
		this.prixHT = prixHT;
	}

	public BigDecimal getTva() {
		return tva;
	}

	public void setTva(BigDecimal tva) {
		this.tva = tva;
	}

	public BigDecimal getTotalTTC() {
		return totalTTC;
	}

	public void setTotalTTC(BigDecimal totalTTC) {
		this.totalTTC = totalTTC;
	}

//	public Boolean getActif() {
//		return actif;
//	}
//
//	public void setActif(Boolean actif) {
//		this.actif = actif;
//	}

	public List<LignePanier> getLignes() {
		return lignes;
	}

	public void setLignes(List<LignePanier> lignes) {
		this.lignes = lignes;
	}

	public UserCompany getIdEntreprise() {
		return idEntreprise;
	}

	public void setIdEntreprise(UserCompany idEntreprise) {
		this.idEntreprise = idEntreprise;
	}

	public BigDecimal getMontantTVA() {
		return montantTVA;
	}

	public void setMontantTVA(BigDecimal montantTVA) {
		this.montantTVA = montantTVA;
	}

	public BigDecimal getMontantLivraison() {
		return montantLivraison;
	}

	public void setMontantLivraison(BigDecimal montantLivraison) {
		this.montantLivraison = montantLivraison;
	}

	public static BigDecimal getTauxtvagarantiepe() {
		return tauxTVAGarantiePE;
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

	public BigDecimal getMontantLivraisonHT() {
		return montantLivraisonHT;
	}

	public BigDecimal getMontantLivraisonTVA() {
		return montantLivraisonTVA;
	}

	public BigDecimal getTotalPEHT() {
		return totalPEHT;
	}

	public BigDecimal getTotalPETVA() {
		return totalPETVA;
	}

	public BigDecimal getTotalPETTC() {
		return totalPETTC;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setMontantLivraisonHT(BigDecimal montantLivraisonHT) {
		this.montantLivraisonHT = montantLivraisonHT;
	}

	public void setMontantLivraisonTVA(BigDecimal montantLivraisonTVA) {
		this.montantLivraisonTVA = montantLivraisonTVA;
	}

	public void setTotalPEHT(BigDecimal totalPEHT) {
		this.totalPEHT = totalPEHT;
	}

	public void setTotalPETVA(BigDecimal totalPETVA) {
		this.totalPETVA = totalPETVA;
	}

	public void setTotalPETTC(BigDecimal totalPETTC) {
		this.totalPETTC = totalPETTC;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}
