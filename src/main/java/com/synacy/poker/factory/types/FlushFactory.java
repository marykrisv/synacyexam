package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.utils.DeckBySuit;

import java.util.ArrayList;
import java.util.List;

public class FlushFactory extends HandFactory {
    private List<Card> cards;

    private int FLUSH_SIZE_CARD = 5;

    @Override
    public void initializeCards() {
        this.cards = super.getCards();
    }

    @Override
    public boolean check() {
        for (DeckBySuit deckBySuit : super.getGroupedDeckBySuit()) {
            if (deckBySuit.getCardRanks().size() >= FLUSH_SIZE_CARD) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void populateCards() {
        CardSuit cardSuit;
        List<CardRank> cardRanks;

        for (DeckBySuit deckBySuit : super.getGroupedDeckBySuit()) {
            cardSuit = deckBySuit.getCardSuit();
            cardRanks = deckBySuit.getCardRanks();
            if (deckBySuit.getCardRanks().size() >= FLUSH_SIZE_CARD) {
                this.cards = new ArrayList<>();
                for (int i = 0; i < FLUSH_SIZE_CARD; i++) {
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
