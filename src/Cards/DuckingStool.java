package Cards;
import Game_operator.*;
public class DuckingStool extends Card{

    public DuckingStool(){
        setName("DuckingStool");
        setActionHunt("Choose a player. They must reveal their identity or discard a card from their hand\n Witch you gain 1pt\nHunt you loose 1 pt");
        setConditionWitch("Choose next player");
    }

    @Override
    public void actionWitch(Player player) {
        System.out.println(getActionWitch());
        Player chosenPlayer = player.chooseAPlayer();
        player.getGame().chooseNextPlayer(chosenPlayer);
    }

    @Override
    public void actionHunt(Player player) {
        System.out.println(getActionHunt());
        Player choosenPlayer;
        // permet de s'assurer qu'il ne choisit pas un joueur qui a joué une carte wart
        boolean containsWart = false;
        do{
            choosenPlayer = player.chooseAPlayer();
            containsWart = false;
            for (Card card : choosenPlayer.getTable()){
                if (card instanceof Broomstick){
                    System.out.println("Choose another player. You can't choose him he has a special card");
                    containsWart = true;
                    break;
                }
            }
        }while (containsWart);
        Player chosenPlayer =player.chooseAPlayer();
        System.out.println(chosenPlayer.getName() + "--> Choose :\n1- Reveal your identity\n2- Discard a card from your hand");
        int choice = s.nextInt();
        switch (choice){
            case 1:
                chosenPlayer.getIdentity().setRevealed(true);
                break;

            case 2:
                if (chosenPlayer.getDeck().isEmpty()){// pour résoudre le problème du cas ou le joueur n'a plus de carte dans sa main
                    chosenPlayer.getIdentity().setRevealed(true);
                }
                else {
                    chosenPlayer.discardCardFrom(chosenPlayer.getDeck(), chosenPlayer.chooseCardIn(chosenPlayer.getDeck()));
                }
// attribution point
        }
        if ((chosenPlayer.getIdentity().getRole() == Role.Witch) & (chosenPlayer.getIdentity().isRevealed())){
            player.addPoints(1);
        }
        else if ((chosenPlayer.getIdentity().getRole() == Role.Hunt) & (chosenPlayer.getIdentity().isRevealed())){
            player.addPoints(-1);
        }
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
