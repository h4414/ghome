/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.camel.processors;

import java.security.*;
import javax.crypto.*;
import org.apache.camel.Message;

/**
 *
 * @author Jérémy
 */
public class Security {
    
    private static String URL = "http://localhost:8084/ghome/html/";
            
    /**
     * Methode qui vérifie le referrer de la requête HTTP (=la page depuis laquelle
     * la requête a été effectuée) et vérifie que c'est bien notre site. Attention,
     * ca peut être falsifié donc c'est pas super probant.
     * @param in
     * @return vrai si ca vient bien de notre site
     */
    public static boolean CheckReferrer(Message in){
        String referrer = in.getHeader("referer", String.class);
        return referrer.startsWith(URL);
        
        
    }
    
    public static SecretKey GenererCle(){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(56);
            SecretKey key = keyGen.generateKey(); 
            return key;
        } catch (NoSuchAlgorithmException e){
            return null;
        }     
    }
    
    public static byte[] Crypter(String text, Key key){
        try{
            //System.out.println("Debut cryptage");
            //Generer un objet Cipher
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            //Encrypter en utilisant la clé
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = text.getBytes();
            byte[] cipherText = cipher.doFinal(plainText);
            /*String result = new String(cipherText, "ISO-8859-1");
            System.out.println("Message crypté : ");
            System.out.println(result);
            System.out.println("Fin cryptage");*/
            return cipherText;            
        }catch(Exception e){
            System.out.println("Erreur cryptage");
            return null;
        }
    }
    
    public static String Decrypter(byte[] crypText, Key key){
        try{
            //System.out.println("Debut décryptage");
            //Generer un objet Cipher
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            //Encrypter en utilisant la clé
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptText = cipher.doFinal(crypText);
            String result = new String(decryptText, "ISO-8859-1");
            /*System.out.println("Message décrypté : ");
            System.out.println(result);
            System.out.println("Fin décryptage");*/
            return result; 
        } catch (Exception e){
            System.out.println("Erreur décryptage");
            return null;
        }
    }
}
