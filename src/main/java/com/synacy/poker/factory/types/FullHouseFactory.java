package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.Pair;
import com.synacy.poker.utils.CardUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FullHouseFactory extends ThreeOfAKindFactory implements Pair {
    private List<Card> threeOfAKindCards;
    private List<Card> pairCards;

    @Override
    public void initializeCards() {
        super.initializeCards();

        pairCards = new ArrayList<>();
    }

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

    @Override
    public void populateCards() {
        this.threeOfAKindCards = super.getThreeOfAKindCards();
        CardUtil.sortCardsDesc(pairCards, threeOfAKindCards);
    }

    @Override
    public List<Card> getThreeOfAKindCards() {
        return threeOfAKindCards;
    }

    @Override
    public void setThreeOfAKindCards(List<Card> threeOfAKindCards) {
        this.threeOfAKindCards = threeOfAKindCards;
    }

    public List<Card> getPairCards() {
        return pairCards;
    }

    public void setPairCards(List<Card> pairCards) {
        this.pairCards = pairCards;
    }

    @Override
    public void populatePairCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (pairCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                pairCards.add(new Card(cardRank, cardSuit));
            }
        }
    }

    @Override
    public boolean checkPair() {
        boolean isOnePair = false;
        Map<CardRank, List<CardSuit>> groupedDeck = this.groupDeckByRank();

        // check for suit with 5 or greater than 5 value
        for (Map.Entry<CardRank, List<CardSuit>> entry : groupedDeck.entrySet()) {
            CardRank cardRank = entry.getKey();
            List<CardSuit> cardSuits = entry.getValue();
            if (cardSuits.size() == 2) {
                populatePairCards(cardRank, cardSuits);
            } else {
                populateOtherCards(cardRank, cardSuits);
            }
        }

        if (pairCards.size() == 2) {
            return true;
        } else {
            return false;
        }
    }
}
