
package fr.careco.aaa.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.8-patch-01
 * 2013-11-26T11:15:22.033+01:00
 * Generated source version: 2.4.8-patch-01
 */

@WebFault(name = "NumeroSirenInconnu", targetNamespace = "http://aaa.asso.fr/sivin/schemas")
public class NumeroSirenInconnu_Exception extends Exception {
    
    private fr.careco.aaa.ws.NumeroSirenInconnu numeroSirenInconnu;

    public NumeroSirenInconnu_Exception() {
        super();
    }
    
    public NumeroSirenInconnu_Exception(String message) {
        super(message);
    }
    
    public NumeroSirenInconnu_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public NumeroSirenInconnu_Exception(String message, fr.careco.aaa.ws.NumeroSirenInconnu numeroSirenInconnu) {
        super(message);
        this.numeroSirenInconnu = numeroSirenInconnu;
    }

    public NumeroSirenInconnu_Exception(String message, fr.careco.aaa.ws.NumeroSirenInconnu numeroSirenInconnu, Throwable cause) {
        super(message, cause);
        this.numeroSirenInconnu = numeroSirenInconnu;
    }

    public fr.careco.aaa.ws.NumeroSirenInconnu getFaultInfo() {
        return this.numeroSirenInconnu;
    }
}