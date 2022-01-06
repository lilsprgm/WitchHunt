package Game_operator;

import Cards.*;
//import org.jetbrains.annotations.NotNull;
import GraphicInterface.*;
import java.util.*;

//test commit

public class Game extends Observable {

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
    private volatile UpdateCode actualCode;


    private Game(){
        TerminalInterface myTerminalInterface = new TerminalInterface(this);
        PlayInterface myGame = new PlayInterface(this);
        SettingsInterface gameSettings = new SettingsInterface(this,myGame);
        LaunchInterface myLaunch = new LaunchInterface(gameSettings);
        gameSettings.setTerminal(myTerminalInterface);
        addObserver(myTerminalInterface);
        addObserver(gameSettings);
        addObserver(myGame);
    }

    private void setUpdateCode(UpdateCode newUpdateCode){
        this.actualCode = newUpdateCode;
        setChanged();
        notifyObservers(newUpdateCode);
    }
    public UpdateCode getUpdateCode(){
        return actualCode;
    }

    public void setNumberOfPlayer(int nbrPlayer){
        if(nbrPlayer > 6 || nbrPlayer<0){
            setUpdateCode(UpdateCode.ERROR_NUMBER);
        }
        else{
            this.numberOfPlayerIRL = nbrPlayer;
            if(nbrPlayer!=6){
                setUpdateCode(UpdateCode.INIT_NUMBER_BOT);
            }else setUpdateCode(UpdateCode.INIT_NAME_PLAYER);
        }
    }
    public void setNumberOfBot(int nbrBot){
        if(nbrBot+numberOfPlayerIRL > 6 || nbrBot+numberOfPlayerIRL<3){
            setUpdateCode(UpdateCode.ERROR_NUMBER);
        }
        else{
            this.numberOfBot = nbrBot;
            if(numberOfPlayerIRL==0){
                setUpdateCode(UpdateCode.INIT_DIFFICULTY_BOT);
            }else
                setUpdateCode(UpdateCode.INIT_NAME_PLAYER);
        }
    }

    public <T> void setPlayers(ArrayList<Player> clone){
        players.addAll(clone);
        if(numberOfBot>0){
            setUpdateCode(UpdateCode.INIT_DIFFICULTY_BOT);
        }else{
            setUpdateCode(UpdateCode.GAME_INIT_ROUND);
        }
    }
    public <T> void setBots(Integer difficulty){
        int indexBot = players.size()-numberOfPlayerIRL;
        if(difficulty>2 || difficulty<0){
            setUpdateCode(UpdateCode.ERROR_DIFFICULTY);
            return;
        }
        if(difficulty==1){
            players.add(new EasyModeBot());
        }
        if(difficulty==2){
            players.add(new HardModeBot());
        }
        players.get(players.size()-1).setName("Bot"+indexBot);
        players.get(players.size()-1).setGame(this);
        if(players.size()==numberOfPlayerIRL+numberOfBot){
            setUpdateCode(UpdateCode.GAME_INIT_ROUND);
        }
        else setUpdateCode(UpdateCode.INIT_DIFFICULTY_BOT);
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
        stockPile.add(AngryMob.getInstance());
        stockPile.add(BlackCat.getInstance());
        stockPile.add(EvilEye.getInstance());
        stockPile.add(Broomstick.getInstance());
        stockPile.add(Cauldron.getInstance());
        stockPile.add(DuckingStool.getInstance());
        stockPile.add(HookedNose.getInstance());
        stockPile.add(Toad.getInstance());
        stockPile.add(PointedHat.getInstance());
        stockPile.add(Wart.getInstance());
        stockPile.add(PetNewt.getInstance());
        stockPile.add(Inquisition.getInstance());
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
        setUpdateCode(UpdateCode.INIT_NUMBER_PLAYER);
    }

    /**
     * Fonction permettant de regrouper les fonctions pour débuter une partie.
     *
     * @author lilsb
     */
    public void init_Game(){
        this.initPlayers();
        this.initStockPile();
        while(actualCode!=UpdateCode.GAME_INIT_ROUND);
    }

    /**
     * Permet de débuter un nouveau round. Pour se faire, on supprime toutes les cartes des joueurs et on leur redistribue de noouvelles cartes.
     * Le nombre de cartes distribuées par personne est déterminé automatiquement pour que tous les joueurs aient le même nombre de cartes.
     *
     * @author lilsb
     */
    public void initNewRound (){
        if(actualCode==UpdateCode.GAME_INIT_ROUND){
            stockPile.removeAll(stockPile);
            initStockPile();
            for (Player player : players){
                player.clearDeck();
                player.chooseIdentity();
                player.getIdentity().setRevealed(false);
            }
            while (stockPile.size() - (numberOfPlayerIRL + numberOfBot) >= 0){
                for (Player player : players){
                    player.addCardTo(player.getDeck(), draw(stockPile));
                }
            }
            this.currentPlayer = players.get(0);
            setUpdateCode(UpdateCode.GAME_ROUND);
        }
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
        if(actualCode==UpdateCode.GAME_ROUND){
            while (!endOfRound()){
                this.currentPlayer.play();
                this.currentPlayer = chosenNextPlayer;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // System.out.println(chosenNextPlayer);
            }
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
            setUpdateCode(UpdateCode.GAME_END_ROUND);
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
     * Permet de déterminer si la partie est finie ou non. (Rappel fin de partie si tous les joueurs ont auu moins 5 points).
     * @return true si c'est la fin de la partie sinon false.
     *
     * @author lilsb
     */
    public boolean endOfGame(){
            for (Player allp : players){
                if (allp.getNumberOfPoints() >= 5){
                    restartPoint();
                    setUpdateCode(UpdateCode.END_GAME);
                    return true;
                }
            }
            if(actualCode!=UpdateCode.GAME_INIT_ROUND){
                setUpdateCode(UpdateCode.GAME_INIT_ROUND);
            }
            return false;

    }

    public void restartPoint(){
        for(Player allp : players){
            allp.restartPoint();
        }
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

    /**
     * Partie main du programme. On crée une partie on joue et on affiche le gagnant à la fin
     */
    public static void main(String[] arg){
        Game game = Game.getInstance();
        while(true){
            game.init_Game();
            while (!game.endOfGame()){
                game.initNewRound();
                game.roundManagement();
            }
            game.theWinnerIs();
        }
    }


}
