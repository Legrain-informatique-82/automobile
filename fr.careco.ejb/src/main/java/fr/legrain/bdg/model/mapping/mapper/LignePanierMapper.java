package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.LignePanierDTO;
import fr.legrain.tiers.model.LignePanier;


public class LignePanierMapper implements ILgrMapper<LignePanierDTO, LignePanier> {

	@Override
	public LignePanier mapDtoToEntity(LignePanierDTO dto, LignePanier entity) {
		if(entity==null)
			entity = new LignePanier();
		
		entity.setId(dto.getId());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public LignePanierDTO mapEntityToDto(LignePanier entity, LignePanierDTO dto) {
		if(dto==null)
			dto = new LignePanierDTO();

		dto.setId(entity.getId());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
