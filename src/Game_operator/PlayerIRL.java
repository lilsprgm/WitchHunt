package Game_operator;

import Cards.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe representant un Joueur Reel.
 * @version 1.0
 * @author Lilsb et AGOUGILE
 */
public class PlayerIRL extends Player {


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
     * Methode qui permet de lancer la phase de choix d'identite sur les differentes vues.
     * @author Lilsb et AGOUGILE
     */
    public void chooseIdentity() {
        setUpdateCode(UpdateCode.CHOOSE_IDENTITY);
        while(actualCode!=UpdateCode.END_CHOOSE_IDENTITY);
    }

    /**
     * Permet de choisir une carte dans un tas de carte (n'importe lequel).
     * @param Stock le tas de carte dans lequel on veut choisir la carte
     * @return la carte choisie
     */
    public Card chooseCardIn(List<Card> Stock) {
        int i;
        System.out.println("Choose a card :");
        for (Card card : Stock){
            i = Stock.indexOf(card)+1;
            System.out.println(i+" - "+ card);
        }
        int chosenCard = s.nextInt();
        while(chosenCard == 0 || chosenCard > Stock.size()){
            chosenCard = s.nextInt();
        }
        return Stock.get(chosenCard-1);
    }

    /**
     * Fonction qui permet aux joueurs de jouer. Il y a plusieurs cas de figure pour jouer.
     * D'abord si le l'identité du joueur a été révélée et qu'il est une sorcière, il ne peut plus jouer.
     * Ensuite, si le joueur est accusé, il va pouvoir jouer, mais doit choisir entre révéler son identité ou jouer une carte,
     * seulement une carte action witch. La fonction permet le choix et appelle les fonctions permettant ces actions.
     * Enfin le dernier cas de figure, c'est le tour du joueur de jouer. Il peut donc soit joueur une carte, soit accuser quelqu'un.
     * La methode permet de lancer le choix dans les differentes vues.
     */
    public void play() {

        if (this.isAccused()) {
            setUpdateCode(UpdateCode.IS_ACCUSED);
            while(actualCode!=UpdateCode.PLAY_CARD_WITCH && actualCode!=UpdateCode.IS_REVEALED);

            while(actualCode==UpdateCode.PLAY_CARD_WITCH || actualCode==UpdateCode.IS_ACCUSED || actualCode==UpdateCode.IS_REVEALED){

                if(actualCode==UpdateCode.IS_REVEALED){
                    getIdentity().setRevealed(true);
                    setAccused(false);
                    return;
                }
                while(actualCode==UpdateCode.PLAY_CARD_WITCH){
                    while(actualCode!=UpdateCode.END_CHOOSE_CARD && actualCode!=UpdateCode.IS_ACCUSED);
                    if(actualCode==UpdateCode.END_CHOOSE_CARD){
                        playCard(UpdateCode.PLAY_CARD_WITCH);
                    }
                }
            }

        } else {
                setUpdateCode(UpdateCode.ACCUSE_OR_PLAY);
                while(actualCode!=UpdateCode.END_ACCUSATION && actualCode!=UpdateCode.PLAY_CARD_HUNT);

                while(actualCode==UpdateCode.END_ACCUSATION || actualCode==UpdateCode.PLAY_CARD_HUNT || actualCode==UpdateCode.ACCUSE_OR_PLAY || actualCode==UpdateCode.ACCUSE){
                    if(actualCode==UpdateCode.END_ACCUSATION){
                        accusation();
                        while(actualCode!=UpdateCode.END_PLAY);
                        return;
                    }
                    while(actualCode==UpdateCode.PLAY_CARD_HUNT){
                        while(actualCode!=UpdateCode.END_CHOOSE_CARD && actualCode!=UpdateCode.ACCUSE_OR_PLAY);

                        if(actualCode==UpdateCode.END_CHOOSE_CARD){
                            playCard(UpdateCode.PLAY_CARD_HUNT);
                            while(actualCode!=UpdateCode.END_PLAY && actualCode!=UpdateCode.PLAY_CARD_HUNT);
                            if(actualCode==UpdateCode.END_PLAY){return;}
                        }
                    }
                    while(actualCode!=UpdateCode.ACCUSE_OR_PLAY && actualCode!=UpdateCode.ACCUSE && actualCode!=UpdateCode.END_ACCUSATION);
                }
        }
    }

    /**
     * Permet de choisir le nom du joueur suivant.
     * On affiche le nom de tous les joueurs pouvant être choisi puis l'utilisateur rentre le nom du joueur qu'il veut choisir.
     * @return le joueur choisi.
     * @author lilsb
     */
    public Player chooseAPlayer(){ // Soucis, le joueur pourra choisir un joueur déja révélé Witch.
        List<Player> allp = new ArrayList<>();
        int i=0;
        boolean verif=false;
        List<Player> players = game.getPlayers();
        for (Player player : players){
            if (player == this || (player.getIdentity().isRevealed()&&player.getIdentity().getRole()==Role.Witch)){
                continue;
            }
            allp.add(player);
            System.out.println(i+" - "+ player.getName());
            i++;
        }
        int indexOfChosenPlayer = s.nextInt();
        while(indexOfChosenPlayer<0 || indexOfChosenPlayer>=allp.size()){
            System.out.println("\nWrong number");
            indexOfChosenPlayer = s.nextInt();
        }
        return allp.get(indexOfChosenPlayer);
    }
}
