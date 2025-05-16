package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.PapotageListener;

@SuppressWarnings("serial")
public class MessagesUI extends JPanel {
	
	private PapotageListener listener;
	
	private JPanel messageContainer;
	
	public MessagesUI(PapotageListener listener, MainUI mainui) {
		this.listener = listener;
		mainui.addMessagesUI(this);
		
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
			String messageText = message.get("auteur") + ": [" + message.get("sujet") + "] " + message.get("contenu");
	        JLabel messageLabel = new JLabel(messageText);
	        messageContainer.add(messageLabel);
		}
        messageContainer.revalidate();
        messageContainer.repaint();
	}
}
