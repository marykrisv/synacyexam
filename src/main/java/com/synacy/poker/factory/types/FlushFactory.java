package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlushFactory extends HandFactory {
    private List<Card> cards;

    @Override
    public void initializeCards() {
        this.cards = super.getCards();
    }

    @Override
    public boolean check() {
        for (Map.Entry<CardSuit, List<CardRank>> entry : super.getGroupedDeckBySuit().entrySet()) {
            if (entry.getValue().size() >= 5) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void populateCards() {
        CardSuit cardSuit;
        List<CardRank> cardRanks;

        for (Map.Entry<CardSuit, List<CardRank>> entry : super.getGroupedDeckBySuit().entrySet()) {
            cardSuit = entry.getKey();
            cardRanks = entry.getValue();

            if (cardRanks.size() >= 5) {
                this.cards = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    cards.add(new Card(cardRanks.get(i), cardSuit));
                }
                super.setCards(this.cards);
                break;
            }
        }
    }

    @Override
    public void groupDeck() {
        super.groupDeckBySuit();
    }
}
