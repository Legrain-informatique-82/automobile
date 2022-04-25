package fr.legrain.careco.aaa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import fr.legrain.tiers.dao.jpa.ILgrEntity;

@Entity
@Table(name = "Immatriculation")
@NamedQueries(value = { 
		@NamedQuery(name=VehiculeCacheAAA.QN.FIND_BY_IMMAT, query="select f from VehiculeCacheAAA f where f.Immat_SIV = :paramImmat "),
		@NamedQuery(name=VehiculeCacheAAA.QN.FIND_BY_VIN, query="select f from VehiculeCacheAAA f where f.Type = :vin "),
		@NamedQuery(name=VehiculeCacheAAA.QN.SELECT_ALL_MARQUE, query="select distinct v.marque  from VehiculeCacheAAA v order by v.marque")
		})
public class VehiculeCacheAAA implements java.io.Serializable {
	
	public static class QN {
		public static final String FIND_BY_IMMAT = "VehiculeCacheAAA.findByImmat";
		public static final String FIND_BY_VIN = "VehiculeCacheAAA.findByVin";
		public static final String SELECT_ALL_MARQUE = "VehiculeCacheAAA.selectAllMarque";
	}

	private static final long serialVersionUID = 3868478505036437873L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Code_Immat")
	private Integer code_Immat;
	
	@Column(name="Carrosserie")
	private String 	Carrosserie;
	
	@Column(name="Carrosserie_CG")
	private String Carrosserie_CG;
	
	@Column(name="CO2")
	private Integer CO2;
	
	@Column(name="Codif_Vin_Pref")
	private String 	Codif_Vin_Pref;
	
	@Column(name="Couleur_Vehic")
	private String 	Couleur_Vehic;
	
	@Column(name="Cylindree")
	private Integer Cylindree;
	
	@Column(name="Date_1er_Cir_Annee")
	private String 	Date_1er_Cir_Annee;
	
	@Column(name="Date_Dcg_Annee")
	private String 	Date_Dcg_Annee;
	
	@Column(name="Depollution")
	private String 	Depollution;
	
	@Column(name="Empat")
	private Integer Empat;
	
	@Column(name="Energie")
	private String 	Energie;
	
	@Column(name="Genre")
	private String 	Genre;
	
	@Column(name="Genre_V_CG")
	private String 	Genre_V_CG;
	
	@Column(name="Hauteur")
	private Integer Hauteur;
	
	@Column(name="Immat_SIV")
	private String 	Immat_SIV;
	
	@Column(name="Largeur")
	private Integer Largeur;
	
	@Column(name="Longueur")
	private Integer Longueur;
	
	@Column(name="Marque")
	private String 	marque;
	
	@Column(name="Marque_Carros")
	private String 	Marque_Carros;
	
	@Column(name="Mode_Inject")
	private String 	Mode_Inject;
	
	@Column(name="Modele")
	private String 	Modele;
	
	@Column(name="Modele_Etude")
	private String Modele_Etude;
	
	@Column(name="Modele_Prf")
	private String 	Modele_Prf;
	
	@Column(name="N_Serie")
	private String 	N_Serie;
	
	@Column(name="Nb_Cylind")
	private Integer Nb_Cylind;
	
	@Column(name="Nb_Pl_Ass")
	private Integer Nb_Pl_Ass;
	
	@Column(name="Nb_Portes")
	private Integer Nb_Portes;
	
	@Column(name="Nb_Soupapes")
	private Integer Nb_Soupapes;
	
	@Column(name="Nb_Vitesse")
	private Integer Nb_Vitesse;
	
	@Column(name="Nb_Volumes")
	private Integer Nb_Volumes;
	
	@Column(name="Poids_Vide")
	private Integer Poids_Vide;
	
	@Column(name="Propulsion")
	private String 	Propulsion;
	
	@Column(name="Ptr")
	private Integer Ptr;
	
	@Column(name="Ptr_Prf")
	private Integer Ptr_Prf;
	
	@Column(name="Puis_Ch")
	private Integer	 Puis_Ch;
	
	@Column(name="Puis_Fisc")
	private Integer Puis_Fisc;
	
	@Column(name="Tp_Boite_Vit")
	private String 	Tp_Boite_Vit;
	
	@Column(name="Turbo_Compr")
	private String 	Turbo_Compr;
	
	@Column(name="Type")
	private String 	Type;
	
	@Column(name="Type_Vin_Cg")
	private String 	Type_Vin_Cg;
	
	@Column(name="Version")
	private String 	Version;
	
	@Column(name="Date_Dcg_Jour")
	private String 	Date_Dcg_Jour;
	
	@Column(name="Date_Dcg_Mois")
	private String 	Date_Dcg_Mois;
	
	@Column(name="Date_1er_Cir_Jour")
	private String 	Date_1er_Cir_Jour;
	
	@Column(name="Date_1er_Cir_Mois")
	private String 	Date_1er_Cir_Mois;
	
	@Column(name="Nom_proprio")
	private String 	Nom_proprio;
	
	@Column(name="DateMAJ")
	@Temporal(TemporalType.DATE)
	private Date DateMAJ;
	
	@Column(name="Code_Moteur")
	private String 	Code_Moteur;
	
	@Column(name="Cons_Exurb")
	private String 	Cons_Exurb;
	
	@Column(name="Cons_Urb")
	private String 	Cons_Urb;
	
	@Column(name="Cons_Mixte")
	private String 	Cons_Mixte;
	
	@Column(name="Type_Prf")
	private String 	Type_Prf;
	
	@Column(name="Type_Var_Ver_Prf")
	private String 	Type_Var_Ver_Prf;
	
	@Column(name="Prix_Vehic")
	private String Prix_Vehic;
	
	@Column(name="Puis_Kw")
	private String 	Puis_Kw;
	
	@Column(name="siren")
	//@Transient
	private String 	siren;
	
	@Column(name="pneu")
	//@Transient
	private String 	pneu;
	
	@Column(name="ktype")
	//@Transient
	private String 	kType;
	
	public boolean estVide() {
		boolean vide = false;
		if(Immat_SIV==null
				&& Type==null
				&& Type_Vin_Cg==null
				&& Type_Var_Ver_Prf==null
				&& Type_Prf==null
				) {
			vide = true; 
		}
		return vide;
	}
	
	static public String immatNettoyee(String immat) {
		//supprimer les espaces ou les tirets
		immat = immat.replaceAll("-", "").replaceAll(" ", "");
		immat = immat.toUpperCase();
		immat = immat.trim();
		return immat;
	}
	
	static public String immatPourWebService(String immat) {
		String immatOK = null;
		
		if(immat!=null && !immat.equals("")) {
			
			immat = immatNettoyee(immat);
			
			if(Character.isLetter(immat.charAt(0))) {
				//commence par une lettre donc nouvelle immatriculation du type AB-123-CD
				immatOK = immat;
			} else {
				//ancienne immatriculation du type 1234 AB 56, il peut y avoir plus de lettre,
//				if(Character.isDigit(immat.charAt(0)) && Character.isLetter(immat.charAt(2))){
//					//ancienne immatriculation mais déjà au bon format ("inversé") => 56AB1234
//					immatOK = immat;
//				} else {
					//il faut inverser pour le webservice => 56AB1234
					char[] charImmat = immat.toCharArray();
					String debut="";
					String milieu="";
					String fin="";
					for (int i = 0; i < charImmat.length; i++) {
						char c = charImmat[i];
						if(Character.isDigit(charImmat[i])) {
							if(milieu.equals(""))//on n'a pas passé les lettre
								debut+=charImmat[i];
							else
								fin+=charImmat[i];
						} else {
							milieu+=charImmat[i];
						}
					}
					while(debut.length()<4) {
						debut = "0"+debut;
					}
					immatOK = fin+milieu+debut;
				}
//			}
			
		}
		
		return immatOK;
	}
	
	public List<DimensionPneu> findListeDimensionPneu() {
		List<DimensionPneu> r = new ArrayList<DimensionPneu>();
		if(pneu!=null && !pneu.equals("")) {
			String[] dim = pneu.split("\\|");
			for (String s : dim) {
				r.add(new DimensionPneu(s));
			}
		}
		return r;
	}
	
//    @PrePersist void onPrePersist() {
//    	setDateMAJ(new Date());
//    }
//    
//    @PreUpdate void onPreUpdate() {
//    	setDateMAJ(new Date());
//    }

	public Integer getCode_Immat() {
		return code_Immat;
	}

	public void setCode_Immat(Integer code_Immat) {
		this.code_Immat = code_Immat;
	}

	public String getCarrosserie() {
		return Carrosserie;
	}

	public void setCarrosserie(String carrosserie) {
		Carrosserie = carrosserie;
	}

	public String getCarrosserie_CG() {
		return Carrosserie_CG;
	}

	public void setCarrosserie_CG(String carrosserie_CG) {
		Carrosserie_CG = carrosserie_CG;
	}

	public Integer getCO2() {
		return CO2;
	}

	public void setCO2(Integer cO2) {
		CO2 = cO2;
	}

	public String getCodif_Vin_Pref() {
		return Codif_Vin_Pref;
	}

	public void setCodif_Vin_Pref(String codif_Vin_Pref) {
		Codif_Vin_Pref = codif_Vin_Pref;
	}

	public String getCouleur_Vehic() {
		return Couleur_Vehic;
	}

	public void setCouleur_Vehic(String couleur_Vehic) {
		Couleur_Vehic = couleur_Vehic;
	}

	public Integer getCylindree() {
		return Cylindree;
	}

	public void setCylindree(Integer cylindree) {
		Cylindree = cylindree;
	}

	public String getDate_1er_Cir_Annee() {
		return Date_1er_Cir_Annee;
	}

	public void setDate_1er_Cir_Annee(String date_1er_Cir_Annee) {
		Date_1er_Cir_Annee = date_1er_Cir_Annee;
	}

	public String getDate_Dcg_Annee() {
		return Date_Dcg_Annee;
	}

	public void setDate_Dcg_Annee(String date_Dcg_Annee) {
		Date_Dcg_Annee = date_Dcg_Annee;
	}

	public String getDepollution() {
		return Depollution;
	}

	public void setDepollution(String depollution) {
		Depollution = depollution;
	}

	public Integer getEmpat() {
		return Empat;
	}

	public void setEmpat(Integer empat) {
		Empat = empat;
	}

	public String getEnergie() {
		return Energie;
	}

	public void setEnergie(String energie) {
		Energie = energie;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getGenre_V_CG() {
		return Genre_V_CG;
	}

	public void setGenre_V_CG(String genre_V_CG) {
		Genre_V_CG = genre_V_CG;
	}

	public Integer getHauteur() {
		return Hauteur;
	}

	public void setHauteur(Integer hauteur) {
		Hauteur = hauteur;
	}

	public String getImmat_SIV() {
		return Immat_SIV;
	}

	public void setImmat_SIV(String immat_SIV) {
		Immat_SIV = immat_SIV;
	}

	public Integer getLargeur() {
		return Largeur;
	}

	public void setLargeur(Integer largeur) {
		Largeur = largeur;
	}

	public Integer getLongueur() {
		return Longueur;
	}

	public void setLongueur(Integer longueur) {
		Longueur = longueur;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getMarque_Carros() {
		return Marque_Carros;
	}

	public void setMarque_Carros(String marque_Carros) {
		Marque_Carros = marque_Carros;
	}

	public String getMode_Inject() {
		return Mode_Inject;
	}

	public void setMode_Inject(String mode_Inject) {
		Mode_Inject = mode_Inject;
	}

	public String getModele() {
		return Modele;
	}

	public void setModele(String modele) {
		Modele = modele;
	}

	public String getModele_Etude() {
		return Modele_Etude;
	}

	public void setModele_Etude(String modele_Etude) {
		Modele_Etude = modele_Etude;
	}

	public String getModele_Prf() {
		return Modele_Prf;
	}

	public void setModele_Prf(String modele_Prf) {
		Modele_Prf = modele_Prf;
	}

	public String getN_Serie() {
		return N_Serie;
	}

	public void setN_Serie(String n_Serie) {
		N_Serie = n_Serie;
	}

	public Integer getNb_Cylind() {
		return Nb_Cylind;
	}

	public void setNb_Cylind(Integer nb_Cylind) {
		Nb_Cylind = nb_Cylind;
	}

	public Integer getNb_Pl_Ass() {
		return Nb_Pl_Ass;
	}

	public void setNb_Pl_Ass(Integer nb_Pl_Ass) {
		Nb_Pl_Ass = nb_Pl_Ass;
	}

	public Integer getNb_Portes() {
		return Nb_Portes;
	}

	public void setNb_Portes(Integer nb_Portes) {
		Nb_Portes = nb_Portes;
	}

	public Integer getNb_Soupapes() {
		return Nb_Soupapes;
	}

	public void setNb_Soupapes(Integer nb_Soupapes) {
		Nb_Soupapes = nb_Soupapes;
	}

	public Integer getNb_Vitesse() {
		return Nb_Vitesse;
	}

	public void setNb_Vitesse(Integer nb_Vitesse) {
		Nb_Vitesse = nb_Vitesse;
	}

	public Integer getNb_Volumes() {
		return Nb_Volumes;
	}

	public void setNb_Volumes(Integer nb_Volumes) {
		Nb_Volumes = nb_Volumes;
	}

	public Integer getPoids_Vide() {
		return Poids_Vide;
	}

	public void setPoids_Vide(Integer poids_Vide) {
		Poids_Vide = poids_Vide;
	}

	public String getPropulsion() {
		return Propulsion;
	}

	public void setPropulsion(String propulsion) {
		Propulsion = propulsion;
	}

	public Integer getPtr() {
		return Ptr;
	}

	public void setPtr(Integer ptr) {
		Ptr = ptr;
	}

	public Integer getPtr_Prf() {
		return Ptr_Prf;
	}

	public void setPtr_Prf(Integer ptr_Prf) {
		Ptr_Prf = ptr_Prf;
	}

	public Integer getPuis_Ch() {
		return Puis_Ch;
	}

	public void setPuis_Ch(Integer puis_Ch) {
		Puis_Ch = puis_Ch;
	}

	public Integer getPuis_Fisc() {
		return Puis_Fisc;
	}

	public void setPuis_Fisc(Integer puis_Fisc) {
		Puis_Fisc = puis_Fisc;
	}

	public String getTp_Boite_Vit() {
		return Tp_Boite_Vit;
	}

	public void setTp_Boite_Vit(String tp_Boite_Vit) {
		Tp_Boite_Vit = tp_Boite_Vit;
	}

	public String getTurbo_Compr() {
		return Turbo_Compr;
	}

	public void setTurbo_Compr(String turbo_Compr) {
		Turbo_Compr = turbo_Compr;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getType_Vin_Cg() {
		return Type_Vin_Cg;
	}

	public void setType_Vin_Cg(String type_Vin_Cg) {
		Type_Vin_Cg = type_Vin_Cg;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getDate_Dcg_Jour() {
		return Date_Dcg_Jour;
	}

	public void setDate_Dcg_Jour(String date_Dcg_Jour) {
		Date_Dcg_Jour = date_Dcg_Jour;
	}

	public String getDate_Dcg_Mois() {
		return Date_Dcg_Mois;
	}

	public void setDate_Dcg_Mois(String date_Dcg_Mois) {
		Date_Dcg_Mois = date_Dcg_Mois;
	}

	public String getDate_1er_Cir_Jour() {
		return Date_1er_Cir_Jour;
	}

	public void setDate_1er_Cir_Jour(String date_1er_Cir_Jour) {
		Date_1er_Cir_Jour = date_1er_Cir_Jour;
	}

	public String getDate_1er_Cir_Mois() {
		return Date_1er_Cir_Mois;
	}

	public void setDate_1er_Cir_Mois(String date_1er_Cir_Mois) {
		Date_1er_Cir_Mois = date_1er_Cir_Mois;
	}

	public String getNom_proprio() {
		return Nom_proprio;
	}

	public void setNom_proprio(String nom_proprio) {
		Nom_proprio = nom_proprio;
	}

	public Date getDateMAJ() {
		return DateMAJ;
	}

	public void setDateMAJ(Date dateMAJ) {
		DateMAJ = dateMAJ;
	}

	public String getCode_Moteur() {
		return Code_Moteur;
	}

	public void setCode_Moteur(String code_Moteur) {
		Code_Moteur = code_Moteur;
	}

	public String getCons_Exurb() {
		return Cons_Exurb;
	}

	public void setCons_Exurb(String cons_Exurb) {
		Cons_Exurb = cons_Exurb;
	}

	public String getCons_Urb() {
		return Cons_Urb;
	}

	public void setCons_Urb(String cons_Urb) {
		Cons_Urb = cons_Urb;
	}

	public String getCons_Mixte() {
		return Cons_Mixte;
	}

	public void setCons_Mixte(String cons_Mixte) {
		Cons_Mixte = cons_Mixte;
	}

	public String getType_Prf() {
		return Type_Prf;
	}

	public void setType_Prf(String type_Prf) {
		Type_Prf = type_Prf;
	}

	public String getType_Var_Ver_Prf() {
		return Type_Var_Ver_Prf;
	}

	public void setType_Var_Ver_Prf(String type_Var_Ver_Prf) {
		Type_Var_Ver_Prf = type_Var_Ver_Prf;
	}

	public String getPrix_Vehic() {
		return Prix_Vehic;
	}

	public void setPrix_Vehic(String prix_Vehic) {
		Prix_Vehic = prix_Vehic;
	}

	public String getPuis_Kw() {
		return Puis_Kw;
	}

	public void setPuis_Kw(String puis_Kw) {
		Puis_Kw = puis_Kw;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSiren() {
		return siren;
	}

	public void setSiren(String siren) {
		this.siren = siren;
	}

	public String getPneu() {
		return pneu;
	}

	public void setPneu(String pneu) {
		this.pneu = pneu;
	}

	public String getkType() {
		return kType;
	}

	public void setkType(String kType) {
		this.kType = kType;
	}

	
}
