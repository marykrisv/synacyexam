package com.synacy.poker.factory;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.utils.CardUtil;
import com.synacy.poker.utils.DeckByRank;
import com.synacy.poker.utils.DeckBySuit;

import java.util.*;

public abstract class HandFactory {
    private List<Card> cards;
    private List<DeckByRank> groupedDeckByRank;
    private List<DeckBySuit> groupedDeckBySuit;

    public abstract void initializeCards();

    public abstract boolean check();

    public abstract void populateCards();

    public abstract void groupDeck();

    public HandFactory() {}

    public HandFactory create () {
        this.initializeCards();
        CardUtil.sortCardsDesc(cards);
        this.groupDeck();
        if (this.check()) {
            populateCards();
            return this;
        } else {
            return null;
        }
    }

    public void groupDeckBySuit () {
        groupedDeckBySuit = new ArrayList<>();

        int cardSuitInd;

        for (Card card: cards) {
            cardSuitInd = deckBySuitContains(card.getSuit());
            if (cardSuitInd != -1) {
                groupedDeckBySuit.get(cardSuitInd).addCardRankToList(card.getRank());
            } else {
                DeckBySuit deckBySuit = new DeckBySuit();
                deckBySuit.setCardSuit(card.getSuit());
                deckBySuit.addCardRankToList(card.getRank());

                groupedDeckBySuit.add(deckBySuit);
            }
        }
    }

    private int deckBySuitContains(CardSuit cardSuit) {
        int i, cardInd = -1;
        for (i=0; i < groupedDeckBySuit.size(); i++) {
            if (groupedDeckBySuit.get(i).getCardSuit().equals(cardSuit)) {
                cardInd = i;
                break;
            }
        }

        return cardInd;
    }

    public void groupDeckByRank () {
        groupedDeckByRank = new ArrayList<>();

        int cardRankInd;

        for (Card card: cards) {
            cardRankInd = deckByRankContains(card.getRank());
            if (cardRankInd != -1) {
                groupedDeckByRank.get(cardRankInd).addCardSuitToList(card.getSuit());
            } else {
                DeckByRank deckByRank = new DeckByRank();
                deckByRank.setCardRank(card.getRank());
                deckByRank.addCardSuitToList(card.getSuit());

                groupedDeckByRank.add(deckByRank);
            }
        }
    }

    private int deckByRankContains(CardRank cardRank) {
        int i, cardInd = -1;
        for (i=0; i < groupedDeckByRank.size(); i++) {
            if (groupedDeckByRank.get(i).getCardRank().equals(cardRank)) {
                cardInd = i;
                break;
            }
        }

        return cardInd;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<DeckByRank> getGroupedDeckByRank() {
        return groupedDeckByRank;
    }

    public void setGroupedDeckByRank(List<DeckByRank> groupedDeckByRank) {
        this.groupedDeckByRank = groupedDeckByRank;
    }

    public List<DeckBySuit> getGroupedDeckBySuit() {
        return groupedDeckBySuit;
    }

    public void setGroupedDeckBySuit(List<DeckBySuit> groupedDeckBySuit) {
        this.groupedDeckBySuit = groupedDeckBySuit;
    }
}
