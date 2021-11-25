import Cards.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player extends Observable {
    private int numberOfPoints = 0;
    private String name;
    private boolean accused = false;
    private Identity identity =new Identity();
    private List<Action> action = new ArrayList<Action> (); // je sais pas a quuoi sert cette variable ?
    private List<Card> deck = new ArrayList<Card>();
    private Scanner s = new Scanner(System.in);
    private Game game;

    public String toString(){
        return name +" --> Score : " + numberOfPoints;
    }

    public int getNumberOfPoints() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfPoints;
    }

    public void setGame(Game instance){
        this.game = instance;
    }


    public void addPoints(int point){
        this.numberOfPoints += point;
    }
 /*
    private void setNumberOfPoints(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numberOfPoints = value;
    }
*/
    public String getName() {
        // Automatically generated method. Please do not modify this code.
        return this.name;
    }

    public void setName(String value) {
        // Automatically generated method. Please do not modify this code.
        this.name = value;
    }

    public boolean isAccused() {
        // Automatically generated method. Please do not modify this code.
        return this.accused;
    }

    public void setAccused(boolean value) {
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
    public void playCard(){
        print
    }

    public void accusation(){}

    public void play(){
        if (this.identity.isRevealed() & this.identity.getRole() == Role.Witch){
            System.out.println("You can't play : you are a witch !");
            return;
        }
        else if (this.isAccused()){
            System.out.println("You are accused !!!!\nWhat do you want to do ?\n1- Reveal your identity\n2- Play a card (only a Witch action");
            int choice = s.nextInt();
            switch (choice){
                case 1:
                    this.getIdentity().setRevealed(true);
                    break;
                case  2:
                    playCard();
                    break;
            }
        }
        else {
            System.out.println("What do you want to do ?\n1- Accuse someone \n2-Play a card");
            int choice = s.nextInt();
            switch (choice){
                case 1:
                    game.accusation(this);
                case  2:
                    playCard();
                    break;
            }

        }
    }
}
