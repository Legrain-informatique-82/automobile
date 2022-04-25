package fr.legrain.careco.v1.model;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.tiers.model.Adherent;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.model.UserCompany;


public class v1Mapper implements ILgrMapper<FakeV1DTO, CarPart> {
	
	
	public User mapCoreUserToUser(CoreUser v1, User entity) {
		if(entity==null)
			entity = new User();
		
		//entity.setId(v1.getId());
				
		entity.setNom(v1.getLname());
		entity.setPrenom(v1.getFname());
		entity.setEmail(v1.getEmail());
		entity.setUsername(v1.getEmail());
		entity.setPasswd(entity.passwordHashSHA256_Base64("careco"));
		
		entity.setRoles(null);
		
		entity.setUserCompany(null);
		
//		entity.setVersion(null);

		return entity;
	}
	
	public UserCompany mapCompanyToUserCompany(Company v1, UserCompany entity) {
		if(entity==null)
			entity = new UserCompany();
				
		//entity.setId(v1.getId());
		
		entity.setNom(v1.getName());
		
		entity.setActive(v1.getActive());
		entity.setAdresse1(v1.getAddress1());
		entity.setAdresse2(v1.getAddress2());
		entity.setCodePostal(v1.getZipCode());
		entity.setDepartement(v1.getDepartement());
		entity.setEmail(v1.getEmail());
		entity.setFax(v1.getFax());
		entity.setLatitude(v1.getLatitude());
		entity.setLongitude(v1.getLongitude());
		entity.setRegion(v1.getRegion());
		entity.setTelephone(v1.getTel());
		entity.setVille(v1.getCity());

		return entity;
	}
	
	public Adherent mapAdherentToAdherentCareco(AdherentV1 v1, Adherent entity) {
		if(entity==null)
			entity = new Adherent();
				
		//entity.setId(v1.getId());
		
		entity.setNom(v1.getName());
		entity.setCode(v1.getCode());
		
		entity.setActif(v1.getActive());
		entity.setAdresse1(v1.getAddress1());
		entity.setAdresse2(v1.getAddress2());
		entity.setCodePostal(v1.getZipcode());
		entity.setDateCreation(v1.getSinceDate());
		entity.setDateDernierEngagement(v1.getResignationDate());
		entity.setEmail(v1.getEmail());
		entity.setFax(v1.getFax());
		entity.setPays(v1.getCountry());
		entity.setTelephone1(v1.getPhone());
		entity.setTelephone2(v1.getPhone2());
		entity.setTva(v1.getTaxRateTypePublic());
		entity.setVille(v1.getCity());

		return entity;
	}
	
	public Stock mapCarPartToStock(CarPart v1, Stock entity) {
		if(entity==null)
			entity = new Stock();
		
		///////////////////////////////////
		entity.setDate1erMiseEnCirculation(null);
		entity.setDemonte(null);
		entity.setDisponibilite(null);
		entity.setEmplacementCasier(null);
		entity.setEmpl(null);
		entity.setExport(null);
		entity.setGarantie(null);
		//entity.setPieceLourde(null);
		entity.setPrixAchat(null);
		entity.setPrixCareco(null);
		entity.setPrixMinimum(null);
		entity.setPrixVenteConseille(null);
		entity.setVendeur(null);
		
		if(v1.getTypeId()==18) {
			entity.setTypeDePiece("Moteur");
		} else if(v1.getTypeId()==19){
			entity.setTypeDePiece("Boite");
		} else {
			entity.setTypeDePiece(null);
		}
		///////////////////////////////////

				
		entity.setRefConstructeur(v1.getPartType());
		
		entity.setCNITTypeMine(v1.getMineType());
		entity.setCodeCompatibilite(v1.getPartCode());
		entity.setKms(v1.getCarKm());
		entity.setEmplacementCasier(v1.getStorageLocation());
		entity.setCommentaireCommercial(v1.getGlobalComment());
		entity.setCommentaireInterne(v1.getInternalComment());
		
		entity.setPieceLourde(true);
		
		entity.setPrixVente(v1.getPrice());
		entity.setStatus(v1.getPartStatus());
		entity.setDateAchat(v1.getPublishDate());
		
		entity.setVendeur(v1.getLocation());
		
		entity.setImmatriculation(v1.getLicensePlate());
		entity.setVin(v1.getSerieNumber());
		
		entity.setNogo(v1.getNogo());
		entity.setDossier(v1.getNogo());
		entity.setNumLivrePolice(v1.getNogo());
		
		entity.setModele(v1.getCarModelId().getName());
		entity.setMarque(v1.getCarBrandId().getName());
		entity.setVersion(v1.getCarVersion());
		entity.setEnergie(v1.getEnergy());
		
		if(v1.getIsVisibleInStock()!=null)
			entity.setVisibleAdherent(LibConversion.intToBoolean(v1.getIsVisibleInStock()));
		
		if(v1.getIsVisibleInStock()!=null)
			entity.setVisibleStock(LibConversion.intToBoolean(v1.getIsVisibleInStock()));
		
		entity.setNumeroDeSeriePiece(v1.getPartSerialNumber());
		
		

		return entity;
	}
	

	@Override
	public CarPart mapDtoToEntity(FakeV1DTO dto, CarPart entity) {
		if(entity==null)
			entity = new CarPart();
		return entity;
	}

	@Override
	public FakeV1DTO mapEntityToDto(CarPart entity, FakeV1DTO dto) {
		if(dto==null)
			dto = new FakeV1DTO();
		return dto;
	}

}
