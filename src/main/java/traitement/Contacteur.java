/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitement;

import h4414.ghome.entities.Historique;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.CamelContext;
import trames.Trame;

/**
 *
 * @author Qdunoyer
 */
public class Contacteur implements Runnable{
    private static List<Historique> listeTrame = new ArrayList<Historique>();
    
    Trame trameTraitee;
    CamelContext ctx;
    
    /**Permet de savoir si une porte est fermé ou non.
     * 
     * @param trame
     * @return True si la porte est fermé.
     */
    public static boolean getContact(Trame trame){
        boolean bool = trame.getDataX_Y(0, 1)  == 1;
        return bool;
    };
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
