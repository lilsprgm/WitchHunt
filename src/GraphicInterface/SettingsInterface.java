package GraphicInterface;

//import Game_operator.Observer;
import Cards.Card;
import Game_operator.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * L'interface Graphique d'initialisation des Joueurs et de la difficulte des bots.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class SettingsInterface extends JFrame implements Observer{

    /**
     * La partie que la vue graphique observe.
     * @author Lilsb et AGOUGILE
     */
    private final Game currentGame;
    /**
     * L'interface de Jeu relie a la Partie.
     * @author Lilsb et AGOUGILE
     */
    private final PlayInterface playGame;
    /**
     * Le terminal relie a la Partie.
     * @author Lilsb et AGOUGILE
     */
    private TerminalInterface myTerminal;
    private JLabel settingLabel;
    /**
     * L'interface de choix d'identite relie a la Partie.
     * @author Lilsb et AGOUGILE
     */
    private ChooseIdentity chooseAnIdentity;

    private JPanel settingsPanel;
    private JLabel nameLabel;
    private final int nbr = 0;
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

    /**
     * Methode permettant de remplir une ArrayList de 6 elements.
     * @param arrayElements La liste qui doit etre remplie.
     * @param elem1 L'element 0 de la liste.
     * @param elem2 L'element 1 de la liste.
     * @param elem3 L'element 2 de la liste.
     * @param elem5 L'element 3 de la liste.
     * @param elem5 L'element 4 de la liste.
     * @param elem6 L'element 5 de la liste.
     * @author Lilsb et AGOUGILE
     */
    public <T> void fillArray(
            ArrayList<T> arrayElements, T elem1, T elem2, T elem3, T elem4, T elem5, T elem6){
        arrayElements.add(elem1);
        arrayElements.add(elem2);
        arrayElements.add(elem3);
        arrayElements.add(elem4);
        arrayElements.add(elem5);
        arrayElements.add(elem6);
    }

    /**
     * Methode permettant de lier un terminal a l'interface graphique.
     * @param t Le terminal qui doit etre lie.
     * @author Lilsb et AGOUGILE
     */
    public void setTerminal(TerminalInterface t){
        myTerminal = t;
        chooseAnIdentity = new ChooseIdentity(myTerminal);
    }

    /**
     * Methode permettant d'activer/desactiver un nombre d'element d'une liste d'Objet JTextField uniquement.
     * @param list La liste comportant les elements a activer/desactiver.
     * @param b true = activer ; false = desactiver.
     * @param nbr Le nombre d'element de la liste a activer/desactiver.
     * @author Lilsb et AGOUGILE
     */
    public void setArrayEnable(ArrayList<JTextField> list, boolean b, int nbr){
        for(int i=0;i<nbr;i++){
            list.get(i).setEnabled(b);
        }
    }

    /**
     * Le constructeur de l'interface settings invisible des la creation avec tous les elements la composant.
     * @param game La Partie observée par l'interface.
     * @param gamePlay L'interface de jeu en partie.
     * @author Lilsb et AGOUGILE
     */
    public SettingsInterface(Game game,PlayInterface gamePlay) {
        super("WITCHHUNT");
        currentGame = game;
        playGame = gamePlay;

        this.setContentPane(settingsPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

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
        play.setVisible(false);

        nbrBot.setVisible(false);
        okButtonBOT.setVisible(false);
        textNbrBot.setVisible(false);
        allAction();
    }

    /**
     * Methode permettant de gerer toutes les actions disponibles sur l'interface.
     * @author Lilsb et AGOUGILE
     */
    public void allAction() {
        okButtonPlayer.addActionListener(e -> {
            currentGame.setNumberOfPlayer(nbrPlayer.getSelectedIndex());
            myTerminal.interruptScan();
        });

        okButtonBOT.addActionListener(e -> {
            currentGame.setNumberOfBot(nbrBot.getSelectedIndex());
            myTerminal.interruptScan();
        });

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
                myTerminal.interruptScan();
            }
        });
        play.addActionListener(e -> {
            for(int i=0;i<currentGame.getNumberOfBot();i++){
                currentGame.setBots(comboBoxList.get(i).getSelectedIndex()+1);
            }myTerminal.interruptScan();
        });
    }

    /**
     * Update permet de modifier l'interface en fonction de l'avancement de la configuration du jeu.
     * Elle utilisera l'énumération "UpdateCode" pour détecter l'étape actuelle.
     * @param o L'objet qui a notifié les observeurs
     * @param arg Le code envoye par l'observable.
     * @author Lilsb et AGOUGILE
     */
    @Override
    public void update(Observable o, Object arg) {
        switch ((UpdateCode) arg) {
            case INIT_NUMBER_PLAYER -> {
                this.setVisible(true);
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
            case ERROR_NUMBER -> {
                ErrorNbr.setVisible(true);
                nbrPlayer.setEnabled(true);
                okButtonPlayer.setEnabled(true);
            }
            case INIT_NAME_PLAYER -> {
                nbrBot.setSelectedIndex(currentGame.getNumberOfBot());
                ErrorNbr.setVisible(false);
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
                play.setVisible(true);
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
            case GAME_INIT_ROUND -> {
                for(Player allp : currentGame.getPlayers()){
                    allp.setVue(this,myTerminal);
                }
                dispose();
            }
            case CHOOSE_IDENTITY -> {
                chooseAnIdentity.display((Player)o);
            }

            case GAME_ROUND ->{
                playGame.setVisible(true);
                playGame.setMyTerminal(myTerminal);
            }
        }
    }
}
