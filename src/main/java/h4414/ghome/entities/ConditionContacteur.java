/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.entities;

import h4414.ghome.vues.PersistanceUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Mathis
 */
@Entity
@DiscriminatorValue("CONDITION_CONTACTEUR")
public class ConditionContacteur extends RegleCondition implements Serializable {

    public ConditionContacteur(){
        
    }
    public ConditionContacteur ( List<Piece> pieces){
        this.pieces = pieces;
    }
    
    
    /*
     * condition declenchee si l'un des contacteurs des pieces concernées est en position ouverte
     */
    @Override
    public boolean isMet() {
     EntityManager em = PersistanceUtils.getEmf().createEntityManager();
        //recuperer la liste des capteurs de type presence de la piece
        
        
        String whereClause ="";
        Iterator<Piece> itPieces= this.pieces.iterator();
        while( itPieces.hasNext()){
            whereClause += "o.piece.nom = '";
            whereClause += itPieces.next().getNom();
            whereClause += "'";
            if ( itPieces.hasNext()){
                whereClause += " OR ";
            }
        }
        Query getCapteurs = em.createQuery("SELECT o FROM Capteur o WHERE "+whereClause, Capteur.class);
        List<Capteur> capteurs = getCapteurs.getResultList();
        
        
        
        Iterator<Capteur> itTousCapteurs = capteurs.iterator();
        while(itTousCapteurs.hasNext()){
            if (  ! itTousCapteurs.next().getType().equals(Capteur.TypeCapteur.CONTACTEUR)){
                itTousCapteurs.remove();
            }
        }
        if ( capteurs.isEmpty()){
            return false;
        }
        
        // recuperer les ids des capteurs concernes et generer la clause where
        whereClause = "";
        //Iterator<Capteur> it = capteurs.iterator();
        
        /*
         * mettre une condition sur la date dans la requete pour ne pas prendre toutes les presences en cours ?
         * ( faire correspondre cette condition a l'intervalle de verification des regles ???)
         */
        List<Historique> relatedHistoriques = new ArrayList();
        Iterator<Capteur> itCapteurs = capteurs.iterator();
        while ( itCapteurs.hasNext()){
            Query q = em.createQuery("SELECT x FROM HISTORIQUE x WHERE x.idCapteur = '"+itCapteurs.next().getIdCapteur()+"' order by x.debutPresence desc", Historique.class);
            Object res = q.getSingleResult();
            if ( res instanceof Historique ){
                relatedHistoriques.add((Historique)res);
            }
        }
        if ( relatedHistoriques.isEmpty()){
            return false;
        }
        Iterator<Historique> itHistoriques = relatedHistoriques.iterator();
        while (itHistoriques.hasNext()){
            // condition a modifier eventuellement ?
            if ( itHistoriques.next().getDonnee()==0){
                return true;
            }
        }
        
    
    return false;
    }
    
    
    
    
}
