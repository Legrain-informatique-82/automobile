package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.UserCompanyDTO;
import fr.legrain.tiers.model.UserCompany;


public class UserCompanyMapper implements ILgrMapper<UserCompanyDTO, UserCompany> {

	@Override
	public UserCompany mapDtoToEntity(UserCompanyDTO dto, UserCompany entity) {
		if(entity==null)
			entity = new UserCompany();
		
		entity.setId(dto.getId());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public UserCompanyDTO mapEntityToDto(UserCompany entity, UserCompanyDTO dto) {
		if(dto==null)
			dto = new UserCompanyDTO();

		dto.setId(entity.getId());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
