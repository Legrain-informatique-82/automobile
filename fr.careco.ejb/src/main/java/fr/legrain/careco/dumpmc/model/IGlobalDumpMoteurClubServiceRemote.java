package fr.legrain.careco.dumpmc.model;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServer;
import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServerDTO;
import fr.legrain.bdg.tiers.service.remote.IGenericCRUDServiceRemote;

@Remote
public interface IGlobalDumpMoteurClubServiceRemote extends IGenericCRUDServiceRemote<StockMoteurClub,FakeMoteurClubDTO>,
														IAbstractLgrDAOServer<StockMoteurClub>,IAbstractLgrDAOServerDTO<FakeMoteurClubDTO>{
	
}
