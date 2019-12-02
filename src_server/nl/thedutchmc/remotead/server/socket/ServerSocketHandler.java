/*
Copyright (C) Tobias de Bruijn

Under domain thedutchmc.nl

You are not allowed to redistribute this code as your own!
*/
package nl.thedutchmc.remotead.server.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.thedutchmc.remotead.server.clientHandler.Clients;

public class ServerSocketHandler {
	
	//List<Clients> clients = new ArrayList<Clients>();
	List<Socket> clientsSocket = new ArrayList<Socket>();
	public HashMap<Integer,Socket> clientIds = new HashMap<Integer, Socket>();
	Socket newClientSocket;
	ServerSocket serverSocket = null;

		
	public void startServer() {
		//Open socket
		try {
			serverSocket = new ServerSocket(8028);
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

					ClientConnector connector = new ClientConnector(this, newClientSocket);
					connector.start();
					this.run();					
				}
			}
			
		} catch(Exception e) {
			System.err.println("[ServerSocketHandler] Couldn't accept client");
			e.printStackTrace(System.err);
		}
	}
	

	public void removeClient(Socket socket) {
		synchronized(this) {
			//clients.remove(socket);
		}
	}
	

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
				in = new DataInputStream(this.socket.getInputStream());
				out = new DataOutputStream(this.socket.getOutputStream());
			} catch (IOException e) {

				System.err.println("[ServerSocketHandler] Failed to open input stream");

				System.err.println("Failed to open input or output stream");

				e.printStackTrace(System.err);
				return;
			}
			
			//Read client's message
			while(true) {
				String msg_rec = null;
				try {
					msg_rec = in.readUTF();
				} catch (Exception e) {
					if(e instanceof EOFException) {
						continue;
					} else {
						System.err.println("[ServerSocketHandler] Issue with reading Client message!");
						e.printStackTrace(System.err);
					}
				}
				
				//Process Message
				if(msg_rec != null) {    
					String[] msgReceivedArray = msg_rec.split(",");

					int id = 0;
					
					try {
						id = Integer.valueOf(msgReceivedArray[0]);						
						
					} catch (Exception e) {
						System.err.println("[ServerSocketHandler] Unable to grab UUID, Does the UUID consist of numbers, and only numbers?");
					}
					new Clients(id, newClientSocket, true);
					
					for(Map.Entry<Integer, Socket> entry : clientIds.entrySet()) {
						int key = entry.getKey();
						if(id == key) {
							System.err.println("[ServerSocketHandler] Client ID already exists, closing socket!");
							try {
								socket.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					System.out.println("[ServerSocketHandler] Client " + socket.getInetAddress().getHostName() + " Has ID: " + id);
					
					System.out.println("[ServerSocketHandler] Starting new listener...");

					String message = msgReceivedArray[1];
					
					System.out.println(message);
										
					if(message.equalsIgnoreCase("reqKey")) {
						//TODO: KeyFinder
						
						System.out.println("[ALPHA] Request Video from Database");
						final ServerSocketSender sss = new ServerSocketSender();
						sss.sendMessage("ABgDxowve3Q", socket, out);
						//END OF TEST CODE
						
					}
				}
			}
 		} 
	}
}
