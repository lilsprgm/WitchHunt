package Game_operator;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class TerminalInterface implements Observer, Runnable{

    private Game currentGame;
    private Scanner s = new Scanner(System.in);          // "s" Permet d'utiliser les méthodes de la classe Scanner.
    private volatile UpdateCode flag = UpdateCode.ATTENTE;

    private int nbPlayer;
    private int nbBot;

    public TerminalInterface(Game game){
        currentGame = game;
        Thread t = new Thread(this);
        t.start();
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
            ArrayList<Player> p = new ArrayList<Player>();
            int indexBot = currentGame.getPlayers().size()-currentGame.getNumberOfPlayer()+1 ;

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
                        if(verifCode(UpdateCode.INIT_NAME_PLAYER)){
                            System.out.print("\nEntrez le nom du Joueur n°"+i+" :");
                            p.add(i-1,new PlayerIRL());
                            p.get(i-1).setName(s.nextLine());
                            p.get(i-1).setGame(currentGame);
                        }else break;
                    }
                    if(verifCode(UpdateCode.INIT_NAME_PLAYER)){
                        currentGame.setPlayers(p);
                    }
                    break;

                case INIT_DIFFICULTY_BOT:
                    flag = UpdateCode.ATTENTE;
                    int chosenDifficulty;
                    System.out.print("\nDifficulté Bot n°"+indexBot+"\n1-Easy\n2-Hard\n");
                    chosenDifficulty = s.nextInt();
                    if(verifCode(UpdateCode.INIT_DIFFICULTY_BOT)){
                        currentGame.setBots(chosenDifficulty-1);
                    }
                    break;

                case ERROR_DIFFICULTY:
                    flag = UpdateCode.ATTENTE;
                    System.out.print("\nWrong Difficulty");
                    System.out.print("\nDifficulty Bot n°"+indexBot+"\n1-Easy\n2-Hard\n");
                    chosenDifficulty = s.nextInt();
                    if(verifCode(UpdateCode.ERROR_DIFFICULTY)){
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

        switch ((UpdateCode)arg){
            case INIT_NUMBER_PLAYER:
                flag = UpdateCode.INIT_NUMBER_PLAYER;
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
            case GAME_INIT_ROUND:
                flag = UpdateCode.GAME_INIT_ROUND;
                break;
            case GAME_ROUND:
                flag = UpdateCode.GAME_ROUND;
                break;
        }

    }
}
