/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 *
 * @author Jérémy
 */
public class PlageHoraire{
    private GregorianCalendar debut;
    private GregorianCalendar fin;

    public PlageHoraire(GregorianCalendar debut, GregorianCalendar fin) {
        this.debut = debut;
        this.fin = fin;
    }
        
    public boolean estDansPlage (GregorianCalendar dateTrame){
        return (this.debut.before(dateTrame) && dateTrame.before(fin));
    }
}
