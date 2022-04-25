package fr.careco.blueway.ws;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.io.output.ByteArrayOutputStream;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		logToSystemOut(context);
		return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		logToSystemOut(context);
		return true;
	}

	private void logToSystemOut(SOAPMessageContext smc) {

		Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		try {
			if (!outboundProperty.booleanValue()) {

				SOAPMessage message = smc.getMessage();

				System.out.println("Incoming message:");
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				message.writeTo(stream);

				System.out.println(stream.toString());
				System.out.println("=====================================");                
			} else {
				SOAPMessage message = smc.getMessage();

				System.out.println("Outcoming message:");
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				message.writeTo(stream);
				
				//smc.setMessage(testLgr());
				
				//message.
				//xmlns="http://carecoprod.blueway.fr:8180/engine53/52"

				System.out.println(stream.toString());
				System.out.println("=====================================");       
			}
		}
		catch (Exception e) {
			System.out.println("Exception in handler: " + e);
		}
	}
	
	public SOAPMessage test() throws SOAPException, IOException {
		String messageRef = 
		"<SOAP-ENV:Envelope "
		+ "SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" "
		+ "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" "
		+ "xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" "
		+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
		+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
		  +" <SOAP-ENV:Header>"
		  +" <wsse:Security SOAP-ENV:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"
			  +" <wsu:Timestamp xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">"
				  +"     <wsu:Created>2014-06-04T14:10:27Z</wsu:Created>"
		      +"      <wsu:Expires>2015-06-04T14:10:32Z</wsu:Expires>"
		     +"    </wsu:Timestamp>"
		     +" </wsse:Security>"
		  +" </SOAP-ENV:Header>"
		 +" <SOAP-ENV:Body>"
		 +"     <tns:AAA00_WsB2COUT xmlns:tns=\"http://carecoprod.blueway.fr:8180/engine53/52/WebserviceLauncher\">"
			 +"       <tns:Var_AAA_retourWs>"
		  +"          <tns:Version xsi:type=\"string\">X/SX</tns:Version>"
		  +"          <tns:Type xsi:type=\"string\">MCT000AB6455</tns:Type>"
		  +"         <tns:Type_Vin_CG xsi:type=\"string\">S0HDZF</tns:Type_Vin_CG>"
		  +"          <tns:Turbo_Compr xsi:type=\"string\">NON TURBO</tns:Turbo_Compr>"
		  +"         <tns:TP_Boite_Vit xsi:type=\"string\">MECANIQUE</tns:TP_Boite_Vit>"
		  +"          <tns:Puis_Fisc xsi:type=\"string\">5</tns:Puis_Fisc>"
		  +" <tns:Puis_Ch xsi:type=\"string\">60</tns:Puis_Ch>"
		            +" <tns:PTR_PRF xsi:type=\"string\">0191</tns:PTR_PRF>"
		            +" <tns:PTR xsi:type=\"string\">1310</tns:PTR>"
		            +" <tns:Propulsion xsi:type=\"string\">AVANT</tns:Propulsion>"
		            +" <tns:Poids_Vide xsi:type=\"string\">805</tns:Poids_Vide>"
		            +" <tns:N_Serie xsi:type=\"string\">56452725</tns:N_Serie>"
		            +" <tns:NB_Volume xsi:type=\"string\">2</tns:NB_Volume>"
		            +" <tns:NB_Vitesse xsi:type=\"string\">5</tns:NB_Vitesse>"
		            +"  <tns:NB_Soupapes xsi:type=\"string\">2</tns:NB_Soupapes>"
		            +" <tns:NB_Portes xsi:type=\"string\">3</tns:NB_Portes>"
		            +" <tns:NB_Cylind xsi:type=\"string\">4</tns:NB_Cylind>"
		            +" <tns:NB_PL_Ass xsi:type=\"string\">5</tns:NB_PL_Ass>"
		            +" <tns:Mode_Inject xsi:type=\"string\">AUTR. CARBURATIONS</tns:Mode_Inject>"
		            +" <tns:Modele_PRF xsi:type=\"string\">SAXO</tns:Modele_PRF>"
		            +" <tns:Modele_Etude xsi:type=\"string\">SAXO</tns:Modele_Etude>"
		            +" <tns:Modele xsi:type=\"string\">SAXO</tns:Modele>"
		            +" <tns:Marque_Carros xsi:type=\"string\">CITROEN</tns:Marque_Carros>"
		            +"  <tns:Marque xsi:type=\"string\">CITROEN</tns:Marque>"
		            +" <tns:Longueur xsi:type=\"string\"/>"
		            +" <tns:Hauteur xsi:type=\"string\">136</tns:Hauteur>"
		            +"  <tns:Immat_SIV xsi:type=\"string\">80XC5072</tns:Immat_SIV>"
		            +"  <tns:Largeur xsi:type=\"string\">160</tns:Largeur>"
		            +"  <tns:Genre_V_CG xsi:type=\"string\">VP</tns:Genre_V_CG>"
		            +" <tns:Energie xsi:type=\"string\">ESSENCE</tns:Energie>"
		            +" <tns:Genre_V xsi:type=\"string\">VP</tns:Genre_V>"
		            +" <tns:Empat xsi:type=\"string\">239</tns:Empat>"
		            +" <tns:Depollution xsi:type=\"string\">NON</tns:Depollution>"
		            +"  <tns:Date_Dcg_Mois xsi:type=\"string\">08</tns:Date_Dcg_Mois>"
		            +"  <tns:Date_Dcg_Jour xsi:type=\"string\">25</tns:Date_Dcg_Jour>"
		            +"  <tns:Date_DCG_Annee xsi:type=\"string\">2006</tns:Date_DCG_Annee>"
		            +"   <tns:Date_1er_Cir_Mois xsi:type=\"string\">03</tns:Date_1er_Cir_Mois>"
		            +"   <tns:Date_1er_Cir_jour xsi:type=\"string\">19</tns:Date_1er_Cir_jour>"
		            +"   <tns:Date_1Er_CIR_Annee xsi:type=\"string\">1998</tns:Date_1Er_CIR_Annee>"
		            +"    <tns:Cylindree xsi:type=\"string\">1124</tns:Cylindree>"
		            +"    <tns:Couleur_Vehic xsi:type=\"string\">GRIS</tns:Couleur_Vehic>"
		            +"     <tns:Codif_Vin_PRF xsi:type=\"string\">VF7S0HDZF56452725</tns:Codif_Vin_PRF>"
		            +"  <tns:Carrosserie_CG xsi:type=\"string\">CI</tns:Carrosserie_CG>"
		            +"      <tns:CO2 xsi:type=\"string\">155</tns:CO2>"
		            +"      <tns:Carrosserie xsi:type=\"string\">BERLINE</tns:Carrosserie>"
		            +"     </tns:Var_AAA_retourWs>"
		            +"    </tns:AAA00_WsB2COUT>"
		      +"  </SOAP-ENV:Body>"
		   +"</SOAP-ENV:Envelope>"
		;
		
		return getSoapMessageFromString(messageRef);
	}
	
	public SOAPMessage testLgr() throws SOAPException, IOException {
		String messageRef = 
		"<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+"<soap:Body>"
     +" <AAA00_WsB2BOUT xmlns=\"http://carecoprod.blueway.fr:8180/engine53/52\">"
        +" <Var_AAA_retourWs>"
           +" <Turbo_Compr/>"
         +"   <Puis_Kw/>"
         +"   <TP_Boite_Vit/>"
         +"   <Puis_Ch/>"
         +"   <Puis_Fisc/>"
         +"   <PTR_PRF/>"
         +"   <PTR/>"
        +"    <Propulsion/>"
         +"   <Prix_Vehic/>"
         +"   <N_Serie/>"
         +"   <Poids_Vide/>"
         +"   <NB_Volume/>"
         +"   <NB_Vitesse/>"
         +"   <NB_Soupapes/>"
         +"   <NB_Portes/>"
         +"   <NB_PL_Ass/>"
          +"  <NB_Cylind/>"
		+"  <Mode_Inject/>"
            +"  <Modele_PRF/>"
            +"  <Modele/>"
            +" <Modele_Etude/>"
            +" <Marque_Carros/>"
            +"  <Marque>test OK pour </Marque>"
          +"  <Largeur/>"
            +" <Longueur/>"
            +" <Immat_SIV>azerty</Immat_SIV>"
            +"  <Hauteur/>"
          +"  <Genre_V/>"
            +"  <Genre_V_CG/>"
            +"<Energie/>"
            +" <Empat/>"
            +" <Depollution/>"
            +" <Date_Dcg_Mois/>"
            +" <Date_Dcg_Jour/>"
           +" <Date_DCG_Annee/>"
            +"<Date_1er_Cir_Mois/>"
            +" <Date_1Er_CIR_Annee/>"
           +" <Date_1er_Cir_jour/>"
            +" <Cylindree/>"
            +" <Couleur_Vehic/>"
            +" <Cons_Urb/>"
            +" <Cons_Mixte/>"
            +" <Cons_Exurb/>"
            +" <Codif_Vin_PRF/>"
            +" <Code_Moteur/>"
            +" <CO2/>"
            +" <Carrosserie_CG/>"
            +" <Carrosserie/>"
            +" <Type/>"
            +" <Type_Prf/>"
            +"<Type_Variante_Version/>"
            +"  <Type_Vin_CG/>"
            +"  <Version/>"
            +"</Var_AAA_retourWs>"
         +" </AAA00_WsB2BOUT>"
      +"</soap:Body>"
   +"</soap:Envelope>"
		;
		
		return getSoapMessageFromString(messageRef);
	}
	
	private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
	    MessageFactory factory = MessageFactory.newInstance();
	    SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
	    return message;
	}
}