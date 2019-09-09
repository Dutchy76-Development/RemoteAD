package client;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ClientGui {
	
	public JFrame frame = new JFrame();
	
	public JLabel splashText = new JLabel("RemoteAD Client");
	public JLabel stateInit = new JLabel("Initializing");
	public JLabel stateConnected = new JLabel("Connected to server");
	public JLabel waitingForTrigger = new JLabel("Waiting for Sensor Trigger");
	
	public void init() {
			
		//Frame init
		//JFrame frame = new JFrame();
		frame.setTitle("RemoteAD Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false);
		frame.getContentPane().setLayout(null);		
		frame.setVisible(true);
		frame.setSize(800, 800);
		
		int w = frame.getWidth();
		int h = frame.getHeight();
		
		//Labels for init State
		splashText.setFont(new Font("Serif", Font.BOLD, 60));
		splashText.setBounds(w / 2 - splashText.getPreferredSize().width / 2, h / 2 - splashText.getPreferredSize().height / 2, splashText.getPreferredSize().width, splashText.getPreferredSize().height);
		frame.getContentPane().add(splashText);
		
		stateInit.setFont(new Font("Serif", Font.BOLD, 30));
		stateInit.setBounds(w / 2 - stateInit.getPreferredSize().width / 2, h / 2 - stateInit.getPreferredSize().height + splashText.getPreferredSize().height + 5, stateInit.getPreferredSize().width, stateInit.getPreferredSize().height);
		frame.getContentPane().add(stateInit);

		//Labels for connected State
		stateConnected.setFont(new Font("Serif", Font.PLAIN, 25));
		stateConnected.setBounds(10, 10, stateConnected.getPreferredSize().width, stateConnected.getPreferredSize().height);
			
		waitingForTrigger.setFont(new Font("Serif", Font.PLAIN, 15));
		waitingForTrigger.setBounds(10, stateConnected.getPreferredSize().height + 10, waitingForTrigger.getPreferredSize().width, waitingForTrigger.getPreferredSize().height);
	}
	
	
	//Change fron init to connected state
	public void setConnectedState() {		
		frame.remove(splashText);
		frame.remove(stateInit);
		frame.add(stateConnected);
		frame.add(waitingForTrigger);

		frame.revalidate();
		frame.repaint();
		
		System.out.println("Changing Frame to ConnectedState");
	}
}
