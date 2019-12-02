package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ClientVideoPlayer extends Application{
	
	private Stage stage;
	private WebView webview;
	
	@Override
	public void start(Stage stage) throws Exception {
	    WebView webview = new WebView();
	    
	    this.webview = webview;
	    this.stage = stage;

	    playVideo();
	    
	}
	
	public void playVideo() {
		String videoKey = ClientSocketHandler.videoKey;
	    webview.getEngine().load("https://www.youtube.com/embed/" + videoKey + "?&autoplay=1&showinfo=0&modestbranding=0&controls=0&disablekb=1&vq=hd1080&rel=0");
	    webview.setPrefSize(640, 390);
	    
	    stage.setScene(new Scene(webview));
	    stage.show(); 
	}
}
