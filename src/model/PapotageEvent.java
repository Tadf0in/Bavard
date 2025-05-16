package model;

import java.util.EventObject;

@SuppressWarnings("serial")
public class PapotageEvent extends EventObject {
	// Attributs
	private String sujet;
    private String corps;
    private ThemesEnum theme;

    // Constructeur
    public PapotageEvent(Object source, String sujet, String corps, ThemesEnum theme) {
        super(source);
        this.sujet = sujet;
        this.corps = corps;
        this.theme = theme;
    }
    
    // Getters
    public String getSujet() {
        return sujet;
    }
    public String getCorps() {
        return corps;
    }
    public ThemesEnum getTheme() {
    	return theme;
    }
}
