package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FullHouseFactoryTest {
    @Test
    public void initializeCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS)
        );

        FullHouseFactory fullHouseFactory = new FullHouseFactory();
        fullHouseFactory.setCards(cards);
        fullHouseFactory.initializeCards();

        assertEquals(true, fullHouseFactory.check());
    }

    @Test
    public void initializeCards1() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.THREE, CardSuit.CLUBS)
        );

        FullHouseFactory fullHouseFactory = new FullHouseFactory();
        fullHouseFactory.setCards(cards);
        fullHouseFactory.initializeCards();

        assertEquals(false, fullHouseFactory.check());
    }
}
