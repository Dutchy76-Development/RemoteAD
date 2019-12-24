package nl.thedutchmc.remotead.client;

import nl.thedutchmc.remotead.client.pi4j.GpioSetup;
import nl.thedutchmc.remotead.client.pi4j.SensorTriggered;
import nl.thedutchmc.remotead.client.socket.ClientSocketHandler;
import nl.thedutchmc.remotead.client.ui.Ui;

public class Client {
	public static void main(String[] args) throws Exception {
	

		
		//Start the base JavaFX app
		log("Started the UI");
		
		new Thread() {
			@Override
			public void run() {
				javafx.application.Application.launch(Ui.class);
			}
		}.start();
		log("Done.");

		
		//COMMENT GPIO SECTION IF NOT ON PI!
		
		
		//GPIO
		/*
		log("Starting GPIO...");
		final GpioSetup gpioSetup = new GpioSetup();
		gpioSetup.initGpio();
		log("Done.");
		
		*/
		//Start the SocketHandler and Connect to the server
		log("Starting ClientSocketHandler...");
		try {
			final ClientSocketHandler handler = new ClientSocketHandler();
			handler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//UNCOMMENT IF NOI ON PI
		
		
		log("Sleeping for 1000 ms");
		
		try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
			e.printStackTrace(System.err);
		}
		
		log("Starting SensorTriggered");
		
		final SensorTriggered trigger = new SensorTriggered();
		try {
			trigger.onTrigger();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//UNCOMMENT TO HERE.
	}

	public static void log(String log) {
		System.out.println("[RemoteAD Client] " + log);
	}
}
