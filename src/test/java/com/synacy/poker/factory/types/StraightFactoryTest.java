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

public class StraightFactoryTest {
    private List<Card> cards;

    private void sortedCardsDesc () {
        Collections.sort(cards, Comparator.comparing(Card::getRank).reversed());
    }

    @Test
    public void check() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);

        this.cards = cards;
        this.sortedCardsDesc();
        straightFactory.initializeCards();

        assertEquals(true, straightFactory.check());
    }
}
