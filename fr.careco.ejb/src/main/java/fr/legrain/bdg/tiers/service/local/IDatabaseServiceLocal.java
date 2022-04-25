package fr.legrain.bdg.tiers.service.local;

import java.io.File;
import java.io.IOException;

import javax.ejb.Local;

@Local
public interface IDatabaseServiceLocal{
	public File backupDB(String nomDump) throws IOException;
	
}
