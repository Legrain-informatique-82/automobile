package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.FicheCarecoDTO;
import fr.legrain.tiers.model.FicheCareco;


public class FicheCarecoMapper implements ILgrMapper<FicheCarecoDTO, FicheCareco> {

	@Override
	public FicheCareco mapDtoToEntity(FicheCarecoDTO dto, FicheCareco entity) {
		if(entity==null)
			entity = new FicheCareco();
		
//		entity.setFicheCareconame(dto.getFicheCareconame());

//		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public FicheCarecoDTO mapEntityToDto(FicheCareco entity, FicheCarecoDTO dto) {
		if(dto==null)
			dto = new FicheCarecoDTO();
		
//		dto.setFicheCareconame(entity.getFicheCareconame());

//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
