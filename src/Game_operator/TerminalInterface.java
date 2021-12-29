package Game_operator;
import GraphicInterface.SettingsInterface;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
                int nbPlayer;
                int nbBot;
                do {
                    System.out.print("Veuillez entrer le nombre de Joueur dans la Partie (6 joueurs max) : ");
                    nbPlayer = s.nextInt();
                    System.out.print("Veuillez entrer le nombre de Bot dans la Partie : ");
                    nbBot = s.nextInt();
                }while( (nbPlayer + nbBot) > 6 || (nbPlayer + nbBot) < 3 );
                currentGame.setNumberOfUser(nbPlayer, nbBot);
                break;

            case INIT_NAME_PLAYER:
                ArrayList<Player> p = new ArrayList<Player>();
                for(int i=1;i< currentGame.getNumberOfPlayer()+1;i++){
                    System.out.print("\nEntrez le nom du Joueur n°"+i+" :");
                    p.add(i-1,new PlayerIRL());
                    p.get(i-1).setName(s.nextLine());
                    p.get(i-1).setGame(currentGame);
                }
                currentGame.setPlayers(p);
                break;
            case INIT_DIFFICULTY_BOT:
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
