package Cards;

import Game_operator.Player;

public class HookedNose extends Card {

    private HookedNose(){
        setName("HookedNose");
        setActionHunt("Choose next player\n Before their turn take a card from its hand and add it to yours");
        setActionWitch("You take a card from the  hand of the player who has accused you\n You take next turn");
        setConditionHunt("");
        setConditionHunt("");
    }

    private static HookedNose instance =null;
    public static HookedNose getInstance(){
        if (HookedNose.instance == null) {
            HookedNose.instance = new HookedNose();
        }
        return instance;
    }
    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());
        Player currentPlayer = player.getGame().getCurrentPlayer();
        Card chosencard = player.chooseCardIn(currentPlayer.getDeck());
        player.addCardTo(player.getDeck(), chosencard);
        currentPlayer.discardCardFrom(currentPlayer.getDeck(), chosencard);
        //on ajoute une carte au joueur
        //on la choisit dans la main du joueur qui joue actuellement (donc qui a accusé). On y accede a travers a partie du joueur,
        // la variable current player et on obtient le deck avec getDeck().
    }

    @Override
    public void actionHunt(Player player) {
        System.out.println(getActionHunt());
        Player chosenPlayer = player.chooseAPlayer();
        player.getGame().chooseNextPlayer(chosenPlayer);
        player.addCardTo(player.getDeck(), player.getGame().draw(chosenPlayer.getDeck())); // on ajoute une carte au deck du joueur qui joue,
        // qu'on a pioché dans la main du joueur choisi

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
