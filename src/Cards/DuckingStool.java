package Cards;
import Game_operator.*;

/**
 * Classe representant la carte DuckingStool.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class DuckingStool extends Card{

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private DuckingStool(){
        setName("DuckingStool");
        setActionHunt("Choose a player. They must reveal their identity or discard a card from their hand\n Witch you gain 1pt\nHunt you loose 1 pt");
        setActionWitch("Choose next player");
        setConditionHunt("");
        setConditionWitch("");
    }
    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static DuckingStool instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static DuckingStool getInstance(){
        if (DuckingStool.instance == null) {
            DuckingStool.instance = new DuckingStool();
        }
        return instance;
    }

    @Override
    public void actionWitch(Player player) {
        Player chosenPlayer = player.chooseAPlayer();
        player.getGame().chooseNextPlayer(chosenPlayer);
    }

    @Override
    public void actionHunt(Player player) {
        Player choosenPlayer;
        boolean containsWart = false;
        do{
            choosenPlayer = player.chooseAPlayer();
            containsWart = false;
            for (Card card : choosenPlayer.getTable()){
                if (card instanceof Broomstick){
                    System.out.println("Choose another player. You can't choose him he has a special card");
                    containsWart = true;
                    break;
                }
            }
        }while (containsWart);
        Player chosenPlayer =player.chooseAPlayer();
        System.out.println(chosenPlayer.getName() + "--> Choose :\n1- Reveal your identity\n2- Discard a card from your hand");
        int choice = s.nextInt();
        switch (choice){
            case 1:
                chosenPlayer.getIdentity().setRevealed(true);
                break;

            case 2:
                if (chosenPlayer.getDeck().isEmpty()){
                    chosenPlayer.getIdentity().setRevealed(true);
                }
                else {
                    chosenPlayer.discardCardFrom(chosenPlayer.getDeck(), chosenPlayer.chooseCardIn(chosenPlayer.getDeck()));
                }
        }
        if ((chosenPlayer.getIdentity().getRole() == Role.Witch) & (chosenPlayer.getIdentity().isRevealed())){
            player.addPoints(1);
        }
        else if ((chosenPlayer.getIdentity().getRole() == Role.Hunt) & (chosenPlayer.getIdentity().isRevealed())){
            player.addPoints(-1);
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
