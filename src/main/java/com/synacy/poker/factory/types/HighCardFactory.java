package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.utils.CardUtil;

import java.util.List;

public class HighCardFactory extends HandFactory {

    private List<Card> cards;

    @Override
    public void initializeCards() {
        this.cards = super.getCards();
    }

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void populateCards() {
        CardUtil.maxOutCardsOnHand(cards);
    }

    @Override
    public void groupDeck() {
        // do nothing
    }
}
