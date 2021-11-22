import Cards.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Game extends Observable {

    Scanner s = new Scanner(System.in);          // "s" Permet d'utiliser les méthodes de la classe Scanner.
    private int numberOfPlayer=0;
    private int numberOfBot;
    private Administrator admnistrator;

    private List<Player> players = new ArrayList<Player> (6);
    private static List<Card> stockPile = new ArrayList<Card> (); //stock pile = pioche

    private static Game instance = null;


    private Game(){
    }

    public static Game getInstance() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    public int getNumberOfPlayer() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfPlayer;
    }

    public void setNumberOfPlayer(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numberOfPlayer = value;
    }

    /* private Game game;

    private Game getGame() {
        // Automatically generated method. Please do not modify this code.
        return this.game;
    }                           Je sais pas trop ca sert à quoi comme méthode ????

    private void setGame(Game value) {
        // Automatically generated method. Please do not modify this code.
        this.game = value;
    }*/

    public int getNumberOfBot() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfBot;
    }

    public void setNumberOfBot(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numberOfBot = value;
    }

    public Administrator getAdmnistrator() {
        // Automatically generated method. Please do not modify this code.
        return this.admnistrator;
    }

    public void setAdmnistrator(Administrator value) {
        // Automatically generated method. Please do not modify this code.
        this.admnistrator = value;
    }

    public List<Player> getPlayers() {
        // Automatically generated method. Please do not modify this code.
        return this.players;
    }

    public void setPlayers(List<Player> value) {
        // Automatically generated method. Please do not modify this code.
        this.players = value;
    }

    public List<Card> getCards() {
        // Automatically generated method. Please do not modify this code.
        return this.stockPile;
    }
    /*
    public void setCards(List<Card> value) {
        // Automatically generated method. Please do not modify this code.
        this.cards = value;
    }
*/
    public void initStockPile(){ //Initialisation de la pioche
        stockPile.add(new AngryMob());
        stockPile.add(new BlackCat());
        stockPile.add(new EvilEye());
        stockPile.add(new Broomstick());
        stockPile.add(new Cauldron());
        stockPile.add(new DuckingStool());
        stockPile.add(new HookedNose());
        stockPile.add(new Toad());
        stockPile.add(new PointedHat());
        stockPile.add(new Wart());
        stockPile.add(new PetNewt());
        stockPile.add(new Inquisition());
        suffle(stockPile);
    }

    public Card draw(@NotNull List<Card> cards){
        Card card = cards.get(0);// on prend toujours l'index 1 car dans tout les cas les cartes sont mélangés
        cards.remove(0);
        return card;

    }
    public void suffle(List<Card> cards){   // Pour être plus clair et rapide si on a besoin de melanger n'importe quoi par ex la main d'un joueur
        Collections.shuffle(cards);
    }

    public String toString(){ // A quoi sert cette méthode ?
        return (""+this.numberOfPlayer);
    }

    public void init_Game(){
        int nombreJoueur=0;
        int nombreBot=0;
        Player player_temp = new Player();
        Scanner s = new Scanner(System.in);          // "s" Permet d'utiliser les méthodes de la classe Scanner.


        //On créer une partie contenant le nombre de Joueurs et le nombre de Bot souhaité
        do {
            System.out.print("Veuillez entrer le nombre de Joueur dans la Partie (6 joueurs max) : ");
            nombreJoueur = s.nextInt();
        }while(nombreJoueur > 6);
        numberOfPlayer=nombreJoueur;
        if(nombreJoueur<6) {
            do {
                System.out.print("Veuillez entrer le nombre de Bot dans la Partie : ");
                nombreBot = s.nextInt();
            } while (nombreBot > 6 - nombreJoueur);
            numberOfBot=nombreBot;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        s.nextLine();                   // Je pense qu'il faut supprimer le ENTR précédent
        for(int i=1;i<nombreJoueur+1;i++){
            System.out.print("Entrez le nom du Joueur n°"+i+" :");
            players.add(i-1,new Player());
            player_temp = players.get(i-1);
            player_temp.setName(s.nextLine());
        }
        for(Iterator<Player> p = players.iterator(); p.hasNext();){
            player_temp = p.next();
            System.out.println(player_temp.getName());
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public void game(){
    }

    public void gameManagement() {
    }
    public static void main(String[] args){
        Game game = Game.getInstance();
        game.init_Game();



    }

}
