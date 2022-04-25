package fr.careco.blueway.ws.model;

import fr.careco.aaa.ws.Vehicule;
import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.lib.data.LibConversion;


public class AutorisationB2BMapper implements ILgrMapper<AutorisationB2BDTO, AutorisationB2B> {

	@Override
	public AutorisationB2B mapDtoToEntity(AutorisationB2BDTO dto, AutorisationB2B entity) {
		if(entity==null)
			entity = new AutorisationB2B();
		
//		entity.setAutorisationB2Bname(dto.getAutorisationB2Bname());

		return entity;
	}

	@Override
	public AutorisationB2BDTO mapEntityToDto(AutorisationB2B entity, AutorisationB2BDTO dto) {
		if(dto==null)
			dto = new AutorisationB2BDTO();
		
//		dto.setAutorisationB2Bname(entity.getAutorisationB2Bname());

		return dto;
	}
	
	public AutorisationB2B mapWSToEntity(Vehicule ws, AutorisationB2B entity) {
		if(entity==null)
			entity = new AutorisationB2B();
				
		//entity.setCode_Immat(null);
		
		//entity.setVersion(null);

		return entity;
	}

}
