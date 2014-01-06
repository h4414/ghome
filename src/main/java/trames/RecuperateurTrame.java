/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trames;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;

/**
 *
 * @author Maud et Thomas
 */
public class RecuperateurTrame {

 //TODO: a mettre dans fichier config
  final static int port = 5000;
  final static String IP = "134.214.105.28";
  
  public static void main(String[] args) {

    Socket socket;
    BufferedReader trame;

    try {
    
      socket = new Socket(IP, port);
      trame = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String trameRecue = trame.readLine();
      System.out.println(trameRecue);
      socket.close();
    } catch (IOException e) {
        System.out.println("Serveur offline");
    }

  }
}  

