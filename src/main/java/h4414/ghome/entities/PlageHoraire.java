/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;

import java.util.Calendar;

/**
 *
 * @author Jérémy
 */
public class PlageHoraire{
    private Calendar debut;
    private Calendar fin;

    public PlageHoraire(Calendar debut, Calendar fin) {
        this.debut = debut;
        this.fin = fin;
    }
        
    public boolean estDansPlage (Calendar dateTrame){
        return (this.debut.before(dateTrame) && dateTrame.before(fin));
    }
}
