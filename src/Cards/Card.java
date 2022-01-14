
package Cards;

import Game_operator.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Classe representant une carte en general.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class Card implements Action{
    /**
     * Carte revele ou non.
     * @author Lilsb et AGOUGILE
     */
    private boolean revealed = false;
    /**
     * Nom de la carte.
     * @author Lilsb et AGOUGILE
     */
    protected String name;
    /**
     * Description de l'effet Witch de la Carte.
     * @author Lilsb et AGOUGILE
     */
    private String actionWitch;
    /**
     * Description de l'effet Hunt de la Carte.
     * @author Lilsb et AGOUGILE
     */
    private String actionHunt;
    /**
     * Description de la condition Hunt de la Carte.
     * @author Lilsb et AGOUGILE
     */
    private String conditionHunt;
    /**
     * Description de la condition Witch de la Carte.
     * @author Lilsb et AGOUGILE
     */
    private String conditionWitch;
    /**
     * Scanner permettant de relever l'entre utilisateur.
     * @author Lilsb et AGOUGILE
     */
    protected Scanner s = new Scanner(System.in);



    /**
     * Setter du nom de la carte.
     * @param name Le nom de la carte.
     * @author Lilsb et AGOUGILE
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter du nom de la carte.
     * @return Le nom de la carte.
     * @author Lilsb et AGOUGILE
     */
    public String getName() {
        return name;
    }

    /**
     * Methode permettant de verifier si la carte a ete revelee.
     * @return true si la carte est revelee.
     * @author Lilsb et AGOUGILE
     */
    public boolean isRevealed() {
        return this.revealed;
    }

    /**
     * Setter de l'etat revele de la carte.
     * @param value true : revele la carte.
     * @author Lilsb et AGOUGILE
     */
    public void setRevealed(boolean value) {
        this.revealed = value;
    }

    /**
     * Getter de la description de l'effet Hunt.
     * @return La description de l'effet Hunt.
     * @author Lilsb et AGOUGILE
     */
    public String getActionHunt() {
        return actionHunt;
    }
    /**
     * Getter de la description de l'effet Witch.
     * @return La description de l'effet Witch.
     * @author Lilsb et AGOUGILE
     */
    public String getActionWitch() {
        return actionWitch;
    }
    /**
     * Getter de la description de la condition Hunt.
     * @return La description de la condition Hunt.
     * @author Lilsb et AGOUGILE
     */
    public String getConditionHunt() {
        return conditionHunt;
    }
    /**
     * Getter de la description de la condition Witch.
     * @return La description de la condition Witch.
     * @author Lilsb et AGOUGILE
     */
    public String getConditionWitch() {
        return conditionWitch;
    }
    /**
     * Setter de la description de l'effet Hunt.
     * @param actionHunt La description de l'effet Hunt.
     * @author Lilsb et AGOUGILE
     */
    public void setActionHunt(String actionHunt) {
        this.actionHunt = actionHunt;
    }
    /**
     * Setter de la description de l'effet Witch.
     * @param actionWitch  La description de l'effet Witch.
     * @author Lilsb et AGOUGILE
     */
    public void setActionWitch(String actionWitch) {
        this.actionWitch = actionWitch;
    }
    /**
     * Setter de la description de la condition Hunt.
     * @param conditionHunt  La description de la condition Hunt.
     * @author Lilsb et AGOUGILE
     */
    public void setConditionHunt(String conditionHunt) {
        this.conditionHunt = conditionHunt;
    }
    /**
     * Setter de la description de la condition Witch.
     * @param conditionWitch  La description de la condition Witch.
     * @author Lilsb et AGOUGILE
     */
    public void setConditionWitch(String conditionWitch) {
        this.conditionWitch = conditionWitch;
    }
    /**
     * Methode permettant d'afficher le nom de la carte.
     * @return Le nom de la carte.
     * @author Lilsb et AGOUGILE
     */
    public String toString (){
        return this.name;
    }


    /**
     * Methode permettant d'effectuer l'action Witch de la carte  appele par un Joueur.
     * @param player Le Joueur qui a joue la carte.
     * @author Lilsb et AGOUGILE
     */
    public void actionWitch(Player player){}
    public void actionHunt(Player player){}
    public boolean conditionWitch(Player player){return false;}
    public boolean conditionHunt(Player player){return false;}

}
