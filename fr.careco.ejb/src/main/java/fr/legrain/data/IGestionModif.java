package fr.legrain.data;

import java.sql.Connection;

public interface IGestionModif {

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#connection(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean connection(String url, String user, String pass,
			String driver);

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#initIp(java.lang.String)
	 */
	public void initIp(String ip);

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#initQuery()
	 */
	public boolean initQuery();

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#setListeGestionModif(java.lang.String)
	 */
	public void setListeGestionModif(String fileName);

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#recupChampGenere(java.lang.String)
	 */
	public String recupChampGenere(String nomTable) throws Exception;

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#recupChampVerifModification(java.lang.String)
	 */
	public String recupChampVerifModification(String nomTable) throws Exception;

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#autoriseModif(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean autoriseModif(String nomTable, String nomChamp,
			String valeurChamp) throws Exception;

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#autoriseModifCodeGenere(java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	public boolean autoriseModifCodeGenere(String nomTable, String nomChamp,
			String valeurChamp, Boolean verif_Connection) throws Exception;

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#annuleModif(java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	public void annuleModif(String nomTable, String nomChamp,
			String valeurChamp, boolean commited) throws Exception;

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#annuleModif(java.lang.String, java.lang.String, java.lang.Integer, boolean)
	 */
	public void annuleModif(String nomTable, String nomChamp,
			Integer valeurChamp, boolean commited) throws Exception;

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#rentreEnModif(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void rentreEnModif(String nomTable, String nomChamp,
			String valeurChamp) throws Exception;

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#rentreEnModif(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public void rentreEnModif(String nomTable, String nomChamp,
			Integer valeurChamp) throws Exception;

	/* (non-Javadoc)
	 * @see fr.legrain.data.IGestionModif#setCnx(java.sql.Connection)
	 */
	public void setCnx(Connection cnx);

}