package Cards;

import Game_operator.Player;

public class Wart extends Card {

    private Wart(){
        setName("Wart");
        setActionHunt("[Hunt Effect] : Choose next player");
        setActionWitch("[Witch Effect] : Take next turn");
        setConditionHunt("[Condition] : No condition");
        setConditionWitch("[Condition] : No condition");
    }
    private static Wart instance =null;
    public static Wart getInstance(){
        if (Wart.instance == null) {
            Wart.instance = new Wart();
        }
        return instance;
    }

    /**
     * Permet d'activer l'effet Witch de la carte Wart.
     *Le Joueur est le prochain à jouer.
     * @param player Représente le joueur qui a jouer la carte.
     */
    @Override
    public void actionWitch(Player player) {
        player.getGame().chooseNextPlayer(player);
        player.setAccused(false);
    }

    /**
     * Permet d'activer l'effet Hunt de la carte Wart.
     *Le Joueur doit choisir parmis les Joueurs de la partie le prochain à jouer.
     * @param player Représente le joueur qui a jouer la carte.
     */
    @Override
    public void actionHunt(Player player) {
        player.getGame().chooseNextPlayer(player.chooseAPlayer());
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
