import Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Game extends Observable {
    private int numberOfPlayer;

    private int getNumberOfPlayer() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfPlayer;
    }

    private void setNumberOfPlayer(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numberOfPlayer = value;
    }

    private Game game;

    private Game getGame() {
        // Automatically generated method. Please do not modify this code.
        return this.game;
    }

    private void setGame(Game value) {
        // Automatically generated method. Please do not modify this code.
        this.game = value;
    }

    private int numberOfBot;

    private int getNumberOfBot() {
        // Automatically generated method. Please do not modify this code.
        return this.numberOfBot;
    }

    private void setNumberOfBot(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numberOfBot = value;
    }

    private Administrator admnistrator;

    public Administrator getAdmnistrator() {
        // Automatically generated method. Please do not modify this code.
        return this.admnistrator;
    }

    public void setAdmnistrator(Administrator value) {
        // Automatically generated method. Please do not modify this code.
        this.admnistrator = value;
    }

    private List<Player> players = new ArrayList<Player> ();

    public List<Player> getPlayers() {
        // Automatically generated method. Please do not modify this code.
        return this.players;
    }

    public void setPlayers(List<Player> value) {
        // Automatically generated method. Please do not modify this code.
        this.players = value;
    }

    private List<Card> cards = new ArrayList<Card> ();

    public List<Card> getCards() {
        // Automatically generated method. Please do not modify this code.
        return this.cards;
    }

    public void setCards(List<Card> value) {
        // Automatically generated method. Please do not modify this code.
        this.cards = value;
    }

    public void getInstance() {
    }

    public void game() {
    }

    public void gameManagement() {
    }

}
