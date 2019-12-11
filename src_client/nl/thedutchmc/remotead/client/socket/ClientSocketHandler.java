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

import javafx.application.Platform;
import nl.thedutchmc.remotead.client.Client;
import nl.thedutchmc.remotead.client.ui.ClientVideoPlayer;
import nl.thedutchmc.remotead.client.ui.Ui;

public class ClientSocketHandler  extends Thread {

	Socket socket = null;
	DataInputStream in = null;
	DataOutputStream out = null;
	boolean connected = false;
	
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

			SocketReceiver socketReceiver = new SocketReceiver(in, socket);
			socketReceiver.start();
			
			//gui.setConnectedState();
			System.out.println("connected");
			connected = true;
		} catch (Exception e) {
			Client.log("Unable to connect to server on IP: " + ip + ":" + port);
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
			Client.log("Not Connected, Not sending keyReq");
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
							
						//Start playing the video
						new Thread(new Runnable() {
							@Override
							public void run() {
								System.out.println("Starting JavaFX app");
								
								//Start the video player
								Ui ui = new Ui();
								
								if(Ui.uiRunning) {
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											ui.playVideo(msg_rec);
										}
									});
								} else {
									Client.log("JavaFX Isn't running yet!");
								}
							}
						}).start();
						
					} catch (Exception e) {
						if(e instanceof EOFException) {
							continue;
						} else {
							Client.log("[ClientSocketHandler] Issue with reading reading message!");
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
				Client.log("Not connected to server!");
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
				Client.log("Not connected to server");
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
