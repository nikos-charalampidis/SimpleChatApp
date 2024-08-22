import java.net.*;
 import java.io.*;

 public class ChatClientProtocol {

 	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

 	/**
	 * Prompts the user to input a message and returns the input.
	 * 
	 * @return The message input by the user.
	 * @throws IOException If an input or output exception occurs while reading the user's input.
	 */
	public String sendMessage() throws IOException {

		// Prompt the user to enter a message or type "CLOSE" to exit
		System.out.print("Send message, CLOSE for exit:");

		// Read the user's input from the console
		String theOutput = user.readLine();

		// Return the user's input
		return theOutput;
	}


 	public String receiveMessage(String theInput) throws IOException {

 		System.out.println("Received message: " + theInput);
        System.out.print("Send a reply, CLOSE for exit:");
		
 			return theInput;
 	}
 }