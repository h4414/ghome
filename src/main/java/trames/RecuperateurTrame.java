

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trames;

import h4414.ghome.entities.Historique;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultProducerTemplate;
import org.apache.camel.spi.Registry;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import traitement.Presence;
/**
 *
 * @author Maud et Thomas
 */
public class RecuperateurTrame implements Runnable {
  Socket socket;
 //TODO: a mettre dans fichier config
  final static int port = 5000;
  final static String IP = "134.214.106.23";
  final static String ID_CONTACTEUR = "0001B595";
 // final static String ID_CONTACTEUR = "0001B25E";
  final static String ID_PRISE = "dfgbjfdkhbv";
  //final static String ID_PRISE = "dfgbjfdkhbv";
  final static String ID_BOUTON = "0021CC31";
 // final static String ID_BOUTON = "0021CBE3";
  //final static String ID_PRESENCE ="00053E7B"; // n2
  final static String ID_PRESENCE = "00054A7F";
  final static String ID_TEMPERATURE = "0089337F";
  private CamelContext context;

  
  
  public RecuperateurTrame(CamelContext context)
  {
      this.context = context;
  }
  public  Boolean execute() {
          try {
              BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                char[] buf = new char[28];
             in.read(buf, 0, 28);
             String trame = new String(buf);
              Trame trameRecue;
              trameRecue = new Trame(trame);


    
    switch(trameRecue.getID())
    {
        case ID_PRISE: 
            System.out.println("TRAME PRISE DETECTEE");
            return true;
            //TO DO Traitement
        case ID_TEMPERATURE: 
            System.out.println("TRAME TEMPERATURE DETECTEE");
            return true;
            //TO DO Traitement     
        case ID_CONTACTEUR:
            System.out.println("TRAME CONTACTEUR DETECTEE");
            return true;
            //TO DO Traitement
          
        case ID_BOUTON :
            System.out.println("TRAME BOUTON DETECTEE");
            return true;
            
        case ID_PRESENCE :
            System.out.println("TRAME PRESENCE DETECTEE");
            Presence traitementPresence = new Presence(trameRecue, this.context);
            Thread presence = new Thread(traitementPresence);
            presence.start();
            return true;
            //TO DO Traitement
          
        default:
            System.out.println("FAIL");
            return false;
                     
    
          } }catch (IOException ex1) {
              Logger.getLogger(RecuperateurTrame.class.getName()).log(Level.SEVERE, null, ex1);
                  } 
      //System.out.println(trameRecue);
      return false;
  
             
  
    }
    public void initialize()
    {
        Registry reg = context.getRegistry();
        Object emf = reg.lookupByName("entityManagerFactory");
        
        if ( emf != null ){
            if ( emf instanceof EntityManagerFactory ){
                EntityManagerFactory emFactory = (EntityManagerFactory )( emf);
                EntityManager em = emFactory.createEntityManager();
                List datas = em.createQuery("SELECT o FROM CAPTEUR o").getResultList();
                System.out.println(datas);
            }
            else{
                System.out.println("failed to retrieve emfactory : is instance of "+emf.getClass()+"");
            }
        }
        else{
            System.out.println("failed to retrieve emfactory : is null ...");
        }
    }
    

    @Override
    public void run() {
      try {
          this.initialize();
          socket= new Socket(IP,port);
              while(true)
          {
              this.execute();
              
          }
      } catch (IOException ex) {
          Logger.getLogger(RecuperateurTrame.class.getName()).log(Level.SEVERE, "Impossible de se connecter à la base de capteurs ...");
      

      }
    }


    
public void setContext ( CamelContext context){
    this.context = context;
}

            
}


