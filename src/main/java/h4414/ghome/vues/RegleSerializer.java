/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.vues;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import h4414.ghome.entities.Action;
import h4414.ghome.entities.AllumerPrise;
import h4414.ghome.entities.ConditionBouton;
import h4414.ghome.entities.ConditionContacteur;
import h4414.ghome.entities.ConditionPresence;
import h4414.ghome.entities.ConditionTemperature;
import h4414.ghome.entities.ConditionTemps;
import h4414.ghome.entities.EmploiTemps;
import h4414.ghome.entities.EnvoyerMail;
import h4414.ghome.entities.Piece;
import h4414.ghome.entities.PlageHoraire;
import h4414.ghome.entities.Regle;
import h4414.ghome.entities.RegleCondition;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mathis
 */
public class RegleSerializer extends StdSerializer<Regle>{
    
    public RegleSerializer( ){
        super ( Regle.class);
    }
    
    
    @Override
        public void serialize(Regle regle, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
            jg.writeStartObject();
            // recuperer la liste des pieces
            List <RegleCondition> conditions = regle.getConditions();
            Iterator<RegleCondition> lookinForPieces = conditions.iterator();
            List<Piece> pieces = new ArrayList();
            while (lookinForPieces.hasNext() ){
                RegleCondition rc = lookinForPieces.next();
                if ( rc instanceof ConditionPresence || rc instanceof ConditionContacteur || rc instanceof ConditionTemperature ){
                    pieces = rc.getPieces();
                    break;
                }
                
            }
            // ecrire les pieces
           jg.writeFieldName("salles");
           jg.writeStartArray();
           Iterator<Piece> itPieces = pieces.iterator();
           while ( itPieces.hasNext()){
               jg.writeString(itPieces.next().getNom());
           }
           jg.writeEndArray();
           //ecrire les conditions
           jg.writeFieldName("conditions");
           jg.writeStartArray();
           Iterator<RegleCondition> itConditions = conditions.iterator();
           RegleCondition rc = itConditions.next();
           if ( rc instanceof ConditionPresence){
               jg.writeStartObject();
               jg.writeStringField("type","presence");
               ConditionPresence cp = (ConditionPresence) rc;
               long begin = cp.getBeginDetect().getTime().getTime();
               long end = cp.getEndDetect().getTime().getTime();
               jg.writeNumberField("dateDebut",begin);
               jg.writeNumberField("dateFin", end);
               jg.writeEndObject();
           }else if ( rc instanceof ConditionTemperature ){
               jg.writeStartObject();
               jg.writeStringField("type", "temperature");
               ConditionTemperature ct = (ConditionTemperature) rc;
               jg.writeNumberField("tempMin", ct.getTempMin());
               jg.writeNumberField("tempMax", ct.getTempMax());
               jg.writeEndObject();
           }else if ( rc instanceof ConditionContacteur ){
               jg.writeStartObject();
               jg.writeStringField("type","contacteur");
               jg.writeEndObject();
           }else if ( rc instanceof ConditionTemps ){
               jg.writeStartObject();
               jg.writeStringField("type","temps");
               jg.writeFieldName("dates");
               ConditionTemps ct = (ConditionTemps) rc;
               EmploiTemps et = ct.getEmploitemps();
               List<PlageHoraire> plages = et.getPlages();
               jg.writeStartArray();
               Iterator<PlageHoraire> itPlages = plages.iterator();
               while ( itPlages.hasNext()){
                   PlageHoraire pH = itPlages.next();
                   jg.writeStartObject();
                   jg.writeNumberField("dateDebut",pH.getDebut().getTime().getTime());
                   jg.writeNumberField("datefin",pH.getFin().getTime().getTime());
                   jg.writeEndObject();
               }
               jg.writeEndArray();
               
               
               jg.writeEndObject();
           }else if ( rc instanceof ConditionBouton){
               jg.writeStartObject();
               jg.writeStringField("type","bouton");
               ConditionBouton cb = (ConditionBouton) rc;
               jg.writeStringField("id", cb.getCapteurId());
               jg.writeEndObject();
           }

           jg.writeEndArray();
            
            // ecrire les actions
           jg.writeFieldName("actions");
           jg.writeStartArray();
           List<Action> actions = regle.getActions();
           Iterator<Action> itAction = actions.iterator();
           while ( itAction.hasNext()){
               Action action = itAction.next();
               if ( action instanceof AllumerPrise ){
                   AllumerPrise ap= (AllumerPrise)action;
                   jg.writeStartObject();
                   jg.writeStringField("type","allumerPrise");
                   jg.writeStringField("envoiMail", ap.getIdPrise());
                   jg.writeEndObject();
               }
               else if ( action instanceof EnvoyerMail){
                   EnvoyerMail evm = (EnvoyerMail) action;
                   jg.writeStartObject();
                   jg.writeStringField("type","envoiMail");
                   jg.writeStringField("mail", evm.getAdresse());
                   jg.writeEndObject();
               }
           }
           jg.writeEndArray();
            
            jg.writeEndObject();
        }

    
}
