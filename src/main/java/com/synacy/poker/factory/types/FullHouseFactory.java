package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.interfaces.Pair;
import com.synacy.poker.utils.PackByRank;

import java.util.ArrayList;
import java.util.List;

public class FullHouseFactory extends ThreeOfAKindFactory implements Pair {
    private List<Card> pairCards;

    private int SAME_RANK_TRIP_SIZE = 3;
    private int SAME_RANK_PAIR_SIZE = 2;
    private int TWO_PAIR = 2;

    /**
     * initialize card declarations
     */
    @Override
    public void initializeCards() {
        super.initializeCards();

        pairCards = new ArrayList<>();
    }

    /**
     * Choose whether deck is grouped by suit or by rank, this is not needed for straight cards
     */
    @Override
    public void groupDeck() {
        super.groupPackByRank();
    }

    /**
     * Calls checkPair and super class' checkThreeOfAkind function and returns its result
     *
     * @return checkPair() and super.checkThreeOfAKind() result.
     */
    @Override
    public boolean check() {
        boolean isThreeOfAKind = super.checkThreeOfAKind();
        boolean isPair = checkPair();
        if (isThreeOfAKind && isPair) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Populate pair and threeOfAKind cards arranged in descending order
     */
    @Override
    public void populateCards() {
        for (PackByRank packByRank : super.getGroupedPackByRank()) {
            CardRank cardRank = packByRank.getCardRank();
            List<CardSuit> cardSuits = packByRank.getCardSuits();
            if (cardSuits.size() == SAME_RANK_TRIP_SIZE && getThreeOfAKindCards().isEmpty()) {
                super.populateThreeOfAKindCards(cardRank, cardSuits);
            } else if (cardSuits.size() >= SAME_RANK_PAIR_SIZE){
                this.populatePairCards(cardRank, cardSuits);
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
            for (int i = 0; i < SAME_RANK_PAIR_SIZE; i++) {
                pairCards.add(new Card(cardRank, cardSuits.get(i)));
            }
        }
    }

    /**
     * Checks if the pack of cards contains pair
     *
     * @return true if cards contains pair else false.
     */
    @Override
    public boolean checkPair() {
        int ctr = 0;

        for (PackByRank packByRank : super.getGroupedPackByRank()) {
            if (packByRank.getCardSuits().size() >= SAME_RANK_PAIR_SIZE) {
                ctr++;
            }
        }

        if (ctr == TWO_PAIR) {
            return true;
        }

        return false;
    }

    public List<Card> getPairCards() {
        return pairCards;
    }

    public void setPairCards(List<Card> pairCards) {
        this.pairCards = pairCards;
    }
}
