package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.AuthViewDTO;
import fr.legrain.tiers.model.AuthView;


public class AuthViewMapper implements ILgrMapper<AuthViewDTO, AuthView> {

	@Override
	public AuthView mapDtoToEntity(AuthViewDTO dto, AuthView entity) {
		if(entity==null)
			entity = new AuthView();
		
		entity.setUrl(dto.getUrl());

//		entity.setIdTCivilite(dto.getId()!=null?dto.getId():0);
//		entity.setCodeTCivilite(dto.getCodeTCivilite());
//		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public AuthViewDTO mapEntityToDto(AuthView entity, AuthViewDTO dto) {
		if(dto==null)
			dto = new AuthViewDTO();
		
		dto.setUrl(entity.getUrl());

//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
