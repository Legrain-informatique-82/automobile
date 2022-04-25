package fr.legrain.lib.data;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Le Grain SA</p>
 * @author Le Grain SA
 * @version 1.0
 */

public class MessUtilisateur {

   private String fFileNameMsg;
   private String fFileNameIDMsg;
   private HashMap fListeMessageMsg;
   private HashMap fListeMessageIDMsg;
   //private FIniFile:TIniFile;

   protected void ecritFileNameMsg(String value){}
   protected void ecritFileNameIDMsg(String value){}

   public MessUtilisateur() {}

   /**
    * Called by the garbage collector on an object when garbage collection
    * determines that there are no more references to the object.
    * @throws Throwable the <code>Exception</code> raised by this method
    */
   protected void finalize() throws Throwable {
   }

//    public property FileNameMsg:string Write EcritFileNameMsg;
//    public property FileNameIDMsg:string Write EcritFileNameIDMsg;
//    public function MessageUtil(IDMsg : string;const ParamMess : array of const; MsgDlgBtn:TMsgDlgButtons):TResultMessUtil;
//////    property TitreChamp[const NomChamp: string]: String read LitTitreChamp; // Contient la liste des champs ainsi que les valeurs de l'enregistrment courant

}
