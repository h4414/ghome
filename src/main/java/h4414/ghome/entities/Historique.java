
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    @ManyToOne(optional = false)
    private Capteur capteur;
    //private String idCapteur;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar debutPresence;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar finPresence;
    @Column(nullable=true)
    private double donnee;

    public Historique(){}
        
    
    public Historique ( Capteur capteur, Calendar debutPresence, Calendar finPresence ){
        this.capteur = capteur;
        //this.idCapteur = idCapteur;
        this.debutPresence = debutPresence;
        this.finPresence = finPresence;
        /*System.out.println("NOUVEL HISTO");
        System.out.println(debutPresence.toString());
        System.out.println(finPresence.toString());*/
    }
    
    public Historique ( Capteur capteur, Calendar debutPresence, Calendar finPresence, double data ){
        this.capteur = capteur;
        //this.Capteur = idCapteur;
        this.debutPresence = debutPresence;
        this.finPresence = finPresence;
        this.donnee = data;
        /*System.out.println("NOUVEL HISTO");
        System.out.println(debutPresence.toString());
        System.out.println(finPresence.toString());*/
    }
    
    public Historique ( Capteur capteur, Calendar debutPresence, Calendar finPresence, int data ){
        this.capteur = capteur;
        //this.idCapteur = idCapteur;
        this.debutPresence = debutPresence;
        this.finPresence = finPresence;
        this.donnee = data;
        /*System.out.println("NOUVEL HISTO");
        System.out.println(debutPresence.toString());
        System.out.println(finPresence.toString());*/
    }
    
    public Historique ( Capteur capteur, Calendar debutPresence, Calendar finPresence, boolean data ){
        this.capteur = capteur;
        //this.idCapteur = idCapteur;
        this.debutPresence = debutPresence;
        this.finPresence = finPresence;
        if (data){
            this.donnee =  1;
        }
        else {
            this.donnee = 0;
        }
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
        return capteur.getIdCapteur();
    }

    public void setIdCapteur(String idCapteur) {
        this.capteur.setIdCapteur(idCapteur);
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
    
    public double getDonnee(){
        return this.donnee;
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

