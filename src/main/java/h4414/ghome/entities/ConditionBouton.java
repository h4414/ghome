/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.util.List;
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
    private int boutonConcerne;
    public ConditionBouton ( ){
        
    }
    
    public ConditionBouton ( String id, int boutonsConcernes){
        this.capteurId = id;
        this.boutonConcerne=boutonsConcernes;
    }

    public int  getBoutonConcerne() {
        return boutonConcerne;
    }

    public void setBoutonConcerne(int boutonConcerne) {
        this.boutonConcerne = boutonConcerne;
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
