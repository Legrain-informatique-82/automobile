package fr.legrain.careco.webapp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserServiceRemote;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.tiers.model.User;

@ManagedBean
@ViewScoped 
public class MonCompteBean implements Serializable {  
   
	private String motDePasseActuel = "";
	private String nouveauMotDePasse = "";
	private String confirmationMotDePasse = "";
	
	private User u = null;
	
	@EJB
    private IUserServiceRemote userService;
	
	@PostConstruct
	public void init() {
		u = Auth.findUserInSession();
	}
	
	public void changeMotDePasse(ActionEvent event) throws Exception {
		
		if(motDePasseActuel!=null && !motDePasseActuel.equals("") && u.passwordHashSHA256_Base64(motDePasseActuel).equals(u.getPasswd())) {
			//le mot de passe actuel est bien le bon
			
			if(nouveauMotDePasse!=null && !nouveauMotDePasse.equals("") 
					&& confirmationMotDePasse!=null && !confirmationMotDePasse.equals("") 
					&& nouveauMotDePasse.equals(confirmationMotDePasse)) {
				//le nouveau mot de passe saisie est correct
				
				if(verifComplexiteMotDePasse(nouveauMotDePasse)) {
					u.setPasswd(u.passwordHashSHA256_Base64(nouveauMotDePasse));
					userService.enregistrerMerge(u);
					
					Auth a = new Auth();
					a.logout();
					
					FacesContext context = FacesContext.getCurrentInstance();  
					context.addMessage(null, new FacesMessage("Mon compte", 
							"Votre mot de passe à bien été modifié."
							)); 
					
				} else {
					FacesContext context = FacesContext.getCurrentInstance();  
					context.addMessage(null, new FacesMessage("Mon compte", 
							"Votre nouveau mot de passe n'est pas assez complexe"
							)); 
				}
			} else {
				
				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage("Mon compte", 
						"Votre nouveau mot de passe n'est pas correct"
						)); 
			}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage("Mon compte", 
					"Votre mot de passe actuel n'est pas correct"
					)); 
		}
		
	}
	
	private boolean verifComplexiteMotDePasse(String pwd) {
		return true;
	}
	
	public String getMotDePasseActuel() {
		return motDePasseActuel;
	}
	public String getNouveauMotDePasse() {
		return nouveauMotDePasse;
	}
	public String getConfirmationMotDePasse() {
		return confirmationMotDePasse;
	}
	public void setMotDePasseActuel(String motDePasseActuel) {
		this.motDePasseActuel = motDePasseActuel;
	}
	public void setNouveauMotDePasse(String nouveaMotDePasse) {
		this.nouveauMotDePasse = nouveaMotDePasse;
	}
	public void setConfirmationMotDePasse(String confirmationMotDePasse) {
		this.confirmationMotDePasse = confirmationMotDePasse;
	}
   
}  
              