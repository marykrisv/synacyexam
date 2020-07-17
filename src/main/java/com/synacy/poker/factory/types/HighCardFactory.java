package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.utils.CardUtil;

import java.util.List;

public class HighCardFactory extends HandFactory {

    /**
     * initialize card declarations
     */
    @Override
    public void initializeCards() {
    }

    /**
     * Choose whether deck is grouped by suit or by rank, this is not needed for straight cards
     */
    @Override
    public void groupDeck() {
        // do nothing
    }

    /**
     * Checks if the pack of cards is High Card or not
     *
     * @return true if cards is a flush else false.
     */
    @Override
    public boolean check() {
        return true;
    }

    /**
     * Populate flush cards arranged in descending order
     */
    @Override
    public void populateCards() {
        super.setCards(CardUtil.maxOutCardsOnHand(super.getCards()));
    }


}
