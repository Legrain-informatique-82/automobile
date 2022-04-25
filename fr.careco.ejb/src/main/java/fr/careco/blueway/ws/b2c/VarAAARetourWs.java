
package fr.careco.blueway.ws.b2c;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Var_AAA_retourWs complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Var_AAA_retourWs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Type_Vin_CG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Turbo_Compr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TP_Boite_Vit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Puis_Fisc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Puis_Ch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PTR_PRF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PTR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Propulsion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Poids_Vide" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="N_Serie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NB_Volume" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NB_Vitesse" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NB_Soupapes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NB_Portes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NB_Cylind" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NB_PL_Ass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Mode_Inject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Modele_PRF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Modele_Etude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Modele" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Marque_Carros" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Marque" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Longueur" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Hauteur" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Immat_SIV" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Largeur" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Genre_V_CG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Energie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Genre_V" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Empat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Depollution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date_Dcg_Mois" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date_Dcg_Jour" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date_DCG_Annee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date_1er_Cir_Mois" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date_1er_Cir_jour" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date_1Er_CIR_Annee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cylindree" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Couleur_Vehic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Codif_Vin_PRF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Carrosserie_CG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CO2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Carrosserie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Var_AAA_retourWs", propOrder = {
    "version",
    "type",
    "typeVinCG",
    "turboCompr",
    "tpBoiteVit",
    "puisFisc",
    "puisCh",
    "ptrprf",
    "ptr",
    "propulsion",
    "poidsVide",
    "nSerie",
    "nbVolume",
    "nbVitesse",
    "nbSoupapes",
    "nbPortes",
    "nbCylind",
    "nbplAss",
    "modeInject",
    "modelePRF",
    "modeleEtude",
    "modele",
    "marqueCarros",
    "marque",
    "longueur",
    "hauteur",
    "immatSIV",
    "largeur",
    "genreVCG",
    "energie",
    "genreV",
    "empat",
    "depollution",
    "dateDcgMois",
    "dateDcgJour",
    "dateDCGAnnee",
    "date1ErCirMois",
    "date1ErCirJour",
    "date1ErCIRAnnee",
    "cylindree",
    "couleurVehic",
    "codifVinPRF",
    "carrosserieCG",
    "co2",
    "carrosserie"
})
public class VarAAARetourWs {

    @XmlElement(name = "Version", required = true)
    protected String version;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Type_Vin_CG", required = true)
    protected String typeVinCG;
    @XmlElement(name = "Turbo_Compr", required = true)
    protected String turboCompr;
    @XmlElement(name = "TP_Boite_Vit", required = true)
    protected String tpBoiteVit;
    @XmlElement(name = "Puis_Fisc", required = true)
    protected String puisFisc;
    @XmlElement(name = "Puis_Ch", required = true)
    protected String puisCh;
    @XmlElement(name = "PTR_PRF", required = true)
    protected String ptrprf;
    @XmlElement(name = "PTR", required = true)
    protected String ptr;
    @XmlElement(name = "Propulsion", required = true)
    protected String propulsion;
    @XmlElement(name = "Poids_Vide", required = true)
    protected String poidsVide;
    @XmlElement(name = "N_Serie", required = true)
    protected String nSerie;
    @XmlElement(name = "NB_Volume", required = true)
    protected String nbVolume;
    @XmlElement(name = "NB_Vitesse", required = true)
    protected String nbVitesse;
    @XmlElement(name = "NB_Soupapes", required = true)
    protected String nbSoupapes;
    @XmlElement(name = "NB_Portes", required = true)
    protected String nbPortes;
    @XmlElement(name = "NB_Cylind", required = true)
    protected String nbCylind;
    @XmlElement(name = "NB_PL_Ass", required = true)
    protected String nbplAss;
    @XmlElement(name = "Mode_Inject", required = true)
    protected String modeInject;
    @XmlElement(name = "Modele_PRF", required = true)
    protected String modelePRF;
    @XmlElement(name = "Modele_Etude", required = true)
    protected String modeleEtude;
    @XmlElement(name = "Modele", required = true)
    protected String modele;
    @XmlElement(name = "Marque_Carros", required = true)
    protected String marqueCarros;
    @XmlElement(name = "Marque", required = true)
    protected String marque;
    @XmlElement(name = "Longueur", required = true)
    protected String longueur;
    @XmlElement(name = "Hauteur", required = true)
    protected String hauteur;
    @XmlElement(name = "Immat_SIV", required = true)
    protected String immatSIV;
    @XmlElement(name = "Largeur", required = true)
    protected String largeur;
    @XmlElement(name = "Genre_V_CG", required = true)
    protected String genreVCG;
    @XmlElement(name = "Energie", required = true)
    protected String energie;
    @XmlElement(name = "Genre_V", required = true)
    protected String genreV;
    @XmlElement(name = "Empat", required = true)
    protected String empat;
    @XmlElement(name = "Depollution", required = true)
    protected String depollution;
    @XmlElement(name = "Date_Dcg_Mois", required = true)
    protected String dateDcgMois;
    @XmlElement(name = "Date_Dcg_Jour", required = true)
    protected String dateDcgJour;
    @XmlElement(name = "Date_DCG_Annee", required = true)
    protected String dateDCGAnnee;
    @XmlElement(name = "Date_1er_Cir_Mois", required = true)
    protected String date1ErCirMois;
    @XmlElement(name = "Date_1er_Cir_jour", required = true)
    protected String date1ErCirJour;
    @XmlElement(name = "Date_1Er_CIR_Annee", required = true)
    protected String date1ErCIRAnnee;
    @XmlElement(name = "Cylindree", required = true)
    protected String cylindree;
    @XmlElement(name = "Couleur_Vehic", required = true)
    protected String couleurVehic;
    @XmlElement(name = "Codif_Vin_PRF", required = true)
    protected String codifVinPRF;
    @XmlElement(name = "Carrosserie_CG", required = true)
    protected String carrosserieCG;
    @XmlElement(name = "CO2", required = true)
    protected String co2;
    @XmlElement(name = "Carrosserie", required = true)
    protected String carrosserie;

    /**
     * Obtient la valeur de la propriété version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Définit la valeur de la propriété version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété typeVinCG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeVinCG() {
        return typeVinCG;
    }

    /**
     * Définit la valeur de la propriété typeVinCG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeVinCG(String value) {
        this.typeVinCG = value;
    }

    /**
     * Obtient la valeur de la propriété turboCompr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTurboCompr() {
        return turboCompr;
    }

    /**
     * Définit la valeur de la propriété turboCompr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTurboCompr(String value) {
        this.turboCompr = value;
    }

    /**
     * Obtient la valeur de la propriété tpBoiteVit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTPBoiteVit() {
        return tpBoiteVit;
    }

    /**
     * Définit la valeur de la propriété tpBoiteVit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTPBoiteVit(String value) {
        this.tpBoiteVit = value;
    }

    /**
     * Obtient la valeur de la propriété puisFisc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuisFisc() {
        return puisFisc;
    }

    /**
     * Définit la valeur de la propriété puisFisc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuisFisc(String value) {
        this.puisFisc = value;
    }

    /**
     * Obtient la valeur de la propriété puisCh.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuisCh() {
        return puisCh;
    }

    /**
     * Définit la valeur de la propriété puisCh.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuisCh(String value) {
        this.puisCh = value;
    }

    /**
     * Obtient la valeur de la propriété ptrprf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPTRPRF() {
        return ptrprf;
    }

    /**
     * Définit la valeur de la propriété ptrprf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPTRPRF(String value) {
        this.ptrprf = value;
    }

    /**
     * Obtient la valeur de la propriété ptr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPTR() {
        return ptr;
    }

    /**
     * Définit la valeur de la propriété ptr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPTR(String value) {
        this.ptr = value;
    }

    /**
     * Obtient la valeur de la propriété propulsion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropulsion() {
        return propulsion;
    }

    /**
     * Définit la valeur de la propriété propulsion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropulsion(String value) {
        this.propulsion = value;
    }

    /**
     * Obtient la valeur de la propriété poidsVide.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoidsVide() {
        return poidsVide;
    }

    /**
     * Définit la valeur de la propriété poidsVide.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoidsVide(String value) {
        this.poidsVide = value;
    }

    /**
     * Obtient la valeur de la propriété nSerie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNSerie() {
        return nSerie;
    }

    /**
     * Définit la valeur de la propriété nSerie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNSerie(String value) {
        this.nSerie = value;
    }

    /**
     * Obtient la valeur de la propriété nbVolume.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNBVolume() {
        return nbVolume;
    }

    /**
     * Définit la valeur de la propriété nbVolume.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNBVolume(String value) {
        this.nbVolume = value;
    }

    /**
     * Obtient la valeur de la propriété nbVitesse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNBVitesse() {
        return nbVitesse;
    }

    /**
     * Définit la valeur de la propriété nbVitesse.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNBVitesse(String value) {
        this.nbVitesse = value;
    }

    /**
     * Obtient la valeur de la propriété nbSoupapes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNBSoupapes() {
        return nbSoupapes;
    }

    /**
     * Définit la valeur de la propriété nbSoupapes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNBSoupapes(String value) {
        this.nbSoupapes = value;
    }

    /**
     * Obtient la valeur de la propriété nbPortes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNBPortes() {
        return nbPortes;
    }

    /**
     * Définit la valeur de la propriété nbPortes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNBPortes(String value) {
        this.nbPortes = value;
    }

    /**
     * Obtient la valeur de la propriété nbCylind.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNBCylind() {
        return nbCylind;
    }

    /**
     * Définit la valeur de la propriété nbCylind.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNBCylind(String value) {
        this.nbCylind = value;
    }

    /**
     * Obtient la valeur de la propriété nbplAss.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNBPLAss() {
        return nbplAss;
    }

    /**
     * Définit la valeur de la propriété nbplAss.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNBPLAss(String value) {
        this.nbplAss = value;
    }

    /**
     * Obtient la valeur de la propriété modeInject.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModeInject() {
        return modeInject;
    }

    /**
     * Définit la valeur de la propriété modeInject.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModeInject(String value) {
        this.modeInject = value;
    }

    /**
     * Obtient la valeur de la propriété modelePRF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelePRF() {
        return modelePRF;
    }

    /**
     * Définit la valeur de la propriété modelePRF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelePRF(String value) {
        this.modelePRF = value;
    }

    /**
     * Obtient la valeur de la propriété modeleEtude.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModeleEtude() {
        return modeleEtude;
    }

    /**
     * Définit la valeur de la propriété modeleEtude.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModeleEtude(String value) {
        this.modeleEtude = value;
    }

    /**
     * Obtient la valeur de la propriété modele.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModele() {
        return modele;
    }

    /**
     * Définit la valeur de la propriété modele.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModele(String value) {
        this.modele = value;
    }

    /**
     * Obtient la valeur de la propriété marqueCarros.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarqueCarros() {
        return marqueCarros;
    }

    /**
     * Définit la valeur de la propriété marqueCarros.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarqueCarros(String value) {
        this.marqueCarros = value;
    }

    /**
     * Obtient la valeur de la propriété marque.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Définit la valeur de la propriété marque.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarque(String value) {
        this.marque = value;
    }

    /**
     * Obtient la valeur de la propriété longueur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongueur() {
        return longueur;
    }

    /**
     * Définit la valeur de la propriété longueur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongueur(String value) {
        this.longueur = value;
    }

    /**
     * Obtient la valeur de la propriété hauteur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHauteur() {
        return hauteur;
    }

    /**
     * Définit la valeur de la propriété hauteur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHauteur(String value) {
        this.hauteur = value;
    }

    /**
     * Obtient la valeur de la propriété immatSIV.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImmatSIV() {
        return immatSIV;
    }

    /**
     * Définit la valeur de la propriété immatSIV.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImmatSIV(String value) {
        this.immatSIV = value;
    }

    /**
     * Obtient la valeur de la propriété largeur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLargeur() {
        return largeur;
    }

    /**
     * Définit la valeur de la propriété largeur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLargeur(String value) {
        this.largeur = value;
    }

    /**
     * Obtient la valeur de la propriété genreVCG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenreVCG() {
        return genreVCG;
    }

    /**
     * Définit la valeur de la propriété genreVCG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenreVCG(String value) {
        this.genreVCG = value;
    }

    /**
     * Obtient la valeur de la propriété energie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnergie() {
        return energie;
    }

    /**
     * Définit la valeur de la propriété energie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnergie(String value) {
        this.energie = value;
    }

    /**
     * Obtient la valeur de la propriété genreV.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenreV() {
        return genreV;
    }

    /**
     * Définit la valeur de la propriété genreV.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenreV(String value) {
        this.genreV = value;
    }

    /**
     * Obtient la valeur de la propriété empat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpat() {
        return empat;
    }

    /**
     * Définit la valeur de la propriété empat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpat(String value) {
        this.empat = value;
    }

    /**
     * Obtient la valeur de la propriété depollution.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepollution() {
        return depollution;
    }

    /**
     * Définit la valeur de la propriété depollution.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepollution(String value) {
        this.depollution = value;
    }

    /**
     * Obtient la valeur de la propriété dateDcgMois.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateDcgMois() {
        return dateDcgMois;
    }

    /**
     * Définit la valeur de la propriété dateDcgMois.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateDcgMois(String value) {
        this.dateDcgMois = value;
    }

    /**
     * Obtient la valeur de la propriété dateDcgJour.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateDcgJour() {
        return dateDcgJour;
    }

    /**
     * Définit la valeur de la propriété dateDcgJour.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateDcgJour(String value) {
        this.dateDcgJour = value;
    }

    /**
     * Obtient la valeur de la propriété dateDCGAnnee.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateDCGAnnee() {
        return dateDCGAnnee;
    }

    /**
     * Définit la valeur de la propriété dateDCGAnnee.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateDCGAnnee(String value) {
        this.dateDCGAnnee = value;
    }

    /**
     * Obtient la valeur de la propriété date1ErCirMois.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate1ErCirMois() {
        return date1ErCirMois;
    }

    /**
     * Définit la valeur de la propriété date1ErCirMois.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate1ErCirMois(String value) {
        this.date1ErCirMois = value;
    }

    /**
     * Obtient la valeur de la propriété date1ErCirJour.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate1ErCirJour() {
        return date1ErCirJour;
    }

    /**
     * Définit la valeur de la propriété date1ErCirJour.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate1ErCirJour(String value) {
        this.date1ErCirJour = value;
    }

    /**
     * Obtient la valeur de la propriété date1ErCIRAnnee.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate1ErCIRAnnee() {
        return date1ErCIRAnnee;
    }

    /**
     * Définit la valeur de la propriété date1ErCIRAnnee.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate1ErCIRAnnee(String value) {
        this.date1ErCIRAnnee = value;
    }

    /**
     * Obtient la valeur de la propriété cylindree.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCylindree() {
        return cylindree;
    }

    /**
     * Définit la valeur de la propriété cylindree.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCylindree(String value) {
        this.cylindree = value;
    }

    /**
     * Obtient la valeur de la propriété couleurVehic.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCouleurVehic() {
        return couleurVehic;
    }

    /**
     * Définit la valeur de la propriété couleurVehic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCouleurVehic(String value) {
        this.couleurVehic = value;
    }

    /**
     * Obtient la valeur de la propriété codifVinPRF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodifVinPRF() {
        return codifVinPRF;
    }

    /**
     * Définit la valeur de la propriété codifVinPRF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodifVinPRF(String value) {
        this.codifVinPRF = value;
    }

    /**
     * Obtient la valeur de la propriété carrosserieCG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrosserieCG() {
        return carrosserieCG;
    }

    /**
     * Définit la valeur de la propriété carrosserieCG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrosserieCG(String value) {
        this.carrosserieCG = value;
    }

    /**
     * Obtient la valeur de la propriété co2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCO2() {
        return co2;
    }

    /**
     * Définit la valeur de la propriété co2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCO2(String value) {
        this.co2 = value;
    }

    /**
     * Obtient la valeur de la propriété carrosserie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrosserie() {
        return carrosserie;
    }

    /**
     * Définit la valeur de la propriété carrosserie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrosserie(String value) {
        this.carrosserie = value;
    }

}
