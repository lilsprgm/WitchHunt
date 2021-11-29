package Cards;

import Game_operator.Player;

public interface Action {
    public void actionWitch(Player player);

    public void actionHunt(Player player);

    public boolean conditionWitch(Player player);

    public boolean conditionHunt(Player player);
}
