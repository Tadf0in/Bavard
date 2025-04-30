package model;

import java.util.EventObject;

@SuppressWarnings("serial")
public class PapotageEvent extends EventObject {
	// Attributs
	private String sujet;
    private String corps;

    // Constructeur
    public PapotageEvent(Object source, String sujet, String corps) {
        super(source);
        this.sujet = sujet;
        this.corps = corps;
    }
    
    // Getters
    public String getSujet() {
        return sujet;
    }
    public String getCorps() {
        return corps;
    }
}
