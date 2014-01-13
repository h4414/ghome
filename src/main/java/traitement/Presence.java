/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitement;

import trames.Trame;

/** 
 *
 * @author Jérémy
 */
public class Presence {
    
    public static boolean OccupancyDetected(Trame trame){
        return trame.getDataX_Y(0, 1) == 0;
    }

    
}
