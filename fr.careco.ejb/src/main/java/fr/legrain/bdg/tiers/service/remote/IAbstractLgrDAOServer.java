package fr.legrain.bdg.tiers.service.remote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.FinderException;
import javax.ejb.Remote;

//@Remote
public interface IAbstractLgrDAOServer<Entity> {

	public abstract void enregistrerPersist(Entity transientInstance) throws Exception;

	public abstract Entity enregistrerMerge(Entity persistentInstance) throws Exception;
	
	//public void consultation();

	//public void afterEnregistrer() throws Exception;

	public Boolean autoriseModification(Entity persistentInstance);

	public void messageNonAutoriseModification() throws Exception;

	public void verifAutoriseModification(Entity persistentInstance) throws Exception;

	public void modifier(Entity persistentInstance) throws Exception;

	public void inserer(Entity transientInstance) throws Exception;

	public void annuler(Entity persistentInstance) throws Exception;

	public Entity annulerT(Entity persistentInstance) throws Exception;

	public void supprimer(Entity persistentInstance) throws Exception;
	
	//public void commit(EntityTransaction transaction) throws Exception;

	//public void begin(EntityTransaction transaction) throws Exception;

	public Entity findById(int id) throws FinderException;

	public List<Entity> selectAll();

	//public void initConnexion();

	public String getChampIdTable();

	public Map<String, String[]> getParamWhereSQL();

	public void setParamWhereSQL(Map<String, String[]> paramWhereSQL);

	public String getJPQLQuery();

	public void setJPQLQuery(String query);

	public int initValeurIdTable(Entity entity);

	public boolean autoriseUtilisationCodeGenere(String code) throws Exception;

	public boolean rentreCodeGenere(String code, String ChampCourant) throws Exception;

	public void annulerCodeGenere(String code, String ChampCourant) throws Exception;

	public boolean recordModifiable(String nomTable, Integer valeurChamp);

	public boolean autoriseModification() throws Exception;

	public void rentreEnModification() throws Exception;

	public void annuleModification(boolean commited) throws Exception;

	public void setListeChampMaj(String fileName) throws Exception;

	public String getNomTable();

	public void setNomTable(String nomTable);

	public Integer getValeurIdTable();

	public String getChampIdEntite();

	public HashMap getListeChampsCalcules();

	public void setListeChampsCalcules(HashMap listeChampsCalcules);

	public int selectCount();

	public String getChampGenere();

	public void setChampGenere(String champGenere);

	public String getJPQLQueryInitial();

	public void setJPQLQueryInitial(String jPQLQueryInitial);

	//public ModeObjet getModeObjet();

	//public void setModeObjet(ModeObjet modeObjet);
}