package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.Message;
//import javax.ejb.Remote;

//@Remote
public interface IMessageDAO extends IGenericDAO<Message> {
	
	public List<Message> selectMessageFrom(int idUser);
	public List<Message> selectMessageTo(int idUser);
	public Integer getNbNouveauMessage(int idUser);
	
}
