/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package h4414.ghome.vues;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import h4414.ghome.entities.Piece;

/**
 *
 * @author Mathis
 */
public class JsonUtils {
    
    public static ObjectMapper getObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        
        // ajout du serializer pour pieces : evite d'avoir une recursion infinie ( piece contient des capteurs et capteur contient une piece)
        // ajout d'un serializer pour regle
        SimpleModule pieces = new SimpleModule ( "piecesModule" );
        pieces.addSerializer( new PieceSerializer());
        pieces.addSerializer(new RegleSerializer());
        mapper.registerModule(pieces);
        
        
        return mapper;
        
    }
    
}
