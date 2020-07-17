package com.synacy.poker.factory.types;


import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.factory.interfaces.Flush;
import com.synacy.poker.factory.interfaces.Straight;
import com.synacy.poker.utils.DeckBySuit;

import java.util.List;

public class StraightFlushFactory extends HandFactory implements Straight, Flush {

    @Override
    public void initializeCards() {
        // do nothing
    }

    @Override
    public boolean check() {
        return false;
    }

    @Override
    public void populateCards() {

    }

    @Override
    public void groupDeck() {
        super.groupDeckBySuit();
    }

    @Override
    public boolean checkFlush(DeckBySuit deckBySuit) {
        return false;
    }

    @Override
    public boolean checkStraight(List<CardRank> cardRanks) {
        return false;
    }
}
