package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.AuthURLDTO;
import fr.legrain.tiers.model.AuthURL;


public class AuthURLMapper implements ILgrMapper<AuthURLDTO, AuthURL> {

	@Override
	public AuthURL mapDtoToEntity(AuthURLDTO dto, AuthURL entity) {
		if(entity==null)
			entity = new AuthURL();
		
		entity.setUrl(dto.getUrl());

//		entity.setIdTCivilite(dto.getId()!=null?dto.getId():0);
//		entity.setCodeTCivilite(dto.getCodeTCivilite());
//		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public AuthURLDTO mapEntityToDto(AuthURL entity, AuthURLDTO dto) {
		if(dto==null)
			dto = new AuthURLDTO();
		
		dto.setUrl(entity.getUrl());

//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
