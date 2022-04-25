package fr.careco.blueway.ws.model;

import fr.careco.aaa.ws.Vehicule;
import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.lib.data.LibConversion;


public class LogAppelB2BMapper implements ILgrMapper<LogAppelB2BDTO, LogAppelB2B> {

	@Override
	public LogAppelB2B mapDtoToEntity(LogAppelB2BDTO dto, LogAppelB2B entity) {
		if(entity==null)
			entity = new LogAppelB2B();
		
//		entity.setLogAppelB2Bname(dto.getLogAppelB2Bname());

		return entity;
	}

	@Override
	public LogAppelB2BDTO mapEntityToDto(LogAppelB2B entity, LogAppelB2BDTO dto) {
		if(dto==null)
			dto = new LogAppelB2BDTO();
		
//		dto.setLogAppelB2Bname(entity.getLogAppelB2Bname());

		return dto;
	}
	
	public LogAppelB2B mapWSToEntity(Vehicule ws, LogAppelB2B entity) {
		if(entity==null)
			entity = new LogAppelB2B();
				
		//entity.setCode_Immat(null);
		
		//entity.setVersion(null);

		return entity;
	}

}
