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
			serverSocket = new ServerSocket(8008);
			System.out.println("Server Started");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Couldn't create socket on port 8008");
			System.exit(1);
		}
		
		//Listen for clients
		try {
			while(true) {
				Socket newClientSocket = serverSocket.accept();
				synchronized(this) {
				clients.add(newClientSocket);
				}
				ClientConnector connector = new ClientConnector(this, newClientSocket);
				connector.start();
			}
			
		} catch(Exception e) {
			System.err.println("Couldn't accept client");
			e.printStackTrace(System.err);
		}
	}
	
	public void removeClient(Socket socket) {
		synchronized(this) {
			clients.remove(socket);
		}
	}
	class ClientConnector extends Thread {
		private Socket socket;
		private ServerSocketHandler server;

		public ClientConnector(ServerSocketHandler server, Socket socket) {
			this.socket = socket;
			this.server = server;
		}
		 
		public void run() {
			System.out.println("Client Connected with name: " + this.socket.getInetAddress().getHostName());
			DataInputStream in;
			try {
				in = new DataInputStream(this.socket.getInputStream()); // create input stream for listening for incoming messages
			} catch (IOException e) {
				System.err.println("Failed to open input stream");
				e.printStackTrace(System.err);
				return;
			}
			
			while(true) {
				String msg_rec;
				try {
					msg_rec = in.readUTF();
				} catch (Exception e) {
					System.err.println("Issue with reading Client message!");
					e.printStackTrace(System.err);
					this.server.removeClient(this.socket);
					return;
				}
				
				if(msg_rec.length() == 0) {
					continue;
				}
				
				System.out.println(this.socket.getPort() + ": " + msg_rec);
			}
 		} 
	}
}
