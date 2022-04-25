package fr.legrain.bdg.tiers.service.local;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.RemoveException;

import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServer;
import fr.legrain.bdg.tiers.service.remote.IAbstractLgrDAOServerDTO;
import fr.legrain.bdg.tiers.service.remote.IGenericCRUDServiceRemote;
import fr.legrain.tiers.dto.StockDTO;
import fr.legrain.tiers.model.ImportStockTemp;
import fr.legrain.tiers.model.Stock;

@Local

public interface IStockServiceLocal extends IGenericCRUDServiceRemote<Stock,StockDTO>,
														IAbstractLgrDAOServer<Stock>,IAbstractLgrDAOServerDTO<StockDTO>{
	
	public List<Stock> findByRefConstructeur(String refConstructeur, String typePiece);
	public List<Stock> findByUserStock(int idStock);
	public List<Stock> findByUserStock(int idStock, List<String> listeStatusPiece);
	
	public List<Stock> findByUserEtPieceStock(int idStock, String refConstructeur, String typePiece);
	public List<Stock> findByMultiCompanyEtPieceStock(List<Integer> idStock, String refConstructeur, String typePiece);
	public List<Stock> findByUserEtPieceStock(int idStock, List<String> refsConstructeur, String typePiece);
	public List<Stock> findByMultiCompanyEtPieceStock(List<Integer> idStock, List<String> refsConstructeur, String typePiece);
	
	public List<Stock> findByGroupeEntrepiseEtPieceStock(int idGroupeEntrepise, String refConstructeur, String typePiece);
	public List<Stock> findByGroupeEntrepiseEtPieceStock(int idGroupeEntrepise, List<String> refsConstructeur, String typePiece);
	
	public List<Stock> findByMarqueModeleAnnee(int idStock, String typePiece,String marque, String modele, String anneeMin, String anneeMax);
	
	public List<Stock> findByNumLivrePolice(int idStock,String numLivrePolice);
	public List<Stock> findByVin(String vin);
	public List<Stock> findByCNIT(String cnit);
	
	public List<String> selectAllCNIT(int idGroupeEntrepise, int idStock, String debut);
	public List<String> selectAllVIN(int idGroupeEntrepise, int idStock, String debut);
	public List<String> selectAllLivrePolice(int idStock, String debut);
	public List<String> selectAllTypeMoteur(int idStock, String debut);
	public List<String> selectAllTypeBoite(int idStock, String debut);
	public List<String> selectAllCasier(int idStock, String debut);
	
	//Importation
	public void removeImportStock(ImportStockTemp persistentInstance) throws RemoveException;
	public ImportStockTemp mergeImportStock(ImportStockTemp detachedInstance);
	public void validerImportationStock(int idCompany, boolean effacerAvantImport) throws Exception;
	public void annulerImportationStock(int idCompany);
}
