package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Remote;
import javax.ejb.RemoveException;

///@Remote
public interface IGenericCRUDServiceRemoteDTO<DTO> {
	
	public void persistDTO(DTO transientInstance) throws CreateException;
	public void removeDTO(DTO persistentInstance) throws RemoveException;
	public void mergeDTO(DTO detachedInstance) throws EJBException;
	
	public List<DTO> findWithNamedQueryDTO(String queryName) throws FinderException;
	public List<DTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException;
	
	public List<DTO> selectAllDTO();
	
	public DTO findByIdDTO(int id) throws FinderException;
	public DTO findByCodeDTO(String code) throws FinderException;
	
	public abstract void validateDTO(DTO dto);
	public abstract void validateDTOProperty(DTO dto, String propertyName);
	
	public int selectCount();
	
	public void error(DTO dto);
}
