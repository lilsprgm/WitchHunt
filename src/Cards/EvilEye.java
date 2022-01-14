package Cards;
 import  Game_operator.*;

/**
 * Classe representant la carte EvilEye.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class EvilEye extends Card{

    /**
     * Constructeur Singleton.
     * @author Lilsb et AGOUGILE
     */
    private EvilEye(){
        setName("EvilEye");
        setActionHunt("Choose next player\n At their turn they must accuse a player other than you if possible");
        setActionWitch(getActionHunt());
        setConditionHunt("");
        setConditionWitch("");
    }
    /**
     * Unique instance de la classe.
     * @author Lilsb et AGOUGILE
     */
    private static EvilEye instance =null;
    /**
     * Permet de creer une uniquer instance de la classe.
     * @return L'instance de la carte.
     * @author Lilsb et AGOUGILE
     */
    public static EvilEye getInstance(){
        if (EvilEye.instance == null) {
            EvilEye.instance = new EvilEye();
        }
        return instance;
    }

    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());
        Player chosenPlayer = player.chooseAPlayer();
        player.getGame().chooseNextPlayer(chosenPlayer);
        player.getGame().setProtectedPlayer(player);
    }


    @Override
    public void actionHunt(Player player) {
        Player chosenPlayer = player.chooseAPlayer();
        player.getGame().chooseNextPlayer(chosenPlayer);
        player.getGame().setProtectedPlayer(player);
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
