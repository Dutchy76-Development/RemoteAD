package client;

import java.io.DataOutputStream;
import java.io.IOException;

public class ClientSocketSender {

	public void sendKeyRequest(String uuid, DataOutputStream out) {
		try {
			out.writeUTF(uuid);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
	
}
