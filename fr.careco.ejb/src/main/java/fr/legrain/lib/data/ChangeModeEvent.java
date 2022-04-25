package fr.legrain.lib.data;

import java.util.EventObject;

public class ChangeModeEvent extends EventObject {
	
	private ModeObjet.EnumModeObjet ancienMode = null;
	private ModeObjet.EnumModeObjet nouveauMode = null;
	
	public ChangeModeEvent(Object source) {
		super(source);
	}
	
	public ChangeModeEvent(Object source, ModeObjet.EnumModeObjet nouveauMode) {
		super(source);
		this.nouveauMode = nouveauMode; 
	}
	
	public ChangeModeEvent(Object source, ModeObjet.EnumModeObjet nouveauMode, ModeObjet.EnumModeObjet ancienMode) {
		super(source);
		this.nouveauMode = nouveauMode;
		this.ancienMode = ancienMode;
	}

	public ModeObjet.EnumModeObjet getAncienMode() {
		return ancienMode;
	}

	public void setAncienMode(ModeObjet.EnumModeObjet ancienMode) {
		this.ancienMode = ancienMode;
	}

	public ModeObjet.EnumModeObjet getNouveauMode() {
		return nouveauMode;
	}

	public void setNouveauMode(ModeObjet.EnumModeObjet nouveauMode) {
		this.nouveauMode = nouveauMode;
	}
	
}
