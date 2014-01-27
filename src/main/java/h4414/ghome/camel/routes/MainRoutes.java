/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.routes;



import h4414.ghome.camel.processors.PresenceRuleProcessor;
import h4414.ghome.entities.Historique;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpBinding;
import org.apache.camel.component.http.HttpEndpoint;
import org.apache.camel.component.http.HttpMessage;
import org.apache.camel.component.jpa.JpaEndpoint;
import org.apache.camel.impl.DefaultHeaderFilterStrategy;
import trames.RecuperateurTrame;

/**
 *
 * @author Mathis
 */
public class MainRoutes extends RouteBuilder{
    private final String PERSISTANCE_UNIT_NAME = "4414_ghhome_war_1.0-SNAPSHOTPU";
    private PresenceRuleProcessor presenceRuleProcessor = new PresenceRuleProcessor();

    

    //@PersistenceUnit(unitName="ghome")
    //private EntityManagerFactory factory;
    
    @Override
    public void configure() throws Exception {
        CamelContext context = this.getContext();
            RecuperateurTrame recuperateur = new RecuperateurTrame(context);
            Thread listener = new Thread(recuperateur);
            listener.start();
        //EntityManager eManager = factory.createEntityManager();

            from("direct:capteur").to("log:capteur?showAll=true");

            from("jetty:http://localhost:8087/test")
           

                
                .process(new Processor(){
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
    }
    
    
    
}
