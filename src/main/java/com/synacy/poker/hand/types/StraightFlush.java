package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.utils.CardUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Straight_flush">What is a Straight Flush?</a>
 */
public class StraightFlush extends Straight {

    public StraightFlush(List<Card> cards) {
        super(cards);
    }

    @Override
    public HandType getHandType() {
        return HandType.STRAIGHT_FLUSH;
    }

    /**
     * @return Royal Flush if the hand is a royal flush, or Straight Flush with the highest rank card,
     * e.g. Straight Flush (K High)
     */
    @Override
    public String toString() {
        if (isRoyal()) {
            return String.format("Royal Flush",
                    CardUtil.getHighestCardToString(super.getCards(), 1));
        } else {
            return String.format("Straight Flush (%s High)",
                    CardUtil.getHighestCardToString(super.getCards(), 1));
        }
    }

    private boolean isRoyal () {
        if (super.getCards() != null && !super.getCards().isEmpty()) {
            if (super.getCards().get(0).getRank() == CardRank.ACE) {
                return true;
            }
        }

        return false;
    }

}
