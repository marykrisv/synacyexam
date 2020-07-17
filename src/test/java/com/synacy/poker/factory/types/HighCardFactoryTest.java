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
        highCardFactory.create();

        assertEquals(cards, highCardFactory.getCards());
    }

    @Test
    public void populateCards_sixCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );

        List<Card> expected = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.HEARTS),
                new Card(CardRank.TEN, CardSuit.DIAMONDS),
                new Card(CardRank.FOUR, CardSuit.DIAMONDS)
        );

        HighCardFactory highCardFactory = new HighCardFactory();
        highCardFactory.setCards(cards);
        highCardFactory.create();

        assertEquals(expected, highCardFactory.getCards());
    }

    @Test
    public void populateCards_fiveCards() {
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
        highCardFactory.create();

        assertEquals(expected, highCardFactory.getCards());
    }

    @Test
    public void populateCards_twoCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TEN, CardSuit.HEARTS),
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS)
        );

        List<Card> expected = Arrays.asList(
                new Card(CardRank.QUEEN, CardSuit.DIAMONDS),
                new Card(CardRank.TEN, CardSuit.HEARTS)
        );

        HighCardFactory highCardFactory = new HighCardFactory();
        highCardFactory.setCards(cards);
        highCardFactory.create();

        assertEquals(expected, highCardFactory.getCards());
    }
}
