/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.camel.processors;
import  h4414.ghome.entities.Piece;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.AsyncCallback;
import org.apache.camel.AsyncProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

/**
 *
 * @author Thomas
 */
public class PieceProcessor implements Processor{
    
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
            JsonNode nom = node.path("nom");
            
             Piece newPiece = new Piece(nom.asText(),null);
            in.setBody(newPiece);
            
        } catch (IOException ex) {
            Logger.getLogger(PresenceRuleProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        
    }
    


