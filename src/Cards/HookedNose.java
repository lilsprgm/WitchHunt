package Cards;

import Game_operator.Player;

/**
 * Classe representant la carte HookedNose.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class HookedNose extends Card {

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private HookedNose(){
        setName("HookedNose");
        setActionHunt("Choose next player\n Before their turn take a card from its hand and add it to yours");
        setActionWitch("You take a card from the  hand of the player who has accused you\n You take next turn");
        setConditionHunt("");
        setConditionHunt("");
    }

    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static HookedNose instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static HookedNose getInstance(){
        if (HookedNose.instance == null) {
            HookedNose.instance = new HookedNose();
        }
        return instance;
    }
    @Override
    public void actionWitch(Player player) {
        Player currentPlayer = player.getGame().getCurrentPlayer();
        Card chosencard = player.chooseCardIn(currentPlayer.getDeck());
        player.addCardTo(player.getDeck(), chosencard);
        currentPlayer.discardCardFrom(currentPlayer.getDeck(), chosencard);
    }

    @Override
    public void actionHunt(Player player) {
        Player chosenPlayer = player.chooseAPlayer();
        player.getGame().chooseNextPlayer(chosenPlayer);
        chosenPlayer.setAccused(false);
        player.addCardTo(player.getDeck(), player.getGame().draw(chosenPlayer.getDeck()));
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
