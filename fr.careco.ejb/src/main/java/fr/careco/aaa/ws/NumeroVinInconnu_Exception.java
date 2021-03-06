
package fr.careco.aaa.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.8-patch-01
 * 2013-11-26T11:15:22.041+01:00
 * Generated source version: 2.4.8-patch-01
 */

@WebFault(name = "NumeroVinInconnu", targetNamespace = "http://aaa.asso.fr/sivin/schemas")
public class NumeroVinInconnu_Exception extends Exception {
    
    private fr.careco.aaa.ws.NumeroVinInconnu numeroVinInconnu;

    public NumeroVinInconnu_Exception() {
        super();
    }
    
    public NumeroVinInconnu_Exception(String message) {
        super(message);
    }
    
    public NumeroVinInconnu_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public NumeroVinInconnu_Exception(String message, fr.careco.aaa.ws.NumeroVinInconnu numeroVinInconnu) {
        super(message);
        this.numeroVinInconnu = numeroVinInconnu;
    }

    public NumeroVinInconnu_Exception(String message, fr.careco.aaa.ws.NumeroVinInconnu numeroVinInconnu, Throwable cause) {
        super(message, cause);
        this.numeroVinInconnu = numeroVinInconnu;
    }

    public fr.careco.aaa.ws.NumeroVinInconnu getFaultInfo() {
        return this.numeroVinInconnu;
    }
}
