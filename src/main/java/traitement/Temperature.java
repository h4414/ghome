/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitement;

import h4414.ghome.entities.Historique;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultProducerTemplate;
import static traitement.Presence.OccupancyDetected;
import trames.RecuperateurTrame;
import static trames.RecuperateurTrame.envoyerTrame;
import trames.Trame;

/*
 *
 * @author qdunoyer
 */
public class Temperature implements Runnable {
    private static List<Historique> listeTrame = new ArrayList<>();
    /*
     * Champ contenant la trame traitée ( pour execution dans un thread a part 
     */
    Trame trameTraitee;
    CamelContext ctx;
    
    public static double getTemperature(Trame trame){
        
        double temperature = (double)(trame.getDATA1()*40)/250;
        return temperature;
        
    }
    
    public static List<Historique> getHistorique(){
        return listeTrame;
    }
    
    public static void viderHistorique(){
        listeTrame = new ArrayList<>();
    }

    public Temperature(Trame trameTraitee, CamelContext ctx){
        this.trameTraitee = trameTraitee ;
        this.ctx = ctx;
    }
    
    public Historique traitementTemperature(Trame trame){
        Calendar now = new GregorianCalendar();     
        Historique newhist = new Historique(trame.getID(),now,now,Temperature.getTemperature(trame));
        return newhist;
    }
    
    @Override
    public void run() {
        double dtemperature = Temperature.getTemperature(trameTraitee);
        //TODO : A remplacer par la gestion de règle.
        if (dtemperature<25){
            String envoi = Actionneur.allumerPrise("6");
            System.out.println("Temp" + dtemperature);
            RecuperateurTrame.envoyerTrame(envoi);
        }
        else 
        {
            String envoi = Actionneur.eteindrePrise("6");
            System.out.println(envoi+"pour eteindre");
            RecuperateurTrame.envoyerTrame(envoi);
        }
        
        Historique traitementTemperature = traitementTemperature(trameTraitee);
        listeTrame.add(traitementTemperature);
        System.out.println(traitementTemperature);
        ProducerTemplate pdt = new DefaultProducerTemplate( this.ctx );
        try {
            pdt.start();
            pdt.sendBody("direct:capteur",traitementTemperature);
            pdt.stop();
        } catch (Exception ex) {
            Logger.getLogger(Presence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
