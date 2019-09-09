package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerSocketSender extends Thread {

	DataOutputStream out;
	
	public void sendMessage(String message, Socket socket) {
		
		//Open an output stream
		try {
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.err.println("Failed to open output stream!");
			e.printStackTrace();
		}
		
		//Send message
		try {			
			out.writeUTF(message);
			out.flush();
		} catch (IOException e) {
			System.err.println("Failed to send message!");
			e.printStackTrace();
		}
	}
	
}
