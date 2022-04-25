package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.UserDTO;
import fr.legrain.tiers.model.User;


public class UserMapper implements ILgrMapper<UserDTO, User> {

	@Override
	public User mapDtoToEntity(UserDTO dto, User entity) {
		if(entity==null)
			entity = new User();
		
		entity.setUsername(dto.getUsername());

//		entity.setIdTCivilite(dto.getId()!=null?dto.getId():0);
//		entity.setCodeTCivilite(dto.getCodeTCivilite());
//		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public UserDTO mapEntityToDto(User entity, UserDTO dto) {
		if(dto==null)
			dto = new UserDTO();
		
		dto.setUsername(entity.getUsername());

//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
