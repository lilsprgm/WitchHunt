package Game_operator;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TerminalInterface implements Observer, Runnable{

    private final Game currentGame;
    private volatile UpdateCode flag = UpdateCode.ATTENTE;
    private volatile UpdateCode flagPlayer = UpdateCode.ATTENTE;
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

    public <T> T input(boolean writeAname) {
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
                        interrupt=false;
                        return (T) Integer.valueOf(input);
                    }
                }
                else{
                    if(br.ready()){
                        inputString = br.readLine();
                        return (T) String.valueOf(inputString);
                    }
                    if(interrupt){
                        interrupt=false;
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
                        int nbPlayer = input(false);
                        if (verifCode(UpdateCode.INIT_NUMBER_PLAYER)) {
                            currentGame.setNumberOfPlayer(nbPlayer);
                        }
                        break;

                    case ERROR_NUMBER:
                        flag = UpdateCode.ATTENTE;
                        System.out.print("Incorrect Number\n");
                        System.out.print("\nVeuillez entrer le nombre de Joueur dans la Partie (6 joueurs max) : ");
                        nbPlayer = input(false);
                        if (verifCode(UpdateCode.ERROR_NUMBER)) {
                            currentGame.setNumberOfPlayer(nbPlayer);
                        }
                        break;

                    case INIT_NUMBER_BOT:
                        flag = UpdateCode.ATTENTE;
                        System.out.print("\nVeuillez entrer le nombre de Bot dans la Partie : ");
                        int nbBot = input(false);
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
                                p.get(i - 1).setName(input(true));
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
                        chosenDifficulty = input(false);
                        if (verifCode(UpdateCode.INIT_DIFFICULTY_BOT)) {
                            currentGame.setBots(chosenDifficulty);
                        }
                        break;

                    case ERROR_DIFFICULTY:
                        flag = UpdateCode.ATTENTE;
                        System.out.print("\nWrong Difficulty");
                        System.out.print("\nDifficulty Bot n°" + indexBot + "\n1-Easy\n2-Hard\n");
                        chosenDifficulty = input(false);
                        if (verifCode(UpdateCode.ERROR_DIFFICULTY)) {
                            currentGame.setBots(chosenDifficulty);
                        }
                        break;

                    case GAME_INIT_ROUND:
                        switch (flagPlayer){
                            case CHOOSE_IDENTITY :
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.println("\n"+actualObservable.getName()+" Witch role do you want to take ?\n1- Witch\n2- Hunt\n--> ");
                                int role = input(false);
                                actualObservable.setIdentity(role);
                                break;
                        }
                        break;

                    case GAME_ROUND:
                        switch (flagPlayer){
                            case ACCUSE_OR_PLAY:
                                flagPlayer = UpdateCode.ATTENTE;
                                System.out.println("Your turn "+ actualObservable.getName());
                                System.out.println("What do you want to do ?\n1- Accuse someone \n2- Play a card");
                                int choice = input(false);
                                actualObservable.setChoice(choice);
                                break;

                            case IS_ACCUSED:
                                System.out.println(actualObservable.getName() + " you are accused !!!!" +
                                        "\nWhat do you want to do ?\n1- Reveal your identity\n2- Play a card (only a Witch action)");
                                choice = input(false);
                                actualObservable.setChoiceAccused(choice);
                                break;
                        }
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

            case GAME_INIT_ROUND -> flag = UpdateCode.GAME_INIT_ROUND;
            case CHOOSE_IDENTITY -> {
                actualObservable = (Player)o;
                flagPlayer = UpdateCode.CHOOSE_IDENTITY;
            }

            case GAME_ROUND -> flag = UpdateCode.GAME_ROUND;
            case ACCUSE_OR_PLAY -> {
                actualObservable = (Player)o;
                flagPlayer = UpdateCode.ACCUSE_OR_PLAY;
            }
            case IS_ACCUSED -> {
                actualObservable = (Player)o;
                flagPlayer = UpdateCode.IS_ACCUSED;
            }
        }

    }
}
