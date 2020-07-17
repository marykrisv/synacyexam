package com.synacy.poker.factory.interfaces;

import com.synacy.poker.card.CardRank;

import java.util.List;

public interface Straight {
    boolean checkStraight (List<CardRank> cardRanks);
}
