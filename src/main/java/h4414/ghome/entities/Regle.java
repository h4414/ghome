/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
/**
 *
 * @author Jérémy
 */
@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="ruletype",discriminatorType=DiscriminatorType.STRING)
//@DiscriminatorValue("Regle")
public class Regle implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    
    private String nom;
     
    @ManyToMany
    private List<RegleCondition> conditions;
    @ManyToMany
    private List<Action> actions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RegleCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<RegleCondition> conditions) {
        this.conditions = conditions;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Objects.hashCode(this.conditions);
        hash = 97 * hash + Objects.hashCode(this.actions);
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
        final Regle other = (Regle) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.conditions, other.conditions)) {
            return false;
        }
        if (!Objects.equals(this.actions, other.actions)) {
            return false;
        }
        return true;
    }
    
    public Regle(){
        this.actions = new ArrayList<Action>();
        this.conditions = new ArrayList<RegleCondition>();
    }
    
    public Regle ( List<RegleCondition> conditions, List<Action> actions){
       this.actions = actions;
       this.conditions = conditions;
    }
    
    public String getNom() {
        return nom;
    }     
    
    
    public boolean conditionsRemplies ( ){
        boolean res = true;
        Iterator<RegleCondition> itConditions = this.conditions.iterator();
        while (itConditions.hasNext() ){
             RegleCondition rc = itConditions.next();

            if ( ! (rc instanceof ConditionBouton) ){
                if ( ! rc.isMet()){
                    return false;
                }
            }
            else{
                return false;
            }
            
            
        }
        return res;
    }
    public boolean ConditionsRemplies ( String idBouton ){
        
        boolean res = true;
        Iterator<RegleCondition> itConditions = this.conditions.iterator();
        while (itConditions.hasNext() ){
            RegleCondition rc = itConditions.next();
            if ( ! (rc instanceof ConditionBouton) ){
                if (!rc.isMet()){
                    return false;
                }
            }
            else{
                ConditionBouton rb = (ConditionBouton)rc;
                if( !rb.getCapteurId().equals(idBouton)){
                    return false;
                }
            }
        }
        return res;
        
    }
    
    
}
