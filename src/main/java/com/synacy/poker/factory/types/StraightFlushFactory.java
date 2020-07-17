package com.synacy.poker.factory.types;

import com.synacy.poker.factory.interfaces.Flush;
import com.synacy.poker.utils.PackBySuit;

public class StraightFlushFactory extends StraightFactory implements Flush {

    int FLUSH_SIZE_CARD = 5;

    @Override
    public boolean check() {
        for (PackBySuit packBySuit : super.getGroupedPackBySuit()) {
            if (packBySuit.getCardRanks().size() >= FLUSH_SIZE_CARD) {
                if (checkFlush(packBySuit) && super.checkStraight(packBySuit.getCardRanks())) {
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
        super.groupPackBySuit();
    }

    @Override
    public boolean checkFlush(PackBySuit packBySuit) {
        if (packBySuit.getCardRanks().size() >= FLUSH_SIZE_CARD) {
            return true;
        }

        return false;
    }
}
