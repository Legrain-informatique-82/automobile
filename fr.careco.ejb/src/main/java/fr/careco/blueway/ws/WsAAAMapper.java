package fr.careco.blueway.ws;

import fr.legrain.bdg.model.mapping.ILgrMapper;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.tiers.dto.AdherentCarecoDTO;
import fr.legrain.tiers.model.Adherent;


public class WsAAAMapper implements ILgrMapper<AdherentCarecoDTO, Adherent> {

	@Override
	public Adherent mapDtoToEntity(AdherentCarecoDTO dto, Adherent entity) {
		if(entity==null)
			entity = new Adherent();
		
		entity.setId(dto.getId());
		
//		entity.setVersionObj(dto.getVersionObj());

		return entity;
	}

	@Override
	public AdherentCarecoDTO mapEntityToDto(Adherent entity, AdherentCarecoDTO dto) {
		if(dto==null)
			dto = new AdherentCarecoDTO();

		dto.setId(entity.getId());
		
//		dto.setVersionObj(entity.getVersionObj());

		return dto;
	}
	
	public fr.careco.blueway.ws.b2b.VarAAARetourWs mapCacheAAAtoVarAAARetourWsB2B(VehiculeCacheAAA ws) {
		fr.careco.blueway.ws.b2b.VarAAARetourWs var = null;
		if(var==null)
			var = new fr.careco.blueway.ws.b2b.VarAAARetourWs();
								
		var.setTurboCompr(ws.getTurbo_Compr());
	    var.setPuisKw(ws.getPuis_Kw());
	    var.setTPBoiteVit(ws.getTp_Boite_Vit());
	    var.setPuisCh(LibConversion.integerToString(ws.getPuis_Ch()));
	    var.setPuisFisc(LibConversion.integerToString(ws.getPuis_Fisc()));
	    var.setPTRPRF(LibConversion.integerToString(ws.getPtr_Prf()));
	    var.setPTR(LibConversion.integerToString(ws.getPtr()));
	    var.setPropulsion(ws.getPropulsion());
	    var.setPrixVehic(ws.getPrix_Vehic());
	    var.setNSerie(ws.getN_Serie());
	    var.setPoidsVide(LibConversion.integerToString(ws.getPoids_Vide()));
	    var.setNBVolume(LibConversion.integerToString(ws.getNb_Volumes()));
	    var.setNBVitesse(LibConversion.integerToString(ws.getNb_Vitesse()));
	    var.setNBSoupapes(LibConversion.integerToString(ws.getNb_Soupapes()));
	    var.setNBPortes(LibConversion.integerToString(ws.getNb_Portes()));
	    var.setNBPLAss(LibConversion.integerToString(ws.getNb_Pl_Ass()));
	    var.setNBCylind(LibConversion.integerToString(ws.getNb_Cylind()));
	    var.setModeInject(ws.getMode_Inject());
	    var.setModelePRF(ws.getModele_Prf());
	    var.setModele(ws.getModele());
	    var.setModeleEtude(ws.getModele_Etude());
	    var.setMarqueCarros(ws.getMarque_Carros());
	    var.setMarque(ws.getMarque());
	    var.setLargeur(LibConversion.integerToString(ws.getLargeur()));
	    var.setLongueur(LibConversion.integerToString(ws.getLongueur()));
	    var.setImmatSIV(ws.getImmat_SIV());
	    var.setHauteur(LibConversion.integerToString(ws.getHauteur()));
	    var.setGenreV(ws.getGenre());
	    var.setGenreVCG(ws.getGenre_V_CG());
	    var.setEnergie(ws.getEnergie());
	    var.setEmpat(LibConversion.integerToString(ws.getEmpat()));
	    var.setDepollution(ws.getDepollution());
	    var.setDateDcgMois(ws.getDate_Dcg_Mois());
	    var.setDateDcgJour(ws.getDate_Dcg_Jour());
	    var.setDateDCGAnnee(ws.getDate_Dcg_Annee());
	    var.setDate1ErCirMois(ws.getDate_1er_Cir_Mois());
	    var.setDate1ErCIRAnnee(ws.getDate_1er_Cir_Annee());
	    var.setDate1ErCirJour(ws.getDate_1er_Cir_Jour());
	    var.setCylindree(LibConversion.integerToString(ws.getCylindree()));
	    var.setCouleurVehic(ws.getCouleur_Vehic());
	    var.setConsUrb(ws.getCons_Urb());
	    var.setConsMixte(ws.getCons_Mixte());
	    var.setConsExurb(ws.getCons_Exurb());
	    var.setCodifVinPRF(ws.getCodif_Vin_Pref());
	    var.setCodeMoteur(ws.getCode_Moteur());
	    var.setCO2(LibConversion.integerToString(ws.getCO2()));
	    var.setCarrosserieCG(ws.getCarrosserie_CG());
	    var.setCarrosserie(ws.getCarrosserie());
	    var.setType(ws.getType());
	    var.setTypePrf(ws.getType_Prf());
	    var.setTypeVarianteVersion(ws.getType_Var_Ver_Prf());
	    var.setTypeVinCG(ws.getType_Vin_Cg());
	    var.setVersion(ws.getVersion());
	    
	    if(var.getDateDcgJour().length()==1) var.setDateDcgJour("0"+var.getDateDcgJour()); //les programmes galaxie attendent un format jj/mm/aaaa
	    if(var.getDateDcgMois().length()==1) var.setDateDcgMois("0"+var.getDateDcgMois()); //les programmes galaxie attendent un format jj/mm/aaaa
	    
	    if(var.getDate1ErCirJour().length()==1) var.setDate1ErCirJour("0"+var.getDate1ErCirJour()); //les programmes galaxie attendent un format jj/mm/aaaa
	    if(var.getDate1ErCirMois().length()==1) var.setDate1ErCirMois("0"+var.getDate1ErCirMois()); //les programmes galaxie attendent un format jj/mm/aaaa
		
		return var;
    }
	
	public VehiculeCacheAAA mapVarAAARetourWsB2BtoCacheAAA(fr.careco.blueway.ws.b2b.VarAAARetourWs ws) {
		VehiculeCacheAAA var = null;
		if(var==null)
			var = new VehiculeCacheAAA();
								
		var.setTurbo_Compr(ws.getTurboCompr());
	    var.setPuis_Kw(ws.getPuisKw());
	    var.setTp_Boite_Vit(ws.getTPBoiteVit());
	    var.setPuis_Ch(LibConversion.stringToInteger(ws.getPuisCh()));
	    var.setPuis_Fisc(LibConversion.stringToInteger(ws.getPuisFisc()));
	    var.setPtr_Prf(LibConversion.stringToInteger(ws.getPTRPRF()));
	    var.setPtr(LibConversion.stringToInteger(ws.getPTR()));
	    var.setPropulsion(ws.getPropulsion());
	    var.setPrix_Vehic(ws.getPrixVehic());
	    var.setN_Serie(ws.getNSerie());
	    var.setPoids_Vide(LibConversion.stringToInteger(ws.getPoidsVide()));
	    var.setNb_Volumes(LibConversion.stringToInteger(ws.getNBVolume()));
	    var.setNb_Soupapes(LibConversion.stringToInteger(ws.getNBVitesse()));
	    var.setNb_Soupapes(LibConversion.stringToInteger(ws.getNBSoupapes()));
	    var.setNb_Portes(LibConversion.stringToInteger(ws.getNBPortes()));
	    var.setNb_Pl_Ass(LibConversion.stringToInteger(ws.getNBPLAss()));
	    var.setNb_Cylind(LibConversion.stringToInteger(ws.getNBCylind()));
	    var.setMode_Inject(ws.getModeInject());
	    var.setModele_Prf(ws.getModelePRF());
	    var.setModele(ws.getModele());
	    var.setModele_Etude(ws.getModeleEtude());
	    var.setMarque_Carros(ws.getMarqueCarros());
	    var.setMarque(ws.getMarque());
	    var.setLargeur(LibConversion.stringToInteger(ws.getLargeur()));
	    var.setLongueur(LibConversion.stringToInteger(ws.getLongueur()));
	    var.setImmat_SIV(ws.getImmatSIV());
	    var.setHauteur(LibConversion.stringToInteger(ws.getHauteur()));
	    var.setGenre(ws.getGenreV());
	    var.setGenre_V_CG(ws.getGenreVCG());
	    var.setEnergie(ws.getEnergie());
	    var.setEmpat(LibConversion.stringToInteger(ws.getEmpat()));
	    var.setDepollution(ws.getDepollution());
	    var.setDate_Dcg_Mois(ws.getDateDcgMois());
	    var.setDate_Dcg_Jour(ws.getDateDcgJour());
	    var.setDate_Dcg_Annee(ws.getDateDCGAnnee());
	    var.setDate_1er_Cir_Mois(ws.getDate1ErCirMois());
	    var.setDate_1er_Cir_Annee(ws.getDate1ErCIRAnnee());
	    var.setDate_1er_Cir_Jour(ws.getDate1ErCirJour());
	    var.setCylindree(LibConversion.stringToInteger(ws.getCylindree()));
	    var.setCouleur_Vehic(ws.getCouleurVehic());
	    var.setCons_Urb(ws.getConsUrb());
	    var.setCons_Mixte(ws.getConsMixte());
	    var.setCons_Exurb(ws.getConsExurb());
	    var.setCodif_Vin_Pref(ws.getCodifVinPRF());
	    var.setCode_Moteur(ws.getCodeMoteur());
	    var.setCO2(LibConversion.stringToInteger(ws.getCO2()));
	    var.setCarrosserie_CG(ws.getCarrosserieCG());
	    var.setCarrosserie(ws.getCarrosserie());
	    var.setType(ws.getType());
	    var.setType_Prf(ws.getTypePrf());
	    var.setType_Var_Ver_Prf(ws.getTypeVarianteVersion());
	    var.setType_Vin_Cg(ws.getTypeVinCG());
	    var.setVersion(ws.getVersion());
		
		return var;
    }
	
	public fr.careco.blueway.ws.b2c.VarAAARetourWs mapCacheAAAtoVarAAARetourWsB2C(VehiculeCacheAAA ws) {
		fr.careco.blueway.ws.b2c.VarAAARetourWs var = null;
		if(var==null)
			var = new fr.careco.blueway.ws.b2c.VarAAARetourWs();
								
		var.setTurboCompr(ws.getTurbo_Compr());
	    var.setTPBoiteVit(ws.getTp_Boite_Vit());
	    var.setPuisCh(LibConversion.integerToString(ws.getPuis_Ch()));
	    var.setPuisFisc(LibConversion.integerToString(ws.getPuis_Fisc()));
	    var.setPTRPRF(LibConversion.integerToString(ws.getPtr_Prf()));
	    var.setPTR(LibConversion.integerToString(ws.getPtr()));
	    var.setPropulsion(ws.getPropulsion());
	    var.setNSerie(ws.getN_Serie());
	    var.setPoidsVide(LibConversion.integerToString(ws.getPoids_Vide()));
	    var.setNBVolume(LibConversion.integerToString(ws.getNb_Volumes()));
	    var.setNBVitesse(LibConversion.integerToString(ws.getNb_Vitesse()));
	    var.setNBSoupapes(LibConversion.integerToString(ws.getNb_Soupapes()));
	    var.setNBPortes(LibConversion.integerToString(ws.getNb_Portes()));
	    var.setNBPLAss(LibConversion.integerToString(ws.getNb_Pl_Ass()));
	    var.setNBCylind(LibConversion.integerToString(ws.getNb_Cylind()));
	    var.setModeInject(ws.getMode_Inject());
	    var.setModelePRF(ws.getModele_Prf());
	    var.setModele(ws.getModele());
	    var.setModeleEtude(ws.getModele_Etude());
	    var.setMarqueCarros(ws.getMarque_Carros());
	    var.setMarque(ws.getMarque());
	    var.setLargeur(LibConversion.integerToString(ws.getLargeur()));
	    var.setLongueur(LibConversion.integerToString(ws.getLongueur()));
	    var.setImmatSIV(ws.getImmat_SIV());
	    var.setHauteur(LibConversion.integerToString(ws.getHauteur()));
	    var.setGenreV(ws.getGenre());
	    var.setGenreVCG(ws.getGenre_V_CG());
	    var.setEnergie(ws.getEnergie());
	    var.setEmpat(LibConversion.integerToString(ws.getEmpat()));
	    var.setDepollution(ws.getDepollution());
	    var.setDateDcgMois(ws.getDate_Dcg_Mois());
	    var.setDateDcgJour(ws.getDate_Dcg_Jour());
	    var.setDateDCGAnnee(ws.getDate_Dcg_Annee());
	    var.setDate1ErCirMois(ws.getDate_1er_Cir_Mois());
	    var.setDate1ErCIRAnnee(ws.getDate_1er_Cir_Annee());
	    var.setDate1ErCirJour(ws.getDate_1er_Cir_Jour());
	    var.setCylindree(LibConversion.integerToString(ws.getCylindree()));
	    var.setCouleurVehic(ws.getCouleur_Vehic());
	    var.setCodifVinPRF(ws.getCodif_Vin_Pref());
	    var.setCO2(LibConversion.integerToString(ws.getCO2()));
	    var.setCarrosserieCG(ws.getCarrosserie_CG());
	    var.setCarrosserie(ws.getCarrosserie());
	    var.setType(ws.getType());
	    var.setTypeVinCG(ws.getType_Vin_Cg());
	    var.setVersion(ws.getVersion());
	    
	    if(var.getDateDcgJour().length()==1) var.setDateDcgJour("0"+var.getDateDcgJour()); //les programmes galaxie attendent un format jj/mm/aaaa
	    if(var.getDateDcgMois().length()==1) var.setDateDcgMois("0"+var.getDateDcgMois()); //les programmes galaxie attendent un format jj/mm/aaaa
	    
	    if(var.getDate1ErCirJour().length()==1) var.setDate1ErCirJour("0"+var.getDate1ErCirJour()); //les programmes galaxie attendent un format jj/mm/aaaa
	    if(var.getDate1ErCirMois().length()==1) var.setDate1ErCirMois("0"+var.getDate1ErCirMois()); //les programmes galaxie attendent un format jj/mm/aaaa

		
		return var;
    }
	
	public VehiculeCacheAAA mapVarAAARetourWsB2CtoCacheAAA(fr.careco.blueway.ws.b2c.VarAAARetourWs ws) {
		VehiculeCacheAAA var = null;
		if(var==null)
			var = new VehiculeCacheAAA();
								
		var.setTurbo_Compr(ws.getTurboCompr());
	    var.setTp_Boite_Vit(ws.getTPBoiteVit());
	    var.setPuis_Ch(LibConversion.stringToInteger(ws.getPuisCh()));
	    var.setPuis_Fisc(LibConversion.stringToInteger(ws.getPuisFisc()));
	    var.setPtr_Prf(LibConversion.stringToInteger(ws.getPTRPRF()));
	    var.setPtr(LibConversion.stringToInteger(ws.getPTR()));
	    var.setPropulsion(ws.getPropulsion());
	    var.setN_Serie(ws.getNSerie());
	    var.setPoids_Vide(LibConversion.stringToInteger(ws.getPoidsVide()));
	    var.setNb_Volumes(LibConversion.stringToInteger(ws.getNBVolume()));
	    var.setNb_Soupapes(LibConversion.stringToInteger(ws.getNBVitesse()));
	    var.setNb_Soupapes(LibConversion.stringToInteger(ws.getNBSoupapes()));
	    var.setNb_Portes(LibConversion.stringToInteger(ws.getNBPortes()));
	    var.setNb_Pl_Ass(LibConversion.stringToInteger(ws.getNBPLAss()));
	    var.setNb_Cylind(LibConversion.stringToInteger(ws.getNBCylind()));
	    var.setMode_Inject(ws.getModeInject());
	    var.setModele_Prf(ws.getModelePRF());
	    var.setModele(ws.getModele());
	    var.setModele_Etude(ws.getModeleEtude());
	    var.setMarque_Carros(ws.getMarqueCarros());
	    var.setMarque(ws.getMarque());
	    var.setLargeur(LibConversion.stringToInteger(ws.getLargeur()));
	    var.setLongueur(LibConversion.stringToInteger(ws.getLongueur()));
	    var.setImmat_SIV(ws.getImmatSIV());
	    var.setHauteur(LibConversion.stringToInteger(ws.getHauteur()));
	    var.setGenre(ws.getGenreV());
	    var.setGenre_V_CG(ws.getGenreVCG());
	    var.setEnergie(ws.getEnergie());
	    var.setEmpat(LibConversion.stringToInteger(ws.getEmpat()));
	    var.setDepollution(ws.getDepollution());
	    var.setDate_Dcg_Mois(ws.getDateDcgMois());
	    var.setDate_Dcg_Jour(ws.getDateDcgJour());
	    var.setDate_Dcg_Annee(ws.getDateDCGAnnee());
	    var.setDate_1er_Cir_Mois(ws.getDate1ErCirMois());
	    var.setDate_1er_Cir_Annee(ws.getDate1ErCIRAnnee());
	    var.setDate_1er_Cir_Jour(ws.getDate1ErCirJour());
	    var.setCylindree(LibConversion.stringToInteger(ws.getCylindree()));
	    var.setCouleur_Vehic(ws.getCouleurVehic());
	    var.setCodif_Vin_Pref(ws.getCodifVinPRF());
	    var.setCO2(LibConversion.stringToInteger(ws.getCO2()));
	    var.setCarrosserie_CG(ws.getCarrosserieCG());
	    var.setCarrosserie(ws.getCarrosserie());
	    var.setType(ws.getType());
	    var.setType_Vin_Cg(ws.getTypeVinCG());
	    var.setVersion(ws.getVersion());
		
		return var;
    }

}
