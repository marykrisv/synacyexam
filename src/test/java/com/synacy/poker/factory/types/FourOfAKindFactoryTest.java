package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FourOfAKindFactoryTest {
    @Test
    public void initializeCards() {
        FourOfAKindFactory fourOfAKindFactory = new FourOfAKindFactory();
        fourOfAKindFactory.initializeCards();

        assertNotNull(fourOfAKindFactory.getOtherCards());
        assertNotNull(fourOfAKindFactory.getFourOfAKindCards());
    }

    @Test
    public void check_true() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.SPADES),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        FourOfAKindFactory fourOfAKindFactory = new FourOfAKindFactory();
        fourOfAKindFactory.setCards(cards);
        fourOfAKindFactory.create();

        assertTrue(fourOfAKindFactory.check());
    }

    @Test
    public void check_false() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        FourOfAKindFactory fourOfAKindFactory = new FourOfAKindFactory();
        fourOfAKindFactory.setCards(cards);
        fourOfAKindFactory.create();

        assertFalse(fourOfAKindFactory.check());
    }

    @Test
    public void populateCards_fourOfAKind() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.DIAMONDS)
        );

        List<Card> fourOfAKindExcepected = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.DIAMONDS)
        );

        FourOfAKindFactory fourOfAKindFactory = new FourOfAKindFactory();
        fourOfAKindFactory.setCards(cards);
        fourOfAKindFactory.create();

        assertEquals(fourOfAKindExcepected, fourOfAKindFactory.getFourOfAKindCards());
    }

    @Test
    public void populateCards_otherCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.DIAMONDS)
        );

        List<Card> otherCardExcepted = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.HEARTS)
        );

        FourOfAKindFactory fourOfAKindFactory = new FourOfAKindFactory();
        fourOfAKindFactory.setCards(cards);
        fourOfAKindFactory.create();

        assertEquals(otherCardExcepted, fourOfAKindFactory.getOtherCards());
    }
}
