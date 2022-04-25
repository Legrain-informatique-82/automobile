package fr.legrain.careco.webapp.wsaaa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.RemoveException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import fr.careco.blueway.ws.WsAAAMapper;
import fr.careco.blueway.ws.b2b.AAA00WsB2BIN;
import fr.careco.blueway.ws.b2b.AAA00WsB2BOUT;
import fr.careco.blueway.ws.b2b.AAA00WsB2BPortType;
import fr.careco.blueway.ws.b2c.AAA00WsB2CIN;
import fr.careco.blueway.ws.b2c.AAA00WsB2COUT;
import fr.careco.blueway.ws.b2c.ProxyWsAAAB2CPortType;
import fr.careco.blueway.ws.model.AutorisationB2B;
import fr.careco.blueway.ws.model.AutorisationB2C;
import fr.careco.blueway.ws.model.IAutorisationServiceRemote;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;

@ManagedBean
@ViewScoped  
public class WsAAABean {  

	private String immatriculation;
	private String debutNom;
	private String login;
	private String password;
	
	private VehiculeCacheAAA v;
	
	@EJB private AAA00WsB2BPortType b2bService;
	@EJB private ProxyWsAAAB2CPortType b2cService;
	
	@EJB private IAutorisationServiceRemote autorisationService;
    
	public WsAAABean() {  
	}  
	
	@PostConstruct
	public void postConstruct(){
		
	}
	
	public void recherche(ActionEvent evt){
		System.out.println("WsAAABean.recherche() -- appel direct des EJB, pas de webservcie");
		WsAAAMapper mapper = new WsAAAMapper();
		v = null;
		boolean donneesValide = true;
		
		if(login == null || login.equals("") || password == null ||password.equals("")) {
			FacesContext context = FacesContext.getCurrentInstance();  
		    context.addMessage(null, new FacesMessage("AAA", 
		    		"Veuillez saisir vos identifiant"
		    		)); 
		    donneesValide = false;
		}
		
		if(immatriculation == null || immatriculation.equals("")) {
			FacesContext context = FacesContext.getCurrentInstance();  
		    context.addMessage(null, new FacesMessage("AAA", 
		    		"Veuillez saisir une immatriculation"
		    		)); 
		    donneesValide = false;
		}

		if(donneesValide) {
			
			if(debutNom!=null && !debutNom.equals("")) {
				//B2C
				
				AutorisationB2C aut = autorisationService.findByLoginB2C(login,password);
				
				if(aut!=null) {
					AAA00WsB2CIN aaa00WsB2CInput = new AAA00WsB2CIN();
					fr.careco.blueway.ws.b2c.VarAAA varAAA = new fr.careco.blueway.ws.b2c.VarAAA();
					varAAA.setImmat(immatriculation);
					varAAA.setLogin(login);
					varAAA.setMdp(password);
					varAAA.setNom(debutNom);
					varAAA.setTypeReq("0");
					aaa00WsB2CInput.setVarAAA(varAAA);
	
					AAA00WsB2COUT aaa00WsB2COUT = b2cService.aaa00WsB2C(aaa00WsB2CInput);
					
					if(aaa00WsB2COUT!=null && aaa00WsB2COUT.getVarAAARetourWs()!=null && aaa00WsB2COUT.getVarAAARetourWs().getImmatSIV()!=null) {
						v = mapper.mapVarAAARetourWsB2CtoCacheAAA(aaa00WsB2COUT.getVarAAARetourWs());
						
						FacesContext context = FacesContext.getCurrentInstance();  
					    context.addMessage(null, new FacesMessage("AAA", 
					    		"Recherche B2C valide"
					    		)); 
					} else {
						FacesContext context = FacesContext.getCurrentInstance();  
					    context.addMessage(null, new FacesMessage("AAA", 
					    		"Recherche B2C sans résultat"
					    		)); 
					}
					
				} else {
					FacesContext context = FacesContext.getCurrentInstance();  
				    context.addMessage(null, new FacesMessage("AAA", 
				    		"Identification B2C incorrecte."
				    		)); 
				}

				System.out.println("WsAAABean.recherche() B2C OK");
			} else {
				//B2B
				
				AutorisationB2B aut = autorisationService.findByLoginB2B(login,password);
				
				if(aut!=null) {
					AAA00WsB2BIN aaa00WsB2BInput = new AAA00WsB2BIN();
					fr.careco.blueway.ws.b2b.VarAAA varAAA = new fr.careco.blueway.ws.b2b.VarAAA();
					varAAA.setImmat(immatriculation);
					varAAA.setLogin(login);
					varAAA.setMdp(password);
					varAAA.setTypeReq("0");
					aaa00WsB2BInput.setVarAAA(varAAA);
	
					AAA00WsB2BOUT aaa00WsB2BOUT = b2bService.aaa00WsB2B(aaa00WsB2BInput);
	
					if(aaa00WsB2BOUT!=null && aaa00WsB2BOUT.getVarAAARetourWs()!=null && aaa00WsB2BOUT.getVarAAARetourWs().getImmatSIV()!=null) {
						v = mapper.mapVarAAARetourWsB2BtoCacheAAA(aaa00WsB2BOUT.getVarAAARetourWs());
						
						FacesContext context = FacesContext.getCurrentInstance();  
					    context.addMessage(null, new FacesMessage("AAA", 
					    		"Recherche B2B valide"
					    		)); 
					} else {
						FacesContext context = FacesContext.getCurrentInstance();  
					    context.addMessage(null, new FacesMessage("AAA", 
					    		"Recherche B2B sans résultat"
					    		)); 
					}
				
				} else {
					FacesContext context = FacesContext.getCurrentInstance();  
				    context.addMessage(null, new FacesMessage("AAA", 
				    		"Identification B2B incorrecte."
				    		)); 
				}

				System.out.println("WsAAABean.recherche() B2B OK");
			}
		}
		
	}
	
	public void rechercheWS(ActionEvent evt){
		System.out.println("WsAAABean.rechercheWS() -- appel des webservices");
		
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public String getDebutNom() {
		return debutNom;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public VehiculeCacheAAA getV() {
		return v;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public void setDebutNom(String debutNom) {
		this.debutNom = debutNom;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setV(VehiculeCacheAAA v) {
		this.v = v;
	}
	
	
	
	
}  
