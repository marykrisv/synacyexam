package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.factory.interfaces.Pair;
import com.synacy.poker.utils.CardUtil;
import com.synacy.poker.utils.PackByRank;

import java.util.*;

public class OnePairFactory extends HandFactory implements Pair {
    private List<Card> pairCards;
    private List<Card> otherCards;

    private int SAME_RANK_SIZE = 2;

    /**
     * initialize card declarations
     */
    @Override
    public void initializeCards() {
        pairCards = new ArrayList<>();
        otherCards = new ArrayList<>();
    }

    /**
     * Choose whether deck is grouped by suit or by rank
     */
    @Override
    public void groupDeck() {
        super.groupPackByRank();
    }

    /**
     * Calls checkPair and returns its result
     *
     * @return checkPair() result.
     */
    @Override
    public boolean check() {
        return this.checkPair();
    }

    /**
     * Populate pair and other cards arranged in descending order
     */
    @Override
    public void populateCards() {
        for (PackByRank packByRank : super.getGroupedPackByRank()) {
            CardRank cardRank = packByRank.getCardRank();
            List<CardSuit> cardSuits = packByRank.getCardSuits();
            if (cardSuits.size() == SAME_RANK_SIZE && pairCards.isEmpty()) {
                populatePairCards(cardRank, cardSuits);
            } else {
                populateOtherCards(cardRank, cardSuits);
            }
        }

        CardUtil.sortCardsDesc(pairCards, otherCards);
        otherCards = CardUtil.maxOutCardsOnHand(pairCards, otherCards);
    }

    /**
     * Populate other cards arranged in descending order
     *
     * @param cardRank {@link CardRank} of non-onePair card
     * @param cardSuits list of {@link CardSuit} of non-onePair card
     */
    public void populateOtherCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (otherCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                otherCards.add(new Card(cardRank, cardSuit));
            }
        }
    }

    /**
     * Populate pair cards arranged in descending order
     *
     * @param cardRank {@link CardRank} of onePair card
     * @param cardSuits list of {@link CardSuit} of onePair card
     */
    @Override
    public void populatePairCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (pairCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                pairCards.add(new Card(cardRank, cardSuit));
            }
        }
    }

    /**
     * Checks if the pack of cards is One Pair
     *
     * @return true if cards is a One Pair else false.
     */
    @Override
    public boolean checkPair() {
        for (PackByRank packByRank : super.getGroupedPackByRank()) {
            List<CardSuit> cardSuits = packByRank.getCardSuits();
            if (cardSuits.size() >= SAME_RANK_SIZE) {
                return true;
            } else {
                // do nothing
            }
        }

        return false;
    }

    public List<Card> getPairCards() {
        return pairCards;
    }

    public void setPairCards(List<Card> pairCards) {
        this.pairCards = pairCards;
    }

    public List<Card> getOtherCards() {
        return otherCards;
    }

    public void setOtherCards(List<Card> otherCards) {
        this.otherCards = otherCards;
    }
}
