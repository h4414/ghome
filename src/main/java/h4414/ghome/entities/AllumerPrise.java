/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Mathis
 * 
 * pour le moment on n'a qu'une seule prise, id codé en dur ...
 */
@Entity
public class AllumerPrise implements Action, Serializable{
    @Id
    @GeneratedValue
    private int id;

    
    
    public AllumerPrise ( ){
        
    }
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
