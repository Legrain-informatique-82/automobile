
package fr.careco.blueway.ws.b2c;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.0.0
 * 2014-06-13T15:57:13.283+02:00
 * Generated source version: 3.0.0
 * 
 */
 
public class ProxyWsAAAB2CPortType_ProxyWsAAAB2CPort_Server{

    protected ProxyWsAAAB2CPortType_ProxyWsAAAB2CPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new ProxyWsAAAB2CPortImpl();
        String address = "http://carecoprod.blueway.fr:8180/engine53/52/WebserviceLauncher";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new ProxyWsAAAB2CPortType_ProxyWsAAAB2CPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
