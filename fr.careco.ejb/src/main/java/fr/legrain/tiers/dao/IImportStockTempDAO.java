package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.ImportStockTemp;

public interface IImportStockTempDAO extends IGenericDAO<ImportStockTemp> {

	public List<ImportStockTemp> findByUserImportStockTemp(int idStock);
	
	public void deleteByUserImportStockTemp(int idStock);
}
