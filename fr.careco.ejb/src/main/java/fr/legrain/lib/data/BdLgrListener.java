package fr.legrain.lib.data;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: Interface qui recoit les évènement de base de données Le Grain </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Le Grain SA</p>
 * @author Le Grain SA
 * @version 1.0
 */

public interface BdLgrListener extends EventListener {

  /**
   * Invoqué quand les modifications d'un sont enregsitrées
   * @param evt TBdLgrEvent
   */
  public void enregistrer(BdLgrEvent evt);

  /**
   * Invoqué quand une ligne est supprimée
   * @param evt TBdLgrEvent
   */
  public void supprimer(BdLgrEvent evt);

  /**
   * Invoqué quand une ligne est modifiée
   * @param evt TBdLgrEvent
   */
  public void modifier(BdLgrEvent evt);

  /**
   *
   * @param evt TBdLgrEvent
   */
  public void annuler(BdLgrEvent evt);

  /**
   * Invoqué quand une ligne est ajoutée
   * @param evt TBdLgrEvent
   */
  public void inserer(BdLgrEvent evt);

}
