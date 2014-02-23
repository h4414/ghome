/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Jérémy
 */
@Entity
public class EmploiTemps implements Serializable{

    @Id
    @GeneratedValue
    private int id;
    
    @ManyToMany
    private List<PlageHoraire> plages;

    public EmploiTemps() {
        this.plages = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.plages);
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
        final EmploiTemps other = (EmploiTemps) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.plages, other.plages)) {
            return false;
        }
        return true;
    }
    
    public boolean ajouterPlage (PlageHoraire plage){
        this.plages.add(plage);
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PlageHoraire> getPlages() {
        return plages;
    }

    public void setPlages(List<PlageHoraire> plages) {
        this.plages = plages;
    }
    
    
    
    public PlageHoraire chercherPlage (Calendar dateTrame){
        for (PlageHoraire p:plages){
            if (p.estDansPlage(dateTrame)){
                return p;
            }
        }
        return null;
    }
    
}
