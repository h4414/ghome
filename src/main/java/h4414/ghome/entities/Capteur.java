/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    public enum TypeCapteur{
      PRESENCE,
      TEMPERATURE,
      CONTACTEUR,
      BOUTON,
      PRISE,
      TEST
    };
    @Enumerated(EnumType.STRING)
    TypeCapteur type;

    public TypeCapteur getType() {
        return type;
    }
    private String idCapteur;
    private String NomCapteur;
    @ManyToOne @JoinColumn(name="IDPIECE", nullable=false)
    private Piece piece;
    
    public Capteur(){}
    public Capteur( String idCapteur,String NomCapteur, Piece piece,TypeCapteur type  ){
        
        this.idCapteur = idCapteur;
        this.NomCapteur = NomCapteur;
        this.piece=piece;
        this.type = type;
    }
    public int getId(){
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
    
    public String getNomCapteur() {
        return NomCapteur;
    }
    public void setNomCapteur(String nomCapteur) {
        this.NomCapteur = nomCapteur;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
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
