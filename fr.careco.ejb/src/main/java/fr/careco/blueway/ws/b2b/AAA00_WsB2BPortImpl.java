
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package fr.careco.blueway.ws.b2b;

import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import fr.careco.aaa.ws.Vehicule;
import fr.careco.blueway.ws.WsAAAMapper;
import fr.careco.blueway.ws.model.AutorisationB2B;
import fr.careco.blueway.ws.model.IAutorisationServiceRemote;
import fr.careco.blueway.ws.model.ILogAppelServiceRemote;
import fr.careco.blueway.ws.model.LogAppelB2B;
import fr.legrain.careco.aaa.model.IVehiculeCacheAAAServiceRemote;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.lib.data.LibConversion;

/**
 * This class was generated by Apache CXF 3.0.0
 * 2014-06-17T12:16:52.752+02:00
 * Generated source version: 3.0.0
 * 
 */

@Stateless
@javax.jws.WebService(
                      serviceName = "AAA00_WsB2B",
                      portName = "AAA00_WsB2BPort",
                      targetNamespace = "http://carecoprod.blueway.fr:8180/engine53/52",
                      //wsdlLocation = "file:/home/nicolas/Bureau/Careco/Pack Careco/WSDL_B2B.wsdl",
                      wsdlLocation = "/fr/careco/blueway/ws/b2b/WSDL_B2B.wsdl",
                      endpointInterface = "fr.careco.blueway.ws.b2b.AAA00WsB2BPortType")
@org.jboss.ws.api.annotation.WebContext(contextRoot = "/engine53/52" , urlPattern="/WebserviceLauncher")
@HandlerChain(file="/fr/careco/blueway/ws/handler-chain.xml")                     
public class AAA00_WsB2BPortImpl implements AAA00WsB2BPortType {

    private static final Logger LOG = Logger.getLogger(AAA00_WsB2BPortImpl.class.getName());
    
    @EJB
    private IVehiculeCacheAAAServiceRemote vehiculeCacheAAAService;
    
    @EJB
    private IAutorisationServiceRemote autorisationService;
    
    @EJB
    private ILogAppelServiceRemote logAppelService;

    /* (non-Javadoc)
     * @see fr.careco.bleuway.ws2.b2b.AAA00WsB2BPortType#aaa00WsB2B(fr.careco.bleuway.ws2.b2b.AAA00WsB2BIN  aaa00WsB2BInput )*
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public fr.careco.blueway.ws.b2b.AAA00WsB2BOUT aaa00WsB2B(AAA00WsB2BIN aaa00WsB2BInput) { 
        LOG.info("Executing operation aaa00WsB2B");
        System.out.println(getCurrentClientIpAddress());
        System.out.println(aaa00WsB2BInput);
        System.out.println("login : "+aaa00WsB2BInput.getVarAAA().getLogin());
        System.out.println("mdp : "+aaa00WsB2BInput.getVarAAA().getMdp());
        System.out.println("typeReq : "+aaa00WsB2BInput.getVarAAA().getTypeReq());
        System.out.println("immat : "+aaa00WsB2BInput.getVarAAA().getImmat());
        
        System.out.println(aaa00WsB2BInput);
        
        LogAppelB2B log = new LogAppelB2B();
        AutorisationB2B aut = null;
        fr.careco.blueway.ws.b2b.AAA00WsB2BOUT _return = null;
        
        try {
	        	_return = new fr.careco.blueway.ws.b2b.AAA00WsB2BOUT(); 
	            VarAAARetourWs aaaWs = new VarAAARetourWs();
	    		_return.setVarAAARetourWs(aaaWs);
             	
             	log.setDate_Log(new Date());
             	log.setIp(getCurrentClientIpAddress());
             	log.setHeure_Log(new Date());
             	log.setImmat_Log(aaa00WsB2BInput.getVarAAA().getImmat());
             	log.setLogin_Log(aaa00WsB2BInput.getVarAAA().getLogin());
             	log.setMdp_Log(aaa00WsB2BInput.getVarAAA().getMdp());
             	log.setTyperReq_Log(LibConversion.stringToInteger(aaa00WsB2BInput.getVarAAA().getTypeReq()));
             	
             	//vérifier les droits d'utilisation du webservice dans la table AutorisationB2B et AutorisationB2C
            	aut = autorisationService.findByLoginB2B(aaa00WsB2BInput.getVarAAA().getLogin(),aaa00WsB2BInput.getVarAAA().getMdp());
            	
            	if(aut!=null) {
            		if(aut.getActiver()==1 ) {
            			//if {aut.getNbAppelMin()!=null && aut.getNbAppelMin() <50 ) {
            				//if {aaa00WsB2CInput.getVarAAA().getNom()==null ) {
            				
			            		System.out.println("Web Service B2B appelé par : "+aut.getNomAuthen()+" (login : "+aut.getLoginAuthen()+")");
			    	        	//si OK, consultation du cache puis appel du WS AAA si besoin.
			    	        	//VehiculeCacheAAA v = vehiculeCacheAAAService.wsSiVinConsulterVehiculeParImmatOuVin(aaa00WsB2BInput.getVarAAA().immat, null);
			            		VehiculeCacheAAA vCache = vehiculeCacheAAAService.rechercheDansCache(aaa00WsB2BInput.getVarAAA().immat, null);
			            		//VehiculeCacheAAA vCache = vehiculeCacheAAAService.rechercheDansCache("AA132SW", null);
			            		
			            		if(vCache==null) {
			            			//pas dans le cache, donc appel du ws
			            			vCache = vehiculeCacheAAAService.wsSiVinConsulterVehiculeParImmatOuVinProprio(aaa00WsB2BInput.getVarAAA().immat, null, null,true);
			            			//Appel de AAA avec une réponse positive - code type de log = 1 (Immat non trouvé dans la base de données. Appel du Ws  AAA avec retour des données sur l’immat)
			            			log.setCodeType_Log(1);
			            			log.setMessage("Vehicule trouvé par AAA");
			            		} else {
			            			//Lecture de la base de données – code type de log = 3 (Immat trouvé dans la base de données)
			            			log.setMessage("Vehicule dans le cache");
			            			log.setCodeType_Log(3);
			            		}
			            		
			    	        	if(vCache!=null) {
			            			//préparation de la réponse
//			    	        		_return = new fr.careco.blueway.ws.b2b.AAA00WsB2BOUT(); 
			    	        		WsAAAMapper mapper = new WsAAAMapper();
		             				//VarAAARetourWs aaaWs = mapper.mapCacheAAAtoVarAAARetourWsB2B(vCache);
			    	        		aaaWs = mapper.mapCacheAAAtoVarAAARetourWsB2B(vCache);
			            			_return.setVarAAARetourWs(aaaWs);
			            		
			    		        	// log de l'appel au WS pour facturation
			    		        	//logAppelService.enregistreLogB2B(log);
			    		        	
			    	        	}
		//            		} else {
		//            			log.setCodeType_Log(6);
		//                		log.setMessage("Début du nom non renseigné (seulement pour B2C)");
		//            		}
	//            		} else {
	//            			log.setCodeType_Log(6);
	//                		log.setMessage("Accès trop fréquents");
	//            		}
            		} else {
            			log.setCodeType_Log(6);
                		log.setMessage("Compte Utilisateur désactivé");
            		}
            	} else {
            		//Erreur fonctionnelle de traitement Blueway – code type de log = 6 (Demande non conforme) 
            		//2.11.2.1 Identification erronée
            		log.setCodeType_Log(6);
            		log.setMessage("Identification erronée");
            	}
             	
             	return _return;
        } catch (javax.ejb.EJBException ex) {
        	System.out.println("==================== javax.ejb.EJBException");
        	//javax.ejb.EJBException: BB88BB Vehicule non trouve
        	if(ex.getMessage()!=null && ex.getMessage().contains("Vehicule non trouve")) {
        		//Appel de AAA avec une réponse négative – code type de log = 2 (Immat non trouvé dans la base de données. Appel du Ws  AAA avec retour en erreur)
        		log.setCodeType_Log(2);
        		log.setMessage("Vehicule non trouvé");
        		System.out.println("==================== javax.ejb.EJBException 2");
        	} else {
        		//Erreur fonctionnelle de traitement AAA – code type de log = 4 (Erreur du ws AAA)
        		log.setCodeType_Log(4);
        		log.setMessage("Erreur AAA");
        		System.out.println("==================== javax.ejb.EJBException 4");
        	}
        	
        	ex.printStackTrace();
            //throw new RuntimeException(ex);
        	return _return;
        } catch (java.lang.Exception ex) {
			//Erreur technique de traitement Blueway – code type de log = 5 (Erreur de Blueway  dans le processus de traitement de la demande)
        	log.setCodeType_Log(5);
        	log.setMessage("Erreur WS");
        	System.out.println("==================== javax.ejb.Exception 5");
        	
            ex.printStackTrace();
        	return _return;
           // throw new RuntimeException(ex);
        } finally {
        	// log de l'appel au WS pour facturation
        	logAppelService.enregistreLogB2B(log);
        	if(aut!=null) {
        		try {
        			aut.setTime(new Date());
//        			if( (new Date().getTime()-aut.getTime().getTime()) > 60000 ) {
//        				if(aut.getNbAppelMin()==null) {
//        					aut.setNbAppelMin(0);
//        				}
//        				aut.setNbAppelMin(aut.getNbAppelMin()+1);
//        			} else {
//        				aut.setTime(new Date());
//        				aut.setNbAppelMin(1);
//        			}
					autorisationService.enregistrerMerge(aut);
				} catch (Exception e) {
					System.out.println("==========finally ========== javax.ejb.Exception");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
    }
    
    private String getCurrentClientIpAddress() {
    	//http://stackoverflow.com/questions/1014358/how-can-you-get-the-calling-ip-address-on-an-ejb-call
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("Threadname: "+currentThreadName);
//        int begin = currentThreadName.indexOf('[') +1;
//        int end = currentThreadName.indexOf(']')-1;
        int begin = currentThreadName.indexOf('/') +1;
        int end = currentThreadName.indexOf(':');
        String remoteClient = currentThreadName.substring(begin, end);
        return remoteClient;
    }
    
//    @Resource
//    private EJBContext ejbContext;
//
//    public void doSomething(){
//
//        ejbContext.getCallerPrincipal().;
//
//    }
    
//    public VarAAARetourWs mapAAAtoVarAAARetourWs(Vehicule ws) {
//    	VarAAARetourWs var = null;
//		if(var==null)
//			var = new VarAAARetourWs();
//								
//	    var.setTurboCompr(ws.getTurboCompr());
//	    var.setPuisKw(ws.getPuisKw());
//	    var.setTPBoiteVit(ws.getTpBoiteVit());
//	    var.setPuisCh(ws.getPuisCh());
//	    var.setPuisFisc(ws.getPuisFisc());
//	    var.setPTRPRF(ws.getPtrPrf());
//	    var.setPTR(ws.getPtr());
//	    var.setPropulsion(ws.getPropulsion());
//	    var.setPrixVehic(ws.getPrixVehic());
//	    var.setNSerie(ws.getNSerie());
//	    var.setPoidsVide(ws.getPoidsVide());
//	    var.setNBVolume(ws.getNbVolume());
//	    var.setNBVitesse(ws.getNbVitesse());
//	    var.setNBSoupapes(ws.getNbSoupape());
//	    var.setNBPortes(ws.getNbPortes());
//	    var.setNBPLAss(ws.getNbPlAss());
//	    var.setNBCylind(ws.getNbCylind());
//	    var.setModeInject(ws.getModeInject());
//	    var.setModelePRF(ws.getModelePrf());
//	    var.setModele(ws.getModele());
//	    var.setModeleEtude(ws.getModeleEtude());
//	    var.setMarqueCarros(ws.getMarqueCarros());
//	    var.setMarque(ws.getMarque());
//	    var.setLargeur(ws.getLargeur());
//	    var.setLongueur(ws.getLongueur());
//	    var.setImmatSIV(ws.getImmatSiv());
//	    var.setHauteur(ws.getHauteur());
//	    var.setGenreV(ws.getGenreV());
//	    var.setGenreVCG(ws.getGenreVCG());
//	    var.setEnergie(ws.getEnergie());
//	    var.setEmpat(ws.getEmpat());
//	    var.setDepollution(ws.getDepollution());
//	    var.setDateDcgMois(LibConversion.integerToString(ws.getDateDCG().getMois().getMonth()));
//	    var.setDateDcgJour(LibConversion.integerToString(ws.getDateDCG().getJour().getDay()));
//	    var.setDateDCGAnnee(LibConversion.integerToString(ws.getDateDCG().getAnnee().getYear()));
//	    var.setDate1ErCirMois(LibConversion.integerToString(ws.getDate1ErCir().getMois().getMonth()));
//	    var.setDate1ErCIRAnnee(LibConversion.integerToString(ws.getDate1ErCir().getAnnee().getYear()));
//	    var.setDate1ErCirJour(LibConversion.integerToString(ws.getDate1ErCir().getJour().getDay()));
//	    var.setCylindree(ws.getCylindree());
//	    var.setCouleurVehic(ws.getCouleurVehic());
//	    var.setConsUrb(ws.getConsUrb());
//	    var.setConsMixte(ws.getConsMixte());
//	    var.setConsExurb(ws.getConsExurb());
//	    var.setCodifVinPRF(ws.getCodifVin());
//	    var.setCodeMoteur(ws.getCodeMoteur());
//	    var.setCO2(ws.getCo2());
//	    var.setCarrosserieCG(ws.getCarrosserieCG());
//	    var.setCarrosserie(ws.getCarrosserie());
//	    var.setType(ws.getType());
//	    var.setTypePrf(ws.getTypePrf());
//	    var.setTypeVarianteVersion(ws.getTypeVarVersPrf());
//	    var.setTypeVinCG(ws.getTypeVinCG());
//	    var.setVersion(ws.getVersion());
//		
//		return var;
//    }
    
}