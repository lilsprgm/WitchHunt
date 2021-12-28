package GraphicInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Set;

public class SettingsInterface extends JFrame {

    private String[] playerType = {"Player", "Bot (hard)", "Bot (easy)"};
    private JLabel settingLabel;
    private JLabel nameLabel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JComboBox comboBox1 = new JComboBox(playerType);
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JPanel settingsPanel;

    private JButton registerButton;

    public JButton getRegisterButton() {
        return registerButton;
    }

  /*  public void initializeComboBox(){
        comboBox1.setEditable(true);
        comboBox2.setEditable(true);
        comboBox3.setEditable(true);
        comboBox4.setEditable(true);
        comboBox5.setEditable(true);
        comboBox6.setEditable(true);
        comboBox1.addItem(playerType);
        comboBox2.addItem(playerType);
        comboBox3.addItem(playerType);
        comboBox4.addItem(playerType);
        comboBox5.addItem(playerType);
        comboBox6.addItem(playerType);
        this.add(comboBox1);
        this.add(comboBox2);
        this.add(comboBox3);
        this.add(comboBox4);
        this.add(comboBox5);
        this.add(comboBox6);



    }*/

    public SettingsInterface(){
        super("WITCHHUNT");

        this.setContentPane(settingsPanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique
        this.setVisible(true);

    }

    public static void main(String[] args) {
        SettingsInterface settings = new SettingsInterface();

        //listener
        settings.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

}
