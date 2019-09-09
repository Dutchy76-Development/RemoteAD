package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SensorTrigger {

	//Run this when sennsor is triggered
	//TODO
	public void onTrigger() throws Exception {
		 
		ClientSocketHandler handler = new ClientSocketHandler();
		
		
		String uuid = getUuid();
		handler.sendKeyRequest(uuid);
	}
	
	//Get UUID from uuid.txt
	public String getUuid() {
		String uuid = null;
		
		String path = new File("src/client/uuid.txt").getAbsolutePath();
		
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			
			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) {
				uuid = sCurrentLine;
				System.out.println("Client UUID: " + uuid);
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return uuid;
	}
}
