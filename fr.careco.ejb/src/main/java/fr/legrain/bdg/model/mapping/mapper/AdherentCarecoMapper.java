package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.AdherentCarecoDTO;
import fr.legrain.tiers.model.Adherent;


public class AdherentCarecoMapper implements ILgrMapper<AdherentCarecoDTO, Adherent> {

	@Override
	public Adherent mapDtoToEntity(AdherentCarecoDTO dto, Adherent entity) {
		if(entity==null)
			entity = new Adherent();
		
		entity.setId(dto.getId());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public AdherentCarecoDTO mapEntityToDto(Adherent entity, AdherentCarecoDTO dto) {
		if(dto==null)
			dto = new AdherentCarecoDTO();

		dto.setId(entity.getId());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
