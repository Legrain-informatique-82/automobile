package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.AdherentCarecoDTO;
import fr.legrain.tiers.model.Adherent;

@Remote

public interface IAdherentCarecoServiceRemote extends IGenericCRUDServiceRemote<Adherent,AdherentCarecoDTO>,
														IAbstractLgrDAOServer<Adherent>,IAbstractLgrDAOServerDTO<AdherentCarecoDTO>{
	
	public List<String> selectAll(String debut);
}
