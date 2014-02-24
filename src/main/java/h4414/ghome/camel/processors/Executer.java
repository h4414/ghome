/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.camel.processors;

import h4414.ghome.entities.AllumerPrise;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultProducerTemplate;
import traitement.Presence;

/**
 *
 * @author Thomas
 */
public class Executer implements Processor{

    @Override
    public void process(Exchange exchng) {
        List<AllumerPrise> listePrise;
        List<String> listeEmail;
        ProducerTemplate pdt = new DefaultProducerTemplate(exchng.getContext());
        if ((boolean)exchng.getProperty("prise")){
            listePrise = (List<AllumerPrise>) exchng.getProperty("listePrises");
            for (int i = 0 ; i<listePrise.size() ; i++){
                listePrise.get(i).allumer(listePrise.get(i).getIdPrise());
            }
        }
        if ((boolean)exchng.getProperty("email")){
            listeEmail = (List<String>) exchng.getProperty("ListeEmail");
            for (int i =0 ; i<listeEmail.size(); i++){
                 try {
                    pdt.start();
                    pdt.sendBody("smtp://localhost?username=ghomeadmin&password=mouton&from=ghomeadmin@localdomain.com&subject=test&to="+listeEmail.get(i),"Demande d'envoi d'email");
                    pdt.stop();
                } catch (Exception ex) {
                    Logger.getLogger(Presence.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
