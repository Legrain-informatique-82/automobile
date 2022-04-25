
package fr.asso.aaa.sivin.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.asso.aaa.sivin.xsd package. 
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

    private final static QName _Vehicule_QNAME = new QName("http://aaa.asso.fr/sivin/xsd", "Vehicule");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.asso.aaa.sivin.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Vehicule }
     * 
     */
    public Vehicule createVehicule() {
        return new Vehicule();
    }

    /**
     * Create an instance of {@link Proprietaire }
     * 
     */
    public Proprietaire createProprietaire() {
        return new Proprietaire();
    }

    /**
     * Create an instance of {@link Date }
     * 
     */
    public Date createDate() {
        return new Date();
    }

    /**
     * Create an instance of {@link Loueur }
     * 
     */
    public Loueur createLoueur() {
        return new Loueur();
    }

    /**
     * Create an instance of {@link Acquereur }
     * 
     */
    public Acquereur createAcquereur() {
        return new Acquereur();
    }

    /**
     * Create an instance of {@link Locataire }
     * 
     */
    public Locataire createLocataire() {
        return new Locataire();
    }

    /**
     * Create an instance of {@link MentionUsages }
     * 
     */
    public MentionUsages createMentionUsages() {
        return new MentionUsages();
    }

    /**
     * Create an instance of {@link Pneu }
     * 
     */
    public Pneu createPneu() {
        return new Pneu();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Vehicule }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aaa.asso.fr/sivin/xsd", name = "Vehicule")
    public JAXBElement<Vehicule> createVehicule(Vehicule value) {
        return new JAXBElement<Vehicule>(_Vehicule_QNAME, Vehicule.class, null, value);
    }

}
