/*
Copyright (C) Tobias de Bruijn

Under domain thedutchmc.nl

You are not allowed to redistribute this code as your own!
*/

package server;

public class Server {

	public static void main(String[] args) {
		
		//Start the GUI
	//	ServerGui gui = new ServerGui();
	//	gui.init();
		
		
		//Start the SocketHandler and get server online 
		final ServerSocketHandler handler = new ServerSocketHandler();
		handler.startServer();
	}
}
