package Cards;

import Game_operator.*;

/**
 * Classe representant la carte Inquisition.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class Inquisition extends Card {

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private Inquisition(){
        setName("Inquisition");
        setActionHunt("Choose next player\nBefore their turn secretly look at their identity");
        setActionWitch("Discard a card from your hand\n You take next turn");
        setConditionHunt("Only available if you have been revealed as a Villager");
        setConditionWitch("");
    }
    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static Inquisition instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static Inquisition getInstance(){
        if (Inquisition.instance == null) {
            Inquisition.instance = new Inquisition();
        }
        return instance;
    }

    @Override
    public void actionWitch(Player player) {
        Card cardToBeDiscard = player.chooseCardIn(player.getDeck());
        player.discardCardFrom(player.getDeck(), cardToBeDiscard);
    }

    @Override
    public void actionHunt(Player player) {
        player.getGame().chooseNextPlayer(player.chooseAPlayer());
        System.out.println("Its identtity is  : " + player.getGame().getChosenNextPLayer().getIdentity().getRole());
    }

    @Override
    public boolean conditionWitch(Player player) {
        return true;
    }

    @Override
    public boolean conditionHunt(Player player) {
        return player.getIdentity().isRevealed() & player.getIdentity().getRole() == Role.Hunt;
    }
}
