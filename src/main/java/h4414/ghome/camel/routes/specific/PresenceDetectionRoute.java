/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.routes.specific;

import java.util.Date;
import java.util.List;
import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author Mathis
 */
public class PresenceDetectionRoute extends RouteBuilder{

    protected String SensorAddress = "??"; //addresse à interroger pour obtenir l'état
    //du capteur de présence
    
    public PresenceDetectionRoute ( String SensorAddress, String cronExpr ){ //cron expression correspond aux périodes pendant lesquelles les routes sont actives
        super();
        
    }
    
    @Override
    public void configure() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
