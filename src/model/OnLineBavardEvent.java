package model;

@SuppressWarnings("serial")
public class OnLineBavardEvent extends PapotageEvent {
    private Bavard bavard;

    public OnLineBavardEvent(Bavard bavard) {
    	super("", "Connexion", bavard.getNom() + " s'est connecté(e)", ThemesEnum.INFORMATION);
        this.bavard = bavard;
    }

    public Bavard getBavard() {
        return bavard;
    }
}