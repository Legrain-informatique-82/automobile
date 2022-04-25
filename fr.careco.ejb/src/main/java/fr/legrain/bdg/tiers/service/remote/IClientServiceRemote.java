package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.ClientDTO;
import fr.legrain.tiers.model.Client;

@Remote

public interface IClientServiceRemote extends IGenericCRUDServiceRemote<Client,ClientDTO>,
														IAbstractLgrDAOServer<Client>,IAbstractLgrDAOServerDTO<ClientDTO>{
}
