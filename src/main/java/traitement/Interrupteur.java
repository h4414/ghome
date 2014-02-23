/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitement;

import h4414.ghome.entities.Historique;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultProducerTemplate;
import trames.Trame;

/**
 *
 * @author qdunoyer
 */
public class Interrupteur implements Runnable{
        private static List<Historique> listeTrame = new ArrayList<Historique>();
    
    Trame trameTraitee;
    CamelContext ctx;
    
    public Interrupteur(Trame trameTraitee, CamelContext ctx){
        this.trameTraitee = trameTraitee;
        this.ctx = ctx;
    }
    
    /**Permet de savoir si une porte est fermé ou non.
     * 
     * @param trame
     * @return True si la porte est fermé.
     */
    public static int getBouton(Trame trame){
        int bouton;
        if (trame.getData()==0){
            bouton = 0;
        }
        else{
            switch(trame.getDATA3()){
                case 1*16:
                    bouton = 10; // A1
                    break;
                case 3*16:
                    bouton = 20; // A0
                    break;
                case 5*16:
                    bouton = 01; // B1
                    break;
                case 7*16:
                    bouton = 02; // B0
                    break;
                case 1*16+5:
                    bouton = 11; // A1 et B1
                    break;
                case 1*16+7:
                    bouton = 12; // A1 et B0
                    break;
                case 3*16+5:
                    bouton = 21;
                    break;
                case 3*16+7:
                    bouton = 22;
                    break;
                default:
                    throw new NumberFormatException("Trame non comforme a ce qui était attendu.");
            }
        }
        return bouton;
    };
    

    
    @Override
    public void run() {
        //TODO : Faire la gestion de règles.
        
    }
}
