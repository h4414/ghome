/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import h4414.ghome.entities.Capteur.TypeCapteur;
import h4414.ghome.vues.PersistanceUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Temporal;

/**
 *
 * @author Mathis
 */
@Entity
@DiscriminatorValue("CONDITION_PRESENCE")
public class ConditionPresence extends Condition implements Serializable{

    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar beginDetect;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar endDetect;
    
    public ConditionPresence() {
    }
    
    
    public ConditionPresence( Piece piece, Calendar begin, Calendar end ){
        this.piece = piece;
        this.beginDetect = begin;
        this.endDetect = end;
    }

  
    /*
     * implementation fausse : l'info sur la presence n'est pas stockée dans les calendriers de l'objet historique ...
     */
    @Override
    public boolean isMet() {
        EntityManager em = PersistanceUtils.getEmf().createEntityManager();
        //recuperer la liste des capteurs de type presence de la piece
        
        Query getCapteurs = em.createQuery("SELECT o FROM Capteur o WHERE o.piece.nom = "+this.piece.getNom(), Capteur.class);
        List<Capteur> capteurs = getCapteurs.getResultList();
        
        
        
        Iterator<Capteur> itTousCapteurs = capteurs.iterator();
        while(itTousCapteurs.hasNext()){
            if (  ! itTousCapteurs.next().getType().equals(TypeCapteur.PRESENCE)){
                itTousCapteurs.remove();
            }
        }
        if ( capteurs.isEmpty()){
            return false;
        }
        
        // recuperer les ids des capteurs concernes et generer la clause where
        String whereClause = "";
        Iterator<Capteur> it = capteurs.iterator();
        while ( it.hasNext()){
            whereClause += "x.idCapteur = ";
            whereClause += it.next().getIdCapteur();
            if ( it.hasNext()){
                whereClause += " OR ";
            }
        }
        if ( whereClause.equals("")){
            return false;
        }
        
        Query getAllrelatedHistoriques = em.createQuery("SELECT x FROM Historique x WHERE " + whereClause, Historique.class);
        List<Historique> relatedHistoriques =  getAllrelatedHistoriques.getResultList();
        if ( relatedHistoriques.isEmpty()){
            return false;
        }
        Iterator<Historique> itHistoriques = relatedHistoriques.iterator();
        while ( itHistoriques.hasNext()){
            Historique historique = itHistoriques.next();
            if ( historique.getDebutPresence().getTime().after( this.beginDetect.getTime()) 
                    && historique.getDebutPresence().getTime().before( this.endDetect.getTime()) && 
                    historique.getDonnee() == 1){
                return true;
            }
        }
        return false;
        
        
        
    }
    
    
}
