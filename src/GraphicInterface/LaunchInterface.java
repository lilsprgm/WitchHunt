package GraphicInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import  Game_operator.*;

public class LaunchInterface extends  JFrame {

    private JButton STARTPLAYButton;
    private JPanel launchPanel;
    private JLabel WicthHuntLabel;
    private SettingsInterface mySettingsInterface;

    public LaunchInterface(SettingsInterface gameSettings){ // constructeur avec titre de l'app

        super("WITCHHUNT");
        mySettingsInterface = gameSettings;
        this.setContentPane(launchPanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique
        this.setVisible(true);
        allAction();
    }
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
