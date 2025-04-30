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

    public void Connecter(Bavard bavard) {
    	bavard.seConnecter(this.concierge);
    }
    
    public void Deconnecter(Bavard bavard) {
    	bavard.seDeconnecter();
    }
    
    public void ConnecterAll() {
    	for (Bavard bavard : bavards) {
    		Connecter(bavard);
    	}
    }
    
    public void DeconnecterAll() {
    	for (Bavard bavard : bavards) {
    		Deconnecter(bavard);
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
        Connecter(bavard); // Connécté par défaut
        
        this.bavards.add(bavard);
        return bavard;
    }
}
