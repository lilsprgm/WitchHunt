package Cards;

public interface Action {
    public void actionWitch();

    public void actionHunt();

    public boolean conditionWitch();

    public boolean conditionHunt();

    public void playActionCard();

    void playActionCard(Player play);
}
