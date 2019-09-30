package server;

import java.util.ArrayList;
import java.util.List;

public class ClientsHandler {

	static List<Clients> clients = new ArrayList<Clients>();
	
	public static void addClient(Clients client) {
		clients.add(client);
	}
	
	public static boolean removeClient(Clients client) {
		return clients.remove(client);
	}
	
	public static List<Clients> getClients() {
		return clients;
	}
	
	public static int getClientId(Clients client1) {
		for(Clients client : clients) {
			if(client.equals(client1)) {
				return client.getId();
			}
			break;
		}
		return 0;
	}
}
