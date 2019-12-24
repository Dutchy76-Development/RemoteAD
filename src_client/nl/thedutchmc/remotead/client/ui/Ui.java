package nl.thedutchmc.remotead.client.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import nl.thedutchmc.remotead.client.Client;

public class Ui extends Application {
	
	static Stage stage;
	
	String STYLESHEET_PATH = "style.css";
	public static Stage getStage() {
		return stage;
	}
	
	public static Thread tr;
	public static boolean uiRunning = false;
	
	@Override
	public void start(Stage stage) {
		Ui.stage = stage;		

		uiRunning = true;
		
		tr = Thread.currentThread();
		
        BackgroundFill background_fill = new BackgroundFill(Color.BLACK,  
                CornerRadii.EMPTY, Insets.EMPTY);
        
        Background background = new Background(background_fill);
        
        HBox hbox = new HBox();
        
        hbox.setBackground(background);
        Scene scene = new Scene(hbox, 280, 280); 
		
		stage.setTitle("RemoteAD Client");
		stage.setScene(scene);
		stage.show();
	}
	
	public void playVideo(String videoKey) {
		WebView webview = new WebView();
		
		Stage stage = Ui.getStage();
		
	    webview.getEngine().load("https://www.youtube.com/embed/" + videoKey + "?&autoplay=1&showinfo=0&modestbranding=0&controls=0&disablekb=1&vq=hd1080&rel=0");
	    webview.setPrefSize(640, 390);
	    
	    stage.setScene(new Scene(webview));
	    stage.show();
}

}
