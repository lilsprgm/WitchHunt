package Cards;

import Game_operator.Player;

/**
 * Classe representant la carte BroomStick.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class Broomstick extends Card{

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private Broomstick(){
        setName("Broomstick");
        setActionHunt("You choose next player");
        setActionWitch("You take next turn");
        setConditionHunt("");
        setConditionWitch("");
    }
    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static Broomstick instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static Broomstick getInstance(){
        if (Broomstick.instance == null) {
            Broomstick.instance = new Broomstick();
        }
        return instance;
    }

    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());
    }

    @Override
    public void actionHunt(Player player) {
        Player chosenPlayer = player.chooseAPlayer();
        player.getGame().chooseNextPlayer(chosenPlayer);
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
