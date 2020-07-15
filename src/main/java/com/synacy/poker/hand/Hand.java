package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;

import java.util.*;

/**
 * The base class of the different Hands such as {@link com.synacy.poker.hand.types.Flush},
 * {@link com.synacy.poker.hand.types.FullHouse}, etc.
 */
public abstract class Hand {

    /**
     * @return The {@link HandType}
     */
    public abstract HandType getHandType();

    public String ranksToString (List<Card> cards) {
        List<CardRank> cardRanks = new ArrayList<>();

        for (Card card: cards) {
            cardRanks.add(card.getRank());
        }

        cardRanks.sort(Collections.reverseOrder());

        return cardRanks.toString()
                .replace("[", "")
                .replace("]", "")
                .replaceAll(" ","");
    }

    public void sortedCardsDesc (List<Card> cards) {
        Collections.sort(cards, Comparator.comparing(Card::getRank).reversed());
    }

}
