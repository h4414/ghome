/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package h4414.ghome.camel.processors;

import java.security.Key;
import javax.crypto.SecretKey;
import org.apache.camel.Message;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jérémy
 */
public class SecurityTest {
    
    public SecurityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test d'encryption
     */
    @Test
    public void testCryptage(){
        Key key = Security.GenererCle();
        String text = "Ceci est un texte décrypté!";
        byte[] crypt = Security.Crypter(text, key);
        String decrypt = Security.Decrypter(crypt, key);
        Assert.assertEquals(text,decrypt);

    }
    
}
