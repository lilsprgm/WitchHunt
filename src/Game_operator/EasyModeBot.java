package Game_operator;

import Cards.Card;
import Cards.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe representant un bot de difficulte facile.
 * @version 1.3
 * @author Lilsb et AGOUGILE
 */
public class EasyModeBot extends Player {

    /**
     * Methode qui permet de changer l'etape actuelle du Joueur.
     * et de notifier tous les observateurs.
     * @param newUpdateCode Le nouveau code à set.
     * @author Lilsb et AGOUGILE
     */
    private void setUpdateCode(UpdateCode newUpdateCode){
        this.actualCode = newUpdateCode;
        setChanged();
        notifyObservers(newUpdateCode);
    }

    /**
     * Le Bot facile choisi son role de manière aleatoire.
     * @author Lilsb et AGOUGILE
     */
    @Override
    public void chooseIdentity() {
        this.getIdentity().setRole((Role.values()[(int) Math.round(Math.random())]));
    }

    /**
     * Le Bot facile choisi une carte aleatoire.
     * @param Stock La liste de carte parmis laquelle le bot doit choisir une carte.
     * @author Lilsb et AGOUGILE
     */
    @Override
    public Card chooseCardIn(List<Card> Stock) {
        if (Stock.size() == 0){
            return null;
        }
        else{
            Card card = Stock.get((int) Math.round(Math.random()*Stock.size()));
            return card;
        }
    }

    /**
     * Le Bot facile revele son identite si il se fait accuse.
     * Il accusera le tout le temps le premier joueur qui a joue de la partie.
     * @author Lilsb et AGOUGILE
     */
    public void play() {

        List<Player> listP = new ArrayList<Player>();

        if(isAccused()){
            if(identity.getRole()==Role.Hunt){
                setChoiceAccused(1);
                getIdentity().setRevealed(true);
              game.chooseNextPlayer(game.getCurrentPlayer());
              setAccused(false);
           }else{
               setChoiceAccused(2);
            }
     }
     if(game.getCurrentPlayer()==this){
         AccusedPlayer = chooseThis(UpdateCode.ACCUSE).get(0);
         makeAchoice(0,UpdateCode.ACCUSE);
         accusation();
      }
    }

    /**
     * Le Bot facile choisi un joueur aleatoirement.
     * @author Lilsb et AGOUGILE
     */
    @Override
    public Player chooseAPlayer() {
        Player player;
        do {
            player = game.getPlayers().get((int) Math.round(Math.random() * game.getPlayers().size()));
        } while (player == this);
        return player;
    }
}
