package fr.legrain.bdg.tiers.service.remote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.FicheCarecoDTO;
import fr.legrain.tiers.model.FicheCareco;

@Remote
public interface IFicheCarecoServiceRemote extends IGenericCRUDServiceRemote<FicheCareco,FicheCarecoDTO>,
														IAbstractLgrDAOServer<FicheCareco>,IAbstractLgrDAOServerDTO<FicheCarecoDTO>{
	public List<FicheCareco> findByCNIT_TypeCG(String typeCG, Date date1ereMiseEnCirculation);
}
