package model;

public class Bavard implements PapotageListener {
    // Attributs
	private String nom;
    private Concierge concierge;

    // Constructeur
    public Bavard(String nom) {
        this.nom = nom;
    }
    
    // Getters
    public String getNom() {
        return nom;
    }

    // Méthodes
    
    public void seConnecter(Concierge concierge) {
        this.concierge = concierge;     	
    	concierge.addListener(this);
    }
    
    public void seDeconnecter() {
    	this.concierge.removeListener(this);
    	this.concierge = null;
    }
    
    public boolean isConnected() {
    	return this.concierge != null;
    }

    public void envoyerMessage(String sujet, String corps) {
        PapotageEvent event = new PapotageEvent(this, sujet, corps);
        this.concierge.receivePapotage(event);
    }

    @Override
    public void onPapotage(PapotageEvent event) {
        System.out.println(nom + " a reçu un message : [" + event.getSujet() + "] " + event.getCorps());
    }
}
