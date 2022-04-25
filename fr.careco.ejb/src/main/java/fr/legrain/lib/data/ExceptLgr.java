package fr.legrain.lib.data;

import org.apache.log4j.Logger;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Le Grain SA</p>
 * @author Le Grain SA
 * @version 1.0
 */

public class ExceptLgr extends Exception {

  int codeErreur = 0;
  boolean afficheMessage = false;
  int typeMessage = 0;
  
  static Logger logger = Logger.getLogger(ExceptLgr.class.getName());	
  
  public ExceptLgr(MessCtrlLgr ObjMessage,String message, int codeErreur, boolean afficheMessage, int typeMessage) {
	    super(message);
	   // this.message = message;
	    this.codeErreur = codeErreur;
	    this.afficheMessage = afficheMessage;
	    this.typeMessage = typeMessage;


	    logger.error(this);
//	    if(isAfficheMessage()){
//	    	ObjMessage.setDejaAffiche(true);
//	    	MessageDialog.openWarning(Workbench.getInstance().getActiveWorkbenchWindow().getShell(),"ATTENTION",message);
//	       // JOptionPane.showMessageDialog(LgrConstantes.getFocusedLgr(),message,"ATTENTION",JOptionPane.WARNING_MESSAGE);
//	    }
	    
	  }

  public ExceptLgr(String message, int codeErreur, boolean afficheMessage, int typeMessage) {
    super(message);
    //this.message = message;
    this.codeErreur = codeErreur;
    this.afficheMessage = afficheMessage;
    this.typeMessage = typeMessage;
    /** @todo a changer/finir + problème si affichage pendant un appel a verify() d'un InputVerifier */
   /* if(isAfficheMessage()) {
      Thread t = new Thread() {
        public void run() {
          JOptionPane.showMessageDialog(null, "message", "ERREUR",JOptionPane.ERROR_MESSAGE);
        }
      };
      t.start();
    }
    */
  /* SwingUtilities.invokeLater(new Runnable() {
     public void run() {
       JOptionPane.showMessageDialog(null, "message", "ERREUR",JOptionPane.ERROR_MESSAGE);
     }
   });
*/
    logger.error(this);

  }

  public ExceptLgr() {
	  logger.error(this);
  }
  
  public ExceptLgr(String message) {
	  super(message);
	  logger.error("",this);
  }

  public boolean isAfficheMessage() {
    return afficheMessage;
  }

  public int getCodeErreur() {
    return codeErreur;
  }

  public void setTypeMessage(int typeMessage) {
    this.typeMessage = typeMessage;
  }

  public void setAfficheMessage(boolean afficheMessage) {
    this.afficheMessage = afficheMessage;
  }

  public void setCodeErreur(int codeErreur) {
    this.codeErreur = codeErreur;
  }

  public int getTypeMessage() {
    return typeMessage;
  }


}
