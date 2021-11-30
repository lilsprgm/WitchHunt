package Game_operator;

import Cards.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.LinkedList;

public class Game extends Observable {

    Scanner s = new Scanner(System.in);          // "s" Permet d'utiliser les méthodes de la classe Scanner.
    private int numberOfPlayer=0;
    private int numberOfBot;
    private Administrator admnistrator;
    
    private LinkedList<Player> players = new LinkedList<Player> ();
    private Player chosenNextPlayer = null;
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
    public Card draw(@NotNull List<Card> cards){ // pour piocher
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
    public void initPlayers(){ // A faire : permettre de modifier la difficulté des bot
        int nombreJoueur=0;
        int nombreBot=0;
        Player player_temp = new Player();

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
            player_temp.setGame(instance);
        }
        for(Iterator<Player> p = players.iterator(); p.hasNext();){
            player_temp = p.next();
            System.out.println(player_temp.getName());
        }

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
        }
        while (stockPile.size() - (numberOfPlayer + numberOfBot) >= 0){
            for (Player player : players){
                player.addCardTo(player.getDeck(), draw(stockPile));
            }
        }
        for (Player player : players){
            System.out.println(player.getName() + " -->");
            player.chooseIdentity();
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
     * Permet de choisir le nom du joueur suivant.
     * On affiche le nom de tous les joueurs pouvant être choisi puis l'utilisateur rentre le nom du joueur qu'il veut choisir.
     * @param exception le joueur qui ne pourra pas être désigné comme étant le joueur suivant.
     * (Parce que c'est lui qui appelle la fonction par exemeple donc il ne pourra pas se désigner lui même).
     * @return le joueur choisi.
     *
     * @author lilsb
     */
    public Player chooseAPlayer(Player exception){
        for (Player player : players){
            if (player == exception){
                continue;
            }
            System.out.println(player);
        }
        String nameOfChosenPlayer = s.nextLine(); // attention a revoir car exception si on se trompe lorsque l'on tape le nom.
        // regarder avec la fonction scanner .next(pattern))
        for (Player player : players){
            if (player.getName() == nameOfChosenPlayer){
                return player;
            }
        }
    }

    /**
     * Fonction quui permet de gérer la phase de jeu correspondant à une accusation.
     * Elle regroupe le choix du joueur accusé, l'action du joueur accusé, ainsi que l'attribution des points à la fin de l'accusation.
     * @param accusator le joueur qui accuse. Ce paramettre permet d'attribuer les points à celui-ci à la fin du tour.
     *
     * @author lilsb
     */
    public void accusation(Player accusator){ // on transmet en parametre d'entré le joueur qui accuse
        System.out.println("Who do you want to accuse ?");
        Player accusedPlayer = chooseAPlayer(accusator);
        accusedPlayer.setAccused(true);
        accusedPlayer.play();
        accusedPlayer.setAccused(false);
        if (accusedPlayer.getIdentity().isRevealed() & accusedPlayer.getIdentity().getRole() == Role.Witch){
            accusator.addPoints(1);
            System.out.println(accusator.getName() + " won 1 point");
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
        int index = 0;
        Player currentPLayer = players.get(index);
        while (!endOfRound()){
            System.out.println("Your turn "+ currentPLayer.getName());
           currentPLayer.play();
            if (chosenNextPlayer != null){
               currentPLayer = chosenNextPlayer;
               chooseNextPlayer(null);
               index = players.indexOf(currentPLayer);
               continue;
            }
            else if (currentPLayer == players.getLast()){
                currentPLayer = players.getFirst();
                index = 0;
                continue;
            }
            else {
                index++;
                currentPLayer = players.get(index);
            }
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
        winner.add(players.getFirst());
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

    public void gameManagement() {
    }

    /**
     * Partie main du programme. On crée une partie on joue et on affiche le gagnant à la fin
     * @param args
     */
    public static void main(String[] args){
        Game game = Game.getInstance();
        game.init_Game();
        while (!game.endOfGame()){
            game.initNewRound();
            game.roundManagement();
        }
        game.theWinnerIs();
    }

}
