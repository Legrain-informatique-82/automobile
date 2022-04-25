package fr.legrain.tiers.model;

import java.util.ArrayList;
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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import fr.legrain.bdg.tiers.service.remote.IUserCompanyServiceRemote;
import fr.legrain.tiers.dao.jpa.ILgrEntity;
import fr.legrain.tiers.dao.jpa.MyListener;

@Entity
@Table(name = "UserCompany")
@EntityListeners({MyListener.class})
public class UserCompany implements java.io.Serializable, ILgrEntity {

	private static final long serialVersionUID = 6855728949500558500L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="idMoteurClub")
	private Integer idMoteurClub;
	
	@Column(name="idCarecoFr")
	private Integer idCarecoFr;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "idGroupeEntreprise")
	private GroupeEntreprise idGroupeEntreprise;
	
	@Column(name="nom")
	private String nom;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "idAdherent")
	private Adherent idAdherent;
	
//	@LazyCollection(LazyCollectionOption.FALSE) //annotation hibernate
//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinTable(name="SocietesAmies",
//		joinColumns={@JoinColumn(name="id", referencedColumnName="societeA")},
//		inverseJoinColumns={@JoinColumn(name="id", referencedColumnName="societeB")}
//	)
	//@Transient
	//@LazyCollection(LazyCollectionOption.FALSE) //annotation hibernate
	
	@OneToMany(mappedBy="societeA", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//	@OneToMany( fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinColumns ({
//        @JoinColumn(name="societeA", referencedColumnName = "id", insertable=false ,updatable=false),
//        @JoinColumn(name="societeB", referencedColumnName = "id", insertable=false ,updatable=false)
//    })
	private List<SocietesAmies> amies;
 	
 	@Column(name="adresse1")
	private String adresse1;
 	
 	@Column(name="adresse2")
	private String adresse2;
 	
 	@Column(name="codePostal")
	private String codePostal;
 	
 	@Column(name="ville")
	private String ville;
 	
 	@Column(name="departement")
	private String departement;
 	
 	@Column(name="region")
	private String region;
	
	@Column(name="longitude")
	private String longitude;
 	
 	@Column(name="latitude")
	private String latitude;
 	
 	@Column(name="telephone")
	private String telephone;
 	
 	@Column(name="fax")
	private String fax;
 	
 	@Column(name="email")
	private String email;
 	
 	@Column(name="active")
	private Boolean active;
 	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public GroupeEntreprise getIdGroupeEntreprise() {
		return idGroupeEntreprise;
	}

	public void setIdGroupeEntreprise(GroupeEntreprise idGroupeEntreprise) {
		this.idGroupeEntreprise = idGroupeEntreprise;
	}
	
	public List<Integer> findIdMultiSite(Integer idUserCompanyANePasInclure) {
		return findIdRelation(idUserCompanyANePasInclure, IUserCompanyServiceRemote.TYPE_SOCIETE_AMIE_MULTISITE);
	}
	
	public List<SocietesAmies> findMultiSite(Integer idUserCompanyANePasInclure) {
		return findRelation(idUserCompanyANePasInclure, IUserCompanyServiceRemote.TYPE_SOCIETE_AMIE_MULTISITE);
	}
	
	public List<Integer> findIdAmie(Integer idUserCompanyANePasInclure) {
		return findIdRelation(idUserCompanyANePasInclure, IUserCompanyServiceRemote.TYPE_SOCIETE_AMIE_AMIE);
	}
	
	public List<SocietesAmies> findAmie(Integer idUserCompanyANePasInclure) {
		return findRelation(idUserCompanyANePasInclure, IUserCompanyServiceRemote.TYPE_SOCIETE_AMIE_AMIE);
	}
	
	public List<Integer> findIdRelation(Integer idUserCompanyANePasInclure, String typeRelation) {
		List<Integer> idMultiSite = null;
		//if(amies.size()>1) { //il n'y a pas que l'entreprise de l'utilisateur dans la liste
		if(amies!=null && amies.size()>0) { //il n'y a pas que l'entreprise de l'utilisateur dans la liste
			idMultiSite = new ArrayList<Integer>();
			for (SocietesAmies a : findRelation(idUserCompanyANePasInclure,typeRelation)) {
				idMultiSite.add(a.getSocieteB().getId());
			}
		}
		return idMultiSite;
	}
	
	public List<SocietesAmies> findRelation(Integer idUserCompanyANePasInclure, String typeRelation) {
		List<SocietesAmies> idMultiSite = null;
		//if(amies.size()>1) { //il n'y a pas que l'entreprise de l'utilisateur dans la liste
		if(amies!=null && amies.size()>0) { //il n'y a pas que l'entreprise de l'utilisateur dans la liste
			idMultiSite = new ArrayList<SocietesAmies>();
			for (SocietesAmies a : amies) {
				if(a.getTypeRelation().equals(typeRelation)) {
					if(idUserCompanyANePasInclure==null || !a.getSocieteB().getId().equals(idUserCompanyANePasInclure)) {
						idMultiSite.add(a);
					}
				}
			}
		}
		return idMultiSite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((idGroupeEntreprise == null) ? 0 : idGroupeEntreprise
						.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		UserCompany other = (UserCompany) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
//		if (idGroupeEntreprise == null) {
//			if (other.idGroupeEntreprise != null)
//				return false;
//		} else if (!idGroupeEntreprise.equals(other.idGroupeEntreprise))
//			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	public Adherent getIdAdherent() {
		return idAdherent;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public String getVille() {
		return ville;
	}

	public String getDepartement() {
		return departement;
	}

	public String getRegion() {
		return region;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getTelephone() {
		return telephone;
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

	public void setIdAdherent(Adherent idAdherent) {
		this.idAdherent = idAdherent;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public Integer getIdMoteurClub() {
		return idMoteurClub;
	}

	public Integer getIdCarecoFr() {
		return idCarecoFr;
	}

	public void setIdMoteurClub(Integer idMoteurClub) {
		this.idMoteurClub = idMoteurClub;
	}

	public void setIdCarecoFr(Integer idCarecoFr) {
		this.idCarecoFr = idCarecoFr;
	}

	public void setAmies(List<SocietesAmies> amies) {
		this.amies = amies;
	}

	public List<SocietesAmies> getAmies() {
		return amies;
	}

}
