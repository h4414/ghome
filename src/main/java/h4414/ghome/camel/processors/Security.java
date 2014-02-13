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
            
    public static boolean CheckReferrer(Message in) throws SecurityException{
        String referrer = in.getHeader("referer", String.class);
        return referrer.startsWith(URL);
        
        
    }
    
}
