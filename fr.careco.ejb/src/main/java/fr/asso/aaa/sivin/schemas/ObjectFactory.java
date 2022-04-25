
package fr.asso.aaa.sivin.schemas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.asso.aaa.sivin.schemas package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Vin_QNAME = new QName("http://aaa.asso.fr/sivin/schemas", "Vin");
    private final static QName _BaseDeDonneeInaccessibleMessage_QNAME = new QName("http://aaa.asso.fr/sivin/schemas", "message");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.asso.aaa.sivin.schemas
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NumeroVinInconnu }
     * 
     */
    public NumeroVinInconnu createNumeroVinInconnu() {
        return new NumeroVinInconnu();
    }

    /**
     * Create an instance of {@link NumeroImmatriculationInconnu }
     * 
     */
    public NumeroImmatriculationInconnu createNumeroImmatriculationInconnu() {
        return new NumeroImmatriculationInconnu();
    }

    /**
     * Create an instance of {@link WSSiVinConsulterVehiculeParVin }
     * 
     */
    public WSSiVinConsulterVehiculeParVin createWSSiVinConsulterVehiculeParVin() {
        return new WSSiVinConsulterVehiculeParVin();
    }

    /**
     * Create an instance of {@link Quota }
     * 
     */
    public Quota createQuota() {
        return new Quota();
    }

    /**
     * Create an instance of {@link WSSiVinConsulterVehiculeResponse }
     * 
     */
    public WSSiVinConsulterVehiculeResponse createWSSiVinConsulterVehiculeResponse() {
        return new WSSiVinConsulterVehiculeResponse();
    }

    /**
     * Create an instance of {@link WSSiVinConsulterFlotteParSirenResponse }
     * 
     */
    public WSSiVinConsulterFlotteParSirenResponse createWSSiVinConsulterFlotteParSirenResponse() {
        return new WSSiVinConsulterFlotteParSirenResponse();
    }

    /**
     * Create an instance of {@link Vin }
     * 
     */
    public Vin createVin() {
        return new Vin();
    }

    /**
     * Create an instance of {@link NumeroSirenInconnu }
     * 
     */
    public NumeroSirenInconnu createNumeroSirenInconnu() {
        return new NumeroSirenInconnu();
    }

    /**
     * Create an instance of {@link WSSiVinConsulterVehiculeParImmat }
     * 
     */
    public WSSiVinConsulterVehiculeParImmat createWSSiVinConsulterVehiculeParImmat() {
        return new WSSiVinConsulterVehiculeParImmat();
    }

    /**
     * Create an instance of {@link WSSiVinConsulterFlotteParSiren }
     * 
     */
    public WSSiVinConsulterFlotteParSiren createWSSiVinConsulterFlotteParSiren() {
        return new WSSiVinConsulterFlotteParSiren();
    }

    /**
     * Create an instance of {@link BaseDeDonneeInaccessible }
     * 
     */
    public BaseDeDonneeInaccessible createBaseDeDonneeInaccessible() {
        return new BaseDeDonneeInaccessible();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aaa.asso.fr/sivin/schemas", name = "Vin")
    public JAXBElement<Vin> createVin(Vin value) {
        return new JAXBElement<Vin>(_Vin_QNAME, Vin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aaa.asso.fr/sivin/schemas", name = "message", scope = BaseDeDonneeInaccessible.class)
    public JAXBElement<String> createBaseDeDonneeInaccessibleMessage(String value) {
        return new JAXBElement<String>(_BaseDeDonneeInaccessibleMessage_QNAME, String.class, BaseDeDonneeInaccessible.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aaa.asso.fr/sivin/schemas", name = "message", scope = NumeroVinInconnu.class)
    public JAXBElement<String> createNumeroVinInconnuMessage(String value) {
        return new JAXBElement<String>(_BaseDeDonneeInaccessibleMessage_QNAME, String.class, NumeroVinInconnu.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aaa.asso.fr/sivin/schemas", name = "message", scope = NumeroSirenInconnu.class)
    public JAXBElement<String> createNumeroSirenInconnuMessage(String value) {
        return new JAXBElement<String>(_BaseDeDonneeInaccessibleMessage_QNAME, String.class, NumeroSirenInconnu.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aaa.asso.fr/sivin/schemas", name = "message", scope = NumeroImmatriculationInconnu.class)
    public JAXBElement<String> createNumeroImmatriculationInconnuMessage(String value) {
        return new JAXBElement<String>(_BaseDeDonneeInaccessibleMessage_QNAME, String.class, NumeroImmatriculationInconnu.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aaa.asso.fr/sivin/schemas", name = "message", scope = Quota.class)
    public JAXBElement<String> createQuotaMessage(String value) {
        return new JAXBElement<String>(_BaseDeDonneeInaccessibleMessage_QNAME, String.class, Quota.class, value);
    }

}
