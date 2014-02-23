/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitement;

import h4414.ghome.entities.Capteur;
import h4414.ghome.entities.Historique;
import h4414.ghome.vues.PersistanceUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultProducerTemplate;
import trames.Trame;

/**
 *
 * @author Qdunoyer
 */
public class Contacteur implements Runnable{
    private static List<Historique> listeTrame = new ArrayList<Historique>();
    
    Trame trameTraitee;
    CamelContext ctx;
    
    public Contacteur(Trame trameTraitee, CamelContext ctx){
        this.trameTraitee = trameTraitee;
        this.ctx = ctx;
    }
    
    /*Permet de savoir si une porte est fermé ou non.
     * 
     * @param trame
     * @return True si la porte est fermé.
     */
    public static boolean getContact(Trame trame){
        boolean bool = trame.getDataX_Y(0, 1)  == 1;
        return bool;
    };
    
    public Historique traitementContacteur(Trame trame){
        Calendar now = new GregorianCalendar();
        EntityManager em = PersistanceUtils.getEmf().createEntityManager();
        Query getCapteurs = em.createQuery("SELECT o FROM Capteur o WHERE o.idCapteur = "+trame.getID(), Capteur.class);
        Capteur capteur = (Capteur) getCapteurs.getSingleResult();
        Historique newhist = new Historique(capteur,now,now,Contacteur.getContact(trame));
        return newhist;
    }
    
    @Override
    public void run() {
        Calendar begin= new GregorianCalendar();
        Historique traitementContacteur = traitementContacteur(trameTraitee);
        listeTrame.add(traitementContacteur);
        System.out.println(traitementContacteur);
        ProducerTemplate pdt = new DefaultProducerTemplate( this.ctx );
        try {
            pdt.start();
            pdt.sendBody("direct:capteur",traitementContacteur);
            pdt.stop();
        } catch (Exception ex) {
            Logger.getLogger(Presence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
