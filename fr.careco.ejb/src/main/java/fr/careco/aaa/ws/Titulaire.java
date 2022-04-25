
package fr.careco.aaa.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Titulaire complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Titulaire">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cCreditBail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CSP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nSirenLeasing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Titulaire", namespace = "http://aaa.asso.fr/sivin/xsd", propOrder = {
    "cCreditBail",
    "csp",
    "nSirenLeasing",
    "typeLocation"
})
public class Titulaire {

    protected String cCreditBail;
    @XmlElement(name = "CSP")
    protected String csp;
    protected String nSirenLeasing;
    protected String typeLocation;

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
     * Gets the value of the csp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSP() {
        return csp;
    }

    /**
     * Sets the value of the csp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCSP(String value) {
        this.csp = value;
    }

    /**
     * Gets the value of the nSirenLeasing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNSirenLeasing() {
        return nSirenLeasing;
    }

    /**
     * Sets the value of the nSirenLeasing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNSirenLeasing(String value) {
        this.nSirenLeasing = value;
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

}
