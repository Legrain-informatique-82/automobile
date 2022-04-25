package fr.legrain.validator.common;

import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.MessCtrlLgr;

public interface ICtrlLgr {
	
	public void ctrlSaisie(MessCtrlLgr message) throws ExceptLgr;
	
	public boolean isModeServeur();
	public void setModeServeur(boolean modeServeur);

}
