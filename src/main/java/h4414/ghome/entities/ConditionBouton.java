/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Mathis
 */
@Entity
@DiscriminatorValue("CONDITION_BOUTON")
public class ConditionBouton extends RegleCondition{

    
    private String capteurId;
    
    public ConditionBouton ( ){
        
    }
    
    public ConditionBouton ( String id){
        this.capteurId = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.capteurId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConditionBouton other = (ConditionBouton) obj;
        if (!Objects.equals(this.capteurId, other.capteurId)) {
            return false;
        }
        return true;
    }

    public String getCapteurId() {
        return capteurId;
    }

    public void setCapteurId(String capteurId) {
        this.capteurId = capteurId;
    }
    
    @Override
    public boolean isMet() {
        return true;
    }
    
}
