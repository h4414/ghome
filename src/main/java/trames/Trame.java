/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trames;

import java.math.BigInteger;

/** Classe des trames.
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
    
    /**
     * Constructeur de la classe Trame.
     * @param trame : La String décrivant la trame.
     */
    public Trame(String trame){
        this.trame = new BigInteger(trame,16);
    }
    
    /**Permet d'obtenir l'ID de la trame sous forme d'integer.
     * 
     * @return : les 32 bits de l'ID de la trame
     */
    public int getID(){
        int id = (trame.and(MASQUE_ID)).shiftRight(4*4).intValue();
        return id;
    }
    
    /**
     * Permet d'obtenir la charge de données d'un capteur.
     * 
     * @return : les 32 bits de données de la trame.
     */
    public int getData(){
	int data = trame.and(MASQUE_DATA).shiftRight(4*12).intValue();
	return data;
    }

    /**
     * Permet d'obtenir les 2 premiers octets des données de la trame correspondant à DATA0
     * 
     * @return 
     */
    public int getDATA0(){
        int data0 = trame.and(MASQUE_DATA0).shiftRight(4*12).intValue();
        return data0;
    }
    
    /**
     * Permet d'obtenir 2 octets des données de la trame correspondant à DATA1
     * 
     * @return 
     */
    public int getDATA1(){
        int data1 = trame.and(MASQUE_DATA1).shiftRight(4*14).intValue();
        return data1;
    }

    /**
     * Permet d'obtenir 2 octets des données de la trame correspondant à DATA2
     * 
     * @return 
     */
    public int getDATA2(){
        int data2 = trame.and(MASQUE_DATA2).shiftRight(4*16).intValue();
        return data2;
    }

    /**
     * Permet d'obtenir les 2 derniers octets des données de la trame, correspondant a DATA3
     * 
     * @return 
     */
    public int getDATA3(){
        int data3 = trame.and(MASQUE_DATA3).shiftRight(4*18).intValue();
        return data3;
    }
    
   
    /**
     * Permet d'obtenir le bit Y de DATAX.
     * 
     * @param x : La paire d'octet que l'on recherche
     * @param y : le bit que l'on recherche.
     * @return : Le bit demandé ou -1 si cas d'erreur.
     */
    public int getDataX_Y(int x, int y){
        int data;
        if (y>=0 && y<8){
            switch(x){
                case 0:
                    data = (this.getDATA0() & (1 << y))>> y;
                    break;
                case 1:
                    data = (this.getDATA1() & (1 << y))>> y;
                    break;
                case 2:
                    data = (this.getDATA2() & (1 << y))>> y;
                    break;
                case 3:
                    data = (this.getDATA3() & (1 << y))>> y;
                    break;
                default:
                    data = -1;
            }
        }
        else{
            data = -1;
        }
        return data;
    }
}
