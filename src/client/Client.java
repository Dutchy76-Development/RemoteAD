package client;

public class Client {

	public static void main(String[] args) {
		final ClientGui gui = new ClientGui();
		gui.init();
		
		try {
			final ClientSocketHandler handler = new ClientSocketHandler();
			handler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
