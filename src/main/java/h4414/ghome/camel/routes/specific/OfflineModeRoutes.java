/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.routes.specific;

import h4414.ghome.entities.Historique;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author Mathis
 */
public class OfflineModeRoutes extends RouteBuilder{
    
    public OfflineModeRoutes(){
        
    }
    
    @ Override
    public void configure(){
        final String PERSISTANCE_UNIT_NAME = "4414_ghhome_war_1.0-SNAPSHOTPU";
        
        
        int timer = 60; // nb de secondes d'intervalle entre chaque donnée envoyée
                    // ( pratique pour changer tout d'un coup )
        
        
        /*
         * Route pour générer des objets historiques et les persister
         */
        from("timer://foo?fixedRate=true&period="+timer+"s")
                .setBody().constant(new Historique("FakeCapteur",new GregorianCalendar(), new GregorianCalendar()))
                .to("jpa:Historique?persistenceUnit="+PERSISTANCE_UNIT_NAME)
                .log("Fake Historique added to DB"); 
        
        // TODO : ajouter d'autre routes comme celle des historiques pour générer 
        //          d'autres objets au fur et a mesure qu'on prendra en compte d'autres types de donnees
    }
}
