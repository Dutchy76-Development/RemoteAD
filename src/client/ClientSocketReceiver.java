package client;

import java.io.DataInputStream;

public class ClientSocketReceiver extends Thread {
	
	public String receivedMessage;
	private DataInputStream in;
	
	public ClientSocketReceiver(DataInputStream in) {
		this.in = in;
	}
	
	public void run() {
		System.out.println("Receiver Thread started!");
		try {
			String message;
			while((message = this.in.readUTF()) != null) {
				receivedMessage = this.in.readUTF();
				//TODO: Handling this
				System.out.println(receivedMessage);
			}
		} catch (Exception e) {
			
		}
	}
}
