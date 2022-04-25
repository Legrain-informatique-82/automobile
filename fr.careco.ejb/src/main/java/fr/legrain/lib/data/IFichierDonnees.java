package fr.legrain.lib.data;

import java.util.List;

public interface IFichierDonnees<T> {
	
	public void creationFichierDonnees(List<T> liste, String repertoireModele, String nomFichier, boolean html);

}
