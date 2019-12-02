/*
Copyright (C) Tobias de Bruijn

Under domain thedutchmc.nl

You are not allowed to redistribute this code as your own!
*/
package nl.thedutchmc.remotead.server.clientHandler;

import java.net.Socket;

public class Clients {
	
	private Socket socket;
	private int id;
	private boolean enabled;
	
	
	public Clients(int id, Socket socket, boolean enabled) {
		this.socket = socket;
		this.id = id;
		this.enabled = enabled;
	}
	
	//Socket
	public Socket getSocket() {
		return this.socket;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	//ID
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	//Boolean enabled
	public boolean getEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
