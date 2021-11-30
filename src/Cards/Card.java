
package Cards;

import Game_operator.*;

import java.util.Scanner;

public class Card implements Action{ //(extends Observable)
    private boolean revealed;
    protected String name;
    private String actionWitch;
    private String actionHunt;
    private String conditionHunt;
    private String conditionWitch;
    private Scanner s = new Scanner(System.in);

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isRevealed() {
        // Automatically generated method. Please do not modify this code.
        return this.revealed;
    }

    public void setRevealed(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.revealed = value;
    }

    public String getActionHunt() {
        return actionHunt;
    }

    public String getActionWitch() {
        return actionWitch;
    }

    public String getConditionHunt() {
        return conditionHunt;
    }

    public String getConditionWitch() {
        return conditionWitch;
    }

    public void setActionHunt(String actionHunt) {
        this.actionHunt = actionHunt;
    }

    public void setActionWitch(String actionWitch) {
        this.actionWitch = actionWitch;
    }

    public void setConditionHunt(String conditionHunt) {
        this.conditionHunt = conditionHunt;
    }

    public void setConditionWitch(String conditionWitch) {
        this.conditionWitch = conditionWitch;
    }

    public String toString (){
        return this.name;
    }


    @Override
    public void actionWitch(Player player) {

    }

    @Override
    public void actionHunt(Player player) {

    }

    @Override
    public boolean conditionWitch(Player player) {
        return false;
    }

    @Override
    public boolean conditionHunt(Player player) {
        return false;
    }

}
