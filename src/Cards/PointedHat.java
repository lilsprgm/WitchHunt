package Cards;

import Game_operator.Player;

/**
 * Classe representant la carte PointedHat.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class PointedHat extends Card{

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private PointedHat(){
        setName("PointedHat");
        setActionWitch("Take one of your own revealed  rumour card into your hand\nTake next turn");
        setActionHunt("Take one of your own revealed  rumour card into your hand\nChoose next Player");
        setConditionHunt("Only available if you have a revealed Rumour Card");
        setConditionWitch("Only available if you have a revealed Rumour Card");
    }
    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static PointedHat instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static PointedHat getInstance(){
        if (PointedHat.instance == null) {
            PointedHat.instance = new PointedHat();
        }
        return instance;
    }

    @Override
    public void actionWitch(Player player) {
        Card chosenCard = player.chooseCardIn(player.getTable());
        chosenCard.setRevealed(false);
        player.addCardTo(player.getDeck(), chosenCard);
        player.discardCardFrom(player.getTable(), chosenCard);
        player.getGame().chooseNextPlayer(player);
    }

    @Override
    public void actionHunt(Player player) {
        Card chosenCard = player.chooseCardIn(player.getTable());
        chosenCard.setRevealed(false);
        player.addCardTo(player.getDeck(), chosenCard);
        player.discardCardFrom(player.getTable(), chosenCard);
        player.getGame().chooseNextPlayer(player.chooseAPlayer());
    }

    @Override
    public boolean conditionWitch(Player player) {
        return !player.getTable().isEmpty();
    }

    @Override
    public boolean conditionHunt(Player player) {
        return !player.getTable().isEmpty();
    }
}
