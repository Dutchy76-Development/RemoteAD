package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketHandler {

	protected List<Socket> clients = new ArrayList<Socket>();
	
	public ServerSocketHandler() {
		ServerSocket serverSocket = null;
		
		//Start socket
		try {
			serverSocket = new ServerSocket(888);
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
}
