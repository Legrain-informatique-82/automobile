package fr.legrain.lib.data;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

public interface IModelGeneral{
	public List remplirListe();
	public List remplirListeElement(Object e,String propertyName, Object value);
	public Object recherche(String propertyName, Object value);
	public ModelObject rechercheDansListe(LinkedList<ModelObject> listeObjet,String propertyName, Object value);
	public void razListEntity();
	public ModelObject remplirListeLazy(EntityManager em, int id);
}
