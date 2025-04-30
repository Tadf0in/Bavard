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
    
    // Setters
    public void setConcierge(Concierge concierge) {
    	this.concierge = concierge;
    	
    	for (PapotageListener listener : this.bavards) {
            this.concierge.addListener(listener);
        }
    }
    
    // Méthodes
    
    public Bavard creerBavard(String nom) {
        Bavard bavard = new Bavard(nom);
        this.bavards.add(bavard);
        bavard.seConnecter(this.concierge);
        return bavard;
    }
}
