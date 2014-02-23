/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Jérémy
 */
@Entity
public class PlageHoraire implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar debut;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fin;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.debut);
        hash = 17 * hash + Objects.hashCode(this.fin);
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
        final PlageHoraire other = (PlageHoraire) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.debut, other.debut)) {
            return false;
        }
        if (!Objects.equals(this.fin, other.fin)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDebut() {
        return debut;
    }

    public void setDebut(Calendar debut) {
        this.debut = debut;
    }

    public Calendar getFin() {
        return fin;
    }

    public void setFin(Calendar fin) {
        this.fin = fin;
    }

    public PlageHoraire(Calendar debut, Calendar fin) {
        this.debut = debut;
        this.fin = fin;
    }
    public PlageHoraire(){}
        
    public boolean estDansPlage (Calendar dateTrame){
        return (this.debut.before(dateTrame) && dateTrame.before(fin));
    }
}
