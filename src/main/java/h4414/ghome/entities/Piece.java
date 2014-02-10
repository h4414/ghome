/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Jérémy
 */
@Entity
public class Piece implements Serializable{
    
    @Id @GeneratedValue
    private int id;
    private String nom;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="piece")
    private List<Capteur> capteurs;
    
    public Piece(){
        this.capteurs = new ArrayList<Capteur>();
    }
    public Piece(Capteur capteur){
        this.capteurs = new ArrayList<Capteur>();
        this.capteurs.add(capteur);
    }
    public void addCapteur(Capteur capteur){
        this.capteurs.add(capteur);
    }

    public String getNom() {
        return nom;
    }

    public List<Capteur> getCapteurs() {
        return capteurs;
    }
    
    
}