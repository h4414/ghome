/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Mathis
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="conditiontype",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("CONDITION")
public abstract class Condition {
    @Id
    private int id;
    
    public abstract boolean isMet();
    
    protected Piece piece;

    

    

    public int getId() {
        return id;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
