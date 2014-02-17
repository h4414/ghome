/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import h4414.ghome.entities.Historique;
import h4414.ghome.vues.JsonUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 *
 * @author Mathis
 */
public class DataToJson implements Processor{
    
    
    /*
     * input :  liste de données (n'importe lesquelles) dans les parametres de l'echange
     * output : string json dans le body du message
    */
    public void process (Exchange ex ){
        JsonFactory factory = new JsonFactory();
        //ReglePresence reglePresence = new ReglePresence("numeroCapteur", new Date(),new Date() );
        List datas = ex.getProperty("dataRetrieved", List.class);
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        
        ObjectWriter oWriter = mapper.writer();
        StringWriter sWriter = new StringWriter();
        try { 
            oWriter.writeValue(sWriter, datas); 
        } 
        catch (IOException e) {
            Logger.getLogger(PresenceRuleProcessor.class.getName()).log(Level.SEVERE, null, e);
        } 
        String resultat = sWriter.toString();
        //System.out.println("resultat : "+resultat);
        ex.getIn().setBody(resultat);
    }
    
}
