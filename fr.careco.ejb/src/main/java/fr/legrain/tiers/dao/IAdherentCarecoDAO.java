package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.Adherent;

public interface IAdherentCarecoDAO extends IGenericDAO<Adherent> {
	
	public List<String> selectAll(String debut);
	
	public Adherent findByIdOrigine(Integer idOrigine);
	
}
