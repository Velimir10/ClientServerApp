package server;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private ServerSocket server = null;
    private Socket client = null;
    private InputStreamReader inputReader = null;
    private PrintWriter writer = null;
    
    

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Listening...");
            client = server.accept();
            System.out.println("Connection estabilished");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void go() {
        try {
            // Stuff for reading Client input and writing to Client
            inputReader = new InputStreamReader(client.getInputStream());
            writer = new PrintWriter(client.getOutputStream());
            
            // Read client input and make sum
            int firstNum = inputReader.read();
            int secondNum = inputReader.read();
            int sum = firstNum + secondNum;
            
            //Sending sum to Client
            writer.write(sum);
            writer.flush();
           
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Closing streams
            try{
                inputReader.close();
                client.close();
                server.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
       

       Server server = new Server(7575);
       server.go();

    }

}
