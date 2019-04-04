package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import client.Client;

public class ServerSocketHandler {
	
	static List<Clients> clients = new ArrayList<Clients>();
	List<Socket> clientsSocket = new ArrayList<Socket>();
	//HashMap<Integer, Clients> clientsHash = new HashMap<>();
	Socket newClientSocket;
		
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
		
		//Listen for clients & redirect them to ClientConnector
		try {
			while(true) {
				newClientSocket = serverSocket.accept();
				synchronized(this) {
					clientsSocket.add(newClientSocket);
										
					//clientsObj.add(new Clients(newClientSocket, 0, false));
					
					Clients clientList = new Clients(0, newClientSocket, false);
					
					String clientId = getClientBySocket(newClientSocket).getSocket().toString();
					System.err.println(clientId);
					
					System.err.println(clientList.getSocket());
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
	
	//Connect Client & handle messages
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
			
			//Read client's message
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
				
				//Process Message
				if(msg_rec != null) {    
					System.out.println("Splitting msg_rec");
					String[] msg_rec_array;
					msg_rec_array = msg_rec.split(",");
					
					for(int i = 0; i < msg_rec_array.length; i++) {
						System.out.println(msg_rec_array[i]);
					}
				
					int id = 0;
					
					String idAsString = msg_rec_array[0];
					try {
						id = Integer.valueOf(idAsString);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String message = msg_rec_array[1];
					
					clients.add(new Clients(id, newClientSocket, true));
					
					if(message.equalsIgnoreCase("reqKey")) {
						//TODO: KeyFinder
						
						System.out.println("Request Key from database");
					}
				}
				System.out.println(this.socket.getPort() + ": " + msg_rec);
			}
 		} 
	}
	
	
	//TEMP
	public static Clients getClientBySocket(Socket socket) {
		for(Clients client : clients) {
			if(client.getSocket() == socket) {
				return client;
			}
		} return null;
	}
}
