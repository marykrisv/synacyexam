package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.utils.CardUtil;
import com.synacy.poker.utils.DeckByRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TwoPairFactory extends OnePairFactory {
    private List<Card> firstPairCards;
    private List<Card> secondPairCards;

    private int SAME_RANK_SIZE = 2;
    private int FIRST_PAIR = 1;
    private int SECOND_PAIR = 2;

    @Override
    public boolean check() {
        int pairCtr = 0;

        for (DeckByRank deckByRank : this.getGroupedDeckByRank()) {
            if (deckByRank.getCardSuits().size() >= SAME_RANK_SIZE) {
                pairCtr++;
            }
        }

        if (pairCtr == 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void populateCards() {
        int pairCtr = 0;

        CardRank cardRank;
        List<CardSuit> cardSuits;

        for (DeckByRank deckByRank: this.getGroupedDeckByRank()) {
            cardRank = deckByRank.getCardRank();
            cardSuits = deckByRank.getCardSuits();

            if (cardSuits.size() >= SAME_RANK_SIZE) {
                pairCtr++;
                if (pairCtr == FIRST_PAIR) {
                    super.setPairCards(firstPairCards);
                    this.populatePairCards(cardRank, cardSuits);
                } else if (pairCtr == SECOND_PAIR){
                    super.setPairCards(secondPairCards);
                    this.populatePairCards(cardRank, cardSuits);
                } else {
                    //do nothing
                }
            } else {
                populateOtherCards(cardRank, cardSuits);
            }
        }

        CardUtil.sortCardsDesc(super.getOtherCards());
        super.setOtherCards(CardUtil.maxOutCardsOnHand(
                CardUtil.combineCards(firstPairCards, secondPairCards),
                super.getOtherCards()));
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
