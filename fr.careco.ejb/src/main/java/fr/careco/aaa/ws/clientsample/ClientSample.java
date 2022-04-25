package fr.careco.aaa.ws.clientsample;

import fr.careco.aaa.ws.*;

public class ClientSample {

	public static void main(String[] args) {
		try {
			System.out.println("***********************");
			System.out.println("Create Web Service Client...");
			WSSiVinConsulter service1 = new WSSiVinConsulter();
			System.out.println("Create Web Service...");
			
			/********************************************/
			HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver(HeaderHandlerResolver.TYPE_B2B);
			service1.setHandlerResolver(handlerResolver);
			/********************************************/
			
			WSSiVinConsulterPortType port1 = service1.getWSSiVinConsulterSOAP12Port();
			System.out.println("Call Web Service Operation...");

			System.out.println("Server said: " + port1.wsSiVinConsulterVehiculeParImmat("AA500HY",null,null,null,null));

			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Server said: " + port1.wsSiVinConsulterFlotteParSiren(null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Server said: " + port1.wsSiVinConsulterVehiculeParVin(null,null,null,null,null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Create Web Service...");
			WSSiVinConsulterPortType port2 = service1.getWSSiVinConsulterSOAP12Port();
			System.out.println("Call Web Service Operation...");
			System.out.println("Server said: " + port2.wsSiVinConsulterVehiculeParImmat(null,null,null,null,null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Server said: " + port2.wsSiVinConsulterFlotteParSiren(null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Server said: " + port2.wsSiVinConsulterVehiculeParVin(null,null,null,null,null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Create Web Service...");
			WSSiVinConsulterPortType port3 = service1.getWSSiVinConsulterSOAP11Port();
			System.out.println("Call Web Service Operation...");
			System.out.println("Server said: " + port3.wsSiVinConsulterVehiculeParImmat(null,null,null,null,null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Server said: " + port3.wsSiVinConsulterFlotteParSiren(null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Server said: " + port3.wsSiVinConsulterVehiculeParVin(null,null,null,null,null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Create Web Service...");
			WSSiVinConsulterPortType port4 = service1.getWSSiVinConsulterSOAP11Port();
			System.out.println("Call Web Service Operation...");
			System.out.println("Server said: " + port4.wsSiVinConsulterVehiculeParImmat(null,null,null,null,null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Server said: " + port4.wsSiVinConsulterFlotteParSiren(null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("Server said: " + port4.wsSiVinConsulterVehiculeParVin(null,null,null,null,null));
			//Please input the parameters instead of 'null' for the upper method!

			System.out.println("***********************");
			System.out.println("Call Over!");
		} catch (NumeroImmatriculationInconnu_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BaseDeDonneeInaccessible_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Quota_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumeroSirenInconnu_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumeroVinInconnu_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
