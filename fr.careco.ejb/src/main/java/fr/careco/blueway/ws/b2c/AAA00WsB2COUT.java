
package fr.careco.blueway.ws.b2c;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Var_AAA_retourWs" type="{http://carecoprod.blueway.fr:8180/engine53/52}Var_AAA_retourWs"/>
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
    "varAAARetourWs"
})
@XmlRootElement(name = "AAA00_WsB2COUT")
public class AAA00WsB2COUT {

    @XmlElement(name = "Var_AAA_retourWs", required = true)
    protected VarAAARetourWs varAAARetourWs;

    /**
     * Obtient la valeur de la propriété varAAARetourWs.
     * 
     * @return
     *     possible object is
     *     {@link VarAAARetourWs }
     *     
     */
    public VarAAARetourWs getVarAAARetourWs() {
        return varAAARetourWs;
    }

    /**
     * Définit la valeur de la propriété varAAARetourWs.
     * 
     * @param value
     *     allowed object is
     *     {@link VarAAARetourWs }
     *     
     */
    public void setVarAAARetourWs(VarAAARetourWs value) {
        this.varAAARetourWs = value;
    }

}
