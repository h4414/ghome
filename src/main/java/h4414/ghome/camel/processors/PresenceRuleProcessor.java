/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import h4414.ghome.entities.InfoRegles;
import h4414.ghome.entities.ReglePresence;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

/**
 *
 * @author Mathis
 */
public class PresenceRuleProcessor implements Processor{
    
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
            //System.out.println(type.asText());
            JsonNode begin = node.path("time").path("begin");
            JsonNode end = node.path("time").path("end");
            
            SimpleDateFormat sd = new SimpleDateFormat("h:mma");
            Date b = sd.parse(begin.asText());
            Date e = sd.parse(end.asText());
            ReglePresence regle = new ReglePresence("numcapteur",b,e);
            //InfoRegles infos = new InfoRegles(regle,"",InfoRegles.TypeRegle.PRESENCE);
            in.setBody(regle);
            //in.setBody(infos);
            System.out.println (regle);
        } catch (IOException ex) {
            Logger.getLogger(PresenceRuleProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PresenceRuleProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
