package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.factory.HandFactory;
import com.synacy.poker.utils.CardUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OnePairFactoryTest {

    @Test
    public void initializeCards() {
        OnePairFactory onePairFactory = new OnePairFactory();
        onePairFactory.initializeCards();

        assertNotNull(onePairFactory.getOtherCards());
        assertNotNull(onePairFactory.getPairCards());
    }

    @Test
    public void check_true() {
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
        onePairFactory.initializeCards();
        CardUtil.sortCardsDesc(cards);

        assertTrue(onePairFactory.check());
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

        OnePairFactory onePairFactory = new OnePairFactory();
        onePairFactory.setCards(cards);
        onePairFactory.initializeCards();
        CardUtil.sortCardsDesc(cards);

        assertFalse(onePairFactory.check());
    }

    @Test
    public void populateCards_onePair() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.SPADES),
                new Card(CardRank.EIGHT, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS)
        );

        List<Card> onePairExpected = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS)
        );

        OnePairFactory onePairFactory = new OnePairFactory();
        onePairFactory.setCards(cards);
        onePairFactory.initializeCards();
        CardUtil.sortCardsDesc(cards);
        onePairFactory.populateCards();

        assertEquals(onePairExpected, onePairFactory.getPairCards());
    }

    @Test
    public void populateCards_otherCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.SPADES),
                new Card(CardRank.EIGHT, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS)
        );

        List<Card> otherCardsExpected = Arrays.asList(
                new Card(CardRank.JACK, CardSuit.SPADES),
                new Card(CardRank.EIGHT, CardSuit.DIAMONDS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS)
        );

        OnePairFactory onePairFactory = new OnePairFactory();
        onePairFactory.setCards(cards);
        onePairFactory.initializeCards();
        CardUtil.sortCardsDesc(cards);
        onePairFactory.populateCards();

        assertEquals(otherCardsExpected, onePairFactory.getOtherCards());
    }
}
