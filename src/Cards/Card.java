package Cards;

public class Card {

    private boolean isRevealed;

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public void Card(){
        setRevealed(false);
    }

}
