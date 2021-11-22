import Cards.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player extends Observable {
    private String numberOfPoints;
    private String name;
    private boolean accused;
    private Identity identity =new Identity();
    private List<Action> action = new ArrayList<Action> (); // je sais pas a quuoi sert cette variable ?
    private List<Card> deck = new ArrayList<Card>();
    private Scanner s = new Scanner(System.in);


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

    public void revealIdentity (){
        identity.setRevealed(true);
    }

    public void chooseIdentity(){
        System.out.println("Witch role do you want to take ?\n1- Witch\n2-Hunt\n--> ");
        if (s.nextInt() == 1){
            identity.setRole(Role.Witch);
        }
        else {
            identity.setRole(Role.Hunt);
        }
    }
/*
    public void setIdentity(Identity value) {
        // Automatically generated method. Please do not modify this code. // cette methode ne sert a rien ?
        this.identity = value;
    }
*/
    public List<Action> getAction() {
        // Automatically generated method. Please do not modify this code.
        return this.action;
    }

    public void setAction(List<Action> value) {
        // Automatically generated method. Please do not modify this code.
        this.action = value;
    }

    public List<Card> getDeck() {
        return deck;
    }
     public void clearDeck(){
        deck.removeAll(deck);
     }

    public void addCardToDeck (Card card){
        deck.add(card);
    }

    public void giveCard (){

    }

    public void play(){}

    public static void main(String[] args) {

    }
}
