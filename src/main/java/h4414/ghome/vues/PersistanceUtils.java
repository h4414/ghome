/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.vues;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mathis
 */
public class PersistanceUtils {
    
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        PersistanceUtils.emf = emf;
    }
    
    
    
}
