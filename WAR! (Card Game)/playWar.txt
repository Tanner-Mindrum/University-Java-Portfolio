package project1;

import project1.Card;
import project1.Deck;
import project1.Hand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * project1.playWar class is the main class. It contains the logic
 * for the game of war and contains instances of each class
 * within it.
 * @author Tanner Mindrum
 * @since 2019-02-04
 * **/
public class playWar {

    public static void main(String[] args) {

        //Initializes variables used throughout main
        Scanner in = new Scanner(System.in);
        Deck gameDeck = new Deck();
        Hand player = new Hand();
        Hand computer = new Hand();
        Card currentComputerCard;
        Card currentComputerWarCard;
        Card currentPlayerCard;
        Card currentPlayerWarCard;
        Card currentPlayerCardLosing;
        ArrayList<Card> warCards;
        String playersChoice = "";
        boolean gameOver = false;

        //Calls the deck class to construct a deck of 52 cards and shuffles deck.
        gameDeck.makeDeck();
        gameDeck.Shuffle();

        //Creating the card hands for the computer and player.
        for (int i = 0; i < 26; i++){
            computer.takeCard(gameDeck.dealCard());
            player.takeCard(gameDeck.dealCard());
        }

        System.out.println("You are now playing: \"WAR!\"");

        //Begins the game and will loop until a player runs out of cards.
        while (!gameOver){

            //Initializes variables used in the game that must reset after each round.
            boolean cardsAreEqual = true;
            boolean playersChoiceCheck = true;
            int sumOfCards = 0;

            /*
             * Displays current deck size of each player; then,
             * plays a card from each players hand.
             */
            System.out.println();
            System.out.println("Computer: " + computer.checkDeckSize() + " | " + "You: " + player.checkDeckSize());
            currentComputerCard = computer.playCard();
            currentPlayerCard = player.playCard();

            /*
             * Displays what card each player just played
             * and checks to see if the card played is a
             * face card.
             */
            System.out.println("----------------------------------");
            if (currentComputerCard.getRank() < 2 || currentComputerCard.getRank() > 10){
                currentComputerCard.setFaceCard();
                System.out.println("The computer played: " + currentComputerCard.getFaceCard() + " of " + currentComputerCard.getSuit());
            }
            else {
                System.out.println("The computer played: " + currentComputerCard.getRank() + " of " + currentComputerCard.getSuit());
            }
            if (currentPlayerCard.getRank() < 2 || currentPlayerCard.getRank() > 10){
                currentPlayerCard.setFaceCard();
                System.out.println("You played: " + currentPlayerCard.getFaceCard() + " of " + currentPlayerCard.getSuit());
            }
            else {
                System.out.println("You played: " + currentPlayerCard.getRank() + " of " + currentPlayerCard.getSuit());
            }
            System.out.println("----------------------------------\n");

            /*
             * Player wins the battle, so they take the
             * computer's card and their own and place
             * them at the bottom of their deck.
             */
            if (currentComputerCard.getRank() < currentPlayerCard.getRank()){
                player.takeCard(currentComputerCard);
                player.takeCard(currentPlayerCard);
                System.out.println("You won the battle!");
            }
            /*
             * Computer wins the battle, so the player has a "player's choice",
             * which means they can lose their cards to the computer or play
             * the next card from their hand.
             */
            else if (currentPlayerCard.getRank() < currentComputerCard.getRank()){
                System.out.println("The computer played a higher ranked card.\n");
                if (player.checkDeckSize() != 0) {
                    System.out.println("Player's choice!\nEnter \"L\" to lose your card to the computer or \"P\" to play the next card at the top of your deck.");
                    while (playersChoiceCheck) {
                        System.out.println("Enter your choice: ");
                        playersChoice = in.nextLine().trim();
                        //Throws an exception if the user does not enter "L" or "P", used to validate their respone.
                        try {
                            if (playersChoice.equals("L") || playersChoice.equals("P")) {
                                playersChoiceCheck = false;
                            } else {
                                throw new IOException();
                            }
                        } catch (IOException e) {
                            System.out.println("Not a valid input, try again.");
                        }
                    }
                    //Player loses their cards to the computer
                    if (playersChoice.equals("L")) {
                        System.out.println();
                        System.out.println("The computer took your card.");
                        computer.takeCard(currentPlayerCard);
                        computer.takeCard(currentComputerCard);
                    }
                    //Player chooses to play one more card from their deck.
                    //Depending on the sum of their new card and original card, they will lose, win, or enter war.
                    else if (playersChoice.equals("P")) {
                        currentPlayerCardLosing = player.playCard();
                        System.out.println();
                        System.out.println("-------------------------");
                        if (currentPlayerCardLosing.getRank() < 2 || currentPlayerCardLosing.getRank() > 10) {
                            currentPlayerCardLosing.setFaceCard();
                            System.out.println("You played: " + currentPlayerCardLosing.getFaceCard() + " of " + currentPlayerCardLosing.getSuit());
                        } else {
                            System.out.println("You played: " + currentPlayerCardLosing.getRank() + " of " + currentPlayerCardLosing.getSuit());
                        }
                        sumOfCards = currentPlayerCard.getRank() + currentPlayerCardLosing.getRank();
                        System.out.println("Sum of cards' ranks: " + sumOfCards);
                        System.out.println("-------------------------\n");
                        if (currentPlayerCard.getRank() + currentPlayerCardLosing.getRank() < currentComputerCard.getRank()) {
                            System.out.println("You won the player's choice!");
                            player.takeCard(currentComputerCard);
                            player.takeCard(currentPlayerCard);
                            player.takeCard(currentPlayerCardLosing);
                        } else if (currentPlayerCard.getRank() + currentPlayerCardLosing.getRank() > currentComputerCard.getRank()) {
                            System.out.println("You lost the player's choice.");
                            computer.takeCard(currentPlayerCard);
                            computer.takeCard(currentPlayerCardLosing);
                            computer.takeCard(currentComputerCard);
                        } else if (currentPlayerCard.getRank() + currentPlayerCardLosing.getRank() == currentComputerCard.getRank()) {
                            while (cardsAreEqual) {
                                /*
                                 * Plays 4 cards from each hand and assigns the value
                                 * of the final "war" card to a variable.
                                 */
                                if (computer.checkDeckSize() != 0 && player.checkDeckSize() != 0) {
                                    System.out.println("WAR!\n");
                                    computer.playWarCards();
                                    player.playWarCards();
                                    currentComputerWarCard = computer.revealWarCard();
                                    currentPlayerWarCard = player.revealWarCard();

                                    /*
                                     * Displays each players' war card.
                                     * Checks for face card values.
                                     */
                                    System.out.println("Flip cards: ");
                                    System.out.println("---------------------------------------");
                                    if (currentComputerWarCard.getRank() < 2 || currentComputerWarCard.getRank() > 10) {
                                        currentComputerWarCard.setFaceCard();
                                        System.out.println("The computer's war card is: " + currentComputerWarCard.getFaceCard() + " of " + currentComputerWarCard.getSuit());
                                    } else {
                                        System.out.println("The computer's war card is: " + currentComputerWarCard.getRank() + " of " + currentComputerWarCard.getSuit());
                                    }
                                    if (currentPlayerWarCard.getRank() < 2 || currentPlayerWarCard.getRank() > 10) {
                                        currentPlayerWarCard.setFaceCard();
                                        System.out.println("Your war card is: " + currentPlayerWarCard.getFaceCard() + " of " + currentPlayerWarCard.getSuit());
                                    } else {
                                        System.out.println("Your war card is: " + currentPlayerWarCard.getRank() + " of " + currentPlayerWarCard.getSuit());
                                    }
                                    System.out.println("---------------------------------------\n");

                                    if (currentComputerWarCard.getRank() == currentPlayerWarCard.getRank()) {
                                        if (computer.checkDeckSize() == 0) {
                                            System.out.println("The computer has 0 cards for the war!");
                                            warCards = computer.getWarCards();
                                            for (int i = 0; i < warCards.size(); i++) {
                                                player.takeCard(warCards.get(i));
                                            }
                                            computer.clearWarDeck();
                                            player.takeWarCards();
                                            player.takeCard(currentComputerCard);
                                            player.takeCard(currentPlayerCard);
                                            player.takeCard(currentPlayerCardLosing);
                                            cardsAreEqual = false;
                                        } else if (player.checkDeckSize() == 0) {
                                            System.out.println("You have 0 cards for the war.");
                                            warCards = player.getWarCards();
                                            for (int i = 0; i < warCards.size(); i++) {
                                                computer.takeCard(warCards.get(i));
                                            }
                                            player.clearWarDeck();
                                            computer.takeWarCards();
                                            computer.takeCard(currentPlayerCard);
                                            computer.takeCard(currentComputerCard);
                                            computer.takeCard(currentPlayerCardLosing);
                                            cardsAreEqual = false;
                                        }
                                    } else if (currentComputerWarCard.getRank() > currentPlayerWarCard.getRank()) {
                                        System.out.println("You lost the war. The computer takes all your cards.");
                                        warCards = player.getWarCards();
                                        for (int i = 0; i < warCards.size(); i++) {
                                            computer.takeCard(warCards.get(i));
                                        }
                                        player.clearWarDeck();
                                        computer.takeWarCards();
                                        computer.takeCard(currentPlayerCard);
                                        computer.takeCard(currentPlayerCardLosing);
                                        computer.takeCard(currentComputerCard);
                                        cardsAreEqual = false;
                                    } else if (currentPlayerWarCard.getRank() > currentComputerWarCard.getRank()) {
                                        System.out.println("You won the war! You take all the computer's cards.");
                                        warCards = computer.getWarCards();
                                        for (int i = 0; i < warCards.size(); i++) {
                                            player.takeCard(warCards.get(i));
                                        }
                                        computer.clearWarDeck();
                                        player.takeWarCards();
                                        player.takeCard(currentComputerCard);
                                        player.takeCard(currentPlayerCard);
                                        player.takeCard(currentPlayerCardLosing);
                                        cardsAreEqual = false;
                                    }
                                } else {
                                    if (computer.checkDeckSize() == 0) {
                                        System.out.println("\nThe computer has 0 cards for the war!");
                                        player.takeCard(currentComputerCard);
                                        player.takeCard(currentPlayerCard);
                                        player.takeCard(currentPlayerCardLosing);
                                    } else if (player.checkDeckSize() == 0) {
                                        System.out.println("\nYou have 0 cards for the war.");
                                        computer.takeCard(currentPlayerCard);
                                        computer.takeCard(currentPlayerCardLosing);
                                        computer.takeCard(currentComputerCard);
                                    }
                                    cardsAreEqual = false;
                                }
                            }
                        }
                    }
                }
                /*
                 * If the player's hand size is 0, they can't play the player's choice,
                 * so the computer will take their cards and the game will end.
                 */
                else{
                    computer.takeCard(currentPlayerCard);
                    computer.takeCard(currentComputerCard);
                    cardsAreEqual = false;
                }
            }
            else if (currentComputerCard.getRank() == currentPlayerCard.getRank()){
                while (cardsAreEqual){
                    /*
                     * Plays 4 cards from each hand and assigns the value
                     * of the final "war" card to a variable.
                     */
                    if (computer.checkDeckSize() != 0 && player.checkDeckSize() != 0) {
                        System.out.println("WAR!\n");
                        computer.playWarCards();
                        player.playWarCards();
                        currentComputerWarCard = computer.revealWarCard();
                        currentPlayerWarCard = player.revealWarCard();

                        /*
                         * Displays each players' war card.
                         * Checks for face card values.
                         */
                        System.out.println("Flip cards: ");
                        System.out.println("---------------------------------------");
                        if (currentComputerWarCard.getRank() < 2 || currentComputerWarCard.getRank() > 10) {
                            currentComputerWarCard.setFaceCard();
                            System.out.println("The computer's war card is: " + currentComputerWarCard.getFaceCard() + " of " + currentComputerWarCard.getSuit());
                        } else {
                            System.out.println("The computer's war card is: " + currentComputerWarCard.getRank() + " of " + currentComputerWarCard.getSuit());
                        }
                        if (currentPlayerWarCard.getRank() < 2 || currentPlayerWarCard.getRank() > 10) {
                            currentPlayerWarCard.setFaceCard();
                            System.out.println("Your war card is: " + currentPlayerWarCard.getFaceCard() + " of " + currentPlayerWarCard.getSuit());
                        } else {
                            System.out.println("Your war card is: " + currentPlayerWarCard.getRank() + " of " + currentPlayerWarCard.getSuit());
                        }
                        System.out.println("---------------------------------------\n");

                        if (currentComputerWarCard.getRank() == currentPlayerWarCard.getRank()) {
                            if (computer.checkDeckSize() == 0) {
                                System.out.println("The computer has 0 cards for the war!");
                                warCards = computer.getWarCards();
                                for (int i = 0; i < warCards.size(); i++) {
                                    player.takeCard(warCards.get(i));
                                }
                                computer.clearWarDeck();
                                player.takeWarCards();
                                player.takeCard(currentComputerCard);
                                player.takeCard(currentPlayerCard);
                                cardsAreEqual = false;
                            } else if (player.checkDeckSize() == 0) {
                                System.out.println("You have 0 cards for the war.");
                                warCards = player.getWarCards();
                                for (int i = 0; i < warCards.size(); i++) {
                                    computer.takeCard(warCards.get(i));
                                }
                                player.clearWarDeck();
                                computer.takeWarCards();
                                computer.takeCard(currentPlayerCard);
                                computer.takeCard(currentComputerCard);
                                cardsAreEqual = false;
                            }
                        } else if (currentComputerWarCard.getRank() > currentPlayerWarCard.getRank()) {
                            System.out.println("You lost the war. The computer takes all your cards.");
                            warCards = player.getWarCards();
                            for (int i = 0; i < warCards.size(); i++) {
                                computer.takeCard(warCards.get(i));
                            }
                            player.clearWarDeck();
                            computer.takeWarCards();
                            computer.takeCard(currentPlayerCard);
                            computer.takeCard(currentComputerCard);
                            cardsAreEqual = false;
                        } else if (currentPlayerWarCard.getRank() > currentComputerWarCard.getRank()) {
                            System.out.println("You won the war! You take all the computer's cards.");
                            warCards = computer.getWarCards();
                            for (int i = 0; i < warCards.size(); i++) {
                                player.takeCard(warCards.get(i));
                            }
                            computer.clearWarDeck();
                            player.takeWarCards();
                            player.takeCard(currentComputerCard);
                            player.takeCard(currentPlayerCard);
                            cardsAreEqual = false;
                        }
                    }
                    else {
                        if (computer.checkDeckSize() == 0){
                            System.out.println("\nThe computer has 0 cards for the war!");
                            player.takeCard(currentComputerCard);
                            player.takeCard(currentPlayerCard);
                        }
                        else if (player.checkDeckSize() == 0){
                            System.out.println("\nYou have 0 cards for the war.");
                            computer.takeCard(currentPlayerCard);
                            computer.takeCard(currentComputerCard);
                        }
                        cardsAreEqual = false;
                    }
                }
            }
            if (computer.checkDeckSize() == 0){
                System.out.println("\n*******************************************");
                System.out.println("The computer lost all their cards.\nYou won!");
                System.out.println("*******************************************");
                System.out.println("\nFINAL: ~ Computer: " + computer.checkDeckSize() + " | " + "You: " + player.checkDeckSize() + " ~");
                gameOver = true;
            }
            else if (player.checkDeckSize() == 0){
                System.out.println("\n*******************************************");
                System.out.println("You lost all your cards.\nThe computer won.");
                System.out.println("*******************************************");
                System.out.println("\nFINAL: ~ Computer: " + computer.checkDeckSize() + " | " + "You: " + player.checkDeckSize() + " ~");
                gameOver = true;
            }
        }
    }
}
