package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.FinderException;
import javax.ejb.Remote;
import javax.ejb.RemoveException;

import fr.legrain.tiers.dto.UserCompanyDTO;
import fr.legrain.tiers.model.SocietesAmies;
import fr.legrain.tiers.model.UserCompany;

@Remote
public interface IUserCompanyServiceRemote extends IGenericCRUDServiceRemote<UserCompany,UserCompanyDTO>,
														IAbstractLgrDAOServer<UserCompany>,IAbstractLgrDAOServerDTO<UserCompanyDTO>{
	
	static public String TYPE_SOCIETE_AMIE_AMIE = "AMIE";
	static public String TYPE_SOCIETE_AMIE_MULTISITE = "MULTISITE";
	
	public SocietesAmies findByIdSocieteAmie(int idSocietesAmies) throws FinderException;
	public List<SocietesAmies> findSocieteAmie(int idUserCompany);
	public List<SocietesAmies> findSocieteAmieAjoutee(int idUserCompany);
	public List<SocietesAmies> findSocieteMultiSite(int idUserCompany);
	public void removeSocietesAmies(SocietesAmies persistentInstance) throws RemoveException;
	public SocietesAmies mergeSocietesAmies(SocietesAmies detachedInstance);
	public SocietesAmies persistSocietesAmies(SocietesAmies detachedInstance);
	
}
