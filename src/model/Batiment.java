package model;

import java.util.ArrayList;
import java.util.List;

public class Batiment {
	// Attributs
    private Concierge concierge;
    private List<Bavard> bavards;

    // Constructeur
    public Batiment() {
        this.bavards = new ArrayList<Bavard>();
    }

    // Getters
    public Concierge getConcierge() {
        return concierge;
    }
    public List<Bavard> getBavards() {
        return bavards;
    }
    
    // Méthodes

    public void connecter(Bavard bavard) {
    	bavard.seConnecter(this.concierge);
    }
    
    public void deconnecter(Bavard bavard) {
    	bavard.seDeconnecter();
    }
    
    public void connecterAll() {
    	for (Bavard bavard : bavards) {
    		connecter(bavard);
    	}
    }
    
    public void deconnecterAll() {
    	for (Bavard bavard : bavards) {
    		deconnecter(bavard);
    	}
    }
    
    public Concierge creerConcierge(String nom) {
    	Concierge concierge = new Concierge(nom);
    	
    	for (PapotageListener listener : this.bavards) {
            concierge.addListener(listener);
        }
    	
    	this.concierge = concierge;
    	return concierge;
    }
    
    public Bavard creerBavard(String nom) {
        Bavard bavard = new Bavard(nom);
        connecter(bavard); // Connécté par défaut
        
        this.bavards.add(bavard);
        return bavard;
    }
}
