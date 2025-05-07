package view;

import java.awt.GridLayout;

import javax.swing.JFrame;

import model.Batiment;
import model.Concierge;

@SuppressWarnings("serial")
class MainUI extends JFrame {
	
	private Batiment batiment;
	private Concierge concierge;
	
	public MainUI() {
		this.setTitle("Bavard");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		this.setLayout(new GridLayout(1, 2));
		
		this.batiment = new Batiment();
		this.concierge = batiment.creerConcierge("Serge le concierge");
		
		BatimentUI batimentUI = new BatimentUI(batiment);
		this.getContentPane().add(batimentUI);
		
		ConciergeUI conciergeUI = new ConciergeUI(concierge);
		this.getContentPane().add(conciergeUI);
	}
}