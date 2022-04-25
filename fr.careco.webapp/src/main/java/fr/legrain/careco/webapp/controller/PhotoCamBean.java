package fr.legrain.careco.webapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;

import fr.legrain.bdg.tiers.service.remote.IStockServiceRemote;
import fr.legrain.tiers.model.ImagePiece;
import fr.legrain.tiers.model.Stock;

@ManagedBean
@ViewScoped 
public class PhotoCamBean {  
	
	private Stock piece;
    
    private List<ImagePiece> photos = new ArrayList<ImagePiece>();  
    
	@EJB
    private IStockServiceRemote stockService;
      
    private String getRandomImageName() {  
        int i = (int) (Math.random() * 10000000);  
          
        return String.valueOf(i);  
    }  
  
    public List<ImagePiece> getPhotos() {  
        return photos;  
    }      
      
    public void oncapture(CaptureEvent captureEvent) {  
//        String photo = getRandomImageName();  
//        this.photos.add(0,photo);  
//        byte[] data = captureEvent.getData();  
//          
//        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
//        String newFileName = servletContext.getRealPath("") + File.separator + "photocam" + File.separator + photo + ".png";  
//          
//        FileImageOutputStream imageOutput;  
//        try {  
//            imageOutput = new FileImageOutputStream(new File(newFileName));  
//            imageOutput.write(data, 0, data.length);  
//            imageOutput.close();  
//        }  
//        catch(Exception e) {  
//            throw new FacesException("Error in writing captured image.");  
//        }  
    }  
    
    public void handleFileUpload(FileUploadEvent event) throws Exception {  
    	
    	Integer idPiece = (Integer) event.getComponent().getAttributes().get("idPiece");
    	
    	//Stock s = stockService.findById(idPiece);
    	piece = stockService.findById(idPiece);
    	
    	ImagePiece img = new ImagePiece();
    	if(piece.getImages()==null) {
    		piece.setImages(new ArrayList<ImagePiece>());
    	}
    	String nomFichier = "img_"+idPiece+"_"+piece.getImages().size()/*+"."*/+event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf("."));
    	img.setChemin(nomFichier);
    	
//    	Path target = Paths.get("D:\\Backup\\MyStuff.txt");
//    	Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");
//    	FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
//    	Files.createFile(target, attr);
//    	
//    	Path target2 = Paths.get("D:\\Backup\\MyStuff.txt");
//    	Files.delete(target2);
//    	
//    	Path source = Paths.get("C:\\My Documents\\Stuff.txt");
//    	Path target3 = Paths.get("D:\\Backup\\MyStuff.txt");
//    	Files.copy(source, target3);
//
//    	Path source2 = Paths.get("C:\\My Documents\\Stuff.txt");
//    	Path target4 = Paths.get("D:\\Backup\\MyStuff.txt");
//    	Files.copy(source2, target4, StandardCopyOption.REPLACE_EXISTING);
//
//    	Path source3 = Paths.get("C:\\My Documents\\Stuff.txt");
//    	Path target5 = Paths.get("D:\\Backup\\MyStuff.txt");
//    	Files.move(source3, target5, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);

    	Path target3 = Paths.get("/var/careco/images/"+nomFichier);
    	try {
			Files.copy(event.getFile().getInputstream(), target3);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	img.setIdPiece(piece);
    	piece.getImages().add(img);
    	
    	piece = stockService.enregistrerMerge(piece);
    	
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }

	public Stock getPiece() {
		return piece;
	}

	public void setPiece(Stock piece) {
		this.piece = piece;
	} 
}  
