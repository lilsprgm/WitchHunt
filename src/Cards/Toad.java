package Cards;

import Game_operator.Player;

/**
 * Classe representant la carte Toad.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class Toad extends Card {

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private Toad(){
        setName("Toad");
        setActionHunt("Reveal your identity\nVillager : Choose next player\nWitch : Player to your left takes next turn");
        setActionWitch("Choose next player");
        setConditionHunt("");
        setConditionWitch("");
    }
    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static Toad instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static Toad getInstance(){
        if (Toad.instance == null) {
            Toad.instance = new Toad();
        }
        return instance;
    }

    @Override
    public void actionWitch(Player player) {
        player.getGame().chooseNextPlayer(player.chooseAPlayer());
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
        return true;
    }

    @Override
    public boolean conditionHunt(Player player) {
        return true;
    }
}
