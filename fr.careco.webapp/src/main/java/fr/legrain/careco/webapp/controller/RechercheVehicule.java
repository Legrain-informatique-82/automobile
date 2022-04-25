package fr.legrain.careco.webapp.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;

import fr.legrain.bdg.tiers.service.remote.IFicheCarecoServiceRemote;
import fr.legrain.careco.aaa.model.IVehiculeCacheAAAServiceRemote;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.LibDate;
import fr.legrain.tiers.model.FicheCareco;
import fr.legrain.tiers.model.User;

public class RechercheVehicule {
	
	private List<FicheCareco> listeFicheCareco;
	private VehiculeCacheAAA infosAAA;
	
	private Set<String> listeCodeMoteur = new HashSet<String>();
	private Set<String> listeCodeBoite = new HashSet<String>();
	
	private Boolean ajoutPossible = false;
	
	private User user;
	
	@EJB
    private IVehiculeCacheAAAServiceRemote vehiculeCacheAAAService;
	
	@EJB
    private IFicheCarecoServiceRemote ficheCarecoService;
	
	@PostConstruct
	public void init() {
		user = Auth.findUserInSession();
	}

	public void rechercheImmatOuVIN(String immatriculation, String vin) throws Exception, EJBException {
		System.out.println("RechercheVehicule.recherche() *** user = "+user.getUsername()+" ("+user.getNom()+" "+user.getPrenom()+") "+user.getUserCompany().getNom());
		System.out.println("Immatriculation : "+immatriculation);
		infosAAA = null;
		
		String typeRecherche = "0";
		String typeRechercheImmatriculation = "1";
		String typeRechercheVIN = "2";

		if(immatriculation!=null) {
			typeRecherche = typeRechercheImmatriculation;
		} else if(vin!=null) {
			typeRecherche = typeRechercheVIN;
		} else {
			System.out.println("Pas de paramètre de recherche : immat et vin vides");
		}
		
		typeRecherche = typeRechercheImmatriculation; //pour test
		
		try {
		if (typeRecherche.equals(typeRechercheImmatriculation)) {
			
			System.out.println("Recherche par immatriculation, saisie : "+immatriculation);
			System.out.println("Recherche par immatriculation, rechercher : "+VehiculeCacheAAA.immatPourWebService(immatriculation));
//			if(immatriculation!=null 
//					&& !immatriculation.equals("") 
//					&& VehiculeCacheAAA.immatPourWebService(immatriculation)!=null 
//					&& !VehiculeCacheAAA.immatPourWebService(immatriculation).equals("")
//					) {
				infosAAA = vehiculeCacheAAAService.wsSiVinConsulterVehiculeParImmatOuVin(VehiculeCacheAAA.immatPourWebService(immatriculation),null);
//			}
		} else if (typeRecherche.equals(typeRechercheVIN)) {
			System.out.println("Recherche par VIN");
			System.out.println("Recherche par VIN, rechercher : "+vin);
			infosAAA = vehiculeCacheAAAService.wsSiVinConsulterVehiculeParImmatOuVin(null,vin);
		}
		
		if(infosAAA.estVide()) {
			System.out.println("Le service n'a pas trouvé de véhicule");
			infosAAA = null;
		}
				
		if(infosAAA!=null) {
			//recupérer le CNIT
			//recupérer date début
			//recupérer date fin
			//rechercher dans les fiches careco avec CNIT, date deb et fin de mise en circulation
			//récuprer compatibilité boite et moteur
			String cnit = infosAAA.getType();
			Date date1ereCirc = LibDate.stringToDate(infosAAA.getDate_1er_Cir_Jour()+"/"+infosAAA.getDate_1er_Cir_Mois()+"/"+infosAAA.getDate_1er_Cir_Annee());
			List<FicheCareco> fc = ficheCarecoService.findByCNIT_TypeCG(cnit,date1ereCirc);
			// les différents onglets seront généré à partir de cette liste dans les méthodes getTabsAstuceMoteur() et getTabsAstuceBoite()
			
			if(fc.isEmpty()) {
				//Création d'une nouvelle fiche
				System.out.println("Pas de FicheInfos trouvée, création d'une nouvelle fiche");
				
				FicheCareco ficheGenere = generateNewFicheInfo();
				
				ficheGenere = ficheCarecoService.enregistrerMerge(ficheGenere);

				fc.add(ficheGenere);
			}
			listeFicheCareco = fc;

		}
		} catch(Exception e) {
			throw e;
		}

	}
	
	public FicheCareco generateNewFicheInfo() throws ParseException {
		FicheCareco ficheGenere = new FicheCareco();
		
		ficheGenere.setIdCareco("autogen-"+infosAAA.getType_Vin_Cg());
		
		ficheGenere.setTypeMot(infosAAA.getType()+"@"+infosAAA.getDate_1er_Cir_Mois()+"/"+infosAAA.getDate_1er_Cir_Annee());
		String bv="AT"; //boite auto
		if(infosAAA.getTp_Boite_Vit().equals("MECANIQUE")) {
			bv = LibConversion.integerToString(infosAAA.getNb_Vitesse())+"V"; 
		}
		
		ficheGenere.setRefBte(bv+"-"+infosAAA.getType()+"@"+infosAAA.getDate_1er_Cir_Mois()+"/"+infosAAA.getDate_1er_Cir_Annee()+"@"+infosAAA.getModele()+" ("+infosAAA.getMarque()+" ) < "+infosAAA.getDate_1er_Cir_Annee());
		
		ficheGenere.setDateDe(LibDate.stringToDate("01"+"/"+"01"+"/"+infosAAA.getDate_1er_Cir_Annee()));
		ficheGenere.setDateA(LibDate.stringToDate("31"+"/"+"12"+"/"+infosAAA.getDate_1er_Cir_Annee()));
		
		ficheGenere.setTypeCG(infosAAA.getType());
		
		ficheGenere.setMarque(infosAAA.getMarque());
		ficheGenere.setModele(infosAAA.getModele());
		ficheGenere.setMode(""); //? infosAAA.getMode_Inject()
		ficheGenere.setGenre(infosAAA.getGenre());
		ficheGenere.setCm3(LibConversion.integerToString(infosAAA.getCylindree()));
		ficheGenere.setCyl(LibConversion.integerToString(infosAAA.getNb_Cylind()));
		ficheGenere.setSppes(LibConversion.integerToString(infosAAA.getNb_Soupapes()));
		ficheGenere.setCvDin(LibConversion.integerToString(infosAAA.getPuis_Ch()));
		
		ficheGenere.setObsMot("Pas d'astuce pour ce moteur.<br>Bien vérifier alors les éléments CNIT, Date de 1er mise en circulation de ce véhicule pour ce moteur…<br>Si vous avez une astuce n'ésitez pas à l'ajouter !");
		ficheGenere.setObsBte("Pas d'astuce pour cette boite.<br>Bien vérifier alors les éléments CNIT, Type moteur, Date de 1er mise en circulation de ce véhicule pour ce moteur…<br>Si vous avez une astuce n'ésitez pas à l'ajouter !");
		
		return ficheGenere;
	}
	
	public List<TabFicheMoteur> getTabsAstuceMoteur() {
		List<TabFicheMoteur> l = new ArrayList<TabFicheMoteur>();
		
		if(listeFicheCareco!=null) {
			//créer les différents onglet vert (origine) et jaune (compatible)
			listeCodeMoteur.clear();
			for (FicheCareco ficheCareco : listeFicheCareco) {
				//original
				l.add(new TabFicheMoteur(ficheCareco.getTypeCG(), false, ficheCareco.getTypeMot()/*M*/, ficheCareco.getObsMot()/*O*/,
						ficheCareco.getDateDe(),ficheCareco.getDateA(),ficheCareco.getIdCareco(),ficheCareco.getPivotMoteurs()));
				listeCodeMoteur.add(ficheCareco.getTypeMot());
				
				if(ficheCareco.getCodeMot()!=null && !ficheCareco.getCodeMot().equals("")) {
					//compatible
					String[] codeCompatible = ficheCareco.getCodeMot().split("\\|");
					for (int i = 0; i < codeCompatible.length; i++) {
						String code = codeCompatible[i];
						//l.add(new TabFicheMoteur(ficheCareco.getTypeCG(), true, code/*N*/, "?"));
						l.add(new TabFicheMoteur(ficheCareco.getTypeCG(), true, code/*N*/, ficheCareco.getObsMot()/*O*/,
								ficheCareco.getDateDe(),ficheCareco.getDateA(),ficheCareco.getIdCareco(),ficheCareco.getPivotMoteurs()));
						listeCodeMoteur.add(code);
					}
				}
				
			}
			
			if(ajoutPossible) {
				TabFicheMoteur tabAjout = new TabFicheMoteur();
				tabAjout.setTypeMoteur("Ajouter : Type moteur");
				l.add(tabAjout);
			}
			
		}
		return l;
	}
	
	public List<TabFicheBoite> getTabsAstuceBoite() {
		List<TabFicheBoite> l = new ArrayList<TabFicheBoite>();
		
		if(listeFicheCareco!=null) {
			listeCodeBoite.clear();
			//créer les différents onglet vert (origine) et jaune (compatible)
			for (FicheCareco ficheCareco : listeFicheCareco) {
				//original
				l.add(new TabFicheBoite(ficheCareco.getTypeCG(), false, ficheCareco.getRefBte()/*P*/, ficheCareco.getObsBte()/*S*/,
							ficheCareco.getDateDe(),ficheCareco.getDateA(),ficheCareco.getIdCareco(),ficheCareco.getPivotBoiteDeVitesse()));
				listeCodeBoite.add(ficheCareco.getRefBte());
				
				if(ficheCareco.getCodeBte()!=null && !ficheCareco.getCodeBte().equals("")) {
					//compatible
					String[] codeCompatible = ficheCareco.getCodeBte().split("\\|");
					for (int i = 0; i < codeCompatible.length; i++) {
						String code = codeCompatible[i];
						//l.add(new TabFicheBoite(ficheCareco.getTypeCG(), true, code/*Q*/, "?"));
						l.add(new TabFicheBoite(ficheCareco.getTypeCG(), true, code/*Q*/, ficheCareco.getObsBte()/*S*/,
								ficheCareco.getDateDe(),ficheCareco.getDateA(),ficheCareco.getIdCareco(),ficheCareco.getPivotBoiteDeVitesse()));
						listeCodeBoite.add(code);
					}
				}
			}
			
			if(ajoutPossible) {
				TabFicheBoite tabAjout = new TabFicheBoite();
				tabAjout.setRefBoite("Ajouter : Type boite");
				l.add(tabAjout);
			}
			
		}
		return l;
	}

	public List<FicheCareco> getListeFicheCareco() {
		return listeFicheCareco;
	}

	public void setListeFicheCareco(List<FicheCareco> listeFicheCareco) {
		this.listeFicheCareco = listeFicheCareco;
	}

	public VehiculeCacheAAA getInfosAAA() {
		return infosAAA;
	}

	public void setInfosAAA(VehiculeCacheAAA infosAAA) {
		this.infosAAA = infosAAA;
	}

	public Set<String> getListeCodeMoteur() {
		return listeCodeMoteur;
	}

	public void setListeCodeMoteur(Set<String> listeCodeMoteur) {
		this.listeCodeMoteur = listeCodeMoteur;
	}

	public Set<String> getListeCodeBoite() {
		return listeCodeBoite;
	}

	public void setListeCodeBoite(Set<String> listeCodeBoite) {
		this.listeCodeBoite = listeCodeBoite;
	}

	public Boolean getAjoutPossible() {
		return ajoutPossible;
	}

	public void setAjoutPossible(Boolean ajoutPossible) {
		this.ajoutPossible = ajoutPossible;
	}
}
