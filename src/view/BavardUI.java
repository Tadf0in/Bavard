package view;

import javax.swing.JFrame;

import model.Bavard;

public class BavardUI extends JFrame {

	private Bavard bavard;
	
	public BavardUI(Bavard bavard) {
		this.setTitle(bavard.getNom());
	}
}
