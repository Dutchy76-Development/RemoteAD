/*
Copyright (C) Tobias de Bruijn

Under domain thedutchmc.nl

You are not allowed to redistribute this code as your own!
*/
package nl.thedutchmc.remotead.client.ui;

import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import nl.thedutchmc.remotead.client.Client;
import nl.thedutchmc.remotead.client.socket.ClientSocketHandler;

public class ClientVideoPlayer {
	
	public void playVideo(String videoKey) {
			WebView webview = new WebView();
			
			Stage stage = Ui.getStage();
			
		    webview.getEngine().load("https://www.youtube.com/embed/" + videoKey + "?&autoplay=1&showinfo=0&modestbranding=0&controls=0&disablekb=1&vq=hd1080&rel=0");
		    webview.setPrefSize(640, 390);
		    
		    stage.setScene(new Scene(webview));
		    stage.show(); 
	}
}