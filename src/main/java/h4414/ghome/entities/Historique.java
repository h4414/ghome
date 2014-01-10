/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Mathis
 */
@ Entity
public class Historique implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String idCapteur;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date debutPresence;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finPresence;

    public Historique(){}
        
    
    public Historique ( String idCapteur, Date debutPresence, Date finPresence ){
        this.idCapteur = idCapteur;
        this.debutPresence = debutPresence;
        this.finPresence = finPresence;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCapteur() {
        return idCapteur;
    }

    public void setIdCapteur(String idCapteur) {
        this.idCapteur = idCapteur;
    }

    public Date getDebutPresence() {
        return debutPresence;
    }

    public void setDebutPresence(Date debutPresence) {
        this.debutPresence = debutPresence;
    }

    public Date getFinPresence() {
        return finPresence;
    }

    public void setFinPresence(Date finPresence) {
        this.finPresence = finPresence;
    }
    
    @Override
    public boolean equals ( Object o ){
        boolean resultat = false;
        if ( o == null ){
            return false;
        }
        if( o == this ){
            return true;
        }else{
            if ( ! ( o instanceof Historique ) ){
                return false;
            }
            else{
                Historique autre = ( Historique ) o ;
                if ( autre.getId() != this.getId() ){
                    return false;
                }
                else{
                    if ( ! autre.getIdCapteur().equals( this.getIdCapteur())){
                        return false;
                    }
                    else{
                        if ( ! autre.getDebutPresence().equals( this.getDebutPresence() ) ){
                            return false;
                        }
                        else{
                            if ( ! autre.getFinPresence().equals( this.getFinPresence())){
                                return false;
                            }
                            else{
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }
    public int hashCode(){
        return ( ( this.getIdCapteur()+ this.getDebutPresence().toString()+this.getFinPresence().toString()).hashCode());
    }
    
}
