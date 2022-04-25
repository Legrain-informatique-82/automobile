package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.Annuaire;

public interface IAnnuaireDAO extends IGenericDAO<Annuaire> {
	
	public List<String> selectAll(String debut);
	
}
