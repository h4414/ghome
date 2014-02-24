/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package traitement;

import trames.Trame;

/**'A55A 6B05 5000 0000 FF9F 1E0'
 *
 * @author qdunoyer
 */
public class Actionneur {
    private static final String sync = "A55A";
    private static final String hseqLength = "6B";
    private static final String org = "05";
    private static final String payload3on = "50";
    private static final String payload3off = "70";
    private static final String payload2 = "00";
    private static final String payload1 = "00";
    private static final String payload0 = "00";
    private static final String id1 = "FF";
    private static final String id0 = "9F";
    private static final String last1 = "1E";
    private static final String last0 = "0";
    
    
    public static String allumerPrise(String numPrise){
        Integer checksum = Integer.parseInt(hseqLength,16) + Integer.parseInt(org,16) + Integer.parseInt(payload3on,16)
                + Integer.parseInt(payload2,16) + Integer.parseInt(payload1,16) + Integer.parseInt(payload0,16)
                + Integer.parseInt(id1,16) + Integer.parseInt(id0,16) + Integer.parseInt(last1,16)
                + Integer.parseInt(last0,16) + Integer.parseInt(numPrise,16)+ Integer.parseInt("30",16);
        System.out.println(checksum);
        String chkSum = String.format("%02X",checksum);
        String trame =sync+hseqLength+org+payload3on+payload2+payload1+payload0+id1+id0+last1+last0+numPrise+"30"+chkSum.substring(1);
        return trame;
    }
    
    public static String eteindrePrise(String numPrise){
        Integer checksum = Integer.parseInt(hseqLength,16) + Integer.parseInt(org,16) + Integer.parseInt(payload3off,16)
                + Integer.parseInt(payload2,16) + Integer.parseInt(payload1,16) + Integer.parseInt(payload0,16)
                + Integer.parseInt(id1,16) + Integer.parseInt(id0,16) + Integer.parseInt(last1,16)
                + Integer.parseInt(last0,16) + Integer.parseInt(numPrise,16)+ Integer.parseInt("30",16);
        System.out.println(checksum);
        String chkSum = String.format("%02X",checksum);
        String trame =sync+hseqLength+org+payload3off+payload2+payload1+payload0+id1+id0+last1+last0+numPrise+"30"+chkSum.substring(1);
        return trame;
    }
}
