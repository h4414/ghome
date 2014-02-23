/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Mathis
 */
@Entity
public class ConditionTemperature extends Condition implements Serializable {

    
    
    
    @Override
    public boolean isMet(Historique historique) {
        return false;
    }
    
}
