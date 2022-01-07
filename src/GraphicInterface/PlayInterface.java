package GraphicInterface;

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
import java.util.*;

public class PlayInterface extends JFrame implements Observer {

    private final Game currentGame;
    private TerminalInterface myTerminal;
    private Player actualObservable;
    private JLabel tourPlayer;
    private JPanel playPanel;

    private ImageIcon angryMob; private ImageIcon blackCat; private ImageIcon broomstick; private ImageIcon cauldron;
    private ImageIcon duckingStool; private ImageIcon evilEye; private ImageIcon hookedNose; private ImageIcon petNewt;
    private ImageIcon pointedHat; private ImageIcon theInquisition; private ImageIcon toad; private ImageIcon wart;
    private HashMap<Card,ImageIcon> imageList = new HashMap();
    private ArrayList<Card> hand;

    private JLabel carteN1;
    private JLabel carteN2;
    private JLabel carteN3;
    private JLabel carteN4;
    private JButton playACardButton;
    private JButton accuseButton;
    private JComboBox playerComboBox;
    private JButton validAccusation;
    private JButton validCardBtn;
    private JButton revealBtn;
    private JLabel phaseLabel;
    private JLabel identityLabel;
    private JLabel pointLabel;
    private ArrayList<JLabel> carteN = new ArrayList<>();

    private JButton buttontest;
    private int i = 0;
    private int choice=50;

    public PlayInterface(Game game){
        super("WITCHHUNT");
        currentGame=game;
        this.setContentPane(playPanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique
        //this.setSize(800,1000);
        this.setVisible(false);
        validAccusation.setVisible(false);
        playerComboBox.setVisible(false);
        validCardBtn.setVisible(false);
        revealBtn.setVisible(false);
        initImageIcon();
        allActions();



    }

    public void setMyTerminal(TerminalInterface ter){
        myTerminal = ter;
    }

    public void allActions(){

        //Lors d'un appui sur la carte n°1 :
        carteN1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(carteN1.isEnabled()){
                    carteN1.setEnabled(false);carteN2.setEnabled(true);
                    carteN3.setEnabled(true);carteN4.setEnabled(true);
                    choice = 0;
                }
            }
        });
        carteN2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(carteN2.isEnabled()){
                    carteN1.setEnabled(true);carteN2.setEnabled(false);
                    carteN3.setEnabled(true);carteN4.setEnabled(true);
                    choice = 1;
                }
            }
        });
        carteN3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(carteN3.isEnabled()){
                    carteN1.setEnabled(true);carteN2.setEnabled(true);
                    carteN3.setEnabled(false);carteN4.setEnabled(true);
                    choice = 2;
                }
            }
        });
        carteN4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(carteN4.isEnabled()){
                    carteN1.setEnabled(true);carteN2.setEnabled(true);
                    carteN3.setEnabled(true);carteN4.setEnabled(false);
                    choice = 3;
                }
            }
        });
        accuseButton.addActionListener(e -> {
            myTerminal.interruptScan();
            actualObservable.setChoice(1);
        });
        playACardButton.addActionListener(e -> {
            myTerminal.interruptScan();
            validCardBtn.setVisible(true);
            accuseButton.setVisible(false);
            actualObservable.setChoice(2);
        });
        validAccusation.addActionListener(e -> {
            myTerminal.interruptScan();
            Player choosenPlayer = (Player) playerComboBox.getSelectedItem();
            actualObservable.makeAchoice(playerComboBox.getSelectedIndex(), UpdateCode.ACCUSE);
        });

        validCardBtn.addActionListener(e -> {
            myTerminal.interruptScan();
            if(actualObservable.isAccused() && choice != 50){
                actualObservable.makeAchoice(choice,UpdateCode.PLAY_CARD_WITCH);
            }else if(choice != 50){
                actualObservable.makeAchoice(choice,UpdateCode.PLAY_CARD_HUNT);
            }
        });

        revealBtn.addActionListener(e -> {
            myTerminal.interruptScan();
            actualObservable.setChoiceAccused(1);
        });
    }

    public void initImageIcon(){

        //Permet de créer les images des cartes
         angryMob = new ImageIcon(this.getClass().getResource("/Ressources/AngryMob.png"));
         blackCat = new ImageIcon(this.getClass().getResource("/Ressources/BlackCat.png"));
         broomstick = new ImageIcon(this.getClass().getResource("/Ressources/Broomstick.png"));
         cauldron = new ImageIcon(this.getClass().getResource("/Ressources/Cauldron.png"));
         duckingStool = new ImageIcon(this.getClass().getResource("/Ressources/DuckingStool.png"));
         evilEye = new ImageIcon(this.getClass().getResource("/Ressources/EvilEye.png"));
         hookedNose = new ImageIcon(this.getClass().getResource("/Ressources/HookedNose.png"));
         petNewt = new ImageIcon(this.getClass().getResource("/Ressources/PetNewt.png"));
         pointedHat = new ImageIcon(this.getClass().getResource("/Ressources/PointedHat.png"));
         theInquisition = new ImageIcon(this.getClass().getResource("/Ressources/TheInquisition.png"));
         toad = new ImageIcon(this.getClass().getResource("/Ressources/Toad.png"));
         wart = new ImageIcon(this.getClass().getResource("/Ressources/Wart.png"));

         //Permet d'affilier une image à la carte qu'elle représente
        imageList.put(AngryMob.getInstance(),angryMob);imageList.put(BlackCat.getInstance(),blackCat);
        imageList.put(Broomstick.getInstance(),broomstick);imageList.put(Cauldron.getInstance(),cauldron);
        imageList.put(DuckingStool.getInstance(),duckingStool);imageList.put( EvilEye.getInstance(),evilEye);
        imageList.put(HookedNose.getInstance(),hookedNose);imageList.put(PetNewt.getInstance(),petNewt);
        imageList.put(PointedHat.getInstance(),pointedHat);imageList.put(Inquisition.getInstance(),theInquisition);
        imageList.put(Toad.getInstance(),toad);imageList.put(Wart.getInstance(),wart);

        carteN.add(carteN1);carteN.add(carteN2);carteN.add(carteN3);carteN.add(carteN4);

    }
    /**
     * Permet d'initialiser la liste "actualImage" en fonction de la main du Joueur donné en paramètre (current Joueur)
     * @param p
     */
    public void initRound(Player p){
        hand = p.getDeck();
        for(int i=0;i<hand.size();i++){
            setImageIcon(carteN.get(i),imageList.get(hand.get(i)));
        }
        tourPlayer.setText("Tour : "+p.getName());
    }


    public void setImageIcon(JLabel label, ImageIcon image){
        label.setIcon(image);
    }

    public void showAccuseInterface(){
        accuseButton.setVisible(false);
        playACardButton.setVisible(false);
        validCardBtn.setVisible(true);
        revealBtn.setVisible(true);
        phaseLabel.setText("Your are ACCUSED. What do you want to do ?");
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((UpdateCode)arg){
            case GAME_ROUND:
                initRound(currentGame.getCurrentPlayer());
                validCardBtn.setEnabled(false);
                for (Player p : currentGame.getPlayers()){
                    p.setVue(this);// Permet d'obtenir la main du joueur actuel
                }
                break;

            case ACCUSE_OR_PLAY:
                actualObservable =(Player) o;
                accuseButton.setVisible(true);
                playACardButton.setVisible(true);
                validAccusation.setVisible(false);
                playerComboBox.setVisible(false);
                validCardBtn.setVisible(false);
                revealBtn.setVisible(false);

                break;
            case ACCUSE:
                List<Player> playerList = new ArrayList<Player>();
                playerList = actualObservable.chooseThis(UpdateCode.ACCUSE);
                for (Player p : playerList){
                    playerComboBox.addItem(p);
                }
                playerComboBox.setVisible(true);
                validAccusation.setVisible(true);
                break;

            case IS_ACCUSED:
                playerComboBox.setVisible(false);
                validAccusation.setVisible(false);
                actualObservable = (Player)o;
                initRound(actualObservable);
                showAccuseInterface();
                break;
            case IS_REVEALED:
                    actualObservable.getIdentity().setRevealed(true);
                    accuseButton.setVisible(false);
                    accuseButton.setVisible(false);
                    validAccusation.setVisible(false);
                    playerComboBox.setVisible(false);
                    validCardBtn.setVisible(false);
                    revealBtn.setVisible(false);
                    phaseLabel.setText(actualObservable.getName() + "has been revealed : he is a "+actualObservable.getIdentity().getRole());
                    break;

            case PLAY_CARD_WITCH:
                validCardBtn.setEnabled(true);
                break;
            case PLAY_CARD_HUNT:
                validCardBtn.setEnabled(true);
                break;
            case EFFECT_CARD_HUNT:
                actualObservable = (Player)o;
                actualObservable.makeAchoice(1,UpdateCode.EFFECT_CARD_HUNT);
                break;
            case EFFECT_CARD_WITCH:
                actualObservable = (Player)o;
                actualObservable.makeAchoice(1,UpdateCode.EFFECT_CARD_WITCH);
                break;
        }
    }


}
