/*
Copyright (C) Tobias de Bruijn

Under domain thedutchmc.nl

You are not allowed to redistribute this code as your own!
*/

package nl.thedutchmc.remotead.client.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javafx.scene.web.WebView;
import javafx.stage.Stage;
import nl.thedutchmc.remotead.client.Client;
import nl.thedutchmc.remotead.client.ui.ClientVideoPlayer;

public class ClientSocketHandler  extends Thread {

	Socket socket = null;
	DataInputStream in = null;
	DataOutputStream out = null;
	boolean connected = false;
	
	public static String videoKey;
	String msg_rec = null;
	
	final Client client = new Client();

	public ClientSocketHandler() throws Exception {

		String ip = "192.168.1.7";
		int port = 8028;
		
		//Attempt server connection
		try {
			socket = new Socket(ip, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			System.out.println("Trying to receive message!");
			SocketReceiver socketReceiver = new SocketReceiver(in, socket);
			socketReceiver.start();
			
			//gui.setConnectedState();
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
				out.writeUTF("0002,reqKey");
				out.flush();
				System.out.println("reqKey send!");
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		} else {
			System.out.println("Not Connected, Not sending keyReq");
		}
	}
	
	//Receive data back from server
	class SocketReceiver extends Thread {
		private Socket socket;
		private DataInputStream in;
		
		public SocketReceiver(DataInputStream in, Socket socket) {
			this.socket = socket;
			this.in = in;
		}
		
		public void run() {
			
			if(socket.isConnected()) {
				while(true) {
					
					try {
						msg_rec = in.readUTF();
						System.out.println(msg_rec);
						
						
						//Start playing the video
						new Thread() {
							@Override
							public void run() {
								System.out.println("Starting JavaFX app");
								
								videoKey = msg_rec;
																
								javafx.application.Application.launch(ClientVideoPlayer.class);
							}
						}.start();
						
					} catch (Exception e) {
						if(e instanceof EOFException) {
							continue;
						} else {
							System.err.println("[ClientSocketHandler] Issue with reading reading message!");
							e.printStackTrace(System.err);
							break;
						}
					}
						
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (Exception e1) {
						e1.printStackTrace();
						break;
					}
				}
			} else {
				System.out.println("Im not connected!");
			}
		}
	}
	
	//Check if we're connected or not
	class checkIfConnected {
		
		private Socket socket;
		
		public checkIfConnected(Socket socket) {
			this.socket = socket;
		}
		
		void run() {
			if(socket.isConnected()) {
				System.err.println("[ClientSocketHandler] Socket is closed!");
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ClientSocketHandler handler;
				try {
					handler = new ClientSocketHandler();
					handler.connect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
