package Cards;

import Game_operator.Player;

public class BlackCat extends Card{

    public BlackCat(){
        setName("Black Cat");
        setActionHunt("Add one discarded card to your hand, and then discard this card\n Take next turn");
        setActionWitch("Take next turn");
        setConditionHunt("At least one card is discarded");
    }

    /**
     * Permet d'activer l'effet Witch de la carte BlackCat.
     *Le Joueur qui à joué la carte est le prochain à jouer.
     * @param player Représente le joueur qui a jouer la carte.
     */
    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());
        player.getGame().chooseNextPlayer(player.getGame().chooseAPlayer(player));
    }

    /**
     * Permet d'activer l'effet Hunt de la carte BlackCat.
     * Le Joueur peut ajouter une carte de la défausse en échange de la carte BlackCat jouée.
     * Il joue le prochain tour.
     * @param player Représente le joueur qui a jouer la carte.
     */
    @Override
    public void actionHunt(Player player) {
        System.out.println(getActionHunt());
        player.discardCardFrom(player.getDeck(),this);
        player.getDeck().add(player.chooseCardIn(player.getGame().getDiscardedCard()));
    }

    @Override
    public boolean conditionWitch(Player player) {

        return true;
    }

    @Override
    public boolean conditionHunt(Player player) {
        System.out.println(getConditionHunt());
        if(player.getGame().getDiscardedCard().size()>0){
            return true;
        }
        return false;
    }
}
