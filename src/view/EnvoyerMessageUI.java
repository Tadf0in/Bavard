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
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import model.Bavard;
import model.PapotageListener;
import model.ThemesEnum;

@SuppressWarnings("serial")
public class EnvoyerMessageUI extends JPanel {

	private Bavard bavard;
	
	private JTextField sujet;
    private JComboBox<ThemesEnum> theme;
    private JComboBox<Object> destinataire;
	private JTextArea contenu;
	private JButton envoyer;
	
	public EnvoyerMessageUI(Bavard bavard, MainUI mainui) {
		this.bavard = bavard;
		
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Sujet
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Sujet :"), gbc);

        sujet = new JTextField(20);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(sujet, gbc);

        // Theme
        gbc.gridy++;
        gbc.fill = GridBagConstraints.WEST;
        panel.add(new JLabel("Thème :"), gbc);

        theme = new JComboBox<>(ThemesEnum.values());
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(theme, gbc);
        
        // Destinataire
        gbc.gridy++;
        gbc.fill = GridBagConstraints.WEST;
        panel.add(new JLabel("Destinataire :"), gbc);

        destinataire = new JComboBox<>();
        destinataire.addItem("Général");
        destinataire.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            	destinataire.removeAllItems();
                destinataire.addItem("Général");
                for (PapotageListener b : bavard.getConcierge().getListeners()) {
                    if (!b.equals(bavard)) {
                        destinataire.addItem(b);
                    }
                }
            }
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });
        gbc.gridy++;
        panel.add(destinataire, gbc);
        
        // Contenu
        JLabel contenuLabel = new JLabel("Contenu :");
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(contenuLabel, gbc);

        contenu = new JTextArea(10, 20);
        contenu.setLineWrap(true);
        contenu.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(contenu);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        envoyer = new JButton("Envoyer");
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(envoyer, gbc);

        this.add(panel);
        
        envoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sujetText = sujet.getText();
                String contenuText = contenu.getText();
                ThemesEnum themeEnum = (ThemesEnum) theme.getSelectedItem();
                Object destinataireObject = destinataire.getSelectedItem();

                if ("Général".equals(destinataireObject)) {
                    bavard.envoyerMessage(sujetText, contenuText, themeEnum);
                } else if (destinataireObject instanceof Bavard) {
                    bavard.directMessage(sujetText, contenuText, themeEnum, (Bavard) destinataireObject);
                }

                sujet.setText("");
                contenu.setText("");
                theme.setSelectedIndex(0);
                destinataire.setSelectedIndex(0);
                
                mainui.refreshMessagesUIs();
            }
        });
	}
}
