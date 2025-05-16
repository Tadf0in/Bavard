package view;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import model.Bavard;

@SuppressWarnings("serial")
public class BavardUI extends JFrame {
	
	private Bavard bavard;

	public BavardUI(Bavard bavard, MainUI mainui) {	
		this.bavard = bavard; 
		
		this.setTitle("Bavard - " + bavard.getNom());
		this.setSize(750, 500);
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setLayout(new GridLayout(1, 3));
		
		EnvoyerMessageUI envoyer = new EnvoyerMessageUI(this.bavard, mainui);
		this.getContentPane().add(envoyer);
		
		MessagesUI messages = new MessagesUI(this.bavard, mainui);
		this.getContentPane().add(messages);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainui.removeMessagesUI(messages);
                dispose();
            }
        });
	}
}
