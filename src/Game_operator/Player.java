package Game_operator;

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

    /**
     *
     * @return le nombre de du joueur qui appelle la classe
     */
    public int getNumberOfPoints() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfPoints;
    }

    /**
     * Permet de lier le joueur a une partie.
     * @param instance l'instance de la partie dans laquelle le joueur joue.
     */
    public void setGame(Game instance){
        this.game = instance; // On lie le joueur à une partie.
        // C'est une methode comme une autre on aurait pu aussi passer l'instance de la parrtie comme argument dans la fonction play du joueur
    }

    public Game getGame(){
        return this.game;
    }

    /**
     * Peermet de rajouter des points au joueur qui appelle la méthode
     * @param point le nombre de points qu'on veut lui ajouter. Lors de l'instanciation les points du joueur sont initialisés à 0.
     */
    public void addPoints(int point){
        this.numberOfPoints += point;
    }
 /*
    private void setNumberOfPoints(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numberOfPoints = value;
    }
*/

    /**
     *
     * @return le nom du joueur
     */
    public String getName() {
        // Automatically generated method. Please do not modify this code.
        return this.name;
    }

    /**
     * Permet d'enregistrer le nom du joueur
     * @param name le nom du joueur
     */
    public void setName(String name) {
        // Automatically generated method. Please do not modify this code.
        this.name = name;
    }

    /**
     * Permet de savoir si je joueur est accusé
     * @return true si oui sinon false
     */
    public boolean isAccused() {
        // Automatically generated method. Please do not modify this code.
        return this.accused;
    }

    /**
     * Permet d'enregistrer qu'un joueur est accusé, ou ne l'est plus.
     * @param value un boolean true s'il on veut l'accusé et false si on veut qu'il ne le soit plus/pas.
     */
    public void setAccused(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.accused = value;
    }

    /**
     * Permet d'avoir l'identité du joueur, et de savoir s'il a été découvert
     * @return l'instance de la classe Identity du joueur
     */
    public Identity getIdentity() {
        // Automatically generated method. Please do not modify this code.
        return this.identity;
    }

    /**
     * Permet d'enregistrer que l'identité d'un joueur a été révélée
     * Enregistré grâce à l'instance de la classe Identity du joueur.
     */
    public void revealIdentity (){
        identity.setRevealed(true);
    }

    /**
     * Permet au joueur de choisir le rôle, l'identité qu'il veut prendre.
     * Enregistré grâce à l'instance de la classe Identity du joueur.
     */
    public void chooseIdentity(){
        System.out.println("Witch role do you want to take ?\n1- Witch\n2-Hunt\n--> ");
        if (s.nextInt() == 1){
            identity.setRole(Role.Witch);
        }
        else {
            identity.setRole(Role.Hunt);
        }
    }

    public List<Action> getAction() { // a quoi elle sert ?
        // Automatically generated method. Please do not modify this code.
        return this.action;
    }

    public void setAction(List<Action> value) {// a quoi elle sert ?
        // Automatically generated method. Please do not modify this code.
        this.action = value;
    }

    /**
     * Permet de récupérer la main du joueur, contenant ses cartes
     * @return la variable deck, une collection d'instances de Card.
     */
    public List<Card> getDeck() {
        return deck;
    }

    /**
     * Permet de supprimer toutes les cartes de la main du joueur.
     */
     public void clearDeck(){
        deck.removeAll(deck);
     }

    /**
     * Permet d'ajouter une carte à la main du joueur
     * @param card l'instance de la carte que l'on veut ajouter
     */
    public void addCardToDeck (Card card){
        deck.add(card);
    }

    public void giveCard (){

    }
    public void playCard(){

    }

    /**
     * Fonction qui permet aux joueurs de jouer. Il y a plusieurs cas de figure pour jouer.
     * D'abord si le l'identité du joueur a été révélée et qu'il est une sorcière, il ne peut plus jouer.
     * Ensuite, si le joueur est accusé, il va pouvoir jouer, mais doit choisir entre révéler son identité ou jouer une carte,
     * seulement une carte action witch. La fonction permet le choix et appelle les fonctions permettant ces actions.
     * Enfin le dernier cas de figure, c'est le tour du joueur de jouer. Il peut donc soit joueur une carte, soit accuser quelqu'un.
     * La fonction permet le choix et appelle les fonctions permettant ces actions.
     */
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
