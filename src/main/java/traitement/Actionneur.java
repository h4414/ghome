/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitement;

import trames.Trame;

/**
 *
 * @author qdunoyer
 */
public class Actionneur {
    private static final String sync = "A55A";
    private static final String hseqLength = "6B";
    private static final String org = "06";
    private static final String payload3 = "09";
    private static final String payload2 = "00";
    private static final String payload1 = "00";
    private static final String payload0 = "00";
    public Trame sendTrame(String id){
        Integer checksum = Integer.parseInt(hseqLength,16) + Integer.parseInt(org,16) + Integer.parseInt(payload3,16)
                + Integer.parseInt(payload2,16) + Integer.parseInt(payload1,16) + Integer.parseInt(payload0,16)
                + Integer.parseInt(id.substring(0,2)) + Integer.parseInt(id.substring(2,4)) + Integer.parseInt(id.substring(4,6))
                + Integer.parseInt(id.substring(6,8));
        String chkSum = String.format("%02X",checksum);
        Trame trame = new Trame(sync+hseqLength+org+payload3+payload2+payload1+payload0+id+chkSum);
        return trame;
    }
}
