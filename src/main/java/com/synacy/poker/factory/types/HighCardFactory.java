package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.factory.HandFactory;

import java.util.List;

public class HighCardFactory extends HandFactory {

    private List<Card> cards;

    public HighCardFactory() { }

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void initializeCards() {
        this.cards = super.getCards();
    }
}
