/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.routes;



import h4414.ghome.entities.Historique;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpBinding;
import org.apache.camel.component.http.HttpEndpoint;
import org.apache.camel.component.http.HttpMessage;
import org.apache.camel.component.jpa.JpaEndpoint;
import org.apache.camel.impl.DefaultHeaderFilterStrategy;

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
                .process(new Processor(){
                    public void process(Exchange e){
                        Message m = e.getIn();
                        HttpServletRequest sr = m.getHeader(Exchange.HTTP_SERVLET_REQUEST, HttpServletRequest.class);
                        sr.setAttribute("histo", m.getBody(Historique.class));
                        m.setHeader(Exchange.HTTP_SERVLET_REQUEST, sr );
                        HttpEndpoint h= new HttpEndpoint(); 
                        HttpBinding b = h.getBinding();
                        DefaultHeaderFilterStrategy strat = (DefaultHeaderFilterStrategy)(b.getHeaderFilterStrategy());
                        System.out.println(strat.getOutFilter());
                    }

           
                })
                .setHeader("message",simple("${body}"))
                .setBody(constant(null))
           .to("log:yo?showAll=true")
        .to("http://localhost:8084/ghome/mainView.jsp?bridgeEndpoint=true"/*+&disableStreamCache=true"+*/);
        
        /*
         * definir une plage horaire sur laquelle l'on détecte une présence
         */
        from( "jetty:http://localhost:8087/dtctintrusion")
        .log("detecte intrusion");
    }
    
    
    
}
