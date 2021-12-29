package Game_operator;

import Cards.*;
//import org.jetbrains.annotations.NotNull;
import GraphicInterface.*;
import java.util.*;
import java.util.LinkedList;


public class Game extends Observable  implements Runnable {

    Scanner s = new Scanner(System.in);          // "s" Permet d'utiliser les méthodes de la classe Scanner.
    private int numberOfPlayerIRL=0;
    private int numberOfBot=0;
    
    private ArrayList<Player> players = new ArrayList<Player> ();     // Utiliser ArrayList pour les Joueurs
    private Player chosenNextPlayer = null;

    private Player protectedPlayer = null;

    private Player currentPlayer = null;

    private static List<Card> stockPile = new ArrayList<Card> (); // Utiliser les Liste QUEUE à la place
    private static List<Card> discardedCard = new ArrayList<Card> ();

    private static Game instance = null;
    private UpdateCode actualCode;


    private Game(){
        Thread t = new Thread(this);
        SettingsInterface gameSettings = new SettingsInterface(this);
        TerminalInterface myTerminalInterface = new TerminalInterface(this,gameSettings);
        LaunchInterface myLaunch = new LaunchInterface(gameSettings);
        addObserver(myTerminalInterface);
        addObserver(gameSettings);
        t.start();
    }

    private void setUpdateCode(UpdateCode newUpdateCode){
        this.actualCode = newUpdateCode;
        setChanged();
        notifyObservers(newUpdateCode);
    }

    public void setNumberOfUser(int nbrPlayer, int nbrBot){
        this.numberOfPlayerIRL = nbrPlayer;
        this.numberOfBot = nbrBot;
        setUpdateCode(UpdateCode.INIT_NAME_PLAYER);
    }

    public void setPlayers(ArrayList<Player> clone){
        players = clone;
        setUpdateCode(UpdateCode.INIT_DIFFICULTY_BOT);
    }

    public static Game getInstance() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    public static List<Card> getDiscardedCard() {
        return discardedCard;
    }

    public int getNumberOfPlayer() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfPlayerIRL;
    }

    public void setNumberOfPlayer(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numberOfPlayerIRL = value;
    }

    public Player getProtectedPlayer() {
        return protectedPlayer;
    }

    public void setProtectedPlayer(Player protectedPlayer) {
        this.protectedPlayer = protectedPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getNumberOfBot() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfBot;
    }

    public void setNumberOfBot(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numberOfBot = value;
    }

    public List<Player> getPlayers() {
        // Automatically generated method. Please do not modify this code.
        return this.players;
    }

    /*
    public void setPlayers(List<Player> value) {
        // Automatically generated method. Please do not modify this code.
        this.players = value;
    }
*/
    public List<Card> getCards() {
        // Automatically generated method. Please do not modify this code.
        return this.stockPile;
    }

    /**
     * intiStockPile est la fonction qui permet d'instancier les cartes action du jeu.
     * Les cartes sont crées puis stockées dans la collection de la classe game appelé "Stockpile".
     * Après la création du tas de cartes on les mélange ("suffle(stockPile);") pour pouvoir les distribuer directement ensuite.
     * La distribution ne s'effectue pas dans cette méthode.
     *
     * @author lilsb
     *
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
        shuffle(stockPile);
    }

    /**
     * Cette fonction permet de piocher aléatoirement une carte dans un tas
     * @param cards est la collection du tas de carte dans lequel on souhaite piocher.
     * @return on retourne la carte qui a été piochée.
     *
     * @author lilsb
     */
    public Card draw(/*@NotNull*/ List<Card> cards){ // pour piocher
        Card card = cards.get(0);// on prend toujours l'index 1 car dans tout les cas les cartes sont mélangés
        cards.remove(0);
        return card;
    }

    /**
     * Permet de melanger n'importe quelle collection de cartes.
     * @param cards correspond au tas de cartes que l'on souhaite mélanger.
     *
     * @author lilsb
     */
    public void shuffle(List<Card> cards){   // Pour être plus clair et rapide si on a besoin de melanger n'importe quoi par ex la main d'un joueur
        Collections.shuffle(cards);
    }

    /**
     *
     *
     */
    public void initPlayers(){
        //On créer une partie contenant le nombre de Joueurs et le nombre de Bot souhaité
        setUpdateCode(UpdateCode.INIT_NUMBER_PLAYER);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * Fonction permettant de regrouper les fonctions pour débuter une partie.
     *
     * @author lilsb
     */
    public void init_Game(){
        this.initPlayers();
        this.initStockPile();
    }

    /**
     * Permet de débuter un nouveau round. Pour se faire, on supprime toutes les cartes des joueurs et on leur redistribue de noouvelles cartes.
     * Le nombre de cartes distribuées par personne est déterminé automatiquement pour que tous les joueurs aient le même nombre de cartes.
     *
     * @author lilsb
     */
    public void initNewRound (){
        stockPile.removeAll(stockPile);
        initStockPile();
        for (Player player : players){
            player.clearDeck();
            player.getIdentity().setRevealed(false);
        }
        while (stockPile.size() - (numberOfPlayerIRL + numberOfBot) >= 0){
            for (Player player : players){
                player.addCardTo(player.getDeck(), draw(stockPile));
            }
        }
        for (Player player : players){
            System.out.println(player.getName() + " -->");
            player.chooseIdentity();
            player.getIdentity().setRevealed(false); // je croit qu'il faut remettre les roles en place
        }
    }

    /**
     * Permet de déterminer si le round est terminé (rappel round terminé si l'identité de tous les joueurs a été révélée sauf un).
     * Cette fonction gère aussi l'attributiion des points à la fin du round, pour le joueur qui n'a pas été découvert.
     * @return true si c'est la fin du round, false si ca ne l'est pas.
     *
     * @author lilsb
     */
    public boolean endOfRound(){ // fonction pour déterminer si le round est termiiné ou pas
        int numberOfRevealed = 0;
        for (Player player : players){
            if (player.getIdentity().isRevealed()) {
                numberOfRevealed++;
            }
        }
        if (numberOfRevealed == players.size() - 1){
            System.out.println("End of the round !!");

            // partie pour compter les points si la personne n'est pas decouverte
            for (Player player :players){
                if (!player.getIdentity().isRevealed()){
                    if (player.getIdentity().getRole() == Role.Witch){
                        player.addPoints(2);
                    }
                    else{
                        player.addPoints(1);
                    }
                }
            }
            //
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Permet de récupérer le joueurs suivant à joueur, qui n'est pas celui jouant naturellement apres dans l'ordre de jeu.
     * Ce joueur est stocké dans une variable et on retourne ce joueur.
     * @return le joueur qui doit joueur ensuite.
     *
     * @author lilsb
     */
    public Player getChosenNextPLayer (){
        return chosenNextPlayer;
    }

    /**
     * Permet de stocker le nom du joueur suivant désigné.
     * @param player le joueur qui a été désigné pour jouer en suivant
     *
     * @author lilsb
     */
    public void chooseNextPlayer(Player player){
        chosenNextPlayer = player;
    }

    /**
     * Permet la gestion d'un round complet. Tant que le round n'est pas fini cette fonction permet de gérer l'ordre de jeu ses joueurs.
     * Il y a deux cas possible : soit on suit l'orde "naturel" de jeu des joueurs,
     * soit le joueurs suivant a été désigné par l'action d'une carte d'un joueurs.
     * Quand on arrive au dernier joueurs à jouer, on refait jouer le premier.
     *
     * @author lilsb
     */
    public void roundManagement(){ // pour gerer à qui cest le tour de jouer jsp sii cest tres otpi ?
        this.currentPlayer = players.get(0);
        while (!endOfRound()){
            System.out.println("Your turn "+ this.currentPlayer.getName());
            this.currentPlayer.play();
            this.currentPlayer = chosenNextPlayer;
            System.out.println(chosenNextPlayer);
        }
    }

    /**
     * Permet de déterminer si la partie est finie ou non. (Rappel fin de partie si tous les joueurs ont auu moins 5 points).
     * @return true si c'est la fin de la partie sinon false.
     *
     * @author lilsb
     */
    public boolean endOfGame(){
        for (Player player : players){
            if (player.getNumberOfPoints() < 5){
                return false;
            }
        }
        System.out.println("End of the game");
        return true;
    }

    /**
     * Permet d'afficher le nom du ou des gagnants de la partie.
     * On regarde quel est/sont les joueurs ayant le plus de points. On les affiche ensuite.
     *
     * @author lilsb
     */
    public void theWinnerIs(){ // fonction permettant d'afficher les gagnants
        List<Player> winner = new ArrayList<Player>();
        winner.add(players.get(0));
        for (Player player : players){
            if (player.getNumberOfPoints() > winner.get(0).getNumberOfPoints()){
                winner.removeAll(winner);
                winner.add(player);
            }
            else if (player.getNumberOfPoints() == winner.get(0).getNumberOfPoints()){
                winner.add(player);
            }
        }
        System.out.println("----- WINNER -----");
        for (Player player : winner){
            System.out.println(player);
        }

    }
    public void round(){
        do{

        }while(true);
    }

    public void game(){
    }


    @Override
    public void run() {
        while(true){
            this.init_Game();
            while (!this.endOfGame()){
                this.initNewRound();
                this.roundManagement();
            }
            this.theWinnerIs();
        }
    }

    /**
     * Partie main du programme. On crée une partie on joue et on affiche le gagnant à la fin
     */
    public static void main(String[] arg){
        Game game = Game.getInstance();
    }


}
