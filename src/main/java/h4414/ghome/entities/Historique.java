/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.io.Serializable;
import java.util.Calendar;
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
    @Temporal(javax.persistence.TemporalType.TIME)
    private Calendar debutPresence;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Calendar finPresence;
    private double data;

    public Historique(){}
        
    
    public Historique ( String idCapteur, Calendar debutPresence, Calendar finPresence ){
        this.idCapteur = idCapteur;
        this.debutPresence = debutPresence;
        this.finPresence = finPresence;
        /*System.out.println("NOUVEL HISTO");
        System.out.println(debutPresence.toString());
        System.out.println(finPresence.toString());*/
    }
    
    public Historique ( String idCapteur, Calendar debutPresence, Calendar finPresence, double data ){
        this.idCapteur = idCapteur;
        this.debutPresence = debutPresence;
        this.finPresence = finPresence;
        this.data = data;
        /*System.out.println("NOUVEL HISTO");
        System.out.println(debutPresence.toString());
        System.out.println(finPresence.toString());*/
    }
    
    public Historique ( String idCapteur, Calendar debutPresence, Calendar finPresence, int data ){
        this.idCapteur = idCapteur;
        this.debutPresence = debutPresence;
        this.finPresence = finPresence;
        this.data = data;
        /*System.out.println("NOUVEL HISTO");
        System.out.println(debutPresence.toString());
        System.out.println(finPresence.toString());*/
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

    public Calendar getDebutPresence() {
        return debutPresence;
    }

    public void setDebutPresence(Calendar debutPresence) {
        this.debutPresence = debutPresence;
    }

    public Calendar getFinPresence() {
        return finPresence;
    }

    public void setFinPresence(Calendar finPresence) {
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
    
    @Override
    public String toString(){
        return "["+this.getClass()+" "+this.getIdCapteur()+" "+ this.getDebutPresence()+"]";
    }
    
}
