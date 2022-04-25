package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.AnnuaireDTO;
import fr.legrain.tiers.model.Annuaire;

@Remote

public interface IAnnuaireServiceRemote extends IGenericCRUDServiceRemote<Annuaire,AnnuaireDTO>,
														IAbstractLgrDAOServer<Annuaire>,IAbstractLgrDAOServerDTO<AnnuaireDTO>{
	
	public List<String> selectAll(String debut);
}
