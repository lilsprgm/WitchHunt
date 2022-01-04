package Game_operator;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TerminalInterface implements Observer, Runnable{

    private final Game currentGame;
    private volatile UpdateCode flag = UpdateCode.ATTENTE;

    private volatile boolean interrupt = false;

    public TerminalInterface(Game game){
        currentGame = game;
        Thread t = new Thread(this);
        t.start();
    }
    public void interruptScan(){
        interrupt=true;
    }

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
                        input = Integer.parseInt(br.readLine());
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
                            } else break;
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
                            currentGame.setBots(chosenDifficulty - 1);
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

                        break;

                    case GAME_ROUND:
                        flag = UpdateCode.ATTENTE;
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
            case GAME_ROUND -> flag = UpdateCode.GAME_ROUND;
        }

    }
}
