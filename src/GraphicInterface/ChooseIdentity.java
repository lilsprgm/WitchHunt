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

/**
 * Interface graphique permettant de choisir une identite.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class ChooseIdentity extends JFrame{

    /**
     * Terminal affilie a l'interface graphique.
     * @author Lilsb et AGOUGILE
     */
    private final TerminalInterface myTerminal;

    private JPanel choosePanel;
    private JLabel actuelPlayer;
    private JButton witchButton;
    private JButton huntButton;

    /**
     * L'interface se met invisible des la creation.
     * @param terminalGiven Le terminal qui doit etre affilie a l'interface.
     * @author Lilsb et AGOUGILE
     */
    public ChooseIdentity(TerminalInterface terminalGiven){
        super("WITCHHUNT");
        myTerminal = terminalGiven;
        this.setContentPane(choosePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(false);
    }
    /**
     * Methode permettant d'afficher le nom du Joueur qui doit choisir son identite en activant les actions des boutons.
     * @author Lilsb et AGOUGILE
     */
    public void display(Player player){
        actuelPlayer.setText(player.getName());
        setVisible(true);
        allActions(player);
    }

    /**
     * Methode permettant de gerer toutes les actions disponibles sur l'interface.
     * @author Lilsb et AGOUGILE
     */
    public void allActions(Player player){
        witchButton.addActionListener(e -> {
            if(player.getIdentity().getRole()==null){
                dispose();
                myTerminal.interruptScan();
                player.setIdentity(1);
            }
        });
        huntButton.addActionListener(e -> {
            if(player.getIdentity().getRole()==null){
                dispose();
                myTerminal.interruptScan();
                player.setIdentity(2);
            }
        });
    }
}
