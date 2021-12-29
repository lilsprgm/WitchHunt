package GraphicInterface;

//import Game_operator.Observer;
import Game_operator.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javax.swing.*;

public class SettingsInterface extends JFrame implements Observer {

    private final Game currentGame;
    private JLabel settingLabel;

    private JPanel settingsPanel;
    private JLabel nameLabel;
    private int nbr = 0;
    private JLabel textBotDifficulty;
    private JButton registerButton;
    private JButton okButtonPlayer;
    private JButton okButtonBOT;
    private JLabel textNbrBot;
    private JComboBox<Integer> nbrPlayer;
    private JComboBox<Integer> nbrBot;
    private JTextField ErrorNbr;
    private JButton play;

    private final ArrayList<JComboBox> comboBoxList = new ArrayList<JComboBox>();
    private JComboBox<Integer> comboBox1;
    private JComboBox<Integer> comboBox2;
    private JComboBox<Integer> comboBox3;
    private JComboBox<Integer> comboBox4;
    private JComboBox<Integer> comboBox5;
    private JComboBox<Integer> comboBox6;

    private final ArrayList<JLabel> textPlayerList = new ArrayList<JLabel>();
    private JLabel textPlayer1;
    private JLabel textPlayer2;
    private JLabel textPlayer3;
    private JLabel textPlayer4;
    private JLabel textPlayer5;
    private JLabel textPlayer6;

    private final ArrayList<JTextField> textFieldsList = new ArrayList<JTextField>();
    private JTextField fieldP1;
    private JTextField fieldP2;
    private JTextField fieldP3;
    private JTextField fieldP4;
    private JTextField fieldP5;
    private JTextField fieldP6;

    private final ArrayList<JLabel> textBotList = new ArrayList<>();
    private JLabel textBot1;
    private JLabel textBot2;
    private JLabel textBot3;
    private JLabel textBot4;
    private JLabel textBot5;
    private JLabel textBot6;
    private JButton submitButton;

    public <T> void fillArray(
            ArrayList<T> arrayElements, T elem1, T elem2, T elem3, T elem4, T elem5, T elem6){
        arrayElements.add(elem1);
        arrayElements.add(elem2);
        arrayElements.add(elem3);
        arrayElements.add(elem4);
        arrayElements.add(elem5);
        arrayElements.add(elem6);
    }
    public void setArrayEnable(ArrayList<JTextField> list, boolean b, int nbr){
        for(int i=0;i<nbr;i++){
            list.get(i).setEnabled(b);
        }
    }
    public SettingsInterface(Game game) {
        super("WITCHHUNT");
        currentGame = game;

        this.setContentPane(settingsPanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique

        fillArray(comboBoxList, comboBox1, comboBox2, comboBox3, comboBox4, comboBox5, comboBox6);
        fillArray(textPlayerList, textPlayer1, textPlayer2, textPlayer3, textPlayer4, textPlayer5, textPlayer6);
        fillArray(textFieldsList, fieldP1, fieldP2, fieldP3, fieldP4, fieldP5, fieldP6);
        fillArray(textBotList, textBot1, textBot2, textBot3, textBot4, textBot5, textBot6);

        this.setVisible(false);
        ErrorNbr.setVisible(false);
        for(int i=0;i<6;i++){
            comboBoxList.get(i).setVisible(false);
            textFieldsList.get(i).setVisible(false);
            textPlayerList.get(i).setVisible(false);
            textBotList.get(i).setVisible(false);
        }

        submitButton.setVisible(false);
        nameLabel.setVisible(false);
        textBotDifficulty.setVisible(false);

        nbrBot.setVisible(false);
        okButtonBOT.setVisible(false);
        textNbrBot.setVisible(false);

        allAction();
    }

    public void allAction() {
        okButtonPlayer.addActionListener(e -> {
            currentGame.setNumberOfPlayer(nbrPlayer.getSelectedIndex());
        });
        okButtonBOT.addActionListener(e -> {
            nbr = currentGame.getNumberOfPlayer() + nbrBot.getSelectedIndex();
            if (nbr > 6 || nbr < 3) {
                ErrorNbr.setVisible(true);
                nbrPlayer.setEnabled(true);
                okButtonPlayer.setEnabled(true);
            } else {
                ErrorNbr.setVisible(false);
                currentGame.setNumberOfBot(nbrBot.getSelectedIndex());
            }
        });
        // Vérifier si l'utilisateur a bien rentré tout les noms.
        submitButton.addActionListener(e -> {
            ArrayList<Player> p = new ArrayList<Player>();
            boolean verif = true;
            for(int i=0;i<currentGame.getNumberOfPlayer();i++){
                if(textFieldsList.get(i).getText().isEmpty()){
                    textFieldsList.get(i).setBackground(Color.red);
                    verif = false;
                }
                else{
                    textFieldsList.get(i).setBackground(Color.green);
                    p.add(i, new PlayerIRL());
                    p.get(i).setName(textFieldsList.get(i).getText());
                    p.get(i).setGame(currentGame);
                }
            }
            if(verif){
                currentGame.setPlayers(p);
            }
        });

        play.addActionListener(e -> {
            ArrayList<Player> bots = new ArrayList<Player>();
            for(int i=0;i<currentGame.getNumberOfBot();i++){
                if(comboBoxList.get(i).getSelectedIndex()==1){
                    bots.add(i,new EasyModeBot());
                }
                if(comboBoxList.get(i).getSelectedIndex()==2){
                    bots.add(i,new HardModeBot());
                }
            }
            currentGame.setBots(bots);
            dispose();
            PlayInterface gamePlay = new PlayInterface();
            currentGame.addObserver(gamePlay);
            gamePlay.setVisible(true);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((UpdateCode) arg) {
            case INIT_NUMBER_PLAYER -> {
                nbrPlayer.setEnabled(true);
                okButtonPlayer.setEnabled(true);
                nbrBot.setVisible(false);
                okButtonBOT.setVisible(false);
                textNbrBot.setVisible(false);
            }
            case INIT_NUMBER_BOT -> {
                nbrPlayer.setSelectedIndex(currentGame.getNumberOfPlayer());
                nbrPlayer.setEnabled(false);
                okButtonPlayer.setEnabled(false);
                nbrBot.setVisible(true);
                okButtonBOT.setVisible(true);
                textNbrBot.setVisible(true);
            }
            case INIT_NAME_PLAYER -> {
                nbrBot.setSelectedIndex(currentGame.getNumberOfBot());
                nbrBot.setEnabled(false);
                okButtonBOT.setEnabled(false);
                nameLabel.setVisible(true);
                submitButton.setVisible(true);
                for (int i = 0; i < 6; i++) {
                    textFieldsList.get(i).setVisible((i < currentGame.getNumberOfPlayer()));
                    textPlayerList.get(i).setVisible((i < currentGame.getNumberOfPlayer()));
                }
            }
            case INIT_DIFFICULTY_BOT -> {
                for (int i = 0; i < currentGame.getNumberOfPlayer(); i++) {
                    textFieldsList.get(i).setText(currentGame.getPlayers().get(i).getName());
                    textFieldsList.get(i).setBackground(Color.green);
                }
                setArrayEnable(textFieldsList, false, currentGame.getNumberOfPlayer());
                textBotDifficulty.setVisible(true);
                for (int i = 0; i < 6; i++) {
                    comboBoxList.get(i).setVisible((i < currentGame.getNumberOfBot()));
                    textBotList.get(i).setVisible((i < currentGame.getNumberOfBot()));
                }
            }
        }
    }
}
