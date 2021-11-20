import Cards.Action;
import Cards.Identity;

import java.util.ArrayList;
import java.util.List;

public class Player extends Observable {
    private String numberOfPoints;
    private String name;
    private boolean accused;
    private Identity identity;
    private List<Action> action = new ArrayList<Action> ();

    private String getNumberOfPoints() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfPoints;
    }

    private void setNumberOfPoints(String value) {
        // Automatically generated method. Please do not modify this code.
        this.numberOfPoints = value;
    }

    public String getName() {
        // Automatically generated method. Please do not modify this code.
        return this.name;
    }

    public void setName(String value) {
        // Automatically generated method. Please do not modify this code.
        this.name = value;
    }

    private boolean isAccused() {
        // Automatically generated method. Please do not modify this code.
        return this.accused;
    }

    private void setAccused(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.accused = value;
    }

    public Identity getIdentity() {
        // Automatically generated method. Please do not modify this code.
        return this.identity;
    }

    public void setIdentity(Identity value) {
        // Automatically generated method. Please do not modify this code.
        this.identity = value;
    }

    public List<Action> getAction() {
        // Automatically generated method. Please do not modify this code.
        return this.action;
    }

    public void setAction(List<Action> value) {
        // Automatically generated method. Please do not modify this code.
        this.action = value;
    }

    public void chooseIdentity(){}

    public void play(){}

    public static void main(String[] args) {
        System.out.print("test");
    }
}
