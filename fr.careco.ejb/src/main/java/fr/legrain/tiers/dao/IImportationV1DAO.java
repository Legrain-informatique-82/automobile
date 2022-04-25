package fr.legrain.tiers.dao;

import java.util.List;

import fr.legrain.tiers.model.ImportationV1;

//@Remote
public interface IImportationV1DAO extends IGenericDAO<ImportationV1> {

	public List<ImportationV1> findByOrgine(String tableOrigine, int idOrigine);
	
	public List<ImportationV1> findByCourant(String tableCourante, int idCourant);
			
}
