package Cards;

import Game_operator.Player;

/**
 * Interface permettant d'implementer les methodes liees aux actions WITCH et HUNT avec leurs conditions.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public interface Action {
    /**
     * Methode permettant d'effectuer l'action Witch de la carte appele par un Joueur.
     * @param player Le Joueur qui a joue la carte.
     * @author Lilsb et AGOUGILE
     */
    void actionWitch(Player player);

    /**
     * Methode permettant d'effectuer l'action Hunt de la carte appele par un Joueur.
     * @param player Le Joueur qui a joue la carte.
     * @author Lilsb et AGOUGILE
     */
    void actionHunt(Player player);

    /**
     * Methode permettant de verifier la condition Witch de la carte appele par un Joueur.
     * @param player Le Joueur qui a joue la carte.
     * @return true si la carte peut etre jouee.
     * @author Lilsb et AGOUGILE
     */
    boolean conditionWitch(Player player);

    /**
     * Methode permettant de verifier la condition Hunt de la carte appele par un Joueur.
     * @param player Le Joueur qui a joue la carte.
     * @return true si la carte peut etre jouee.
     * @author Lilsb et AGOUGILE
     */
    boolean conditionHunt(Player player);
}
