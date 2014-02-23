/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import h4414.ghome.vues.PersistanceUtils;
import javax.persistence.EntityManagerFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spi.Registry;

/**
 *
 * @author Mathis
 */
public class DBInit implements Processor{
    
    public void process (Exchange ex ){
        Registry reg = ex.getContext().getRegistry();
        Object emf = reg.lookupByName("entityManagerFactory");
        if ( emf != null ){
            if ( emf instanceof EntityManagerFactory ){
                EntityManagerFactory emFactory = (EntityManagerFactory )( emf);
                PersistanceUtils.setEmf(emFactory);
            }
        }
    }
    
}
