/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author Mathis
 */
public class AddRoute implements Processor {

    protected RouteBuilder routes;
    public AddRoute ( RouteBuilder routes ){
        this.routes = routes;
    }
    
    @Override
    public void process(Exchange ex) throws Exception {
        CamelContext context = ex.getContext();
        context.addRoutes ( routes );
    }
    
}
