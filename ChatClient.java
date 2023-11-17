import java.net.*;
import java.io.*;

// Spawn a different thread for receiving messages
// Due to multiple threads used, simultaneous communication is now possible

public class ChatClient {
    private static final int PORT = 1234;
    //private static final InetAddress HOST = InetAddress.getLocalHost();
    private static final String HOST = "localhost";

    // we create and start one thread for the receiving messages and
    // one for the sending messages
	public static void main(String args[]) throws IOException
	{
		Socket dataSocket = new Socket(HOST,PORT);
                System.out.println("Connection to " + HOST + " established");

		SendThread send = new SendThread(dataSocket);
		Thread thread = new Thread(send);
		thread.start();
		ReceiveThread receive = new ReceiveThread(dataSocket);
		Thread thread2 = new Thread(receive);
		thread2.start();
	}
}	

// Send Thread class
class SendThread implements Runnable{

    private Socket dataSocket;
    private OutputStream os;
    private PrintWriter out;
	
	// Test
	//Webhook test
	public SendThread(Socket soc) throws IOException {
		dataSocket = soc;
		os = dataSocket.getOutputStream();
		out = new PrintWriter(os,true);
	}
	// when this thread is running, it will create the message and send it back to the server
	// then the server will send it to the other client
	public void run() {
		try{
            String outmsg;
            ChatClientProtocol app = new ChatClientProtocol();
			outmsg = app.sendMessage();
			while(!outmsg.equals("CLOSE")) {
				out.println(outmsg);
				outmsg = app.sendMessage();
			}	
			out.println(outmsg);
			dataSocket.close();
			
		}catch (IOException e){}
	}
	
}

// when this thread is running,it will be waiting to receive message from the server
class ReceiveThread implements Runnable{

    private Socket dataSocket;
    private InputStream is;
    private BufferedReader in;
	
	public ReceiveThread(Socket soc) throws IOException {
		dataSocket = soc;
        	is = dataSocket.getInputStream();
		in = new BufferedReader(new InputStreamReader(is));
	}
	
	public void run() {
		try{
			String inmsg;
            		ChatClientProtocol app = new ChatClientProtocol();
           		inmsg = app.receiveMessage(in.readLine());
			while(inmsg != null) {
				inmsg = app.receiveMessage(in.readLine());
			}
		}catch (IOException e){}	
	}
	
}

