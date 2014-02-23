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
@DiscriminatorValue("CONDITION_TEMPERATURE")
public class ConditionTemperature extends RegleCondition implements Serializable {


    
    private double tempMin;
    private double tempMax;
    
    public ConditionTemperature ( ){
        
    }
    
    /*
     * La condition est remplie quand la temperature est comprise entre tempMin et tempMax
     */

    public ConditionTemperature( List<Piece> pieces, double tempMin, double tempMax){
        this.pieces = pieces;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.tempMin) ^ (Double.doubleToLongBits(this.tempMin) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.tempMax) ^ (Double.doubleToLongBits(this.tempMax) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConditionTemperature other = (ConditionTemperature) obj;
        if (Double.doubleToLongBits(this.tempMin) != Double.doubleToLongBits(other.tempMin)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tempMax) != Double.doubleToLongBits(other.tempMax)) {
            return false;
        }
        return true;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
    
    
    
    @Override
    public boolean isMet() {
         EntityManager em = PersistanceUtils.getEmf().createEntityManager();
        //recuperer la liste des capteurs de type presence de la piece (shameless copy paste )
        String whereClause ="";
        Iterator<Piece> itPieces= this.pieces.iterator();
        while( itPieces.hasNext()){
            whereClause += "o.piece.nom = ";
            whereClause += itPieces.next().getNom();
            if ( itPieces.hasNext()){
                whereClause += " OR ";
            }
        }
         
        Query getCapteurs = em.createQuery("SELECT o FROM Capteur o WHERE o.piece.nom = "+whereClause, Capteur.class);

        List<Capteur> capteurs = getCapteurs.getResultList();
        
        
        
        Iterator<Capteur> itTousCapteurs = capteurs.iterator();
        while(itTousCapteurs.hasNext()){
            if (  ! itTousCapteurs.next().getType().equals(Capteur.TypeCapteur.TEMPERATURE)){
                itTousCapteurs.remove();
            }
        }
        if ( capteurs.isEmpty()){
            return false;
        }
        // recuperer les historiques correspondant 
        //( les derniers historiques de temperature des capteurs correspondant a la piece)
       
        List<Historique> relatedHistoriques = new ArrayList<Historique>();
        
        Iterator<Capteur> it = capteurs.iterator();
        while ( it.hasNext()){
            Query q = em.createQuery("SELECT x FROM HISTORIQUE x WHERE x.idCapteur = "+it.next().getIdCapteur()+" order by x.debutPresence desc", Historique.class);
            Object res = q.getSingleResult();
            if ( res instanceof Historique ){
                relatedHistoriques.add((Historique)res);
            }
        }
        
        if ( relatedHistoriques.isEmpty()){
            return false;
        }
        Iterator<Historique> itHistoriques = relatedHistoriques.iterator();
        // l'on verifie calcule la temperature moyenne et l'on verifie si elle rempli la condition
        double tempMoy = 0;
        while ( itHistoriques.hasNext()){
            tempMoy += itHistoriques.next().getDonnee();
        }
        tempMoy /= relatedHistoriques.size();
        if ( tempMoy <= this.tempMax && tempMoy >= this.tempMin ){
            return true;
        }
        else{
        
            return false;
        }
    }
    
}
