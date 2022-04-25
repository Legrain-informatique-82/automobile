package fr.legrain.careco.webapp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import fr.legrain.bdg.tiers.service.remote.ILignePanierServiceRemote;
import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITransactionAchatVenteServiceRemote;
import fr.legrain.careco.aaa.model.VehiculeCacheAAA;
import fr.legrain.careco.webapp.Auth;
import fr.legrain.tiers.model.ImportStockTemp;
import fr.legrain.tiers.model.Stock;
import fr.legrain.tiers.model.User;
import fr.legrain.tiers.service.DevService;

@ManagedBean
@ViewScoped 
public class DevBean implements Serializable {  
	
	private User user;
	
	//@EJB private ITransactionAchatVenteServiceRemote transactionAchatVenteService;

	@EJB DevService devService;
	
	private List<ImportStockTemp> importStock;
	
	private String valeur1;
	
	private Boolean premiereImportationTexte;
	
	private String nomDumpMysql;
	
//	private UploadedFile fichierStockUpload;
	
	public void refresh() {
		
	}
	
	@PostConstruct
	public void init() {
//		user = Auth.findUserInSession();
//		refresh();
	}
	
	public List<String> autoCompleteImmatCache(String query) {  
		return devService.autoCompleteImmatCache(query);
	}
	
	public void testImmat(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"xxxxxxxxxx : "+VehiculeCacheAAA.immatPourWebService(valeur1)
	    		)); 
	}

	public void test(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"xxxxxxxxxx : "+devService.test()
	    		)); 
	}
	
	public void test2(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"xxxxxxxxxxxxxxxxx"
	    		)); 
	}
	
	public void immportV1(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		devService.immportV1();
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"Importation des données de la V1"
	    		)); 
	}
	
	public void selectDataV1(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		devService.selectDataV1();
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"Chargement des doonées de la V1, dans le bean"
	    		)); 
	}
	
	public void mysqldump(ActionEvent event) throws Exception {
		//Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		devService.mysqlDump(nomDumpMysql);
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"DumpMySQL OK"
	    		)); 
	}
	
	public void importStockCSV(FileUploadEvent event) {  
		//Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
			
			try {

		    	String nomFichier = "import_stock_"+new Date().getTime()+event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf("."));
		    	String cheminComplet = "/var/careco/tmp/"+nomFichier;
		    	
		    	Path target3 = Paths.get(cheminComplet);
		    	try {
					Files.copy(event.getFile().getInputstream(), target3);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	
		    	//nettoyage précédente importation pour cette entreprise
		    	devService.annulerImportationStock();
		    	
				devService.importStockCSV(cheminComplet);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			

            FacesContext context = FacesContext.getCurrentInstance();  
    	    context.addMessage(null, new FacesMessage("Dev", 
    	    		"Importation du stock CSV OK : "+event.getFile().getFileName()
    	    		)); 		
	}
	
	public void importStockMoteurClub(FileUploadEvent event) {  
		//Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
			
			try {

		    	String nomFichier = "import_stock_moteur_club_"+new Date().getTime()+event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf("."));
		    	String cheminComplet = "/var/careco/tmp/"+nomFichier;
		    	
		    	Path target3 = Paths.get(cheminComplet);
		    	try {
					Files.copy(event.getFile().getInputstream(), target3);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	
		    	//nettoyage précédente importation pour cette entreprise
		    	devService.annulerImportationStock();
		    	
				devService.importStockMoteurClub(cheminComplet);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			

            FacesContext context = FacesContext.getCurrentInstance();  
    	    context.addMessage(null, new FacesMessage("Dev", 
    	    		"Importation du stock CSV OK : "+event.getFile().getFileName()
    	    		)); 		
	}
	
	public void updateStockWithV1(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		devService.updateStockWithV1();
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"Importation du stock Dump V1 OK"
	    		)); 
	}
	
	public void updateStockWithDumpMoteurClub(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		devService.updateStockWithDumpMoteurClub();
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"Mise à jour du stock à partir du dump moteur club OK"
	    		)); 
	}
	
	public void updateStockWithAAA(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		devService.updateStockWithAAA(false);
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"Mise à jour du stock à partir du cache et WS AAA OK"
	    		)); 
	}
	
	public void validerImportationStock(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		devService.validerImportationStock();
		
		//déplacer et conserver le fichier texte ?
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"Importation du stock valider, remplacement du stock actuel"
	    		)); 
	}
	
	public void annulerImportationStock(ActionEvent event) throws Exception {
		Integer idPanier = (Integer) event.getComponent().getAttributes().get("idPanier");
		
		devService.annulerImportationStock();
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev", 
	    		"Importation du stock annuler"
	    		)); 
	}
	
	public String versionProg() {
		//returns the major version (2.1)
		String jsfMajorVersion = FacesContext.class.getPackage().getImplementationVersion();

		//returns the specification version (2.1)
		String jsfSpecificationVersion = Package.getPackage("com.sun.faces").getSpecificationVersion();

		//returns the minor implementation version (2.1.x)
		String jsfMinorImplementationVersion = Package.getPackage("com.sun.faces").getImplementationVersion();
		String jsfNameVersion = Package.getPackage("com.sun.faces").getImplementationTitle();

		//org.primefaces.util.Constants.VERSION;
		
		String primefacesBuildVersion = RequestContext.getCurrentInstance().getApplicationContext().getConfig().getBuildVersion();
		
		String v = "jsfMajorVersion : "+jsfMajorVersion+
				" * jsfSpecificationVersion : "+jsfSpecificationVersion+
				" * jsfMinorImplementationVersion : "+jsfMinorImplementationVersion+
				" * jsfNameVersion : "+jsfNameVersion+
				" * primefacesBuildVersion : "+primefacesBuildVersion
				;
		
		FacesContext context = FacesContext.getCurrentInstance();  
	    context.addMessage(null, new FacesMessage("Dev version", 
	    		"v"
	    		));
	    
	    return v;
	}

	public String getValeur1() {
		return valeur1;
	}

	public void setValeur1(String valeur1) {
		this.valeur1 = valeur1;
	}

	public List<ImportStockTemp> getImportStock() {
		importStock = devService.findImport();
		
		return importStock;
	}

	public void setImportStock(List<ImportStockTemp> importStock) {
		this.importStock = importStock;
	}

	public Boolean getPremiereImportationTexte() {
		premiereImportationTexte = devService.premiereImportationTexte();
		return premiereImportationTexte;
	}

	public String getNomDumpMysql() {
		return nomDumpMysql;
	}

	public void setNomDumpMysql(String nomDumpMysql) {
		this.nomDumpMysql = nomDumpMysql;
	}

//	public UploadedFile getFichierStockUpload() {
//		return fichierStockUpload;
//	}
//
//	public void setFichierStockUpload(UploadedFile fichierStockUpload) {
//		this.fichierStockUpload = fichierStockUpload;
//	}
	
	
}  
              