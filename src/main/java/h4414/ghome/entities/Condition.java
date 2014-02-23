/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Mathis
 */
@Entity
public abstract class Condition {
    @Id
    private int id;
    
    public abstract boolean isMet(Historique historique);
    protected List<Capteur> capteurs;

    public List<Capteur> getCapteurs() {
        return capteurs;
    }

    public void setCapteurs(List<Capteur> capteurs) {
        this.capteurs = capteurs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
