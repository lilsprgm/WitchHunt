package GraphicInterface;

//import Game_operator.Observer;
import Game_operator.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javax.swing.*;

public class SettingsInterface extends JFrame implements Observer {

    private Game currentGame;
    private Scanner s;
    private JLabel settingLabel;

    private JPanel settingsPanel;
    private JLabel nameLabel;
    private int nbr = 0;
    private JLabel textBotDifficulty;
    private JButton registerButton;
    private JButton okButtonPlayer;
    private JComboBox<Integer> nbrPlayer;
    private JComboBox<Integer> nbrBot;
    private JTextField ErrorNbr;
    private JButton play;

    private final ArrayList<JComponent> comboBoxList = new ArrayList<>();
    private JComboBox<Integer> comboBox1;
    private JComboBox<Integer> comboBox2;
    private JComboBox<Integer> comboBox3;
    private JComboBox<Integer> comboBox4;
    private JComboBox<Integer> comboBox5;
    private JComboBox<Integer> comboBox6;

    private final ArrayList<JComponent> textPlayerList = new ArrayList<>();
    private JLabel textPlayer1;
    private JLabel textPlayer2;
    private JLabel textPlayer3;
    private JLabel textPlayer4;
    private JLabel textPlayer5;
    private JLabel textPlayer6;

    private final ArrayList<JComponent> textFieldsList = new ArrayList<>();
    private JTextField fieldP1;
    private JTextField fieldP2;
    private JTextField fieldP3;
    private JTextField fieldP4;
    private JTextField fieldP5;
    private JTextField fieldP6;

    private final ArrayList<JComponent> textBotList = new ArrayList<>();
    private JLabel textBot1;
    private JLabel textBot2;
    private JLabel textBot3;
    private JLabel textBot4;
    private JLabel textBot5;
    private JLabel textBot6;



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

    public void setScanner(Scanner newScanner){
        s=newScanner;
    }
    public <T> void fillArray(
            ArrayList<T> arrayElements, T elem1, T elem2, T elem3, T elem4, T elem5, T elem6){
        arrayElements.add(elem1);
        arrayElements.add(elem2);
        arrayElements.add(elem3);
        arrayElements.add(elem4);
        arrayElements.add(elem5);
        arrayElements.add(elem6);
    }
    public void setArrayVisibility(ArrayList<JComponent> list, boolean b, int nbr){
        for(int i=0;i<nbr;i++){
            list.get(i).setVisible(b);
        }
        for(int i=nbr;i<6;i++){
            list.get(i).setVisible(false);
        }
    }/*
    public void setArrayEnable(ArrayList<JComponent> list, boolean b, int nbr){
        for(int i=0;i<nbr;i++){
            list.get(i).setEnabled(b);
        }
        for(int i=nbr;i<6;i++){
            list.get(i).setEnabled(false);
        }
    }*/


    public SettingsInterface(Game game) {
        super("WITCHHUNT");
        currentGame = game;

        fillArray(comboBoxList, comboBox1, comboBox2, comboBox3, comboBox4, comboBox5, comboBox6);
        fillArray(textPlayerList, textPlayer1, textPlayer2, textPlayer3, textPlayer4, textPlayer5, textPlayer6);
        fillArray(textFieldsList, fieldP1, fieldP2, fieldP3, fieldP4, fieldP5, fieldP6);
        fillArray(textBotList, textBot1, textBot2, textBot3, textBot4, textBot5, textBot6);

        this.setContentPane(settingsPanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique
        this.setVisible(false);
        ErrorNbr.setVisible(false);
        setArrayVisibility(comboBoxList,false,6);
        setArrayVisibility(textFieldsList,false,6);
        setArrayVisibility(textPlayerList,false,6);
        setArrayVisibility(textBotList,false,6);
        nameLabel.setVisible(false);
        textBotDifficulty.setVisible(false);
        nbrPlayer.setEnabled(false);
        nbrBot.setEnabled(false);
        okButtonPlayer.setEnabled(false);

        allAction();
    }

    public void allAction() {

        okButtonPlayer.addActionListener(e -> {
            nbr = nbrPlayer.getSelectedIndex() + nbrBot.getSelectedIndex();
            if (nbr > 6 || nbr < 3) {
                ErrorNbr.setVisible(true);
            } else {
                ErrorNbr.setVisible(false);
                currentGame.setNumberOfPlayer(nbrPlayer.getSelectedIndex());
                currentGame.setNumberOfBot(nbrBot.getSelectedIndex());
            }

        });
        // Vérifier si l'utilisateur a bien rentré tout les noms.
        play.addActionListener(e -> {
            if (true){
                dispose();
                PlayInterface gamePlay = new PlayInterface();
                gamePlay.setVisible(true);
            }
            else{

            }
        });
    }
    @Override
    public void update(Observable o, Object arg) {
        switch ((UpdateCode)arg){
            case INIT_NUMBER_PLAYER:
                nbrPlayer.setEnabled(true);
                nbrBot.setEnabled(true);
                okButtonPlayer.setEnabled(true);
                break;

            case INIT_NAME_PLAYER:
                nbrPlayer.setEnabled(false);
                nbrBot.setEnabled(false);
                okButtonPlayer.setEnabled(false);ErrorNbr.setVisible(false);
                setArrayVisibility(comboBoxList,true,currentGame.getNumberOfBot());
                setArrayVisibility(textBotList,true,currentGame.getNumberOfBot());
                setArrayVisibility(textFieldsList,true,currentGame.getNumberOfPlayer());
                setArrayVisibility(textPlayerList,true,currentGame.getNumberOfPlayer());
                nameLabel.setVisible(true);textBotDifficulty.setVisible(true);
                break;
        }
    }
}
