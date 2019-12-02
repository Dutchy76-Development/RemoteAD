/*
Copyright (C) Tobias de Bruijn

Under domain thedutchmc.nl

You are not allowed to redistribute this code as your own!
*/

package client;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientSocketReceiver extends Thread {
	
	public String receivedMessage;
	private DataInputStream in;
	private Socket socket;
	
	final Client client = new Client();
	
	public ClientSocketReceiver(DataInputStream in, Socket socket) {
		this.in = in;
		this.socket = socket;
	}
	
	public void run() {
		while(true) {
			String msg_rec = null;
			try {
				msg_rec = in.readUTF();
			} catch (Exception e) {
				if(e instanceof EOFException) {
					continue;
				} else {
					System.err.println("[ServerSocketHandler] Issue with reading reading message!");
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
	}
}