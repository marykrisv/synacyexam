package com.synacy.poker.factory.types;

import com.synacy.poker.factory.interfaces.Flush;
import com.synacy.poker.utils.DeckBySuit;

import java.util.List;

public class StraightFlushFactory extends StraightFactory implements Flush {

    int FLUSH_SIZE_CARD = 5;

    @Override
    public boolean check() {
        for (DeckBySuit deckBySuit : super.getGroupedDeckBySuit()) {
            if (deckBySuit.getCardRanks().size() >= FLUSH_SIZE_CARD) {
                if (checkFlush(deckBySuit) && super.checkStraight(deckBySuit.getCardRanks())) {
                    return true;
                }
            }
        }
        return false;
    }

//    @Override
//    public void populateCards() {
//        super.populateCards();
//    }

    @Override
    public void groupDeck() {
        super.groupDeckBySuit();
    }

    @Override
    public boolean checkFlush(DeckBySuit deckBySuit) {
        if (deckBySuit.getCardRanks().size() >= FLUSH_SIZE_CARD) {
            return true;
        }

        return false;
    }
}
