package Game_operator;
import GraphicInterface.SettingsInterface;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class TerminalInterface implements Observer, Runnable{

    private Game currentGame;
    Scanner s = new Scanner(System.in);          // "s" Permet d'utiliser les méthodes de la classe Scanner.
    UpdateCode flag = UpdateCode.ATTENTE;
    private Thread t = new Thread(this);

    private int nbPlayer;
    private int nbBot;

    public TerminalInterface(Game game){
        currentGame = game;
    }

    public boolean verifCode(UpdateCode code){
        if(currentGame.getUpdateCode()==code){
            return true;
        }
        else{
            System.out.println("Terminal en retard, entrée non prise en compte");
            return false;
        }
    }

    @Override
    public void run() {
        while(true){
            ArrayList<Integer> bots = new ArrayList<Integer>();
            ArrayList<Player> p = new ArrayList<Player>();
            switch (flag){
                case INIT_NUMBER_PLAYER:
                    flag = UpdateCode.ATTENTE;
                    System.out.print("\nVeuillez entrer le nombre de Joueur dans la Partie (6 joueurs max) : ");
                    nbPlayer = s.nextInt();
                    if(verifCode(UpdateCode.INIT_NUMBER_PLAYER)){
                        currentGame.setNumberOfPlayer(nbPlayer);
                    }
                    break;

                case ERROR_NUMBER:
                    flag = UpdateCode.ATTENTE;
                    System.out.print("Incorrect Number\n");
                    System.out.print("\nVeuillez entrer le nombre de Joueur dans la Partie (6 joueurs max) : ");
                    nbPlayer = s.nextInt();
                    if(verifCode(UpdateCode.ERROR_NUMBER)){
                        currentGame.setNumberOfPlayer(nbPlayer);
                    }
                    break;

                case INIT_NUMBER_BOT:
                    flag = UpdateCode.ATTENTE;
                    System.out.print("\nVeuillez entrer le nombre de Bot dans la Partie : ");
                    nbBot = s.nextInt();
                    if(verifCode(UpdateCode.INIT_NUMBER_BOT)){
                        currentGame.setNumberOfBot(nbBot);
                    }
                    break;

                case INIT_NAME_PLAYER:
                    flag = UpdateCode.ATTENTE;
                    s.nextLine();
                    for(int i=1;i< currentGame.getNumberOfPlayer()+1;i++){
                        System.out.print("\nEntrez le nom du Joueur n°"+i+" :");
                        p.add(i-1,new PlayerIRL());
                        p.get(i-1).setName(s.nextLine());
                        p.get(i-1).setGame(currentGame);
                    }
                    if(verifCode(UpdateCode.INIT_NAME_PLAYER)){
                        currentGame.setPlayers(p);
                    }
                    break;

                case INIT_DIFFICULTY_BOT:
                    flag = UpdateCode.ATTENTE;
                    int chosenDifficulty;
                    for(int i=1;i<currentGame.getNumberOfBot()+1;i++){
                        if(verifCode(UpdateCode.INIT_DIFFICULTY_BOT)){
                            System.out.print("\nDifficulté Bot n°"+i+"\n1-Easy\n2-Hard\n");
                            chosenDifficulty = s.nextInt();
                            bots.add(i-1,chosenDifficulty);
                        }
                    }
                    if(verifCode(UpdateCode.INIT_DIFFICULTY_BOT)){
                        currentGame.setBots(bots);
                    }
                    break;

                case ERROR_DIFFICULTY:
                    flag = UpdateCode.ATTENTE;
                    for(int i=currentGame.getPlayers().size()-currentGame.getNumberOfBot()+1;i<currentGame.getNumberOfBot()+1;i++){
                        if(verifCode(UpdateCode.INIT_DIFFICULTY_BOT)){
                            System.out.print("\nWrong Difficulty");
                            System.out.print("\nDifficulty Bot n°"+i+"\n1-Easy\n2-Hard\n");
                            chosenDifficulty = s.nextInt();
                            bots.set(i-1,chosenDifficulty);
                        }
                    }
                    if(verifCode(UpdateCode.ERROR_DIFFICULTY)){
                        currentGame.setBots(bots);
                    }
                    break;

                case GAME_ROUND:
                    //System.out.println("Game Round");
                    break;

                case ATTENTE:
                    break;
            }
        }
    }
    @Override
    public void update(Observable o, Object arg) {

        switch ((UpdateCode)arg){
            case INIT_NUMBER_PLAYER:
                flag = UpdateCode.INIT_NUMBER_PLAYER;
                if(!t.isAlive()) {
                    t.start();
                }
                break;

            case ERROR_NUMBER:
                flag = UpdateCode.ERROR_NUMBER;
                break;

            case ERROR_DIFFICULTY:
                flag = UpdateCode.ERROR_DIFFICULTY;
                break;

            case INIT_NUMBER_BOT:
                flag = UpdateCode.INIT_NUMBER_BOT;
                break;

            case INIT_NAME_PLAYER:
                flag = UpdateCode.INIT_NAME_PLAYER;
                break;
            case INIT_DIFFICULTY_BOT:
                flag = UpdateCode.INIT_DIFFICULTY_BOT;
                break;
            case GAME_ROUND:
                flag = UpdateCode.GAME_ROUND;
                break;
        }

    }
}
