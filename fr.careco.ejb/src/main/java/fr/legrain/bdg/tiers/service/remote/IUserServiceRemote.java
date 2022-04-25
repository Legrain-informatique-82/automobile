package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.UserDTO;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;

@Remote
/*
public interface ITaTCiviliteServiceRemote {
	public void persist(TaTCivilite transientInstance) throws CreateException;
	public void remove(TaTCivilite persistentInstance) throws RemoveException;
	public TaTCivilite merge(TaTCivilite detachedInstance);
	public TaTCivilite findById(int id) throws FinderException;
	public TaTCivilite findByCode(String code) throws FinderException;
	public List<TaTCivilite> selectAll();
	
	public List<TaTCiviliteDTO> selectAllDTO();
	
	public void error(TaTCiviliteDTO dto);
}
*/
public interface IUserServiceRemote extends IGenericCRUDServiceRemote<User,UserDTO>,
														IAbstractLgrDAOServer<User>,IAbstractLgrDAOServerDTO<UserDTO>{
	
	public User findUserLogged();
	public Integer findUserLoggedId();
	public User chargeSocietesAmie(User uc);
	
	public List<User> selectAll(String debut);
	
}
