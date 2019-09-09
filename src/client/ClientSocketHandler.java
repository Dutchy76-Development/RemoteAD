package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientSocketHandler  extends Thread {

	Socket socket = null;
	DataInputStream in = null;
	DataOutputStream out = null;
	boolean connected = false;

	public ClientSocketHandler() throws Exception {

		String ip = "192.168.1.7";
		int port = 8008;
		
		ClientGui gui = new ClientGui();
		
		//Attempt server connection
		try {
			socket = new Socket(ip, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			System.out.println("Trying to receive message!");
			final ClientSocketReceiver receiver = new ClientSocketReceiver(in);
			receiver.start();
			
			gui.setConnectedState();
			System.out.println("connected");
			connected = true;
		} catch (Exception e) {
			System.out.println("unable to connect to server on IP: " + ip + ":" + port);
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void connect() throws Exception {
		new ClientSocketHandler();
	}
	
	//Request a video key
	public void sendKeyRequest(String uuid) {
		
		if(connected) {
			try {
				out.writeUTF(uuid + ",reqKey");
				out.flush();
				System.out.println("reqKey send!");
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		} else {
			System.out.println("Not Connected, Not sending keyReq");
		}
	}
}
