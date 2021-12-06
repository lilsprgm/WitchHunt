package Game_operator;

import Cards.Card;

import java.util.List;

public class Bot extends Player implements Strategy {
    private int difficulty;
    private Strategy strategy;

    private int getDifficulty() {
        // Automatically generated method. Please do not modify this code.
        return this.difficulty;
    }

    private void setDifficulty(int value) {
        // Automatically generated method. Please do not modify this code.
        this.difficulty = value;
    }

    public Strategy getStrategy() {
        // Automatically generated method. Please do not modify this code.
        return this.strategy;
    }

    public void setStrategy(Strategy value) {
        // Automatically generated method. Please do not modify this code.
        this.strategy = value;
    }

    @Override
    public void chooseIdentity() {
        
    }

    @Override
    public Card chooseCardIn(List<Card> Stock) {
        return null;
    }

    @Override
    public void playCard() {

    }

    @Override
    public void play() {

    }
}
