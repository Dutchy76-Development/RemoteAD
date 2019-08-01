package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import client.gui.ClientGui;

public class ClientSocketHandler  extends Thread {

	Socket socket = null;
	DataInputStream in = null;
	DataOutputStream out = null;

	public ClientSocketHandler() throws Exception {

		
		ClientGui gui = new ClientGui();
		
		//Attempt server connection
		try {
			socket = new Socket("127.0.0.1", 8008);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			System.out.println("Trying to receive message!");
			final ClientSocketReceiver receiver = new ClientSocketReceiver(in);
			receiver.start();
			
			gui.setConnectedState();
			System.out.println("connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void connect() throws Exception {
		new ClientSocketHandler();
	}
	
	//Request a video key
	public void sendKeyRequest(String uuid) {
		
		try {
			out.writeUTF(uuid + ",reqKey");
			out.flush();
			System.out.println("reqKey send!");
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
}
