package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.*;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Flush">What is a flush?</a>
 */
public class Flush extends Hand {

    private List<Card> cards;

    public Flush(List<Card> cards) {
        this.cards = cards;
    }

    public HandType getHandType() {
        return HandType.FLUSH;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * @return Returns the name of the hand and the highest card, e.g. Flush (K High)
     */
    @Override
    public String toString() {
        return String.format("Flush (%s High)", getHighCard());
    }

    private String getHighCard () {
        List<CardRank> cardRanks = new ArrayList<>();

        for (Card card: cards) {
            cardRanks.add(card.getRank());
        }

        cardRanks.sort(Collections.reverseOrder());

        return cardRanks.get(0).toString();
    }

}
