package fr.legrain.lib.data;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Le Grain SA</p>
 * @author Le Grain SA
 * @version 1.0
 */

public class MessCtrlLgr {

	private int msg;
	private String nomChampDB;      //nomChamp
	private String nomChamp;      //nomChamp
	private String nomTable;      //nomTable
	private String nomTableMere;
	private String nomChampEtranger; //       //nomChampEtranger
	private String nomTableEtrangere; //nomTableEtrangere
	private String nomTableChamp; //nomTable.nomChamp
	private String valeur;        //valeur à mettre dans le champs
	private String oldValeur;    //avant modif
	private String idValeur;     //id de l'enregistrement courant
	private int codeErreur=0;
	private String msgID;         //identifiant du message à afficher
	private String messageAffiche="";
	private Class entityClass;
	private String contexte;
	private boolean afficheMessage=true;
	private boolean dejaAffiche = false;
	private ModeObjet modeObjet;
	private String nomChampId;

	public MessCtrlLgr() {}

	public MessCtrlLgr(int msg, String nomChamp, String nomTable,String nomChampEtranger, String nomTableEtrangere,String nomTableChamp,
			String valeur,String old_Valeur,String id_Valeur,int codeErreur,
			String msgID,ModeObjet modeObjet) {
		setMsg(msg);
		setNomChamp(nomChamp);
		setNomTable(nomTable);
		setNomTableChamp(nomTableChamp);
		setNomChampEtranger(nomChampEtranger);
		setNomTableEtrangere(nomTableEtrangere);
		setValeur(valeur);
		setOld_Valeur(old_Valeur);
		setID_Valeur(id_Valeur);
		setCodeErreur(codeErreur);
		setMsgID(msgID);
		setModeObjet(modeObjet);
	}

	public void setNomTableChamp(String NomTableChamp) {
		this.nomTableChamp = NomTableChamp;
	}

	public String getNomTableChamp() {
		return nomTableChamp;
	}

	public void setOld_Valeur(String Old_Valeur) {
		this.oldValeur = Old_Valeur;
	}

	public String getOld_Valeur() {
		return oldValeur;
	}

	public void setCodeErreur(int CodeErreur) {
		this.codeErreur = CodeErreur;
	}

	public int getCodeErreur() {
		return codeErreur;
	}

	public void setNomTable(String NomTable) {
		this.nomTable = NomTable;
	}

	public String getNomTable() {
		return nomTable;
	}

	public void setNomChamp(String NomChamp) {
		this.nomChamp = NomChamp;
	}

	public String getNomChamp() {
		return nomChamp;
	}

	public void setID_Valeur(String ID_Valeur) {
		this.idValeur = ID_Valeur;
	}

	public String getID_Valeur() {
		return idValeur;
	}

	public void setMsgID(String MsgID) {
		this.msgID = MsgID;
	}

	public String getMsgID() {
		return msgID;
	}

	public void setMsg(int Msg) {
		this.msg = Msg;
	}

	public int getMsg() {
		return msg;
	}

	public void setValeur(String Valeur) {
		this.valeur = Valeur;
	}

	public String getValeur() {
		return valeur;
	}

	public void setModeObjet(ModeObjet ModeObjet) {
		this.modeObjet = ModeObjet;
	}

	public void setNomChampEtranger(String nomChampEtranger) {
		this.nomChampEtranger = nomChampEtranger;
	}

	public void setNomTableEtrangere(String nomTableEtrangere) {
		this.nomTableEtrangere = nomTableEtrangere;
	}

	public ModeObjet getModeObjet() {
		return modeObjet;
	}

	public String getNomChampEtranger() {
		return nomChampEtranger;
	}

	public String getNomTableEtrangere() {
		return nomTableEtrangere;
	}

	public String getMessageAffiche() {
		return messageAffiche;
	}

	public void setMessageAffiche(String messageAffiche) {
		if (!this.messageAffiche.equals(messageAffiche))this.dejaAffiche=false;
		this.messageAffiche = messageAffiche;
	}

	public boolean isAfficheMessage() {
		return afficheMessage;
	}

	public void setAfficheMessage(boolean afficheMessage) {
		this.afficheMessage = afficheMessage;
	}

	public boolean isDejaAffiche() {
		return dejaAffiche;
	}

	public void setDejaAffiche(boolean dejaAffiche) {
		this.dejaAffiche = dejaAffiche;
	}

	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	public String getContexte() {
		return contexte;
	}

	public void setContexte(String contexte) {
		this.contexte = contexte;
	}

	public String getNomChampDB() {
		return nomChampDB;
	}

	public void setNomChampDB(String nomChampDB) {
		this.nomChampDB = nomChampDB;
	}

	public String getNomChampId() {
		return nomChampId;
	}

	public void setNomChampId(String nomChampId) {
		this.nomChampId = nomChampId;
	}

	public String getNomTableMere() {
		return nomTableMere;
	}

	public void setNomTableMere(String nomTableMere) {
		this.nomTableMere = nomTableMere;
	}
}
