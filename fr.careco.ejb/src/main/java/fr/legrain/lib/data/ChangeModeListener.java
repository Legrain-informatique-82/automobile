package fr.legrain.lib.data;

import java.util.EventListener;

public interface ChangeModeListener extends EventListener {
	
	/**
	 * Changement de mode de l'objet gerant la base de données
	 * @param evt
	 */
	public void changementMode(ChangeModeEvent evt);

}
