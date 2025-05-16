package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Bavard;
import model.ThemesEnum;

@SuppressWarnings("serial")
public class EnvoyerMessageUI extends JPanel {

	private Bavard bavard;
	
	private JTextField sujet;
    private JComboBox<ThemesEnum> theme;
	private JTextArea contenu;
	private JButton envoyer;
	
	public EnvoyerMessageUI(Bavard bavard, MainUI mainui) {
		this.bavard = bavard;
		
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Sujet
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

        // Theme
        JLabel themeLabel = new JLabel("Thème :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(themeLabel, gbc);

        theme = new JComboBox<>(ThemesEnum.values());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(theme, gbc);
        
        // Contenu
        JLabel contenuLabel = new JLabel("Contenu :");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(contenuLabel, gbc);

        contenu = new JTextArea(10, 20);
        contenu.setLineWrap(true);
        contenu.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(contenu);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        envoyer = new JButton("Envoyer");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(envoyer, gbc);

        this.add(panel);
        
        envoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sujetText = sujet.getText();
                String contenuText = contenu.getText();
                ThemesEnum themeText = (ThemesEnum) theme.getSelectedItem();

                bavard.envoyerMessage(sujetText, contenuText, themeText);
                mainui.refreshMessagesUIs();

                sujet.setText("");
                contenu.setText("");
            }
        });
	}
}
