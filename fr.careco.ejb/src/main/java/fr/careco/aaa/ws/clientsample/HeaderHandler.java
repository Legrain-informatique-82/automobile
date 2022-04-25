package fr.careco.aaa.ws.clientsample;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {
	 
    public boolean handleMessage(SOAPMessageContext context) {
    	/*
    	 	Identifiant pour l’accès B2B au Ws AAA
			Utilisateur: xxxxxxxxxx
			Mot de passe: xxxxxxxx
			SIRET: xxxxxxx
			Adresses IP autorisées: 88.190.230.9,88.190.230.22,88.190.230.23

			Identifiant pour l’accès B2C (Site Web avec les 3 premières lettres du nom)
			Utilisateur: xxxxxxxx
			Mot de passe: xxxxxxxx
			SIRET: xxxxxx
			Adresses IP autorisées: 88.190.230.9,88.190.230.22,88.190.230.23
    	 */
    	
    	/*
		A tester (nouvellement ouverte) : peux-tu faire la connexion et faire quelques tests pour voir les retours K-TYPE dessus s’il-te-plaît ?

    	Ligne SIVIN_51 :
        	User : xxxxxxx
        	Password : xxxxxxx
        	Siret : xxxx
        	IP autorisées :
	            188.165.45.142
	            188.165.243.53
	            37.59.53.196 

		Tes lignes actuelles :

    	Ligne : SIVIN B2B 49
	        User : xxxxxxx
	        Password : xxxxxxx
	        Siret : xxxxx
	        IP autorisées :
	            88.190.230.9
	            88.190.230.22
	            88.190.230.23
	            82.127.44.34
	            91.142.130.118
	            188.165.45.142
	            188.165.243.53
	            37.59.53.196
	            94.23.246.43

		    Ligne : B2B 41
		        User : xxxxxxx
		        Password : xxxxxx
		        Siret : xxxxxxx
		        IP autorisées :
		            88.190.230.9
		            88.190.230.22
		            88.190.230.23
		            82.127.44.34
		            91.142.130.118
		            91.142.130.118
		            188.165.45.142
		            188.165.243.53
		            37.59.53.196
		            94.23.246.43
		
		    Ligne :
		        User : xxxxxxxx
		        Password : xxxxxxx
		        Siret : xx
		        IP autorisées :
		            88.190.230.9
		            88.190.230.22
		            88.190.230.23
		            82.127.44.34
		            91.142.130.118
		            188.165.45.142
		            188.165.243.53
		            37.59.53.196
		            94.23.246.43
		
		    Ligne :
		        User : xxxxx
		        Password : xxxxxxx
		        Siret : xxxxxxxxxx
		        IP autorisées :
		            188.165.45.142
		            188.165.243.53
		            37.59.53.196
		            94.23.246.43
    	 */
    	
    	try {
            SOAPMessage message = context.getMessage();
            SOAPHeader header = message.getSOAPHeader();
            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
            if (header == null) {
                header = envelope.addHeader();
            }

            String leNameSpace="http://web.service.aaa.asso.fr";
            String lePrefixe="aaa";
//            String _siret = "Numérosiret";
//            String _util = "Identifiant";
//            String _password = "Motdepasse";
            
//            String _siret = "xxxxxxxxxxxxxxx";
//            String _util = "xxxxxxxxxxxx";
//            String _password = "xxxxxxxxx";
            
            String _siret = "xxxxxxxxxx";
            
//            String _util = "xxxxxxxxxxx";
//            String _password = "xxxxxxx";
            
            //49 champs, utilisable pour B2C
//          String _util = "xxxxxx";
//          String _password = "xxxxxxxx";
            
            //v2 avec pneu
//            String _util = "xxxxxxxx";
//            String _password = "xxxxx";
            
            //v2 avec 51 champs (pneu et ktype)
            String _util = "xxxxxx";
            String _password = "xxxxxxxxx";
            
            QName qNameSiret = new QName(leNameSpace, "N_SIRET",lePrefixe);
            SOAPHeaderElement siret = header.addHeaderElement(qNameSiret );
            siret.addTextNode(_siret);
            
            QName qNameUtil = new QName(leNameSpace, "NOM_UTIL",lePrefixe);
            SOAPHeaderElement util = header.addHeaderElement(qNameUtil);
            util.addTextNode(_util);
            
            QName qNamePassword = new QName(leNameSpace, "MDP_UTIL",lePrefixe);
            SOAPHeaderElement password = header.addHeaderElement(qNamePassword);
            password.addTextNode(_password);

            header.addChildElement(siret);
            header.addChildElement(util);
            header.addChildElement(password);

            message.saveChanges();
            //TODO: remove this writer when the testing is finished
            StringWriter writer = new StringWriter();
            message.writeTo(new StringOutputStream(writer));
            System.out.println("SOAP message: \n" + writer.toString());
        } catch (SOAPException e) {
        	System.err.println("Error occurred while adding credentials to SOAP header.");
        	e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error occurred while writing message to output stream.");
            e.printStackTrace();
        }
        return true;
    }

    //TODO: remove this class after testing is finished
    private static class StringOutputStream extends OutputStream {

        private StringWriter writer;

        public StringOutputStream(StringWriter writer) {
            this.writer = writer;
        }

        @Override
        public void write(int b) throws IOException {
            writer.write(b);
        }
    }
 
    public Set<QName> getHeaders() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return null;
    }
 
    public boolean handleFault(SOAPMessageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }
 
    public void close(MessageContext context) {
    //throw new UnsupportedOperationException("Not supported yet.");
    }
}
