package Cards;

import Game_operator.Game;
import Game_operator.Player;
import Game_operator.UpdateCode;

/**
 * Classe representant la carte AngryMob.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class AngryMob extends Card implements Action{

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private AngryMob(){
        setName("AngryMob");
        setActionHunt("[Hunt Effect] : Reveal another player's identity\nIf he is a witch you win 2 points else you lose 2 points");
        setActionWitch("[Witch Effect] : You take next turn");
        setConditionHunt("[Condition] : Only available if you have been revealed as a Villager");
        setConditionWitch("");
    }

    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static AngryMob instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static AngryMob getInstance(){
        if (AngryMob.instance == null) {
            AngryMob.instance = new AngryMob();
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
        Player choosenPlayer;
        boolean containsBroomstick = false;
        do{
            choosenPlayer = player.chooseAPlayer();
            containsBroomstick = false;
            for (Card card : choosenPlayer.getTable()){
                if (card instanceof Broomstick){
                    System.out.println("Choose another player. You can't choose him he has a special card");
                    containsBroomstick = true;
                    break;
                }
            }
        }while (containsBroomstick);
        choosenPlayer.getIdentity().setRevealed(true);
        if (choosenPlayer.getIdentity().getRole() == Role.Hunt){
            System.out.println(" You have revealed a Hunt. You loose 2 points");
            player.addPoints(-2);
        }
        else{
            System.out.println(" You have revealed a Witch. You win 2 points");
            player.addPoints(2);
        }
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
