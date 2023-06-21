package ex3lab13SERVER;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWordCountApplication {
	/**
	 * Main entry point to the server side application
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// Binding to a port or any other port no you are fancy of
				int portNo = 4228;
				ServerSocket serverSocket = new ServerSocket(portNo); 
				
				// Counter to keep track the number of requested connection
				int totalRequest = 0;
				
				// Launch the server frame
		ServerWordCountFrame serverFrame = new ServerWordCountFrame();
		serverFrame.setVisible(true);
		
		
			WordCountGenerator wordcountGenerator = new WordCountGenerator();
			
		
			
			// Server needs to be alive forever
			while (true) {
				
				// Message to indicate server is alive
				serverFrame.updateServerStatus(false);
				
				
				// Accept client request for connection
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
				
				 // Create stream to read from the network
			    BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			    // Create stream to write data on the network
				DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
				
				 // Read the text from the client
			    String text = inputStream.readLine();
			    
			 // Count the number of words in the text
			    int wordCount = wordcountGenerator.wordsCount(text);
				
				
				
				// Send current date back to the client
				outputStream.writeInt(wordCount);
				
				 System.out.println("Accepted connection to from the "
							+ "client. Total request = " + ++totalRequest );
				// Close the socket
				clientSocket.close();
			
				// Update the request status
				
				serverFrame.updateRequestStatus(
						"Data sent to the client: " + wordCount);
				serverFrame.updateRequestStatus("Accepted connection to from the "
						+ "client. Total request = " + ++totalRequest );
			
			}
		
		
		

	}

}
