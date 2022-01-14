package Cards;

import Game_operator.Player;
import java.lang.Math; // Pour les fonctions Random

/**
 * Classe representant la carte Cauldron.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class Cauldron extends Card {

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private Cauldron(){
        setName("Cauldron");
        setActionHunt("Reveal your identity\nVillager : Choose next player\nWitch : Player to your left takes next turn");
        setActionWitch("The player who accused you discards a random card from their hand\nTake next turn");
        setConditionHunt("");
        setConditionWitch("");
    }
    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static Cauldron instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static Cauldron getInstance(){
        if (Cauldron.instance == null) {
            Cauldron.instance = new Cauldron();
        }
        return instance;
    }
    @Override
    public void actionWitch(Player player) {
        int  range = player.getGame().getCurrentPlayer().getDeck().size()+1;
        int i = (int)(Math.random()*range);
        Card randomCard = player.getGame().getCurrentPlayer().getDeck().get(i);
        player.discardCardFrom(player.getGame().getCurrentPlayer().getDeck(),randomCard);

        player.getGame().chooseNextPlayer(player);
    }

    @Override
    public void actionHunt(Player player) {
        player.getIdentity().setRevealed(true);
        int numberofPlayer = player.getGame().getNumberOfPlayer()+player.getGame().getNumberOfBot();
        int indexOfPlayer = player.getGame().getPlayers().indexOf(player);

        if(player.getIdentity().getRole() == Role.Witch){
            if(indexOfPlayer == numberofPlayer-1){
                player.getGame().chooseNextPlayer(player.getGame().getPlayers().get(0));
            }
            else{
                player.getGame().chooseNextPlayer( player.getGame().getPlayers().get(indexOfPlayer+1) );
            }
        }
        else{
            player.getGame().chooseNextPlayer(player.chooseAPlayer());
        }
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
