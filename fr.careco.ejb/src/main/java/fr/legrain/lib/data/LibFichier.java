package fr.legrain.lib.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;



public final class LibFichier {
	static Logger logger = Logger.getLogger(LibFichier.class.getName());

	//Deletes all files and subdirectories under dir.
	// Returns true if all deletions were successful.
	// If a deletion fails, the method stops attempting to delete and returns false.
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
	
	public static void copy(final InputStream inStream, final OutputStream outStream, final int bufferSize) throws IOException {
		try {
			final byte[] buffer = new byte[bufferSize];
			int nbRead;
			while ((nbRead = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, nbRead);
			}
		} catch (Exception e) {
			logger.error("",e);
		}
	}  
	public static void copyDirectory(final File from, final File to) throws IOException {
		try {			
			if (! to.exists()) {
				to.mkdir();
			}
			final File[] inDir = from.listFiles();
			for (int i = 0; i < inDir.length; i++) {
				final File file = inDir[i];
				copy(file, new File(to, file.getName()));
			}
		} catch (Exception e) {
			logger.error("",e);
		}
	}
		
	public static void copyFile(final File from, final File to) throws IOException {
		try {
			final InputStream inStream = new FileInputStream(from);
			final OutputStream outStream = new FileOutputStream(to);
			copy(inStream, outStream, (int) Math.min(from.length(), 4*1024));
			inStream.close();
			outStream.close();
		} catch (Exception e) {
			logger.error("",e);
		}
	}
		
	public static void copy(final File from, final File to) throws IOException {
		try {
			if (from.isFile()) {
				copyFile(from, to);
			} else if (from.isDirectory()){
				copyDirectory(from, to);
			} else {
				throw new FileNotFoundException(from.toString() + " does not exist" );
			}
		} catch (Exception e) {
			logger.error("",e);
		}
	}
}
