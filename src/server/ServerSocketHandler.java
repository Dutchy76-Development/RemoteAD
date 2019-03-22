package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketHandler {

	protected List<Socket> clients = new ArrayList<Socket>();
	
	public void openSocket() {
		new ServerSocketHandler();
	}
	
	public ServerSocketHandler() {
		ServerSocket serverSocket = null;
		
		//Start socket
		try {
			serverSocket = new ServerSocket(888);
			System.out.println("Server Started");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Couldn't create socket on port 888");
			System.exit(1);
		}
		
		//Listen for clients
		try {
			Socket newClientSocket = serverSocket.accept();
			synchronized(this) {
				clients.add(newClientSocket);
			}
			
		} catch(Exception e) {
			System.err.println("Couldn't accept client");
			e.printStackTrace(System.err);
		}
	}
	class ClientConnector extends Thread {
		private Socket socket;

		public ClientConnector(Socket socket) {
			this.socket = socket;
		}
		 
		public void run() {
			System.out.println("Client Connected with name: " + this.socket.getInetAddress().getHostName());
			DataInputStream in;
			try {
				in = new DataInputStream(this.socket.getInputStream()); // create input stream for listening for incoming messages
			} catch (IOException e) {
				System.err.println("[system] could not open input stream!");
				e.printStackTrace(System.err);
				return;
			}
		} 

	}
}
