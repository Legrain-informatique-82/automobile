
package fr.asso.aaa.sivin.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Proprietaire complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Proprietaire">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomUsage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nSiren" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="etage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="immeuble" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nAdrRue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BTQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeVoie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomVoie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lieudit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codePostal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boitePostale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Proprietaire", propOrder = {
    "nom",
    "nomUsage",
    "prenom",
    "nSiren",
    "etage",
    "immeuble",
    "nAdrRue",
    "btq",
    "typeVoie",
    "nomVoie",
    "lieudit",
    "codePostal",
    "commune",
    "boitePostale"
})
public class Proprietaire {

    protected String nom;
    protected String nomUsage;
    protected String prenom;
    protected String nSiren;
    protected String etage;
    protected String immeuble;
    protected String nAdrRue;
    @XmlElement(name = "BTQ")
    protected String btq;
    protected String typeVoie;
    protected String nomVoie;
    protected String lieudit;
    protected String codePostal;
    protected String commune;
    protected String boitePostale;

    /**
     * Gets the value of the nom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the value of the nom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Gets the value of the nomUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomUsage() {
        return nomUsage;
    }

    /**
     * Sets the value of the nomUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomUsage(String value) {
        this.nomUsage = value;
    }

    /**
     * Gets the value of the prenom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Sets the value of the prenom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrenom(String value) {
        this.prenom = value;
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
     * Gets the value of the etage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtage() {
        return etage;
    }

    /**
     * Sets the value of the etage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtage(String value) {
        this.etage = value;
    }

    /**
     * Gets the value of the immeuble property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImmeuble() {
        return immeuble;
    }

    /**
     * Sets the value of the immeuble property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImmeuble(String value) {
        this.immeuble = value;
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
     * Gets the value of the btq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBTQ() {
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
    public void setBTQ(String value) {
        this.btq = value;
    }

    /**
     * Gets the value of the typeVoie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeVoie() {
        return typeVoie;
    }

    /**
     * Sets the value of the typeVoie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeVoie(String value) {
        this.typeVoie = value;
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
     * Gets the value of the lieudit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLieudit() {
        return lieudit;
    }

    /**
     * Sets the value of the lieudit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLieudit(String value) {
        this.lieudit = value;
    }

    /**
     * Gets the value of the codePostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Sets the value of the codePostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodePostal(String value) {
        this.codePostal = value;
    }

    /**
     * Gets the value of the commune property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommune() {
        return commune;
    }

    /**
     * Sets the value of the commune property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommune(String value) {
        this.commune = value;
    }

    /**
     * Gets the value of the boitePostale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoitePostale() {
        return boitePostale;
    }

    /**
     * Sets the value of the boitePostale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoitePostale(String value) {
        this.boitePostale = value;
    }

}
