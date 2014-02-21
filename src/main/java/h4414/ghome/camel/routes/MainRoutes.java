
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.routes;



import h4414.ghome.camel.processors.ContextInitializer;
import h4414.ghome.camel.processors.PresenceRuleProcessor;
import h4414.ghome.camel.processors.AddRoute;
import h4414.ghome.camel.processors.DataBaseReader;
import h4414.ghome.camel.processors.DataToJson;
import h4414.ghome.camel.processors.GetEntityType;

import h4414.ghome.camel.routes.specific.OfflineModeRoutes;
import h4414.ghome.camel.processors.CapteurProcessor;
import h4414.ghome.camel.processors.PieceProcessor;
import h4414.ghome.entities.Historique;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import trames.RecuperateurTrame;
import javax.mail.internet.InternetAddress;
import org.apache.camel.component.gae.mail.GMailBinding;

/**
 *
 * @author Mathis
 */
public class MainRoutes extends RouteBuilder{
    private final String PERSISTANCE_UNIT_NAME = "4414_ghhome_war_1.0-SNAPSHOTPU";

    private PresenceRuleProcessor presenceRuleProcessor = new PresenceRuleProcessor();
    private CapteurProcessor capteurprocessor = new CapteurProcessor(); 
    private PieceProcessor pieceProcessor = new PieceProcessor();
    private DataBaseReader dbReader = new DataBaseReader();
    private DataToJson dataToJson = new DataToJson();
    private GetEntityType getQueryParams = new GetEntityType();

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
            //Thread listener = new Thread(recuperateur);
            //listener.start();
            //EntityManager eManager = factory.createEntityManager();
            ContextInitializer ctxInit = new ContextInitializer(recuperateur);
        
            from("direct:capteur")
            .to("direct:historiqueToDb");
          
            from("jetty:http://localhost:8087/test")
               
                    .to("direct:notifyUser");
        /*            .process(new Processor(){
                    @Override
                    public void process ( Exchange exchange ){
                        Historique hist = new Historique("test",new GregorianCalendar(),new GregorianCalendar());
                        exchange.getIn().setBody(hist);
                    }
                })
                
            .to("direct:historiqueToDb");*/
                
           
        //.to("http://localhost:8084/ghome/mainView.jsp?bridgeEndpoint=true"/*+&disableStreamCache=true"+*/);
        
        /*
         * definir une plage horaire sur laquelle l'on détecte une présence
         */
                from( "jetty:http://localhost:8087/addpiece")
                 .process(pieceProcessor)
                 
                 .to("jpa:Piece?persistenceUnit="+PERSISTANCE_UNIT_NAME)
        .log("ajout d'un Piece");
                
        from( "jetty:http://localhost:8087/addobject")
                 .process(capteurprocessor)
                 .to("jpa:Capteur?persistenceUnit="+PERSISTANCE_UNIT_NAME)
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
        /*
         * route qui envoie un objet historique dans la db
         * @ input : un objet historique dans le body
         * @ output : demarre la route qui pull les historiques et la suspend juste apres
         *              --> cause du spam dans les logs
         */
        from("direct:historiqueToDb")
                .to("jpa:Historique?persistenceUnit="+PERSISTANCE_UNIT_NAME)
                .log("Historique added to DB");
                //.to("controlbus:route?routeId=historiqueDbPull&action=resume")
                //.delay(1000)
                //.to("controlbus:route?routeId=historiqueDbPull&action=suspend");
                
        /*
         * Route activee des que l'on ajoute un historique dans la Db
         * 
         */
        /* from("jpa:Historique?persistenceUnit="+PERSISTANCE_UNIT_NAME+"&consumeDelete=false&maximumResults=1&consumer.initialDelay=0&consumer.query=select o from Historique o")
                .id("historiqueDbPull")
                .to("log:obj retrieved?showAll=true");
          */      
        
        from ( "direct:notifyUser")
            .setBody().constant("hello world")
             
            .removeHeaders("*")
            .to("smtp://localhost?username=ghomeadmin&password=mouton&from=ghomeadmin@localdomain.com&subject=test&to=mathis.loriginal@gmail.com")
        .log("an email has been sent");
        
        
        from ( "jetty:http://localhost:8087/getdata")
                /*
                 *  ça lit les données :)
                 *  les parametres sont passes par requete http get
                 * 
                 * @param name (obligatoire): le nom du type d'entite a recuperer
                 * @param nb (optionnel) : le nombre d'entites a recuperer ( recupere les entites les plus recentes )
                 * 
                 * @return : une liste d'objets json de l'entite correspondante dans le corps du message
                 */
                .process(getQueryParams)
                .choice()
                    .when().simple("${header.go}")
                    //.setProperty("entityName",constant("Historique"))
                        .process(dbReader)
                        .process(dataToJson)
                    .otherwise()
                        .log ( "invalid request :)")
                        //.setBody().constant("invalid request")
                        .setHeader(Exchange.HTTP_RESPONSE_CODE).constant(400)
                 .end()
                //.enrich("jpa:Historique?persistenceUnit="+PERSISTANCE_UNIT_NAME+"&consumeDelete=false&maximumResults=5&consumer.query=select o from Historique o")
                
                
                .to("log:obj retrieved")
                
        .log("lol");
        
        //from("").to("jpa:Historique?persistenceUnit="+PERSISTANCE_UNIT_NAME);
    }
    
    
    
}
