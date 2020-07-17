package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ThreeOfAKindFactoryTest {

    @Test
    public void initializeCards() {
        ThreeOfAKindFactory threeOfAKindFactory = new ThreeOfAKindFactory();
        threeOfAKindFactory.initializeCards();

        assertNotNull(threeOfAKindFactory.getOtherCards());
        assertNotNull(threeOfAKindFactory.getThreeOfAKindCards());
    }

    @Test
    public void check_true() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        ThreeOfAKindFactory threeOfAKindFactory = new ThreeOfAKindFactory();
        threeOfAKindFactory.setCards(cards);
        threeOfAKindFactory.create();

        assertTrue(threeOfAKindFactory.check());
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

        ThreeOfAKindFactory threeOfAKindFactory = new ThreeOfAKindFactory();
        threeOfAKindFactory.setCards(cards);
        threeOfAKindFactory.create();

        assertFalse(threeOfAKindFactory.check());
    }

    @Test
    public void populateCards_threeOfAKind() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        List<Card> threeOfAKindExcepected = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        ThreeOfAKindFactory threeOfAKindFactory = new ThreeOfAKindFactory();
        threeOfAKindFactory.setCards(cards);
        threeOfAKindFactory.create();

        assertEquals(threeOfAKindExcepected, threeOfAKindFactory.getThreeOfAKindCards());
    }

    @Test
    public void populateCards_otherCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        List<Card> otherCardExcepted = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.HEARTS)
        );

        ThreeOfAKindFactory threeOfAKindFactory = new ThreeOfAKindFactory();
        threeOfAKindFactory.setCards(cards);
        threeOfAKindFactory.create();

        assertEquals(otherCardExcepted, threeOfAKindFactory.getOtherCards());
    }
}
