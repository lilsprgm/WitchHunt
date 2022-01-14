package Cards;

import Game_operator.Player;

/**
 * Classe representant la carte Wart.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class Wart extends Card {

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private Wart(){
        setName("Wart");
        setActionHunt("[Hunt Effect] : Choose next player");
        setActionWitch("[Witch Effect] : Take next turn");
        setConditionHunt("[Condition] : No condition");
        setConditionWitch("[Condition] : No condition");
    }
    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static Wart instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static Wart getInstance(){
        if (Wart.instance == null) {
            Wart.instance = new Wart();
        }
        return instance;
    }

    @Override
    public void actionWitch(Player player) {
        player.getGame().chooseNextPlayer(player);
        player.setAccused(false);
    }

    @Override
    public void actionHunt(Player player) {
        player.getGame().chooseNextPlayer(player.chooseAPlayer());
    }

    @Override
    public boolean conditionWitch(Player player) {
        return true;
    }

    @Override
    public boolean conditionHunt(Player player) {
        return true;
    }
}
