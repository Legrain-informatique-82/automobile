
package fr.asso.aaa.sivin;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.8-patch-01
 * 2014-06-24T14:16:30.952+02:00
 * Generated source version: 2.4.8-patch-01
 */

@WebFault(name = "Quota", targetNamespace = "http://aaa.asso.fr/sivin/schemas")
public class Quota extends Exception {
    
    private fr.asso.aaa.sivin.schemas.Quota quota;

    public Quota() {
        super();
    }
    
    public Quota(String message) {
        super(message);
    }
    
    public Quota(String message, Throwable cause) {
        super(message, cause);
    }

    public Quota(String message, fr.asso.aaa.sivin.schemas.Quota quota) {
        super(message);
        this.quota = quota;
    }

    public Quota(String message, fr.asso.aaa.sivin.schemas.Quota quota, Throwable cause) {
        super(message, cause);
        this.quota = quota;
    }

    public fr.asso.aaa.sivin.schemas.Quota getFaultInfo() {
        return this.quota;
    }
}
