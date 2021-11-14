import java.util.ArrayList;
import java.util.List;

public class Partie extends Observable {
    private int nbJoueur;

    private int getNbJoueur() {
        // Automatically generated method. Please do not modify this code.
        return this.nbJoueur;
    }

    private void setNbJoueur(int value) {
        // Automatically generated method. Please do not modify this code.
        this.nbJoueur = value;
    }

    private Partie partie;

    private Partie getPartie() {
        // Automatically generated method. Please do not modify this code.
        return this.partie;
    }

    private void setPartie(Partie value) {
        // Automatically generated method. Please do not modify this code.
        this.partie = value;
    }

    private int nbBot;

    private int getNbBot() {
        // Automatically generated method. Please do not modify this code.
        return this.nbBot;
    }

    private void setNbBot(int value) {
        // Automatically generated method. Please do not modify this code.
        this.nbBot = value;
    }

    private Administrateur administrateur;

    public Administrateur getAdministrateur() {
        // Automatically generated method. Please do not modify this code.
        return this.administrateur;
    }

    public void setAdministrateur(Administrateur value) {
        // Automatically generated method. Please do not modify this code.
        this.administrateur = value;
    }

    private List<Joueur> joueur = new ArrayList<Joueur> ();

    public List<Joueur> getJoueur() {
        // Automatically generated method. Please do not modify this code.
        return this.joueur;
    }

    public void setJoueur(List<Joueur> value) {
        // Automatically generated method. Please do not modify this code.
        this.joueur = value;
    }

    private List<Carte> carte = new ArrayList<Carte> ();

    public List<Carte> getCarte() {
        // Automatically generated method. Please do not modify this code.
        return this.carte;
    }

    public void setCarte(List<Carte> value) {
        // Automatically generated method. Please do not modify this code.
        this.carte = value;
    }

    public void getInstance() {
    }

    public void partie() {
    }

    public void gestionPartie() {
    }

}
