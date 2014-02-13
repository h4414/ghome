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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

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
            if (Security.CheckReferrer(in)){
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
                 Piece piece1=new Piece(piece.asText(),null);
                 Capteur capteur = new Capteur(id.asText(),nomCapteur.asText(),piece1,typeCapteur);
                in.setBody(capteur);
                System.out.println (capteur);
            }
            else{
                System.out.println ("MAUVAIS REFERRER");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PresenceRuleProcessor.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }
        
        
        
        
        
        
}
    


