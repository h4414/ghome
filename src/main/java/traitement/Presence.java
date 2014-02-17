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
import trames.Trame;

/** Classe de traitement des trames du detecteur de présence
 *
 * @author Jérémy
 */
public class Presence implements Runnable {
    private static List<Historique> listeTrame = new ArrayList<Historique>();
    /**
     * Champ contenant la trame traitée ( pour execution dans un thread a part 
     */
    Trame trameTraitee;
    CamelContext ctx;
    /**
     * 
     * @param trame Une trame recue par le détecteur de présence
     * @return true si la trame indique une présence 
     */
    public static boolean OccupancyDetected(Trame trame){
        return trame.getDataX_Y(0, 1) == 0;
    }
    
    public static List<Historique> getHistorique(){
        return listeTrame;
    }
    
    public static void viderHistorique(){
        listeTrame = new ArrayList<Historique>();
    }

    public Presence(Trame trameTraitee, CamelContext ctx){
        this.trameTraitee = trameTraitee ;
        this.ctx = ctx;
    }
    /** Crée un objet historique si on détecte une présence dans la plage
     * horaire donnée.
     * @param trame Une trame recue par le détecteur de présence
     * @param debutplage date de début de la plage horaire qu'on surveille
     * @param finplage date de fin de la plage horaire qu'on surveille
     * @return l'historique créé, null si rien n'a été créé
     */
    public Historique TraitementPresence(Trame trame, Calendar debutplage, Calendar finplage){
        Calendar now = new GregorianCalendar();
        if (debutplage.before(now) && finplage.after(now) && OccupancyDetected(trame)){        
            Historique newhist = new Historique(trame.getID(),now,now);
            
            return newhist;
        }else{
            return null;
        }
    }
    /**
     * Sera modifié sous peu
     */
    @Override
    public void run() {
        Calendar begin= new GregorianCalendar();
        
        Calendar end = new GregorianCalendar();

        end.add(Calendar.HOUR, 4);
        Historique traitementPresence = TraitementPresence(trameTraitee,begin, end);
        if (!(traitementPresence==null)){
            listeTrame.add(traitementPresence);
            System.out.println(traitementPresence);
            ProducerTemplate pdt = new DefaultProducerTemplate( this.ctx );
            try {
                pdt.start();
                pdt.sendBody("direct:capteur",traitementPresence);
                pdt.stop();
            } catch (Exception ex) {
                Logger.getLogger(Presence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
}
