package h4414.ghome.entities;

import h4414.ghome.entities.Capteur.TypeCapteur;
import h4414.ghome.entities.Piece;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-02-24T09:10:16")
@StaticMetamodel(Capteur.class)
public class Capteur_ { 

    public static volatile SingularAttribute<Capteur, Integer> id;
    public static volatile SingularAttribute<Capteur, TypeCapteur> type;
    public static volatile SingularAttribute<Capteur, String> NomCapteur;
    public static volatile SingularAttribute<Capteur, Piece> piece;
    public static volatile SingularAttribute<Capteur, String> idCapteur;

}