/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import h4414.ghome.entities.Action;
import h4414.ghome.entities.AllumerPrise;
import h4414.ghome.entities.EnvoyerMail;
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
            Iterator<Regle> mRegles= matchedRegles.iterator();
            List<Action> actions = new ArrayList();
            while ( mRegles.hasNext()){
                actions.addAll(mRegles.next().getActions());
            }
            Iterator<Action> a = actions.iterator();
            while ( a.hasNext()){
                Action act = a.next();
                ex.setProperty("listeEmail",new ArrayList<String>());
                ex.setProperty("listePrises",new ArrayList<AllumerPrise>());
                if ( act instanceof  EnvoyerMail){
                    EnvoyerMail ac = (EnvoyerMail) act;
                    ex.setProperty("email", true);
                    List l = ex.getProperty("listeEmail",ArrayList.class);
                    l.add(ac.getAdresse());
                     ex.setProperty("listeEmail",l);
                }
                else if ( act instanceof AllumerPrise){
                    AllumerPrise ac = (AllumerPrise) act;
                    ex.setProperty("prise",true);
                    List l = ex.getProperty("listePrises",ArrayList.class);
                    l.add(ac);
                     ex.setProperty("listePrises",l);
                }
            }
        }
        
    }
    
}
