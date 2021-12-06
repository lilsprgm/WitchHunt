package Cards;

import Game_operator.Player;

public interface Action { // peut etre supprimer l'interface et mettre la classe card en abstrct pur la suite.
    public void actionWitch(Player player);

    public void actionHunt(Player player);

    public boolean conditionWitch(Player player);

    public boolean conditionHunt(Player player);
}
