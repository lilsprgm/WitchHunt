package Game_operator;

import Cards.Card;
import Cards.Role;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TerminalInterface implements Observer, Runnable{

    private final Game currentGame;
    private volatile UpdateCode flag = UpdateCode.ATTENTE;
    private volatile UpdateCode flagPlayer = UpdateCode.ATTENTE;
    private volatile UpdateCode flagBot = UpdateCode.ATTENTE;
    private volatile Player actualObservable;
    private volatile boolean interrupt = false;

    public TerminalInterface(Game game){
        currentGame = game;
        Thread t = new Thread(this);
        t.start();
    }
    public void interruptScan(){
        while(interrupt){;}
        interrupt=true;
    }
    public boolean getInterrupt(){return interrupt;}

    public boolean verifCode(UpdateCode code){
        return currentGame.getUpdateCode() == code;
    }

    public <T> T input(boolean writeAname,boolean notChangeInterruptVar) {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        int input = 0;
        String inputString;
        while(true){
            try {
                if(!writeAname){
                    if(br.ready()){
                        String inputVerif = br.readLine();
                        if(inputVerif.equals("")){
                            inputVerif = "50";
                        }
                        input = Integer.valueOf(inputVerif);
                        return (T) Integer.valueOf(input);
                    }
                    if(interrupt){
                        if(!notChangeInterruptVar){interrupt=false;}
                        return (T) Integer.valueOf(input);
                    }
                }
                else{
                    if(br.ready()){
                        inputString = br.readLine();
                        return (T) String.valueOf(inputString);
                    }
                    if(interrupt){
                        if(!notChangeInterruptVar){interrupt=false;}
                        return (T) String.valueOf(input);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;}
    }

    @Override
    public void run() {
        while (true) {
            ArrayList<Player> p = new ArrayList<>();
            int indexBot = currentGame.getPlayers().size() - currentGame.getNumberOfPlayer() + 1;
                switch (flag) {
                    case INIT_NUMBER_PLAYER:
                        flag = UpdateCode.ATTENTE;
                        System.out.print("\nVeuillez entrer le nombre de Joueur dans la Partie (6 joueurs max) : ");
                        int nbPlayer = input(false,false);
                        if (verifCode(UpdateCode.INIT_NUMBER_PLAYER)) {
                            currentGame.setNumberOfPlayer(nbPlayer);
                        }
                        break;

                    case ERROR_NUMBER:
                        flag = UpdateCode.ATTENTE;
                        System.out.print("Incorrect Number\n");
                        System.out.print("\nVeuillez entrer le nombre de Joueur dans la Partie (6 joueurs max) : ");
                        nbPlayer = input(false,false);
                        if (verifCode(UpdateCode.ERROR_NUMBER)) {
                            currentGame.setNumberOfPlayer(nbPlayer);
                        }
                        break;

                    case INIT_NUMBER_BOT:
                        flag = UpdateCode.ATTENTE;
                        System.out.print("\nVeuillez entrer le nombre de Bot dans la Partie : ");
                        int nbBot = input(false,false);
                        if (verifCode(UpdateCode.INIT_NUMBER_BOT)) {
                            currentGame.setNumberOfBot(nbBot);
                        }
                        break;

                    case INIT_NAME_PLAYER:
                        flag = UpdateCode.ATTENTE;
                        for (int i = 1; i < currentGame.getNumberOfPlayer() + 1; i++) {
                            if (verifCode(UpdateCode.INIT_NAME_PLAYER)) {
                                System.out.print("\nEntrez le nom du Joueur n°" + i + " :");
                                p.add(i - 1, new PlayerIRL());
                                p.get(i - 1).setName(input(true,false));
                                p.get(i - 1).setGame(currentGame);
                            } else {
                                break;
                            }
                        }
                        if (verifCode(UpdateCode.INIT_NAME_PLAYER)) {
                            currentGame.setPlayers(p);
                        }
                        break;

                    case INIT_DIFFICULTY_BOT:
                        flag = UpdateCode.ATTENTE;
                        int chosenDifficulty;
                        System.out.print("\nDifficulté Bot n°" + indexBot + "\n1-Easy\n2-Hard\n");
                        chosenDifficulty = input(false,false);
                        if (verifCode(UpdateCode.INIT_DIFFICULTY_BOT)) {
                            currentGame.setBots(chosenDifficulty);
                        }
                        break;

                    case ERROR_DIFFICULTY:
                        flag = UpdateCode.ATTENTE;
                        System.out.print("\nWrong Difficulty");
                        System.out.print("\nDifficulty Bot n°" + indexBot + "\n1-Easy\n2-Hard\n");
                        chosenDifficulty = input(false,false);
                        if (verifCode(UpdateCode.ERROR_DIFFICULTY)) {
                            currentGame.setBots(chosenDifficulty);
                        }
                        break;

                    case GAME_INIT_ROUND:
                        switch (flagPlayer){
                            case CHOOSE_IDENTITY :
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.println("\n"+actualObservable.getName()+" Witch role do you want to take ?\n1- Witch\n2- Hunt\n--> ");
                                int role = input(false,true);
                                if(!interrupt){
                                    actualObservable.setIdentity(role);
                                }else{
                                    interrupt=false;}
                                break;
                        }
                        break;

                    case GAME_END_ROUND:
                        flag = UpdateCode.ATTENTE;
                        System.out.println("End of the round !!");
                        for(Player allp : currentGame.getPlayers()){
                            System.out.println(allp);
                        }
                        break;

                    case GAME_ROUND:
                        int choice;

                        switch (flagPlayer){
                            case ACCUSE_OR_PLAY:
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.println("\n\n\n\nYour turn "+ actualObservable.getName());
                                System.out.println("What do you want to do ?\n1- Accuse someone \n2- Play a card");
                                choice = input(false,false);
                                actualObservable.setChoice(choice);
                                break;

                            case ACCUSE:
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.println("Who do you want to accuse ?");
                                List<Player> listP = actualObservable.chooseThis(UpdateCode.ACCUSE); // On récupère la liste des joueurs accusables
                                for(int i=1;i< listP.size()+1;i++){
                                    System.out.println(i+"- "+listP.get(i-1).getName());
                                }
                                choice = input(false,false);
                                actualObservable.makeAchoice(choice-1,UpdateCode.ACCUSE);
                                break;

                            case PLAY_CARD_HUNT:
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.println("Wich Hunt card do you want to play");
                                for(int i=1; i<actualObservable.getDeck().size()+1;i++){
                                    System.out.printf(i+"- "+actualObservable.getDeck().get(i-1).getName()+"\n");
                                }
                                choice = input(false,false);
                                actualObservable.makeAchoice(choice-1,UpdateCode.PLAY_CARD_HUNT);
                                break;

                            case PLAY_CARD_WITCH:
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.println("Which Witch card do you want to play : ");
                                for(int i=1; i<actualObservable.getDeck().size()+1;i++){
                                    System.out.printf(i+"- "+actualObservable.getDeck().get(i-1).getName()+"\n");
                                }
                                choice = input(false,false);
                                actualObservable.makeAchoice(choice-1,UpdateCode.PLAY_CARD_WITCH);
                                break;

                            case EFFECT_CARD_HUNT:
                                flagPlayer=UpdateCode.ATTENTE;
                                System.out.println("\n"+actualObservable.getCardWichIsPlayed().getActionHunt());
                                System.out.println(actualObservable.getCardWichIsPlayed().getConditionHunt());
                                System.out.println("Play the Card ? \n1- Yes\n2- No");
                                choice = input(false,false);
                                actualObservable.makeAchoice(choice,UpdateCode.EFFECT_CARD_HUNT);
                                break;

                            case EFFECT_CARD_WITCH:
                                flagPlayer=UpdateCode.ATTENTE;
                                System.out.println("\n"+actualObservable.getCardWichIsPlayed().getActionWitch());
                                System.out.println(actualObservable.getCardWichIsPlayed().getConditionWitch());
                                System.out.println("Play the Card ? \n1- Yes\n2- No");
                                choice = input(false,false);
                                actualObservable.makeAchoice(choice,UpdateCode.EFFECT_CARD_WITCH);
                                break;

                            case END_CHOOSE_CARD:
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.printf("\nThe player " + actualObservable.getName()+" play the card "+ actualObservable.getCardWichIsPlayed()+".");
                                break;

                            case EMPTY_DECK:
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.println("\nYou can't play a/this Card");
                                actualObservable.setChoice(3);
                                break;

                            case IS_ACCUSED:
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.println(actualObservable.getName() + " you are accused !!!!" +
                                        "\nWhat do you want to do ?\n1- Reveal your identity\n2- Play a card (only a Witch action)");
                                choice = input(false,false);
                                actualObservable.setChoiceAccused(choice);
                                break;

                            case IS_REVEALED:
                                flagPlayer = UpdateCode.ATTENTE;
                                if(actualObservable.getIdentity().getRole()== Role.Witch){
                                    System.out.printf(actualObservable.getName()+" was a Witch\n ");
                                }
                                if(actualObservable.getIdentity().getRole()== Role.Hunt){
                                    System.out.printf(actualObservable.getName()+" was a Villager\n ");
                                }
                                break;

                            case END_PLAY:
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.println("\nThe player "+actualObservable.getName()+
                                        " finish his round.\nHe have "+actualObservable.getNumberOfPoints()+" point(s).");
                                break;

                            case ATTENTE:
                                break;
                        }
                        switch (flagBot){
                            case BOT_PLAY_WITCH -> {
                                flagBot=UpdateCode.ATTENTE;
                                System.out.println(actualObservable.getName()+" choose to play a Witch Card");
                            }
                            case BOT_PLAY_HUNT -> {
                                flagBot=UpdateCode.ATTENTE;
                                System.out.println(actualObservable.getName()+" choose to play a Hunt Card");
                            }
                            case BOT_ACCUSE -> {
                                flagBot=UpdateCode.ATTENTE;
                                System.out.println(actualObservable.getName()+" choose to accuse "+actualObservable.getAccusedPlayer().getName());
                            }
                        }
                    break;

                    case END_GAME:
                        flag = UpdateCode.ATTENTE;
                        for (Player allp : currentGame.getPlayers()){
                            if (allp.getNumberOfPoints() >= 5){
                                System.out.println("The WINNER is "+allp.getName());
                            }
                        }
                        System.out.println("End Of The Game !");
                        break;

                    case ATTENTE:
                        break;
                }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        switch ((UpdateCode) arg) {
            case INIT_NUMBER_PLAYER -> flag = UpdateCode.INIT_NUMBER_PLAYER;
            case ERROR_NUMBER -> flag = UpdateCode.ERROR_NUMBER;
            case ERROR_DIFFICULTY -> flag = UpdateCode.ERROR_DIFFICULTY;
            case INIT_NUMBER_BOT -> flag = UpdateCode.INIT_NUMBER_BOT;
            case INIT_NAME_PLAYER -> flag = UpdateCode.INIT_NAME_PLAYER;
            case INIT_DIFFICULTY_BOT -> flag = UpdateCode.INIT_DIFFICULTY_BOT;

            case END_GAME -> flag = UpdateCode.END_GAME;
            case GAME_INIT_ROUND -> flag = UpdateCode.GAME_INIT_ROUND;
            case CHOOSE_IDENTITY -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.CHOOSE_IDENTITY;
            }

            case GAME_ROUND -> flag = UpdateCode.GAME_ROUND;
            case GAME_END_ROUND -> flag = UpdateCode.GAME_END_ROUND;
            case ACCUSE_OR_PLAY -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.ACCUSE_OR_PLAY;
            }
            case ACCUSE -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.ACCUSE;
            }
            case PLAY_CARD_HUNT -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.PLAY_CARD_HUNT;
            }
            case PLAY_CARD_WITCH -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.PLAY_CARD_WITCH;
            }

            case EFFECT_CARD_HUNT -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.EFFECT_CARD_HUNT;
            }
            case EFFECT_CARD_WITCH -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.EFFECT_CARD_WITCH;
            }
            case END_CHOOSE_CARD -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.END_CHOOSE_CARD;
            }
            case END_PLAY -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.END_PLAY;
            }
            case IS_ACCUSED -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.IS_ACCUSED;
            }
            case IS_REVEALED -> {
                actualObservable = (Player) o;
                flagPlayer = UpdateCode.IS_REVEALED;
            }
            case BOT_PLAY_WITCH -> {
                actualObservable = (Player) o;
                flagBot = UpdateCode.BOT_PLAY_WITCH;
            }
            case BOT_PLAY_HUNT -> {
                actualObservable = (Player) o;
                flagBot = UpdateCode.BOT_PLAY_HUNT;
            }
            case BOT_ACCUSE -> {
                actualObservable = (Player) o;
                flagBot = UpdateCode.BOT_ACCUSE;
            }
        }
    }
}
