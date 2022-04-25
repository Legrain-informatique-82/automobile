package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.RoleDTO;
import fr.legrain.tiers.model.Role;


public class RoleMapper implements ILgrMapper<RoleDTO, Role> {

	@Override
	public Role mapDtoToEntity(RoleDTO dto, Role entity) {
		if(entity==null)
			entity = new Role();
		
	
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public RoleDTO mapEntityToDto(Role entity, RoleDTO dto) {
		if(dto==null)
			dto = new RoleDTO();
		

//		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
