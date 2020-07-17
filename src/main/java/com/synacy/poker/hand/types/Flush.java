package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.utils.CardUtil;

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
        return String.format("Flush (%s High)",
                CardUtil.getHighestCardToString(cards, 1));
    }

    /**
     * Gets the highest card in the pack
     *
     * @return
     */
//    private List<Card> getHighest () {
//        if (cards != null && !cards.isEmpty()) {
//            List<Card> highest = new ArrayList<>();
//
//            highest.add(cards.get(0));
//
//            return highest;
//        }
//
//        return null;
//    }

}
