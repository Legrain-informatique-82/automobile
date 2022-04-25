package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.MessageDTO;
import fr.legrain.tiers.model.Message;

@Remote

public interface IMessageServiceRemote extends IGenericCRUDServiceRemote<Message,MessageDTO>,
														IAbstractLgrDAOServer<Message>,IAbstractLgrDAOServerDTO<MessageDTO>{
	
	public List<Message> selectMessageFrom(int idUser);
	public List<Message> selectMessageTo(int idUser);
	public Integer getNbNouveauMessage(int idUser);
	
}
