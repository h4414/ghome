/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.camel.processors;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import h4414.ghome.entities.Capteur;
import h4414.ghome.entities.Piece;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spi.Registry;

/**
 *
 * @author Mathis
 */
public class DeletePiece implements Processor{
    
    
    public void process ( Exchange ex ){
        
        Registry reg = ex.getContext().getRegistry();
        String entityName = ex.getProperty("entityName",String.class);
        Object emf = reg.lookupByName("entityManagerFactory");
        if ( emf != null ){
            if ( emf instanceof EntityManagerFactory ){
                EntityManagerFactory emFactory = (EntityManagerFactory )( emf);
                EntityManager em = emFactory.createEntityManager();
                
                JsonFactory jf = new JsonFactory();
                try {
                    JsonParser parser = jf.createParser(ex.getIn().getBody(InputStream.class));
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode rootNode = mapper.readValue(parser, JsonNode.class);
                    if( ! rootNode.path("nom").isMissingNode()){
                        JsonNode idNode = rootNode.path("nom");
                        String nomPiece = idNode.asText();
                        
                        Query q= em.createQuery("SELECT o FROM Piece WHERE o.nom = '"+nomPiece+"'");
                        Object p = q.getSingleResult();
                        if ( p instanceof Piece ){
                            Piece piece = (Piece) p;
                            List<Capteur> capteurs = piece.getCapteurs();
                            Iterator<Capteur> it = capteurs.iterator();
                            while ( it.hasNext()){
                                Capteur c = it.next();
                                Query deleteCapteur = em.createQuery("DELETE o FROM Capteur WHERE o.idCapteur = '"+c.getIdCapteur()+"'");
                                deleteCapteur.executeUpdate();
                                Logger.getLogger(DeletePiece.class.getName()).log(Level.INFO, "Capteur supprime");
                            }
                        }
                        Query deletePiece = em.createQuery("DELETE o FROM Piece WHERE o.nom = '"+nomPiece+"'");
                        deletePiece.executeUpdate();
                        Logger.getLogger(DeletePiece.class.getName()).log(Level.INFO, "Piece supprimee");
                    }
                } catch (IOException ex1) {
                    Logger.getLogger(DeletePiece.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
}
