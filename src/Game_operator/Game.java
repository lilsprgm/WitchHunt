package Game_operator;

import Cards.*;
//import org.jetbrains.annotations.NotNull;
import GraphicInterface.*;
import java.util.*;

/**
 * Classe principale permettant d'integrer toutes les fonctionnalites du Jeu de carte WitchHunt
 * @version 1.3
 * @author Lilsb et AGOUGILE
 */

public class Game extends Observable {

    /**
     * Nombre de Joueur Reel.
     */
    private int numberOfPlayerIRL=0;
    /**
     * Nombre de Bot.
     */
    private int numberOfBot=0;
    /**
     * Liste des Joueurs dans la partie.
     */
    private final ArrayList<Player> players = new ArrayList<Player> ();
    /**
     * Prochain Joueur a joue.
     */
    private Player chosenNextPlayer = null;
    /**
     * Joueur Protege.
     */
    private Player protectedPlayer = null;
    /**
     * Joueur actuellement en train de jouer.
     */
    private Player currentPlayer = null;
    /**
     * Liste des cartes dans la pile.
     */
    private static final List<Card> stockPile = new ArrayList<Card> ();
    /**
     * Liste des cartes dans la defausse.
     */
    private static final List<Card> discardedCard = new ArrayList<Card> ();
    /**
     * Instance unique de la partie.
     */
    private static Game instance = null;
    /**
     * Etape de la partie.
     */
    private volatile UpdateCode actualCode;


    /**
     * Constructeur du jeu integrant le patron Singleton.
     * Ajout des vues observatrices dans le constructeur.
     * @author Lilsb et AGOUGILE
     */
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

    /**
     * Methode qui permet d'instaurer le patron de conception Singleton.
     * @return Le jeu cree ou en cours.
     * @author Lilsb et AGOUGILE
     */
    public static Game getInstance() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    /**
     * Methode qui permet de changer l'etape actuelle du jeu.
     * et de notifier tous les observateurs.
     * @param newUpdateCode Le nouveau code a set.
     * @author Lilsb et AGOUGILE
     */
    private void setUpdateCode(UpdateCode newUpdateCode){
        this.actualCode = newUpdateCode;
        setChanged();
        notifyObservers(newUpdateCode);
    }

    /**
     * Methode qui permet de reçevoir l'etape actuelle du jeu sous forme d'une enumeration "UpdateCode"
     * @return Le code actuel.
     * @author Lilsb et AGOUGILE
     */
    public UpdateCode getUpdateCode(){
        return actualCode;
    }

    /**
     * Methode qui permet de modifier le nombre de joueur reel avant de passer a l'etape suivante.
     * @param nbrPlayer  Le nombre de Joueur reel souhaite.
     * @author Lilsb et AGOUGILE
     */
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

    /**
     * Methode qui permet de modifier le nombre de Bot avant de passer a l'etape suivante.
     * @param nbrBot  Le nombre de Bot souhaite.
     * @author Lilsb et AGOUGILE
     */
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

    /**
     * Methode qui permet de modifier le nom des Joueurs reels avant de passer a l'etape suivante.
     * @param clone  La liste des Joueurs reel a clone.
     * @author Lilsb et AGOUGILE
     */
    public void setPlayers(ArrayList<Player> clone){
        players.addAll(clone);
        if(numberOfBot>0){
            setUpdateCode(UpdateCode.INIT_DIFFICULTY_BOT);
        }else{
            setUpdateCode(UpdateCode.GAME_INIT_ROUND);
        }
    }

    /**
     * Methode qui permet de modifier la difficulte des Bots et d'initialiser leurs noms avant de passer a l'etapes suivante.
     * @param difficulty  La difficulte choisie entre :
     *                    1 = EasyModeBot;
     *                    2 = HardModeBot;
     * @author Lilsb et AGOUGILE
     */
    public void setBots(Integer difficulty){
        int indexBot = players.size()-numberOfPlayerIRL;
        if(difficulty>2 || difficulty<0){
            setUpdateCode(UpdateCode.ERROR_DIFFICULTY);
            return;
        }
        if(difficulty==1){
            players.add(new EasyModeBot());
            players.get(players.size()-1).setName("Bot"+indexBot);
            players.get(players.size()-1).setGame(this);
        }
        if(difficulty==2){
            players.add(new HardModeBot());
            players.get(players.size()-1).setName("Bot"+indexBot);
            players.get(players.size()-1).setGame(this);
        }
        if(players.size()==numberOfPlayerIRL+numberOfBot){
            setUpdateCode(UpdateCode.GAME_INIT_ROUND);
        }
        else setUpdateCode(UpdateCode.INIT_DIFFICULTY_BOT);
    }

    /**
     * Methode qui permet de passer a l'etape d'initialisation des Joueurs.
     * @author Lilsb et AGOUGILE
     */
    public void initPlayers(){
        setUpdateCode(UpdateCode.INIT_NUMBER_PLAYER);
    }

    /**
     * Methode qui permet d'instancier les cartes actions du jeu en les melangeant dans "stockPile".
     * @author Lilsb et AGOUGILE
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
     * Methode qui permet de melanger n'importe quelle collection de cartes.
     * @param cards Correspond au tas de cartes que l'on souhaite melanger.
     * @author Lilsb et AGOUGILE
     */
    public void shuffle(List<Card> cards){   // Pour être plus clair et rapide si on a besoin de melanger n'importe quoi par ex la main d'un joueur
        Collections.shuffle(cards);
    }

    /**
     * Methode permettant d'initialiser une partie puis bloque tant que la partie n'a pas commence.
     * @author Lilsb et AGOUGILE
     */
    public void init_Game(){
        this.initPlayers();
        this.initStockPile();
        while(actualCode!=UpdateCode.GAME_INIT_ROUND);
    }

    /**
     * Methode permettant d'initialiser un nouveau Round.
     * Redistribue les cartes entre tous les Joueurs, reinitialise les identites.
     * Passe ensuite a l'etape suivante.
     * @author Lilsb et AGOUGILE
     */
    public void initNewRound (){
        if(actualCode==UpdateCode.GAME_INIT_ROUND){
            stockPile.removeAll(stockPile);
            initStockPile();
            for (Player player : players){
                player.clearDeck();
                player.chooseIdentity();
                player.getIdentity().setRevealed(false);
                player.setAccused(false);
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
     * Methode permettant de gerer les rounds des Joueurs.
     * Attente de 1 seconde entre chaque tours.
     * @author Lilsb et AGOUGILE
     */
    public void roundManagement(){
        if(actualCode==UpdateCode.GAME_ROUND){
            while (!endOfRound()){
                this.currentPlayer.play();
                this.currentPlayer = chosenNextPlayer;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Methode permettant de verifier la fin d'un round et de gerer les points en fin de Round pour enfin passer a l'etape suivante.
     * True si Identites relevees pour tous les Joueurs sauf 1.
     * @return true si c'est la fin du round.
     * @author Lilsb et AGOUGILE
     */
    public boolean endOfRound(){
        int numberOfRevealed = 0;
        for (Player player : players){
            if (player.getIdentity().isRevealed()) {
                numberOfRevealed++;
            }
        }
        if (numberOfRevealed == players.size() - 1){
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
     * Methode permettant de verifier si la partie est finie ou non et passer a l'etape suivante.
     * Fin de partie si au moins 1 Joueur a 5 points minimum.
     * @return true si la partie est finie.
     * @author Lilsb et AGOUGILE
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

    /**
     * Methode permettant de construire le podium en fin de Jeu.
     * @author Lilsb et AGOUGILE
     */
    public void theWinnerIs(){
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

    /**
     * Methode permettant de piocher aleatoirement dans un tas de carte donne.
     * @param cards La Liste du tas de carte dans lequel on souhaite piocher.
     * @return La carte qui a ete piochee.
     * @author Lilsb et AGOUGILE
     */
    public Card draw(List<Card> cards){ // pour piocher
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
    /**
     * Methode permettant de reinitialiser les points de tous les Joueurs.
     * @author Lilsb et AGOUGILE
     */
    public void restartPoint(){
        for(Player allp : players){
            allp.restartPoint();
        }
    }







    /**
     * Getter du nombre de Joueur reel.
     * @return Le nombre de Joueur reel.
     * @author Lilsb et AGOUGILE
     */
    public int getNumberOfPlayer() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfPlayerIRL;
    }
    /**
     * Getter du nombre de bot
     * @return Le nombre de bot.
     * @author Lilsb et AGOUGILE
     */
    public int getNumberOfBot() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfBot;
    }
    /**
     * Getter des Joueurs composant le Jeu.
     * @return La liste des Jouers composant le Jeu.
     * @author Lilsb et AGOUGILE
     */
    public List<Player> getPlayers() {
        // Automatically generated method. Please do not modify this code.
        return this.players;
    }
    /**
     * Getter du Joueur en train de jouer.
     * @return Le Joueur actuellement en train de jouer.
     * @author Lilsb et AGOUGILE
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    /**
     * Getter du Joueur qui doit jouer le prochain tour.
     * @return le Joueur qui doit joueur le prochain tour.
     * @author Lilsb et AGOUGILE
     */
    public Player getChosenNextPLayer (){
        return chosenNextPlayer;
    }
    /**
     * Getter du Joueur protege.
     * @return Le Joueur protege.
     * @author Lilsb et AGOUGILE
     */
    public Player getProtectedPlayer() {
        return protectedPlayer;
    }
    /**
     * Getter des Cartes en defausse.
     * @return La liste des Cartes en defausse.
     * @author Lilsb et AGOUGILE
     */
    public static List<Card> getDiscardedCard() {
        return discardedCard;
    }
    /**
     * Getter de la pile de carte.
     * @return La liste des carte dans la pile.
     * @author Lilsb et AGOUGILE
     */
    public List<Card> getCards() {
        // Automatically generated method. Please do not modify this code.
        return stockPile;
    }
    /**
     * Setter du Joueur Protege.
     * @param protectedPlayer Le Joueur qui doit être protege.
     * @author Lilsb et AGOUGILE
     */
    public void setProtectedPlayer(Player protectedPlayer) {
        this.protectedPlayer = protectedPlayer;
    }
    /**
     * Setter du prochain Joueur.
     * @param player Le joueur qui doit jouer au prochain tour.
     * @author Lilsb et AGOUGILE
     */
    public void chooseNextPlayer(Player player){
        chosenNextPlayer = player;
    }

    /**
     * Partie main du programme.
     * Fonctionnement d'une partie de jeu classique.
     * @author Lilsb et AGOUGILE
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


