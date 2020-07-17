package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.factory.interfaces.Flush;
import com.synacy.poker.utils.PackBySuit;

import java.util.ArrayList;
import java.util.List;

public class FlushFactory extends HandFactory implements Flush {
    private List<Card> cards;

    private int FLUSH_SIZE_CARD = 5;

    /**
     * initialize card declarations
     */
    @Override
    public void initializeCards() {
        this.cards = super.getCards();
    }

    /**
     * Choose if deck is grouped by suit or by rank
     */
    @Override
    public void groupDeck() {
        super.groupPackBySuit();
    }

    /**
     * Checks if the pack of cards is flush or not
     *
     * @return true if cards is a flush else false.
     */
    @Override
    public boolean check() {
        for (PackBySuit packBySuit : super.getGroupedPackBySuit()) {
            if (packBySuit.getCardRanks().size() >= FLUSH_SIZE_CARD) {
                return checkFlush(packBySuit);
            }
        }

        return false;
    }

    /**
     * Populate flush cards arranged in descending order
     */
    @Override
    public void populateCards() {
        CardSuit cardSuit;
        List<CardRank> cardRanks;

        for (PackBySuit packBySuit : super.getGroupedPackBySuit()) {
            cardSuit = packBySuit.getCardSuit();
            cardRanks = packBySuit.getCardRanks();
            if (packBySuit.getCardRanks().size() >= FLUSH_SIZE_CARD) {
                this.cards = new ArrayList<>();
                for (int i = 0; i < FLUSH_SIZE_CARD; i++) {
                    cards.add(new Card(cardRanks.get(i), cardSuit));
                }
                super.setCards(this.cards);
                break;
            }
        }
    }

    /**
     * Checks whether the player's and community card is flush
     *
     * @param packBySuit group of cards arranged by suit
     * @return true if cards is flush else false
     */
    @Override
    public boolean checkFlush(PackBySuit packBySuit) {
        if (packBySuit.getCardRanks().size() >= FLUSH_SIZE_CARD) {
            return true;
        }

        return false;
    }
}
