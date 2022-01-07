package Cards;

import Game_operator.*;

public class Inquisition extends Card {

    private Inquisition(){
        setName("Inquisition");
        setActionHunt("Choose next player\nBefore their turn secretly look at their identity");
        setActionWitch("Discard a card from your hand\n You take next turn");
        setConditionHunt("Only available if you have been revealed as a Villager");
        setConditionWitch("");
    }
    private static Inquisition instance =null;
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
        player.getGame().chooseNextPlayer(player.chooseAPlayer());// on appelle dans la partie du joueur lafonction pour designer
        //le joueur suivant en appellant en parametre la fonction pour choisir un joueur.
        System.out.println("Its identtity is  : " + player.getGame().getChosenNextPLayer().getIdentity().getRole());
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
