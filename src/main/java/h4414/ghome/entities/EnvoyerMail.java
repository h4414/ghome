/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Mathis
 * 
 */

@Entity
public class EnvoyerMail implements Action, Serializable{
    @Id
    @GeneratedValue
    private int id;
    
    private String adresse;
    
    public void EnvoyerMail( String adresse ){
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.adresse);
        return hash;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

   

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EnvoyerMail other = (EnvoyerMail) obj;
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        return true;
    }
    
}
