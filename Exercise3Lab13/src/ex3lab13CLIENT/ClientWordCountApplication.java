package ex3lab13CLIENT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ClientWordCountApplication {
	
	 public static void main(String[] args) {

	        int serverPort = 4228;

	        try {
	            // Connect to the server
	            Socket clientSocket = new Socket(InetAddress.getLocalHost(), serverPort);
	            System.out.println("Connected to server: " + clientSocket.getInetAddress().getHostAddress());

	            // Create streams to read and write to the server
	            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            DataOutputStream outputToServer = new DataOutputStream(clientSocket.getOutputStream());

	            // Read words from the user and send to the server
	            BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));

	            // Prompt the user for a word
	            System.out.print("Enter a word: ");
	            String word = inputFromUser.readLine();
	            // Send the word to the server
	            outputToServer.writeBytes(word + "\n");

	            // Receive the word count from the server
	            int wordCount = new DataInputStream(clientSocket.getInputStream()).readInt();
	            System.out.println("Word count received from server: " + wordCount);

	            // Close the streams and the socket
	            inputFromServer.close();
	            outputToServer.close();
	            inputFromUser.close();
	            clientSocket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	

}
