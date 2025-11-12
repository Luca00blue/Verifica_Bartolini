package com.example; 
import java.util.concurrent.ThreadLocalRandom;
import java.io.BufferedReader; 
import java.io.IOException;    
import java.io.InputStreamReader; 
import java.io.PrintWriter;    
import java.net.Socket;       
import java.util.ArrayList;   


public class MioThread extends Thread {

    Socket socket;       
    BufferedReader in;  
    PrintWriter out;    
    
    public MioThread(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }
    int a = 0;
    int b = 101;
    int tries = 0;
    int segreto = ThreadLocalRandom.current().nextInt(a, b);
    
    @Override
    public void run() {         
        out.println("WELCOME INDOVINA v1 RANGE 1 100"); 

        String[] comando = {"", ""};
        String[] range = {"","",""};
        String[] stat = {""};
        String rang = ""; 

        while (true) {
            String comn = "";
            try {
                comn = in.readLine(); 
            } catch (IOException e) {
                e.printStackTrace();
            }        
            comando = comn.split(" " , 2);
            range = comn.split(" ");
            stat = comn.split(" " , 3);
            if (comando[0].equals("QUIT")){
                out.println("BYE"); 
                try {
                    socket.close(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            switch (comando[0]) {
              
                case "GUESS":

                    int num = Integer.parseInt(comando[1]);

                    if ( num == segreto ) {
                        out.println("OK COORECT in T=" + tries);
                       
                    }else if (num < segreto) {
                        out.println("HINT HIGHER");

                    }else
                    {
                        out.println("HINT LOWER");
                       
                    }
                    tries++;

                    break;


                default:
                    break;
            }
            
            switch (range[0]) {
                case "RANGE":
                String a = "" ;
                String b = "";
                try {
                    a = in.readLine(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }  
                try {
                    b = in.readLine(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }  
                int an = Integer.parseInt(a);
                int bn = Integer.parseInt(b);

                segreto = ThreadLocalRandom.current().nextInt(an, bn);

                break;

                default:
                    break;
            }

            switch (stat[0]) {
                case "STATS":
                out.println("INFO RANGE "+ a + " " + b + " TRIES " + tries);
                break;

                case "QUIT":
                out.println("BYE"); 
                try {
                    socket.close(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
                case "NEW":

                
                break;

                default:
                    break;
            }
        }
      
        
}
}