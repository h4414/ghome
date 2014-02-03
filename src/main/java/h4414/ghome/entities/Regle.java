/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
/**
 *
 * @author Jérémy
 */
@Entity
public class Regle implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    
    private String nom;
      
    private String piece;
   
    private int idfille;
            
    public enum TypeRegle{
        PRESENCE
    }
    private TypeRegle type;

    

    public String getNom() {
        return nom;
    }

    public String getPiece() {
        return piece;
    }

    public TypeRegle getType() {
        return type;
    }      
    
}
