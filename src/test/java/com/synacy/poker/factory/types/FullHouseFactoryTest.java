package com.synacy.poker.factory.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.utils.CardUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FullHouseFactoryTest {
    private void createInits(FullHouseFactory fullHouseFactory, List<Card> cards) {
        fullHouseFactory.setCards(cards);
        fullHouseFactory.initializeCards();
        CardUtil.sortCardsDesc(cards);
        fullHouseFactory.groupDeck();
    }

    @Test
    public void check_true() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        FullHouseFactory fullHouseFactory = new FullHouseFactory();
        this.createInits(fullHouseFactory, cards);

        assertTrue(fullHouseFactory.check());
    }

    @Test
    public void check_false() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.SEVEN, CardSuit.SPADES),
                new Card(CardRank.QUEEN, CardSuit.CLUBS),
                new Card(CardRank.QUEEN, CardSuit.CLUBS)
        );

        FullHouseFactory fullHouseFactory = new FullHouseFactory();
        this.createInits(fullHouseFactory, cards);

        assertFalse(fullHouseFactory.check());
    }

    @Test
    public void populateCards_pairCards() {
        List<Card> cards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.HEARTS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        List<Card> expectedPairCards = Arrays.asList(
                new Card(CardRank.TWO, CardSuit.DIAMONDS),
                new Card(CardRank.TWO, CardSuit.HEARTS)
        );

        FullHouseFactory fullHouseFactory = new FullHouseFactory();
        this.createInits(fullHouseFactory, cards);
        fullHouseFactory.populateCards();

        assertEquals(expectedPairCards, fullHouseFactory.getPairCards());
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

        List<Card> expectedThreeOfAKind = Arrays.asList(
                new Card(CardRank.ACE, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.HEARTS),
                new Card(CardRank.ACE, CardSuit.DIAMONDS)
        );

        FullHouseFactory fullHouseFactory = new FullHouseFactory();
        this.createInits(fullHouseFactory, cards);
        fullHouseFactory.populateCards();

        assertEquals(expectedThreeOfAKind, fullHouseFactory.getThreeOfAKindCards());
    }
}
