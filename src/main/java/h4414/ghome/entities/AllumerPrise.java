/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import traitement.Actionneur;
import trames.RecuperateurTrame;

/**
 *
 * @author Mathis
 * 
 * pour le moment on n'a qu'une seule prise, id codé en dur ...
 */
@Entity
@DiscriminatorValue("ALLUMER_PRISE")
public class AllumerPrise extends Action implements Serializable{ 
    
    private String id;
    
    protected AllumerPrise(){
    }
    
    public AllumerPrise (String id ){
        this.id = id;
    }
    
    public void allumer(String id){
        String envoi = Actionneur.allumerPrise(id);
        RecuperateurTrame.envoyerTrame(envoi);
    }
    
    public void eteindreString(String id){
        String envoi = Actionneur.eteindrePrise(id);
        RecuperateurTrame.envoyerTrame(envoi);
    }
}
