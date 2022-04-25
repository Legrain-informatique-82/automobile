package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.PanierDTO;
import fr.legrain.tiers.model.Panier;


public class PanierMapper implements ILgrMapper<PanierDTO, Panier> {

	@Override
	public Panier mapDtoToEntity(PanierDTO dto, Panier entity) {
		if(entity==null)
			entity = new Panier();
		
		entity.setId(dto.getId());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public PanierDTO mapEntityToDto(Panier entity, PanierDTO dto) {
		if(dto==null)
			dto = new PanierDTO();

		dto.setId(entity.getId());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
