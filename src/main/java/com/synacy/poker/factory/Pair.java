package com.synacy.poker.factory;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;

import java.util.List;

public interface Pair {
    void populatePairCards(CardRank cardRank, List<CardSuit> cardSuits);
    boolean checkPair();
}
