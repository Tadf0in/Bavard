package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bavard implements PapotageListener {
    // Attributs
	private String nom;
    private Concierge concierge;
    private List<Map<String, String>> messagesRecus;
    private List<ThemesEnum> themesSuivis;

    // Constructeur
    public Bavard(String nom) {
        this.nom = nom;
        this.messagesRecus = new ArrayList<>();
        this.themesSuivis = new ArrayList<>(Arrays.asList(ThemesEnum.values()));
    }
    
    // Getters
    public String getNom() {
        return nom;
    }
    public List<Map<String, String>> getMessagesRecus() {
    	return this.messagesRecus;
    }
    public List<ThemesEnum> getThemesSuivis() {
    	return this.themesSuivis;
    }
    
    // Méthodes
    
    public void seConnecter(Concierge concierge) {
        this.concierge = concierge;     	
    	OnLineBavardEvent event = new OnLineBavardEvent(this);
    	this.concierge.onPapotage(event);
    	concierge.addListener(this);
    }
    
    public void seDeconnecter() {
    	this.concierge.removeListener(this);
    	OffLineBavardEvent event = new OffLineBavardEvent(this);
    	this.concierge.onPapotage(event);
    	this.concierge = null;
    }
    
    public boolean isConnected() {
    	return this.concierge != null;
    }

    public void envoyerMessage(String sujet, String corps, ThemesEnum theme) {
        PapotageEvent event = new PapotageEvent(this, sujet, corps, theme);
        this.concierge.onPapotage(event);
    }

    @Override
    public void onPapotage(PapotageEvent event) {
        System.out.println(nom + " a reçu un message : [" + event.getSujet() + "] " + event.getCorps());
        
        Map<String, String> message = new HashMap<>();
        if (event.getSource() instanceof Bavard) { 
			message.put("auteur", ((Bavard) event.getSource()).getNom());
		} else {
			message.put("auteur", "");
		}
        message.put("sujet", event.getSujet());
        message.put("contenu", event.getCorps());
        message.put("theme", event.getTheme().toString());
        this.messagesRecus.add(message);
    }
    
    public void addTheme(ThemesEnum theme) {
    	this.themesSuivis.add(theme);
    }
    public void removeTheme(ThemesEnum theme) {
    	this.themesSuivis.remove(theme);
    }
}
