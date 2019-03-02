package project1;

/**
 * The project1.Card class outlines the behavior of a standard
 * playing card within a deck of 52 cards. Includes
 * characterists such as, rank and suit of card, and
 * also contains methods to determine if a card is
 * a face card (Ace, Jack, Queen, King).
 * @author Tanner Mindrum
 * @since 2019-01-29
 **/
public class Card {

    //Instance Variables
    private int rank;
    private String suit;
    private String faceCard;

    /**
     * Default constructor creates a card with a rank of zero
     * and suit/face values set to "N/A".
     **/
    public Card(){
        this.rank = 0;
        this.suit = "N/A";
        this.faceCard = "N/A";
    }

    /**
     * Sets the rank of one standard playing card.
     * @param rank - an integer representing the rank
     *             of a card.
     **/
    public void setRank(int rank){
        this.rank = rank;
    }

    /**
     * Gets the current rank of a standard playing card.
     * @return the current rank of a card.
     **/
    public int getRank(){
        return rank;
    }

    /**
     * Sets the suit of one standard playing card.
     * @param suit - a string representing the suit
     *             of a card.
     **/
    public void setSuit(String suit){
        this.suit = suit;
    }

    /**
     * Gets the current suit of a standard playing card.
     * @return - the current suit of a card.
     **/
    public String getSuit(){
        return suit;
    }

    /**
     * Depending on the current rank of a card,
     * assigns a specific string that represents
     * the face card associated with the current
     * rank's value.
     **/
    public void setFaceCard(){
        if (rank == 1){
            this.faceCard = "Ace";
        }
        else if (rank == 11){
            this.faceCard = "Jack";
        }
        else if (rank == 12){
            this.faceCard = "Queen";
        }
        else if (rank == 13){
            this.faceCard = "King";
        }
    }

    /**
     * Gets the current face card value of a
     * card.
     * @return - the current face card value associated
     * with a card
     **/
    public String getFaceCard(){
        return faceCard;
    }

    public String toString(){
        return rank + " of " + suit;
    }
}
