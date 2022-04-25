package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.AnnuaireDTO;
import fr.legrain.tiers.model.Annuaire;


public class AnnuaireMapper implements ILgrMapper<AnnuaireDTO, Annuaire> {

	@Override
	public Annuaire mapDtoToEntity(AnnuaireDTO dto, Annuaire entity) {
		if(entity==null)
			entity = new Annuaire();
		
		entity.setId(dto.getId());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public AnnuaireDTO mapEntityToDto(Annuaire entity, AnnuaireDTO dto) {
		if(dto==null)
			dto = new AnnuaireDTO();

		dto.setId(entity.getId());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
