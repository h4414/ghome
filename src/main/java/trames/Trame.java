/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trames;

import java.math.BigInteger;

/**
 *
 * @author qdunoyer
 */
public class Trame {
    private BigInteger trame;
    private static final BigInteger MASQUE_ID      = new BigInteger("0000000000000000FFFFFFFF0000",16);
    private static final BigInteger MASQUE_DATA    = new BigInteger("00000000FFFFFFFF000000000000",16);
    private static final BigInteger MASQUE_DATA0   = new BigInteger("00000000000000FF000000000000",16);
    private static final BigInteger MASQUE_DATA1   = new BigInteger("000000000000FF00000000000000",16);
    private static final BigInteger MASQUE_DATA2   = new BigInteger("0000000000FF0000000000000000",16);
    private static final BigInteger MASQUE_DATA3   = new BigInteger("00000000FF000000000000000000",16);
    
    public Trame(String trame){
        this.trame = new BigInteger(trame,16);
    }
    
    public int getID(){
        int id = (int)((trame & MASQUE_ID) >>> (4*4));
        return id;
    }
    
    public int getData(){
	int data = (trame & MASQUE_DATA) >>> (4*12);
	return data;
    }

    public int getDATA0(){
            int data0 = (trame & MASQUE_DATA0) >>> (4*12);
            return data0;
    }

    public int getDATA1(){
            int data1 = (trame & MASQUE_DATA1) >>> (4*14);
            return data1;
    }

    public int getDATA2(){
            int data2 = (trame & MASQUE_DATA2) >>> (4*16);
            return data2;
    }

    public int getDATA3(){
            int data3 = (trame & MASQUE_DATA3) >>> (4*18);
            return data3;
    }
    //TODO : Refaire entierement.
    public int getDataX_Y(int x, int y){
            int data0_1 = (trame & MASQUE_DATA0) >>> (4*12);
            return data0_1;
    }
}
