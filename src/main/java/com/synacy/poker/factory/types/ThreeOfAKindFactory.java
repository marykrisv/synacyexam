package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.factory.interfaces.ThreeOfAKind;
import com.synacy.poker.utils.CardUtil;
import com.synacy.poker.utils.PackByRank;

import java.util.ArrayList;
import java.util.List;

public class ThreeOfAKindFactory extends HandFactory implements ThreeOfAKind {

    private List<Card> threeOfAKindCards;
    private List<Card> otherCards;

    private int SAME_RANK_SIZE = 3;

    /**
     * initialize card declarations
     */
    @Override
    public void initializeCards() {
        threeOfAKindCards = new ArrayList<>();
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
     * Calls checkThreeOfAKind function
     *
     * @return returns checkThreeOfAKind function result
     */
    @Override
    public boolean check() {
        return this.checkThreeOfAKind();
    }

    /**
     * Populate threeOfAKind and other cards arranged in descending order
     */
    @Override
    public void populateCards() {
        for (PackByRank packByRank : super.getGroupedPackByRank()) {
            CardRank cardRank = packByRank.getCardRank();
            List<CardSuit> cardSuits = packByRank.getCardSuits();
            if (cardSuits.size() == SAME_RANK_SIZE && threeOfAKindCards.isEmpty()) {
                populateThreeOfAKindCards(cardRank, cardSuits);
            } else {
                populateOtherCards(cardRank, cardSuits);
            }
        }

        CardUtil.sortCardsDesc(otherCards);
        otherCards = CardUtil.maxOutCardsOnHand(threeOfAKindCards, otherCards);
    }

    /**
     * Populates threeOfAKind card
     *
     * @param cardRank {@link CardRank} of the threeOfAKind card
     * @param cardSuits List of {@link CardRank} of the threeOfAKind card
     */
    public void populateThreeOfAKindCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (threeOfAKindCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                threeOfAKindCards.add(new Card(cardRank, cardSuit));
            }
        }
    }

    /**
     * Populates non-threeOfAKind card
     *
     * @param cardRank {@link CardRank} of the non-threeOfAKind card
     * @param cardSuits List of {@link CardSuit} of the non-threeOfAKind card
     */
    public void populateOtherCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (otherCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                otherCards.add(new Card(cardRank, cardSuit));
            }
        }
    }

    /**
     * Checks if the pack of cards is Three of a Kind
     *
     * @return true if cards is a Three of a Kind else false.
     */
    @Override
    public boolean checkThreeOfAKind() {
        for (PackByRank packByRank : super.getGroupedPackByRank()) {
            if (packByRank.getCardSuits().size() == SAME_RANK_SIZE) {
                return true;
            }
        }

        return false;
    }

    public List<Card> getThreeOfAKindCards() {
        return threeOfAKindCards;
    }

    public void setThreeOfAKindCards(List<Card> threeOfAKindCards) {
        this.threeOfAKindCards = threeOfAKindCards;
    }

    public List<Card> getOtherCards() {
        return otherCards;
    }

    public void setOtherCards(List<Card> otherCards) {
        this.otherCards = otherCards;
    }
}
