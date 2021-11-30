package Cards;

import Game_operator.Player;

public class HookedNose extends Card {

    public HookedNose(){
        setName("HookedNose");
        setActionHunt("Choose next player\n Before their turn take a card from its hand and add it to yours");
    }

    @Override
    public void actionWitch(Player player) {

    }

    @Override
    public void actionHunt() {

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
