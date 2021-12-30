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

    ImageIcon angryMob;ImageIcon blackCat;ImageIcon broomstick;ImageIcon cauldron;
    ImageIcon duckingStool;ImageIcon pointedHad;ImageIcon Theinquisition;ImageIcon angryMob;
    ImageIcon angryMob;ImageIcon angryMob;ImageIcon angryMob;ImageIcon angryMob;

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
        initImageIcon();
        setImageIcon(carteN1);
    }

    public void initImageIcon(){
        ImageIcon angryMob = new ImageIcon(this.getClass().getResource("AngryMob.png"));
    }

    public void setImageIcon(JLabel label){

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private void createUIComponents() {
    }
}
