package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.types.Flush;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlushFactoryTest {
    @Test
    public void initializeCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.THREE, CardSuit.HEARTS),
                new Card(CardRank.SIX, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.CLUBS)
        );

        FlushFactory flushFactory = new FlushFactory();
        flushFactory.setCards(cards);
        flushFactory.initializeCards();

        assertEquals(false, flushFactory.check());
    }
}
