package GraphicInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import  Game_operator.*;

/**
 * L'interface Graphique de lancement du jeu.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class LaunchInterface extends  JFrame {

    private JButton STARTPLAYButton;
    private JPanel launchPanel;
    private JLabel WicthHuntLabel;
    private final SettingsInterface mySettingsInterface;

    /**
     * Le constructeur de l'interface de lancement visible des la creation.
     * @param gameSettings L'interface d'initialisation "Settings" a relie.
     * @author Lilsb et AGOUGILE
     */
    public LaunchInterface(SettingsInterface gameSettings){

        super("WITCHHUNT");
        mySettingsInterface = gameSettings;
        this.setContentPane(launchPanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique
        this.setVisible(true);
        allAction();

    }
    /**
     * Methode permettant de gerer toutes les actions disponibles sur l'interface.
     * @author Lilsb et AGOUGILE
     */
    public void allAction(){
        STARTPLAYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                mySettingsInterface.setVisible(true);
            }
        });
    }
}
