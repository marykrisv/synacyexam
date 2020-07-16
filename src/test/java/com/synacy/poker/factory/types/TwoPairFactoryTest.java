package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.utils.CardUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TwoPairFactoryTest {

    private void createInits(TwoPairFactory twoPairFactory, List<Card> cards) {
        twoPairFactory.setCards(cards);
        twoPairFactory.initializeCards();
        CardUtil.sortCardsDesc(cards);
        twoPairFactory.groupDeck();
    }

    @Test
    public void check_true() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.SPADES),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        TwoPairFactory twoPairFactory = new TwoPairFactory();
        this.createInits(twoPairFactory, cards);

        assertTrue(twoPairFactory.check());
    }

    @Test
    public void check_false() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.SPADES),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        TwoPairFactory twoPairFactory = new TwoPairFactory();
        this.createInits(twoPairFactory, cards);

        assertFalse(twoPairFactory.check());
    }

    @Test
    public void populateCards_firstCard() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.SPADES),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        List<Card> expectedFirstCard = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS)
        );

        TwoPairFactory twoPairFactory = new TwoPairFactory();
        this.createInits(twoPairFactory, cards);
        twoPairFactory.populateCards();

        assertEquals(expectedFirstCard, twoPairFactory.getFirstPairCards());
    }

    @Test
    public void populateCards_secondCard() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.SPADES),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        List<Card> expectedSecondCard = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS)
        );

        TwoPairFactory twoPairFactory = new TwoPairFactory();
        this.createInits(twoPairFactory, cards);
        twoPairFactory.populateCards();

        assertEquals(expectedSecondCard, twoPairFactory.getSecondPairCards());
    }

    @Test
    public void populateCards_otherCard() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.SPADES),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        List<Card> expectedOtherCard = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        TwoPairFactory twoPairFactory = new TwoPairFactory();
        this.createInits(twoPairFactory, cards);
        twoPairFactory.populateCards();

        assertEquals(expectedOtherCard, twoPairFactory.getOtherCards());
    }
}
