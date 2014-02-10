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
public class TestTemperature {
    
    public TestTemperature() {
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
    public void testTraitement(){
        Trame trame = new Trame("A55A0B0708282C800089337F0029");
        double temperature = Temperature.getTemperature(trame);
        System.out.println(temperature);
        assertTrue(temperature > 7 && temperature < 8);
    }
}
