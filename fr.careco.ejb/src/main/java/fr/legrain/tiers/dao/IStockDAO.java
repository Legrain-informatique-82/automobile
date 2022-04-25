package fr.legrain.tiers.dao;

import java.util.List;

import javax.persistence.Query;

import fr.legrain.tiers.model.Stock;

public interface IStockDAO extends IGenericDAO<Stock> {
	
	public static final String TYPE_PIECE_MOTEUR = "Moteur";
	public static final String TYPE_PIECE_BOITE_VITESSE = "Boite";
	
	public List<Stock> findByRefConstructeur(String refConstructeur, String typePiece);
	public List<Stock> findByUserStock(int idStock);
	public List<Stock> findByUserStock(int idStock, List<String> listeStatusPiece);
	public List<Stock> findByUserEtPieceStock(int idStock, List<String> refsConstructeur, String typePiece);
	public List<Stock> findByMultiCompanyEtPieceStock(List<Integer> idStock, List<String> refsConstructeur, String typePiece);
	public List<Stock> findByUserEtPieceStock(int idStock, String refConstructeur, String typePiece);
	public List<Stock> findByMultiCompanyEtPieceStock(List<Integer> idStock, String refConstructeur, String typePiece);
	public List<Stock> findByGroupeEntrepiseEtPieceStock(int idGroupeEntrepise, String refConstructeur, String typePiece);
	public List<Stock> findByGroupeEntrepiseEtPieceStock(int idGroupeEntrepise, List<String> refsConstructeur, String typePiece);
	public List<Stock> findByUserEtCasier(int idStock, String casier);
	
	public List<Stock> findByMarqueModeleAnnee(int idStock, String typePiece, String marque, String modele, String anneeMin, String anneeMax);
	
	public List<Stock> findByNumLivrePolice(int idStock, String numLivrePolice);
	public List<Stock> findByVin(String vin);
	public List<Stock> findByCNIT(String cnit);
	
	public List<String> selectAllCNIT(int idGroupeEntrepise, int idStock, String debut);
	public List<String> selectAllVIN(int idGroupeEntrepise, int idStock, String debut);
	public List<String> selectAllLivrePolice(int idStock, String debut);
	public List<String> selectAllTypeMoteur(int idStock, String debut);
	public List<String> selectAllTypeBoite(int idStock, String debut);
	public List<String> selectAllCasier(int idStock, String debut);
	
	public Stock findByIdOrigine(Integer idOrigine);
	public Stock findByIdOrigine(Integer idOrigine, String nomTableOrigine);
	public Stock findByIdFichierOrigine(int idStock, Integer idOrigine, String typeFichierOrigine);
	
	//maintenance
	public void deleteByUserStock(int idStock);
	public List<Stock> findDuplicate(int idStock);

}
