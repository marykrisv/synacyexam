package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.utils.CardUtil;

import java.util.*;

public class OnePairFactory extends HandFactory {
    private List<Card> pairCards;
    private List<Card> otherCards;

    @Override
    public boolean check() {
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
            CardUtil.sortCardsDesc(pairCards, otherCards);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initializeCards() {
        pairCards = new ArrayList<>();
        otherCards = new ArrayList<>();
    }

    public void populatePairCards(CardRank cardRank, List<CardSuit> cardSuits) {
        for (CardSuit cardSuit: cardSuits) {
            pairCards.add(new Card(cardRank, cardSuit));
        }
    }

    public void populateOtherCards(CardRank cardRank, List<CardSuit> cardSuits) {
        for (CardSuit cardSuit: cardSuits) {
            otherCards.add(new Card(cardRank, cardSuit));
        }
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
