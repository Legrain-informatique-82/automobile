
package fr.careco.blueway.ws.b2b;

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
 *         &lt;element name="Var_AAA" type="{http://carecoprod.blueway.fr:8180/engine53/52}Var_AAA"/>
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
    "varAAA"
})
@XmlRootElement(name = "AAA00_WsB2BIN")
public class AAA00WsB2BIN {

    @XmlElement(name = "Var_AAA", required = true)
    protected VarAAA varAAA;

    /**
     * Obtient la valeur de la propriété varAAA.
     * 
     * @return
     *     possible object is
     *     {@link VarAAA }
     *     
     */
    public VarAAA getVarAAA() {
        return varAAA;
    }

    /**
     * Définit la valeur de la propriété varAAA.
     * 
     * @param value
     *     allowed object is
     *     {@link VarAAA }
     *     
     */
    public void setVarAAA(VarAAA value) {
        this.varAAA = value;
    }

}
