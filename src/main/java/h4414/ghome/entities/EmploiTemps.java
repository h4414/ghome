/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Jérémy
 */
public class EmploiTemps {
    public enum JourDeLaSemaine {
        Dimanche,
        Lundi,
        Mardi,
        Mercredi,
        Jeudi,
        Vendredi,
        Samedi;
    }
    private JourDeLaSemaine jourDebut;
    private int heureDebut;   
    private JourDeLaSemaine jourFin;
    private int heureFin;

    public EmploiTemps(JourDeLaSemaine jourDebut, int heureDebut, JourDeLaSemaine jourFin, int heureFin) {
        this.jourDebut = jourDebut;
        this.heureDebut = heureDebut;
        this.jourFin = jourFin;
        this.heureFin = heureFin;
    }
    
    public boolean EstDansPlage (GregorianCalendar dateTrame){
        int ceJour = dateTrame.get(Calendar.DAY_OF_WEEK);
        int cetteHeure = dateTrame.get(Calendar.HOUR_OF_DAY);
        if (jourDebut.ordinal()<jourFin.ordinal()){
            if (jourDebut.ordinal()<ceJour && ceJour<jourFin.ordinal()){
                return true;
            }
            else if (jourDebut.ordinal()==ceJour){
                return (heureDebut<=cetteHeure);
            }
            else if (jourFin.ordinal()==ceJour){
                return (cetteHeure<heureFin);
            }
        }
        else if (jourDebut.ordinal()==jourFin.ordinal()){
            if (heureDebut<=heureFin){
                return (heureDebut<=cetteHeure && cetteHeure<heureFin);
            }
            else{
                return (heureDebut<=cetteHeure || cetteHeure<heureFin);
            }
        }
        else if (jourDebut.ordinal()>jourFin.ordinal()){
            if (jourDebut.ordinal()<ceJour || ceJour<jourFin.ordinal()){
                return true;
            }
            else if (jourDebut.ordinal()==ceJour){
                return (heureDebut<=cetteHeure);
            }
            else if (jourFin.ordinal()==ceJour){
                return (cetteHeure<heureFin);
            }            
        }
        return false;
    }

}
