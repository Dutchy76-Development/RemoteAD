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
		
		//Attempt server connection
		try {
			socket = new Socket("client.remotead.thedutchmc.nl", 888);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// cleanup
		out.close();
		in.close();
		socket.close();
	}
	

}
