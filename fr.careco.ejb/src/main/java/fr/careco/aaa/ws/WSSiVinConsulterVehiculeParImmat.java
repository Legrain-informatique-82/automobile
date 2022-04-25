
package fr.careco.aaa.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="immat" type="{http://aaa.asso.fr/sivin/schemas/types_communs}NumeroImmatriculation"/>
 *         &lt;element name="Nom" type="{http://aaa.asso.fr/sivin/schemas/types_communs}nNom" minOccurs="0"/>
 *         &lt;element name="DateDCG" type="{http://aaa.asso.fr/sivin/xsd}Date" minOccurs="0"/>
 *         &lt;element name="Date1erCir" type="{http://aaa.asso.fr/sivin/xsd}Date" minOccurs="0"/>
 *         &lt;element name="numeroSerie" type="{http://aaa.asso.fr/sivin/xsd}FinNumeroSerie" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "immat",
    "nom",
    "dateDCG",
    "date1ErCir",
    "numeroSerie"
})
@XmlRootElement(name = "WS_SiVin_Consulter_VehiculeParImmat")
public class WSSiVinConsulterVehiculeParImmat {

    @XmlElement(required = true, nillable = true)
    protected String immat;
    @XmlElement(name = "Nom")
    protected String nom;
    @XmlElement(name = "DateDCG")
    protected Date dateDCG;
    @XmlElement(name = "Date1erCir")
    protected Date date1ErCir;
    protected String numeroSerie;

    /**
     * Gets the value of the immat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImmat() {
        return immat;
    }

    /**
     * Sets the value of the immat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImmat(String value) {
        this.immat = value;
    }

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
     * Gets the value of the numeroSerie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * Sets the value of the numeroSerie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroSerie(String value) {
        this.numeroSerie = value;
    }

}
