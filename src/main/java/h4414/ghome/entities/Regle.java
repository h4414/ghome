/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
/**
 *
 * @author Jérémy
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ruletype",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("Regle")
public class Regle implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    
    private String nom;
    @ManyToOne  
    private Piece piece;
     
    public String getNom() {
        return nom;
    }

    public Piece getPiece() {
        return piece;
    }
     
    
}
