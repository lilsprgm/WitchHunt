package Game_operator;
import GraphicInterface.SettingsInterface;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class TerminalInterface implements Observer{


    private Game currentGame;
    Scanner s = new Scanner(System.in);          // "s" Permet d'utiliser les méthodes de la classe Scanner.

    public TerminalInterface(Game game, SettingsInterface settings){
        currentGame = game;
        settings.setScanner(s);
    }

    @Override
    public void update(Observable o, Object arg) {

        switch ((UpdateCode)arg){
            case INIT_NUMBER_PLAYER:
                System.out.print("Veuillez entrer le nombre de Joueur dans la Partie (6 joueurs max) : ");
                int val = s.nextInt();
                currentGame.setNumberOfPlayer(val) ;
                System.out.print("Veuillez entrer le nombre de Bot dans la Partie : ");
                val = s.nextInt();
                currentGame.setNumberOfBot(val);
                s.nextLine();
                break;

            case INIT_NAME_PLAYER:
                for(int i=1;i< currentGame.getNumberOfPlayer()+1;i++){
                    System.out.print("Entrez le nom du Joueur n°"+i+" :");
                    currentGame.getPlayers().add(i-1,new PlayerIRL());
                    currentGame.getPlayers().get(i-1).setName(s.nextLine());
                    currentGame.getPlayers().get(i-1).setGame(currentGame);
                }
                for(int i=1;i<currentGame.getNumberOfBot()+1;i++){
                    System.out.print("Difficulté Bot n°"+i+"\n1-Easy\n2-Hard\n");
                    int chosenDifficulty = s.nextInt();
                    while(chosenDifficulty>2 || chosenDifficulty<1){
                        chosenDifficulty = s.nextInt();
                    }
                    if(chosenDifficulty==1){
                        currentGame.getPlayers().add(i-1,new EasyModeBot());
                    }
                    if(chosenDifficulty==2){
                        currentGame.getPlayers().add(i-1,new HardModeBot());
                    }
                    currentGame.getPlayers().get(i-1).setName("Bot"+i);
                    currentGame.getPlayers().get(i-1).setGame(currentGame);
                }
                break;

        }

    }
}
