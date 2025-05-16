package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.PapotageListener;

@SuppressWarnings("serial")
public class MessagesUI extends JPanel {
	
	private PapotageListener listener;
	
	private JPanel messageContainer;
	private JTextField searchField;
	private String searchText = "";
	
	public MessagesUI(PapotageListener listener, MainUI mainui) {
		this.listener = listener;
		mainui.addMessagesUI(this);
		
		this.setLayout(new BorderLayout());
		
		searchField = new JTextField(20);
		JPanel searchPanel = new JPanel(new BorderLayout());
		searchPanel.add(new JLabel("Rechercher : "), BorderLayout.WEST);
		searchPanel.add(searchField, BorderLayout.CENTER);
		this.add(searchPanel, BorderLayout.NORTH);
		
		searchField.getDocument().addDocumentListener(new DocumentListener() {
		    public void insertUpdate(DocumentEvent e) { refreshMessages(); }
		    public void removeUpdate(DocumentEvent e) { refreshMessages(); }
		    public void changedUpdate(DocumentEvent e) { refreshMessages(); }
		});
		
		messageContainer = new JPanel();
		messageContainer.setPreferredSize(new Dimension(350, 500));
        messageContainer.setLayout(new BoxLayout(messageContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(messageContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, BorderLayout.CENTER);
        
        refreshMessages();
	}
	
	public void refreshMessages() {
		messageContainer.removeAll();
		for (Map<String, String> message: this.listener.getMessagesRecus()) {
			String auteur = message.get("auteur");
	        String sujet = message.get("sujet");
	        String contenu = message.get("contenu");
	        String theme = message.get("theme");

	        String messageText = auteur + ": [" + sujet + "] " + contenu;
	        
	        searchText = searchField.getText().toLowerCase().trim();
	        if (searchText.isEmpty() || messageText.toLowerCase().contains(searchText)) {
	            JLabel messageLabel = new JLabel(messageText);
	            messageContainer.add(messageLabel);
	        }
		}
        messageContainer.revalidate();
        messageContainer.repaint();
	}
}
