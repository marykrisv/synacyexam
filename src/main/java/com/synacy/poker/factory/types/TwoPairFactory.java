package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.utils.CardUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TwoPairFactory extends OnePairFactory {
    private List<Card> firstPairCards;
    private List<Card> secondPairCards;

    @Override
    public boolean check() {
        int pairCtr = 0;
        Map<CardRank, List<CardSuit>> groupedDeck = this.groupDeckByRank();

        CardRank cardRank;
        List<CardSuit> cardSuits;

        for (Map.Entry<CardRank, List<CardSuit>> entry : groupedDeck.entrySet()) {
            cardRank = entry.getKey();
            cardSuits = entry.getValue();

            if (cardSuits.size() >= 2) {
                pairCtr++;
                if (pairCtr == 1) {
                    super.setPairCards(firstPairCards);
                    this.populatePairCards(cardRank, cardSuits);
                } else if (pairCtr == 2){
                    super.setPairCards(secondPairCards);
                    this.populatePairCards(cardRank, cardSuits);
                } else {
                    //do nothing
                }
            } else {
                populateOtherCards(cardRank, cardSuits);
            }
        }

        if (pairCtr == 2) {
            CardUtil.sortCardsDesc(super.getOtherCards());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initializeCards() {
        super.initializeCards();

        firstPairCards = new ArrayList<>();
        secondPairCards = new ArrayList<>();
    }

    public List<Card> getFirstPairCards() {
        return firstPairCards;
    }

    public void setFirstPairCards(List<Card> firstPairCards) {
        this.firstPairCards = firstPairCards;
    }

    public List<Card> getSecondPairCards() {
        return secondPairCards;
    }

    public void setSecondPairCards(List<Card> secondPairCards) {
        this.secondPairCards = secondPairCards;
    }
}
