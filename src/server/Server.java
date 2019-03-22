package server;

public class Server {

	public static void main(String[] args) {
		ServerGui gui = new ServerGui();
		gui.init();
		
		final ServerSocketHandler handler = new ServerSocketHandler();
		handler.openSocket();
	}

}
