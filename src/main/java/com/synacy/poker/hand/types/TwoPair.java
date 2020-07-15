package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.utils.CardUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Two_pair">What is a Two Pair?</a>
 */
public class TwoPair extends Hand {

    private List<Card> firstPairCards;
    private List<Card> secondPairCards;
    private List<Card> otherCards;

    public TwoPair(List<Card> firstPairCards, List<Card> secondPairCards, List<Card> otherCards) {
        this.firstPairCards = firstPairCards;
        this.secondPairCards = secondPairCards;
        this.otherCards = otherCards;
    }

    public HandType getHandType() {
        return HandType.TWO_PAIR;
    }

    /**
     * @return The name of the hand with kicker ranked in descending order, e.g. Two Pair (4,3) - A High
     */
    @Override
    public String toString() {
        return String.format("Two Pair (%s,%s) - %s High",
                getFirstPairRank(), getSecondPairRank(), otherCardsHigh());
    }

    private String getFirstPairRank () {
        return this.firstPairCards.get(0).getRank().toString();
    }

    private String getSecondPairRank () {
        return this.secondPairCards.get(0).getRank().toString();
    }

    private String otherCardsHigh() {
        List<Card> combined = new ArrayList<>();
        combined.addAll(firstPairCards);
        combined.addAll(secondPairCards);
        return ranksToString(CardUtil.maxOutCardsOnHand(combined, otherCards));
    }

}
