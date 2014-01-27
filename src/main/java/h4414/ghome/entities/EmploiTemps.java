/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Jérémy
 */
public class EmploiTemps {

    private List<PlageHoraire> plages;

    public EmploiTemps() {
        this.plages = new ArrayList<>();
    }
    
    public boolean ajouterPlage (PlageHoraire plage){
        this.plages.add(plage);
        return true;
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
