package Game_operator;

import Cards.Card;
import Cards.Role;

import java.util.List;

public class HardModeBot extends Player {
    @Override
    public void chooseIdentity() {
        this.getIdentity().setRole((Role.values()[(int) Math.round(Math.random())]));
        // int entre 0 et 1 car int arrondi toujours a l'entier superieur
    }

    @Override
    public Card chooseCardIn(List<Card> Stock){ //  a faire :  coder l'exceptions quand il n'y a plus de cartes
        if (Stock.size() == 0){
            return null; // ici pb
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
        //role reveler onn peut pas jouer
        if (this.identity.isRevealed() & this.identity.getRole() == Role.Witch) {
            System.out.println("You can't play : you are a witch !");
            return;

            //bot accusé
        } else if (this.isAccused()) {
            System.out.println(this.getName() + " you are accused !!!!\nWhat do you want to do ?\n1- Reveal your identity\n2- Play a card (only a Witch action)");
            if (this.deck.isEmpty() ){
                this.identity.setRevealed(true);
            }else{
               // playCard();
            }

            //tour de jeu du bot classique
        } else {
            System.out.println("What do you want to do ?\n1- Accuse someone \n2- Play a card");
            this.accusation();
        }
    }

//    @Override
//    public Player chooseAPlayer() {
//        Player player = null;
//        List<Player> players = game.getPlayers();
//        int max = 0;
//        do {
//            for (Player p : players){
//                if (p.numberOfPoints > max){//on choisi le player avec le plus de points possible (car les cartes sont souvent désavantageuse
//                    player = p;
//                }
//            }
//        } while (player == this); // ajouter || player == protectedPlayer (quand il y aura les bonnes variables)
//        return player;
//    }
}
