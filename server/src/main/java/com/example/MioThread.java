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
    
    int tries = 0;
    int segreto = ThreadLocalRandom.current().nextInt(1, 101);
    
    @Override
    public void run() {         
        out.println("WELCOME INDOVINA v1 RANGE 1 100"); 

        String[] comando = {"", ""};
        String[] range = {"","",""};

        while (true) {
            String rang = ""; 
            String comn = "";

            
            comando = comn.split("" , 2);

            switch (comando[0]) {
                case "GUESS":
                    String inp = "" ;
                    
                    String[] guess =inp.split("",2);

                    if (guess[1].equals(segreto)) {
                        out.println("corretto");
                    }else
                    {
                        out.println("non corretto");
                    }
                    break;
            
                default:
                    break;
            }

        }
      
        
}
}