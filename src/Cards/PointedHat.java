package Cards;

import Game_operator.Player;

public class PointedHat extends Card{

    private PointedHat(){
        setName("PointedHat");
        setActionWitch("Take one of your own revealed  rumour card into your hand\nTake next turn");
        setActionHunt("Take one of your own revealed  rumour card into your hand\nChoose next Player");
        setConditionHunt("Only available if you have a revealed Rumour Card");
        setConditionWitch("Only available if you have a revealed Rumour Card");
    }

    private static PointedHat instance =null;
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
