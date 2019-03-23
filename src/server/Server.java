package server;

public class Server {

	public static void main(String[] args) {
		
		//Start the GUI
		ServerGui gui = new ServerGui();
		gui.init();
		
		
		//Start the SocketHandler and get server online
		final ServerSocketHandler handler = new ServerSocketHandler();
		handler.openSocket();
	}

}
