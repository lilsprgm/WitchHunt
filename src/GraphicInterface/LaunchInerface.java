package GraphicInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import  Game_operator.*;

public class LaunchInerface extends  JFrame {

    private JButton STARTPLAYButton;
    private JPanel launchPanel;
    private JLabel WicthHuntLabel;


    public LaunchInerface(){ // constructeur avec titre de l'app

        super("WITCHHUNT");
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
                SettingsInterface gameSettings = new SettingsInterface();
                gameSettings.setVisible(true);
            }
        });
    }

}
