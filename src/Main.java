import model.Batiment;
import model.Bavard;
import model.Concierge;

public class Main {
    public static void main(String[] args) {
        Batiment batiment = new Batiment();
        
        Concierge serge = new Concierge("Serge le concierge");
        batiment.setConcierge(serge);
        
        Bavard alice = batiment.creerBavard("Alice");
        Bavard bob = batiment.creerBavard("Bob");
        Bavard clara = batiment.creerBavard("Clara");

        alice.envoyerMessage("Salut", "Hello tout le monde !");
        bob.envoyerMessage("Réponse", "Salut Alice !");
    }
}