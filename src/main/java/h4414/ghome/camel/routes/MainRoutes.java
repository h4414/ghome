/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.routes;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author Mathis
 */
public class MainRoutes extends RouteBuilder{

    @PersistenceUnit(unitName="ghome")
    private EntityManagerFactory factory;
    
    @Override
    public void configure() throws Exception {
        EntityManager eManager = factory.createEntityManager();
        from("jetty:http://localhost:8087/test")
                
                
                .to("jpa:ghome")
                
        .log("test");
        
        /*
         * definir une plage horaire sur laquelle l'on détecte une présence
         */
        from( "jetty:http://localhost:8087/dtctintrusion")
        .log("detecte intrusion");
    }
    
    
    
}
