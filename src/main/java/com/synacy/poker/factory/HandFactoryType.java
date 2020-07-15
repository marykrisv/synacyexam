package com.synacy.poker.factory;

import com.synacy.poker.hand.HandType;

public class HandFactoryType {
    HandType handType;
    HandFactory handFactory;

    public HandFactoryType (HandType handType, HandFactory handFactory) {
        this.handType = handType;
        this.handFactory = handFactory;
    }

    public HandType getHandType() {
        return handType;
    }

    public void setHandType(HandType handType) {
        this.handType = handType;
    }

    public HandFactory getHandFactory() {
        return handFactory;
    }

    public void setHandFactory(HandFactory handFactory) {
        this.handFactory = handFactory;
    }
}
