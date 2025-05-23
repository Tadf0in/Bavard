package view;

import java.awt.BorderLayout;
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
		
		EnvoyerMessageUI envoyer = new EnvoyerMessageUI(this.bavard, mainui);
		this.getContentPane().add(envoyer, BorderLayout.WEST);
		
		MessagesUI messages = new MessagesUI(this.bavard, mainui);
		this.getContentPane().add(messages, BorderLayout.CENTER);
		
		ThemesUI themes = new ThemesUI(this.bavard);
		this.getContentPane().add(themes, BorderLayout.EAST);
		
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
