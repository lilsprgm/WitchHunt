package Game_operator;

import Cards.*;

import java.util.*;

/**
 * Classe representant un Joueur lambda dans une partie.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public abstract class Player extends Observable {

    /**
     * Nombre de point du Joueur.
     */
    protected int numberOfPoints = 0;
    /**
     * Nom du Joueur.
     */
    protected String name;
    /**
     * Le Joueur est accuse ou non.
     */
    protected boolean accused = false;
    /**
     * Le Joueur choisi lors de l'accusation.
     */
    protected Player AccusedPlayer;
    /**
     * La carte a joue.
     */
    protected Card cardWichIsPlayed = null;
    /**
     * L'identite du Joueur.
     */
    protected Identity identity =new Identity();
    /**
     * La main du Joueur.
     */
    protected ArrayList<Card> deck = new ArrayList<>();
    /**
     * La table du Joueur (cartes deja joues).
     */
    protected ArrayList<Card> table = new ArrayList<>();
    protected Scanner s = new Scanner(System.in);
    /**
     * La partie dans laquelle joue le Joueur.
     */
    protected Game game;
    /**
     * L'etape de Jeu du Joueur.
     */
    protected volatile UpdateCode actualCode;

    /**
     * Methode permettant d'affiche le nom et le score du Joueur.
     * @return Le nom et le score du Joueur.
     * @author Lilsb et AGOUGILE
     */
    public String toString(){
        return name +" --> Score : " + numberOfPoints;
    }

    /**
     * Methode permettant d'ajouter un observateur.
     * @param vue L'observateur a ajouter dans les observateurs.
     * @author Lilsb et AGOUGILE
     */
    public <T> void setVue( T vue){
        addObserver((Observer) vue);
    }
    /**
     * Methode permettant d'ajouter deux observateurs.
     * @param vue L'observateur a ajouter dans les observateurs.
     * @param vue2  Le second Observateur à ajouter dans les observateurs.
     * @author Lilsb et AGOUGILE
     */
    public <T> void setVue( T vue,T vue2){
        addObserver((Observer) vue);
        addObserver((Observer)vue2);
    }
    /**
     * Methode qui permet de changer l'etape actuelle du Joueur.
     * et de notifier tous les observateurs.
     * @param newUpdateCode Le nouveau code à set.
     * @author Lilsb et AGOUGILE
     */
    private void setUpdateCode(UpdateCode newUpdateCode){
        this.actualCode = newUpdateCode;
        setChanged();
        notifyObservers(newUpdateCode);
    }
    /**
     * Getter de la Partie du Joueur
     * @return La partie dans laquelle joue le Joueur.
     * @author Lilsb et AGOUGILE
     */
    public Game getGame(){
        return this.game;
    }

    /**
     * Getter du nom du Joueur.
     * @return Le nom du Joueur.
     * @author Lilsb et AGOUGILE
     */
    public String getName() {
        // Automatically generated method. Please do not modify this code.
        return this.name;
    }

    /**
     * Getter du nombre de point du Joueur.
     * @return Le nombre de point du Joueur.
     * @author Lilsb et AGOUGILE
     */
    public int getNumberOfPoints() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfPoints;
    }

    /**
     * Getter de la carte actuellement jouee.
     * @return La carte actuellement jouee.
     * @author Lilsb et AGOUGILE
     */
    public Card getCardWichIsPlayed(){return cardWichIsPlayed;}
    /**
     * Getter du Joueur Accuse.
     * @return Le Joueur Accuse.
     * @author Lilsb et AGOUGILE
     */
    public Player getAccusedPlayer(){
        return AccusedPlayer;
    }
    /**
     * Getter des cartes deja jouees par le Joueur.
     * @return La liste des cartes deja jouees par le Joueur.
     * @author Lilsb et AGOUGILE
     */
    public List<Card> getTable() {
        return table;
    }

    /**
     * Getter de la main du Joueur
     * @return La liste des carte composant la main du Joueur.
     * @author Lilsb et AGOUGILE
     */

    public ArrayList<Card> getDeck() {
        return deck;
    }
    /**
     * Getter de l'identite du Joueur
     * @return L'identite du Joueur.
     * @author Lilsb et AGOUGILE
     */
    public Identity getIdentity() {
        // Automatically generated method. Please do not modify this code.
        return this.identity;
    }

    /**
     * Setter de la Partie du Joueur
     * @param instance La partie dans laquelle doit jouer le Joueur.
     * @author Lilsb et AGOUGILE
     */
    public void setGame(Game instance){
        this.game = instance;
    }

    /**
     * Setter du nom du Joueur
     * @param name Le nom du joueur
     */
    public void setName(String name) {
        // Automatically generated method. Please do not modify this code.
        this.name = name;
    }
    /**
     * Setter de l'etat d'accusation du Joueur.
     * @param value L'etat d'accusation du Joueur.
     * @author Lilsb et AGOUGILE
     */
    public void setAccused(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.accused = value;
    }

    /**
     * Methode permettant d'ajouter des points au Joueur.
     * @param point Le nombre de point a ajouter.
     * @author Lilsb et AGOUGILE
     */
    public void addPoints(int point){
        this.numberOfPoints += point;
    }
    /**
     * Methode permettant de restart les points du Joueur.
     * @author Lilsb et AGOUGILE
     */
    public void restartPoint(){
        numberOfPoints=0;
    }


    /**
     * Verifie si le Joueur est accusee.
     * @return true si le Joueur est accusee.
     * @author Lilsb et AGOUGILE
     */
    public boolean isAccused() {
        // Automatically generated method. Please do not modify this code.
        return this.accused;
    }

    /**
     * Methode permettant de reveler l'identite du Joueur.
     * @author Lilsb et AGOUGILE
     */
    public void revealIdentity (){
        identity.setRevealed(true);
    }

    /**
     * Methode permettant au Joueur de choisir son identite.
     * @author Lilsb et AGOUGILE
     */
    public abstract void chooseIdentity();

    /**
     * Methode permettant de supprimer toute la main du Joueur.
     * @author Lilsb et AGOUGILE
     */
     public void clearDeck(){
        deck.removeAll(deck);
     }

    /**
     * Methode permettant de placer une carte dans la defausse.
     * @param stock La liste de carte visee.
     * @param card La carte a supprimer de la liste.
     * @author Lilsb et AGOUGILE
     */
     public void discardCardFrom(List<Card> stock ,Card card){
         stock.remove(card);
         Game.getDiscardedCard().add(card);
     }
    /**
     * Methode permettant d'ajouter une carte dans une liste donnee.
     * @param stock La "pile de carte dans laquelle on veut ajouter une carte.
     * @param card La carte que l'on veut ajouter.
     * @author Lilsb et AGOUGILE
     */
    public void addCardTo(List<Card> stock , Card card){
        stock.add(card);
        this.getGame().shuffle(stock);
    }
    /**
     * Methode permettant de supprimer une carte d'une liste donnee.
     * @param stock La "pile de carte dans laquelle on veut retirer une carte.
     * @param card La carte que l'on veut retirer.
     * @author Lilsb et AGOUGILE
     */
    public void removeCardTo(List<Card>stock,Card card){
        stock.remove(card);
    }

    /**
     * Methode relative a tous les Joueurs permettant de jouer un Round classique.
     * @author Lilsb et AGOUGILE
     */
    public abstract void play();

    /**
     * Methode relative a tous les Joueurs permettant de jouer une Carte Witch ou Hunt.
     * @param code L'etape du tour du Joueur.
     * @author Lilsb et AGOUGILE
     */
    public void playCard(UpdateCode code) {

        switch (code){
            case PLAY_CARD_HUNT -> {
                setUpdateCode(UpdateCode.EFFECT_CARD_HUNT);

                while(!cardWichIsPlayed.isRevealed() && actualCode!=UpdateCode.PLAY_CARD_HUNT);
                if(cardWichIsPlayed.isRevealed()){
                    if(cardWichIsPlayed.conditionHunt(this)){
                        cardWichIsPlayed.actionHunt(this);
                        removeCardTo(deck,cardWichIsPlayed);
                        addCardTo(table,cardWichIsPlayed);
                        cardWichIsPlayed = null;
                        setUpdateCode(UpdateCode.END_PLAY);
                    }else {
                        cardWichIsPlayed.setRevealed(false);
                        setUpdateCode(UpdateCode.PLAY_CARD_HUNT);
                    }
                }
            }
            case PLAY_CARD_WITCH -> {
                setUpdateCode(UpdateCode.EFFECT_CARD_WITCH);

                while(!cardWichIsPlayed.isRevealed() && actualCode!=UpdateCode.PLAY_CARD_WITCH && actualCode!=UpdateCode.IS_REVEALED);
                if(cardWichIsPlayed.isRevealed()){
                    if(cardWichIsPlayed.conditionWitch(this)){
                        cardWichIsPlayed.actionWitch(this);
                        removeCardTo(deck,cardWichIsPlayed);
                        addCardTo(table,cardWichIsPlayed);
                        cardWichIsPlayed = null;
                        setUpdateCode(UpdateCode.END_PLAY);
                    }else {
                        cardWichIsPlayed.setRevealed(false);
                        setUpdateCode(UpdateCode.PLAY_CARD_WITCH);
                    }
                }
            }
        }
    }

    /**
     * Methode relative a tous les Joueurs permettant de lancer la phase d'accusation sur un Joueur.
     * @author Lilsb et AGOUGILE
     */
    public void accusation(){
        for(Player allp : game.getPlayers()){
            if(allp.isAccused()){
                allp.play();
                if(allp.getIdentity().isRevealed()){
                    game.chooseNextPlayer(this);
                    allp.setAccused(false);
                    if(allp.getIdentity().getRole()==Role.Witch){
                        addPoints(1);
                        setUpdateCode(UpdateCode.END_PLAY);
                    }
                    else setUpdateCode(UpdateCode.END_PLAY);
                }else {
                    setUpdateCode(UpdateCode.END_PLAY);
                }
            }
        }
    }

    /**
     * Methode relative a tous les Joueurs permettant de valider un choix quelconque.
     * @param choice Le choix fait par le Joueur depuis une vue quelconque.
     * @param code L'etape du Joueur caracterisant le but du choix fait par le Joueur.
     * @author Lilsb et AGOUGILE
     */
    public void makeAchoice(int choice, UpdateCode code) {
        switch (code) {
            case ACCUSE -> {
                List<Player> listP = chooseThis(UpdateCode.ACCUSE);
                if (choice < listP.size() && choice >= 0) {
                    listP.get(choice).setAccused(true);
                    if (this instanceof PlayerIRL) {
                        setUpdateCode(UpdateCode.END_ACCUSATION);
                    }
                    if (this instanceof EasyModeBot || this instanceof HardModeBot) {
                        setUpdateCode(UpdateCode.BOT_ACCUSE);
                    }
                } else if (this instanceof PlayerIRL) {
                    setUpdateCode(UpdateCode.ACCUSE_OR_PLAY);
                }
            }
            case PLAY_CARD_HUNT -> {
                if (choice < getDeck().size() && choice >= 0) {
                    cardWichIsPlayed = getDeck().get(choice);
                    setUpdateCode(UpdateCode.END_CHOOSE_CARD);
                } else setUpdateCode(UpdateCode.ACCUSE_OR_PLAY);
            }
            case PLAY_CARD_WITCH -> {
                if (choice < getDeck().size() && choice >= 0) {
                    cardWichIsPlayed = getDeck().get(choice);
                    setUpdateCode(UpdateCode.END_CHOOSE_CARD);
                } else setUpdateCode(UpdateCode.IS_ACCUSED);
            }
            case EFFECT_CARD_HUNT -> {
                switch (choice){
                    case 1 -> {
                        cardWichIsPlayed.setRevealed(true);
                    }
                    case 2 -> setUpdateCode(UpdateCode.PLAY_CARD_HUNT);
                    default -> setUpdateCode(UpdateCode.EFFECT_CARD_HUNT);
                }
            }
            case EFFECT_CARD_WITCH -> {
                switch (choice){
                    case 1 -> cardWichIsPlayed.setRevealed(true);
                    case 2 -> setUpdateCode(UpdateCode.PLAY_CARD_WITCH);
                    default -> setUpdateCode(UpdateCode.EFFECT_CARD_WITCH);
                }
            }
        }
    }

    /**
     * Methode permettant de choisir un Joueur parmis un choix definit.
     * @return Le Joueur choisi.
     * @author Lilsb et AGOUGILE
     */
    public abstract Player chooseAPlayer();
    /**
     * Methode permettant de choisir une carte parmis un choix definit.
     * @return La Carte choisie.
     * @author Lilsb et AGOUGILE
     */
    public abstract Card chooseCardIn(List<Card> Stock);

    /**
     * Methode permettant de recevoir une liste de Joueur choisissable pour une etape donnee.
     * @return La liste des Joueurs choisissable.
     * @param actualCode L'etape du Joueur.
     * @author Lilsb et AGOUGILE
     */
    public List<Player> chooseThis(UpdateCode actualCode){
        List<Player> players = new ArrayList<>();
        if(actualCode==UpdateCode.ACCUSE){
            for(Player allP : game.getPlayers()){
                if (allP != this && !allP.getIdentity().isRevealed() && allP != game.getProtectedPlayer()) {
                    players.add(allP);
                }
            }
        }
        return players;
    }
    /**
     * Methode permettant de valider le choix d'un Joueur entre l'accusation ou le jeu d'une carte Hunt.
     * @param choice Le choix fait par le Joueur : 1 , 2 , retour.
     * @author Lilsb et AGOUGILE
     */
    public void setChoice(int choice){
        switch (choice) {
            case 1 -> setUpdateCode(UpdateCode.ACCUSE);
            case 2 -> {
                if(this.getDeck().isEmpty()){ setUpdateCode(UpdateCode.EMPTY_DECK);
                }else setUpdateCode(UpdateCode.PLAY_CARD_HUNT);}
            default -> setUpdateCode(UpdateCode.ACCUSE_OR_PLAY);
        }
    }
    /**
     * Methode permettant de valider le choix d'un Joueur entre revele son identite ou jouer une carte Witch.
     * @param choice Le choix fait par le Joueur : 1 , 2 , retour.
     * @author Lilsb et AGOUGILE
     */
    public void setChoiceAccused(int choice){
        switch (choice) {
            case 1 -> setUpdateCode(UpdateCode.IS_REVEALED);
            case 2 -> {if(this instanceof EasyModeBot || this instanceof HardModeBot) {
                    setUpdateCode(UpdateCode.BOT_PLAY_WITCH);
                }else setUpdateCode(UpdateCode.PLAY_CARD_WITCH);
            }
            default -> {if(this instanceof EasyModeBot || this instanceof HardModeBot) {
                    setUpdateCode(UpdateCode.BOT_IS_ACCUSED);
                }else setUpdateCode(UpdateCode.IS_ACCUSED);
            }
        }
    }
    /**
     * Methode permettant de valider le choix d'un Joueur lors du choix de l'identite.
     * 1 = Witch ; 2 = Hunt ; else = RETOUR ;
     * @param choice Le choix fait par le Joueur : 1 , 2 , retour.
     * @author Lilsb et AGOUGILE
     */
    public void setIdentity(int choice){
        switch (choice) {
            case 1 -> {
                identity.setRole(Role.Witch);
                setUpdateCode(UpdateCode.END_CHOOSE_IDENTITY);
            }
            case 2 -> {
                identity.setRole(Role.Hunt);
                setUpdateCode(UpdateCode.END_CHOOSE_IDENTITY);
            }
            default -> setUpdateCode(UpdateCode.CHOOSE_IDENTITY);
        }
    }
}
