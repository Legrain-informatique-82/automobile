
package fr.asso.aaa.sivin.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Vehicule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Vehicule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="anNaissMod" type="{http://aaa.asso.fr/sivin/xsd}Annee" minOccurs="0"/>
 *         &lt;element name="btq" type="{http://aaa.asso.fr/sivin/xsd}TypeBtq" minOccurs="0"/>
 *         &lt;element name="cAliLoca" type="{http://aaa.asso.fr/sivin/schemas/types_communs}CodeAlias" minOccurs="0"/>
 *         &lt;element name="cApe" type="{http://aaa.asso.fr/sivin/xsd}TypeApe" minOccurs="0"/>
 *         &lt;element name="cCreditBail" type="{http://aaa.asso.fr/sivin/xsd}TypeCreditBail" minOccurs="0"/>
 *         &lt;element name="cPostal" type="{http://aaa.asso.fr/sivin/xsd}TypeCodePostal" minOccurs="0"/>
 *         &lt;element name="cPropriet" type="{http://aaa.asso.fr/sivin/xsd}TypeCodePropriet" minOccurs="0"/>
 *         &lt;element name="cant" type="{http://aaa.asso.fr/sivin/xsd}TypeCodeCanton" minOccurs="0"/>
 *         &lt;element name="canton" type="{http://aaa.asso.fr/sivin/xsd}TypeCanton" minOccurs="0"/>
 *         &lt;element name="carrosserie" type="{http://aaa.asso.fr/sivin/xsd}TypeCarrosserie" minOccurs="0"/>
 *         &lt;element name="carrosserieCG" type="{http://aaa.asso.fr/sivin/schemas/types_communs}Carrosserie" minOccurs="0"/>
 *         &lt;element name="catClient" type="{http://aaa.asso.fr/sivin/xsd}TypeCategorie" minOccurs="0"/>
 *         &lt;element name="catClientVO" type="{http://aaa.asso.fr/sivin/xsd}TypeCategorie" minOccurs="0"/>
 *         &lt;element name="catVendeur" type="{http://aaa.asso.fr/sivin/xsd}TypeCategorie" minOccurs="0"/>
 *         &lt;element name="certifVin" type="{http://aaa.asso.fr/sivin/xsd}TypeCertifVin" minOccurs="0"/>
 *         &lt;element name="clEnvironPrf" type="{http://aaa.asso.fr/sivin/xsd}TypeClEnviron" minOccurs="0"/>
 *         &lt;element name="co2" type="{http://aaa.asso.fr/sivin/schemas/types_communs}Co2" minOccurs="0"/>
 *         &lt;element name="codeMoteur" type="{http://aaa.asso.fr/sivin/xsd}TypeCodeMoteur" minOccurs="0"/>
 *         &lt;element name="codifVin" type="{http://aaa.asso.fr/sivin/schemas/types_communs}NumeroVin"/>
 *         &lt;element name="communeInsee" type="{http://aaa.asso.fr/sivin/xsd}TypeCommune" minOccurs="0"/>
 *         &lt;element name="complAdressePrf" type="{http://aaa.asso.fr/sivin/xsd}TypeComplAdr" minOccurs="0"/>
 *         &lt;element name="consExurb" type="{http://aaa.asso.fr/sivin/xsd}TypeCons" minOccurs="0"/>
 *         &lt;element name="consMixte" type="{http://aaa.asso.fr/sivin/xsd}TypeCons" minOccurs="0"/>
 *         &lt;element name="consUrb" type="{http://aaa.asso.fr/sivin/xsd}TypeCons" minOccurs="0"/>
 *         &lt;element name="couleurVehic" type="{http://aaa.asso.fr/sivin/xsd}TypeCouleur" minOccurs="0"/>
 *         &lt;element name="cstrVinCG" type="{http://aaa.asso.fr/sivin/xsd}TypeStrVinCG" minOccurs="0"/>
 *         &lt;element name="cylindree" type="{http://aaa.asso.fr/sivin/xsd}TypeCylindree" minOccurs="0"/>
 *         &lt;element name="date1erCir" type="{http://aaa.asso.fr/sivin/xsd}Date" minOccurs="0"/>
 *         &lt;element name="dateDCG" type="{http://aaa.asso.fr/sivin/xsd}Date" minOccurs="0"/>
 *         &lt;element name="dateDCGAchat" type="{http://aaa.asso.fr/sivin/xsd}Date" minOccurs="0"/>
 *         &lt;element name="dateDCGPr" type="{http://aaa.asso.fr/sivin/xsd}Date" minOccurs="0"/>
 *         &lt;element name="dateDCGPrAchat" type="{http://aaa.asso.fr/sivin/xsd}Date" minOccurs="0"/>
 *         &lt;element name="dateDebImm" type="{http://aaa.asso.fr/sivin/xsd}Date" minOccurs="0"/>
 *         &lt;element name="dateFinImm" type="{http://aaa.asso.fr/sivin/xsd}Date" minOccurs="0"/>
 *         &lt;element name="depart" type="{http://aaa.asso.fr/sivin/schemas/types_communs}DepartementHabitation" minOccurs="0"/>
 *         &lt;element name="departement" type="{http://aaa.asso.fr/sivin/xsd}TypeDepartement" minOccurs="0"/>
 *         &lt;element name="depollution" type="{http://aaa.asso.fr/sivin/xsd}TypeDepollution" minOccurs="0"/>
 *         &lt;element name="empat" type="{http://aaa.asso.fr/sivin/xsd}TypeEmpat" minOccurs="0"/>
 *         &lt;element name="empreinteSol" type="{http://aaa.asso.fr/sivin/xsd}TypeEmpreinteSol" minOccurs="0"/>
 *         &lt;element name="energie" type="{http://aaa.asso.fr/sivin/xsd}TypeEnergie" minOccurs="0"/>
 *         &lt;element name="gammeMarche" type="{http://aaa.asso.fr/sivin/xsd}TypeGammeMarche" minOccurs="0"/>
 *         &lt;element name="genreV" type="{http://aaa.asso.fr/sivin/schemas/types_communs}Genre" minOccurs="0"/>
 *         &lt;element name="genreVCG" type="{http://aaa.asso.fr/sivin/xsd}TypeGenreVCG" minOccurs="0"/>
 *         &lt;element name="hauteur" type="{http://aaa.asso.fr/sivin/xsd}TypeHauteur" minOccurs="0"/>
 *         &lt;element name="immatSiv" type="{http://aaa.asso.fr/sivin/schemas/types_communs}NumeroImmatriculation"/>
 *         &lt;element name="immatSivPr" type="{http://aaa.asso.fr/sivin/xsd}NumeroImmatriculation" minOccurs="0"/>
 *         &lt;element name="largeur" type="{http://aaa.asso.fr/sivin/schemas/types_communs}Largeur" minOccurs="0"/>
 *         &lt;element name="latitude" type="{http://aaa.asso.fr/sivin/xsd}TypeLatitude" minOccurs="0"/>
 *         &lt;element name="longitude" type="{http://aaa.asso.fr/sivin/xsd}TypeLongitude" minOccurs="0"/>
 *         &lt;element name="longueur" type="{http://aaa.asso.fr/sivin/xsd}TypeLongueur" minOccurs="0"/>
 *         &lt;element name="marque" type="{http://aaa.asso.fr/sivin/schemas/types_communs}Marque" minOccurs="0"/>
 *         &lt;element name="marqueCarros" type="{http://aaa.asso.fr/sivin/xsd}TypeMarqueCarross" minOccurs="0"/>
 *         &lt;element name="modeInject" type="{http://aaa.asso.fr/sivin/xsd}TypeModeInject" minOccurs="0"/>
 *         &lt;element name="modeRefroid" type="{http://aaa.asso.fr/sivin/xsd}TypeModeRefroid" minOccurs="0"/>
 *         &lt;element name="modele" type="{http://aaa.asso.fr/sivin/xsd}TypeModele" minOccurs="0"/>
 *         &lt;element name="modeleEtude" type="{http://aaa.asso.fr/sivin/xsd}TypeModele" minOccurs="0"/>
 *         &lt;element name="modelePrf" type="{http://aaa.asso.fr/sivin/xsd}TypeModelePrf" minOccurs="0"/>
 *         &lt;element name="mouvmt" type="{http://aaa.asso.fr/sivin/xsd}TypeMouvmt" minOccurs="0"/>
 *         &lt;element name="mouvmtPrf" type="{http://aaa.asso.fr/sivin/xsd}TypeMouvmt" minOccurs="0"/>
 *         &lt;element name="nAdrRue" type="{http://aaa.asso.fr/sivin/xsd}TypeNumeroVoie" minOccurs="0"/>
 *         &lt;element name="natVoie" type="{http://aaa.asso.fr/sivin/xsd}TypeNatVoie" minOccurs="0"/>
 *         &lt;element name="NomVoie" type="{http://aaa.asso.fr/sivin/xsd}TypeNomVoie" minOccurs="0"/>
 *         &lt;element name="nSerie" type="{http://aaa.asso.fr/sivin/xsd}TypeNSerie" minOccurs="0"/>
 *         &lt;element name="nSiren" type="{http://aaa.asso.fr/sivin/schemas/types_communs}NumeroSiren"/>
 *         &lt;element name="nbCylind" type="{http://aaa.asso.fr/sivin/xsd}TypeNbCylind" minOccurs="0"/>
 *         &lt;element name="nbDecibels" type="{http://aaa.asso.fr/sivin/xsd}TypeNbDecibels" minOccurs="0"/>
 *         &lt;element name="nbMain" type="{http://aaa.asso.fr/sivin/xsd}TypeNbMain" minOccurs="0"/>
 *         &lt;element name="nbPlAss" type="{http://aaa.asso.fr/sivin/schemas/types_communs}PlacesAssises" minOccurs="0"/>
 *         &lt;element name="nbPortes" type="{http://aaa.asso.fr/sivin/xsd}TypeNbPortes" minOccurs="0"/>
 *         &lt;element name="nbPropriet" type="{http://aaa.asso.fr/sivin/schemas/types_communs}NombreCoproprietaires" minOccurs="0"/>
 *         &lt;element name="nbSoupape" type="{http://aaa.asso.fr/sivin/xsd}TypeNbSoupape" minOccurs="0"/>
 *         &lt;element name="nbVitesse" type="{http://aaa.asso.fr/sivin/xsd}TypeNbVitesse" minOccurs="0"/>
 *         &lt;element name="nbVolume" type="{http://aaa.asso.fr/sivin/xsd}TypeNbVolume" minOccurs="0"/>
 *         &lt;element name="nuanceCouleur" type="{http://aaa.asso.fr/sivin/xsd}TypeNuanceCouleur" minOccurs="0"/>
 *         &lt;element name="patronyme" type="{http://aaa.asso.fr/sivin/xsd}TypePatronyme" minOccurs="0"/>
 *         &lt;element name="poidsVide" type="{http://aaa.asso.fr/sivin/xsd}TypePoidsVide" minOccurs="0"/>
 *         &lt;element name="prixVehic" type="{http://aaa.asso.fr/sivin/xsd}TypePrixVehic" minOccurs="0"/>
 *         &lt;element name="propulsion" type="{http://aaa.asso.fr/sivin/xsd}TypePropulsion" minOccurs="0"/>
 *         &lt;element name="ptr" type="{http://aaa.asso.fr/sivin/schemas/types_communs}MasseVehiculeEnService" minOccurs="0"/>
 *         &lt;element name="ptrPrf" type="{http://aaa.asso.fr/sivin/xsd}TypePtrPrf" minOccurs="0"/>
 *         &lt;element name="puisCh" type="{http://aaa.asso.fr/sivin/xsd}Puissance" minOccurs="0"/>
 *         &lt;element name="puisFisc" type="{http://aaa.asso.fr/sivin/xsd}Puissance" minOccurs="0"/>
 *         &lt;element name="puisKw" type="{http://aaa.asso.fr/sivin/xsd}Puissance" minOccurs="0"/>
 *         &lt;element name="rapPuisPoids" type="{http://aaa.asso.fr/sivin/xsd}TypeRapPuisPoids" minOccurs="0"/>
 *         &lt;element name="regionAdm" type="{http://aaa.asso.fr/sivin/xsd}TypeRegionAdm" minOccurs="0"/>
 *         &lt;element name="regionAdmin" type="{http://aaa.asso.fr/sivin/xsd}TypeRegionAdmin" minOccurs="0"/>
 *         &lt;element name="regmDecibels" type="{http://aaa.asso.fr/sivin/schemas/types_communs}RegimeMoteur" minOccurs="0"/>
 *         &lt;element name="rueHaxavia" type="{http://aaa.asso.fr/sivin/xsd}TypeRueHexavia" minOccurs="0"/>
 *         &lt;element name="sexe" type="{http://aaa.asso.fr/sivin/xsd}TypeSexe" minOccurs="0"/>
 *         &lt;element name="statutImmat" type="{http://aaa.asso.fr/sivin/xsd}TypeStatutImmat" minOccurs="0"/>
 *         &lt;element name="temoinCession" type="{http://aaa.asso.fr/sivin/xsd}TypeTemoinCession" minOccurs="0"/>
 *         &lt;element name="tpBoiteVit" type="{http://aaa.asso.fr/sivin/xsd}TypeTpBoiteVit" minOccurs="0"/>
 *         &lt;element name="transmiss" type="{http://aaa.asso.fr/sivin/xsd}TypeTransmiss" minOccurs="0"/>
 *         &lt;element name="turboCompr" type="{http://aaa.asso.fr/sivin/xsd}TypeTurboCompr" minOccurs="0"/>
 *         &lt;element name="type" type="{http://aaa.asso.fr/sivin/xsd}TypeType" minOccurs="0"/>
 *         &lt;element name="typeLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typePrf" type="{http://aaa.asso.fr/sivin/xsd}TypeType" minOccurs="0"/>
 *         &lt;element name="typeVarVersPrf" type="{http://aaa.asso.fr/sivin/xsd}TypeTypeVarVers" minOccurs="0"/>
 *         &lt;element name="typeVin" type="{http://aaa.asso.fr/sivin/xsd}TypeVin" minOccurs="0"/>
 *         &lt;element name="typeVinCG" type="{http://aaa.asso.fr/sivin/xsd}TypeVin" minOccurs="0"/>
 *         &lt;element name="versConso" type="{http://aaa.asso.fr/sivin/xsd}TypeVersConso" minOccurs="0"/>
 *         &lt;element name="version" type="{http://aaa.asso.fr/sivin/xsd}TypeVersion" minOccurs="0"/>
 *         &lt;element name="voieAr" type="{http://aaa.asso.fr/sivin/xsd}TypeVoie" minOccurs="0"/>
 *         &lt;element name="voieAv" type="{http://aaa.asso.fr/sivin/xsd}TypeVoie" minOccurs="0"/>
 *         &lt;element name="pneus" type="{http://aaa.asso.fr/sivin/xsd}Pneu" minOccurs="0"/>
 *         &lt;element name="k_type" type="{http://aaa.asso.fr/sivin/xsd}TypeKType" minOccurs="0"/>
 *         &lt;element name="locataireBrut" type="{http://aaa.asso.fr/sivin/xsd}Locataire" minOccurs="0"/>
 *         &lt;element name="ProprietaireBrut" type="{http://aaa.asso.fr/sivin/xsd}Proprietaire" minOccurs="0"/>
 *         &lt;element name="loueurBrut" type="{http://aaa.asso.fr/sivin/xsd}Loueur" minOccurs="0"/>
 *         &lt;element name="acquereurBrut" type="{http://aaa.asso.fr/sivin/xsd}Acquereur" minOccurs="0"/>
 *         &lt;element name="MentionUsages" type="{http://aaa.asso.fr/sivin/xsd}MentionUsages" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vehicule", propOrder = {
    "anNaissMod",
    "btq",
    "cAliLoca",
    "cApe",
    "cCreditBail",
    "cPostal",
    "cPropriet",
    "cant",
    "canton",
    "carrosserie",
    "carrosserieCG",
    "catClient",
    "catClientVO",
    "catVendeur",
    "certifVin",
    "clEnvironPrf",
    "co2",
    "codeMoteur",
    "codifVin",
    "communeInsee",
    "complAdressePrf",
    "consExurb",
    "consMixte",
    "consUrb",
    "couleurVehic",
    "cstrVinCG",
    "cylindree",
    "date1ErCir",
    "dateDCG",
    "dateDCGAchat",
    "dateDCGPr",
    "dateDCGPrAchat",
    "dateDebImm",
    "dateFinImm",
    "depart",
    "departement",
    "depollution",
    "empat",
    "empreinteSol",
    "energie",
    "gammeMarche",
    "genreV",
    "genreVCG",
    "hauteur",
    "immatSiv",
    "immatSivPr",
    "largeur",
    "latitude",
    "longitude",
    "longueur",
    "marque",
    "marqueCarros",
    "modeInject",
    "modeRefroid",
    "modele",
    "modeleEtude",
    "modelePrf",
    "mouvmt",
    "mouvmtPrf",
    "nAdrRue",
    "natVoie",
    "nomVoie",
    "nSerie",
    "nSiren",
    "nbCylind",
    "nbDecibels",
    "nbMain",
    "nbPlAss",
    "nbPortes",
    "nbPropriet",
    "nbSoupape",
    "nbVitesse",
    "nbVolume",
    "nuanceCouleur",
    "patronyme",
    "poidsVide",
    "prixVehic",
    "propulsion",
    "ptr",
    "ptrPrf",
    "puisCh",
    "puisFisc",
    "puisKw",
    "rapPuisPoids",
    "regionAdm",
    "regionAdmin",
    "regmDecibels",
    "rueHaxavia",
    "sexe",
    "statutImmat",
    "temoinCession",
    "tpBoiteVit",
    "transmiss",
    "turboCompr",
    "type",
    "typeLocation",
    "typePrf",
    "typeVarVersPrf",
    "typeVin",
    "typeVinCG",
    "versConso",
    "version",
    "voieAr",
    "voieAv",
    "pneus",
    "kType",
    "locataireBrut",
    "proprietaireBrut",
    "loueurBrut",
    "acquereurBrut",
    "mentionUsages"
})
public class Vehicule {

    protected XMLGregorianCalendar anNaissMod;
    protected String btq;
    protected String cAliLoca;
    protected String cApe;
    protected String cCreditBail;
    protected String cPostal;
    protected String cPropriet;
    protected String cant;
    protected String canton;
    protected String carrosserie;
    protected String carrosserieCG;
    protected String catClient;
    protected String catClientVO;
    protected String catVendeur;
    protected String certifVin;
    protected String clEnvironPrf;
    protected String co2;
    protected String codeMoteur;
    @XmlElement(required = true)
    protected String codifVin;
    protected String communeInsee;
    protected String complAdressePrf;
    protected String consExurb;
    protected String consMixte;
    protected String consUrb;
    protected String couleurVehic;
    protected String cstrVinCG;
    protected String cylindree;
    @XmlElement(name = "date1erCir")
    protected Date date1ErCir;
    protected Date dateDCG;
    protected Date dateDCGAchat;
    protected Date dateDCGPr;
    protected Date dateDCGPrAchat;
    protected Date dateDebImm;
    protected Date dateFinImm;
    protected String depart;
    protected String departement;
    protected String depollution;
    protected String empat;
    protected String empreinteSol;
    protected String energie;
    protected String gammeMarche;
    protected String genreV;
    protected String genreVCG;
    protected String hauteur;
    @XmlElement(required = true)
    protected String immatSiv;
    protected String immatSivPr;
    protected String largeur;
    protected String latitude;
    protected String longitude;
    protected String longueur;
    protected String marque;
    protected String marqueCarros;
    protected String modeInject;
    protected String modeRefroid;
    protected String modele;
    protected String modeleEtude;
    protected String modelePrf;
    protected String mouvmt;
    protected String mouvmtPrf;
    protected String nAdrRue;
    protected String natVoie;
    @XmlElement(name = "NomVoie")
    protected String nomVoie;
    protected String nSerie;
    @XmlElement(required = true)
    protected String nSiren;
    protected String nbCylind;
    protected String nbDecibels;
    protected String nbMain;
    protected String nbPlAss;
    protected String nbPortes;
    protected String nbPropriet;
    protected String nbSoupape;
    protected String nbVitesse;
    protected String nbVolume;
    protected String nuanceCouleur;
    protected String patronyme;
    protected String poidsVide;
    protected String prixVehic;
    protected String propulsion;
    protected String ptr;
    protected String ptrPrf;
    protected String puisCh;
    protected String puisFisc;
    protected String puisKw;
    protected String rapPuisPoids;
    protected String regionAdm;
    protected String regionAdmin;
    protected String regmDecibels;
    protected String rueHaxavia;
    protected String sexe;
    protected String statutImmat;
    protected String temoinCession;
    protected String tpBoiteVit;
    protected String transmiss;
    protected String turboCompr;
    protected String type;
    protected String typeLocation;
    protected String typePrf;
    protected String typeVarVersPrf;
    protected String typeVin;
    protected String typeVinCG;
    protected String versConso;
    protected String version;
    protected String voieAr;
    protected String voieAv;
    protected Pneu pneus;
    @XmlElement(name = "k_type")
    protected String kType;
    protected Locataire locataireBrut;
    @XmlElement(name = "ProprietaireBrut")
    protected Proprietaire proprietaireBrut;
    protected Loueur loueurBrut;
    protected Acquereur acquereurBrut;
    @XmlElement(name = "MentionUsages")
    protected MentionUsages mentionUsages;

    /**
     * Gets the value of the anNaissMod property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAnNaissMod() {
        return anNaissMod;
    }

    /**
     * Sets the value of the anNaissMod property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAnNaissMod(XMLGregorianCalendar value) {
        this.anNaissMod = value;
    }

    /**
     * Gets the value of the btq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBtq() {
        return btq;
    }

    /**
     * Sets the value of the btq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBtq(String value) {
        this.btq = value;
    }

    /**
     * Gets the value of the cAliLoca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCAliLoca() {
        return cAliLoca;
    }

    /**
     * Sets the value of the cAliLoca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCAliLoca(String value) {
        this.cAliLoca = value;
    }

    /**
     * Gets the value of the cApe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCApe() {
        return cApe;
    }

    /**
     * Sets the value of the cApe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCApe(String value) {
        this.cApe = value;
    }

    /**
     * Gets the value of the cCreditBail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCreditBail() {
        return cCreditBail;
    }

    /**
     * Sets the value of the cCreditBail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCCreditBail(String value) {
        this.cCreditBail = value;
    }

    /**
     * Gets the value of the cPostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPostal() {
        return cPostal;
    }

    /**
     * Sets the value of the cPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPostal(String value) {
        this.cPostal = value;
    }

    /**
     * Gets the value of the cPropriet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPropriet() {
        return cPropriet;
    }

    /**
     * Sets the value of the cPropriet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPropriet(String value) {
        this.cPropriet = value;
    }

    /**
     * Gets the value of the cant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCant() {
        return cant;
    }

    /**
     * Sets the value of the cant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCant(String value) {
        this.cant = value;
    }

    /**
     * Gets the value of the canton property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanton() {
        return canton;
    }

    /**
     * Sets the value of the canton property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanton(String value) {
        this.canton = value;
    }

    /**
     * Gets the value of the carrosserie property.
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
     * Sets the value of the carrosserie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrosserie(String value) {
        this.carrosserie = value;
    }

    /**
     * Gets the value of the carrosserieCG property.
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
     * Sets the value of the carrosserieCG property.
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
     * Gets the value of the catClient property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatClient() {
        return catClient;
    }

    /**
     * Sets the value of the catClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatClient(String value) {
        this.catClient = value;
    }

    /**
     * Gets the value of the catClientVO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatClientVO() {
        return catClientVO;
    }

    /**
     * Sets the value of the catClientVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatClientVO(String value) {
        this.catClientVO = value;
    }

    /**
     * Gets the value of the catVendeur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatVendeur() {
        return catVendeur;
    }

    /**
     * Sets the value of the catVendeur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatVendeur(String value) {
        this.catVendeur = value;
    }

    /**
     * Gets the value of the certifVin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertifVin() {
        return certifVin;
    }

    /**
     * Sets the value of the certifVin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertifVin(String value) {
        this.certifVin = value;
    }

    /**
     * Gets the value of the clEnvironPrf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClEnvironPrf() {
        return clEnvironPrf;
    }

    /**
     * Sets the value of the clEnvironPrf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClEnvironPrf(String value) {
        this.clEnvironPrf = value;
    }

    /**
     * Gets the value of the co2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCo2() {
        return co2;
    }

    /**
     * Sets the value of the co2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCo2(String value) {
        this.co2 = value;
    }

    /**
     * Gets the value of the codeMoteur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeMoteur() {
        return codeMoteur;
    }

    /**
     * Sets the value of the codeMoteur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeMoteur(String value) {
        this.codeMoteur = value;
    }

    /**
     * Gets the value of the codifVin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodifVin() {
        return codifVin;
    }

    /**
     * Sets the value of the codifVin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodifVin(String value) {
        this.codifVin = value;
    }

    /**
     * Gets the value of the communeInsee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommuneInsee() {
        return communeInsee;
    }

    /**
     * Sets the value of the communeInsee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommuneInsee(String value) {
        this.communeInsee = value;
    }

    /**
     * Gets the value of the complAdressePrf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplAdressePrf() {
        return complAdressePrf;
    }

    /**
     * Sets the value of the complAdressePrf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplAdressePrf(String value) {
        this.complAdressePrf = value;
    }

    /**
     * Gets the value of the consExurb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsExurb() {
        return consExurb;
    }

    /**
     * Sets the value of the consExurb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsExurb(String value) {
        this.consExurb = value;
    }

    /**
     * Gets the value of the consMixte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsMixte() {
        return consMixte;
    }

    /**
     * Sets the value of the consMixte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsMixte(String value) {
        this.consMixte = value;
    }

    /**
     * Gets the value of the consUrb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsUrb() {
        return consUrb;
    }

    /**
     * Sets the value of the consUrb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsUrb(String value) {
        this.consUrb = value;
    }

    /**
     * Gets the value of the couleurVehic property.
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
     * Sets the value of the couleurVehic property.
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
     * Gets the value of the cstrVinCG property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCstrVinCG() {
        return cstrVinCG;
    }

    /**
     * Sets the value of the cstrVinCG property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCstrVinCG(String value) {
        this.cstrVinCG = value;
    }

    /**
     * Gets the value of the cylindree property.
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
     * Sets the value of the cylindree property.
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
     * Gets the value of the date1ErCir property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDate1ErCir() {
        return date1ErCir;
    }

    /**
     * Sets the value of the date1ErCir property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDate1ErCir(Date value) {
        this.date1ErCir = value;
    }

    /**
     * Gets the value of the dateDCG property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDateDCG() {
        return dateDCG;
    }

    /**
     * Sets the value of the dateDCG property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDateDCG(Date value) {
        this.dateDCG = value;
    }

    /**
     * Gets the value of the dateDCGAchat property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDateDCGAchat() {
        return dateDCGAchat;
    }

    /**
     * Sets the value of the dateDCGAchat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDateDCGAchat(Date value) {
        this.dateDCGAchat = value;
    }

    /**
     * Gets the value of the dateDCGPr property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDateDCGPr() {
        return dateDCGPr;
    }

    /**
     * Sets the value of the dateDCGPr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDateDCGPr(Date value) {
        this.dateDCGPr = value;
    }

    /**
     * Gets the value of the dateDCGPrAchat property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDateDCGPrAchat() {
        return dateDCGPrAchat;
    }

    /**
     * Sets the value of the dateDCGPrAchat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDateDCGPrAchat(Date value) {
        this.dateDCGPrAchat = value;
    }

    /**
     * Gets the value of the dateDebImm property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDateDebImm() {
        return dateDebImm;
    }

    /**
     * Sets the value of the dateDebImm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDateDebImm(Date value) {
        this.dateDebImm = value;
    }

    /**
     * Gets the value of the dateFinImm property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDateFinImm() {
        return dateFinImm;
    }

    /**
     * Sets the value of the dateFinImm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDateFinImm(Date value) {
        this.dateFinImm = value;
    }

    /**
     * Gets the value of the depart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepart() {
        return depart;
    }

    /**
     * Sets the value of the depart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepart(String value) {
        this.depart = value;
    }

    /**
     * Gets the value of the departement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartement() {
        return departement;
    }

    /**
     * Sets the value of the departement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartement(String value) {
        this.departement = value;
    }

    /**
     * Gets the value of the depollution property.
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
     * Sets the value of the depollution property.
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
     * Gets the value of the empat property.
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
     * Sets the value of the empat property.
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
     * Gets the value of the empreinteSol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpreinteSol() {
        return empreinteSol;
    }

    /**
     * Sets the value of the empreinteSol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpreinteSol(String value) {
        this.empreinteSol = value;
    }

    /**
     * Gets the value of the energie property.
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
     * Sets the value of the energie property.
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
     * Gets the value of the gammeMarche property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGammeMarche() {
        return gammeMarche;
    }

    /**
     * Sets the value of the gammeMarche property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGammeMarche(String value) {
        this.gammeMarche = value;
    }

    /**
     * Gets the value of the genreV property.
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
     * Sets the value of the genreV property.
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
     * Gets the value of the genreVCG property.
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
     * Sets the value of the genreVCG property.
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
     * Gets the value of the hauteur property.
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
     * Sets the value of the hauteur property.
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
     * Gets the value of the immatSiv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImmatSiv() {
        return immatSiv;
    }

    /**
     * Sets the value of the immatSiv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImmatSiv(String value) {
        this.immatSiv = value;
    }

    /**
     * Gets the value of the immatSivPr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImmatSivPr() {
        return immatSivPr;
    }

    /**
     * Sets the value of the immatSivPr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImmatSivPr(String value) {
        this.immatSivPr = value;
    }

    /**
     * Gets the value of the largeur property.
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
     * Sets the value of the largeur property.
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
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitude(String value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitude(String value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the longueur property.
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
     * Sets the value of the longueur property.
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
     * Gets the value of the marque property.
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
     * Sets the value of the marque property.
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
     * Gets the value of the marqueCarros property.
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
     * Sets the value of the marqueCarros property.
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
     * Gets the value of the modeInject property.
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
     * Sets the value of the modeInject property.
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
     * Gets the value of the modeRefroid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModeRefroid() {
        return modeRefroid;
    }

    /**
     * Sets the value of the modeRefroid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModeRefroid(String value) {
        this.modeRefroid = value;
    }

    /**
     * Gets the value of the modele property.
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
     * Sets the value of the modele property.
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
     * Gets the value of the modeleEtude property.
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
     * Sets the value of the modeleEtude property.
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
     * Gets the value of the modelePrf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelePrf() {
        return modelePrf;
    }

    /**
     * Sets the value of the modelePrf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelePrf(String value) {
        this.modelePrf = value;
    }

    /**
     * Gets the value of the mouvmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMouvmt() {
        return mouvmt;
    }

    /**
     * Sets the value of the mouvmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMouvmt(String value) {
        this.mouvmt = value;
    }

    /**
     * Gets the value of the mouvmtPrf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMouvmtPrf() {
        return mouvmtPrf;
    }

    /**
     * Sets the value of the mouvmtPrf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMouvmtPrf(String value) {
        this.mouvmtPrf = value;
    }

    /**
     * Gets the value of the nAdrRue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAdrRue() {
        return nAdrRue;
    }

    /**
     * Sets the value of the nAdrRue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAdrRue(String value) {
        this.nAdrRue = value;
    }

    /**
     * Gets the value of the natVoie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNatVoie() {
        return natVoie;
    }

    /**
     * Sets the value of the natVoie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNatVoie(String value) {
        this.natVoie = value;
    }

    /**
     * Gets the value of the nomVoie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomVoie() {
        return nomVoie;
    }

    /**
     * Sets the value of the nomVoie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomVoie(String value) {
        this.nomVoie = value;
    }

    /**
     * Gets the value of the nSerie property.
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
     * Sets the value of the nSerie property.
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
     * Gets the value of the nSiren property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNSiren() {
        return nSiren;
    }

    /**
     * Sets the value of the nSiren property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNSiren(String value) {
        this.nSiren = value;
    }

    /**
     * Gets the value of the nbCylind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbCylind() {
        return nbCylind;
    }

    /**
     * Sets the value of the nbCylind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbCylind(String value) {
        this.nbCylind = value;
    }

    /**
     * Gets the value of the nbDecibels property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbDecibels() {
        return nbDecibels;
    }

    /**
     * Sets the value of the nbDecibels property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbDecibels(String value) {
        this.nbDecibels = value;
    }

    /**
     * Gets the value of the nbMain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbMain() {
        return nbMain;
    }

    /**
     * Sets the value of the nbMain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbMain(String value) {
        this.nbMain = value;
    }

    /**
     * Gets the value of the nbPlAss property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbPlAss() {
        return nbPlAss;
    }

    /**
     * Sets the value of the nbPlAss property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbPlAss(String value) {
        this.nbPlAss = value;
    }

    /**
     * Gets the value of the nbPortes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbPortes() {
        return nbPortes;
    }

    /**
     * Sets the value of the nbPortes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbPortes(String value) {
        this.nbPortes = value;
    }

    /**
     * Gets the value of the nbPropriet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbPropriet() {
        return nbPropriet;
    }

    /**
     * Sets the value of the nbPropriet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbPropriet(String value) {
        this.nbPropriet = value;
    }

    /**
     * Gets the value of the nbSoupape property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbSoupape() {
        return nbSoupape;
    }

    /**
     * Sets the value of the nbSoupape property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbSoupape(String value) {
        this.nbSoupape = value;
    }

    /**
     * Gets the value of the nbVitesse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbVitesse() {
        return nbVitesse;
    }

    /**
     * Sets the value of the nbVitesse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbVitesse(String value) {
        this.nbVitesse = value;
    }

    /**
     * Gets the value of the nbVolume property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbVolume() {
        return nbVolume;
    }

    /**
     * Sets the value of the nbVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbVolume(String value) {
        this.nbVolume = value;
    }

    /**
     * Gets the value of the nuanceCouleur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuanceCouleur() {
        return nuanceCouleur;
    }

    /**
     * Sets the value of the nuanceCouleur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuanceCouleur(String value) {
        this.nuanceCouleur = value;
    }

    /**
     * Gets the value of the patronyme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatronyme() {
        return patronyme;
    }

    /**
     * Sets the value of the patronyme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatronyme(String value) {
        this.patronyme = value;
    }

    /**
     * Gets the value of the poidsVide property.
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
     * Sets the value of the poidsVide property.
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
     * Gets the value of the prixVehic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrixVehic() {
        return prixVehic;
    }

    /**
     * Sets the value of the prixVehic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrixVehic(String value) {
        this.prixVehic = value;
    }

    /**
     * Gets the value of the propulsion property.
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
     * Sets the value of the propulsion property.
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
     * Gets the value of the ptr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPtr() {
        return ptr;
    }

    /**
     * Sets the value of the ptr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPtr(String value) {
        this.ptr = value;
    }

    /**
     * Gets the value of the ptrPrf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPtrPrf() {
        return ptrPrf;
    }

    /**
     * Sets the value of the ptrPrf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPtrPrf(String value) {
        this.ptrPrf = value;
    }

    /**
     * Gets the value of the puisCh property.
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
     * Sets the value of the puisCh property.
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
     * Gets the value of the puisFisc property.
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
     * Sets the value of the puisFisc property.
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
     * Gets the value of the puisKw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuisKw() {
        return puisKw;
    }

    /**
     * Sets the value of the puisKw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuisKw(String value) {
        this.puisKw = value;
    }

    /**
     * Gets the value of the rapPuisPoids property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRapPuisPoids() {
        return rapPuisPoids;
    }

    /**
     * Sets the value of the rapPuisPoids property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRapPuisPoids(String value) {
        this.rapPuisPoids = value;
    }

    /**
     * Gets the value of the regionAdm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionAdm() {
        return regionAdm;
    }

    /**
     * Sets the value of the regionAdm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionAdm(String value) {
        this.regionAdm = value;
    }

    /**
     * Gets the value of the regionAdmin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionAdmin() {
        return regionAdmin;
    }

    /**
     * Sets the value of the regionAdmin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionAdmin(String value) {
        this.regionAdmin = value;
    }

    /**
     * Gets the value of the regmDecibels property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegmDecibels() {
        return regmDecibels;
    }

    /**
     * Sets the value of the regmDecibels property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegmDecibels(String value) {
        this.regmDecibels = value;
    }

    /**
     * Gets the value of the rueHaxavia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRueHaxavia() {
        return rueHaxavia;
    }

    /**
     * Sets the value of the rueHaxavia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRueHaxavia(String value) {
        this.rueHaxavia = value;
    }

    /**
     * Gets the value of the sexe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexe() {
        return sexe;
    }

    /**
     * Sets the value of the sexe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexe(String value) {
        this.sexe = value;
    }

    /**
     * Gets the value of the statutImmat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatutImmat() {
        return statutImmat;
    }

    /**
     * Sets the value of the statutImmat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatutImmat(String value) {
        this.statutImmat = value;
    }

    /**
     * Gets the value of the temoinCession property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemoinCession() {
        return temoinCession;
    }

    /**
     * Sets the value of the temoinCession property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemoinCession(String value) {
        this.temoinCession = value;
    }

    /**
     * Gets the value of the tpBoiteVit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpBoiteVit() {
        return tpBoiteVit;
    }

    /**
     * Sets the value of the tpBoiteVit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpBoiteVit(String value) {
        this.tpBoiteVit = value;
    }

    /**
     * Gets the value of the transmiss property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransmiss() {
        return transmiss;
    }

    /**
     * Sets the value of the transmiss property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransmiss(String value) {
        this.transmiss = value;
    }

    /**
     * Gets the value of the turboCompr property.
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
     * Sets the value of the turboCompr property.
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
     * Gets the value of the type property.
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
     * Sets the value of the type property.
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
     * Gets the value of the typeLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeLocation() {
        return typeLocation;
    }

    /**
     * Sets the value of the typeLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeLocation(String value) {
        this.typeLocation = value;
    }

    /**
     * Gets the value of the typePrf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypePrf() {
        return typePrf;
    }

    /**
     * Sets the value of the typePrf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypePrf(String value) {
        this.typePrf = value;
    }

    /**
     * Gets the value of the typeVarVersPrf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeVarVersPrf() {
        return typeVarVersPrf;
    }

    /**
     * Sets the value of the typeVarVersPrf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeVarVersPrf(String value) {
        this.typeVarVersPrf = value;
    }

    /**
     * Gets the value of the typeVin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeVin() {
        return typeVin;
    }

    /**
     * Sets the value of the typeVin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeVin(String value) {
        this.typeVin = value;
    }

    /**
     * Gets the value of the typeVinCG property.
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
     * Sets the value of the typeVinCG property.
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
     * Gets the value of the versConso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersConso() {
        return versConso;
    }

    /**
     * Sets the value of the versConso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersConso(String value) {
        this.versConso = value;
    }

    /**
     * Gets the value of the version property.
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
     * Sets the value of the version property.
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
     * Gets the value of the voieAr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoieAr() {
        return voieAr;
    }

    /**
     * Sets the value of the voieAr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoieAr(String value) {
        this.voieAr = value;
    }

    /**
     * Gets the value of the voieAv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoieAv() {
        return voieAv;
    }

    /**
     * Sets the value of the voieAv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoieAv(String value) {
        this.voieAv = value;
    }

    /**
     * Gets the value of the pneus property.
     * 
     * @return
     *     possible object is
     *     {@link Pneu }
     *     
     */
    public Pneu getPneus() {
        return pneus;
    }

    /**
     * Sets the value of the pneus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pneu }
     *     
     */
    public void setPneus(Pneu value) {
        this.pneus = value;
    }

    /**
     * Gets the value of the kType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKType() {
        return kType;
    }

    /**
     * Sets the value of the kType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKType(String value) {
        this.kType = value;
    }

    /**
     * Gets the value of the locataireBrut property.
     * 
     * @return
     *     possible object is
     *     {@link Locataire }
     *     
     */
    public Locataire getLocataireBrut() {
        return locataireBrut;
    }

    /**
     * Sets the value of the locataireBrut property.
     * 
     * @param value
     *     allowed object is
     *     {@link Locataire }
     *     
     */
    public void setLocataireBrut(Locataire value) {
        this.locataireBrut = value;
    }

    /**
     * Gets the value of the proprietaireBrut property.
     * 
     * @return
     *     possible object is
     *     {@link Proprietaire }
     *     
     */
    public Proprietaire getProprietaireBrut() {
        return proprietaireBrut;
    }

    /**
     * Sets the value of the proprietaireBrut property.
     * 
     * @param value
     *     allowed object is
     *     {@link Proprietaire }
     *     
     */
    public void setProprietaireBrut(Proprietaire value) {
        this.proprietaireBrut = value;
    }

    /**
     * Gets the value of the loueurBrut property.
     * 
     * @return
     *     possible object is
     *     {@link Loueur }
     *     
     */
    public Loueur getLoueurBrut() {
        return loueurBrut;
    }

    /**
     * Sets the value of the loueurBrut property.
     * 
     * @param value
     *     allowed object is
     *     {@link Loueur }
     *     
     */
    public void setLoueurBrut(Loueur value) {
        this.loueurBrut = value;
    }

    /**
     * Gets the value of the acquereurBrut property.
     * 
     * @return
     *     possible object is
     *     {@link Acquereur }
     *     
     */
    public Acquereur getAcquereurBrut() {
        return acquereurBrut;
    }

    /**
     * Sets the value of the acquereurBrut property.
     * 
     * @param value
     *     allowed object is
     *     {@link Acquereur }
     *     
     */
    public void setAcquereurBrut(Acquereur value) {
        this.acquereurBrut = value;
    }

    /**
     * Gets the value of the mentionUsages property.
     * 
     * @return
     *     possible object is
     *     {@link MentionUsages }
     *     
     */
    public MentionUsages getMentionUsages() {
        return mentionUsages;
    }

    /**
     * Sets the value of the mentionUsages property.
     * 
     * @param value
     *     allowed object is
     *     {@link MentionUsages }
     *     
     */
    public void setMentionUsages(MentionUsages value) {
        this.mentionUsages = value;
    }

}
