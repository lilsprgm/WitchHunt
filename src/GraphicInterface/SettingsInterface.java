package GraphicInterface;

//import Game_operator.Observer;
import java.awt.event.*;
import java.sql.SQLOutput;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import java.util.Set;

public class SettingsInterface extends JFrame implements Observer {

    private String[] playerType = {"Player", "Bot (hard)", "Bot (easy)"};
    private JLabel settingLabel;
    private JLabel nameLabel;
    private JTextField textField1;
    private JPanel settingsPanel;

    private int nbr = 0;

    private JButton registerButton;
    private JButton OKButton;
    private JComboBox NbrPlayer;
    private JComboBox NbrBot;
    private JTextField ErrorNbr;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;

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
  @Override
  public void update(Observable o, Object arg) {

  }

    public SettingsInterface(){
        super("WITCHHUNT");
        this.setContentPane(settingsPanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique
        this.setVisible(false);
        ErrorNbr.setVisible(false);
        allAction();
    }

    public void allAction(){

        OKButton.addActionListener(e -> {

            nbr = NbrPlayer.getSelectedIndex() + NbrBot.getSelectedIndex();
            System.out.println(nbr);
            if ( nbr > 6 || nbr < 3) {
                System.out.println("oui");
                ErrorNbr.setVisible(true);
            }
            else{
                ErrorNbr.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
    }

}
