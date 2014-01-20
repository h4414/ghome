/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitement;

import h4414.ghome.entities.Historique;
import java.util.Calendar;
import java.util.GregorianCalendar;
import trames.Trame;

/** Classe de traitement des trames du detecteur de présence
 *
 * @author Jérémy
 */
public class Presence {
    
    /**
     * 
     * @param trame Une trame recue par le détecteur de présence
     * @return true si la trame indique une présence 
     */
    public static boolean OccupancyDetected(Trame trame){
        return trame.getDataX_Y(0, 1) == 0;
    }

    /** Crée un objet historique si on détecte une présence dans la plage
     * horaire donnée.
     * @param trame Une trame recue par le détecteur de présence
     * @param debutplage date de début de la plage horaire qu'on surveille
     * @param finplage date de fin de la plage horaire qu'on surveille
     * @return l'historique créé, null si rien n'a été créé
     */
    public static Historique TraitementPresence(Trame trame, Calendar debutplage, Calendar finplage){
        Calendar now = new GregorianCalendar();
        if (debutplage.before(now) && finplage.after(now) && OccupancyDetected(trame)){        
            Historique newhist = new Historique(trame.getID(),now,now);
            return newhist;
        }else{
            return null;
        }
    }
}
