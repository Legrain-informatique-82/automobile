package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.LignePanierDTO;
import fr.legrain.tiers.model.LignePanier;

@Remote

public interface ILignePanierServiceRemote extends IGenericCRUDServiceRemote<LignePanier,LignePanierDTO>,
														IAbstractLgrDAOServer<LignePanier>,IAbstractLgrDAOServerDTO<LignePanierDTO>{
}
