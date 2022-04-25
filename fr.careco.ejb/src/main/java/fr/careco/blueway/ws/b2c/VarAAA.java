
package fr.careco.blueway.ws.b2c;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Var_AAA complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Var_AAA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TypeReq" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Mdp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Login" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Immat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Var_AAA", propOrder = {
    "typeReq",
    "nom",
    "mdp",
    "login",
    "immat"
})
public class VarAAA {

    @XmlElement(name = "TypeReq", required = true)
    protected String typeReq;
    @XmlElement(name = "Nom", required = true)
    protected String nom;
    @XmlElement(name = "Mdp", required = true)
    protected String mdp;
    @XmlElement(name = "Login", required = true)
    protected String login;
    @XmlElement(name = "Immat", required = true)
    protected String immat;

    /**
     * Obtient la valeur de la propriété typeReq.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeReq() {
        return typeReq;
    }

    /**
     * Définit la valeur de la propriété typeReq.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeReq(String value) {
        this.typeReq = value;
    }

    /**
     * Obtient la valeur de la propriété nom.
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
     * Définit la valeur de la propriété nom.
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
     * Obtient la valeur de la propriété mdp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Définit la valeur de la propriété mdp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdp(String value) {
        this.mdp = value;
    }

    /**
     * Obtient la valeur de la propriété login.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogin() {
        return login;
    }

    /**
     * Définit la valeur de la propriété login.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Obtient la valeur de la propriété immat.
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
     * Définit la valeur de la propriété immat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImmat(String value) {
        this.immat = value;
    }

}
