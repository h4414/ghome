/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import trames.RecuperateurTrame;

/**
 *
 * @author Mathis
 */
public class ContextInitializer implements Processor {
    
    public RecuperateurTrame rec;
    public ContextInitializer( RecuperateurTrame  rec){
        this.rec = rec;
    }
    
    public void process ( Exchange ex){
        CamelContext ctx = ex.getContext();
        this.rec.setContext(ctx);
         Thread listener = new Thread(this.rec);
            listener.start();
    }
    
}
