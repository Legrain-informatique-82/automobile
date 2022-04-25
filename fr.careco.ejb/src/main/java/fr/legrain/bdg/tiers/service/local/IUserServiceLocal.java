package fr.legrain.bdg.tiers.service.local;

import javax.ejb.Local;
import javax.ejb.Remote;

import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServer;
import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServerDTO;
import fr.legrain.bdg.tiers.service.remote.IGenericCRUDServiceRemote;
import fr.legrain.tiers.dto.UserDTO;
import fr.legrain.tiers.model.User;

@Local
public interface IUserServiceLocal extends IGenericCRUDServiceRemote<User,UserDTO>,
														IAbstractLgrDAOServer<User>,IAbstractLgrDAOServerDTO<UserDTO>{
	
	public User findUserLogged();
	public Integer findUserLoggedId();
	
}
