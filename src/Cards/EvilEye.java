package Cards;
 import  Game_operator.*;
public class EvilEye extends Card{

    public EvilEye(){
        setName("EvilEye");
        setActionHunt("Choose next player\n At their turn they must accuse a player other than you if possible");
        setActionWitch(getActionHunt());
    }

    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());
        Player chosenPlayer = player.getGame().chooseAPlayer(player);
        player.getGame().chooseNextPlayer(chosenPlayer);
        player.getGame().setProtectedPlayer(player);
    }

    @Override
    public void actionHunt(Player player) {
        System.out.println(getActionHunt());
        Player chosenPlayer = player.getGame().chooseAPlayer(player);
        player.getGame().chooseNextPlayer(chosenPlayer);
        player.getGame().setProtectedPlayer(player);
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
