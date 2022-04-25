package fr.legrain.bdg.model.mapping.mapper;

import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.tiers.dto.StockDTO;
import fr.legrain.tiers.model.ImportStockTemp;
import fr.legrain.tiers.model.Stock;


public class StockMapper implements ILgrMapper<StockDTO, Stock> {

	@Override
	public Stock mapDtoToEntity(StockDTO dto, Stock entity) {
		if(entity==null)
			entity = new Stock();
		
		entity.setId(dto.getId());
		//entity.setIdStock(dto.getIdStock());
		
		entity.setDisponibilite(dto.getDisponibilite());
		entity.setTypeDePiece(dto.getTypeDePiece());
		entity.setRefConstructeur(dto.getRefConstructeur());
		entity.setCNITTypeMine(dto.getCNITTypeMine());
		entity.setMarque(dto.getMarque());
		entity.setModele(dto.getModele());
		entity.setVersion(dto.getVersion());
		entity.setKms(dto.getKms());
		entity.setEmpl(dto.getEmpl());
		entity.setVendeur(dto.getVendeur());
		entity.setGarantie(dto.getGarantie());
		entity.setPrixCareco(dto.getPrixCareco());
		entity.setPrixVente(dto.getPrixVente());
		
		entity.setNumLivrePolice(dto.getNumLivrePolice());
		entity.setImmatriculation(dto.getImmatriculation());
		entity.setExport(dto.getExport());
		entity.setDemonte(dto.getDemonte());
		entity.setDate1erMiseEnCirculation(dto.getDate1erMiseEnCirculation());
		entity.setPrixAchat(dto.getPrixAchat());
		entity.setEmplacementCasier(dto.getEmplacementCasier());
		entity.setNumeroDeSeriePiece(dto.getNumeroDeSeriePiece());
		entity.setPrixMinimum(dto.getPrixMinimum());
		entity.setPrixVenteConseille(dto.getPrixVenteConseille());
		entity.setCommentaireCommercial(dto.getCommentaireCommercial());
		entity.setCommentaireInterne(dto.getCommentaireInterne());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public StockDTO mapEntityToDto(Stock entity, StockDTO dto) {
		if(dto==null)
			dto = new StockDTO();

		dto.setId(entity.getId());
		//dto.setIdStock(entity.getIdStock());
		
		dto.setDisponibilite(entity.getDisponibilite());
		dto.setTypeDePiece(entity.getTypeDePiece());
		dto.setRefConstructeur(entity.getRefConstructeur());
		dto.setCNITTypeMine(entity.getCNITTypeMine());
		dto.setMarque(entity.getMarque());
		dto.setModele(entity.getModele());
		dto.setVersion(entity.getVersion());
		dto.setKms(entity.getKms());
		dto.setEmpl(entity.getEmpl());
		dto.setVendeur(entity.getVendeur());
		dto.setGarantie(entity.getGarantie());
		dto.setPrixCareco(entity.getPrixCareco()); 
		dto.setPrixVente(entity.getPrixVente());
		
		dto.setNumLivrePolice(entity.getNumLivrePolice());
		dto.setImmatriculation(entity.getImmatriculation());
		dto.setExport(entity.getExport());
		dto.setDemonte(entity.getDemonte());
		dto.setDate1erMiseEnCirculation(entity.getDate1erMiseEnCirculation());
		dto.setPrixAchat(entity.getPrixAchat());
		dto.setEmplacementCasier(entity.getEmplacementCasier());
		dto.setNumeroDeSeriePiece(entity.getNumeroDeSeriePiece());
		dto.setPrixMinimum(entity.getPrixMinimum());
		dto.setPrixVenteConseille(entity.getPrixVenteConseille());
		dto.setCommentaireCommercial(entity.getCommentaireCommercial());
		dto.setCommentaireInterne(entity.getCommentaireInterne());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}
	
	public Stock mapImportStockTempToStock(ImportStockTemp tmp, Stock entity) {
		if(entity==null)
			entity = new Stock();
		
		entity.setIdStock(tmp.getIdStock());
		entity.setIdStockOrigine(tmp.getIdStockOrigine());
		entity.setDisponibilite(tmp.getDisponibilite());
		entity.setTypeDePiece(tmp.getTypeDePiece());
		entity.setRefConstructeur(tmp.getRefConstructeur());
		entity.setCodeCompatibilite(tmp.getCodeCompatibilite());
		entity.setCNITTypeMine(tmp.getCNITTypeMine());
		entity.setVin(tmp.getVin());
		entity.setMarque(tmp.getMarque());
		entity.setModele(tmp.getModele());
		entity.setVersion(tmp.getVersion());
		entity.setKms(tmp.getKms());
		entity.setEmpl(tmp.getEmpl());
		entity.setVendeur(tmp.getVendeur());
		entity.setGarantie(tmp.getGarantie());
		entity.setPrixCareco(tmp.getPrixCareco());
		entity.setPrixVente(tmp.getPrixVente());
		entity.setImmatriculation(tmp.getImmatriculation());
		entity.setNumLivrePolice(tmp.getNumLivrePolice());
		entity.setExport(tmp.getExport());
		entity.setDemonte(tmp.getDemonte());
		entity.setDate1erMiseEnCirculation(tmp.getDate1erMiseEnCirculation());
		entity.setPrixAchat(tmp.getPrixAchat());
		entity.setEmplacementCasier(tmp.getEmplacementCasier());
		entity.setNumeroDeSeriePiece(tmp.getNumeroDeSeriePiece());
		entity.setPrixMinimum(tmp.getPrixMinimum());
		entity.setPrixVenteConseille(tmp.getPrixVenteConseille());
		entity.setCommentaireInterne(tmp.getCommentaireInterne());
		entity.setCommentaireCommercial(tmp.getCommentaireCommercial());
		entity.setDossier(tmp.getDossier());
		entity.setNogo(tmp.getNogo());
		entity.setPieceLourde(tmp.getPieceLourde());
		entity.setPieceOrpheline(tmp.getPieceOrpheline());
		entity.setVisibleAdherent(tmp.getVisibleAdherent());
		entity.setVisibleStock(tmp.getVisibleStock());
		entity.setDmsRef(tmp.getDmsRef());
		entity.setEnergie(tmp.getEnergie());
		entity.setCacheAAA(tmp.getCacheAAA());
		entity.setDumpMoteurClub(tmp.getDumpMoteurClub());
		entity.setIdImportFichier(tmp.getIdImportFichier());
		entity.setImportFichier(tmp.getImportFichier());
		entity.setImportTypeFichier(tmp.getImportTypeFichier());
		entity.setStatus(tmp.getStatus());
		entity.setDateAchat(tmp.getDateAchat());

		return entity;
	}
	

}
