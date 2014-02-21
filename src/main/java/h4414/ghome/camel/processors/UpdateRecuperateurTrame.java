/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import h4414.ghome.entities.Capteur;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import trames.RecuperateurTrame;

/**
 *
 * @author Mathis
 */
public class UpdateRecuperateurTrame implements Processor{
    
    private RecuperateurTrame recupTrames;
    
    public UpdateRecuperateurTrame ( RecuperateurTrame rec ){
       this.recupTrames = rec; 
    }
    
    public void process (Exchange ex ){
        Capteur capteur = ex.getIn().getBody(Capteur.class);
        this.recupTrames.update(capteur);
    }
    
    
}
