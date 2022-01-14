package Cards;

import Game_operator.*;

/**
 * Classe representant la carte BlackCat.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class BlackCat extends Card{

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
      private BlackCat(){
            setName("Black Cat");
            setActionHunt("Add one discarded card to your hand, and then discard this card\n Take next turn");
            setActionWitch("Take next turn");
            setConditionHunt("At least one card is discarded");
            setConditionWitch("");
        }

    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static BlackCat instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static BlackCat getInstance(){
        if (BlackCat.instance == null) {
            BlackCat.instance = new BlackCat();
        }
        return instance;
    }
    @Override
    public void actionWitch(Player player) {
        player.getGame().chooseNextPlayer(player.chooseAPlayer());
    }

    @Override
    public void actionHunt(Player player) {
        player.discardCardFrom(player.getDeck(),this);
        player.getDeck().add(player.chooseCardIn(Game.getDiscardedCard()));
    }

    @Override
    public boolean conditionWitch(Player player) {
        return true;
    }
    @Override
    public boolean conditionHunt(Player player) {
        System.out.println(getConditionHunt());
        return Game.getDiscardedCard().size() > 0;
    }
}
