package fr.legrain.careco.dumpmc.model;

import fr.legrain.tiers.dao.IGenericDAO;
import fr.legrain.tiers.model.Stock;

//@Remote
public interface IGlobalDumpMcDAO extends IGenericDAO<StockMoteurClub> {
	
	public StockMoteurClub findWithLocalStock(Stock s);
	
}
