package com.synacy.poker.factory.types;

import com.synacy.poker.factory.interfaces.Flush;
import com.synacy.poker.utils.PackBySuit;

public class StraightFlushFactory extends StraightFactory implements Flush {

    int FLUSH_SIZE_CARD = 5;

    /**
     * Choose whether deck is grouped by suit or by rank
     */
    @Override
    public void groupDeck() {
        super.groupPackBySuit();
    }

    /**
     * Calls checkFlush and returns its result
     *
     * @return checkFlush() result.
     */
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

    /**
     * Checks if the pack of cards is Straight Flush
     *
     * @return true if cards is a Straight Flush else false.
     */
    @Override
    public boolean checkFlush(PackBySuit packBySuit) {
        if (packBySuit.getCardRanks().size() >= FLUSH_SIZE_CARD) {
            return true;
        }

        return false;
    }
}
