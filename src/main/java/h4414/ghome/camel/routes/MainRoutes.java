/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.routes;



import h4414.ghome.entities.Historique;
import java.util.Date;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;


/**
 *
 * @author Mathis
 */
public class MainRoutes extends RouteBuilder{
    private final String PERSISTANCE_UNIT_NAME = "4414_ghhome_war_1.0-SNAPSHOTPU";
    //@PersistenceUnit(unitName="ghome")
    //private EntityManagerFactory factory;
    
    @Override
    public void configure() throws Exception {
        //EntityManager eManager = factory.createEntityManager();
        
        from("jetty:http://localhost:8087/test")
                
                
                .process(new Processor(){
                    public void process ( Exchange exchange ){
                        Historique hist = new Historique("test", new Date(), new Date());
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
        .log("ajout d'une règle");
    }
    
    
    
}
