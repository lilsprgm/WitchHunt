package Cards;

public class Card  { //(extends Observable)
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
        Card c = new AngryMob();
        Card c2 = new BlackCat();
        System.out.println(c);
        System.out.println(c2);
    }
}
