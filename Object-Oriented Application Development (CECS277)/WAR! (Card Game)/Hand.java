package project1;

import project1.Card;

import java.util.ArrayList;

/**
 * The project1.Hand class models a player's hand of cards
 * in the game of WAR! Enables a player to take
 * cards from another deck or hand and play a card.
 * Also, contains methods for players to use when
 * a war situation arises while playing
 * @author Tanner Mindrum
 * @since 2019-01-31
 **/
public class Hand {

    //Instance variables
    private ArrayList<Card> playerDeck;
    private ArrayList<Card> playerWarDeck;

    /**
     * Default constructor initializes two decks with
     * zero cards. One used for non-war rounds and one
     * for war rounds.
     **/
    public Hand(){
        this.playerDeck = new ArrayList<>();
        this.playerWarDeck = new ArrayList<>();
    }

    /**
     * Takes a card from another deck and adds it
     * to the player's deck.
     * @param c - a card object representing the card
     * being taken and added.
     **/
    public void takeCard(Card c){
        playerDeck.add(c);
    }

    /**
     * Takes cards from the player's war deck and adds
     * it to the player's main deck. Then, clears the war
     * deck in preparation for the next war occurrence. Only
     * clears once there a non-war round happens next; therefore,
     * does not clear in the case of double or triple wars. Clearing,
     * in this case, represents a user losing their war cards to the
     * other player.
     **/
    public void takeWarCards(){
        playerDeck.addAll(playerWarDeck);
        playerWarDeck.subList(0, playerWarDeck.size()).clear();
    }

    /**
     * Plays the first card from the player's main deck
     * and removes it from their deck.
     * @return the card at the top of the player's main deck.
     **/
    public Card playCard(){
        return playerDeck.remove(0);
    }

    /**
     * Plays four cards from the player's deck, which are now
     * the player's war cards. If the player cannot play
     * four cards, the method allows the user to play their
     * remaining cards for war.
     **/
    public void playWarCards(){
        if (playerDeck.size() >= 4) {
            for (int i = 0; i < 4; i++) {
                playerWarDeck.add(playerDeck.remove(0));
            }
        }
        else {
            playerWarDeck.addAll(playerDeck);
            playerDeck.subList(0, playerDeck.size()).clear();
        }
    }

    /**
     * Gets the current player's war cards.
     * @return - an ArrayList of project1.Card objects representing
     * a player's current war cards.
     **/
    public ArrayList<Card> getWarCards(){
        return playerWarDeck;
    }

    /**
     * This is the "flip" card in a round of war. Gets the last war
     * card played and reveals it to the other player.
     * @return - a project1.Card object representing the player's final
     * war card in a war round.
     **/
    public Card revealWarCard(){
        return playerWarDeck.get(playerWarDeck.size() - 1);
    }

    /**
     * Clears a player's war deck, which means to give their war
     * cards to the other player.
     **/
    public void clearWarDeck(){
        playerWarDeck.subList(0, playerWarDeck.size()).clear();
    }

    /**
     * Gets the current amount of cards owned by a player.
     * @return - an int representing the current amount of cards
     * in a player's hand.
     */
    public int checkDeckSize(){
        return playerDeck.size();
    }
}
