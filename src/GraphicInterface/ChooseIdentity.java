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

    private TerminalInterface myTerminal;

    private JPanel choosePanel;
    private JLabel actuelPlayer;
    private JButton witchButton;
    private JButton huntButton;

    public ChooseIdentity(TerminalInterface terminalGiven){
        super("WITCHHUNT");
        myTerminal = terminalGiven;
        this.setContentPane(choosePanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique
        //this.setSize(800,1000);
        this.setVisible(false);
    }

    public void display(Player player){
        actuelPlayer.setText(player.getName());
        setVisible(true);
        allActions(player);
    }

    public void allActions(Player player){
        witchButton.addActionListener(e -> {
            dispose();
            myTerminal.interruptScan();
            player.setIdentity(1);
        });
        huntButton.addActionListener(e -> {
            dispose();
            myTerminal.interruptScan();
            player.setIdentity(2);
        });
    }
}
