package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Three_of_a_kind">What is a Three of a Kind?</a>
 */
public class ThreeOfAKind extends Hand {

    private List<Card> threeOfAKindCards;
    private List<Card> otherCards;

    public ThreeOfAKind(List<Card> threeOfAKindCards, List<Card> otherCards) {
        this.threeOfAKindCards = threeOfAKindCards;
        this.otherCards = otherCards;
    }

    public HandType getHandType() {
        return HandType.THREE_OF_A_KIND;
    }

    /**
     * @return The name of the hand plus kickers in descending rank, e.g. Trips (4) - A,2 High
     */
    @Override
    public String toString() {
        return String.format("Trips (%s) - %s High", getThreeOfAKindRank(), otherCardsHigh());
    }

    private String getThreeOfAKindRank () {
        return this.threeOfAKindCards.get(0).getRank().toString();
    }

    private String otherCardsHigh() {
        return ranksToString(getFirstTwoHigh());
    }

    private List<Card> getFirstTwoHigh () {
        this.sortedCardsDesc(otherCards);
        List<Card> firstTwoHigh = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            firstTwoHigh.add(otherCards.get(i));
        }

        return firstTwoHigh;
    }

}
