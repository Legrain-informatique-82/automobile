package fr.legrain.bdg.tiers.service.remote;

import javax.ejb.Remote;

//@Remote
public interface IGenericCRUDServiceRemote<Entity, DTO> extends IGenericCRUDServiceRemoteDTO<DTO>, IGenericCRUDServiceRemoteEntity<Entity>  {
	
}
