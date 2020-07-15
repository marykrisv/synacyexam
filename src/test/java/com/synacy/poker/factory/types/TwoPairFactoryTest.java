package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TwoPairFactoryTest {

    @Test
    public void initializeCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        TwoPairFactory twoPairFactory = new TwoPairFactory();
        twoPairFactory.setCards(cards);
        twoPairFactory.initializeCards();

        assertEquals(true, twoPairFactory.check());
    }
}
