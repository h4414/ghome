package h4414.ghome.entities;

import h4414.ghome.entities.Capteur;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-02-24T09:10:16")
@StaticMetamodel(Historique.class)
public class Historique_ { 

    public static volatile SingularAttribute<Historique, Integer> id;
    public static volatile SingularAttribute<Historique, Calendar> debutPresence;
    public static volatile SingularAttribute<Historique, Calendar> finPresence;
    public static volatile SingularAttribute<Historique, Double> donnee;
    public static volatile SingularAttribute<Historique, Capteur> capteur;

}