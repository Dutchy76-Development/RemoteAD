package client;

import client.gui.ClientGui;

public class Client {

	public static void main(String[] args) {
		
		//Start the GUI
		final ClientGui gui = new ClientGui();
		gui.init();
		
		//TEMP
		//To be replaced with a SensorListener
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
