/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import h4414.ghome.vues.PersistanceUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 *
 * @author Mathis
 */
public class RuleJsonToJava implements Processor{

    @Override
    public void process(Exchange exchng) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = new JsonFactory();
        JsonParser jparser = factory.createParser(exchng.getIn().getBody ( InputStream.class));
        JsonNode rootNode = mapper.readValue(jparser, JsonNode.class);
        
        // créer un objet regle a partir de ca
        
        JsonNode piecesNode = rootNode.path("salles");
        JsonNode conditionsNodes = rootNode.path("conditions");
        JsonNode actionNodes = rootNode.path("actions");
        
        if ( ! ( piecesNode.isMissingNode() || conditionsNodes.isMissingNode() || actionNodes.isMissingNode())){
            List<Piece> pieces = new ArrayList<Piece>();
            String whereClause = "";
            List <String> pieceNames = new ArrayList<String>();
            Iterator<JsonNode> pNodes = piecesNode.iterator();
            Regle regle = new Regle();
            //recuperer tous les noms des pieces passes depuis le front end
            while ( pNodes.hasNext()){
                //pieceNames.add(pNodes.next().asText());
                whereClause += "o.nom = '";
                whereClause += pNodes.next().asText();
                whereClause += "'";
                if ( pNodes.hasNext()){
                    whereClause += " OR ";
                }
            }
            EntityManager em = PersistanceUtils.getEmf().createEntityManager();
            Query getPieces = em.createQuery("SELECT o FROM Piece o WHERE "+whereClause,Piece.class);
            pieces = getPieces.getResultList();
            
            // recuperer toutes les conditions :o
            Iterator<JsonNode> itConditions = conditionsNodes.iterator();
            List<RegleCondition> conditions = regle.getConditions();
            while ( itConditions.hasNext()){
                JsonNode condNode = itConditions.next();
                String type = condNode.path("type").asText();
                RegleCondition rc ;
                switch ( type ){
                    case "presence" :
                    {
                       long dateBegin = condNode.path("dateDebut").asLong();
                       long dateFin = condNode.path("dateFin").asLong();
                       Date debut = new Date ( dateBegin );
                       Date fin = new Date ( dateFin );
                       GregorianCalendar gcDebut = new GregorianCalendar();
                       gcDebut.setTime(debut);
                       GregorianCalendar gcFin = new GregorianCalendar ( );
                       gcFin.setTime ( fin );
                       rc = new ConditionPresence(pieces, gcDebut, gcFin);
                       conditions.add(rc);
                       break; 
                    }
                    case "contacteur" : {
                        
                        rc = new ConditionContacteur ( pieces, condNode.path("ferme").asBoolean() );
                        conditions.add(rc);
                        break;
                    }
                    case "temps" : {
                        Iterator<JsonNode> itDates = conditionsNodes.path("dates").iterator();
                        EmploiTemps emploiTemps = new EmploiTemps();
                        while ( itDates.hasNext()){
                            GregorianCalendar gcDebut = new GregorianCalendar();
                            GregorianCalendar gcFin = new GregorianCalendar();
                            JsonNode nDate = itDates.next();
                            Date debut = new Date ( nDate.path("dateDebut").asLong());
                            Date fin = new Date ( nDate.path("dateFin").asLong());
                            gcDebut.setTime(debut);
                            gcFin.setTime ( fin );
                            PlageHoraire ph = new PlageHoraire ( gcDebut, gcFin);
                            emploiTemps.ajouterPlage(ph);
                        }
                        rc = new ConditionTemps(emploiTemps);
                        conditions.add(rc);
                        
                        break;
                    }
                    case "temperature" : {
                        double tMin = condNode.path("tempMin").asDouble();
                        double tMax= condNode.path("tempMax").asDouble();
                        rc = new ConditionTemperature ( pieces , tMin, tMax);
                        conditions.add(rc);
                        break;
                    }
                    case "bouton" : {
                        String id = condNode.path("id").asText();
                        int boutonConcerne = condNode.path("bouton").asInt();
                        
                        
                        rc = new ConditionBouton(id,boutonConcerne );
                        conditions.add(rc);
                        break;
                    }
                }
                
                
                
            }
            regle.setConditions(conditions);
            
            // recuperer toutes les actions !
            Iterator<JsonNode> itActions = actionNodes.iterator();
            List<Action> actions = new ArrayList<Action>();
            while ( itActions.hasNext()){
                Action action;
                JsonNode nNode = itActions.next();
                if ( nNode.path("type").asText().equals("envoiMail")){
                    String email = nNode.path("mail").asText();
                    action = new EnvoyerMail(email);
                    actions.add(action);
                }
                else if ( nNode.path("type").asText().equals("allumerPrise")){
                    String idCapteur = nNode.path("id").asText();
                    action = new AllumerPrise( idCapteur );
                    actions.add(action);
                }
                
                
            }
            regle.setActions(actions);
            exchng.getIn().setBody(regle);
            exchng.setProperty("erreur",false);
            
        }
        else{ //erreur :( impossible de créer une regle : setter un header ?
            exchng.setProperty("erreur",true);
        }
        
        
        
    }
    
}
