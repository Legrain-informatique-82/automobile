package fr.legrain.lib.data;

public class InfosVerifSaisie<T> {
	
	public static final int CTRL_QUE_DES_CHIFFRES = 1;
	public static final int CTRL_QUE_DES_CHIFFRES_POSITIFS = 2;
	public static final int CTRL_LONGUEUR_TEXTE = 3;
	public static final int CTRL_QUE_DES_CHIFFRES_INTERVALE = 4;
	
	private T entity;
	private String field;
	private int[] listeControles;
	
	/**
	 * 
	 * @param entity - objet contenant le champ a verifier
	 * @param field - nom du champ a verifier
	 * @param listeControles
	 */
	public InfosVerifSaisie(T entity, String field, int[] listeControles) {
		super();
		this.entity = entity;
		this.field = field;
		this.listeControles = listeControles;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int[] getListeControles() {
		return listeControles;
	}

	public void setListeControles(int[] listeControles) {
		this.listeControles = listeControles;
	}
	

}
