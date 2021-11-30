package Cards;

import Game_operator.*;

public class Inquisition extends Card {

    public Inquisition(){
        setName("Inquisition");
        setActionHunt("Choose next player\nBefore their turn secretly look at their identity");
        setActionWitch("Discard a card from your hand\n You take next turn");
        setConditionHunt("Only available if you have been revealed as a Villager");
    }

    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());
        Card cardToBeDiscard = player.chooseCardIn(player.getDeck());
        player.discardCardFrom(player.getDeck(), cardToBeDiscard);
    }

    @Override
    public void actionHunt(Player player) {
        System.out.println(getActionHunt());
        player.getGame().chooseNextPlayer(player.getGame().chooseAPlayer(player));// on appelle dans la partie du joueur lafonction pour designer
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
