/*
Copyright (C) Tobias de Bruijn

Under domain thedutchmc.nl

You are not allowed to redistribute this code as your own!
*/
package nl.thedutchmc.remotead.server.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerSocketSender extends Thread {
	
	public void sendMessage(String message, Socket socket, DataOutputStream out) {
		
		if(socket.isConnected()) {
			try {
				out.writeUTF(message);
				out.flush();
				System.out.println("Send key!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("[ServerSocketSender] Not Connected, Not sending the key!");
		}
	}
}
