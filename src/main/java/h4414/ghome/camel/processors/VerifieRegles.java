/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import h4414.ghome.entities.Regle;
import h4414.ghome.vues.PersistanceUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 *
 * @author Mathis
 */
public class VerifieRegles implements Processor {
    
    public void process (Exchange ex ){
     
        EntityManager em = PersistanceUtils.getEmf().createEntityManager();
        Query getRegles = em.createQuery("SELECT o FROM Regle o",Regle.class);
        List <Regle> regles = getRegles.getResultList();
        List<Regle> matchedRegles = new ArrayList();
        Iterator<Regle> itRegles = regles.iterator();
        while ( itRegles.hasNext()){
            Regle r = itRegles.next();
            if ( r.conditionsRemplies()){
                matchedRegles.add(r);
            }
        }
        if ( matchedRegles.isEmpty() ){
            //do nuthing
        }else{
            
        }
        
    }
    
}
