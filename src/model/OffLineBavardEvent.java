package model;

@SuppressWarnings("serial")
public class OffLineBavardEvent extends PapotageEvent {
    private Bavard bavard;

    public OffLineBavardEvent(Bavard bavard) {
    	super("", "Déconnexion", bavard.getNom() + " s'est déconnecté(e)", ThemesEnum.INFORMATION);
        this.bavard = bavard;
    }

    public Bavard getBavard() {
        return bavard;
    }
}