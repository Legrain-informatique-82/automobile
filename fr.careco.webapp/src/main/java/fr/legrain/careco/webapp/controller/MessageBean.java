package fr.legrain.careco.webapp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import fr.legrain.bdg.tiers.service.remote.IMessageServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IUserServiceRemote;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.tiers.model.Message;
import fr.legrain.tiers.model.User;

@ManagedBean
@ViewScoped 
public class MessageBean implements Serializable {  
	
	private User user;
	
	private List<Message> mesMessagesEnvoyes;
	private List<Message> mesMessagesRecu;
	
	private Message messageEnCours;
	
	private Message reponseA;
	
	private Integer repondre = null;
	
	@EJB
	private IMessageServiceRemote messageService;
	
	@EJB
	private IUserServiceRemote userServiceRemote;
	
	private TreeNode arbreMessagesEnvoyes;
	private TreeNode arbreMessagesRecu;
	
	public void refresh() {
		//mesMessages = messageService.findById(user);
		mesMessagesEnvoyes = messageService.selectMessageFrom(user.getId());
		mesMessagesRecu = messageService.selectMessageTo(user.getId());
		
		messageEnCours = new Message();
		
		try {
			if(repondre!=null) {
				
					reponseA = messageService.findById(repondre);
				
				if(reponseA!=null) {
					messageEnCours.setA(reponseA.getDe());
					messageEnCours.setSujet("Re : "+reponseA.getSujet());
				}
			}
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshArbres() {
		arbreMessagesEnvoyes = new DefaultTreeNode("rootEnvoyes", null);  
		TreeNode documents = new DefaultTreeNode(new Message(), arbreMessagesEnvoyes); 
	}
	
	@PostConstruct
	public void init() {
		user = Auth.findUserInSession();
		refresh();
	}
	
	public void envoyerMessage(ActionEvent event) throws Exception {
		//Integer idLignePanier = (Integer) event.getComponent().getAttributes().get("idLignePanier");
		
		messageEnCours.setDe(user);
		messageEnCours.setImportant(false);
		messageEnCours.setNouveau(true);
		messageEnCours.setType(null);
		
		messageEnCours = messageService.enregistrerMerge(messageEnCours);

		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Message", 
	    		"Envoyer message."
	    		));  
	    
	    ExternalContext context2 = FacesContext.getCurrentInstance().getExternalContext();
	    context2.redirect(context2.getRequestContextPath() + "/messages_careco_template.xhtml");
		
	}
	
	public void repondreMessage(ActionEvent event) {
		Integer idMessage = (Integer) event.getComponent().getAttributes().get("idMessage");
	    
	    ExternalContext context2 = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			context2.redirect(context2.getRequestContextPath() + "/envoye_message_careco_template.xhtml?repondre="+idMessage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void lireMessage(SelectEvent event) throws Exception {

		messageEnCours.setNouveau(false);
		
		messageEnCours = messageService.enregistrerMerge(messageEnCours);
		
	}
	
	public Integer getNbNouveauMessage() {
		return messageService.getNbNouveauMessage(user.getId());
	}
	
	public List<User> autoCompleteUser(String query) {		
		List<User> l = userServiceRemote.selectAll(query);

		return l;
	}

	public Message getMessageEnCours() {
		return messageEnCours;
	}

	public void setMessageEnCours(Message messageEnCours) {
		this.messageEnCours = messageEnCours;
	}

	public List<Message> getMesMessagesEnvoyes() {
		return mesMessagesEnvoyes;
	}

	public void setMesMessagesEnvoyes(List<Message> mesMessagesEnvoyes) {
		this.mesMessagesEnvoyes = mesMessagesEnvoyes;
	}

	public List<Message> getMesMessagesRecu() {
		return mesMessagesRecu;
	}

	public void setMesMessagesRecu(List<Message> mesMessagesRecu) {
		this.mesMessagesRecu = mesMessagesRecu;
	}

	public Integer getRepondre() {
		return repondre;
	}

	public void setRepondre(Integer repondre) {
		this.repondre = repondre;
	}

	public TreeNode getArbreMessagesEnvoyes() {
		return arbreMessagesEnvoyes;
	}

	public TreeNode getArbreMessagesRecu() {
		return arbreMessagesRecu;
	}

	public void setArbreMessagesEnvoyes(TreeNode arbreMessagesEnvoyes) {
		this.arbreMessagesEnvoyes = arbreMessagesEnvoyes;
	}

	public void setArbreMessagesRecu(TreeNode arbreMessagesRecu) {
		this.arbreMessagesRecu = arbreMessagesRecu;
	}
	
	
}  
              