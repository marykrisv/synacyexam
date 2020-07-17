package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class StraightFactoryTest {

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

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertEquals(cards, straightFactory.getCards());
    }

    @Test
    public void check_true() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertTrue(straightFactory.check());
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

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertFalse(straightFactory.check());
    }

    @Test
    public void false_4_straight() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.DIAMONDS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.HEARTS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.TEN, CardSuit.SPADES),
                new Card(CardRank.EIGHT, CardSuit.SPADES)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertFalse(straightFactory.check());
    }

    @Test
    public void false_normal() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.DIAMONDS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS),
                new Card(CardRank.TEN, CardSuit.SPADES),
                new Card(CardRank.QUEEN, CardSuit.SPADES)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertFalse(straightFactory.check());
    }

    @Test
    public void true_normal() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.KING, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS),
                new Card(CardRank.TEN, CardSuit.SPADES),
                new Card(CardRank.SEVEN, CardSuit.SPADES)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertTrue(straightFactory.check());
    }

    @Test
    public void true_fiveHigh() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS),
                new Card(CardRank.THREE, CardSuit.HEARTS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS),
                new Card(CardRank.JACK, CardSuit.SPADES),
                new Card(CardRank.FIVE, CardSuit.SPADES)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertTrue(straightFactory.check());
    }

    @Test
    public void true_lastCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.DIAMONDS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS),
                new Card(CardRank.EIGHT, CardSuit.DIAMONDS),
                new Card(CardRank.NINE, CardSuit.SPADES),
                new Card(CardRank.SIX, CardSuit.SPADES)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertTrue(straightFactory.check());
    }

    @Test
    public void populateCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.CLUBS)
        );

        List<Card> cardsExpected = Arrays.asList(
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.SIX, CardSuit.CLUBS)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertEquals(cardsExpected, straightFactory.getCards());
    }

    @Test
    public void populateCards_AceHigh() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        List<Card> cardsExpected = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertEquals(cardsExpected, straightFactory.getCards());
    }

    @Test
    public void populateCards_FiveHigh() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.CLUBS)
        );

        List<Card> cardsExpected = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.FOUR, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertEquals(cardsExpected, straightFactory.getCards());
    }

    @Test
    public void populateCards_lastCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.DIAMONDS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS),
                new Card(CardRank.EIGHT, CardSuit.DIAMONDS),
                new Card(CardRank.NINE, CardSuit.SPADES),
                new Card(CardRank.SIX, CardSuit.SPADES)
        );

        List<Card> cardsExpected = Arrays.asList(
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.NINE, CardSuit.SPADES),
                new Card(CardRank.EIGHT, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS),
                new Card(CardRank.SIX, CardSuit.SPADES)
        );

        StraightFactory straightFactory = new StraightFactory();
        straightFactory.setCards(cards);
        straightFactory.create();

        assertEquals(cardsExpected, straightFactory.getCards());
    }
}
