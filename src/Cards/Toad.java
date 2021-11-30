package Cards;

import Game_operator.Player;

public class Toad extends Card {

    public Toad(){
        setName("Toad");
        setActionHunt("Reveal your identity\nVillager : Choose next player\nWitch : Player to your left takes next turn");
        setActionWitch("Choose next player");
    }

    /**
     * Permet d'activier l'effet Witch de la carte Toad.
     *Le Joueur doit choisir parmis les Joueurs de la partie le prochain à jouer.
     * @param player Représente le joueur qui a jouer la carte.
     */
    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());
        player.getGame().chooseNextPlayer(player.getGame().chooseAPlayer(player));
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
        System.out.println(getActionHunt());
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
            player.getGame().chooseNextPlayer(player.getGame().chooseAPlayer(player));
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
