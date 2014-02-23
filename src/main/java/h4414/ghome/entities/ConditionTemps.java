/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mathis
 */
@Entity
@DiscriminatorValue("CONDITION_TEMPS")
public class ConditionTemps extends RegleCondition implements Serializable{

    @ManyToOne
    private EmploiTemps emploitemps;

    public EmploiTemps getEmploitemps() {
        return emploitemps;
    }

    public void setEmploitemps(EmploiTemps emploitemps) {
        this.emploitemps = emploitemps;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.emploitemps);
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
        final ConditionTemps other = (ConditionTemps) obj;
        if (!Objects.equals(this.emploitemps, other.emploitemps)) {
            return false;
        }
        return true;
    }
    
    public ConditionTemps ( ){
        
    }
    public ConditionTemps(  EmploiTemps emploitemps ){
        this.emploitemps = emploitemps;
        this.pieces = new ArrayList();
    }
    
    
    @Override
    public boolean isMet() {
        PlageHoraire p = this.emploitemps.chercherPlage(new GregorianCalendar());
        if ( p != null ){
            return true;
        }
        else{
            return false;
        }
    
    }
    
}
