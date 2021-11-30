package Cards;

import Game_operator.Player;

public class Wart extends Card {

    public Wart(){
        setName("Wart");
        setActionHunt("Choose next player");
        setActionWitch("Take next turn");
    }

    /**
     * Permet d'activer l'effet Witch de la carte Wart.
     *Le Joueur est le prochain à jouer.
     * @param player Représente le joueur qui a jouer la carte.
     */
    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());
        player.getGame().chooseNextPlayer(player);
    }

    /**
     * Permet d'activer l'effet Hunt de la carte Wart.
     *Le Joueur doit choisir parmis les Joueurs de la partie le prochain à jouer.
     * @param player Représente le joueur qui a jouer la carte.
     */
    @Override
    public void actionHunt(Player player) {
        System.out.println(getActionHunt());
        player.getGame().chooseNextPlayer(player.getGame().chooseAPlayer(player));
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
