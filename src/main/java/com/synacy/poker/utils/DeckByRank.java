package com.synacy.poker.utils;

import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;

import java.util.ArrayList;
import java.util.List;

public class DeckByRank {
    CardRank cardRank;
    List<CardSuit> cardSuits;

    public DeckByRank() {
        cardSuits = new ArrayList<>();
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public void addCardSuitToList(CardSuit cardSuit) {
        if (cardSuits == null) {
            cardSuits = new ArrayList<>();
        } else {
            cardSuits.add(cardSuit);
        }
    }

    public void setCardRank(CardRank cardRank) {
        this.cardRank = cardRank;
    }

    public List<CardSuit> getCardSuits() {
        return cardSuits;
    }

    public void setCardSuits(List<CardSuit> cardSuits) {
        this.cardSuits = cardSuits;
    }
}
