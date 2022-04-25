package fr.legrain.careco.aaa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.legrain.bdg.model.ModelObject;

public class VehiculeCacheAAADTO  extends ModelObject implements java.io.Serializable {


	private Integer Code_Immat;
	private String 	Carrosserie;
	private String Carrosserie_CG;
	private Integer CO2;
	private String 	Codif_Vin_Pref;
	private String 	Couleur_Vehic;
	private Integer Cylindree;
	private String 	Date_1er_Cir_Annee;
	private String 	Date_Dcg_Annee;
	private String 	Depollution;
	private Integer Empat;
	private String 	Energie;
	private String 	Genre;
	private String 	Genre_V_CG;
	private Integer Hauteur;
	private String 	Immat_SIV;
	private Integer Largeur;
	private Integer Longueur;
	private String 	Marque;
	private String 	Marque_Carros;
	private String 	Mode_Inject;
	private String 	Modele;
	private String Modele_Etude;
	private String 	Modele_Prf;
	private String 	N_Serie;
	private Integer Nb_Cylind;
	private Integer Nb_Pl_Ass;
	private Integer Nb_Portes;
	private Integer Nb_Soupapes;
	private Integer Nb_Vitesse;
	private Integer Nb_Volumes;
	private Integer Poids_Vide;
	private String 	Propulsion;
	private Integer Ptr;
	private Integer Ptr_Prf;
	private Integer	 Puis_Ch;
	private Integer Puis_Fisc;
	private String 	Tp_Boite_Vit;
	private String 	Turbo_Compr;
	private String 	Type;
	private String 	Type_Vin_Cg;
	private String 	Version;
	private String 	Date_Dcg_Jour;
	private String 	Date_Dcg_Mois;
	private String 	Date_1er_Cir_Jour;
	private String 	Date_1er_Cir_Mois;
	private String 	Nom_proprio;
	private Date DateMAJ;
	private String 	Code_Moteur;
	private String 	Cons_Exurb;
	private String 	Cons_Urb;
	private String 	Cons_Mixte;
	private String 	Type_Prf;
	private String 	Type_Var_Ver_Prf;
	private String Prix_Vehic;
	private String 	Puis_Kw;

}
