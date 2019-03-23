package server;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServerGui {
	
	boolean stateInit = true;

	public void init() {
		
		//Frame Init
		JFrame frame = new JFrame();
		frame.setTitle("RemoteAD Server");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false);
		frame.getContentPane().setLayout(null);		
		frame.setVisible(true);
		frame.setSize(800, 800);

		int w = frame.getWidth();
		int h = frame.getHeight();
		
		//Labels during Initializing 
		JLabel splashText = new JLabel("RemoteAD Server");
		splashText.setFont(new Font("Serif", Font.BOLD, 60));
		splashText.setBounds(w / 2 - splashText.getPreferredSize().width / 2, h / 2 - splashText.getPreferredSize().height / 2, splashText.getPreferredSize().width, splashText.getPreferredSize().height);
		frame.getContentPane().add(splashText);
			
		JLabel stateInit = new JLabel("Initializing");
		stateInit.setFont(new Font("Serif", Font.BOLD, 30));
		stateInit.setBounds(w / 2 - stateInit.getPreferredSize().width / 2, h / 2 - stateInit.getPreferredSize().height + splashText.getPreferredSize().height + 5, stateInit.getPreferredSize().width, stateInit.getPreferredSize().height);
		frame.getContentPane().add(stateInit);
		
		
		//Labels for when the server is online
		JLabel stateOnline = new JLabel("Sever Online!");
		stateOnline.setFont(new Font("Serif", Font.PLAIN, 25));
		stateOnline.setBounds(10, 10, stateOnline.getPreferredSize().width, stateOnline.getPreferredSize().height);
		frame.getContentPane().add(stateOnline);
			
		JLabel waitingForTrigger = new JLabel("Accepting all clients on port 888");
		waitingForTrigger.setFont(new Font("Serif", Font.PLAIN, 15));
		waitingForTrigger.setBounds(10, stateOnline.getPreferredSize().height + 10, waitingForTrigger.getPreferredSize().width, waitingForTrigger.getPreferredSize().height);
		frame.getContentPane().add(waitingForTrigger);
	}
}
