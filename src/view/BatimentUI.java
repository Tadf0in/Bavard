package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Batiment;
import model.Bavard;

public class BatimentUI extends JPanel {
	
	private Batiment batiment;
  
	private JTextField nomBavardField;
	private JButton ajouterBtn;
	//private DefaultListModel<String> bavardListModel;
	//private JList<String> bavardList;
	private JPanel bavardsPanel;
	
	public BatimentUI(Batiment batiment) {		
		this.batiment = batiment;
		
		this.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.add(new JLabel("Nom :"));
		nomBavardField = new JTextField(15);
		top.add(nomBavardField);
		ajouterBtn = new JButton("Ajouter");
		top.add(ajouterBtn);
		this.add(top, BorderLayout.NORTH);
		
		//bavardListModel = new DefaultListModel<>();
	    //bavardList = new JList<>(bavardListModel);
		//this.add(new JScrollPane(bavardList), BorderLayout.CENTER);
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
        });
	}
	
	public void ajouterBavardUI(Bavard bavard) {
		JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nomLabel = new JLabel(bavard.getNom());
        JButton toggleBtn = new JButton("🟢");
        toggleBtn.setForeground(Color.GREEN);
        JButton openBtn = new JButton("🗨️");
        
        ligne.add(nomLabel);
        ligne.add(toggleBtn);
        ligne.add(openBtn);

        bavardsPanel.add(ligne);
        bavardsPanel.revalidate();
        bavardsPanel.repaint();
	}
}
