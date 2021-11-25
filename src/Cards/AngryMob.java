package Cards;

public class AngryMob extends Card implements Action{

    public AngryMob(){
        setName("AngryMob");
    }

    @Override
    public void actionWitch() {
        System.out.println("You take next turn");
    }

    @Override
    public void actionHunt() {
        System.out.println("");
    }

    @Override
    public boolean conditionWitch() {
        return true;
    }

    @Override
    public boolean conditionHunt() {

    }
}
