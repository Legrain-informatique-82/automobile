package fr.legrain.bdg.tiers.service.remote;

import java.util.List;

import javax.ejb.Remote;

import fr.legrain.tiers.dto.StockDTO;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.UserCompany;

@Remote

public interface IStockServiceRemote extends IGenericCRUDServiceRemote<Stock,StockDTO>,
														IAbstractLgrDAOServer<Stock>,IAbstractLgrDAOServerDTO<StockDTO>{
	
	static final public String STATUS_PIECE_DISPONIBLE = "available";
	static final public String STATUS_PIECE_PANIER = "panier";
	static final public String STATUS_PIECE_TRANSACTION = "transaction";
	static final public String STATUS_PIECE_DESTOCKER = "destocked";
	static final public String STATUS_PIECE_VENDUE_CLIENT = "vendu";
	static final public String STATUS_PIECE_SUPPRIMEE = "supprime";
	
	public Stock changeProprio(Stock detachedInstance, UserCompany nouveau);
	
	public List<Stock> findByRefConstructeur(String refConstructeur, String typePiece);
	public List<Stock> findByUserStock(int idStock);
	public List<Stock> findByUserStock(int idStock, List<String> listeStatusPiece);
	
	public List<Stock> findByUserEtPieceStock(int idStock, String refConstructeur, String typePiece);
	public List<Stock> findByMultiCompanyEtPieceStock(List<Integer> idStock, String refConstructeur, String typePiece);
	public List<Stock> findByUserEtPieceStock(int idStock, List<String> refsConstructeur, String typePiece);
	public List<Stock> findByMultiCompanyEtPieceStock(List<Integer> idStock, List<String> refsConstructeur, String typePiece);
	public List<Stock> findByUserEtCasier(int idStock, String casier);
	
	public List<Stock> findByGroupeEntrepiseEtPieceStock(int idGroupeEntrepise, String refConstructeur, String typePiece);
	public List<Stock> findByGroupeEntrepiseEtPieceStock(int idGroupeEntrepise, List<String> refsConstructeur, String typePiece);
	
	public List<Stock> findByMarqueModeleAnnee(int idStock, String typePiece, String marque, String modele, String anneeMin, String anneeMax);
	
	public List<Stock> findByNumLivrePolice(int idStock,String numLivrePolice);
	public List<Stock> findByVin(String vin);
	public List<Stock> findByCNIT(String cnit);
	
	public List<String> selectAllCNIT(int idGroupeEntrepise, int idStock, String debut);
	public List<String> selectAllVIN(int idGroupeEntrepise, int idStock, String debut);
	public List<String> selectAllLivrePolice(int idStock, String debut);
	public List<String> selectAllTypeMoteur(int idStock, String debut);
	public List<String> selectAllTypeBoite(int idStock, String debut);
	public List<String> selectAllCasier(int idStock, String debut);
	
	public List<Stock> findDuplicate(int idStock);
}
