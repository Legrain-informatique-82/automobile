
package fr.careco.aaa.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.8-patch-01
 * 2013-11-26T11:15:22.022+01:00
 * Generated source version: 2.4.8-patch-01
 */

@WebFault(name = "Quota", targetNamespace = "http://aaa.asso.fr/sivin/schemas")
public class Quota_Exception extends Exception {
    
    private fr.careco.aaa.ws.Quota quota;

    public Quota_Exception() {
        super();
    }
    
    public Quota_Exception(String message) {
        super(message);
    }
    
    public Quota_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Quota_Exception(String message, fr.careco.aaa.ws.Quota quota) {
        super(message);
        this.quota = quota;
    }

    public Quota_Exception(String message, fr.careco.aaa.ws.Quota quota, Throwable cause) {
        super(message, cause);
        this.quota = quota;
    }

    public fr.careco.aaa.ws.Quota getFaultInfo() {
        return this.quota;
    }
}
