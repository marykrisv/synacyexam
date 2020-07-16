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

public class FlushFactoryTest {
    private void createInits(FlushFactory flushFactory, List<Card> cards) {
        flushFactory.setCards(cards);
        flushFactory.initializeCards();
        CardUtil.sortCardsDesc(cards);
        flushFactory.groupDeck();
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

        FlushFactory flushFactory = new FlushFactory();
        flushFactory.setCards(cards);
        flushFactory.initializeCards();

        assertEquals(cards, flushFactory.getCards());
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

        FlushFactory flushFactory = new FlushFactory();
        this.createInits(flushFactory, cards);

        assertTrue(flushFactory.check());
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

        FlushFactory flushFactory = new FlushFactory();
        this.createInits(flushFactory, cards);

        assertFalse(flushFactory.check());
    }

    @Test
    public void populateCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        List<Card> onePairExpected = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS)
        );

        FlushFactory flushFactory = new FlushFactory();
        this.createInits(flushFactory, cards);
        flushFactory.populateCards();

        assertEquals(onePairExpected, flushFactory.getCards());
    }
}
