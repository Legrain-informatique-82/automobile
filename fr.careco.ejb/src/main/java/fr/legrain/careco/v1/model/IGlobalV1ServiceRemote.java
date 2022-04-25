package fr.legrain.careco.v1.model;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServer;
import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServerDTO;
import fr.legrain.bdg.tiers.service.remote.IGenericCRUDServiceRemote;

@Remote
public interface IGlobalV1ServiceRemote extends IGenericCRUDServiceRemote<CarPart,FakeV1DTO>,
														IAbstractLgrDAOServer<CarPart>,IAbstractLgrDAOServerDTO<FakeV1DTO>{
	
}
