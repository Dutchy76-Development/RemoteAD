package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocketHandler  extends Thread {

	public ClientSocketHandler() throws Exception {
		Socket socket = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		
		ClientGui gui = new ClientGui();
		
		//Attempt server connection
		try {
			socket = new Socket("192.168.1.24", 888);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			gui.stateConnected = true;
			gui.stateInit = false;
			gui.reloadFrame();
			System.out.println("connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// cleanup
		out.close();
		in.close();
		socket.close();
	}
	
	public void connect() throws Exception {
		new ClientSocketHandler();
	}
	

}
