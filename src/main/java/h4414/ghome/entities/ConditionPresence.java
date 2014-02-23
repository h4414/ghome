/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import h4414.ghome.vues.PersistanceUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Mathis
 */
@Entity
public class ConditionPresence extends Condition implements Serializable{

    
    @Temporal(javax.persistence.TemporalType.TIME)
    private Calendar beginDetect;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Calendar endDetect;
    
    public ConditionPresence() {
    }
    
    
    public ConditionPresence( List<Capteur> capteurs, Calendar begin, Calendar end ){
        this.capteurs = capteurs;
        this.beginDetect = begin;
        this.endDetect = end;
    }

  
    /*
     * implementation fausse : l'info sur la presence n'est pas stockée dans les calendriers de l'objet historique ...
     */
    @Override
    public boolean isMet(Historique historique ) {
        if ( historique.getDebutPresence().getTime().after( this.beginDetect.getTime()) 
                && historique.getDebutPresence().getTime().before( this.endDetect.getTime())
             || historique.getFinPresence().getTime().before( this.endDetect.getTime()) 
                && historique.getFinPresence().getTime().before( this.beginDetect.getTime())){
            return true;
        }
        else{
            return false;
        }
        
        
    }
    
    
}
