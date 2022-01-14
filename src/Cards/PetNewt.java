package Cards;

import Game_operator.Player;

/**
 * Classe representant la carte PetNewt.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class PetNewt extends Card {

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private PetNewt(){
        setName("Pet Newt");
        setActionHunt("Take a revealed Rumour card from any other player into your hand\nChoose next player");
        setActionWitch("Take next turn");
        setConditionHunt("At least one player has already revealed a Rumor Card");
        setConditionWitch("");
    }

    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static PetNewt instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
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
