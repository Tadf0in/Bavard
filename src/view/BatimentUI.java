package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Batiment;
import model.Bavard;

@SuppressWarnings("serial")
public class BatimentUI extends JPanel {
	
	private Batiment batiment;
  
	private MainUI mainui;
	private Map<Bavard, BavardUI> bavardUIs = new HashMap<>();
	
	private JTextField nomBavardField;
	private JButton ajouterBtn;
	private JPanel bavardsPanel;
	
	public BatimentUI(Batiment batiment, MainUI mainui) {		
		this.batiment = batiment;
		this.mainui = mainui;
		this.bavardUIs = new HashMap<>();
		
		this.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.add(new JLabel("Nom :"));
		nomBavardField = new JTextField(15);
		top.add(nomBavardField);
		ajouterBtn = new JButton("Ajouter");
		top.add(ajouterBtn);
		this.add(top, BorderLayout.NORTH);
		
	    bavardsPanel = new JPanel();
	    bavardsPanel.setLayout(new BoxLayout(bavardsPanel, BoxLayout.Y_AXIS));
	    this.add(new JScrollPane(bavardsPanel), BorderLayout.CENTER);
	    
	    ajouterBtn.addActionListener(e -> {
            String nom = nomBavardField.getText().trim();
            if (!nom.isEmpty()) {
                Bavard bavard = batiment.creerBavard(nom);
                ajouterBavardUI(bavard);
                nomBavardField.setText("");
            }
            mainui.refreshMessagesUIs();
        });
	}
	
	public void ajouterBavardUI(Bavard bavard) {
		JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nomLabel = new JLabel(bavard.getNom());
        JButton toggleBtn = new JButton("🟢");
        JButton openBtn = new JButton("🗨️");
        
        bavardUIs.put(bavard, new BavardUI(bavard, this.mainui));
        
        toggleBtn.setForeground(Color.GREEN);
        toggleBtn.addActionListener(e -> {
            if (bavard.isConnected()) {
                batiment.deconnecter(bavard);
                toggleBtn.setForeground(Color.RED);
                bavardUIs.get(bavard).dispose();
            } else {
                batiment.connecter(bavard);
                toggleBtn.setForeground(Color.GREEN);
            }
            mainui.refreshMessagesUIs();
        });
        
        openBtn.addActionListener(e -> {
            BavardUI ui = bavardUIs.get(bavard);
            if (ui.isVisible()) {
            	ui.toFront(); // remonter la fenêtre si déjà ouverte            	
            } else {
            	if (bavard.isConnected()) {            		
            		ui.setVisible(true);
            	}
            }
        });
        
        ligne.add(nomLabel);
        ligne.add(toggleBtn);
        ligne.add(openBtn);

        bavardsPanel.add(ligne);
        bavardsPanel.revalidate();
        bavardsPanel.repaint();
	}
}
