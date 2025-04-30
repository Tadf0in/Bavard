package model;

import java.util.ArrayList;
import java.util.List;

public class Concierge {
	private String nom;
    private List<PapotageListener> listeners;
    
    // Constructeur
    public Concierge(String nom) {
    	this.nom = nom;
    	this.listeners = new ArrayList<PapotageListener>();
    }
    
    // Getters
    public String getNom() {
        return nom;
    }
    
    // Méthodes
    
    public void addListener(PapotageListener listener) {
        listeners.add(listener);
    }

    public void removeListener(PapotageListener listener) {
        listeners.remove(listener);
    }

    public void receivePapotage(PapotageEvent event) {
    	Bavard author = (Bavard) event.getSource();
    	
    	System.out.println(author.getNom() + " a envoyé un message : [" + event.getSujet() + "] " + event.getCorps());
  
        for (PapotageListener listener : listeners) {
	        if (listener != author) {
	        	listener.onPapotage(event);
	        }
        }
    }
}
