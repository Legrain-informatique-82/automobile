package fr.legrain.tiers.dao;

import java.math.BigDecimal;

import fr.legrain.tiers.model.GarantieCareco;

public interface IGarantieCarecoDAO extends IGenericDAO<GarantieCareco> {
	
	public GarantieCareco findGarantieCarecoByMontant(BigDecimal prix, Integer duree);
}
