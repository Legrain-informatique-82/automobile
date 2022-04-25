package fr.legrain.careco.dumpmc.model;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.tiers.model.Adherent;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;


public class DumpMoteurClubMapper implements ILgrMapper<FakeMoteurClubDTO, StockMoteurClub> {

	@Override
	public StockMoteurClub mapDtoToEntity(FakeMoteurClubDTO dto, StockMoteurClub entity) {
		if(entity==null)
			entity = new StockMoteurClub();
		return entity;
	}

	@Override
	public FakeMoteurClubDTO mapEntityToDto(StockMoteurClub entity, FakeMoteurClubDTO dto) {
		if(dto==null)
			dto = new FakeMoteurClubDTO();
		return dto;
	}

}
