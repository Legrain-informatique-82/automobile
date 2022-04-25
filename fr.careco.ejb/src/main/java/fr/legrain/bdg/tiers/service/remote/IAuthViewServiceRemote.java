package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.AuthViewDTO;
import fr.legrain.tiers.model.AuthView;

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
public interface IAuthViewServiceRemote extends IGenericCRUDServiceRemote<AuthView,AuthViewDTO>,
														IAbstractLgrDAOServer<AuthView>,IAbstractLgrDAOServerDTO<AuthViewDTO>{
	public List<AuthView> findByRoleID(int roleID);
}
