package client;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    
    private Socket client;
    private InputStreamReader inputReader;
    private PrintWriter writer;
    private Scanner userInput;
    
    // Client constructor
    public Client(String adress, int port){
        try {
            client = new Socket(adress, port);
            System.out.println("Connected to Server");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void go(){
        try {
            
            writer = new PrintWriter(client.getOutputStream());
            inputReader = new InputStreamReader(client.getInputStream());
            userInput = new Scanner(System.in);
            
            // User input
            System.out.println("Enter first number: ");
            int firstNum = userInput.nextInt();
            System.out.println("Enter second number: ");
            int secondNum = userInput.nextInt();
            
            //Send numbers to Server
            writer.write(firstNum);
            writer.write(secondNum);
            writer.flush();
            
            // Incoming messege from Server
            int sum = inputReader.read();
            System.out.println("Sum from Server>>> " +sum);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            try{
                //Closing streams
                inputReader.close();
                client.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
       
       Client client = new Client("localhost", 7575);
       client.go();
    }
}
