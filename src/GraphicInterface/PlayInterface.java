package GraphicInterface;

import Game_operator.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PlayInterface extends JFrame implements Observer {

    private final Game currentGame;
    private JLabel tourPlayer;
    private JPanel playPanel;
    private JTextField textField1;

    private JLabel carteN1;
    private JLabel carteN2;
    private JLabel carteN3;
    private JLabel carteN4;


    public PlayInterface(Game game){
        super("WITCHHUNT");
        currentGame=game;
        this.setContentPane(playPanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique
        this.setVisible(true);
    }



    @Override
    public void update(Observable o, Object arg) {

    }

    private void createUIComponents() {



        ImageIcon myIcon = new ImageIcon(this.getClass().getResource("/Ressources/AngryMob.png"));
        carteN1 = new JLabel(myIcon);
    }
}
