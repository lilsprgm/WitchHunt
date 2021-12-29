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

    public TerminalInterface(Game game, SettingsInterface settings){
        currentGame = game;
        settings.setScanner(s);
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
        try {
            Robot robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        while(true){
            switch (flag){
                case INIT_NUMBER_PLAYER:
                    flag = UpdateCode.ATTENTE;
                    do{
                        System.out.print("\nVeuillez entrer le nombre de Joueur dans la Partie (6 joueurs max) : ");
                            nbPlayer = s.nextInt();
                    }while(nbPlayer > 6);
                    if(verifCode(UpdateCode.INIT_NUMBER_PLAYER)){
                        currentGame.setNumberOfPlayer(nbPlayer);
                    }
                    break;

                case INIT_NUMBER_BOT:
                    flag = UpdateCode.ATTENTE;
                    if(currentGame.getNumberOfPlayer()!=6){
                        System.out.print("\nVeuillez entrer le nombre de Bot dans la Partie : ");
                        nbBot = s.nextInt();
                        if(nbBot+currentGame.getNumberOfPlayer()>6 || nbBot+currentGame.getNumberOfPlayer()<3){
                            if(verifCode(UpdateCode.INIT_NUMBER_BOT)) {
                                currentGame.setUpdateCode(UpdateCode.INIT_NUMBER_PLAYER);
                            }
                        }
                        else{
                            if(verifCode(UpdateCode.INIT_NUMBER_BOT)){
                                currentGame.setNumberOfBot(nbBot);
                            }
                        }
                    }
                    else{
                        currentGame.setNumberOfBot(0);
                    }
                    break;

                case INIT_NAME_PLAYER:
                    flag = UpdateCode.ATTENTE;
                    ArrayList<Player> p = new ArrayList<Player>();
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
                    ArrayList<Player> bots = new ArrayList<Player>();
                    int chosenDifficulty;
                    for(int i=1;i<currentGame.getNumberOfBot()+1;i++){
                        if(verifCode(UpdateCode.INIT_DIFFICULTY_BOT)){
                            do{
                                System.out.print("\nDifficulté Bot n°"+i+"\n1-Easy\n2-Hard\n");
                                chosenDifficulty = s.nextInt();
                            }while(chosenDifficulty>2 || chosenDifficulty<1);
                            if(chosenDifficulty==1){
                                bots.add(i-1,new EasyModeBot());
                            }
                            if(chosenDifficulty==2){
                                bots.add(i-1,new HardModeBot());
                            }
                            bots.get(i-1).setName("Bot"+i);
                            bots.get(i-1).setGame(currentGame);
                        }
                    }
                    if(verifCode(UpdateCode.INIT_DIFFICULTY_BOT)){
                        currentGame.setBots(bots);
                    }
                    break;

                case GAME:

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

            case INIT_NUMBER_BOT:
                flag = UpdateCode.INIT_NUMBER_BOT;
                break;

            case INIT_NAME_PLAYER:
                flag = UpdateCode.INIT_NAME_PLAYER;
                break;
            case INIT_DIFFICULTY_BOT:
                flag = UpdateCode.INIT_DIFFICULTY_BOT;
                break;
            case GAME:
                flag = UpdateCode.GAME;
                break;
        }

    }
}
