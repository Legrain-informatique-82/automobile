
package fr.careco.aaa.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Date complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Date">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jour" type="{http://www.w3.org/2001/XMLSchema}gDay"/>
 *         &lt;element name="mois" type="{http://www.w3.org/2001/XMLSchema}gMonth"/>
 *         &lt;element name="annee" type="{http://www.w3.org/2001/XMLSchema}gYear"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Date", namespace = "http://aaa.asso.fr/sivin/xsd", propOrder = {
    "jour",
    "mois",
    "annee"
})
public class Date {

    @XmlElement(required = true)
    @XmlSchemaType(name = "gDay")
    protected XMLGregorianCalendar jour;
    @XmlElement(required = true)
    @XmlSchemaType(name = "gMonth")
    protected XMLGregorianCalendar mois;
    @XmlElement(required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar annee;

    /**
     * Gets the value of the jour property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getJour() {
        return jour;
    }

    /**
     * Sets the value of the jour property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setJour(XMLGregorianCalendar value) {
        this.jour = value;
    }

    /**
     * Gets the value of the mois property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMois() {
        return mois;
    }

    /**
     * Sets the value of the mois property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMois(XMLGregorianCalendar value) {
        this.mois = value;
    }

    /**
     * Gets the value of the annee property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAnnee() {
        return annee;
    }

    /**
     * Sets the value of the annee property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAnnee(XMLGregorianCalendar value) {
        this.annee = value;
    }

}
