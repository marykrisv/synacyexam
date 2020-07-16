package com.synacy.poker.factory.types;

import com.synacy.poker.factory.HandFactory;

public class FourOfAKindFactory extends HandFactory {
    @Override
    public boolean check() {
        return false;
    }

    @Override
    public void populateCards() {

    }

    @Override
    public void groupDeck() {

    }

    @Override
    public void initializeCards() {

    }
}
