package Cards;

import Game_operator.Player;

public class PetNewt extends Card {

    public PetNewt(){
        setName("Pet Newt");
        setActionHunt("Take a revealed Rumour card from any other player into your hand\nChoose next player");
        setActionWitch("Take next turn");
        setConditionHunt("At least one player has already revealed a Rumor Card");
    }

    @Override
    public void actionWitch(Player player) {
        player.getGame().chooseNextPlayer(player);
    }

    /**
     * Permet d'activer l'effet Hunt de la carte PetNewt.
     * Le Joueur va devoir choisir parmis la liste de tous les joueurs de la partie celui à qui il va prendre
     * une carte Rumeur déja révélée. La liste des Joueurs apparaitra avec leur
     * nombre de carte Rmeur révélée, puis la liste de ces cartes révélées.
     */
    @Override
    public void actionHunt(Player player) {
        int i=1;
        System.out.println("Choose a player from whom you want to take a revealed Card : \n");
        for(Player p : player.getGame().getPlayers()){
            if (p == player ){
                continue;
            }
            System.out.println(i+"- "+p.getName()+"--> "+p.getTable().size()+" Revealed Cards");
            i++;
        }

        player.addCardTo(player.getDeck(), player.chooseCardIn(player.getGame().chooseAPlayer(player).getTable()) );
    }

    @Override
    public boolean conditionWitch(Player player) {
        return true;
    }

    /**
     * Permet de vérifier si le Joueur peut jouer la carte PetNewt ou non.
     * On vérifie pour tout les Joueurs de la partie si au moins 1 dispose de carte Rumeur révélée.
     */
    @Override
    public boolean conditionHunt(Player player) {
        for(Player p : player.getGame().getPlayers()){
            if(p.getTable().size()>0){
                return true;
            }
        }
        return false;
    }
}
