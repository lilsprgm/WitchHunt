package Cards;

import Game_operator.Player;

public class Toad extends Card {

    private Toad(){
        setName("Toad");
        setActionHunt("Reveal your identity\nVillager : Choose next player\nWitch : Player to your left takes next turn");
        setActionWitch("Choose next player");
        setConditionHunt("");
        setConditionWitch("");
    }
    private static Toad instance =null;
    public static Toad getInstance(){
        if (Toad.instance == null) {
            Toad.instance = new Toad();
        }
        return instance;
    }

    /**
     * Permet d'activier l'effet Witch de la carte Toad.
     *Le Joueur doit choisir parmis les Joueurs de la partie le prochain à jouer.
     * @param player Représente le joueur qui a jouer la carte.
     */
    @Override
    public void actionWitch(Player player) {
        player.getGame().chooseNextPlayer(player.chooseAPlayer());
    }

    /**
     * Permet d'activier l'effet Hunt de la carte Toad.
     * Le Joueur révèle sa carte.
     * Si le Joueur est un Villageois il doit choisir le prochain Joueur à jouer.
     * Si le joueur est une Witch il doit laisser le prochain Joueur de la partie jouer.
     * @param player Représente le joueur qui a jouer la carte.
     */
    @Override
    public void actionHunt(Player player) {
        player.getIdentity().setRevealed(true);
        int numberofPlayer = player.getGame().getNumberOfPlayer()+player.getGame().getNumberOfBot();
        int indexOfPlayer = player.getGame().getPlayers().indexOf(player);

        if(player.getIdentity().getRole() == Role.Witch){
            if(indexOfPlayer == numberofPlayer-1){
                player.getGame().chooseNextPlayer(player.getGame().getPlayers().get(0));
            }
            else{
                player.getGame().chooseNextPlayer( player.getGame().getPlayers().get(indexOfPlayer+1) );
            }
        }
        else{
            player.getGame().chooseNextPlayer(player.chooseAPlayer());
        }
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
