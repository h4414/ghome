/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Mathis
 */
@ Entity
public class ReglePresence extends Regle{
    
    @Id
    @GeneratedValue
    private int id;
    
    private String idCapteur;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateBegin;

    @Override
    public String toString() {
        return "ReglePresence{" + "id=" + id + ", idCapteur=" + idCapteur + ", begin=" + dateBegin + ", end=" + dateEnd + '}';
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEnd;
    
    public  ReglePresence(){
        
    }
    
    public  ReglePresence( String idCapteur, Date begin, Date end){
        this.idCapteur=idCapteur;
        this.dateBegin = begin;
        this.dateEnd = end;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.idCapteur);
        hash = 89 * hash + Objects.hashCode(this.dateBegin);
        hash = 89 * hash + Objects.hashCode(this.dateEnd);
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
        final ReglePresence other = (ReglePresence) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.idCapteur, other.idCapteur)) {
            return false;
        }
        if (!Objects.equals(this.dateBegin, other.dateBegin)) {
            return false;
        }
        if (!Objects.equals(this.dateEnd, other.dateEnd)) {
            return false;
        }
        return true;
    }

    

    public String getIdCapteur() {
        return idCapteur;
    }

    public void setIdCapteur(String idCapteur) {
        this.idCapteur = idCapteur;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    
    
    
}
