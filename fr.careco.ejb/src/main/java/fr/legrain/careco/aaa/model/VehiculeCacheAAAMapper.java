package fr.legrain.careco.aaa.model;

//import fr.careco.aaa.ws.Vehicule;
import fr.asso.aaa.sivin.xsd.Vehicule;
import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.lib.data.LibConversion;


public class VehiculeCacheAAAMapper implements ILgrMapper<VehiculeCacheAAADTO, VehiculeCacheAAA> {

	@Override
	public VehiculeCacheAAA mapDtoToEntity(VehiculeCacheAAADTO dto, VehiculeCacheAAA entity) {
		if(entity==null)
			entity = new VehiculeCacheAAA();
		
//		entity.setVehiculeCacheAAAname(dto.getVehiculeCacheAAAname());

		return entity;
	}

	@Override
	public VehiculeCacheAAADTO mapEntityToDto(VehiculeCacheAAA entity, VehiculeCacheAAADTO dto) {
		if(dto==null)
			dto = new VehiculeCacheAAADTO();
		
//		dto.setVehiculeCacheAAAname(entity.getVehiculeCacheAAAname());

		return dto;
	}
	
	public VehiculeCacheAAA mapWSToEntity(Vehicule ws, VehiculeCacheAAA entity) {
		if(entity==null)
			entity = new VehiculeCacheAAA();
				
		//entity.setCode_Immat(null);
		
		entity.setCarrosserie(ws.getCarrosserie());
		entity.setCarrosserie_CG(ws.getCarrosserieCG());
		entity.setCO2(LibConversion.stringToInteger(ws.getCo2()));
		entity.setCode_Moteur(ws.getCodeMoteur());
		entity.setCodif_Vin_Pref(ws.getCodifVin());
		entity.setCons_Exurb(ws.getConsExurb());
		entity.setCons_Mixte(ws.getConsMixte());
		entity.setCons_Urb(ws.getConsUrb());
		entity.setCouleur_Vehic(ws.getCouleurVehic());
		entity.setCylindree(LibConversion.stringToInteger(ws.getCylindree()));
		entity.setDate_1er_Cir_Annee(LibConversion.integerToString(ws.getDate1ErCir().getAnnee().getYear()));
		entity.setDate_1er_Cir_Jour(LibConversion.integerToString(ws.getDate1ErCir().getJour().getDay()));
		entity.setDate_1er_Cir_Mois(LibConversion.integerToString(ws.getDate1ErCir().getMois().getMonth()));
		entity.setDate_Dcg_Annee(LibConversion.integerToString(ws.getDateDCG().getAnnee().getYear())); //////////DCG ?
		entity.setDate_Dcg_Jour(LibConversion.integerToString(ws.getDateDCG().getJour().getDay())); //////////DCG ?
		entity.setDate_Dcg_Mois(LibConversion.integerToString(ws.getDateDCG().getMois().getMonth())); //////////DCG ?
		//entity.setDateMAJ(ws.getDate); ////////////////////////////////////////////////////// ?
		entity.setDepollution(ws.getDepollution());
		entity.setEmpat(LibConversion.stringToInteger(ws.getEmpat()));
		entity.setEnergie(ws.getEnergie());
		entity.setGenre(ws.getGenreV());
		entity.setGenre_V_CG(ws.getGenreVCG());
		entity.setHauteur(LibConversion.stringToInteger(ws.getHauteur()));
		entity.setImmat_SIV(ws.getImmatSiv()); ///////////////////////////////////////////////////ou ws.getImmatSivPr()
		entity.setLargeur(LibConversion.stringToInteger(ws.getLargeur()));
		entity.setLongueur(LibConversion.stringToInteger(ws.getLongueur()));
		entity.setMarque(ws.getMarque());
		entity.setMarque_Carros(ws.getMarqueCarros());
		entity.setMode_Inject(ws.getModeInject());
		entity.setModele(ws.getModele());
		entity.setModele_Etude(ws.getModeleEtude());
		entity.setModele_Prf(ws.getModelePrf());
		entity.setN_Serie(ws.getNSerie());
		entity.setNb_Cylind(LibConversion.stringToInteger(ws.getNbCylind()));
		entity.setNb_Pl_Ass(LibConversion.stringToInteger(ws.getNbPlAss()));
		entity.setNb_Portes(LibConversion.stringToInteger(ws.getNbPortes()));
		entity.setNb_Soupapes(LibConversion.stringToInteger(ws.getNbSoupape()));
		entity.setNb_Vitesse(LibConversion.stringToInteger(ws.getNbVitesse()));
		entity.setNb_Volumes(LibConversion.stringToInteger(ws.getNbVolume()));
		if(ws.getProprietaireBrut()!=null) {
			entity.setNom_proprio(ws.getProprietaireBrut().getNom());////////////////////////////////////////////////////// ?
		}
		entity.setPoids_Vide(LibConversion.stringToInteger(ws.getPoidsVide()));
		entity.setPrix_Vehic(ws.getPrixVehic());
		entity.setPropulsion(ws.getPropulsion());
		entity.setPtr(LibConversion.stringToInteger(ws.getPtr()));
		entity.setPtr_Prf(LibConversion.stringToInteger(ws.getPtrPrf()));
		entity.setPuis_Ch(LibConversion.stringToInteger(ws.getPuisCh()));
		entity.setPuis_Fisc(LibConversion.stringToInteger(ws.getPuisFisc()));
		entity.setPuis_Kw(ws.getPuisKw());
		entity.setTp_Boite_Vit(ws.getTpBoiteVit());
		entity.setTurbo_Compr(ws.getTurboCompr());
		entity.setType(ws.getType());
		entity.setType_Prf(ws.getTypePrf());
		entity.setType_Var_Ver_Prf(ws.getTypeVarVersPrf());
		entity.setType_Vin_Cg(ws.getTypeVinCG());
		
		entity.setSiren(ws.getNSiren());
		entity.setType_Var_Ver_Prf(ws.getTypeVarVersPrf());
		entity.setPrix_Vehic(ws.getPrixVehic());
		
		if(ws.getPneus()!=null) {
			String listeDimension = "";
			boolean first = true;
			for (String d : ws.getPneus().getDimension()) {
				if(first) {
					listeDimension += d;
					first = false;
				} else {
					listeDimension += "|"+d;
				}
				
			} 
			entity.setPneu(listeDimension);
		}
		
		entity.setkType(ws.getKType());
		entity.setVersion(ws.getVersion());

		return entity;
	}

}
