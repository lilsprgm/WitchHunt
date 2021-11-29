package Cards;

public class Card implements Action{ //(extends Observable)
    private boolean revealed;
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isRevealed() {
        // Automatically generated method. Please do not modify this code.
        return this.revealed;
    }

    public void setRevealed(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.revealed = value;
    }

    public String toString (){
        return this.name;
    }


    public static void main(String[] args) {

    }

    @Override
    public void actionWitch() {

    }

    @Override
    public void actionHunt() {

    }

    @Override
    public boolean conditionWitch() {
        return false;
    }

    @Override
    public boolean conditionHunt() {
        return false;
    }

    @Override
    public void playActionCard(Player play) {

    }
}
