package com.synacy.poker.factory;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;

import java.util.*;

public abstract class HandFactory {
    private List<Card> cards;

    public abstract boolean check();

    public abstract void initializeCards();

    public HandFactory() {}

    public HandFactory create () {
        this.initializeCards();
        this.sortedCardsDesc();
        if (this.check()) {
            return this;
        } else {
            return null;
        }
    }

    private void sortedCardsDesc () {
        Collections.sort(cards, Comparator.comparing(Card::getRank).reversed());
    }

    public Map<CardSuit, List<CardRank>> groupDeckBySuit () {
        Map<CardSuit, List<CardRank>> groupedCard = new HashMap<>();

        // put a map for suits
        List<CardRank> cardRank;
        for (Card card: cards) {
            try {
                cardRank = groupedCard.get(card.getSuit());
                cardRank.add(card.getRank());
                groupedCard.replace(card.getSuit(), cardRank);
            } catch (NullPointerException e) {
                cardRank = new ArrayList<>();
                cardRank.add(card.getRank());
                groupedCard.put(card.getSuit(), cardRank);
            }
        }

        return groupedCard;
    }

    public Map<CardRank, List<CardSuit>> groupDeckByRank () {
        Map<CardRank, List<CardSuit>> groupedCard = new HashMap<>();

        // put a map for suits
        List<CardSuit> cardSuit;
        for (Card card: cards) {
            try {
                cardSuit = groupedCard.get(card.getRank());
                cardSuit.add(card.getSuit());
                groupedCard.replace(card.getRank(), cardSuit);
            } catch (NullPointerException e) {
                cardSuit = new ArrayList<>();
                cardSuit.add(card.getSuit());
                groupedCard.put(card.getRank(), cardSuit);
            }
        }

        return groupedCard;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
