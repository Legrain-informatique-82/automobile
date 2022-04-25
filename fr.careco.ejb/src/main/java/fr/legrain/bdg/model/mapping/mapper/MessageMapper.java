package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.MessageDTO;
import fr.legrain.tiers.model.Message;


public class MessageMapper implements ILgrMapper<MessageDTO, Message> {

	@Override
	public Message mapDtoToEntity(MessageDTO dto, Message entity) {
		if(entity==null)
			entity = new Message();
		
		entity.setId(dto.getId());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public MessageDTO mapEntityToDto(Message entity, MessageDTO dto) {
		if(dto==null)
			dto = new MessageDTO();

		dto.setId(entity.getId());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
