/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import h4414.ghome.entities.Historique;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spi.Registry;

/**
 *
 * @author Mathis
 */
public class DataBaseReader implements Processor{
    
    
    /*   ^
     *  /!\ coucou :)
     * /___\
     *  
     * passer le nom de l'entité à mettre dans la requete jpql dans une proprité de l'change
     * 
     * le resultat est dans le body du message
     * 
     * une seule jpql pour l'instant, je suppose que c'est assez mais si non passez la genre en propriete de l'echange
     */
    @Override
    public void process ( Exchange ex ){
        Registry reg = ex.getContext().getRegistry();
        String entityName = ex.getProperty("entityName",String.class);
        Object emf = reg.lookupByName("entityManagerFactory");
        if ( emf != null ){
            if ( emf instanceof EntityManagerFactory ){
                EntityManagerFactory emFactory = (EntityManagerFactory )( emf);
                EntityManager em = emFactory.createEntityManager();
                List datas = em.createQuery("SELECT o FROM "+entityName+" o").getResultList();
                //System.out.println("Grrrreat success : "+datas);
                ex.setProperty("dataRetrieved", datas);
            }
            else{
                System.out.println("failed to retrieve emfactory : is instance of "+emf.getClass()+"");
            }
        }
        else{
            System.out.println("failed to retrieve emfactory : is null ...");
        }
    }
    
}
