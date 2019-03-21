package client;

import java.io.DataInputStream;

public class CllientSocketReceiver extends Thread {
	
	public String receivedMessage;
	private DataInputStream in;
	
	public CllientSocketReceiver(DataInputStream in) {
		this.in = in;
	}
	
	public void run() {
		try {
			String message;
			while((message = this.in.readUTF()) != null) {
				receivedMessage = this.in.readUTF();
				//TODO: Handling this
			}
		} catch (Exception e) {
			
		}
	}
}
