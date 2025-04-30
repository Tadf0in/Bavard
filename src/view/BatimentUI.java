package view;

import java.awt.BorderLayout;

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
	private DefaultListModel<String> bavardListModel;
	private JList<String> bavardList;
	
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
		
		bavardListModel = new DefaultListModel<>();
	    bavardList = new JList<>(bavardListModel);
	    this.add(new JScrollPane(bavardList), BorderLayout.CENTER);
	    
	    ajouterBtn.addActionListener(e -> {
            String nom = nomBavardField.getText().trim();
            if (!nom.isEmpty()) {
                Bavard b = batiment.creerBavard(nom);
                bavardListModel.addElement(nom);
                nomBavardField.setText("");
            }
        });
	}
}
