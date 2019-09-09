package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerSocketHandler {
	
	//List<Clients> clients = new ArrayList<Clients>();
	List<Socket> clientsSocket = new ArrayList<Socket>();
	HashMap<Socket, Integer> clientsHash = new HashMap<Socket, Integer>();
	HashMap<Integer, Socket> finalClientRegister = new HashMap<Integer, Socket>();
	Socket newClientSocket;
	ServerSocket serverSocket = null;

		
	public void startServer() {
		//Open socket
		try {
			serverSocket = new ServerSocket(8008);
			System.out.println("[ServerSocketHandler] Server Started");
			run();
		} catch (Exception e) {
			System.err.println("[ServerSocketHandler] Couldn't create socket on port 8008");
			System.exit(1);
		}
	}
	public void run() {
		System.out.println("[ServerSocketHandler] New listener started!");
		//Listen for clients & redirect them to ClientConnector
		try {
			while(true) {
				synchronized(serverSocket) {
					newClientSocket = serverSocket.accept();
					clientsSocket.add(newClientSocket);
<<<<<<< HEAD
<<<<<<< HEAD
					ClientConnector connector = new ClientConnector(this, newClientSocket);
					connector.start();
					this.run();
=======
=======
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
					
					//Put the client in Hashmap
					clientsHash.put(newClientSocket, 0);
					System.err.println(clientsHash.get(newClientSocket));
					
<<<<<<< HEAD
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
=======
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
				}
			}
			
		} catch(Exception e) {
			System.err.println("[ServerSocketHandler] Couldn't accept client");
			e.printStackTrace(System.err);
		}
	}
	
<<<<<<< HEAD
=======
	public void removeClient(Socket socket) {
		synchronized(this) {
			//clients.remove(socket);
		}
	}
	
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
	//Connect Client & handle messages
	class ClientConnector extends Thread {
		private Socket socket;
		@SuppressWarnings("unused")
		private ServerSocketHandler server;

		public ClientConnector(ServerSocketHandler server, Socket socket) {
			this.socket = socket;
			this.server = server;
		}
		
		public void run() {
			System.out.println("[ServerSocketHandler] Client Connected with name: " + this.socket.getInetAddress().getHostName());
			DataInputStream in;
			DataOutputStream out;
			try {
				in = new DataInputStream(this.socket.getInputStream()); // create input stream for listening for incoming messages
				out = new DataOutputStream(this.socket.getOutputStream());
			} catch (IOException e) {
<<<<<<< HEAD
<<<<<<< HEAD
				System.err.println("[ServerSocketHandler] Failed to open input stream");
=======
				System.err.println("Failed to open input or output stream");
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
=======
				System.err.println("Failed to open input or output stream");
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
				e.printStackTrace(System.err);
				return;
			}
			
			//Read client's message
			
			while(true) {
				String msg_rec;
				try {
					msg_rec = in.readUTF();
				} catch (Exception e) {
					System.err.println("[ServerSocketHandler] Issue with reading Client message!");
					e.printStackTrace(System.err);
					return;
				}
				
				//Process Message
				if(msg_rec != null) {    
					String[] msgReceivedArray = msg_rec.split(",");

					int uuid = 0;
					
					try {
<<<<<<< HEAD
						uuid = Integer.valueOf(msgReceivedArray[0]);
=======
						id = Integer.valueOf(idAsString);
						clientsHash.put(socket, id);
						System.out.println("ClientsHash: " + clientsHash.get(socket));
						
						finalClientRegister.put(id, socket);
						System.out.println("finalClientRegister Entry: " + finalClientRegister.get(id));
<<<<<<< HEAD
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
=======
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
					} catch (Exception e) {
						System.err.println("[ServerSocketHandler] Unable to grab UUID, Does the UUID consist of numbers, and only numbers?");
					}
					
<<<<<<< HEAD
<<<<<<< HEAD
					String message = msgReceivedArray[1];
					
					clients.add(new Clients(uuid, newClientSocket, true));
					System.out.println("[ServerSocketHandler] Client " + socket.getInetAddress().getHostName() + " Has UUID: " + uuid);
					System.out.println("[ALPHA] " + getClientBySocket(socket));
					
					System.out.println("[ServerSocketHandler] Starting new listener...");
=======
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
					
=======
					
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
					String message = msg_rec_array[1];
										
					if(message.equalsIgnoreCase("reqKey")) {
						//TODO: KeyFinder
						
<<<<<<< HEAD
<<<<<<< HEAD
						System.out.println("[ALPHA] Request Key from database");
=======
=======
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
						// A test (TEMP CODE)
						ServerSocketSender SSS = new ServerSocketSender();
						SSS.sendMessage("This will be a key", socket);
						//END OF TEST CODE
						
						System.out.println("Request Key from database");
>>>>>>> 297275866751761321b6922181ba7e98c8829c02
					}
				}
			}
 		} 
	}
	
	//TEMP
	/*public static Clients getClientBySocket(Socket socket) {
		for(Clients client : clients) {
			if(client.getSocket() == socket) {
				return client;
			}
		} return null;
	}*/
}
