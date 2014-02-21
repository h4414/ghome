

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package trames;

import h4414.ghome.entities.Capteur;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.spi.Registry;
import traitement.Actionneur;
import traitement.Presence;
import traitement.Temperature;
/**
 *
 * @author Maud et Thomas
 */
public class RecuperateurTrame implements Runnable {
    Socket socket;
    //TODO: a mettre dans fichier config
    final static int port = 5000;
    final static String IP = "134.214.106.23";
    
    List<String> IDS_CONTACTEUR;
    List<String> IDS_PRISE;
    List<String> IDS_BOUTON;
    List<String> IDS_PRESENCE;
    List<String> IDS_TEMPERATURE;
    // final static String ID_CONTACTEUR = "0001B25E";
    /*    final static String ID_CONTACTEUR = "0001B595";
    final static String ID_PRISE = "dfgbjfdkhbv";
    //final static String ID_PRISE = "dfgbjfdkhbv";
    final static String ID_BOUTON = "0021CC31";
    // final static String ID_BOUTON = "0021CBE3";
    //final static String ID_PRESENCE ="00053E7B"; // n2
    final static String ID_PRESENCE = "00054A7F";
    final static String ID_TEMPERATURE = "0089337F";*/
    private CamelContext context;
    
    
    
    public RecuperateurTrame(CamelContext context)
    {
        this.context = context;
        this.IDS_CONTACTEUR = new ArrayList<>();
        this.IDS_PRISE= new ArrayList<>();
        this.IDS_BOUTON= new ArrayList<>();
        this.IDS_PRESENCE= new ArrayList<>();
        this.IDS_TEMPERATURE= new ArrayList<>();
    }
    public  Boolean execute() {
        try {
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            char[] buf = new char[28];
            in.read(buf, 0, 28);
            String trame = new String(buf);
            Trame trameRecue;
            trameRecue = new Trame(trame);
            if (this.IDS_BOUTON.contains(trameRecue.getID()))
            {
                System.out.println("TRAME BOUTON DETECTEE");
                return true;
            }
            else if (this.IDS_TEMPERATURE.contains(trameRecue.getID()))
            {
                System.out.println("TRAME TEMPERATURE DETECTEE");
                Temperature traitementTemperature = new Temperature(trameRecue, this.context);
                double dtemperature = Temperature.getTemperature(trameRecue);
                if (dtemperature<25){
                    String envoi = Actionneur.sendTrame("7");
                    System.out.println(envoi);
                    envoyerTrame(envoi);
                }
                Thread temperature = new Thread(traitementTemperature);
                temperature.start();
         
                return true;
                //TO DO Traitement
            }
            else if (this.IDS_CONTACTEUR.contains(trameRecue.getID()))
            {
                System.out.println("TRAME CONTACTEUR DETECTEE");
                return true;
                //TO DO Traitement
            }
            else if (this.IDS_PRESENCE.contains(trameRecue.getID()))
            {
                System.out.println("TRAME PRESENCE DETECTEE");
                Presence traitementPresence = new Presence(trameRecue, this.context);
                Thread presence = new Thread(traitementPresence);
                presence.start();
                return true;
            }
            else if (this.IDS_PRISE.contains(trameRecue.getID()))
            {
                System.out.println("TRAME PRISE DETECTEE");
                return true;
                //TO DO Traitement
            }
            else
            {
                System.out.println("PAS A NOUS");
                return false;
            }
            
            
            /*
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
            
            */
        }
        catch (IOException ex1) {
            Logger.getLogger(RecuperateurTrame.class.getName()).log(Level.SEVERE, null, ex1);
        }
        catch (NumberFormatException ex2){
            System.out.println("Trame de merde");
        }
        //System.out.println(trameRecue);
        return false;
        
        
        
    }
    
    public void update(Capteur c)
    {
                            switch ( c.getType().toString())
                    {
                        case "CONTACTEUR":
                            IDS_CONTACTEUR.add(c.getIdCapteur());
                            break;
                        case "PRESENCE":
                            IDS_PRESENCE.add(c.getIdCapteur());
                            break;
                        case "PRISE":
                            IDS_PRISE.add(c.getIdCapteur());
                            break;
                        case "BOUTON":
                            IDS_BOUTON.add(c.getIdCapteur());
                            break;
                        case "TEMPERATURE":
                            IDS_TEMPERATURE.add(c.getIdCapteur());
                            break;
                    }
    }
    public void initialize()
    {
        Registry reg = context.getRegistry();
        Object emf = reg.lookupByName("entityManagerFactory");
        
        if ( emf != null ){
            if ( emf instanceof EntityManagerFactory ){
                EntityManagerFactory emFactory = (EntityManagerFactory )( emf);
                EntityManager em = emFactory.createEntityManager();
                List<Capteur> datas =  em.createQuery("SELECT o FROM Capteur o").getResultList();
                for (Capteur c : datas)
                {
                    update(c);                  
                }
                
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
    
    	public static void envoyerTrame(String trame)
	{
		final String laTrame = trame;
		
		new Thread(new Runnable()
		{			
			@Override
			public void run() 
			{	
				Socket mSocket;
				OutputStream os;
				PrintWriter pw;
				SocketAddress skAdd = new InetSocketAddress(IP, port);
				mSocket = new Socket();
				try 
				{
					mSocket.connect(skAdd, 0);
					if (mSocket.isConnected())
					{
						os = mSocket.getOutputStream();
						pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)), true);
						pw.print(laTrame);
						pw.flush();
						pw.close();
						mSocket.close();
												
					
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}).start();
	}
}



