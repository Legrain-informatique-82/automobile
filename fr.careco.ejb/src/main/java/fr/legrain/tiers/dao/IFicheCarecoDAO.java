package fr.legrain.tiers.dao;

import java.util.Date;
import java.util.List;

import fr.legrain.tiers.model.FicheCareco;

//@Remote
public interface IFicheCarecoDAO extends IGenericDAO<FicheCareco> {
	public List<FicheCareco> findByCNIT_TypeCG(String typeCG, Date date1ereMiseEnCirculation);
}
