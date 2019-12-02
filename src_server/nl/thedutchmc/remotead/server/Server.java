/*
Copyright (C) Tobias de Bruijn

Under domain thedutchmc.nl

You are not allowed to redistribute this code as your own!
*/

package nl.thedutchmc.remotead.server;

import nl.thedutchmc.remotead.server.socket.ServerSocketHandler;

public class Server {

	public static void main(String[] args) {
		
		//Start the SocketHandler and get server online 
		final ServerSocketHandler handler = new ServerSocketHandler();
		handler.startServer();
	}
}
