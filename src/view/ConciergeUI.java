package view;

import javax.swing.JPanel;

import model.Concierge;

@SuppressWarnings("serial")
public class ConciergeUI extends JPanel {
	
	private Concierge concierge;
	
	public ConciergeUI(Concierge concierge) {		
		this.concierge = concierge;
	}
}
