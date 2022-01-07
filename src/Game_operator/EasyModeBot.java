package Game_operator;

import Cards.Card;
import Cards.Role;

import java.util.ArrayList;
import java.util.List;

public class EasyModeBot extends Player {

    private void setUpdateCode(UpdateCode newUpdateCode){
        this.actualCode = newUpdateCode;
        setChanged();
        notifyObservers(newUpdateCode);
    }

    // idée : en mode easy on choisit les joueurs et les cartes aléatoirement.
    // on  révèle l'identité directement lorsque l'on est hunt mais pas witch
    @Override
    public void chooseIdentity() {
        //this.getIdentity().setRole((Role.values()[(int) Math.round(Math.random())]));
        this.getIdentity().setRole(Role.Hunt);                                          // Pour tester A enlever
        // int entre 0 et 1 car int arrondi toujours a l'entier superieur
    }

    @Override
    public Card chooseCardIn(List<Card> Stock) { //  a faire :  coder l'exceptions quand il n'y a plus de cartes
        if (Stock.size() == 0){
            return null;
        }
        else{
            Card card = Stock.get((int) Math.round(Math.random()*Stock.size()));
            return card;
        }
    }

    /**
     * Fonction qui permet aux joueurs de jouer. Il y a plusieurs cas de figure pour jouer.
     * D'abord si le l'identité du joueur a été révélée et qu'il est une sorcière, il ne peut plus jouer.
     * Ensuite, si le joueur est accusé, il va pouvoir jouer, mais doit choisir entre révéler son identité ou jouer une carte,
     * seulement une carte action witch. La fonction permet le choix et appelle les fonctions permettant ces actions.
     * Enfin le dernier cas de figure, c'est le tour du joueur de jouer. Il peut donc soit joueur une carte, soit accuser quelqu'un.
     * La fonction permet le choix et appelle les fonctions permettant ces actions.
     */
    public void play() {

        List<Player> listP = new ArrayList<Player>();

    if(isAccused()){
        if(identity.getRole()==Role.Hunt){
            setChoiceAccused(1);
            getIdentity().setRevealed(true);
            game.chooseNextPlayer(game.getCurrentPlayer());
            setAccused(false);
        }else{                                  // 1 Se Revele et 2 Joue Carte
            setChoiceAccused(2);
        }
    }
    if(game.getCurrentPlayer()==this){          // C'est au bot de Jouer
        AccusedPlayer = chooseThis(UpdateCode.ACCUSE).get(0);
        makeAchoice(0,UpdateCode.ACCUSE);         // Va accuser le premier Joueur
        accusation();                                     // Va faire jouer celui qui a été accusé
    }


//        //role reveler onn peut pas jouer
//        if (this.identity.isRevealed() & this.identity.getRole() == Role.Witch) {
//            System.out.println("You can't play : you are a witch !");
//            return;
//
//        //bot accusé
//        } else if (this.isAccused()) {
//            System.out.println(this.getName() + " you are accused !!!!\nWhat do you want to do ?\n1- Reveal your identity\n2- Play a card (only a Witch action)");
//            if (this.identity.getRole() == Role.Hunt ){
//                this.identity.setRevealed(true);
//            }else{
//                //playCard();
//            }
//
//            //tour de jeu du bot classique
//        } else {
//            System.out.println("What do you want to do ?\n1- Accuse someone \n2- Play a card");
//            if (this.deck.isEmpty()){
//                accusation();
//            }
//            else{
//                //playCard();
//            }
//        }
    }

    @Override
    public Player chooseAPlayer() {
        Player player;
        do {
            player = game.getPlayers().get((int) Math.round(Math.random() * game.getPlayers().size()));
        } while (player == this); // ajouter || player == protectedPlayer (quand il y aura les bonnes variables)
        return player;
    }
}
