package Game_operator;

import Cards.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player {

    protected int numberOfPoints = 0;
    protected String name;
    protected boolean accused = false;
    protected Identity identity =new Identity();
    protected ArrayList<Action> action = new ArrayList<Action> (); // je sais pas a quoi sert cette variable ?
    protected ArrayList<Card> deck = new ArrayList<Card>();
    protected ArrayList<Card> table = new ArrayList<Card>();//collection dans laquelle sont stocké les cartes deja jouées. (c'est comme si on les posait devant soit.



    protected Scanner s = new Scanner(System.in);
    protected Game game;

    public String toString(){
        return name +" --> Score : " + numberOfPoints;
    }

    public List<Card> getTable() {
        return table;
    }

    public void setTable(ArrayList<Card> table) {
        this.table = table;
    }
    /**
     *
     * @return le nombre de joueur qui appelle la classe
     */
    public int getNumberOfPoints() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfPoints;
    }

    /**
     * Permet de lier le joueur a une partie.
     * @param instance l'instance de la partie dans laquelle le joueur joue.
     */
    public void setGame(Game instance){ // Pas obligé il me semple que l'instance est retourné directement par getInstance
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
    public abstract void chooseIdentity();

    /**
     * Permet de récupérer la main du joueur, contenant ses cartes
     * @return la variable deck, une collection d'instances de Card.
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Permet de supprimer toutes les cartes de la main du joueur.
     */
     public void clearDeck(){
        deck.removeAll(deck);
     }

     public void discardCardFrom(List<Card> stock ,Card card){
         stock.remove(card);
         this.getGame().getDiscardedCard().add(card);
     }
    /**
     * Permet d'ajouter une carte dans un tas de carte(n'importe lequel). On mélange le tas à la fin.
     * @param stock la "pile de carte dans laquelle on veut ajouter une carte.
     * @param card l'instance de la carte que l'on veut ajouter
     */
    public void addCardTo(List<Card> stock , Card card){
        stock.add(card);
        this.getGame().shuffle(stock);
    }

    /**
     * Fonction qui permet aux joueurs de jouer. Il y a plusieurs cas de figure pour jouer.
     * D'abord si le l'identité du joueur a été révélée et qu'il est une sorcière, il ne peut plus jouer.
     * Ensuite, si le joueur est accusé, il va pouvoir jouer, mais doit choisir entre révéler son identité ou jouer une carte,
     * seulement une carte action witch. La fonction permet le choix et appelle les fonctions permettant ces actions.
     * Enfin le dernier cas de figure, c'est le tour du joueur de jouer. Il peut donc soit joueur une carte, soit accuser quelqu'un.
     * La fonction permet le choix et appelle les fonctions permettant ces actions.
     */
    public abstract void play();

    /**
     * Fonction quui permet de gérer la phase de jeu correspondant à une accusation.
     * Elle regroupe le choix du joueur accusé, l'action du joueur accusé, ainsi que l'attribution des points à la fin de l'accusation.
     * @author lilsb
     */
    public void accusation(){ // on transmet en parametre d'entré le joueur qui accuse
        System.out.println("Who do you want to accuse ?");
        Player accusedPlayer;
        do {
            accusedPlayer = chooseAPlayer();
        }while ((accusedPlayer == game.getProtectedPlayer()) || accusedPlayer == this);// pb si le joueur protégé est le seul à pouvoir être accusé // remettre en protected player pour que ca soit plus simple et que ca colle avec les cartes.
        // solution peut etre lorsque l'on set le playerprotected faire une verif
        accusedPlayer.setAccused(true);
        accusedPlayer.play();
        accusedPlayer.setAccused(false);
        game.setProtectedPlayer(null); // permet la réinitialistation de joueur protégé (car protégé un tour seulement
        if (accusedPlayer.getIdentity().isRevealed() & accusedPlayer.getIdentity().getRole() == Role.Witch){
            this.addPoints(1);
            System.out.println(this.getName() + " won 1 point");
        }

    }


    public void playCard() {
        System.out.println("Wich card do you want to play");
        Card cardToBePlayed = this.chooseCardIn(deck);
        if(accused && cardToBePlayed.conditionWitch(this)){
            cardToBePlayed.actionWitch(this);
            this.addCardTo(this.table,cardToBePlayed);
        }
        else if(!accused && cardToBePlayed.conditionHunt(this)){
            cardToBePlayed.actionHunt(this);
            this.addCardTo(this.table,cardToBePlayed);
        }
        this.getGame().chooseNextPlayer(this);
    }

    public abstract Player chooseAPlayer();

    public abstract Card chooseCardIn(List<Card> Stock);
}
