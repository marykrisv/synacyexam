package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Full_house">What is a Full House?</a>
 */
public class FullHouse extends Hand {

    private final List<Card> threeOfAKindCards;
    private final List<Card> pairCards;

    public FullHouse(List<Card> threeOfAKindCards, List<Card> pairCards) {
        this.threeOfAKindCards = threeOfAKindCards;
        this.pairCards = pairCards;
    }

    public HandType getHandType() {
        return HandType.FULL_HOUSE;
    }

    /**
     * @return The name of the hand with rank of the three pair and two pair, e.g.
     * 444AA - Full House (4,A)
     */
    @Override
    public String toString() {
        return String.format("Full House (%s,%s)",this.getThreeOfAKindRank(), this.getPairRank());
    }

    private String getThreeOfAKindRank () {
        return this.threeOfAKindCards.get(0).getRank().toString();
    }

    private String getPairRank () {
        return this.pairCards.get(0).getRank().toString();
    }

}
