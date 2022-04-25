
package fr.careco.aaa.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.8-patch-01
 * 2013-11-26T11:15:22.013+01:00
 * Generated source version: 2.4.8-patch-01
 */

@WebFault(name = "BaseDeDonneeInaccessible", targetNamespace = "http://aaa.asso.fr/sivin/schemas")
public class BaseDeDonneeInaccessible_Exception extends Exception {
    
    private fr.careco.aaa.ws.BaseDeDonneeInaccessible baseDeDonneeInaccessible;

    public BaseDeDonneeInaccessible_Exception() {
        super();
    }
    
    public BaseDeDonneeInaccessible_Exception(String message) {
        super(message);
    }
    
    public BaseDeDonneeInaccessible_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseDeDonneeInaccessible_Exception(String message, fr.careco.aaa.ws.BaseDeDonneeInaccessible baseDeDonneeInaccessible) {
        super(message);
        this.baseDeDonneeInaccessible = baseDeDonneeInaccessible;
    }

    public BaseDeDonneeInaccessible_Exception(String message, fr.careco.aaa.ws.BaseDeDonneeInaccessible baseDeDonneeInaccessible, Throwable cause) {
        super(message, cause);
        this.baseDeDonneeInaccessible = baseDeDonneeInaccessible;
    }

    public fr.careco.aaa.ws.BaseDeDonneeInaccessible getFaultInfo() {
        return this.baseDeDonneeInaccessible;
    }
}
