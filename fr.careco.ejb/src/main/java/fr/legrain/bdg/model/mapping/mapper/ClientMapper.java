package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.ClientDTO;
import fr.legrain.tiers.model.Client;


public class ClientMapper implements ILgrMapper<ClientDTO, Client> {

	@Override
	public Client mapDtoToEntity(ClientDTO dto, Client entity) {
		if(entity==null)
			entity = new Client();
		
		entity.setId(dto.getId());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public ClientDTO mapEntityToDto(Client entity, ClientDTO dto) {
		if(dto==null)
			dto = new ClientDTO();

		dto.setId(entity.getId());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
