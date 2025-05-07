package model;

@SuppressWarnings("serial")
public class OnLineBavardEvent extends PapotageEvent {
    private Bavard bavard;

    public OnLineBavardEvent(Bavard bavard) {
    	super(bavard, "Connexion", bavard.getNom() + " s'est connecté(e)");
        this.bavard = bavard;
    }

    public Bavard getBavard() {
        return bavard;
    }
}