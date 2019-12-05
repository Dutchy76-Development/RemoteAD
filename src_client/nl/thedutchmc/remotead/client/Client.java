package nl.thedutchmc.remotead.client;

import nl.thedutchmc.remotead.client.pi4j.GpioSetup;
import nl.thedutchmc.remotead.client.socket.ClientSocketHandler;

public class Client {
	public static void main(String[] args) throws Exception {
	
		/*final SensorTrigger trigger = new SensorTrigger();
		try {
			trigger.onTrigger();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//GPIO
		final GpioSetup gpioSetup = new GpioSetup();
		gpioSetup.initGpio();
		
		
		//Start the SocketHandler and Connect to the server
		try {
			final ClientSocketHandler handler = new ClientSocketHandler();
			handler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
	}
}
