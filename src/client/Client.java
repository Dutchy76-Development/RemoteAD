package client;

public class Client {
	public static void main(String[] args) throws Exception {
	
		final SensorTrigger trigger = new SensorTrigger();
		try {
			trigger.onTrigger();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Start the SocketHandler and Connect to the server
		try {
			final ClientSocketHandler handler = new ClientSocketHandler();
			handler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
	}
}
