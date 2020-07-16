package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.utils.CardUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HighCardFactoryTest {

    @Test
    public void initializeCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS)
        );

        HighCardFactory highCardFactory = new HighCardFactory();
        highCardFactory.setCards(cards);
        highCardFactory.initializeCards();
        CardUtil.sortCardsDesc(cards);

        assertEquals(cards, highCardFactory.getCards());
    }

    @Test
    public void check() {
        HighCardFactory highCardFactory = new HighCardFactory();

        assertTrue(highCardFactory.check());
    }

    @Test
    public void populateCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS)
        );

        List<Card> expected = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS)
        );

        HighCardFactory highCardFactory = new HighCardFactory();
        highCardFactory.setCards(cards);
        highCardFactory.initializeCards();
        CardUtil.sortCardsDesc(cards);
        highCardFactory.populateCards();

        assertEquals(expected, highCardFactory.getCards());
    }
}
