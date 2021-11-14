import java.util.ArrayList;
import java.util.List;

public abstract class Joueur extends Observable {
    private String nbPoints;

    private String getNbPoints() {
        // Automatically generated method. Please do not modify this code.
        return this.nbPoints;
    }

    private void setNbPoints(String value) {
        // Automatically generated method. Please do not modify this code.
        this.nbPoints = value;
    }

    private String name;

    private String getName() {
        // Automatically generated method. Please do not modify this code.
        return this.name;
    }

    private void setName(String value) {
        // Automatically generated method. Please do not modify this code.
        this.name = value;
    }

    private boolean accusé;

    private boolean isAccusé() {
        // Automatically generated method. Please do not modify this code.
        return this.accusé;
    }

    private void setAccusé(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.accusé = value;
    }

    private Identité identité;

    public Identité getIdentité() {
        // Automatically generated method. Please do not modify this code.
        return this.identité;
    }

    public void setIdentité(Identité value) {
        // Automatically generated method. Please do not modify this code.
        this.identité = value;
    }

    private List<Action> action = new ArrayList<Action> ();

    public List<Action> getAction() {
        // Automatically generated method. Please do not modify this code.
        return this.action;
    }

    public void setAction(List<Action> value) {
        // Automatically generated method. Please do not modify this code.
        this.action = value;
    }

    public abstract void choisirIdentité();

    public abstract void jouer();

    public static void main(String[] args) {
        System.out.print("Ide");
    }
}
