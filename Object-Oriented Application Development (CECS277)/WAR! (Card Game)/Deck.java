package project1;

import project1.Card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The project1.Deck class is an all-purpose class to be
 * used as a deck of 52 cards. This class can
 * construct a standard deck of playing cards,
 * shuffle its deck, and deal a card from its deck.
 * @author Tanner Mindrum
 * @since 2019-01-29
 **/
public class Deck {

    //Instance variable
    private ArrayList <Card> mainDeck;

    /**
     * Default constructor initializes ArrayList representing
     * a standard deck of 52 cards.
     **/
    public Deck(){
        this.mainDeck = new ArrayList<>();
    }

    /**
     * Constructs a deck of 52 cards with 4 suits using a
     * nested for loop. The inner loop is ued to create
     * 13 cards, while the outer loop is used to create 4 suits.
     **/
    public void makeDeck(){
        for (int i = 0; i < 4; i++){
            for (int j = 1; j < 14; j++){
                if (i == 0){
                    Card c = new Card();
                    c.setRank(j);
                    c.setSuit("Hearts");
                    mainDeck.add(c);
                }
                else if (i == 1){
                    Card c = new Card();
                    c.setRank(j);
                    c.setSuit("Diamonds");
                    mainDeck.add(c);
                }
                else if (i == 2){
                    Card c = new Card();
                    c.setRank(j);
                    c.setSuit("Clubs");
                    mainDeck.add(c);
                }
                else {
                    Card c = new Card();
                    c.setRank(j);
                    c.setSuit("Spades");
                    mainDeck.add(c);
                }
            }
        }
    }

    /**
     * Shuffles the deck of 52 cards.
     **/
    public void Shuffle(){
        Collections.shuffle(mainDeck);
    }

    /**
     * Deals the first card from the deck of 52
     * cards and removes it from the deck.
     * @return the card at the top of the deck.
     **/
    public Card dealCard(){
        return mainDeck.remove(0);
    }
}
