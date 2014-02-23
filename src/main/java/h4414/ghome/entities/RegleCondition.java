/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mathis
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="conditiontype",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("REGLE_CONDITION")
public abstract class RegleCondition implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.pieces);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegleCondition other = (RegleCondition) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.pieces, other.pieces)) {
            return false;
        }
        return true;
    }
    
    public abstract boolean isMet();
    
    @ManyToMany
    protected List<Piece> pieces;

    

    

    public int getId() {
        return id;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPiece(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
