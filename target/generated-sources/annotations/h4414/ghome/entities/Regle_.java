package h4414.ghome.entities;

import h4414.ghome.entities.Action;
import h4414.ghome.entities.RegleCondition;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-02-24T09:10:16")
@StaticMetamodel(Regle.class)
public class Regle_ { 

    public static volatile SingularAttribute<Regle, Integer> id;
    public static volatile ListAttribute<Regle, RegleCondition> conditions;
    public static volatile SingularAttribute<Regle, String> nom;
    public static volatile ListAttribute<Regle, Action> actions;

}