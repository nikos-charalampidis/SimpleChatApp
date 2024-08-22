import java.net.*;
import java.io.*;

 public class ServerProtocol {

    /**
     * Processes the client's request.
     * 
     * @param theInput The message received from the client.
     * @return The response to be sent back to the client. In this implementation, 
     *         it simply echoes the received message.
     */
    public String processRequest(String theInput) {
        // Print the received message from the client to the server console
        System.out.println("Received message from client: " + theInput);
		 
        // The output is simply the input message (echoed back)
        String theOutput = theInput;
        
        // Print the message that will be sent back to the client to the server console
        System.out.println("Send message to client: " + theOutput);
        
        // Return the echoed message to the client
        return theOutput;
    }
}
