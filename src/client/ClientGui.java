package client;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
public class ClientGui {
	
	boolean stateInit = false;
	boolean stateConnected = true;
	
	public void init() {
			
		//Frame init
		JFrame frame = new JFrame();
		frame.setTitle("VideoAD Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false);
		frame.getContentPane().setLayout(null);		
		frame.setVisible(true);
		
		int w = frame.getWidth();
		int h = frame.getHeight();
		
		//Labels
		
		//If in Initialization state
		if(stateInit) {
			JLabel splashText = new JLabel("RemoteAD");
			splashText.setFont(new Font("Serif", Font.BOLD, 60));
			splashText.setBounds(w / 2 - splashText.getPreferredSize().width / 2, h / 2 - splashText.getPreferredSize().height / 2, splashText.getPreferredSize().width, splashText.getPreferredSize().height);
			frame.getContentPane().add(splashText);
			
			JLabel state = new JLabel("Initializing");
			state.setFont(new Font("Serif", Font.BOLD, 30));
			state.setBounds(w / 2 - state.getPreferredSize().width / 2, h / 2 - state.getPreferredSize().height + splashText.getPreferredSize().height + 5, state.getPreferredSize().width, state.getPreferredSize().height);
			frame.getContentPane().add(state);
		} else if(stateConnected) { //If Connected to Server
			JLabel state = new JLabel("Connected to server");
			state.setFont(new Font("Serif", Font.PLAIN, 25));
			state.setBounds(10, 10, state.getPreferredSize().width, state.getPreferredSize().height);
			frame.getContentPane().add(state);
			
			JLabel waitingForTrigger = new JLabel("Waiting for Sensor Trigger");
			waitingForTrigger.setFont(new Font("Serif", Font.PLAIN, 15));
			waitingForTrigger.setBounds(10, state.getPreferredSize().height + 10, waitingForTrigger.getPreferredSize().width, waitingForTrigger.getPreferredSize().height);
			frame.getContentPane().add(waitingForTrigger);
		}
	}
}
