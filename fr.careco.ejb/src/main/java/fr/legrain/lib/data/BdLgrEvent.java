package fr.legrain.lib.data;

/**
 * <p>Title: </p>
 * <p>Description: Evenement de base de données Le Grain.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Le Grain SA</p>
 * @author Le Grain SA
 * @version 1.0
 */

public class BdLgrEvent {

  protected Object source;

  public BdLgrEvent(Object source) {
    this.source = source;
  }

}
