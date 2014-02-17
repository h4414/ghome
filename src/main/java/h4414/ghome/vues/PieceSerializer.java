/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.vues;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import h4414.ghome.entities.Capteur;
import h4414.ghome.entities.Piece;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mathis
 */
public class PieceSerializer extends StdSerializer<Piece>{

    
    public PieceSerializer( ){
        super(Piece.class);
    }
    
    @Override
    public void serialize(Piece t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        jg.writeStartObject();
        jg.writeStringField("nom",t.getNom());
        List<Capteur> listeCapteurs = t.getCapteurs();
        if ( listeCapteurs != null && listeCapteurs.size() != 0 ){
            jg.writeFieldName("capteurs");
            jg.writeStartArray();
            Iterator<Capteur> it = listeCapteurs.iterator();
            while ( it.hasNext()){
                Capteur c = it.next();
                jg.writeStartObject();
                jg.writeStringField("idCapteur",c.getIdCapteur());
                jg.writeStringField("type",c.getType().toString());
                jg.writeStringField("NomCapteur", c.getNomCapteur());
                jg.writeEndObject();
            }
            jg.writeEndArray();
        }
        jg.writeEndObject();
    }
    
    
   
    
    
    
}
