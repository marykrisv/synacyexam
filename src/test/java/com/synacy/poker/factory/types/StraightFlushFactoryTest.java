package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.utils.CardUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class StraightFlushFactoryTest {
    private void createInits(StraightFlushFactory straightFlushFactory, List<Card> cards) {
        straightFlushFactory.setCards(cards);
        straightFlushFactory.initializeCards();
        CardUtil.sortCardsDesc(cards);
        straightFlushFactory.groupDeck();
    }

    @Test
    public void initializeCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        straightFlushFactory.setCards(cards);
        straightFlushFactory.initializeCards();

        assertEquals(cards, straightFlushFactory.getCards());
    }

    @Test
    public void check_true() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.CLUBS)
        );

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);

        assertTrue(straightFlushFactory.check());
    }

    @Test
    public void check_false() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.SPADES),
                new Card(CardRank.JACK, CardSuit.DIAMONDS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);

        assertFalse(straightFlushFactory.check());
    }

    @Test
    public void true_5high() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.DIAMONDS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.HEARTS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS),
                new Card(CardRank.TEN, CardSuit.SPADES),
                new Card(CardRank.EIGHT, CardSuit.SPADES)
        );

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);

        assertFalse(straightFlushFactory.check());
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

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);

        assertFalse(straightFlushFactory.check());
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

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);

        assertFalse(straightFlushFactory.check());
    }

    @Test
    public void true_normal() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.JACK, CardSuit.SPADES),
                new Card(CardRank.QUEEN, CardSuit.SPADES),
                new Card(CardRank.ACE, CardSuit.SPADES),
                new Card(CardRank.KING, CardSuit.SPADES),
                new Card(CardRank.SEVEN, CardSuit.SPADES),
                new Card(CardRank.TEN, CardSuit.SPADES),
                new Card(CardRank.SEVEN, CardSuit.SPADES)
        );

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);

        assertTrue(straightFlushFactory.check());
    }

    @Test
    public void true_fiveHigh() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.SPADES),
                new Card(CardRank.TWO, CardSuit.SPADES),
                new Card(CardRank.SEVEN, CardSuit.SPADES),
                new Card(CardRank.THREE, CardSuit.SPADES),
                new Card(CardRank.FOUR, CardSuit.SPADES),
                new Card(CardRank.JACK, CardSuit.SPADES),
                new Card(CardRank.FIVE, CardSuit.SPADES)
        );

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);

        assertTrue(straightFlushFactory.check());
    }

    @Test
    public void true_lastCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.KING, CardSuit.HEARTS),
                new Card(CardRank.TEN, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS),
                new Card(CardRank.EIGHT, CardSuit.HEARTS),
                new Card(CardRank.NINE, CardSuit.HEARTS),
                new Card(CardRank.SIX, CardSuit.HEARTS)
        );

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);

        assertTrue(straightFlushFactory.check());
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

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);
        straightFlushFactory.populateCards();

        assertEquals(cardsExpected, straightFlushFactory.getCards());
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

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);
        straightFlushFactory.populateCards();

        assertEquals(cardsExpected, straightFlushFactory.getCards());
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

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);
        straightFlushFactory.populateCards();

        assertEquals(cardsExpected, straightFlushFactory.getCards());
    }

    @Test
    public void populateCards_lastCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.KING, CardSuit.DIAMONDS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS),
                new Card(CardRank.EIGHT, CardSuit.DIAMONDS),
                new Card(CardRank.NINE, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS)
        );

        List<Card> cardsExpected = Arrays.asList(
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.NINE, CardSuit.DIAMONDS),
                new Card(CardRank.EIGHT, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.DIAMONDS)
        );

        StraightFlushFactory straightFlushFactory = new StraightFlushFactory();
        this.createInits(straightFlushFactory, cards);
        straightFlushFactory.populateCards();

        assertEquals(cardsExpected, straightFlushFactory.getCards());
    }
}
