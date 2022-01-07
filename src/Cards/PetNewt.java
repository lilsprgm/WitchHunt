package Cards;

import Game_operator.Player;

public class PetNewt extends Card {

    private PetNewt(){
        setName("Pet Newt");
        setActionHunt("Take a revealed Rumour card from any other player into your hand\nChoose next player");
        setActionWitch("Take next turn");
        setConditionHunt("At least one player has already revealed a Rumor Card");
        setConditionWitch("");
    }
    private static PetNewt instance =null;
    public static PetNewt getInstance(){
        if (PetNewt.instance == null) {
            PetNewt.instance = new PetNewt();
        }
        return instance;
    }

    @Override
    public void actionWitch(Player player) {
        player.getGame().chooseNextPlayer(player);
    }

    /**
     * Permet d'activer l'effet Hunt de la carte PetNewt.
     * Le Joueur va devoir choisir parmis la liste de tous les joueurs de la partie celui à qui il souhaite prendre
     * une carte Rumeur déja révélée. La liste des Joueurs apparaitra avec leur
     * nombre de carte Rumeur révélée, puis la liste de ces cartes révélées ensuite.
     * @param player Représente le joueur qui a jouer la carte.
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

       player.addCardTo(player.getDeck(), player.chooseCardIn(player.chooseAPlayer().getTable()) );
    }

    @Override
    public boolean conditionWitch(Player player) {
        return true;
    }

    /**
     * Permet de vérifier si le Joueur peut jouer la carte PetNewt ou non.
     * On vérifie pour tout les Joueurs de la partie si au moins 1 dispose de carte Rumeur révélée.
     * @param player Représente le joueur qui a jouer la carte.
     * @return la réponse à la condition.
     */
    @Override
    public boolean conditionHunt(Player player) {
        System.out.println(getConditionHunt());
        for(Player p : player.getGame().getPlayers()){
            if(p.getTable().size()>0){
                return true;
            }
        }
        return false;
    }
}
