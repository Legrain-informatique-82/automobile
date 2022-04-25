package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.GroupeEntrepriseDTO;
import fr.legrain.tiers.model.GroupeEntreprise;

@Remote

public interface IGroupeEntrepriseServiceRemote extends IGenericCRUDServiceRemote<GroupeEntreprise,GroupeEntrepriseDTO>,
														IAbstractLgrDAOServer<GroupeEntreprise>,IAbstractLgrDAOServerDTO<GroupeEntrepriseDTO>{
	
}
