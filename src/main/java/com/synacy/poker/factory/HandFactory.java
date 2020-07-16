package com.synacy.poker.factory;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.utils.CardUtil;

import java.util.*;

public abstract class HandFactory {
    private List<Card> cards;
    private Map<CardRank, List<CardSuit>> groupedDeckByRank;
    private Map<CardSuit, List<CardRank>> groupedDeckBySuit;

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
        groupedDeckBySuit = new HashMap<>();

        List<CardRank> cardRank;
        for (Card card: cards) {
            try {
                cardRank = groupedDeckBySuit.get(card.getSuit());
                cardRank.add(card.getRank());
                groupedDeckBySuit.replace(card.getSuit(), cardRank);
            } catch (NullPointerException e) {
                cardRank = new ArrayList<>();
                cardRank.add(card.getRank());
                groupedDeckBySuit.put(card.getSuit(), cardRank);
            }
        }
    }

    public void groupDeckByRank () {
        groupedDeckByRank = new HashMap<>();

        List<CardSuit> cardSuit;
        for (Card card: cards) {
            try {
                cardSuit = groupedDeckByRank.get(card.getRank());
                cardSuit.add(card.getSuit());
                groupedDeckByRank.replace(card.getRank(), cardSuit);
            } catch (NullPointerException e) {
                cardSuit = new ArrayList<>();
                cardSuit.add(card.getSuit());
                groupedDeckByRank.put(card.getRank(), cardSuit);
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Map<CardRank, List<CardSuit>> getGroupedDeckByRank() {
        return groupedDeckByRank;
    }

    public void setGroupedDeckByRank(Map<CardRank, List<CardSuit>> groupedDeckByRank) {
        this.groupedDeckByRank = groupedDeckByRank;
    }

    public Map<CardSuit, List<CardRank>> getGroupedDeckBySuit() {
        return groupedDeckBySuit;
    }

    public void setGroupedDeckBySuit(Map<CardSuit, List<CardRank>> groupedDeckBySuit) {
        this.groupedDeckBySuit = groupedDeckBySuit;
    }
}
