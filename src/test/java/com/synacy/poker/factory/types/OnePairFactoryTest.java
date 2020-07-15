package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OnePairFactoryTest {
    private List<Card> cards;

    private void sortedCardsDesc () {
        Collections.sort(cards, Comparator.comparing(Card::getRank).reversed());
    }

    @Test
    public void initializeCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        OnePairFactory onePairFactory = new OnePairFactory();
        onePairFactory.setCards(cards);

        this.cards = cards;
        this.sortedCardsDesc();
        onePairFactory.initializeCards();

        assertEquals(true, onePairFactory.check());
    }
}
