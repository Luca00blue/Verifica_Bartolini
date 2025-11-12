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
        String rang = ""; 

        while (true) {
            String comn = "";
            try {
                comn = in.readLine(); 
            } catch (IOException e) {
                e.printStackTrace();
            }        
            comando = comn.split(" " , 2);

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
                    String inp = " " ;
                    
                    String[] guess = inp.split(" ",2);
                    int num = Integer.parseInt(guess[1]);
                    if ( num == segreto ) {
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