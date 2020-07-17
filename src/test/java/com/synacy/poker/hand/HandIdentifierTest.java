package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HandIdentifierTest {

    @Test
    public void initializeMap () {
        HandIdentifier handIdentifier = new HandIdentifier();

        assertNotNull(handIdentifier.getHandFactoryTypes());
    }

    @Test
    public void identifyHand_highCard() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.SPADES),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.NINE, CardSuit.CLUBS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);
        assertEquals(hand.getHandType(), HandType.HIGH_CARD);
    }

    @Test
    public void identifyHand_onePairCard() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.FIVE, CardSuit.CLUBS),
                new Card(CardRank.SEVEN, CardSuit.HEARTS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.FOUR, CardSuit.SPADES),
                new Card(CardRank.ACE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.HEARTS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);
        assertEquals(hand.getHandType(), HandType.ONE_PAIR);
    }

    @Test
    public void identifyHand_twoPairCard() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.EIGHT, CardSuit.CLUBS),
                new Card(CardRank.NINE, CardSuit.HEARTS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.EIGHT, CardSuit.SPADES),
                new Card(CardRank.NINE, CardSuit.DIAMONDS),
                new Card(CardRank.FIVE, CardSuit.HEARTS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);
        assertEquals(hand.getHandType(), HandType.TWO_PAIR);
    }

    @Test
    public void identifyHand_flush() {
        List<Card> playerCard = Arrays.asList(
                new Card(CardRank.KING, CardSuit.CLUBS),
                new Card(CardRank.ACE, CardSuit.CLUBS)
        );

        List<Card> community = Arrays.asList(
                new Card(CardRank.SEVEN, CardSuit.CLUBS),
                new Card(CardRank.TWO, CardSuit.CLUBS),
                new Card(CardRank.JACK, CardSuit.CLUBS)
        );

        HandIdentifier handIdentifier = new HandIdentifier();
        Hand hand = handIdentifier.identifyHand(playerCard, community);
        assertEquals(hand.getHandType(), HandType.FLUSH);
    }
}
