
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.routes;



import h4414.ghome.camel.processors.ContextInitializer;
import h4414.ghome.camel.processors.PresenceRuleProcessor;
import h4414.ghome.camel.processors.AddRoute;

import h4414.ghome.camel.routes.specific.OfflineModeRoutes;

import h4414.ghome.entities.Historique;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpBinding;
import org.apache.camel.component.http.HttpEndpoint;
import org.apache.camel.component.http.HttpMessage;
import org.apache.camel.component.jpa.JpaEndpoint;
import org.apache.camel.impl.DefaultHeaderFilterStrategy;
import traitement.Presence;
import trames.RecuperateurTrame;
import trames.Trame;

/**
 *
 * @author Mathis
 */
public class MainRoutes extends RouteBuilder{
    private final String PERSISTANCE_UNIT_NAME = "4414_ghhome_war_1.0-SNAPSHOTPU";

    private PresenceRuleProcessor presenceRuleProcessor = new PresenceRuleProcessor();

    private final String IP = "134.214.106.23";
    private final String ID_CONTACTEUR = "0001B25E";
    private final String ID_PRISE = "dfgbjfdkhbv";
    private final String ID_BOUTON = "0021CBE3";
    private final String ID_PRESENCE = "00054A7F";
    private final String ID_TEMPERATURE = "0089337F";
    
    // offlineMode = ne pas charger de trames de la base de capteurs, simuler des trames à la place ( pour bosser à la maison )
    private boolean offlineMode = true;

    

    //@PersistenceUnit(unitName="ghome")
    //private EntityManagerFactory factory;
    
    @Override
    public void configure() throws Exception {
        
            CamelContext context = this.getContext();
           
            RecuperateurTrame recuperateur = new RecuperateurTrame(context);
            Thread listener = new Thread(recuperateur);
            listener.start();
            //EntityManager eManager = factory.createEntityManager();
            ContextInitializer ctxInit = new ContextInitializer(recuperateur);
        
            from("direct:capteur")
                    .to("jpa:Historique?persistenceUnit="+PERSISTANCE_UNIT_NAME)
                    .to("log:capteur?showAll=true");
          
            from("jetty:http://localhost:8087/test")
                .process(new Processor(){
                    @Override
                    public void process ( Exchange exchange ){
                        Historique hist = new Historique("test",new GregorianCalendar(),new GregorianCalendar());
                        exchange.getIn().setBody(hist);
                    }
                })
                
                .to("jpa:Historique?persistenceUnit="+PERSISTANCE_UNIT_NAME)
                
           .to("log:yo?showAll=true");
        //.to("http://localhost:8084/ghome/mainView.jsp?bridgeEndpoint=true"/*+&disableStreamCache=true"+*/);
        
        /*
         * definir une plage horaire sur laquelle l'on détecte une présence
         */
        from( "jetty:http://localhost:8087/addobject")
        .log("ajout d'un capteur");
        from( "jetty:http://localhost:8087/addrule")
                .process(presenceRuleProcessor)
                .to("log:regle ajoutee?showAll=true")
                .to("jpa:ReglePresence?persistenceUnit="+PERSISTANCE_UNIT_NAME)
        .log("ajout d'une règle");
        
        
        from("timer://runOnce?repeatCount=1&delay=5000")
               .choice()
                .when().constant(!offlineMode)
                    .process(ctxInit)
                    .log("code de thomas op")
                .otherwise()
                    .process(new AddRoute(new OfflineModeRoutes()))
                    .log("\n***************************\nOffline mode engaged :@\n changez le boolean dans mainroutes pour changer de mode\nYAAAAARRRHHH\n***************************");
        
        
        from("jpa:Historique?persistenceUnit="+PERSISTANCE_UNIT_NAME+"&consumeDelete=false&maximumResults=5&consumer.query=select o from Historique o")
                .to("log:obj retrieved?showAll=true");
        
        from ( "jetty:http://localhost:8087/gethistorique")
                
                //.enrich("jpa:Historique?persistenceUnit="+PERSISTANCE_UNIT_NAME+"&consumeDelete=false&maximumResults=5&consumer.query=select o from Historique o")
                .to("log:obj retrieved?showAll=true")
        .log("lol");
        
        //from("").to("jpa:Historique?persistenceUnit="+PERSISTANCE_UNIT_NAME);
    }
    
    
    
}

