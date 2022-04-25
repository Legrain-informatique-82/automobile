package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.TransactionAchatVenteDTO;
import fr.legrain.tiers.model.TransactionAchatVente;


public class TransactionAchatVenteMapper implements ILgrMapper<TransactionAchatVenteDTO, TransactionAchatVente> {

	@Override
	public TransactionAchatVente mapDtoToEntity(TransactionAchatVenteDTO dto, TransactionAchatVente entity) {
		if(entity==null)
			entity = new TransactionAchatVente();
		
		entity.setId(dto.getId());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public TransactionAchatVenteDTO mapEntityToDto(TransactionAchatVente entity, TransactionAchatVenteDTO dto) {
		if(dto==null)
			dto = new TransactionAchatVenteDTO();

		dto.setId(entity.getId());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
