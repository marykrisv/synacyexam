package com.synacy.poker.utils;

import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;

import java.util.ArrayList;
import java.util.List;

public class PackBySuit {
    CardSuit cardSuit;
    List<CardRank> cardRanks;

    public PackBySuit() {
        cardRanks = new ArrayList<>();
    }

    public void addCardRankToList(CardRank cardRank) {
        if (this.cardRanks == null) {
            cardRanks = new ArrayList<>();
        } else {
            cardRanks.add(cardRank);
        }
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(CardSuit cardSuit) {
        this.cardSuit = cardSuit;
    }

    public List<CardRank> getCardRanks() {
        return cardRanks;
    }

    public void setCardRanks(List<CardRank> cardRanks) {
        this.cardRanks = cardRanks;
    }
}
