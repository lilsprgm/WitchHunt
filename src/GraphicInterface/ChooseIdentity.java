package GraphicInterface;

import javax.swing.*;
import Cards.*;
import Game_operator.Game;
import Game_operator.Player;
import Game_operator.TerminalInterface;
import Game_operator.UpdateCode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class ChooseIdentity extends JFrame{

    private Player currentPlayer;
    private TerminalInterface myTerminal;

    private JPanel choosePanel;
    private JLabel actuelPlayer;
    private JButton witchButton;
    private JButton huntButton;

    public ChooseIdentity(Player player, TerminalInterface terminalGiven){
        super("WITCHHUNT");
        currentPlayer = player;
        myTerminal = terminalGiven;
        this.setContentPane(choosePanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique
        //this.setSize(800,1000);
        this.setVisible(true);
        actuelPlayer.setText(player.getName());
        allActions();


    }

    public void allActions(){
        witchButton.addActionListener(e -> {
            myTerminal.interruptScan();
            currentPlayer.setIdentity(1);
            this.dispose();
        });
        huntButton.addActionListener(e -> {
            myTerminal.interruptScan();
            currentPlayer.setIdentity(2);
            this.dispose();
        });
    }
}
