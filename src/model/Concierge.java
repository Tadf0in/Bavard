package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Concierge implements PapotageListener {
	private String nom;
	private List<Map<String, String>> messagesRecus;
    private List<PapotageListener> listeners;
    
    // Constructeur
    public Concierge(String nom) {
    	this.nom = nom;
    	this.messagesRecus = new ArrayList<>();
    	this.listeners = new ArrayList<PapotageListener>();
    }
    
    // Getters
    public String getNom() {
        return nom;
    }
    public List<Map<String, String>> getMessagesRecus() {
    	return this.messagesRecus;
    }
	public List<ThemesEnum> getThemesSuivis() {
		return null;
	}
    
    // Méthodes
    
    public void addListener(PapotageListener listener) {
        listeners.add(listener);
    }

    public void removeListener(PapotageListener listener) {
        listeners.remove(listener);
    }

    public void onPapotage(PapotageEvent event) {
    	try {    		
    		Bavard auteur = null;
    		String message = "";
    		Map<String, String> messageMap = new HashMap<>();
    		
    		if (event.getSource() instanceof Bavard) {    			
    			auteur = (Bavard) event.getSource();
    			messageMap.put("auteur", auteur.getNom());
    			message += auteur.getNom() + " a envoyé un message : ";
    		} else {
    			messageMap.put("auteur", "");
    		}
    		
    		String sujet = event.getSujet();
    		String corps = event.getCorps();
    		ThemesEnum theme = event.getTheme();
    		
    		message += "[" + sujet + "] " + corps;
    		System.out.println(message);
    		            
    		messageMap.put("sujet", sujet);
    		messageMap.put("contenu", corps);
    		messageMap.put("theme", theme.toString());
            this.messagesRecus.add(messageMap);
    		
    		for (PapotageListener listener : listeners) {
    			if (listener != auteur) {
    				if (listener.getThemesSuivis().contains(theme)) {    					
    					listener.onPapotage(event);
    				}
    			}
    		}
    	} catch (Exception e) {
    		System.out.println("Impossible d'envoyer le message." + e);
    	}
    }
}
