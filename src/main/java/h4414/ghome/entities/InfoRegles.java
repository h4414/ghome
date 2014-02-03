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
public class InfoRegles implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    
    @OneToOne
    private Regle regle;
    
    private String piece;
    
    
    public enum TypeRegle{
        PRESENCE
    }
    private TypeRegle type;

    
    public InfoRegles() {
    }
    
    public InfoRegles(Regle regle, String piece, TypeRegle type) {
        this.regle = regle;
        this.piece = piece;
        this.type = type;
    }

    public Regle getRegle() {
        return regle;
    }

    public String getPiece() {
        return piece;
    }

    public TypeRegle getType() {
        return type;
    }

    

}
