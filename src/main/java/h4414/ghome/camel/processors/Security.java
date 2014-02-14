/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.camel.processors;

import org.apache.camel.Message;

/**
 *
 * @author Jérémy
 */
public class Security {
    
    private static String URL = "http://localhost:8084/ghome/html/";
            
    /**
     * Methode qui vérifie le referrer de la requête HTTP (=la page depuis laquelle
     * la requête a été effectuée) et vérifie que c'est bien notre site. Attention,
     * ca peut être falsifié donc c'est pas super probant.
     * @param in
     * @return vrai si ca vient bien de notre site
     */
    public static boolean CheckReferrer(Message in){
        String referrer = in.getHeader("referer", String.class);
        return referrer.startsWith(URL);
        
        
    }
    
}
