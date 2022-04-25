package fr.legrain.careco.v1.model;

import fr.careco.aaa.ws.Vehicule;
import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.tiers.model.User;


public class UserV1Mapper implements ILgrMapper<FakeV1DTO, User> {

	@Override
	public User mapDtoToEntity(FakeV1DTO dto, User entity) {
		if(entity==null)
			entity = new User();
		
//		entity.setUsername(dto.getUsername());

		return entity;
	}

	@Override
	public FakeV1DTO mapEntityToDto(User entity, FakeV1DTO dto) {
		if(dto==null)
			dto = new FakeV1DTO();
		
//		dto.setUsername(entity.getUsername());

		return dto;
	}
	
	public User mapWSToEntity(Vehicule ws, User entity) {
		if(entity==null)
			entity = new User();
				
		//entity.setCode_Immat(null);
		
//		entity.setVersion(null);

		return entity;
	}

}
