package client.gui;

import javax.swing.JFrame;

public class YoutubeViewer {
	
	public static void main(String[] args) {
		final YoutubeViewer ytv = new YoutubeViewer();
		ytv.init();
	}
	
	public void init() {
		JFrame frame = new JFrame();
		frame.setTitle("RemoteAD Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false);
		frame.getContentPane().setLayout(null);		
		frame.setVisible(true);
		frame.setSize(800, 800);
	}
}
