package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import model.Batiment;
import model.Bavard;
import model.Concierge;

@SuppressWarnings("serial")
class MainUI extends JFrame {
	
	private Batiment batiment;
	private Concierge concierge;
	
	private List<MessagesUI> messagesUIs;
	
	public MainUI() {
		this.setTitle("Concierge");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		this.setLayout(new GridLayout(1, 2));
		
		this.batiment = new Batiment();
		this.concierge = batiment.creerConcierge("Serge le concierge");
		
		this.messagesUIs = new ArrayList<MessagesUI>();
		
		BatimentUI batimentUI = new BatimentUI(batiment, this);
		this.getContentPane().add(batimentUI);
		
		Bavard bob = batiment.creerBavard("Bob");
		bob.envoyerMessage("Réponse", "Salut !");
		
		ConciergeUI conciergeUI = new ConciergeUI(concierge, this);
		this.getContentPane().add(conciergeUI);
	}
	
	public void addMessagesUI(MessagesUI messagesUI) {
		this.messagesUIs.add(messagesUI);
	}

	public void removeMessagesUI(MessagesUI messagesUI) {
		this.messagesUIs.remove(messagesUI);
	}
	
	public void refreshMessagesUIs() {
		for (MessagesUI messagesUI: this.messagesUIs) {
			messagesUI.refreshMessages();
		}
	}
}