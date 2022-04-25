package fr.legrain.bdg.model.mapping.mapper;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.GroupeEntrepriseDTO;
import fr.legrain.tiers.model.GroupeEntreprise;


public class GroupeEntrepriseMapper implements ILgrMapper<GroupeEntrepriseDTO, GroupeEntreprise> {

	@Override
	public GroupeEntreprise mapDtoToEntity(GroupeEntrepriseDTO dto, GroupeEntreprise entity) {
		if(entity==null)
			entity = new GroupeEntreprise();
		
		entity.setId(dto.getId());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public GroupeEntrepriseDTO mapEntityToDto(GroupeEntreprise entity, GroupeEntrepriseDTO dto) {
		if(dto==null)
			dto = new GroupeEntrepriseDTO();

		dto.setId(entity.getId());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}

}
