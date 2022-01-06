package Cards;

import Game_operator.Player;

public class Broomstick extends Card{

    private Broomstick(){
        setName("Broomstick");
        setActionHunt("You choose next player");
        setActionWitch("You take next turn");
        setConditionHunt("");
        setConditionWitch("");
    }
    private static Broomstick instance =null;
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

//    @Override
//    public void actionHunt(Player player) {
//        System.out.println(getActionHunt());
//        Player chosenPlayer = player.chooseAPlayer();
//        player.getGame().chooseNextPlayer(chosenPlayer);
//    }

    @Override
    public boolean conditionWitch(Player player) {
        return true;
    }

    @Override
    public boolean conditionHunt(Player player) {
        return true;
    }
}
