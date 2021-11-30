package Cards;

import Game_operator.Player;

public class PointedHat extends Card{

    public PointedHat(){
        setName("PointedHat");
        setActionWitch("Take one of your own revealed  rumour card into your hand\nTake next turn");
    }

    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());
        Card chosenCard = player.chooseCardIn(player.getTable());
        chosenCard.setRevealed(false);
        player.addCardTo(player.getDeck(), chosenCard);
        player.discardCardFrom(player.getTable(), chosenCard);
    }

    @Override
    public void actionHunt(Player player) {
        System.out.println(getActionWitch());
        Card chosenCard = player.chooseCardIn(player.getTable());
        chosenCard.setRevealed(false);
        player.addCardTo(player.getDeck(), chosenCard);
        player.discardCardFrom(player.getTable(), chosenCard);
        player.getGame().chooseNextPlayer(player.getGame().chooseAPlayer(player));
    }

    @Override
    public boolean conditionWitch(Player player) {
        if (player.getTable().isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean conditionHunt(Player player) {
        if (player.getTable().isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }
}
