/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.camel.processors;
import  h4414.ghome.entities.Piece;
import  h4414.ghome.entities.Capteur;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.spi.Registry;

/**
 *
 * @author Mathis
 */
public class CapteurProcessor implements Processor{

    @Override
    public void process( Exchange exc ){
        Message in = exc.getIn();
        JsonFactory jFactory = new JsonFactory();
        JsonParser jp;
        System.out.println("processing");
        try {
            String input = in.getBody(String.class);
            //System.out.println(input);
            //jp = jFactory.createParser(in.getBody(InputStream.class));
            jp = jFactory.createParser(input);
            ObjectMapper mapper = new ObjectMapper();

            
            JsonNode node = mapper.readTree(jp);
            System.out.println(node);
            JsonNode type = node.path("type");
            Capteur.TypeCapteur typeCapteur;
            typeCapteur=Capteur.TypeCapteur.valueOf(type.asText());
            //System.out.println(type.asText());
            JsonNode id = node.path("id");
            JsonNode nomCapteur = node.path("nomCapteur");
            JsonNode piece = node.path("piece");

            Registry reg = exc.getContext().getRegistry();
        String entityName = exc.getProperty("entityName",String.class);
            Object emf = reg.lookupByName("entityManagerFactory");

                    if ( emf != null ){
            if ( emf instanceof EntityManagerFactory ){
                EntityManagerFactory emFactory = (EntityManagerFactory )( emf);
                    EntityManager em = emFactory.createEntityManager();

            Piece datas =  (Piece) em.createQuery("SELECT o FROM Piece o WHERE o.nom=:p_nom" )
                    .setParameter("p_nom",piece.asText())
                            .getSingleResult();

             Capteur capteur = new Capteur(id.asText(),nomCapteur.asText(),datas,typeCapteur);
                    in.setHeader("Access-Control-Allow-Origin", "*");
                    in.setBody(capteur);
            System.out.println (capteur);
            }
            else
            {

                }
                    }
                    else
                    {

            }
        } catch (IOException ex) {
            Logger.getLogger(PresenceRuleProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
        
        
        
        
}
    


