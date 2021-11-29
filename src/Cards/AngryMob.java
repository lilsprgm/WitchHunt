package Cards;

import Game_operator.Player;

public class AngryMob extends Card implements Action{

    public AngryMob(){
        setName("AngryMob");
    }

    @Override
    public void actionWitch(Player player) {
        System.out.println("You take next turn");
    }

    @Override
    public void actionHunt(Player player) {
        System.out.println("You can reveal another player's identity");
        Player choosenPlayer = player.getGame().chooseAPlayer(player);
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
        if (player.getIdentity().isRevealed() & player.getIdentity().getRole() == Role.Hunt){
            return true;
        }
        else {
            return false;
        }
    }
}