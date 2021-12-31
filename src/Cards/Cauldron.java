package Cards;

import Game_operator.Player;
import java.lang.Math; // Pour les fonctions Random

public class Cauldron extends Card {

    private Cauldron(){
        setName("Cauldron");
        setActionHunt("Reveal your identity\nVillager : Choose next player\nWitch : Player to your left takes next turn");
        setConditionWitch("The player who accused you discards a random card from their hand\nTake next turn");
    }
    private static Cauldron instance =null;
    public static Cauldron getInstance(){
        if (Cauldron.instance == null) {
            Cauldron.instance = new Cauldron();
        }
        return instance;
    }

    /**
     * Permet d'activier l'effet Witch de la carte Cauldron.
     * Le Joueur qui à accusé doit défausser une carte aléatoire de sa main.
     * C'est ensuite au tour du Joueur accusé.
     * @param player Représente le joueur qui a jouer la carte.
     */
    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());  // peut-etre utiliser une version avec draw
        int  range = player.getGame().getCurrentPlayer().getDeck().size()+1;
        int i = (int)(Math.random()*range);
        Card randomCard = player.getGame().getCurrentPlayer().getDeck().get(i);
        player.discardCardFrom(player.getGame().getCurrentPlayer().getDeck(),randomCard);

        player.getGame().chooseNextPlayer(player);
    }

    /**
     * Permet d'activier l'effet Hunt de la carte Cauldron.
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
            player.getGame().chooseNextPlayer(player.chooseAPlayer());
        }
    }
    @Override
    public boolean conditionWitch(Player player) {
        return false;
    }

    @Override
    public boolean conditionHunt(Player player) {
        return false;
    }
}
