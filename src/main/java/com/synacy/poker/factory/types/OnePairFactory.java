package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.factory.Pair;
import com.synacy.poker.utils.CardUtil;
import com.synacy.poker.utils.DeckByRank;

import java.util.*;

public class OnePairFactory extends HandFactory implements Pair {
    private List<Card> pairCards;
    private List<Card> otherCards;

    private int SAME_RANK_SIZE = 2;

    @Override
    public void initializeCards() {
        pairCards = new ArrayList<>();
        otherCards = new ArrayList<>();
    }

    @Override
    public boolean check() {
        return this.checkPair();
    }

    @Override
    public void populateCards() {
        for (DeckByRank deckByRank : super.getGroupedDeckByRank()) {
            CardRank cardRank = deckByRank.getCardRank();
            List<CardSuit> cardSuits = deckByRank.getCardSuits();
            if (cardSuits.size() == SAME_RANK_SIZE && pairCards.isEmpty()) {
                populatePairCards(cardRank, cardSuits);
            } else {
                populateOtherCards(cardRank, cardSuits);
            }
        }

        CardUtil.sortCardsDesc(pairCards, otherCards);
        otherCards = CardUtil.maxOutCardsOnHand(pairCards, otherCards);
    }

    @Override
    public void groupDeck() {
        super.groupDeckByRank();
    }

    public void populateOtherCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (otherCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                otherCards.add(new Card(cardRank, cardSuit));
            }
        }
    }

    @Override
    public void populatePairCards(CardRank cardRank, List<CardSuit> cardSuits) {
        if (pairCards != null) {
            for (CardSuit cardSuit: cardSuits) {
                pairCards.add(new Card(cardRank, cardSuit));
            }
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

    @Override
    public boolean checkPair() {
        for (DeckByRank deckByRank: super.getGroupedDeckByRank()) {
            List<CardSuit> cardSuits = deckByRank.getCardSuits();
            if (cardSuits.size() >= SAME_RANK_SIZE) {
                return true;
            } else {
                // do nothing
            }
        }

        return false;
    }
}
