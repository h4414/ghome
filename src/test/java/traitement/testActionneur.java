/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitement;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import trames.Trame;

/**
 *
 * @author qdunoyer
 */
public class testActionneur {
    
    public testActionneur() {
        
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testGenerationTrameEnvoi(){
        String sync = "A55A";
        String hseqLength = "6B";
        String org = "06";
        String payload3 = "09";
        String payload2 = "00";
        String payload1 = "00";
        String payload0 = "00";
        String id = "06";
        
        String envoi = Actionneur.sendTrame(id);
        System.out.println(envoi);
    }
}
