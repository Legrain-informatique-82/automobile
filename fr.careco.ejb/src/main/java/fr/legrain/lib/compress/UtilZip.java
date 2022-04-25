package fr.legrain.lib.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class UtilZip {
	
	static Logger logger = Logger.getLogger(UtilZip.class.getName());
	
	private static final int BUFFER = 2048;
	
	//Liste des fichiers à Zipper
	public static Vector<File> fichiers = new Vector<File>();
	
	
	//Remplissage de la liste des fichiers à traiter (récursif)
	private void list(File file) {
		if (file.isFile()) {
			fichiers.add(file);
		} else if (file.isDirectory()) {
			File[] listeFichier = file.listFiles();
			for (int i = 0; i < listeFichier.length; i++) {
				if (listeFichier[i].isDirectory()) {
					list(listeFichier[i]);
				} else {
					fichiers.add(listeFichier[i]);
				}
			}
		}
	}
	
	/**
	 * Zippe <code>files</code> dans <code>fichierZipDestination</code>
	 * @param files - fichiers (chemin absolue) à mettre dans l'archive
	 * @param fichierZipDestination
	 */
	public void zipFiles(String[] files, String fichierZipDestination) throws Exception {	
		//buffer pour la lecture des fichiers
		byte[] data = new byte[BUFFER];
		
		//Création du fichier zip
		String outFilename = fichierZipDestination;
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
		
		//ajout des fichiers et compression
		for (int i=0; i<files.length; i++) {
			FileInputStream in = new FileInputStream(files[i]);
			
			//ajout du fichier - on enleve le debut du chemin absolu
			out.putNextEntry(new ZipEntry(new File(files[i]).getName()));
			
			//ecriture dans le fichier zip
			int len;
			while ((len = in.read(data)) > 0) {
				out.write(data, 0, len);
			}
			
			//fin de l'ajout du fichier au zip
			out.closeEntry();
			in.close();
		}
		
		//fin du fichier zip
		out.close();
	}
	
	/**
	 * Zippe <code>repertoire</code> dans <code>fichierZipDestination</code>
	 * @param repertoire
	 * @param fichierZipDestination
	 */
	public void zip(String repertoire, String fichierZipDestination) throws Exception {
		//récupération de la liste des fichiers à zipper
		fichiers.clear();
		list(new File(repertoire));
		
		String[] filenames = new String[fichiers.size()];
		int j = 0;
		for (File fichier : fichiers) {
			filenames[j]=fichier.getAbsolutePath();
			j++;
		}
		
		//buffer pour la lecture des fichiers
		byte[] data = new byte[BUFFER];
		
		//Création du fichier zip
		String outFilename = fichierZipDestination;
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
		
		//ajout des fichiers et compression
		for (int i=0; i<filenames.length; i++) {
			FileInputStream in = new FileInputStream(filenames[i]);
			
			//ajout du fichier - on enleve le debut du chemin absolu
			out.putNextEntry(new ZipEntry(filenames[i].substring(new File(repertoire).getParent().length() + 1)));
			
			//ecriture dans le fichier zip
			int len;
			while ((len = in.read(data)) > 0) {
				out.write(data, 0, len);
			}
			
			//fin de l'ajout du fichier au zip
			out.closeEntry();
			in.close();
		}
		
		//fin du fichier zip
		out.close();
	}
	
	/**
	 * Dezippe <code>fichierZipSource</code> dans <code>repertoire</code>
	 * @param repertoire
	 * @param fichierZipSource
	 */
	public void unzip(String repertoire, String fichierZipSource) throws Exception {
		byte data[] = new byte[BUFFER];
		BufferedOutputStream dest = null;
		FileInputStream fis = new FileInputStream(fichierZipSource);
		
		//ouverture du fichier zip
		BufferedInputStream buffi = new BufferedInputStream(fis);
		ZipInputStream zis = new ZipInputStream(buffi);
		
		//Lecture du fichier zip
		ZipEntry entry;
		while((entry = zis.getNextEntry()) != null) {
			//creation des répertoires eventuels
			File f = new File(repertoire+entry.getName());
			f.getParentFile().mkdirs();
			
			//Création du fichier de sortie à partir du nom de l’entrée
			FileOutputStream fos = new FileOutputStream(repertoire+entry.getName());
			
			//ecriture du fichier 
			dest = new BufferedOutputStream(fos, BUFFER);
			int count = 0;
			while ((count = zis.read(data, 0, BUFFER)) != -1) {
				dest.write(data, 0, count);
			}
			dest.flush();
//			10. Fermeture du flux de sortie
			dest.close();
		}
		
		//fermeture du fichier zip
		zis.close();
		
	}
	
	/**
	 * 
	 * @param fichierZipSource
	 */
	public String firstEntryName(String fichierZipSource) throws Exception {

		FileInputStream fis = new FileInputStream(fichierZipSource);
		String res = null;
		
		//ouverture du fichier zip
		BufferedInputStream buffi = new BufferedInputStream(fis);
		ZipInputStream zis = new ZipInputStream(buffi);
		
		//Lecture du fichier zip
		ZipEntry entry;
		entry = zis.getNextEntry();
		if(entry.isDirectory()) {
			res = entry.getName();
		} else {
			File f = new File(entry.getName());
			res = f.getParent();
		}
		
		//fermeture du fichier zip
		zis.close();
		
		return res;
		
	}
	
}
