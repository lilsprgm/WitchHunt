package GraphicInterface;

import Cards.*;
import Game_operator.Game;
import Game_operator.Player;
import Game_operator.UpdateCode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class PlayInterface extends JFrame implements Observer {

    private final Game currentGame;
    private JLabel tourPlayer;
    private JPanel playPanel;
    private JTextField textField1;

    private ImageIcon angryMob; private ImageIcon blackCat; private ImageIcon broomstick; private ImageIcon cauldron;
    private ImageIcon duckingStool; private ImageIcon evilEye; private ImageIcon hookedNose; private ImageIcon petNewt;
    private ImageIcon pointedHat; private ImageIcon theInquisition; private ImageIcon toad; private ImageIcon wart;
    private HashMap<Card,ImageIcon> imageList = new HashMap();
    private ArrayList<Card> hand;

    private JLabel carteN1;
    private JLabel carteN2;
    private JLabel carteN3;
    private JLabel carteN4;
    private ArrayList<JLabel> carteN = new ArrayList<>();

    private JButton buttontest;
    private JButton button1;
    private int i = 0;

    public PlayInterface(Game game){
        super("WITCHHUNT");
        currentGame=game;
        this.setContentPane(playPanel); // permet de choisr la fenetre a afficher
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set arret prgm quand ferme fenetre
        this.pack(); // ajustement taille de la fenetre automatique
        //this.setSize(800,1000);
        this.setVisible(false);
        initImageIcon();
        allActions();

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
    public void initHand(Player p){
        hand = p.getDeck();
        System.out.println(currentGame.getCurrentPlayer());
        System.out.println(hand);
        for(int i=0;i<hand.size();i++){
            setImageIcon(carteN.get(i),imageList.get(hand.get(i)));
        }
    }
    public void setImageIcon(JLabel label, ImageIcon image){
        label.setIcon(image);
    }

    public void allActions(){

        buttontest.addActionListener(e -> {
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((UpdateCode)arg){
            case GAME_INIT_ROUND:
                break;
            case GAME_ROUND:
                initHand(currentGame.getCurrentPlayer()); // Permet d'obtenir la main du joueur actuel
                break;
        }
    }


}
