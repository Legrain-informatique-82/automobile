package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.AuthURLDTO;
import fr.legrain.tiers.model.AuthURL;

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
public interface IAuthURLServiceRemote extends IGenericCRUDServiceRemote<AuthURL,AuthURLDTO>,
														IAbstractLgrDAOServer<AuthURL>,IAbstractLgrDAOServerDTO<AuthURLDTO>{
	public List<AuthURL> findByRoleID(int roleID);
}
