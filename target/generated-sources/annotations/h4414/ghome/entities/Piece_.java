package h4414.ghome.entities;

import h4414.ghome.entities.Capteur;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-02-24T09:10:16")
@StaticMetamodel(Piece.class)
public class Piece_ { 

    public static volatile SingularAttribute<Piece, Integer> id;
    public static volatile ListAttribute<Piece, Capteur> capteurs;
    public static volatile SingularAttribute<Piece, String> nom;

}