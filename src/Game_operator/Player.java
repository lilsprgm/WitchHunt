package Game_operator;

import Cards.*;

import java.util.*;

public abstract class Player extends Observable {

    protected int numberOfPoints = 0;
    protected String name;
    protected boolean accused = false;
    protected Player AccusedPlayer;
    protected Card cardWichIsPlayed = null;
    protected Identity identity =new Identity();
    //protected ArrayList<Action> action = new ArrayList<Action> (); // je sais pas a quoi sert cette variable ?
    protected ArrayList<Card> deck = new ArrayList<>();
    protected ArrayList<Card> table = new ArrayList<>();//collection dans laquelle sont stocké les cartes deja jouées. (c'est comme si on les posait devant soit.

    protected Scanner s = new Scanner(System.in);
    protected Game game;
    protected volatile UpdateCode actualCode;

    public String toString(){
        return name +" --> Score : " + numberOfPoints;
    }
    public List<Card> getTable() {
        return table;
    }

    public <T> void setVue( T vue){
        addObserver((Observer) vue);
    }
    public <T> void setVue( T vue,T vue2){
        addObserver((Observer) vue);
        addObserver((Observer)vue2);
    }

    private void setUpdateCode(UpdateCode newUpdateCode){
        this.actualCode = newUpdateCode;
        setChanged();
        notifyObservers(newUpdateCode);
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


    public Card getCardWichIsPlayed(){return cardWichIsPlayed;}
    /**
     *
     * @return Le joueur qui a été accusé
     */
    public Player getAccusedPlayer(){
        return AccusedPlayer;
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
    public void losePoints(int point){this.numberOfPoints-=point;}

    public void restartPoint(){
        numberOfPoints=0;
    }

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
    public void removeCardTo(List<Card>stock,Card card){
        stock.remove(card);
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


    public void playCard(UpdateCode code) {            // Lorsque Play card est appelé, on vérifie quelle carte à été révélé pour la jouer

        switch (code){
            case PLAY_CARD_HUNT -> {
                //Affichage des effets + actionHunt
                setUpdateCode(UpdateCode.EFFECT_CARD_HUNT);

                while(!cardWichIsPlayed.isRevealed() && actualCode!=UpdateCode.PLAY_CARD_HUNT);
                if(cardWichIsPlayed.isRevealed()){
                    removeCardTo(deck,cardWichIsPlayed);
                    addCardTo(table,cardWichIsPlayed);
                    //Action effectué
                    cardWichIsPlayed = null;
                    setUpdateCode(UpdateCode.END_PLAY);
                }
            }
            case PLAY_CARD_WITCH -> {
                //Affichage des effets + actionHunt
                setUpdateCode(UpdateCode.EFFECT_CARD_WITCH);

                while(!cardWichIsPlayed.isRevealed() && actualCode!=UpdateCode.PLAY_CARD_WITCH && actualCode!=UpdateCode.IS_REVEALED);
                System.out.println(actualCode);
                if(cardWichIsPlayed.isRevealed()){
                    removeCardTo(deck,cardWichIsPlayed);
                    addCardTo(table,cardWichIsPlayed);
                    //Action effectué
                    cardWichIsPlayed = null;
                    setUpdateCode(UpdateCode.END_PLAY);
                }
                System.out.println(actualCode);
            }
        }
//        System.out.println("Wich card do you want to play");
//        Card cardToBePlayed = this.chooseCardIn(deck);
//        if(accused && cardToBePlayed.conditionWitch(this)){
//            cardToBePlayed.actionWitch(this);
//            this.addCardTo(this.table,cardToBePlayed);
//        }
//        else if(!accused && cardToBePlayed.conditionHunt(this)){
//            cardToBePlayed.actionHunt(this);
//            this.addCardTo(this.table,cardToBePlayed);
//        }
//        this.getGame().chooseNextPlayer(this);
    }

    /**
     * Fonction quui permet de gérer la phase de jeu correspondant à une accusation.
     * Elle regroupe le choix du joueur accusé, l'action du joueur accusé, ainsi que l'attribution des points à la fin de l'accusation.
     * @author lilsb
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

        //        setUpdateCode(UpdateCode.ACCUSE);
//        System.out.println("ACCUSATION LANCE");
//        while(actualCode!=UpdateCode.END_ACCUSATION);
//        Player accusedPlayer = new PlayerIRL();
//        do {
//            //accusedPlayer = chooseAPlayer();
//        }while ((accusedPlayer == game.getProtectedPlayer()) || accusedPlayer == this);// pb si le joueur protégé est le seul à pouvoir être accusé // remettre en protected player pour que ca soit plus simple et que ca colle avec les cartes.
//        // solution peut etre lorsque l'on set le playerprotected faire une verif
//        accusedPlayer.setAccused(true);
//        accusedPlayer.play();
//        accusedPlayer.setAccused(false);
//        game.setProtectedPlayer(null); // permet la réinitialistation de joueur protégé (car protégé un tour seulement
//        if (accusedPlayer.getIdentity().isRevealed() & accusedPlayer.getIdentity().getRole() == Role.Witch){
//            this.addPoints(1);
//            System.out.println(this.getName() + " won 1 point");
//        }

    }

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
                    case 1 -> cardWichIsPlayed.setRevealed(true);
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


    public abstract Card chooseCardIn(List<Card> Stock);

    //Renvoi la liste de player élligibles au codes
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

    public void setChoice(int choice){
        switch (choice) {
            case 1 -> setUpdateCode(UpdateCode.ACCUSE);
            case 2 -> {
                if(this.getDeck().isEmpty()){ setUpdateCode(UpdateCode.EMPTY_DECK);
                }else setUpdateCode(UpdateCode.PLAY_CARD_HUNT);}
            default -> setUpdateCode(UpdateCode.ACCUSE_OR_PLAY);
        }
    }
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

    public void endPlay(){
        setUpdateCode(UpdateCode.END_PLAY);
    }

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
