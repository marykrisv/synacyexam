package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.utils.CardUtil;
import com.synacy.poker.utils.PackByRank;

import java.util.ArrayList;
import java.util.List;

public class FourOfAKindFactory extends HandFactory {
    private List<Card> fourOfAKindCards;
    private List<Card> otherCards;

    int SAME_RANK_SIZE = 4;

    /**
     * initialize card declarations
     */
    @Override
    public void initializeCards() {
        fourOfAKindCards = new ArrayList<>();
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
     * Checks if the pack of cards is four of a kind
     *
     * @return true if cards is a four of a kind else false.
     */
    @Override
    public boolean check() {
        for (PackByRank packByRank : super.getGroupedPackByRank()) {
            if (packByRank.getCardSuits().size() == SAME_RANK_SIZE) {
                return true;
            }
        }

        return false;
    }

    /**
     * Populate fourOfAKind and other cards arranged in descending order
     */
    @Override
    public void populateCards() {
        for (PackByRank packByRank : super.getGroupedPackByRank()) {
            CardRank cardRank = packByRank.getCardRank();
            List<CardSuit> cardSuits = packByRank.getCardSuits();
            if (cardSuits.size() == SAME_RANK_SIZE && fourOfAKindCards.isEmpty()) {
                populateFourOfAKind(cardRank, cardSuits);
            } else {
                populateOtherCards(cardRank, cardSuits);
            }
        }

        CardUtil.sortCardsDesc(otherCards);
        otherCards = CardUtil.maxOutCardsOnHand(fourOfAKindCards, otherCards);
    }

    /**
     * Populate fourOfAkind cards arranged in descending order
     *
     * @param cardRank {@link CardRank} of fourOfAkind card
     * @param cardSuits list of {@link CardSuit} of fourOfAkind card
     */
    public void populateFourOfAKind(CardRank cardRank, List<CardSuit> cardSuits) {
        if (fourOfAKindCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                fourOfAKindCards.add(new Card(cardRank, cardSuit));
            }
        }
    }

    /**
     * Populate non-fourOfAkind cards arranged in descending order
     *
     * @param cardRank {@link CardRank} of non-fourOfAkind card
     * @param cardSuits list of {@link CardSuit} of non-fourOfAkind card
     */
    public void populateOtherCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (otherCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                otherCards.add(new Card(cardRank, cardSuit));
            }
        }
    }

    public List<Card> getFourOfAKindCards() {
        return fourOfAKindCards;
    }

    public void setFourOfAKindCards(List<Card> fourOfAKindCards) {
        this.fourOfAKindCards = fourOfAKindCards;
    }

    public List<Card> getOtherCards() {
        return otherCards;
    }

    public void setOtherCards(List<Card> otherCards) {
        this.otherCards = otherCards;
    }
}
