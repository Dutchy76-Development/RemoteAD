package client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ClientGui {

	public void init() {
		
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Frame init
		JFrame frame = new JFrame();
		frame.setTitle("VideoAD Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(false);
		frame.getContentPane().setLayout(null);		
		frame.setVisible(true);
		
		int w = frame.getSize().width;
		int h = frame.getSize().height;
				
		//Labels
		JLabel splashText = new JLabel("RemoteAD", SwingConstants.CENTER);
		splashText.setFont(new Font("Serif", Font.BOLD, 60));
		splashText.setBounds((dim.width-splashText.getSize().width)/2, 15, 300, 80);
		frame.getContentPane().add(splashText);
		
		JLabel subSplashText = new JLabel("Initializing");
		subSplashText.setFont(new Font("Serif", Font.BOLD, 30));
		subSplashText.setBounds(322, 11, 192, 64);

		frame.getContentPane().add(subSplashText);

	}
}
