package fr.legrain.lib.data;

public class SWTEdition extends ModelObject {
	private String NOM;
	public SWTEdition(String nom) {
		this.setNOM(nom);
	}

	public String getNOM() {
		return this.NOM;
	}

	public void setNOM(String NOM) {
		firePropertyChange("NOM", this.NOM, this.NOM = NOM);
	}
}

