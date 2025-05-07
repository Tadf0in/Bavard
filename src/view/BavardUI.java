package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Bavard;

@SuppressWarnings("serial")
public class BavardUI extends JFrame {

	private Bavard bavard;
	
	private JTextField sujet;
	private JTextArea contenu;
	private JButton envoyer;
	
	public BavardUI(Bavard bavard) {
		this.bavard = bavard;
		this.setTitle(bavard.getNom());
		this.setSize(300, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel sujetLabel = new JLabel("Sujet :");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(sujetLabel, gbc);

        sujet = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(sujet, gbc);

        JLabel contenuLabel = new JLabel("Contenu :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(contenuLabel, gbc);

        contenu = new JTextArea(10, 20);
        contenu.setLineWrap(true);
        contenu.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(contenu);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        envoyer = new JButton("Envoyer");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(envoyer, gbc);

        this.add(panel);
        
        envoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sujetText = sujet.getText();
                String contenuText = contenu.getText();

                bavard.envoyerMessage(sujetText, contenuText);

                sujet.setText("");
                contenu.setText("");
            }
        });
	}
}
