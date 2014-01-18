/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trames;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;

/**
 *
 * @author Maud et Thomas
 */
public class RecuperateurTrame {

 //TODO: a mettre dans fichier config
  final static int port = 5000;
  final static String IP = "134.214.106.23";
  final static String ID_CONTACTEUR = "0001b595";
  final static String ID_PRISE = "dfgbjfdkhbv";
  final static String ID_BOUTON = "0021CC31";
  public void recuperateurTrame()
  {
      
  }
  public  boolean execute(String trame) {

      Trame trameRecue = new Trame(trame);
    
         switch(trameRecue.getID())
    {
        case ID_PRISE: 
            return true;
            //TO DO Traitement
            
        case ID_CONTACTEUR:
            return true;
            //TO DO Traitement
          
        case ID_BOUTON :
            return true;
            //TO DO Traitement
          
        default:
            return false;
                     
    }
      return true;
       
      //System.out.println(trameRecue);
  
             
  
    }


    


            
}
  
  


