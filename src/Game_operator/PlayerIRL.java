package Game_operator;

import Cards.*;

import java.util.LinkedList;
import java.util.List;

public class PlayerIRL extends Player {

 /*
    private void setNumberOfPoints(int value) {
        // Automatically generated method. Please do not modify this code.
        this.numberOfPoints = value;
    }
*/
    /**
     * Permet au joueur de choisir le rôle, l'identité qu'il veut prendre.
     * Enregistré grâce à l'instance de la classe Identity du joueur.
     */
    public void chooseIdentity() {
        System.out.println("Witch role do you want to take ?\n1- Witch\n2- Hunt\n--> ");
        if (s.nextInt() == 1) {
            identity.setRole(Role.Witch);
        } else {
            identity.setRole(Role.Hunt);
        }
    }
    /**
     * Permet de choisir un carte dans un tas de carte (n'importe lequel).
     *
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
     * La fonction permet le choix et appelle les fonctions permettant ces actions.
     */
    public void play() {
        if (this.identity.isRevealed() & this.identity.getRole() == Role.Witch) {
            System.out.println("You can't play : you are a witch !");
            return;
        } else if (this.isAccused()) {
            System.out.println(this.getName() + " you are accused !!!!\nWhat do you want to do ?\n1- Reveal your identity\n2- Play a card (only a Witch action)");
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    this.getIdentity().setRevealed(true);
                    break;
                case 2:
                    this.playCard();
                    break;
            }
        } else {
            System.out.println("What do you want to do ?\n1- Accuse someone \n2- Play a card");
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    this.accusation();
                    break;

                case 2:
                    this.playCard();
                    break;
            }
        }
    }

    /**
     * Permet de choisir le nom du joueur suivant.
     * On affiche le nom de tous les joueurs pouvant être choisi puis l'utilisateur rentre le nom du joueur qu'il veut choisir.
     * @return le joueur choisi.
     *
     * @author lilsb
     */
    public Player chooseAPlayer(){ // Soucis, le joueur pourra choisir un joueur déja révélé Witch.

        int i=0;
        boolean verif=false;
        List<Player> players = game.getPlayers();
        for (Player player : players){
            if (player == this){
                continue;
            }
            i = players.indexOf(player);
            System.out.println(i+" - "+ player);
        }
        int indexOfChosenPlayer = s.nextInt();
        do{
            for (Player player : players){
                if (players.indexOf(player) == indexOfChosenPlayer & indexOfChosenPlayer != players.indexOf(this)){
                    i = players.indexOf(player);
                    verif = true;
                }
            }
            if(!verif){
                indexOfChosenPlayer = s.nextInt();
            }
        }while(!verif);
        return players.get(i);

        // attention a revoir car exception si on se trompe lorsque l'on tape le nom.
        // regarder avec la fonction scanner .next(pattern))
        ///////////////// Je pense que ca devrait marche à voir

    }
}
