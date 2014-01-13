/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author salmazejli
 */
@ Entity
public class Capteur implements Serializable{
     @Id
    @GeneratedValue
      private int id;
      private String idCapteur;
      private String NomCapteur;
       public Capteur(){}
       public Capteur( String idCapteur,String NomCapteur ){
        this.idCapteur = idCapteur;
        this.NomCapteur = NomCapteur;}
          public int getId(){
          return id;
    }
          public void setId(int id) {
        this.id = id;
    }
            public String getIdCapteur() {
        return idCapteur;
    }
              public String setIdCapteur() {
        return idCapteur;
    }
               public String getNomCapteur() {
        return idCapteur;
    }
               public String setNomCapteur() {
        return idCapteur;
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
            if ( ! ( o instanceof Capteur ) ){
                return false;
            }
            else{
                Capteur autre = (Capteur ) o ;
                if ( autre.getId() != this.getId() ){
                    return false;
                }
                else{
                    if ( ! autre.getIdCapteur().equals( this.getIdCapteur())){
                        return false;
                    }
                    else{
                        if ( ! autre.getNomCapteur().equals( this.getNomCapteur() ) ){
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
    
 public int hashCode(){
        return ( ( this.getIdCapteur()+ this.getNomCapteur()).hashCode());
    }
     @Override
    public String toString(){
        return "["+this.getClass()+" "+this.getIdCapteur()+" "+ this.getNomCapteur()+"]";
    }

    
}